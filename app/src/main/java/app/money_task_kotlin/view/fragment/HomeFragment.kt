package app.money_task_kotlin.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.money_task_kotlin.R
import app.money_task_kotlin.adapter.BudgetAdapter
import app.money_task_kotlin.databinding.FragmentHomeBinding
import app.money_task_kotlin.global.GlobalFunctions.setTextView
import app.money_task_kotlin.global.GlobalFunctions.showLog
import app.money_task_kotlin.model.Budget
import app.money_task_kotlin.model.Budget.DataList.BudgetsList
import app.money_task_kotlin.view.activity.MainActivity
import app.money_task_kotlin.view_model.BudgetViewModel
import java.util.*

class HomeFragment : Fragment() {

    // todo vars
    var binding: FragmentHomeBinding? = null
    var budgetViewModel: BudgetViewModel? = null
    var budgetsLists = ArrayList<BudgetsList>()
    var budgetAdapter: BudgetAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view: View = binding!!.root
        showLog("HomeFragment Called --> ")
        initAct()
        initVisibility()
        initRv()
        initHomeViewModel()
        initShimmer()
        return view
    }

    private fun initHomeViewModel() {
        budgetViewModel = ViewModelProvider(activity!!).get(BudgetViewModel::class.java)
        initTabsViewModel()
    }

    private fun initShimmer() {
        budgetViewModel!!.progress.observe(activity!!, { aBoolean ->
            if (aBoolean) {
                binding!!.rvBudget.showShimmer()
            } else {
                binding!!.rvBudget.hideShimmer()
            }
        })
    }

    // todo initTabsViewModel
    private fun initTabsViewModel() {
        budgetViewModel!!.data.observe(activity!!, Observer {
            budgetsLists.clear()
            budgetsLists.addAll(it!!.data!!.budgets!!)
            showLog("budgetList --> " + it!!.data!!.budgets!!.size)
            showLog("myBudgetList. --> " + budgetsLists.size)
            budgetAdapter =
                BudgetAdapter(activity!!, budgetsLists, object : BudgetAdapter.OnItemClickListener {
                    override fun onItemClick(tab_position: Int) {
                        budgetViewModel!!.goBudgetDetails(
                            activity,
                            it!!.data!!.budgets!![0].id!!,
                            it!!.data!!.budgets!![0].name!!
                        )
                    }
                })
            binding!!.rvBudget.adapter = budgetAdapter
        })
        budgetViewModel!!.fetchList()
    } // initTabsViewModel

    // todo init recycler view
    private fun initRv() {
        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding!!.rvBudget.layoutManager = layoutManager
        binding!!.rvBudget.setHasFixedSize(true)
    }

    private fun initVisibility() {
        MainActivity.binding!!.toolbar.toolbar.visibility = View.VISIBLE
        MainActivity.binding!!.toolbar.imgBack.visibility = View.GONE
        setTextView(MainActivity.binding!!.toolbar.tvTitle, activity!!.getString(R.string.app_name))
    }

    private fun initAct() {
        var activity: FragmentActivity? = null
        if (activity == null) activity = getActivity()
    }
}