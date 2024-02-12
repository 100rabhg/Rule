package com.example.rule.dash.ui.adapters.callsadapter

import com.example.rule.dash.ui.adapters.basedapter.InterfaceAdapter
import java.io.File

interface InterfaceCallsAdapter : InterfaceAdapter {
    fun onCheckPermissionAudioCalls(key:String,file: File, childName: String, fileName: String, holder: CallsViewHolder,position:Int)
    fun onClickDownloadAudioCall(file: File, childName: String, holder: CallsViewHolder)
    fun onLongClickDeleteFileCall(keyFileName: String, fileName: String, childName: String,position:Int)
}