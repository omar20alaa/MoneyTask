package app.money_task_kotlin.view_model

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.money_task_kotlin.model.Accounts
import app.money_task_kotlin.repository.AccountsRepository

class AccountsViewModel @ViewModelInject constructor(application: Application) :

    AndroidViewModel(application) {
    var repository: AccountsRepository
    val data: LiveData<Accounts?>
        get() = repository.data

    fun fetchList(id: String?) {
        repository.getNewData(id)
    }

    val endOfResult: MutableLiveData<Boolean>
        get() = repository.endOfResult
    val progress: MutableLiveData<Boolean>
        get() = repository.progressbarObservable

    init {
        repository = AccountsRepository(application)
        data
    }
}