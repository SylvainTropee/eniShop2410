package com.example.enishopcda2410.ui.screen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.enishopcda2410.bo.Article
import com.example.enishopcda2410.repository.ArticleRepository
import com.example.enishopcda2410.ui.common.EniShopAppBar
import com.example.enishopcda2410.utils.toFrenchFormat
import com.example.enishopcda2410.utils.toPriceFormat


@Composable
fun ArticleDetailScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { EniShopAppBar() }
    ) {
        ArticleDetail(
            article = ArticleRepository.getArticle(2)!!,
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
fun ArticleDetail(
    article: Article,
    modifier: Modifier = Modifier
) {

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
            textAlign = TextAlign.Center
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
            textAlign = TextAlign.Justify
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
                checked = false,
                onCheckedChange = {}
            )
            Text(text = "Favoris ?")
        }


    }


}

@Preview
@Composable
private fun ArticlePreview() {
    ArticleDetailScreen()
}