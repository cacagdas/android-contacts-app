package com.cacagdas.contactsapp.core.widget

import android.content.Context
import androidx.appcompat.app.AppCompatDialog
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.cacagdas.contactsapp.R

class WidgetProgressDialog(context: Context) : DefaultLifecycleObserver {

    private var progressDialog: AppCompatDialog? = null

    init {
        progressDialog = AppCompatDialog(context, R.style.Theme_ContactsApp_Dialog_Progress).apply {
            setContentView(R.layout.dialog_progress)
            setCancelable(false)
        }
    }

    fun showOrHide(show: Boolean?) {
        if (show == true) {
            show()
        } else {
            dismissDialog()
        }
    }

    private fun show() {
        if (progressDialog?.isShowing == false) {
            progressDialog?.show()
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        dismissDialog()
    }

    private fun dismissDialog() {
        progressDialog?.dismiss()
    }
}
