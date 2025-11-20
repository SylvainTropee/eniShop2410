package com.example.enishopcda2410.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.enishopcda2410.bo.Article
import com.example.enishopcda2410.dao.ArticleDAO
import com.example.enishopcda2410.utils.DateConverter

@Database(entities = [Article::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class EniShopDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDAO

    companion object {

        private var INSTANCE: EniShopDatabase? = null

        fun getInstance(context: Context): EniShopDatabase {

            var instance = INSTANCE

            if (instance == null) {

                instance = Room.databaseBuilder(
                    context,
                    EniShopDatabase::class.java,
                    "eni_shop.db"
                ).build()

                INSTANCE = instance
            }
            return instance
        }
    }


}