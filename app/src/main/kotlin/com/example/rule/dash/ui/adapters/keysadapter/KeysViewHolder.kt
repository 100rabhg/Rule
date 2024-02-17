package com.example.rule.dash.ui.adapters.keysadapter

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.rule.dash.R
import com.example.rule.dash.data.model.KeyData
import com.example.rule.dash.data.preference.DataSharePreference.getSelectedItem
import com.pawegio.kandroid.hide
import com.pawegio.kandroid.show
import de.hdodenhof.circleimageview.CircleImageView
import kotterknife.bindView

class KeysViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val txtKeyData: TextView by bindView(R.id.txt_key_text)
    private val imgCheck: CircleImageView by bindView(R.id.img_selected_calls)
    private val card : CardView by bindView(R.id.card_view_key)
    val itemClick: LinearLayout by bindView(R.id.item_click_key)
    fun bind(item: KeyData) {
        txtKeyData.text = item.keyText
    }

    fun isSelected(key:String){
        if (itemView.context.getSelectedItem(key)){
            card.setCardBackgroundColor(ContextCompat.getColor(itemView.context,R.color.colorSelected))
            imgCheck.show()
        }else{
            imgCheck.hide()
            card.setCardBackgroundColor(ContextCompat.getColor(itemView.context,R.color.colorWhite))
        }
    }
}