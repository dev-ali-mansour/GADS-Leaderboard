package dev.alimansour.gadsleaderboard.utils

import android.app.Activity
import android.content.ContextWrapper
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * GADS Leaderboard Android Application developed by: Ali Mansour
 * Copyright © 2020 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- GADS Leaderboard IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */

fun hideKeyboard(view: View) {
    var context = view.context
    while (context !is Activity && context is ContextWrapper) {
        context = context.baseContext
    }

    val inputMethodManager =
        (context as Activity).getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

}