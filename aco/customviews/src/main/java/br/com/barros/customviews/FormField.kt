package br.com.barros.customviews

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.widget.EditText
import androidx.annotation.StyleRes
import br.com.barros.customviews.FormItem.Companion.FIELD_TYPE_CEP
import com.google.android.material.textfield.TextInputLayout

abstract class FormField @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0)
    : TextInputLayout(context, attributeSet), FormItem {

    companion object {
        const val ERROR_TYPE_UNDER = 0
        const val ERROR_TYPE_SNACKBAR = 1
        const val ERROR_TYPE_TOOLTIP = 2

        @StyleRes
        val STYLE_DEFAULT = R.style.EditTextMain

        const val EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

        const val TEXT_DEFAULT_INCOME = "0"
    }

    override var fieldType: Int = FIELD_TYPE_CEP
    override var validField: Boolean = true
    override var errorType: Int = ERROR_TYPE_UNDER
    override var animateError: Boolean = true
    override var shouldTagValidationError: Boolean = false
    override var validationErrorTag: String? =  null
    override var isOptional: Boolean = false

    var isWarning: Boolean = false
    var shouldValidateOnFocusChange: Boolean = true
    var hintFontFamily: Int = FONT_LIGHT
    var textFontFamily: Int = FONT_REGULAR
    @StyleRes
    var style: Int = STYLE_DEFAULT

    init {
        setupStyleables(attributeSet)
        setupView()
    }


    fun getStyledAttributes(attributeSet: AttributeSet?): TypedArray {
        return context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.FormField,
            0,0
        )
    }

    internal open fun setupStyleables(attributeSet: AttributeSet?) {
        getStyledAttributes(attributeSet).apply {
            try {
                fieldType = getInteger(R.styleable.FormField_fieldType, FIELD_TYPE_CEP)
            } finally {
                recycle()
            }
        }
    }

    internal open fun setupView(isSpinner: Boolean? = null) {
        addView(createEditText(isSpinner))
    }

    private fun createEditText(isSpinner: Boolean?): EditText {
        return TextInputEditTextMain(ContextThemeWrapper(context, style), null, 0, isSpinner)
    }
}