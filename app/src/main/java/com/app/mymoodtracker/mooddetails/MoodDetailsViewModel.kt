package com.app.mymoodtracker.mooddetails

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.mymoodtracker.database.MoodDatabaseDao
import com.app.mymoodtracker.database.Moods

class MoodDetailsViewModel(
    moodKey: Long = 0L,
    dataSource: MoodDatabaseDao
) : ViewModel() {

    //private val dataSources = dataSource

    private val mood = MediatorLiveData<Moods>()

    fun getMood() = mood

    init {
        mood.addSource(dataSource.getMoodWithId(moodKey), mood::setValue)
    }
}