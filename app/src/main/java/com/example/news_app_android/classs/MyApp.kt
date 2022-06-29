package com.example.news_app_android.classs

import android.app.Application
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


class myApp() : Application() {

    private var mRequestQueue: RequestQueue? = null

    companion object {
         val htttp = "https://dummyjson.com/"

        private var mInstanse: myApp? = null

        fun getInstance(): myApp? {
            return mInstanse
        }


    }

    override fun onCreate() {
        mInstanse = this
        super.onCreate()
    }

    private fun getRequestQueue(): RequestQueue? {

        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(applicationContext)
        }
        return mRequestQueue
    }


    fun <T> addtoRequstQueue(req: Request<T>, tag: String) {
        req.tag = tag
        getRequestQueue()!!.add(req)
    }


}