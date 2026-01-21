package com.learnkmp.myblog.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostDTO(
    val id: String = "",
    val uuid: String = "",
    val title: String = "",
    val slug: String = "",
    val mobiledoc: String = "",
    val lexical: String = "",
    val html: String = "",
    @SerialName("comment_id")
    val commentId: String = "",
    val plaintext: String = "",
    @SerialName("feature_image")
    val featureImage: String = "",
    val featured: Int = 0,
    val type: String = "",
    val status: String = "",
    val locale: String = "",
    val visibility: String = "",
    @SerialName("email_recipient_filter")
    val emailRecipientFilter: String = "",
    @SerialName("created_at")
    val createdAt: String = "",
    @SerialName("updated_at")
    val updatedAt: String = "",
    @SerialName("published_at")
    val publishedAt: String = "",
    @SerialName("custom_excerpt")
    val customExcerpt: String = "",
    @SerialName("codeinjection_head")
    val codeinjectionHead: String = "",
    @SerialName("codeinjection_foot")
    val codeinjectionFoot: String = "",
    @SerialName("custom_template")
    val customTemplate: String = "",
    @SerialName("canonical_url")
    val canonicalUrl: String = "",
    @SerialName("newsletter_id")
    val newsletterId: String = "",
    @SerialName("show_title_and_feature_image")
    val showTitleAndFeatureImage: Int = 0
)

fun PostDTO.toPost() = Post(
    id = id,
    uuid = uuid,
    title = title,
    slug = slug,
    mobiledoc = mobiledoc,
    lexical = lexical,
    html = html,
    commentId = commentId,
    plaintext = plaintext,
    featureImage = featureImage,
    featured = featured,
    type = type,
    status = status,
    locale = locale,
    visibility = visibility,
    emailRecipientFilter = emailRecipientFilter,
    createdAt = createdAt,
    updatedAt = updatedAt,
    publishedAt = publishedAt,
    customExcerpt = customExcerpt,
    codeinjectionHead = codeinjectionHead,
    codeinjectionFoot = codeinjectionFoot,
    customTemplate = customTemplate,
    canonicalUrl = canonicalUrl,
    newsletterId = newsletterId,
    showTitleAndFeatureImage = showTitleAndFeatureImage
)
