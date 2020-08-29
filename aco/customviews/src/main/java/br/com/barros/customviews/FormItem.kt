package br.com.barros.customviews

import android.view.View

interface FormItem {

    companion object {
        const val FIELD_TYPE_CEP = 0
        const val FIELD_TYPE_PASSWORD = 1
        const val FIELD_TYPE_SPINNER = 11
    }

    var fieldType: Int
    var validField: Boolean
    var errorType: Int
    var animateError: Boolean
    var shouldTagValidationError: Boolean
    var validationErrorTag: String?
    var isOptional: Boolean

    fun shouldScrollToBottom() = fieldType != FIELD_TYPE_PASSWORD

    fun getDefaultSpacing(): Int {
        this as View
        return "4".toInt()
    }
}