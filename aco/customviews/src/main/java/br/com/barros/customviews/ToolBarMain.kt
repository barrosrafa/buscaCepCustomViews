package br.com.barros.customviews

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

class ToolBarMain @JvmOverloads constructor( context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {

    companion object {
        const val TOOL_BAR_MAIN = 0
    }

    var toolBarType: Int =
        TOOL_BAR_MAIN
    private var toolBarTitleString: String? = null

    init {
        setupStyleables(attrs)
        setToolbarType(toolBarType)
    }

    private fun setupStyleables(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ToolBarMain,
            0,0).apply {
            try {
                if(toolBarType == TOOL_BAR_MAIN) toolBarType = getInteger(R.styleable.ToolBarMain_toolBarType,
                    TOOL_BAR_MAIN
                )

                toolBarTitleString = getString(R.styleable.ToolBarMain_titleToolbarMain)
            } finally {
                recycle()
            }
        }
    }

    private fun setToolbarType(type: Int) {
        if(!isInEditMode) {
            this.removeAllViews()
        }

        toolBarType = type

        val toolBarLayout: Int = when(type) {
            TOOL_BAR_MAIN -> R.layout.toolbar_main
            else ->
                R.layout.toolbar_main
        }


        inflate(context, toolBarLayout, this)

        if(!isInEditMode) {
            initView()
        }
    }

    private fun initView() {
        //id = R.id.
        //initComponents(toolBarType)
    }
}
