package com.example.rule.dash.data.model

class Calls {

    var contact: String? = null
    var phoneNumber:String?=null
    var dateTime:String?=null
    var type:Int?=null
    var blocked:Int?=null

    constructor()

    constructor(contact:String?,phoneNumber: String?,dateTime:String?,type:Int, blocked:Int) {
        this.contact = contact
        this.phoneNumber = phoneNumber
        this.dateTime = dateTime
        this.type = type
        this.blocked = blocked
    }

}