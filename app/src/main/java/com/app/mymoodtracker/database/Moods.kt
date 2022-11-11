package com.app.mymoodtracker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "daily_mood_quality_table")

data class Moods(
    @PrimaryKey(autoGenerate = true)
    var moodId: Long = 0L,

    @ColumnInfo(name = "current_time_milli")
    val currentTimeMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "mood_content")
    var moodContent: String = "",

    @ColumnInfo(name = "quality_rating")
    var moodQuality: Int = -1
)
