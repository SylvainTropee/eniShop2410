package com.example.enishopcda2410

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.enishopcda2410.bo.Article
import com.example.enishopcda2410.repository.ArticleRepository
import com.example.enishopcda2410.ui.theme.EniShopCDA2410Theme

private const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EniShopCDA2410Theme {

                val article  = ArticleRepository.getArticle(3)
                Log.i(TAG, article.toString())
                val id = ArticleRepository.addArticle(
                    Article(
                        name = "Test"
                    )
                )
                Log.i(TAG, ArticleRepository.getArticle(id).toString())
            }
        }
    }
}

