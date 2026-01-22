package com.learnkmp.myblog

import com.learnkmp.myblog.SERVER_PORT
import com.learnkmp.myblog.database.DatabaseFactory
import com.learnkmp.myblog.database.DatabaseFactory.toPost
import com.learnkmp.myblog.database.PostsTable
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.selectAll

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()
    install(ContentNegotiation) {
        json()
    }
    install(CORS) {
        allowHeader(HttpHeaders.ContentType)
        allowMethod(HttpMethod.Delete)
        // For ease of demonstration we allow any connections.
        // Don't do this in production.
        anyHost()
    }
    routing {
        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }
        get("/posts") {
            val posts = DatabaseFactory.dbQuery {
                PostsTable.selectAll().map { it.toPost() }
            }
            call.respond(posts)
        }
    }
}