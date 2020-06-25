package com.example.dietkuyy.login

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "Users")

 data class Users (
    var uid: String,
    var name: String,
    var email: String,
    var password: String,
    var phone: String,
    var umur: String,
    var Jk: String,
    var alamat: String,
    val foto: String
){
    constructor() : this("", "", "", "", "", "", "", "", "") {

    }
    }