package com.example.enishopcda2410.utils

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {

    @TypeConverter
    fun dateToTimestamp(date : Date) : Long{
        return date.time
    }

    @TypeConverter
    fun timestampToDate(time : Long) : Date{
        return Date(time)
    }
}