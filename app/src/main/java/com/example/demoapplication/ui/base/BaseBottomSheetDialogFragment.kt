package com.example.demoapplication.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.demoapplication.ui.dialog.LoadingDialog
import com.example.demoapplication.utils.extensions.getBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class BaseBottomSheetDialogFragment<B : ViewBinding> : BottomSheetDialogFragment() {

    private var _binding: B? = null
    val binding: B
        get() = _binding ?: throw RuntimeException(
            "Should only use binding after onCreateView and before onDestroyView"
        )
    protected fun requireBinding(): B = requireNotNull(_binding)
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = getBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog = LoadingDialog(requireContext())
        setupView()
        setupObserver()
        setupListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
