package com.example.demoapplication.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import com.example.demoapplication.databinding.FragmentLoadingDialogBinding
import com.example.demoapplication.utils.extensions.makeGone
import com.example.demoapplication.utils.extensions.makeVisible

class LoadingDialog(
    context: Context
) : Dialog(context) {

    private lateinit var binding: FragmentLoadingDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding = FragmentLoadingDialogBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        setCancelable(false)
    }

    override fun onStart() {
        super.onStart()
        binding.progressBar.makeVisible()
    }

    override fun setOnDismissListener(listener: DialogInterface.OnDismissListener?) {
        binding.progressBar.makeGone()
        super.setOnDismissListener(listener)
    }

}