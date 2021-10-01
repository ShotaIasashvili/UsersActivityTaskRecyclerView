package com.example.UsersActivity

import UsersActivity.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usersAdapter = UsersAdapter(mutableListOf())

        rvTodoItems.adapter = usersAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        btnAddUser.setOnClickListener {
            val userTitle = etUsersTitle.text.toString()
            if(userTitle.isNotEmpty()) {
                val todo = Users(userTitle)
                usersAdapter.addUser(todo)
                etUsersTitle.text.clear()
            }
        }
        btnDeleteDoneTodos.setOnClickListener {
            usersAdapter.deleteDoneTodos()
        }
    }
}