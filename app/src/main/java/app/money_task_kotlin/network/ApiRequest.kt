package app.money_task_kotlin.network

import app.money_task_kotlin.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiRequest {
    // todo fetch budgets =======================================================================================
    @GET("budgets")
    fun fetchBudgets(): Call<Budget?>? // budgets

    // todo  budgetsDetails ===ok w====================================================================================
    @GET("budgets/{id}")
    fun budgetsDetails(
        @Path("id") id: String?
    ): Call<BudgetDetails?>? // budget details

    // todo  Accounts =======================================================================================
    @GET("budgets/{id}/accounts")
    fun fetchAccounts(
        @Path("id") id: String?
    ): Call<Accounts?>? // fetchAccounts

    // todo  Add Account =======================================================================================
    @POST("budgets/{id}/accounts")
    fun AddNewAccount(
        @Path("id") id: String?,
        @Body addNewAccount: AddNewAccount?
    ): Call<AddAccResponse?>? // Add Account
}