package com.example.mvulatrustmobileapp

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity3 : AppCompatActivity() {

    private lateinit var chatsRV: RecyclerView
    private lateinit var userMsgEdt: EditText
    private lateinit var sendMsgFAB: FloatingActionButton
    private val BOT_KEY = "bot"
    private val USER_KEY = "user"
    private val chatsModalArrayList = ArrayList<ChatsModal>()
    private lateinit var chatRVAdapter: ChatRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        chatsRV = findViewById(R.id.idRVChats)
        userMsgEdt = findViewById(R.id.idEdtMessage)
        sendMsgFAB = findViewById(R.id.idFABSend)
        chatRVAdapter = ChatRVAdapter(chatsModalArrayList, this)
        val manager = LinearLayoutManager(this)
        chatsRV.layoutManager = manager
        chatsRV.adapter = chatRVAdapter

        sendMsgFAB.setOnClickListener {
            if (userMsgEdt.text.toString().isEmpty()) {
                Toast.makeText(this@MainActivity3, "Please enter your message", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            getResponse(userMsgEdt.text.toString())
            userMsgEdt.text.clear()
        }
    }

    private fun getResponse(message: String) {
        chatsModalArrayList.add(ChatsModal(message, USER_KEY))
        chatRVAdapter.notifyDataSetChanged()
        val url = "http://api.brainshop.ai/get?bid=156541&key=RdjOJWhMeR2JDdOW&uid=[uid]&msg=$message"
        val BASE_URL = "http://api.brainshop.ai/"
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitAPI = retrofit.create(RetrofitAPI::class.java)
        val call = retrofitAPI.getMessage(url)
        if (call != null) {
            call.enqueue(object : Callback<MsgModal?> {
                override fun onResponse(call: Call<MsgModal?>, response: Response<MsgModal?>) {
                    if (response.isSuccessful) {
                        val modal = response.body()
                        if (modal != null) {
                            chatsModalArrayList.add(ChatsModal(modal.cnt ?: "", BOT_KEY))
                            chatRVAdapter.notifyDataSetChanged()
                        } else {
                            chatsModalArrayList.add(ChatsModal("Received a null response", BOT_KEY))
                            chatRVAdapter.notifyDataSetChanged()
                        }
                    } else {
                        chatsModalArrayList.add(ChatsModal("Error in response", BOT_KEY))
                        chatRVAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<MsgModal?>, t: Throwable) {
                    chatsModalArrayList.add(ChatsModal("Please revert your question", BOT_KEY))
                    chatRVAdapter.notifyDataSetChanged()
                }
            })

        }
    }
}
