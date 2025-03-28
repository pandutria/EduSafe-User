package com.example.edusfe.ui.activity

import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edusfe.R
import com.example.edusfe.adapter.CommentAdapter
import com.example.edusfe.model.Comment
import com.example.edusfe.model.Thread
import com.example.edusfe.model.User
import com.example.edusfe.network.DatabaseConection
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement

class KomentarActivity : AppCompatActivity() {
    lateinit var rv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_komentar)

        rv = findViewById(R.id.rv)
        var thread_id = intent.getIntExtra("thread_id", 0)
        showData(this, rv, thread_id).execute()
    }

    class showData(
        private var context: Context,
        private val rv: RecyclerView,
        private val thread_id: Int) : AsyncTask<Void, Void, Void>() {
        var isDone = false
        var commentList: MutableList<Comment> = arrayListOf()

        override fun doInBackground(vararg p0: Void?): Void? {
            try {
                var connection: Connection = DatabaseConection().getConnection()
                if (connection != null) {
                    var query = "SELECT * FROM [Comment] WHERE thread_Id = $thread_id"
                    var statement: Statement = connection.createStatement()
                    var resultSet: ResultSet = statement.executeQuery(query)

                    if (resultSet.next()) {
                        isDone = true
                        var comment = resultSet.getString("comment")
                        var user_id = resultSet.getInt("user_id")
                        var queryUser = "SELECT * FROM [User] WHERE id = $user_id"
                        var statementUser: Statement = connection.createStatement()
                        var resultSetUser: ResultSet = statementUser.executeQuery(queryUser)

                        if (resultSetUser.next()) {
                            var nama = resultSetUser.getString("nama")
                            var kelas = resultSetUser.getString("kelas")
                            var tanggal = resultSetUser.getString("created_at")

                            commentList.add(Comment(
                                0,
                                null,
                                User(0, "", nama, "", "", kelas),
                                comment,
                                tanggal,
                                "",
                                ""

                            ))
                        }
                    }
                }
            } catch (e: Exception) {
                Log.d("SqlServer", "Eror ${e.message}")
            }
            return null

        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            rv.adapter = CommentAdapter(commentList)
            rv.layoutManager = LinearLayoutManager(context)
        }

    }
}