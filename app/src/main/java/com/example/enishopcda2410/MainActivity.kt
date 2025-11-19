package com.example.enishopcda2410

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.enishopcda2410.nav.EniShopNavHost
import com.example.enishopcda2410.repository.ArticleRepository
import com.example.enishopcda2410.ui.screen.ArticleDetailScreen
import com.example.enishopcda2410.ui.screen.ArticleForm
import com.example.enishopcda2410.ui.screen.ArticleFormScreen
import com.example.enishopcda2410.ui.screen.ArticleItem
import com.example.enishopcda2410.ui.screen.ArticleList
import com.example.enishopcda2410.ui.screen.ArticleListScreen
import com.example.enishopcda2410.ui.theme.EniShopCDA2410Theme

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EniShopCDA2410Theme {
                EniShopApp()
            }
        }
    }
}

@Composable
fun EniShopApp(modifier: Modifier = Modifier) {

    val navHostController = rememberNavController()
    EniShopNavHost(navHostController = navHostController)
}

