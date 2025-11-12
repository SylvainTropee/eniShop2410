package com.example.enishopcda2410.dao

import com.example.enishopcda2410.dao.memory.ArticleDAOMemoryImpl

abstract class DaoFactory {

    companion object {
        fun createArticleDAO(type: DaoType): ArticleDAO {

            return when (type) {
                DaoType.MEMORY -> ArticleDAOMemoryImpl()
            }

        }

    }
}