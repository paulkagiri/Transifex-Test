package com.transifextest

import android.app.Application
import android.util.Log
import com.transifex.txnative.LocaleState
import com.transifex.txnative.TxNative

class App : Application() {

    val TAG = "Transifex"
    val TRANSIFEX_TOKEN = ""

    override fun onCreate() {
        super.onCreate()
        initTransifex()
    }

    private fun initTransifex() {
        try {
            Log.d(TAG, "Initializing Transifex")
            val localeState = LocaleState(applicationContext, "en", arrayOf("en", "rw", "sw"), null)
            TxNative.init(applicationContext, localeState, TRANSIFEX_TOKEN, null, null, null)
            Log.d(TAG, "Fetching translations from Transifex")
            TxNative.fetchTranslations("rw")
        } catch (t: Throwable) {
            Log.e(TAG, "Failed to init Transifex with exception ${t.localizedMessage}")
        }
    }
}