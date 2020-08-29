package br.com.barros.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import com.google.android.material.textfield.TextInputEditText

class TextInputEditTextMain @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    var isSpinner: Boolean? = null
): TextInputEditText(context, attributeSet, defStyleAttr) {

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        setAccessibility()
    }

    private fun setAccessibility() {
        ViewCompat.setAccessibilityDelegate(this, object : AccessibilityDelegateCompat(){
            override fun onInitializeAccessibilityNodeInfo(
                host: View?,
                info: AccessibilityNodeInfoCompat?
            ) {
                super.onInitializeAccessibilityNodeInfo(host, info)

                if(isSpinner == true) {
                    info?.className = Spinner::class.java.name
                    info?.setCanOpenPopup(true)
                }
            }
        })
    }
}