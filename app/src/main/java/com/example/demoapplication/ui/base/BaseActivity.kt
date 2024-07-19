package com.example.demoapplication.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.demoapplication.ui.dialog.LoadingDialog
import com.example.demoapplication.utils.extensions.getBinding

abstract class BaseActivity<B: ViewBinding>: AppCompatActivity() {

    lateinit var binding : B
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (::binding.isInitialized.not()) {
            binding = getBinding()
            setContentView(binding.root)
        }
        loadingDialog = LoadingDialog(this)
        setupView()
        setupObserver()
        setupListener()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    open fun setupView() {}

    open fun setupObserver() {}

    open fun setupListener() {}

    open fun showLoading() {
        if (!loadingDialog.isShowing) loadingDialog.show()
    }

    open fun hideLoading() {
        if (loadingDialog.isShowing) loadingDialog.dismiss()
    }


}