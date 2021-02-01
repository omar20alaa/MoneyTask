package app.money_task_kotlin.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.money_task_kotlin.global.MoneyTaskConstants
import app.money_task_kotlin.model.BudgetDetails
import app.money_task_kotlin.network.RetrofitConnection.retrofitConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BudgetDetailsRepository(private val application: Application) {
    private val mutableLiveData = MutableLiveData<BudgetDetails?>()
    var endOfResult = MutableLiveData(false)
    var progressbarObservable = MutableLiveData(false)
    fun getData(id: String?): LiveData<BudgetDetails?> {
        return mutableLiveData
    }

    fun getNewData(id: String?) {
        progressbarObservable.value = true
        val apiService = retrofitConnection
        val call = apiService.budgetsDetails(id)
        call!!.enqueue(object : Callback<BudgetDetails?> {
            override fun onResponse(
                call: Call<BudgetDetails?>,
                response: Response<BudgetDetails?>
            ) {
                if (response.body() != null) Log.d(
                    MoneyTaskConstants.tag,
                    "BudgetDetails List Success  --> " + response.body()!!.data
                )
                progressbarObservable.value = false
                mutableLiveData.value = response.body()
            }

            override fun onFailure(call: Call<BudgetDetails?>, t: Throwable) {
                progressbarObservable.value = false
                Log.d(MoneyTaskConstants.tag, "BudgetDetails List Error  --> " + t.message)
            }
        })
    }
}