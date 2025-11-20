package com.example.enishopcda2410.bo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0,
    var name : String = "",
    var description : String = "",
    var price : Double = 0.0,
    var urlImage : String = "",
    var category : String = "",
    var date : Date = Date()
)
