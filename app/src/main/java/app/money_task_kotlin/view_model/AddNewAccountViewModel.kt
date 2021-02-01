package app.money_task_kotlin.view_model

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import app.money_task_kotlin.model.AddAccResponse
import app.money_task_kotlin.model.AddNewAccount
import app.money_task_kotlin.repository.AddNewAccountRepository

class AddNewAccountViewModel @ViewModelInject constructor(application: Application) :
    AndroidViewModel(application) {
    var repository: AddNewAccountRepository
    fun addNewAccount(id: String?, addNewAccount: AddNewAccount?): LiveData<AddAccResponse?> {
        return repository.addAccount(id, addNewAccount)
    }

    init {
        repository = AddNewAccountRepository(application)
    }
}