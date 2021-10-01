package com.example.UsersActivity

import UsersActivity.R
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class UsersAdapter(
    private val users: MutableList<Users>
) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addUser(user: Users) {
        users.add(user)
        notifyItemInserted(users.size - 1)
    }

    fun deleteDoneTodos() {
       users.removeAll { user ->
            user.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvUserTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvUserTitle.paintFlags = tvUserTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvUserTitle.paintFlags = tvUserTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val curTodo = users[position]
        holder.itemView.apply {
            tvUsersTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(tvUsersTitle, curTodo.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvUsersTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }
}


















