package com.example.enishopcda2410.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.enishopcda2410.bo.Article
import com.example.enishopcda2410.dao.DaoFactory
import com.example.enishopcda2410.dao.DaoType
import com.example.enishopcda2410.db.EniShopDatabase
import com.example.enishopcda2410.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArticleDetailViewModel(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    private val _article = MutableStateFlow<Article?>(null)
    val article = _article.asStateFlow()

    fun loadArticle(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _article.value = articleRepository.getArticle(id)
        }
    }

    fun insertArticle() {
        viewModelScope.launch(Dispatchers.IO) {
            _article.value?.let {
                articleRepository.addArticle(article = it, type = DaoType.ROOM)
            }
        }
    }

    fun deleteArticle() {
        viewModelScope.launch(Dispatchers.IO) {
            _article.value?.let {
                articleRepository.deleteArticle(it, type = DaoType.ROOM)
            }
        }
    }


    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])


                return ArticleDetailViewModel(
                    ArticleRepository(
                        articleDaoMemory = DaoFactory.createArticleDAO(DaoType.MEMORY),
                        articleDaoRoom = EniShopDatabase.getInstance(application.applicationContext)
                            .getArticleDao()
                    )
                ) as T
            }
        }

    }

}