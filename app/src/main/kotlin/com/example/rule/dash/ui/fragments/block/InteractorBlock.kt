package com.example.rule.dash.ui.fragments.block

import android.content.Context
import androidx.fragment.app.FragmentManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.rule.dash.R
import com.example.rule.dash.data.model.Block
import com.example.rule.dash.data.model.DataDelete
import com.example.rule.dash.data.rxFirebase.InterfaceFirebase
import com.example.rule.dash.ui.activities.base.BaseInteractor
import com.example.rule.dash.ui.adapters.blockadapter.BlockRecyclerAdapter
import com.example.rule.dash.ui.adapters.blockadapter.InterfaceBlockAdapter
import com.example.rule.dash.utils.Consts.BLOCK
import com.example.rule.dash.utils.Consts.CALLS
import com.google.firebase.database.DatabaseError
import javax.inject.Inject

class InteractorBlock<V : InterfaceViewBlock> @Inject constructor(
    supportFragment: FragmentManager,
    context: Context,
    firebase: InterfaceFirebase
) : BaseInteractor<V>(supportFragment, context, firebase), InterfaceInteractorBlock<V>,
    InterfaceBlockAdapter {

    private var recyclerAdapter: BlockRecyclerAdapter? = null

    override fun setSearchQuery(query: String) {
        if (recyclerAdapter != null) recyclerAdapter!!.setFilter(query)
    }

    override fun setRecyclerAdapter() {
        recyclerAdapter =
            BlockRecyclerAdapter(firebase().getDatabaseReference("$CALLS/$BLOCK").limitToLast(300))
        getView()!!.setRecyclerAdapter(recyclerAdapter!!)
        recyclerAdapter!!.onRecyclerAdapterListener(this)
    }

    override fun startRecyclerAdapter() {
        if (recyclerAdapter != null) recyclerAdapter!!.startListening()
    }

    override fun stopRecyclerAdapter() {
        if (recyclerAdapter != null) recyclerAdapter!!.stopListening()
    }

    override fun notifyDataSetChanged() {
        if (recyclerAdapter != null) recyclerAdapter!!.notifyDataSetChanged()
    }

    override fun notifyItemChanged(position: Int) {
        if (recyclerAdapter != null) recyclerAdapter!!.notifyItemChanged(position)
    }

    override fun onItemClick(key: String, position: Int) {
        if (getMultiSelected()) if (isNullView()) getView()!!.onItemClick(key, "", "", position)
    }

    override fun onItemLongClick(key: String, position: Int) {
        if (isNullView()) getView()!!.onItemLongClick(key, "", "", position)
    }

    override fun successResult(result: Boolean, filter: Boolean) {
        if (getView() != null) getView()!!.successResult(result, filter)
    }

    override fun failedResult(error: DatabaseError) {
        if (getView() != null) getView()!!.failedResult(Throwable(error.message))
    }

    override fun onDeleteData(data: MutableList<DataDelete>) {
        getView()!!.showDialog(
            SweetAlertDialog.WARNING_TYPE,
            R.string.title_dialog,
            getContext().getString(R.string.message_dialog_delete_block),
            R.string.unblock,
            true
        ) {
            setConfirmClickListener {
                setMultiSelected(false)
                for (i in 0 until data.size) {
                    firebase().getDatabaseReference("$CALLS/$BLOCK/${data[i].key}").removeValue()
                        .addOnCompleteListener {
                            if (i == data.size - 1) getView()!!.setActionToolbar(false)
                        }
                }
                getView()!!.hideDialog()
            }
            show()
        }
    }

    override fun blockNumber(number: String) {
        val block = Block(number)
        firebase().getDatabaseReference("$CALLS/$BLOCK").push().setValue(block)
    }
}