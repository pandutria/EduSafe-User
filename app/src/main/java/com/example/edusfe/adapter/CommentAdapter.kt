package com.example.edusfe.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.edusfe.R
import com.example.edusfe.model.Comment

class CommentAdapter(private var commentList: List<Comment>): RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvNama = itemView.findViewById<TextView>(R.id.tvNama)
        var tvComment = itemView.findViewById<TextView>(R.id.tvComment)
        var tvTanggal = itemView.findViewById<TextView>(R.id.tvTanggal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_komentar, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var comment = commentList[position]
        holder.tvNama.text = "${comment.user.nama} (Kelas ${comment.user.kelas})"
        holder.tvComment.text = "Comment : ${comment.comment}"
        holder.tvTanggal.text = "Tanggal :  " + comment.created_at

//        if (comment.update_at != "") {
//            holder.tvTanggal.text = "Tanggal :  " + comment.update_at
//        } else {
//            holder.tvTanggal.text = "Tanggal :  " + comment.created_at
//        }
    }
}