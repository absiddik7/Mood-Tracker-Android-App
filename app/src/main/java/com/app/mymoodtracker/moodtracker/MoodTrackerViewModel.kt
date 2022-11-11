package com.app.mymoodtracker.moodtracker


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.mymoodtracker.convertDateToString
import com.app.mymoodtracker.database.MoodDatabaseDao
import com.app.mymoodtracker.database.Moods
import kotlinx.coroutines.*

class MoodTrackerViewModel(
    val database: MoodDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var currentMood = MutableLiveData<Moods?>()
    val allMoods = database.getAllMoods()

    private val _navigationToMoodQuality = MutableLiveData<Boolean>()
    val navigationToMoodQuality: LiveData<Boolean>
        get() = _navigationToMoodQuality

    fun doneNavigation() {
        _navigationToMoodQuality.value = null
    }

    fun onStartTracking() {
        _navigationToMoodQuality.value = true
    }

    fun onClear() {
        uiScope.launch {
            clear()
            currentMood.value = null
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    private val _navigateToMoodDetails = MutableLiveData<Long>()
    val navigationToMoodDetails
        get() = _navigateToMoodDetails

    fun onMoodClicked(id: Long) {
        _navigateToMoodDetails.value = id
    }

    fun onMoodDetailsNavigated(){
        _navigateToMoodDetails.value = null
    }
}