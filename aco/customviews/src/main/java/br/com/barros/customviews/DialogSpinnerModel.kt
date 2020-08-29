package br.com.barros.customviews

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class DialogSpinnerModel(
    val codigo: String,
    val nome: String
): Parcelable