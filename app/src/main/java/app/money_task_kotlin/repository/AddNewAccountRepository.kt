package app.money_task_kotlin.repository

import android.app.Application
import androidx.databinding.BaseObservable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.money_task_kotlin.model.AddAccResponse
import app.money_task_kotlin.model.AddNewAccount
import app.money_task_kotlin.network.RetrofitConnection.retrofitConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddNewAccountRepository(private val application: Application) : BaseObservable() {
    fun addAccount(id: String?, addNewAccount: AddNewAccount?): LiveData<AddAccResponse?> {
        val mutableLiveData = MutableLiveData<AddAccResponse?>()
        val apiService = retrofitConnection
        val call = apiService.AddNewAccount(id, addNewAccount)
        call!!.enqueue(object : Callback<AddAccResponse?> {
            override fun onResponse(
                call: Call<AddAccResponse?>,
                response: Response<AddAccResponse?>
            ) {
                mutableLiveData.value = response.body()
            }

            override fun onFailure(call: Call<AddAccResponse?>, t: Throwable) {
                mutableLiveData.value = null
            }
        })
        return mutableLiveData
    }
}