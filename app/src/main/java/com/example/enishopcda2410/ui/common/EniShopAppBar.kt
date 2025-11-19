package com.example.enishopcda2410.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.enishopcda2410.datastore.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EniShopAppBar(
    canBack: Boolean,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {

    TopAppBar(
        actions = { BurgerMenuSettings() },
        title = { EniShopTitle() },
        navigationIcon = {
            if (canBack)
                IconButton(
                    onClick = onNavigateBack
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Retour"
                    )
                }
        }
    )
}


@Composable
fun BurgerMenuSettings(modifier: Modifier = Modifier) {

    var expanded by rememberSaveable { mutableStateOf(false) }
    var isDarkMode by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val coroutine = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        DataStoreManager.getDarkMode(context).collect {
            isDarkMode = it
        }
    }

    Column() {
        IconButton(
            onClick = {
                expanded = !expanded
            }
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu"
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Dark mode") },
                onClick = {},
                trailingIcon = {
                    Switch(
                        checked = isDarkMode,
                        onCheckedChange = {
                            coroutine.launch(Dispatchers.IO) {
                                DataStoreManager.setDarkMode(context, it)
                            }

                        }
                    )
                }

            )

        }


    }


}


@Composable
fun EniShopTitle(modifier: Modifier = Modifier) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Eni-Shop",
            modifier = Modifier.size(40.dp)
        )
        Spacer(
            modifier = Modifier.width(8.dp)
        )
        Text(
            text = "ENI-SHOP",
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview
@Composable
private fun AppBarPreview() {
    //EniShopAppBar()
}

@Preview
@Composable
private fun EniShopTitlePreview() {
    EniShopTitle()
}