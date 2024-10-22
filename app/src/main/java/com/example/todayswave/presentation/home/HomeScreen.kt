package com.example.todayswave.presentation.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.todayswave.data.model.News
import com.example.todayswave.presentation.State

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState = viewModel.state.collectAsState()
    val searchText = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        SearchBar(
            text = searchText.value,
            onSearch = {
                searchText.value = it
                viewModel.getNews(text = it)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        when (uiState.value) {
            is State.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                    Text(text = "Loading...")
                }
            }
            is State.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Failed!")
                    Text(text = (uiState.value as State.Error).error)
                    Button(onClick = { viewModel.getNews(searchText.value) }) {
                        Text(text = "Retry")
                    }
                }
            }
            else -> {
                val data = (uiState.value as State.Success).data
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item{
                        Text(text = "News")
                    }
                    items(data.news){article ->
                        NewsItem(article)
                    }
                }
            }
        }
    }
}

@Composable
fun NewsItem(news: News) {
    Box (
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
            .height(130.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Blue.copy(alpha = 0.5f))
    ){
        AsyncImage(
            model = news.image,
            contentDescription = null,
            modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
        Log.d("ImageUrl", news.image)
        Box(modifier = Modifier.fillMaxSize().padding(8.dp)){
            Text(
                text = news.title,
                modifier = Modifier.align(Alignment.TopStart),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = news.publish_date,
                color = Color.White,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
            Text(
                text = news.authors?.joinToString(",")?: "",
                color = Color.White,
                modifier = Modifier.align(BottomStart)
            )
        }
    }
}


@Composable
fun SearchBar(text: String, onSearch: (String) -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()){
        OutlinedTextField(
            value = text,
            onValueChange = onSearch,
            label = { Text(text = "Search") },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_search),
            contentDescription = "search icon",
            modifier = Modifier
                .padding(end = 16.dp)
                .align(Alignment.CenterEnd)
        )

    }
}