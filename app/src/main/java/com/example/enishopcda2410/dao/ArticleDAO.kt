package com.example.enishopcda2410.dao

import com.example.enishopcda2410.bo.Article

interface ArticleDAO {

    fun findById(id : Long) : Article?

    fun insert(article: Article) : Long
}