package com.learnkmp.myblog

import androidx.compose.runtime.*
import com.learnkmp.myblog.model.Post
import com.learnkmp.myblog.model.SamplePosts
import com.learnkmp.myblog.network.PostApi
import com.learnkmp.myblog.network.createHttpClient
import kotlinx.coroutines.launch

@Composable
fun App() {
    val httpClient = remember { createHttpClient() }
    val postApi = remember { PostApi(httpClient) }
    var posts by remember { mutableStateOf<List<Post>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            val fetchedPosts = postApi.getAllPosts()
            if (fetchedPosts.isNotEmpty()) {
                posts = fetchedPosts
            } else {
                // Fallback to sample posts if server returns empty
                posts = SamplePosts.allPosts
            }
        } catch (e: Exception) {
            println("Error fetching posts: ${e.message}")
            // Fallback to sample posts on error so we have something to show
            posts = SamplePosts.allPosts
        } finally {
            isLoading = false
        }
    }

    BlogApp(posts = posts)
}
