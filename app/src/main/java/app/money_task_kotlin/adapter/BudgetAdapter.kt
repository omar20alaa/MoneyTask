package app.money_task_kotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import app.money_task_kotlin.R
import app.money_task_kotlin.databinding.BudgetItemBinding
import app.money_task_kotlin.global.GlobalFunctions
import app.money_task_kotlin.model.Budget.DataList.BudgetsList
import java.util.*

class BudgetAdapter(
    act: FragmentActivity,
    list: ArrayList<BudgetsList>?,
    listener: OnItemClickListener
) : RecyclerView.Adapter<BudgetAdapter.MyViewHolder>() {
    // todo vars
     var list: ArrayList<BudgetsList>? = ArrayList()
    var act: FragmentActivity
    var binding: BudgetItemBinding? = null
    val listener: OnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = BudgetItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        GlobalFunctions.showLog("getFirst_month. --> " + list!![position].first_month)
        if (list!![position].name != null) {
            GlobalFunctions.setTextView(binding!!.tvName, list!![position].name)
        }
        if (list!![position].last_modified_on != null) {
            GlobalFunctions.setTextView(
                binding!!.lastModifiedOn,
                act.getString(R.string.last_modified_on) + " " +
                        GlobalFunctions.convertDateToString(list!![position].last_modified_on!!)
            )
        }
        if (list!![position].currency_format != null) {
            GlobalFunctions.setTextView(
                binding!!.currencyFormat,
                act.getString(R.string.currency) + " " +
                        list!![position].currency_format!!.iso_code
            )
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

    inner class MyViewHolder(var binding: BudgetItemBinding) : RecyclerView.ViewHolder(
        binding.root
    )

    init {
        this.list = list
        this.act = act
        this.listener = listener
    }
}