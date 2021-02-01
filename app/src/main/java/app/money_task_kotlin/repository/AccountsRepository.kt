package app.money_task_kotlin.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.money_task_kotlin.global.MoneyTaskConstants
import app.money_task_kotlin.model.Accounts
import app.money_task_kotlin.network.RetrofitConnection.retrofitConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountsRepository(private val application: Application) {
    private val mutableLiveData = MutableLiveData<Accounts?>()
    var endOfResult = MutableLiveData(false)
    var progressbarObservable = MutableLiveData(false)
    val data: LiveData<Accounts?>
        get() = mutableLiveData

    fun getNewData(id: String?) {
        progressbarObservable.value = true
        val apiService = retrofitConnection
        val call = apiService.fetchAccounts(id)
        call!!.enqueue(object : Callback<Accounts?> {
            override fun onResponse(call: Call<Accounts?>, response: Response<Accounts?>) {
                if (response.body() != null) Log.d(
                    MoneyTaskConstants.tag,
                    "Accounts List Success  --> " + response.body()!!.data!!.getAccounts()!!.size
                )
                progressbarObservable.value = false
                mutableLiveData.value = response.body()
            }

            override fun onFailure(call: Call<Accounts?>, t: Throwable) {
                progressbarObservable.value = false
                Log.d(MoneyTaskConstants.tag, "Accounts List Error  --> " + t.message)
            }
        })
    }
}