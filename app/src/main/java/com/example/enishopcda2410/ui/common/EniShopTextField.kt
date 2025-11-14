package com.example.enishopcda2410.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.nio.file.WatchEvent

@Composable
fun EniShopTextField(
    label : String,
    value : String,
    onValueChange : (String) -> Unit,
    enabled : Boolean = true,
    readOnly : Boolean = false,
    trailingIcon : @Composable () -> Unit = {},
    modifier: Modifier = Modifier
) {

    Surface(
        border = BorderStroke(1.5.dp, Color.Blue),
        shape = RoundedCornerShape(25),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                fontSize = 24.sp
            )
            TextField(
                value = value,
                onValueChange = onValueChange,
                enabled = enabled,
                readOnly = readOnly,
                trailingIcon = trailingIcon,
                modifier = modifier.fillMaxWidth()
            )
        }
    }
    
}

@Preview
@Composable
private fun TextFieldPreview() {
   //EniShopTextField(label = "Titre")
}