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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArticleListViewModel(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles.asStateFlow()

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories = _categories.asStateFlow()


    init {

        viewModelScope.launch(Dispatchers.IO) {
            _articles.value = articleRepository.getAllArticle()
            _categories.value =
                listOf("electronics", "jewelery", "men's clothing", "women's clothing")
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


                return ArticleListViewModel(
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