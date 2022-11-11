package com.app.mymoodtracker.moodtracker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.mymoodtracker.database.MoodDatabaseDao
import com.app.mymoodtracker.database.Moods

class MoodTrackerViewModelFactory(
    private val dataSource: MoodDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoodTrackerViewModel::class.java)) {
            return MoodTrackerViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}