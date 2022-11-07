package com.transifextest

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.TxContextWrappingDelegate

open class BaseActivity : AppCompatActivity() {

    private var mAppCompatDelegate: TxContextWrappingDelegate? = null
    private val mResources: Resources? = null

    override fun getDelegate(): AppCompatDelegate {
        // Wrap AppCompat delegate
        if (mAppCompatDelegate == null) {
            mAppCompatDelegate = TxContextWrappingDelegate(super.getDelegate())
        }
        return mAppCompatDelegate!!
    }
}

/**
 * This does not work too
 * open class BaseActivity : TxBaseAppCompatActivity()
 */