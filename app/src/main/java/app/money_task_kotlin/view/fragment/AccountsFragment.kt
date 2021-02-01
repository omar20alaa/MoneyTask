package app.money_task_kotlin.view.fragment

import android.app.Activity
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
import app.money_task_kotlin.adapter.AccountsAdapter
import app.money_task_kotlin.databinding.FragmentAccountsBinding
import app.money_task_kotlin.global.GlobalFunctions.setTextView
import app.money_task_kotlin.global.GlobalFunctions.showLog
import app.money_task_kotlin.global.IntentTo.goToAddAccount
import app.money_task_kotlin.model.Accounts
import app.money_task_kotlin.view.activity.MainActivity
import app.money_task_kotlin.view_model.AccountsViewModel
import java.util.*

class AccountsFragment : Fragment() {

    // todo vars
    var binding: FragmentAccountsBinding? = null
    var accountsViewModel: AccountsViewModel? = null
    var accountsLists = ArrayList<Accounts.DataList.AccountsList>()
    var accountsAdapter: AccountsAdapter? = null

    var budget_id: String? = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountsBinding.inflate(inflater, container, false)
        val view: View = binding!!.root
        showLog("AccountsFragment Called --> ")
        initAct()
        initRv()
        initClicks()
        receivedData()
        return view
    }

    private fun initClicks() {
        binding!!.tvAddAcc.setOnClickListener { goToAddAccount(activity, budget_id!!) }
    }

    // todo come from
    private fun receivedData() {
        if (arguments != null) {
            budget_id = arguments!!.getString("budget_id")
            showLog("budget_id --> $budget_id")
        }
        initVisibility()
        initViewModel()
    }

    private fun initViewModel() {
        accountsViewModel = ViewModelProvider(activity!!).get(AccountsViewModel::class.java)
        initShimmer()
        initAccViewModel()
    }

    private fun initShimmer() {
        accountsViewModel!!.progress.observe(activity!!, { aBoolean ->
            if (aBoolean) {
                binding!!.rvAccounts.showShimmer()
            } else {
                binding!!.rvAccounts.hideShimmer()
            }
        })
    }

    // todo initTabsViewModel
    private fun initAccViewModel() {

        accountsViewModel!!.data.observe(activity!!, Observer {
            accountsLists.clear()
            accountsLists.addAll(it!!.data!!.getAccounts()!!)
            showLog("accountsLists --> " + it!!.data!!.getAccounts()!!.size)
            showLog("myAccountsListst. --> " + accountsLists.size)
            accountsAdapter = AccountsAdapter(
                activity!!,
                accountsLists,
                object : AccountsAdapter.OnItemClickListener {
                    override fun onItemClick(tab_position: Int) {}
                })
            binding!!.rvAccounts.adapter = accountsAdapter
        })
        accountsViewModel!!.fetchList(budget_id)
    } // initTabsViewModel

    // todo init recycler view
    private fun initRv() {
        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding!!.rvAccounts.layoutManager = layoutManager
        binding!!.rvAccounts.setHasFixedSize(true)
    }

    private fun initVisibility() {
        MainActivity.binding!!.toolbar.toolbar.visibility = View.VISIBLE
        MainActivity.binding!!.toolbar.imgBack.visibility = View.GONE
        setTextView(MainActivity.binding!!.toolbar.tvTitle, activity!!.getString(R.string.accounts))
    }

    private fun initAct() {
        var activity : FragmentActivity? = null
        if (activity == null) activity = getActivity()
    }
}