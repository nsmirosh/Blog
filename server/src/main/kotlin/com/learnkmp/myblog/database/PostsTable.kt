package com.learnkmp.myblog.database

import org.jetbrains.exposed.sql.Table

object PostsTable : Table("posts") {
    val id = varchar("id", 255)
    val uuid = varchar("uuid", 255)
    val title = varchar("title", 255)
    val slug = varchar("slug", 255)
    val mobiledoc = text("mobiledoc").nullable()
    val lexical = text("lexical").nullable()
    val html = text("html").nullable()
    val commentId = varchar("comment_id", 255).nullable()
    val plaintext = text("plaintext").nullable()
    val featureImage = varchar("feature_image", 255).nullable()
    val featured = integer("featured")
    val type = varchar("type", 50)
    val status = varchar("status", 50)
    val locale = varchar("locale", 50).nullable()
    val visibility = varchar("visibility", 50)
    val emailRecipientFilter = varchar("email_recipient_filter", 255).nullable()
    val createdAt = varchar("created_at", 50)
    val updatedAt = varchar("updated_at", 50)
    val publishedAt = varchar("published_at", 50).nullable()
    val customExcerpt = text("custom_excerpt").nullable()
    val codeinjectionHead = text("codeinjection_head").nullable()
    val codeinjectionFoot = text("codeinjection_foot").nullable()
    val customTemplate = varchar("custom_template", 255).nullable()
    val canonicalUrl = varchar("canonical_url", 255).nullable()
    val newsletterId = varchar("newsletter_id", 255).nullable()
    val showTitleAndFeatureImage = integer("show_title_and_feature_image")

    override val primaryKey = PrimaryKey(id)
}
