package com.example.edusfe.model

data class Comment(
    var id: Int,
    var thread_id: Thread,
    var user_id: User,
    var comment: String,
    var created_at: String,
    var update_at: String,
    var delete_at: String
)
