package app.money_task_kotlin.view_model

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.money_task_kotlin.R
import app.money_task_kotlin.global.GlobalFunctions.showLog
import app.money_task_kotlin.global.Navigator.loadFragment
import app.money_task_kotlin.model.BudgetDetails
import app.money_task_kotlin.repository.BudgetDetailsRepository
import app.money_task_kotlin.view.fragment.AccountsFragment

class BudgetDetailsViewModel @ViewModelInject constructor(application: Application) :
    AndroidViewModel(application) {
    var repository: BudgetDetailsRepository
    fun getData(id: String?): LiveData<BudgetDetails?> {
        return repository.getData(id)
    }

    fun fetchList(id: String?) {
        repository.getNewData(id)
    }

    val endOfResult: MutableLiveData<Boolean>
        get() = repository.endOfResult
    val progress: MutableLiveData<Boolean>
        get() = repository.progressbarObservable

    fun goAccounts(activity: Context?, budget_id: String) {
        val fragment = AccountsFragment()
        val bundle = Bundle()
        bundle.putString("budget_id", budget_id)
        fragment.arguments = bundle
        showLog("sendBudget_id --> $budget_id")
        loadFragment((activity as FragmentActivity?)!!, fragment, R.id.fragment_container, true, "")
    }

    init {
        repository = BudgetDetailsRepository(application)
    }
}