package app.money_task_kotlin.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.money_task_kotlin.R
import app.money_task_kotlin.databinding.FragmentBudgetDetailsBinding
import app.money_task_kotlin.global.GlobalFunctions.convertDateToString
import app.money_task_kotlin.global.GlobalFunctions.setTextView
import app.money_task_kotlin.global.GlobalFunctions.showLog
import app.money_task_kotlin.model.BudgetDetails
import app.money_task_kotlin.view.activity.MainActivity
import app.money_task_kotlin.view_model.BudgetDetailsViewModel

class BudgetDetailsFragment : Fragment() {
    // todo vars
    var binding: FragmentBudgetDetailsBinding? = null
    var budget_name: String? = ""
    var budget_id: String? = ""
    var budgetViewModel: BudgetDetailsViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBudgetDetailsBinding.inflate(inflater, container, false)
        val view: View = binding!!.root
        showLog("BudgetDetailsFragment Called --> ")
        initAct()
        receivedData()
        initProgress()
        return view
    }

    private fun initProgress() {
        budgetViewModel!!.progress.observe(activity!!, { aBoolean ->
            if (aBoolean) {
                binding!!.loadingLayout.loading.visibility = View.VISIBLE
            } else {
                binding!!.loadingLayout.loading.visibility = View.GONE
            }
        })
    }

    // todo come from
    private fun receivedData() {
        if (arguments != null) {
            budget_id = arguments!!.getString("budget_id")
            budget_name = arguments!!.getString("budget_name")
            showLog("budget_id --> $budget_id")
            showLog("budget_name --> $budget_name")
        }
        initVisibility()
        initViewModel()
    }

    // todo initTabsViewModel
    private fun initViewModel() {
        budgetViewModel = ViewModelProvider(activity!!).get(
            BudgetDetailsViewModel::class.java
        )
        budgetViewModel!!.getData(budget_id).observe(activity!! , Observer {
            setData(it!!)
        })
        budgetViewModel!!.fetchList(budget_id)
        initClicks()
    }

    private fun initClicks() {
        binding!!.tvAccounts.setOnClickListener {
            budgetViewModel!!.goAccounts(
                activity,
                budget_id!!
            )
        }
        binding!!.tvPayee.setOnClickListener { }
    }

    // todo set data
    private fun setData(budgetList: BudgetDetails) {
        setTextView(
            binding!!.tvName,
            activity!!.getString(R.string.name) + " " +
                    budgetList.data!!.budget!!.name
        )
        setTextView(
            binding!!.lastModifiedOn,
            activity!!.getString(R.string.last_modified_on) + " " +
                    convertDateToString(budgetList.data!!.budget!!.last_modified_on!!)
        )
        setTextView(
            binding!!.currencyFormat,
            activity!!.getString(R.string.currency) + " " +
                    budgetList.data!!.budget!!.currency_format!!.iso_code
        )
        setTextView(
            binding!!.firstMonth,
            activity!!.getString(R.string.first_month) + " " +
                    budgetList.data!!.budget!!.first_month
        )
        setTextView(
            binding!!.lastMonth,
            activity!!.getString(R.string.last_month) + " " +
                    budgetList.data!!.budget!!.last_month
        )
    }

    private fun initVisibility() {
        MainActivity.binding!!.toolbar.toolbar.visibility = View.VISIBLE
        MainActivity.binding!!.toolbar.imgBack.visibility = View.VISIBLE
        setTextView(MainActivity.binding!!.toolbar.tvTitle, budget_name)
    }

    private fun initAct() {
        var activity : FragmentActivity? = null
        if (activity == null) activity = getActivity()
    }
}