package com.transifextest

import android.content.Context
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.TxContextWrappingDelegate

open class BaseActivity : AppCompatActivity() {

    /**
     * When I activate the method below then no translation is done
     *
     */
    private var mAppCompatDelegate: TxContextWrappingDelegate? = null
    private val mResources: Resources? = null

    override fun getDelegate(): AppCompatDelegate {
        // Wrap AppCompat delegate
        if (mAppCompatDelegate == null) {
            mAppCompatDelegate = TxContextWrappingDelegate(super.getDelegate())
        }
        return mAppCompatDelegate!!
    }

    /**
     * The following method is able to set the locale as needed
     */
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(
            LocaleService.updateBaseContextLocale(newBase)
        )
    }
}
