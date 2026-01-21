package com.learnkmp.myblog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learnkmp.myblog.model.Post
import com.learnkmp.myblog.model.SamplePosts
import com.learnkmp.myblog.network.PostApi
import com.learnkmp.myblog.network.createHttpClient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogApp() {
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
                posts = SamplePosts.allPosts
            }
        } catch (e: Exception) {
            println("Error fetching posts: ${e.message}")
            posts = SamplePosts.allPosts
        } finally {
            isLoading = false
        }
    }

    var selectedPost by remember { mutableStateOf<Post?>(null) }

    MaterialTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("My KMP Blog") },
                    navigationIcon = {
                        if (selectedPost != null) {
                            IconButton(onClick = { selectedPost = null }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    )
                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else if (selectedPost == null) {
                    PostList(posts = posts) { post ->
                        selectedPost = post
                    }
                } else {
                    PostDetail(post = selectedPost!!)
                }
            }
        }
    }
}

@Composable
fun PostList(posts: List<Post>, onPostClick: (Post) -> Unit) {
    if (posts.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No posts found")
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(posts) { post ->
                PostSummary(post = post, onClick = { onPostClick(post) })
            }
        }
    }
}

@Composable
fun PostSummary(post: Post, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            
            if (post.publishedAt.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = post.publishedAt.substringBefore("T"),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            if (post.plaintext.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = post.plaintext,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun PostDetail(post: Post) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = post.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        if (post.publishedAt.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Published on ${post.publishedAt.substringBefore("T")}",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))

        if (post.plaintext.isNotEmpty()) {
            Text(
                text = post.plaintext,
                style = MaterialTheme.typography.bodyLarge,
                lineHeight = 24.sp
            )
        }
    }
}

@Preview
@Composable
fun BlogAppPreview() {
    BlogApp()
}

@Preview
@Composable
fun PostSummaryPreview() {
    MaterialTheme {
        PostSummary(post = SamplePosts.post1, onClick = {})
    }
}

@Preview
@Composable
fun PostDetailPreview() {
    MaterialTheme {
        PostDetail(post = SamplePosts.post2)
    }
}
