package com.example.enishopcda2410

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.isToggleable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.enishopcda2410.bo.Article
import com.example.enishopcda2410.ui.screen.ArticleDetail
import org.junit.Rule
import org.junit.Test

class ArticleDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val mockArticle = Article(
        id = 1,
        name = "Article Test",
        description = "Article Description",
        price = 22.10
    )

    @Test
    fun testArticleDetail() {

//        composeTestRule.setContent {
//            ArticleDetail(article = mockArticle)
//        }
//
//        composeTestRule.onNodeWithTag("articleName")
//            .assertExists()
//            .assertIsDisplayed()
//            .assertTextContains(mockArticle.name)
//
//        composeTestRule.onNodeWithTag("articleDescription")
//            .assertExists()
//            .assertIsDisplayed()
//            .assertTextContains(mockArticle.description)
//
//
//        composeTestRule
//            .onNodeWithTag("articleFav")
//            .assertIsOff()
//
//        composeTestRule
//            .onNode(isToggleable())
//            .assertIsOff()

    }


}