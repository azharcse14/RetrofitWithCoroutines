package com.azhar.retrofitwithcoroutines.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.azhar.retrofitwithcoroutines.R
import com.azhar.retrofitwithcoroutines.model.retrofit.QuotesAPI
import com.azhar.retrofitwithcoroutines.model.retrofit.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.create

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quotesAPI = RetrofitHelper.getInstance().create(QuotesAPI::class.java)

        GlobalScope.launch {
            val result = quotesAPI.getQuotes(10)
            if (result != null){

                val quotaList = result.body()
                if (quotaList != null){
                    quotaList.results.forEach{
                        Log.d("quota", it.content)
                    }
                }
            }
        }
    }
}