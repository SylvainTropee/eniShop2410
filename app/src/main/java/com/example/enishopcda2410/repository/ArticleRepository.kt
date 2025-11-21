package com.example.enishopcda2410.repository

import com.example.enishopcda2410.bo.Article
import com.example.enishopcda2410.dao.ArticleDAO
import com.example.enishopcda2410.dao.DaoFactory
import com.example.enishopcda2410.dao.DaoType
import com.example.enishopcda2410.network.ArticleService

class ArticleRepository(
    private val articleDaoRoom: ArticleDAO,
    private val articleService: ArticleService
) {
    //private val articleDao : ArticleDAO = DaoFactory.createArticleDAO(DaoType.NETWORK)



    suspend fun getArticle(id: Long, type: DaoType = DaoType.NETWORK): Article? {
        return if (type == DaoType.NETWORK) {
            articleService.getArticleById(id)
        } else {
            articleDaoRoom.findById(id)
        }
    }

    suspend fun getAllArticle(): List<Article> {
        return articleService.getAllArticles()
    }


    suspend fun addArticle(article: Article): Long {
        return articleDaoRoom.insert(article)
    }

    suspend fun deleteArticle(article: Article) {
        return articleDaoRoom.delete(article)

    }

    //categories
    suspend fun getCategories(): List<String> {
        return articleService.getCategories()
    }


}