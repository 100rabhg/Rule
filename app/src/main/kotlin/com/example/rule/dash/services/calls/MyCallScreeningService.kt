package com.example.rule.dash.services.calls

import android.os.Build
import android.telecom.Call
import android.telecom.CallScreeningService
import android.util.Log
import com.example.rule.dash.data.model.Calls
import com.example.rule.dash.data.preference.DataSharePreference.childSelected
import com.example.rule.dash.data.preference.DataSharePreference.typeApp
import com.example.rule.dash.utils.ConstFun
import com.example.rule.dash.utils.Consts
import com.example.rule.dash.utils.Consts.CALL_BLOCKED
import com.example.rule.dash.utils.Consts.CALL_NOT_BLOCKED
import com.example.rule.dash.utils.Consts.TYPE_CALL_INCOMING
import com.example.rule.dash.utils.Consts.TYPE_CALL_OUTGOING
import com.example.rule.dash.utils.FileHelper.getContactName
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.database.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class MyCallScreeningService : CallScreeningService() {

    private var db: FirebaseFirestore =  Firebase.firestore
    private var user: FirebaseUser? =  Firebase.auth.currentUser
    override fun onScreenCall(callDetails: Call.Details) {
        // Check if call is incoming and get the phone number
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val incomingNumber = extractPhoneNumber(callDetails)
            Log.d("MyCallScreeningService", "Active Call = $incomingNumber")
            val type = if (callDetails.callDirection == Call.Details.DIRECTION_INCOMING) {
                TYPE_CALL_OUTGOING
            }else{
                TYPE_CALL_INCOMING
            }
            val blockList = mutableListOf<String>()
            if (user != null && !typeApp) {
                db.collection("users").document(user!!.uid).collection("block")
                    .whereEqualTo("block", true).get().addOnSuccessListener{ documents ->
                        for (document in documents) {
                            document.getString("number")?.let { blockList.add(it) }
                        }
                        var blockCall = false
                        if(incomingNumber != null && blockList.any { incomingNumber.endsWith(it)}){
                            Log.d("MyCallScreeningService", "block hit at number:$incomingNumber on list $blockList")
                            blockCall = true
                        }
                        respondBlock(blockCall, callDetails)
                        val calls = Calls(getContactName(incomingNumber), incomingNumber, ConstFun.getDateTime(), type, CALL_BLOCKED)
                        uploadCall(calls)
                    }.addOnFailureListener{
                        it.printStackTrace()
                        respondBlock(false, callDetails)
                        val calls = Calls(getContactName(incomingNumber), incomingNumber, ConstFun.getDateTime(), type, CALL_NOT_BLOCKED)
                        uploadCall(calls)
                    }
            }
            else{
                respondBlock(false, callDetails)
                val calls = Calls(getContactName(incomingNumber), incomingNumber, ConstFun.getDateTime(), type, CALL_NOT_BLOCKED)
                uploadCall(calls)
            }
        }
    }

    private fun uploadCall(calls: Calls){
        user?.let { Firebase.database.getReference(Consts.USER).child(it.uid).child(childSelected).child("${Consts.CALLS}/${Consts.DATA}").push().setValue(calls) }
    }

    private fun respondBlock(isBlock : Boolean, callDetails : Call.Details){
        val callResponse = CallResponse.Builder()
            .setDisallowCall(isBlock)
            .setSkipCallLog(isBlock)
            .setRejectCall(isBlock)
            .setSkipNotification(isBlock)
            .build()
        respondToCall(callDetails, callResponse)
    }

    private fun extractPhoneNumber(callDetails: Call.Details): String? {
        val handle = callDetails.handle
        if (handle != null) {
            return handle.schemeSpecificPart
        } else {
            val gatewayInfo = callDetails.gatewayInfo
            if (gatewayInfo != null) {
                return gatewayInfo.originalAddress.schemeSpecificPart
            }
        }
        return null
    }

}