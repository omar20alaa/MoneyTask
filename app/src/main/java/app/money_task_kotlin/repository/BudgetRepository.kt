package app.money_task_kotlin.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.money_task_kotlin.global.MoneyTaskConstants
import app.money_task_kotlin.model.Budget
import app.money_task_kotlin.network.RetrofitConnection.retrofitConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BudgetRepository(private val application: Application) {
    private val mutableLiveData = MutableLiveData<Budget?>()
    var endOfResult = MutableLiveData(false)
    var progressbarObservable = MutableLiveData(false)
    val data: LiveData<Budget?>
        get() = mutableLiveData
    val newData: Unit
        get() {
            progressbarObservable.value = true
            val apiService = retrofitConnection
            val call = apiService.fetchBudgets()
            call!!.enqueue(object : Callback<Budget?> {
                override fun onResponse(call: Call<Budget?>, response: Response<Budget?>) {
                    if (response.body() != null) Log.d(
                        MoneyTaskConstants.tag,
                        "Budget List Success  --> " + response.body()!!.data!!.budgets!!.size
                    )
                    progressbarObservable.value = false
                    mutableLiveData.value = response.body()
                }

                override fun onFailure(call: Call<Budget?>, t: Throwable) {
                    progressbarObservable.value = false
                    Log.d(MoneyTaskConstants.tag, "Budget List Error  --> " + t.message)
                }
            })
        }
}