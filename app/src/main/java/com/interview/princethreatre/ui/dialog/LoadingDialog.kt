package com.interview.princethreatre.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.interview.princethreatre.R

class LoadingDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_loading)
        dialog.setCancelable(false)
        return dialog
    }
}