package com.example.enishopcda2410.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.OutlinedButton
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
import com.example.enishopcda2410.ui.common.EniShopTextField

@Composable
fun ArticleForm(modifier: Modifier = Modifier) {

    var title by rememberSaveable {
        mutableStateOf("")
    }
    var description by rememberSaveable {
        mutableStateOf("")
    }
    var price by rememberSaveable {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.verticalScroll(rememberScrollState())
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
        Button(
            onClick = {
                Toast.makeText(context, "$title a été ajouté", Toast.LENGTH_LONG).show()
            }
        ) {
            Text("Enregistrer")
        }
    }
}

@Preview
@Composable
private fun ArticleFormPreview() {
    ArticleForm()
}