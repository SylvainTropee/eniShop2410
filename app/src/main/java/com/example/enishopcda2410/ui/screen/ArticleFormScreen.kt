package com.example.enishopcda2410.ui.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.enishopcda2410.repository.ArticleRepository
import com.example.enishopcda2410.ui.common.EniShopAppBar
import com.example.enishopcda2410.ui.common.EniShopTextField
import kotlin.math.exp


@Composable
fun ArticleFormScreen(
    modifier: Modifier = Modifier) {
//    Scaffold(
//        topBar = { EniShopAppBar() }
//    ) {
        ArticleForm(  )
//    }
}



@Composable
fun ArticleForm(modifier: Modifier = Modifier) {

    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var selectedCategory by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        EniShopTextField(
            label = "Titre",
            value = title,
            onValueChange = {
                title = it
            }
        )
        EniShopTextField(
            label = "Description",
            value = description,
            onValueChange = {
                description = it
            }
        )
        EniShopTextField(
            label = "Prix",
            value = price,
            onValueChange = {
                price = it
            }
        )
        DropDownCategories(
            selectedCategory = selectedCategory,
            onCategoryChange = {
                selectedCategory = it
            }
        )
        Button(
            onClick = {
                Toast.makeText(context, "$title a été ajouté", Toast.LENGTH_LONG).show()
            }
        ) {
            Text("Enregistrer")
        }
    }
}


@Composable
fun DropDownCategories(
    selectedCategory : String,
    onCategoryChange : (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val categories = listOf("electronics", "jewelery", "men's clothing", "women's clothing")
    var expanded by rememberSaveable { mutableStateOf(false) }


    Column() {
        EniShopTextField(
            label = "Catégorie",
            value = selectedCategory,
            onValueChange = {},
            enabled = false,
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            },
            modifier = Modifier.clickable {
                expanded = !expanded
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            categories.forEach {
                DropdownMenuItem(
                    text = {
                        Text(text = it.replaceFirstChar {
                            it.uppercaseChar()
                        })
                    },
                    onClick = {
                        onCategoryChange(it)
                        expanded = false
                    }
                )
            }
        }
    }


}


@Preview
@Composable
private fun ArticleFormPreview() {
    //DropDownCategories()
}