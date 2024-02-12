package com.example.rule.dash.ui.adapters.keysadapter

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.rule.dash.R
import com.example.rule.dash.data.model.KeyData
import kotterknife.bindView

class KeysViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val txtKeyData: TextView by bindView(R.id.txt_key_text)

    fun bind(item: KeyData) {
        txtKeyData.text = item.keyText
    }

}