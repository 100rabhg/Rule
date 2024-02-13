package com.example.rule.dash.ui.adapters.blockadapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.rule.dash.R
import com.example.rule.dash.data.model.Block
import com.example.rule.dash.data.preference.DataSharePreference.getSelectedItem
import kotterknife.bindView

class BlockViewHolder(view: View, val context: Context) : RecyclerView.ViewHolder(view) {

    val itemClick: LinearLayout by bindView(R.id.item_click_block)
    private val address: TextView by bindView(R.id.address_item_block)
    private val iconBlock: ImageView by bindView(R.id.img_type_block)
    private val card: CardView by bindView(R.id.cardview_block)
    fun isSelectedItem(key: String) {
        if (itemView.context.getSelectedItem(key)) {
            iconBlock.setImageResource(R.drawable.ic_check)
            card.setCardBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.colorSelected
                )
            )
        } else {
            iconBlock.setImageResource(R.drawable.phone_disabled)
            card.setCardBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.colorWhite
                )
            )
        }
    }

    fun bind(block: Block) {
        this.address.text = block.address
    }

}