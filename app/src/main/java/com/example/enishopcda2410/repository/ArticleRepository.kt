package com.example.enishopcda2410.repository

import com.example.enishopcda2410.bo.Article
import com.example.enishopcda2410.dao.ArticleDAO
import com.example.enishopcda2410.dao.DaoFactory
import com.example.enishopcda2410.dao.DaoType

object ArticleRepository {

    private val articleDao : ArticleDAO = DaoFactory.createArticleDAO(DaoType.MEMORY)

    fun getArticle(id : Long) : Article?{
        return articleDao.findById(id)
    }

    fun addArticle(article: Article) : Long{
        return articleDao.insert(article)
    }



}