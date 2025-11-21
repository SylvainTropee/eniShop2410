package com.example.enishopcda2410.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
    onNavigateToArticleDetail: (Long) -> Unit,
    articleListViewModel: ArticleListViewModel = viewModel(factory = ArticleListViewModel.Factory),
    modifier: Modifier = Modifier
) {
    val articles by articleListViewModel.articles.collectAsState()
    val categories by articleListViewModel.categories.collectAsState()
    var selectedCategory by rememberSaveable() { mutableStateOf("") }


    LaunchedEffect(Unit) {
        articleListViewModel.loadArticleListScreen()
    }

    val filteredList = articles.filter {
        it.category == selectedCategory || selectedCategory == ""
    }


    if (articles.isEmpty() || categories.isEmpty()) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

    } else {

//    Scaffold(
//        topBar = { EniShopAppBar() },
//        floatingActionButton = { ArticleListFAB(onNavigateToArticleForm = onNavigateToArticleForm) }
//    ) {
        Column(
            modifier = Modifier
                //.padding(it)
                .padding(8.dp)
        ) {
            CategoryFilterChip(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategoryClick = {
                    selectedCategory = it
                }
            )
            ArticleList(
                articles = filteredList,
                onNavigateToArticleDetail = onNavigateToArticleDetail
            )
        }
    }
//    }

}

@Composable
fun ArticleList(
    articles: List<Article>,
    onNavigateToArticleDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(articles) { article ->
            ArticleItem(
                article = article,
                onNavigateToArticleDetail = onNavigateToArticleDetail
            )
        }
    }


}

@Composable
fun ArticleItem(
    article: Article,
    onNavigateToArticleDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.clickable {
            onNavigateToArticleDetail(article.id)
        }

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
                leadingIcon = {
                    if (selectedCategory == category) {
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

@Composable
fun ArticleListFAB(
    onNavigateToArticleForm: () -> Unit,
    modifier: Modifier = Modifier
) {

    FloatingActionButton(
        onClick = onNavigateToArticleForm,
        shape = CircleShape
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            "Ajouter un article",
            modifier = Modifier.size(50.dp)
        )
    }

}


@Preview
@Composable
fun ArticleListPreview(modifier: Modifier = Modifier) {
    // ArticleListScreen()
}