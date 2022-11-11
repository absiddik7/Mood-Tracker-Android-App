package com.app.mymoodtracker.moodtracker

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.mymoodtracker.*
import com.app.mymoodtracker.convertDateToString
import com.app.mymoodtracker.database.Moods

@BindingAdapter("dateToString")
fun TextView.setDateToString(item: Moods?) {
    item?.let {
        text = convertDateToString(item.currentTimeMilli)
    }
}
@BindingAdapter("timeToString")
fun TextView.setTimeToString(item: Moods?) {
    item?.let {
        text = convertTimeToString(item.currentTimeMilli)
    }
}

@BindingAdapter("moodQualityString")
fun TextView.setMoodQualityString(item: Moods?) {
    item?.let {
        text = convertNumericQualityToString(item.moodQuality, context.resources)
    }
}

@BindingAdapter("moodContentString")
fun TextView.setMoodContentString(item: Moods?) {
    item?.let {
        text = convertTextString(item.moodContent)
    }
}

@BindingAdapter("moodQualityImage")
fun ImageView.setMoodQualityImage(item: Moods?) {
    item?.let {
        setImageResource(
            when (item.moodQuality) {
                0 -> R.drawable.ic_happy_emoji
                1 -> R.drawable.ic_nervous_emoji
                2 -> R.drawable.ic_excited_emoji
                3 -> R.drawable.ic_depressed_emoji
                4 -> R.drawable.ic_relaxed_emoji
                5 -> R.drawable.ic_sad_emoji
                6 -> R.drawable.ic_angry_emoji
                7 -> R.drawable.ic_bored_emoji
                8 -> R.drawable.ic_scared_emoji

                else -> R.drawable.ic_launcher_mood_tracker_foreground
            }
        )
    }
}
