package com.airatlovesmusic.coding.ui.global

import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment(@LayoutRes layoutId: Int): Fragment(layoutId) {

    infix fun <U> LiveData<U>.bindTo(f: (U) -> Unit) {
        observe(this@BaseFragment.viewLifecycleOwner, Observer { it?.let(f)  })
    }

    open fun showError(message: String?) {
        view?.let {
            if(!message.isNullOrEmpty()) {
                Snackbar.make(it, message ?: "", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark))
                    .setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
                    .apply {
                        view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                            ?.let { tv -> tv.maxLines = 5 }
                    }
                    .show()
            }
        }
    }

}