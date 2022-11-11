package com.app.mymoodtracker.mooddetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.mymoodtracker.database.MoodDatabaseDao

class MoodDetailsViewModelFactory(
    private val moodKey: Long,
    private val dataSource: MoodDatabaseDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoodDetailsViewModel::class.java)) {
            return MoodDetailsViewModel(moodKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}