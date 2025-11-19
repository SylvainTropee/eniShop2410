package com.example.enishopcda2410.nav

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destination {
    val route: String
}

object ArticleListDestination : Destination {

    override val route: String
        get() = "articleList"

}

object ArticleDetailDestination : Destination {

    override val route: String
        get() = "articleDetail"

    val articleIdArg = "articleId"

    val args = listOf(
        navArgument(articleIdArg){
            type = NavType.LongType
        }
    )
    val routeWithArgs = "$route/{$articleIdArg}"

}

object ArticleFormDestination : Destination {

    override val route: String
        get() = "articleForm"

}