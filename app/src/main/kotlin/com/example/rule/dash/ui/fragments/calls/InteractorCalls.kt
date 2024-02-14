package com.example.rule.dash.ui.fragments.calls

import android.content.Context
import androidx.fragment.app.FragmentManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.rule.dash.R
import com.example.rule.dash.data.model.DataDelete
import com.example.rule.dash.data.rxFirebase.InterfaceFirebase
import com.example.rule.dash.ui.activities.base.BaseInteractor
import com.example.rule.dash.ui.adapters.callsadapter.CallsRecyclerAdapter
import com.example.rule.dash.ui.adapters.callsadapter.InterfaceCallsAdapter
import com.example.rule.dash.utils.Consts.CALLS
import com.example.rule.dash.utils.Consts.DATA
import com.google.firebase.database.DatabaseError
import javax.inject.Inject

class InteractorCalls<V: InterfaceViewCalls> @Inject constructor(supportFragment: FragmentManager, context: Context, firebase: InterfaceFirebase) : BaseInteractor<V>(supportFragment, context,firebase), InterfaceInteractorCalls<V>, InterfaceCallsAdapter {

    private var recyclerAdapter:CallsRecyclerAdapter?=null

    override fun setSearchQuery(query: String) {
        if (recyclerAdapter!=null)recyclerAdapter!!.setFilter(query)
    }

    override fun setRecyclerAdapter() {
        recyclerAdapter = CallsRecyclerAdapter(firebase().getDatabaseReference("$CALLS/$DATA").limitToLast(300))
        getView()!!.setRecyclerAdapter(recyclerAdapter!!)
        recyclerAdapter!!.onRecyclerAdapterListener(this)
    }

    override fun startRecyclerAdapter() {
        if (recyclerAdapter!=null) recyclerAdapter!!.startListening()
    }

    override fun stopRecyclerAdapter() {
        if (recyclerAdapter!=null) recyclerAdapter!!.stopListening()
    }

    override fun notifyDataSetChanged() {
        if (recyclerAdapter!=null) recyclerAdapter!!.notifyDataSetChanged()
    }

    override fun notifyItemChanged(position: Int) {
        if (recyclerAdapter!=null) recyclerAdapter!!.notifyItemChanged(position)
    }

    override fun successResult(result: Boolean, filter:Boolean) {
        if (getView()!=null) getView()!!.successResult(result,filter)
    }

    override fun failedResult(error: DatabaseError) {
        if (getView()!=null) getView()!!.failedResult(Throwable(error.message))
    }

    override fun onLongClick(keyFileName:String,fileName: String,childName: String,position:Int) {
        if (isNullView()) getView()!!.onItemLongClick(keyFileName,childName,fileName,position)
    }

    override fun onDeleteData(data: MutableList<DataDelete>) {
        getView()!!.showDialog(SweetAlertDialog.WARNING_TYPE,R.string.title_dialog,getContext().getString(R.string.message_dialog_delete_call_audio),
                R.string.delete,true){
            setConfirmClickListener {
                setMultiSelected(false)
                for (i in 0 until data.size){
                    firebase().getStorageReference("$CALLS/${data[i].child}").delete()
                    firebase().getDatabaseReference("$CALLS/$DATA/${data[i].key}").removeValue().addOnCompleteListener {
                        if (i==data.size-1) getView()!!.setActionToolbar(false)
                    }
                }
                getView()!!.hideDialog()
            }
            show()
        }
    }

}