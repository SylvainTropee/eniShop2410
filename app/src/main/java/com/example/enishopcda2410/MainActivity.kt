package com.example.enishopcda2410

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.enishopcda2410.datastore.DataStoreManager
import com.example.enishopcda2410.nav.ArticleFormDestination
import com.example.enishopcda2410.nav.ArticleListDestination
import com.example.enishopcda2410.nav.EniShopNavHost
import com.example.enishopcda2410.repository.ArticleRepository
import com.example.enishopcda2410.ui.common.EniShopAppBar
import com.example.enishopcda2410.ui.screen.ArticleDetailScreen
import com.example.enishopcda2410.ui.screen.ArticleForm
import com.example.enishopcda2410.ui.screen.ArticleFormScreen
import com.example.enishopcda2410.ui.screen.ArticleItem
import com.example.enishopcda2410.ui.screen.ArticleList
import com.example.enishopcda2410.ui.screen.ArticleListFAB
import com.example.enishopcda2410.ui.screen.ArticleListScreen
import com.example.enishopcda2410.ui.theme.EniShopCDA2410Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var isDarkMode by rememberSaveable { mutableStateOf(false) }
            val coroutine = rememberCoroutineScope()

//            coroutine.launch(Dispatchers.IO) {
//                DataStoreManager.getDarkMode(this@MainActivity).collect {
//                    isDarkMode = it
//                }
//            }

            LaunchedEffect(Unit) {
                DataStoreManager.getDarkMode(this@MainActivity).collect {
                    isDarkMode = it
                }
            }

            EniShopCDA2410Theme(
                darkTheme = isDarkMode
            ) {
                EniShopApp()
            }
        }
    }
}

@Composable
fun EniShopApp(modifier: Modifier = Modifier) {

    val navHostController = rememberNavController()
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = { EniShopAppBar(
            canBack = currentRoute != ArticleListDestination.route,
            onNavigateBack = {
                navHostController.popBackStack()
            }
        ) },
        floatingActionButton = {
            if(currentRoute == ArticleListDestination.route){
                ArticleListFAB(
                    onNavigateToArticleForm = {
                        navHostController.navigate(ArticleFormDestination.route)
                    }
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            EniShopNavHost(navHostController = navHostController)
        }

    }

}

