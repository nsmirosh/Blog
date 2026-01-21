package com.learnkmp.myblog.network


import com.learnkmp.myblog.model.Post
import com.learnkmp.myblog.model.PostDTO
import com.learnkmp.myblog.model.toDTO
import com.learnkmp.myblog.model.toPost
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PostApi(private val httpClient: HttpClient) {

    suspend fun getAllPosts(): List<Post> {
        return httpClient.get("posts").body<List<PostDTO>>().map { it.toPost() }
    }

    suspend fun removePost(post: Post) {
        httpClient.delete("posts/${post.id}")
    }

    suspend fun updatePost(post: Post) {
        httpClient.post("posts") {
            contentType(ContentType.Application.Json)
            setBody(post.toDTO())
        }
    }
}