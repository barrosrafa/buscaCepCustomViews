package br.com.barros.customviews

import android.content.Context
import android.content.res.TypedArray
import android.text.TextUtils
import android.util.AttributeSet
import androidx.fragment.app.FragmentActivity
import br.com.barros.customviews.FormItem.Companion.FIELD_TYPE_SPINNER

class Spinner @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FormField(context, attributeSet, defStyleAttr) {

    enum class SpinnerType(val type: Int) {
        STATES(6)
    }

    var spinnerType: SpinnerType? = null
    var spinnerList: List<DialogSpinnerModel>? = null

    /** Determina o valor que será enviado para a API */
    internal var textFormattedForApi: String? = ""
    internal var textFormattedForTextList: String? = ""

    private var listener: ((DialogSpinnerModel) -> Unit)? = null

    override fun setupStyleables(attributeSet: AttributeSet?) {
        super.setupStyleables(attributeSet)
        context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.Spinner,
            0,0
        ).apply {
            try {
                spinnerType = getTypeFromStylleable()
            } finally {
                recycle()
            }
        }
    }

    private fun TypedArray.getTypeFromStylleable(): Spinner.SpinnerType? {
        return when(getInteger(R.styleable.Spinner_spinnerType, SpinnerType.STATES.type)) {
            SpinnerType.STATES.type -> SpinnerType.STATES
            else -> SpinnerType.STATES
        }
    }

    override fun setupView(isSpinner: Boolean?) {
        super.setupView(isSpinner)
        setFieldType()
        setSpinnerChevron()
    }

    private fun setSpinnerChevron() {
        editText?.apply {
            setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_chevron_left_black_24dp, 0)
            compoundDrawablePadding = 24
            setPadding(paddingLeft, paddingTop, context.dpToInt(12), paddingBottom)
            ellipsize = TextUtils.TruncateAt.END
        }
    }

    private fun setFieldType() {
        fieldType = FIELD_TYPE_SPINNER
    }

    private fun setSpinnerClickListener() {
        setOnFieldClickListener {
            clearCurrentFocus()
            showDialogSpinner()
        }
    }

    private fun showDialogSpinner() {
        listener?.let { listener ->
            val fragmentManager = (context as FragmentActivity).supportFragmentManager
            spinnerList.let {
            }
        }
    }

}