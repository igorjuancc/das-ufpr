package br.com.zig.multiactivity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User (
    val userName: String,
    val n1Peso: Int,
    val n1: Double,
) : Parcelable