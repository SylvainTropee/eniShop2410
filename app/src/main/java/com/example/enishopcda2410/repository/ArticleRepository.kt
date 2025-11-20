package com.example.enishopcda2410.repository

import com.example.enishopcda2410.bo.Article
import com.example.enishopcda2410.dao.ArticleDAO
import com.example.enishopcda2410.dao.DaoFactory
import com.example.enishopcda2410.dao.DaoType

class ArticleRepository(
    private val articleDaoRoom: ArticleDAO,
    private val articleDaoMemory: ArticleDAO
) {
    //private val articleDao : ArticleDAO = DaoFactory.createArticleDAO(DaoType.MEMORY)

    suspend fun getArticle(id: Long, type: DaoType = DaoType.MEMORY): Article? {
        return if (type == DaoType.MEMORY) {
            articleDaoMemory.findById(id)
        } else {
            articleDaoRoom.findById(id)
        }
    }

    suspend fun addArticle(article: Article, type: DaoType = DaoType.MEMORY): Long {
        return if (type == DaoType.MEMORY) {
            articleDaoMemory.insert(article)
        } else {
            articleDaoRoom.insert(article)
        }
    }

    suspend fun getAllArticle(): List<Article> {
        return articleDaoMemory.findAll()
    }

    suspend fun deleteArticle(article: Article, type: DaoType = DaoType.MEMORY) {
        return if (type == DaoType.MEMORY) {
            articleDaoMemory.delete(article)
        } else {
            articleDaoRoom.delete(article)
        }
    }


}