package com.example.mvulatrustmobileapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatRVAdapter(private val chatsModalArrayList: ArrayList<ChatsModal>, private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return when (viewType) {
            0 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.user_msg_rv_item, parent, false)
                UserViewHolder(view)
            }
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.bot_msg_rv_item, parent, false)
                BotViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chatsModal = chatsModalArrayList[position]
        when (chatsModal.sender) {
            "user" -> (holder as UserViewHolder).userTV.text = chatsModal.message
            "bot" -> (holder as BotViewHolder).botMdsgTV.text = chatsModal.message
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (chatsModalArrayList[position].sender) {
            "user" -> 0
            "bot" -> 1
            else -> -1
        }
    }

    override fun getItemCount(): Int {
        return chatsModalArrayList.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userTV: TextView = itemView.findViewById(R.id.idTVUser)
    }

    class BotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val botMdsgTV: TextView = itemView.findViewById(R.id.idTVBot)
    }
}
