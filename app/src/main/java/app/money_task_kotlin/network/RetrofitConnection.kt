package app.money_task_kotlin.network

import app.money_task_kotlin.global.MoneyTaskConstants
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitConnection {
    private var apiRequest: ApiRequest? = null
    private val instance: RetrofitConnection? = null
    private val logging = HttpLoggingInterceptor()
    private var retrofit: Retrofit? = null

    // Retrofit Client Method
    val retrofitConnection: ApiRequest
        get() {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + MoneyTaskConstants.token)
                    .build()
                chain.proceed(request)
            })
            httpClient.addInterceptor(logging)
            val gson = GsonBuilder()
                .setLenient()
                .create()
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(MoneyTaskConstants.base_url)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build())
                    .build()
            }
            return retrofit!!.create(ApiRequest::class.java).also { apiRequest = it }
        }
}