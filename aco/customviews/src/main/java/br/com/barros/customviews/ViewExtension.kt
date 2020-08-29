package br.com.barros.customviews

import android.view.View
import androidx.fragment.app.FragmentActivity

fun View.clearCurrentFocus() {
    (context as? FragmentActivity)?.currentFocus?.clearFocus()
}