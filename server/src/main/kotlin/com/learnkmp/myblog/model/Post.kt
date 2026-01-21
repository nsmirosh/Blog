package com.learnkmp.myblog.model

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: String,
    val uuid: String,
    val title: String,
    val slug: String,
    val mobiledoc: String?,
    val lexical: String?,
    val html: String?,
    val comment_id: String?,
    val plaintext: String?,
    val feature_image: String?,
    val featured: Int,
    val type: String,
    val status: String,
    val locale: String?,
    val visibility: String,
    val email_recipient_filter: String?,
    val created_at: String,
    val updated_at: String,
    val published_at: String?,
    val custom_excerpt: String?,
    val codeinjection_head: String?,
    val codeinjection_foot: String?,
    val custom_template: String?,
    val canonical_url: String?,
    val newsletter_id: String?,
    val show_title_and_feature_image: Int
)
