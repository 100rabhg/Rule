package com.example.rule.dash.services.calls

import android.os.Build
import android.telecom.Call
import android.telecom.CallScreeningService
import android.util.Log
import com.example.rule.dash.data.preference.DataSharePreference.typeApp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class MyCallScreeningService : CallScreeningService() {
    override fun onScreenCall(callDetails: Call.Details) {
        // Check if call is incoming and get the phone number
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val incomingNumber = extractPhoneNumber(callDetails)
            Log.d("MyCallScreeningService", "Active Call = $incomingNumber")
            val db = Firebase.firestore
            val user = Firebase.auth.currentUser

            val blockList = mutableListOf<String>()
            if (user != null && !typeApp) {
                db.collection("users").document(user.uid).collection("block")
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
                    }.addOnFailureListener{
                        it.printStackTrace()
                        respondBlock(false, callDetails)
                    }
            }
            else{
                respondBlock(false, callDetails)
            }
        }
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