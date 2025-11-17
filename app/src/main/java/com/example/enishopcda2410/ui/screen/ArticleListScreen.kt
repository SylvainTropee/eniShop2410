package com.example.enishopcda2410.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.enishopcda2410.bo.Article
import com.example.enishopcda2410.repository.ArticleRepository
import com.example.enishopcda2410.utils.toPriceFormat
import org.w3c.dom.Text



@Composable
fun ArticleList(
    articles: List<Article>,
    modifier: Modifier = Modifier
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(articles) { article ->
            ArticleItem(article = article)
        }
    }


}

@Composable
fun ArticleItem(
    article: Article,
    modifier: Modifier = Modifier
) {

    Card(
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(8.dp)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.tertiary, CircleShape)
                    .padding(8.dp),
                model = article.urlImage,
                contentDescription = article.name
            )
            Text(article.name,
                minLines = 2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify
            )
            Text(article.price.toPriceFormat() + " €")
        }
    }


}


@Composable
fun CategoryFilterChip(modifier: Modifier = Modifier) {

    val categories = listOf("electronics", "jewelery", "men's clothing", "women's clothing")

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            FilterChip(
                selected = false,
                onClick = {},
                label = {
                    Text(category.replaceFirstChar { c ->
                        c.uppercase()
                    })
                }
            )
        }

    }

}

@Preview
@Composable
fun ArticleListPreview(modifier: Modifier = Modifier) {
    ArticleList(articles = ArticleRepository().getAllArticle())
}