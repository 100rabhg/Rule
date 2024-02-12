package com.example.rule.dash.data.model

class KeyData {

    var keyID: String? = null
    var keyText: String? = null

    constructor()

    constructor(keyId: String, keyText: String) {
        this.keyID = keyId
        this.keyText = keyText
    }

}