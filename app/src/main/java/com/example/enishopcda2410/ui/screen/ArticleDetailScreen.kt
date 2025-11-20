package com.example.enishopcda2410.ui.screen

import android.app.SearchManager
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.enishopcda2410.bo.Article
import com.example.enishopcda2410.repository.ArticleRepository
import com.example.enishopcda2410.ui.common.EniShopAppBar
import com.example.enishopcda2410.utils.toFrenchFormat
import com.example.enishopcda2410.utils.toPriceFormat
import com.example.enishopcda2410.vm.ArticleDetailViewModel


@Composable
fun ArticleDetailScreen(
    articleId: Long,
    articleDetailViewModel: ArticleDetailViewModel = viewModel(factory = ArticleDetailViewModel.Factory),
    modifier: Modifier = Modifier
) {

    val article by articleDetailViewModel.article.collectAsState()


    LaunchedEffect(Unit) {
        articleDetailViewModel.loadArticle(articleId)
    }

//    Scaffold(
//        topBar = { EniShopAppBar() }
//    ) {
    article?.let { it1 ->
        ArticleDetail(
            article = it1,
            onCheckedChange = { isChecked ->
                if(isChecked){
                    articleDetailViewModel.insertArticle()
                }else{
                    articleDetailViewModel.deleteArticle()
                }
            }
        )
    }
//    }
}


@Composable
fun ArticleDetail(
    article: Article,
    onCheckedChange : (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = article.name,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .clickable {
//                Intent(
//                    Intent.ACTION_VIEW,
//                    "https://www.google.com/search?q=${article.name}+eni+shop".toUri()
//                ).also {
//                    context.startActivity(it)
//                }
                    Intent(Intent.ACTION_WEB_SEARCH).also {
                        it.putExtra(SearchManager.QUERY, "${article.name}+eni+shop")
                        context.startActivity(it)
                    }
                }
                .testTag("articleName")
        )
        Surface(
            color = MaterialTheme.colorScheme.inverseOnSurface,
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = article.urlImage,
                contentDescription = article.name,
                modifier = Modifier.height(250.dp)
            )
        }
        Text(
            text = article.description,
            textAlign = TextAlign.Justify,
            modifier = Modifier.testTag("articleDescription")
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Prix : ${article.price.toPriceFormat()} €")
            Text(text = "Date de sortie : ${article.date.toFrenchFormat()}")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = true,
                onCheckedChange = onCheckedChange,
                modifier = Modifier.testTag("articleFav")
            )
            Text(text = "Favoris ?")
        }


    }


}

@Preview
@Composable
private fun ArticlePreview() {
    //ArticleDetailScreen()
}