package com.acerolla.ui_android.utils

fun String.isPasswordValid(): Boolean {
    return if (this.isEmpty()) {
        false
    } else this.length >= 6
}

fun String.isUsernameValid(): Boolean {
    return if (this.isEmpty()) {
        false
    } else this.length >= 5
}

fun String.isEmailValid() : Boolean {
    return if (!this.contains("@")) {
        return false
    } else if (!this.contains(".")) {
        return false
    } else (!this.contains("mail")
            || !this.contains("yandex")
            || !this.contains("gmail"))
}