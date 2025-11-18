package com.example.enishopcda2410.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.enishopcda2410.bo.Article
import com.example.enishopcda2410.repository.ArticleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ArticleDetailViewModel(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    private val _article = MutableStateFlow<Article?>(null)
    val article = _article.asStateFlow()

    fun loadArticle(id : Long){
        _article.value = articleRepository.getArticle(id)
    }
    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
//                val application = checkNotNull(extras[APPLICATION_KEY])
//                // Create a SavedStateHandle for this ViewModel from extras
//                val savedStateHandle = extras.createSavedStateHandle()

                return ArticleDetailViewModel(
                    ArticleRepository()
                ) as T
            }
        }

    }

}