package com.learnkmp.myblog.database

import com.learnkmp.myblog.model.Post
import com.learnkmp.myblog.model.SamplePosts
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        val driverClassName = "org.h2.Driver"
        val jdbcURL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"
        val database = Database.connect(jdbcURL, driverClassName)
        transaction(database) {
            SchemaUtils.create(PostsTable)
            // Seed data from SamplePosts if empty
            if (PostsTable.selectAll().empty()) {
                SamplePosts.allPosts.forEach { post ->
                    PostsTable.insert {
                        it[id] = post.id
                        it[uuid] = post.uuid
                        it[title] = post.title
                        it[slug] = post.slug
                        it[mobiledoc] = post.mobiledoc
                        it[lexical] = post.lexical
                        it[html] = post.html
                        it[commentId] = post.comment_id
                        it[plaintext] = post.plaintext
                        it[featureImage] = post.feature_image
                        it[featured] = post.featured
                        it[type] = post.type
                        it[status] = post.status
                        it[locale] = post.locale
                        it[visibility] = post.visibility
                        it[emailRecipientFilter] = post.email_recipient_filter
                        it[createdAt] = post.created_at
                        it[updatedAt] = post.updated_at
                        it[publishedAt] = post.published_at
                        it[customExcerpt] = post.custom_excerpt
                        it[codeinjectionHead] = post.codeinjection_head
                        it[codeinjectionFoot] = post.codeinjection_foot
                        it[customTemplate] = post.custom_template
                        it[canonicalUrl] = post.canonical_url
                        it[newsletterId] = post.newsletter_id
                        it[showTitleAndFeatureImage] = post.show_title_and_feature_image
                        it[notionEmbedUrl] = post.notion_embed_url
                    }
                }
            }
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    fun ResultRow.toPost() = Post(
        id = this[PostsTable.id],
        uuid = this[PostsTable.uuid],
        title = this[PostsTable.title],
        slug = this[PostsTable.slug],
        mobiledoc = this[PostsTable.mobiledoc],
        lexical = this[PostsTable.lexical],
        html = this[PostsTable.html],
        comment_id = this[PostsTable.commentId],
        plaintext = this[PostsTable.plaintext],
        feature_image = this[PostsTable.featureImage],
        featured = this[PostsTable.featured],
        type = this[PostsTable.type],
        status = this[PostsTable.status],
        locale = this[PostsTable.locale],
        visibility = this[PostsTable.visibility],
        email_recipient_filter = this[PostsTable.emailRecipientFilter],
        created_at = this[PostsTable.createdAt],
        updated_at = this[PostsTable.updatedAt],
        published_at = this[PostsTable.publishedAt],
        custom_excerpt = this[PostsTable.customExcerpt],
        codeinjection_head = this[PostsTable.codeinjectionHead],
        codeinjection_foot = this[PostsTable.codeinjectionFoot],
        custom_template = this[PostsTable.customTemplate],
        canonical_url = this[PostsTable.canonicalUrl],
        newsletter_id = this[PostsTable.newsletterId],
        show_title_and_feature_image = this[PostsTable.showTitleAndFeatureImage],
        notion_embed_url = this[PostsTable.notionEmbedUrl]
    )
}
