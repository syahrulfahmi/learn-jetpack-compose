package com.sf.jetcoffee.ui.components

import android.graphics.fonts.FontStyle
import android.view.MenuItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sf.jetcoffee.R
import com.sf.jetcoffee.model.Menu
import com.sf.jetcoffee.ui.theme.JetCoffeeTheme

@Composable
fun MenuItem(menu: Menu, modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = modifier.width(140.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Column {
            Image(
                modifier = Modifier
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                painter = painterResource(id = R.drawable.menu1),
                contentDescription = menu.title,
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp),
                text = menu.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = menu.price,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(
                modifier = Modifier
                    .size(8.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuItemPreview() {
    JetCoffeeTheme {
        MenuItem(
            menu = Menu(
                image = R.drawable.menu1, title = "Tiramisu Coffee Milk", price = "Rp18.000"
            ), modifier = Modifier.padding(
                8.dp
            )
        )
    }
}