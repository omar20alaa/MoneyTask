package app.money_task_kotlin.adapter

import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import app.money_task_kotlin.databinding.AccountItemBinding
import app.money_task_kotlin.global.GlobalFunctions
import app.money_task_kotlin.model.Accounts
import java.util.ArrayList

class AccountsAdapter(
    act: FragmentActivity,
    list: ArrayList<Accounts.DataList.AccountsList>?,
    listener: OnItemClickListener
) : RecyclerView.Adapter<AccountsAdapter.MyViewHolder>() {

    // todo vars
    var list: ArrayList<Accounts.DataList.AccountsList>? = ArrayList()
    var act: FragmentActivity
    var binding: AccountItemBinding? = null
    val listener: OnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = AccountItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (list!![position].name != null) {
            GlobalFunctions.setTextView(binding!!.tvName, list!![position].name)
        }
        if (list!![position].type != null) {
            GlobalFunctions.setTextView(binding!!.type, list!![position].type)
        }
        if (list!![position].balance != null) {
            GlobalFunctions.setTextView(binding!!.balance, list!![position].balance.toString() + "")
        }
        holder.itemView.tag = list!![position]
        holder.itemView.setOnClickListener { listener.onItemClick(position) }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class MyViewHolder(var binding: AccountItemBinding) : RecyclerView.ViewHolder(
        binding.root
    )

    init {
        this.list = list
        this.act = act
        this.listener = listener
    }
}