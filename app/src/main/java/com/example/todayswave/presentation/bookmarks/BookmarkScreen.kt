package com.example.todayswave.presentation.bookmarks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.todayswave.presentation.home.NewsListView
import com.example.todayswave.utils.NavRoute

@Composable
fun BookmarksScreen(navHostController: NavHostController,modifier: Modifier = Modifier) {
    val viewModel: BookmarksViewModel = hiltViewModel()
    val bookmarks = viewModel.getBookmarks().collectAsState(initial = listOf())
    Column {
        Text(text = "Bookmarks", fontSize = 30.sp)
        Spacer(modifier = modifier.size(16.dp))
        NewsListView(bookmarks.value , onClick = {
            navHostController.navigate(NavRoute.createTodaysWaveDetails(it, true))
        })
    }
}