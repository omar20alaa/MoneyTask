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
import app.money_task_kotlin.model.Budget
import app.money_task_kotlin.repository.BudgetRepository
import app.money_task_kotlin.view.fragment.BudgetDetailsFragment

class BudgetViewModel @ViewModelInject constructor(application: Application) :
    AndroidViewModel(application) {
    var repository: BudgetRepository
    val data: LiveData<Budget?>
        get() = repository.data

    fun fetchList() {
        repository.newData
    }

    val endOfResult: MutableLiveData<Boolean>
        get() = repository.endOfResult
    val progress: MutableLiveData<Boolean>
        get() = repository.progressbarObservable

    fun goBudgetDetails(activity: Context?, budget_id: String, budget_name: String) {
        val fragment = BudgetDetailsFragment()
        val bundle = Bundle()
        bundle.putString("budget_id", budget_id)
        bundle.putString("budget_name", budget_name)
        fragment.arguments = bundle
        showLog("sendBudget_id --> $budget_id")
        showLog("sendBudget_name--> $budget_name")
        loadFragment((activity as FragmentActivity?)!!, fragment, R.id.fragment_container, true, "")
    }

    init {
        repository = BudgetRepository(application)
        data
    }
}