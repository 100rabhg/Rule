package com.example.rule.dash.ui.adapters.photoadapter

import com.example.rule.dash.ui.adapters.basedapter.InterfaceAdapter

interface InterfacePhotoAdapter : InterfaceAdapter{
    fun onItemClick(url:String,keyFileName: String,childName: String,holder: PhotoViewHolder,position:Int)
    fun onLongClickDeleteFilePhoto(keyFileName: String, childName: String,position:Int)
}