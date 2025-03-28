package com.example.edusfe.ui.activity

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.edusfe.R
import com.example.edusfe.network.DatabaseConection
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement

class MainActivity : AppCompatActivity() {
    lateinit var tvJudul: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        tvJudul = findViewById(R.id.tbJudul)
        fecthData(this).execute()

    }

    class fecthData(private val activity: MainActivity) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            var connection: Connection?
            var statement: Statement?
            var resultSet: ResultSet?

            try {
                connection = DatabaseConection().getConnection()
                if (connection != null) {
                    var query = "SELECT * FROM Thread WHERE id = 3"
                    statement = connection.createStatement()
                    resultSet = statement.executeQuery(query)

                    if (resultSet.next()) {
                        val judul = resultSet.getString("judul")

//                        activity.runOnUiThread {
//                            activity.tvJudul.text = judul
//                        }
                    }
                }
            } catch (e: Exception) {
                Log.d("Fecth", "Eror : ${e.message}")
            }

            return null
        }
    }
}