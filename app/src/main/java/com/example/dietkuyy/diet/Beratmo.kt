package com.example.dietkuyy.diet

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Beratmo")
class Beratmo (
    var tanggal: String,
    var berat: String

){
    constructor() : this("","")

}
