package com.sf.jetcoffee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sf.jetcoffee.model.BottomBarItem
import com.sf.jetcoffee.model.Menu
import com.sf.jetcoffee.model.dummyBestSellerMenu
import com.sf.jetcoffee.model.dummyCategory
import com.sf.jetcoffee.model.dummyMenu
import com.sf.jetcoffee.ui.components.CategoryItem
import com.sf.jetcoffee.ui.components.HomeSection
import com.sf.jetcoffee.ui.components.MenuItem
import com.sf.jetcoffee.ui.components.Search
import com.sf.jetcoffee.ui.components.SectionText
import com.sf.jetcoffee.ui.theme.JetCoffeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetCoffeeTheme {
                JetCoffeeApp()
            }
        }
    }
}

@Composable
fun JetCoffeeApp() {
    Scaffold(bottomBar = { BottomBar() }) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(it)
        ) {
            Banner()
            SectionText(stringResource(id = R.string.section_category))
            CategoryRow()
            HomeSection(
                title = stringResource(id = R.string.menu_favorite),
                content = { MenuRow(dummyMenu) })
            HomeSection(
                title = stringResource(id = R.string.section_best_seller_menu),
                content = { MenuRow(dummyBestSellerMenu) })
        }
    }
}

@Composable
fun Banner(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.banner),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(160.dp)
        )
        Search()
    }
}

@Composable
fun CategoryRow(modifier: Modifier = Modifier) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(dummyCategory, key = { it.textCategory }) { category ->
            CategoryItem(category = category)
        }
    }
}

@Composable
fun MenuRow(listMenu: List<Menu>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(listMenu, key = { it.title }) { menu ->
            MenuItem(menu = menu)
        }
    }
}

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier,
    ) {
        val navigationItems = listOf(
            BottomBarItem(
                title = stringResource(id = R.string.menu_home),
                icon = Icons.Default.Home
            ),
            BottomBarItem(
                title = stringResource(id = R.string.menu_favorite),
                icon = Icons.Default.Favorite
            ),
            BottomBarItem(
                title = stringResource(id = R.string.menu_profile),
                icon = Icons.Default.AccountCircle
            )
        )
        navigationItems.map {
            NavigationBarItem(
                label = {
                    Text(text = it.title)
                },
                selected = it.title == navigationItems[0].title,
                onClick = { },
                icon = {
                    Icon(imageVector = it.icon, contentDescription = it.title)
                },
            )
        }
    }
}

@Preview
@Composable
private fun BannerPreview() {
    JetCoffeeTheme {
        Banner()
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryRowPreview() {
    JetCoffeeTheme {
        CategoryRow()
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuRowPreview() {
    JetCoffeeTheme {
        MenuRow(dummyMenu)
    }
}

@Preview(showBackground = true)
@Composable
private fun BestSellerMenuPreview() {
    JetCoffeeTheme {
        MenuRow(dummyBestSellerMenu)
    }
}

@Preview(showBackground = true)
@Composable
private fun JetCoffeePreview() {
    JetCoffeeTheme {
        JetCoffeeApp()
    }
}