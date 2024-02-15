package com.example.rule.dash.ui.adapters.callsadapter

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.*
import com.example.rule.dash.R
import com.example.rule.dash.data.model.Calls
import kotterknife.bindView
import com.example.rule.dash.utils.Consts.TYPE_CALL_INCOMING
import com.example.rule.dash.utils.Consts.TYPE_CALL_OUTGOING
import com.pawegio.kandroid.hide
import com.pawegio.kandroid.show
import de.hdodenhof.circleimageview.CircleImageView
import com.example.rule.dash.data.preference.DataSharePreference.getSelectedItem
import com.example.rule.dash.utils.Consts.CALL_BLOCKED
import com.example.rule.dash.utils.Consts.TYPE_CALL_INCOMING_WHATSCALL

class CallsViewHolder(view: View, val context: Context) : RecyclerView.ViewHolder(view) {

    private val contact: TextView by bindView(R.id.contact_item_calls)
    private val phoneNumber: TextView by bindView(R.id.phone_number_item_calls)
    private val dateTime: TextView by bindView(R.id.date_item_calls)
    val itemClick: LinearLayout by bindView(R.id.item_click_calls)
    private val imgCallType : ImageView by bindView(R.id.img_type_call)
    private val imgBlocked : ImageView by bindView(R.id.img_call_blocked)
    private val imgCheck : CircleImageView by bindView(R.id.img_selected_calls)
    private val card : androidx.cardview.widget.CardView by bindView(R.id.cardview_calls)

    fun isSelectedItem(key:String){
        if (itemView.context.getSelectedItem(key)){
            imgCheck.show()
            card.setCardBackgroundColor(ContextCompat.getColor(itemView.context,R.color.colorSelected))
        }else{
            card.setCardBackgroundColor(ContextCompat.getColor(itemView.context,R.color.colorWhite))
            imgCheck.hide()
        }
    }

    fun bind(item: Calls) {
        contact.text = item.contact
        phoneNumber.text = item.phoneNumber
        dateTime.text = item.dateTime
        if (item.type== TYPE_CALL_OUTGOING) imgCallType.setImageResource(R.drawable.ic_made_green_24dp)
        if (item.type== TYPE_CALL_INCOMING) imgCallType.setImageResource(R.drawable.ic_received_blue_24dp)
        if (item.type== TYPE_CALL_INCOMING_WHATSCALL) imgCallType.setImageResource(R.drawable.ic_whatsapp)
        if (item.blocked == CALL_BLOCKED) imgBlocked.setImageResource(R.drawable.ic_call_block)
    }

}