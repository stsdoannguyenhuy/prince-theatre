package com.interview.princethreatre.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.interview.princethreatre.ui.dialog.LoadingDialog


abstract class BaseActivity : AppCompatActivity() {
    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hidingNavigationBar()
    }

    private fun hidingNavigationBar() {
        this.supportActionBar?.hide()
    }

    fun showLoading() {
        loadingDialog = supportFragmentManager.findFragmentByTag("ProgressDialog") as? LoadingDialog
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog()
        }
        loadingDialog?.isCancelable = false

        loadingDialog?.takeIf { !it.isAdded }?.let {
            loadingDialog?.show(supportFragmentManager, "ProgressDialog")
        }
    }

    fun hideLoading() {
        loadingDialog?.dismiss()
        loadingDialog = null
    }
}