package com.example.enishopcda2410.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.enishopcda2410.ui.screen.ArticleDetailScreen
import com.example.enishopcda2410.ui.screen.ArticleFormScreen
import com.example.enishopcda2410.ui.screen.ArticleListScreen

@Composable
fun EniShopNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navHostController,
        startDestination = ArticleListDestination.route
    ) {
        composable(route = ArticleListDestination.route) {
            ArticleListScreen(
                onNavigateToArticleDetail = { articleId ->
                    navHostController.navigate("${ArticleDetailDestination.route}/$articleId")
                }
            )
        }
        composable(
            route = ArticleDetailDestination.routeWithArgs,
            arguments = ArticleDetailDestination.args
        ) {
            val articleId = it.arguments?.getLong(ArticleDetailDestination.articleIdArg) ?: 1
            ArticleDetailScreen(articleId = articleId)
        }
        composable(route = ArticleFormDestination.route) {
            ArticleFormScreen()
        }

    }

}