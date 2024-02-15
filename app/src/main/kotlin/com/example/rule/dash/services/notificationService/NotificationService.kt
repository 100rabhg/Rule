package com.example.rule.dash.services.notificationService

import android.annotation.SuppressLint
import android.app.Notification
import android.content.ContentResolver
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.example.rule.app.Dash
import com.example.rule.dash.data.model.Calls
import com.example.rule.dash.data.preference.DataSharePreference.childSelected
import com.example.rule.dash.data.preference.DataSharePreference.typeApp
import com.example.rule.dash.utils.ConstFun
import com.example.rule.dash.utils.Consts
import com.example.rule.dash.utils.Consts.FACEBOOK_MESSENGER_PACK_NAME
import com.example.rule.dash.utils.Consts.INSTAGRAM_PACK_NAME
import com.example.rule.dash.utils.Consts.TYPE_CALL_INCOMING_WHATSCALL
import com.example.rule.dash.utils.Consts.TYPE_INSTAGRAM
import com.example.rule.dash.utils.Consts.TYPE_MESSENGER
import com.example.rule.dash.utils.Consts.TYPE_WHATSAPP
import com.example.rule.dash.utils.Consts.WHATSAPP_PACK_NAME
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.database.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import javax.inject.Inject
import kotlin.random.Random

class NotificationService : NotificationListenerService() {

    @Inject lateinit var interactor:InteractorNotificationService

    private var db: FirebaseFirestore =  Firebase.firestore
    private var user: FirebaseUser? =  Firebase.auth.currentUser

    override fun onCreate() {
        super.onCreate()
        Dash.appComponent.inject(this)
    }

    override fun onListenerConnected() {
        interactor.setRunService(true)
    }

    override fun onListenerDisconnected() {
        interactor.setRunService(false)
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)

        if (sbn!=null){
            val typeNotification = matchTypeNotification(sbn.packageName)
            if (typeNotification!=0){

                val bundle = sbn.notification.extras
                val text = bundle.getString(Notification.EXTRA_TEXT)
                val title = bundle.getString(Notification.EXTRA_TITLE)
                val icon = sbn.notification.largeIcon
                val nameImage = sbn.postTime

                if (text != null && title != null) {
                    val lowercaseText = text.lowercase()
                    if (lowercaseText.contains("incoming") && lowercaseText.contains("call")) {
                        Log.d("Call", "incoming call hit $title")
                        checkBlock(title)
                    }
                }

                if(sbn.tag != null){
                    interactor.getNotificationExists(nameImage.toString()){
                        interactor.setDataMessageNotification(typeNotification,text,title,nameImage.toString(),icon)
                    }
                }
            }
        }
    }

    private fun matchTypeNotification(packageName:String) : Int =
            when (packageName) {
                FACEBOOK_MESSENGER_PACK_NAME -> TYPE_MESSENGER
                WHATSAPP_PACK_NAME -> TYPE_WHATSAPP
                INSTAGRAM_PACK_NAME -> TYPE_INSTAGRAM
                else -> 0
            }

    private fun checkBlock(contact: String){
        val blockList = mutableListOf<String>()
        val type = TYPE_CALL_INCOMING_WHATSCALL
        if (user != null && !typeApp) {
            db.collection("users").document(user!!.uid).collection("block")
                .whereEqualTo("blockOnWhatsApp", true).get().addOnSuccessListener{ documents ->
                    var blockCall = false
                    var incomingNumbers = listOf(contact)
                    if(!documents.isEmpty) {
                        for (document in documents) {
                            document.getString("number")?.let { blockList.add(it) }
                        }
                        incomingNumbers = getMobileNumberFromContactName(contact)
                        Log.d("NotificationService", "Active Call = $incomingNumbers")

                        for (incomingNumber in incomingNumbers) {
                            blockCall = blockList.any { incomingNumber.endsWith(it) }
                            if (blockCall) break
                        }
                        if (blockCall) {
                            Log.d("NotificationService", "block hit at number:$incomingNumbers on list $blockList")
                            val intent = Intent(Intent.ACTION_CALL)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            intent.setData(Uri.parse("tel:${Random.nextLong(100000000,1000000000)}"))
                            startActivity(intent)
                        }
                    }

                    val blockCallType = if(blockCall) Consts.CALL_BLOCKED else Consts.CALL_NOT_BLOCKED
                    val calls = Calls(contact, incomingNumbers.toString(), ConstFun.getDateTime(), type, blockCallType)
                    uploadCall(calls)

                }.addOnFailureListener{
                    it.printStackTrace()
                    val calls = Calls(contact, contact, ConstFun.getDateTime(), type, Consts.CALL_NOT_BLOCKED)
                    uploadCall(calls)
                }
        }
    }
    private fun uploadCall(calls: Calls){
        user?.let { Firebase.database.getReference(Consts.USER).child(it.uid).child(childSelected).child("${Consts.CALLS}/${Consts.DATA}").push().setValue(calls) }
    }
    @SuppressLint("Range")
    private fun getMobileNumberFromContactName(contactName: String): List<String> {
        val phoneNumbers = mutableListOf<String>()
        val contentResolver: ContentResolver = contentResolver
        val cursor: Cursor? = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            "display_name = '$contactName'",
            null,
            null
        )
        cursor?.use {
            if (it.moveToFirst()) {
                val contactId =
                    it.getString(it.getColumnIndex(ContactsContract.Contacts._ID))
                val hasPhoneNumber =
                    it.getString(it.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)).toInt()
                if (hasPhoneNumber > 0) {
                    val phoneCursor: Cursor? = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        arrayOf(contactId),
                        null
                    )
                    phoneCursor?.use { phone ->
                        while (phone.moveToNext()) {
                            val phoneNumber = phone.getString(
                                phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                            )
                            phoneNumbers.add(phoneNumber)
                        }
                    }
                }
            }
        }
        phoneNumbers.add(contactName)
        return phoneNumbers
    }
}
