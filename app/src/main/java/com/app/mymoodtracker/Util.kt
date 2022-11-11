/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.app.mymoodtracker

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.app.mymoodtracker.database.Moods
import java.text.SimpleDateFormat
import java.util.*

private val ONE_MINUTE_MILLIS = java.util.concurrent.TimeUnit.MILLISECONDS.convert(1, java.util.concurrent.TimeUnit.MINUTES)
private val ONE_HOUR_MILLIS = java.util.concurrent.TimeUnit.MILLISECONDS.convert(1, java.util.concurrent.TimeUnit.HOURS)

/**
 * These functions create a formatted string that can be set in a TextView.
 */

/**
 * Returns a string representing the numeric quality rating.
 */
fun convertNumericQualityToString(quality: Int, resources: Resources): String {
    var qualityString = resources.getString(R.string.mood_Excited)
    when (quality) {
        -1 -> qualityString = "--"
        0 -> qualityString = resources.getString(R.string.mood_Happy)
        1 -> qualityString = resources.getString(R.string.mood_Nervous)
        2 -> qualityString = resources.getString(R.string.mood_Excited)
        3 -> qualityString = resources.getString(R.string.mood_Depressed)
        4 -> qualityString = resources.getString(R.string.mood_Relaxed)
        5 -> qualityString = resources.getString(R.string.mood_Sad)
        6 -> qualityString = resources.getString(R.string.mood_Angry)
        7 -> qualityString = resources.getString(R.string.mood_Bored)
        8 -> qualityString = resources.getString(R.string.mood_Scared)

    }
    return qualityString
}

/**
 * Take the Long milliseconds returned by the system and stored in Room,
 * and convert it to a nicely formatted string for display.
 * EEEE - Display the long letter version of the weekday
 * MMM - Display the letter abbreviation of the nmotny
 * dd-yyyy - day in month and full year numerically
 * HH:mm - Hours and minutes in 24hr format
 */
@SuppressLint("SimpleDateFormat")
fun convertTimeToString(systemTime: Long): String {
    return SimpleDateFormat("'Time: 'hh:mm a")
            .format(systemTime).toString()
}
@SuppressLint("SimpleTextFormat")
fun convertTextString(string: String): String {
    return string
}

@SuppressLint("SimpleDateFormat")
fun convertDateToString(systemTime: Long): String {
    return SimpleDateFormat("EEEE MMM-dd-yyyy")
        .format(systemTime).toString()
}

