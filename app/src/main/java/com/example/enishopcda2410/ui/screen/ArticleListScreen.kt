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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.enishopcda2410.bo.Article
import com.example.enishopcda2410.repository.ArticleRepository
import com.example.enishopcda2410.ui.common.EniShopAppBar
import com.example.enishopcda2410.utils.toPriceFormat
import com.example.enishopcda2410.vm.ArticleListViewModel
import org.w3c.dom.Text

@Composable
fun ArticleListScreen(
    articleListViewModel: ArticleListViewModel = viewModel(factory = ArticleListViewModel.Factory),
    modifier: Modifier = Modifier
) {
    val articles by articleListViewModel.articles.collectAsState()
    val categories by articleListViewModel.categories.collectAsState()
    var selectedCategory by rememberSaveable() { mutableStateOf("") }

    val filteredList = articles.filter {
        it.category == selectedCategory || selectedCategory == ""
    }


    Scaffold(
        topBar = { EniShopAppBar() }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(8.dp)
        ) {
            CategoryFilterChip(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategoryClick = {
                    selectedCategory = it
                }
            )
            ArticleList(articles = filteredList)
        }
    }

}

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
            Text(
                article.name,
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
fun CategoryFilterChip(
    categories: List<String>,
    selectedCategory: String,
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            FilterChip(
                selected = selectedCategory == category,
                onClick = {
                    if (selectedCategory == category) {
                        onCategoryClick("")
                    } else {
                        onCategoryClick(category)
                    }
                },
                label = {
                    Text(category.replaceFirstChar { c ->
                        c.uppercase()
                    })
                },
                leadingIcon =  {
                    if (selectedCategory == category){
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Check"
                        )
                    }
                }
            )
        }

    }

}

@Preview
@Composable
fun ArticleListPreview(modifier: Modifier = Modifier) {
    ArticleListScreen()
}