package com.example.dietkuyy.diary

import androidx.room.Entity

@Entity(tableName = "Diarymo")
class Diarymo (
    var waktu: String,
    var kegiatan: String,
    var cata:String

){
    constructor() : this("","","")

}
