package com.app.mymoodtracker.moodquality

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.mymoodtracker.database.MoodDatabaseDao

class MoodQualityViewModelFactory(

    private val dataSource: MoodDatabaseDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoodQualityViewModel::class.java)) {
            return MoodQualityViewModel( dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}