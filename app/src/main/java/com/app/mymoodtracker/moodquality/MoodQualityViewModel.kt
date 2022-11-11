package com.app.mymoodtracker.moodquality

import android.R
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.app.mymoodtracker.database.MoodDatabaseDao
import com.app.mymoodtracker.database.Moods
import kotlinx.coroutines.*


class MoodQualityViewModel(

    val database: MoodDatabaseDao
) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val currentMood = MutableLiveData<Moods?>()
    private var buttonbol = false
    private var qualityInt: Int = -1
    private var moodContents = ""

    // This observer will invoke onContentChanged() when the user updates the content
    private val contentObserver = Observer<String> { onContentChanged(it) }
    val moodContent = MutableLiveData<String>()


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val _navigationToMoodTracker = MutableLiveData<Boolean?>()
    val navigationToMoodTracker: LiveData<Boolean?>
        get() = _navigationToMoodTracker

    fun doneNavigationToMoodTracker() {
        _navigationToMoodTracker.value = null
    }

    private val _showToast = MutableLiveData<Boolean?>()
    val showToast: LiveData<Boolean?>
        get() = _showToast

    fun doneShowToast() {
        _showToast.value = false
    }

    private val _showToast2 = MutableLiveData<Boolean?>()
    val showToast2: LiveData<Boolean?>
        get() = _showToast2

    fun doneShowToast2() {
        _showToast2.value = false
    }

    init {
        initialize()
        moodContent.observeForever(contentObserver)
    }

    private fun initialize() {
        uiScope.launch {
            currentMood.value = getCurrentMoodFromDatabase()
        }
    }

    private suspend fun getCurrentMoodFromDatabase(): Moods? {
        return withContext(Dispatchers.IO) {
            val mood = database.getCurrentMood()
            mood
        }
    }

    private fun setData() {
        if (qualityInt > -2 && buttonbol) {
            uiScope.launch {
                val newMood = Moods()
                insert(newMood)
                currentMood.value = getCurrentMoodFromDatabase()
                onUpdateMood(qualityInt, moodContents)
            }
        }
    }


    fun onSetMoodQuality(quality: Int): Int {
        qualityInt = when {
            quality > -1 -> quality
            else -> -2
        }


        return qualityInt
    }

    private suspend fun insert(mood: Moods) {
        withContext(Dispatchers.IO) {
            database.insert(mood)
        }
    }

    private fun onUpdateMood(quality: Int, newContent: String) {
        uiScope.launch {
            val currentMood = currentMood.value ?: return@launch
            currentMood.apply {
                currentTimeMilli
                moodQuality = quality
                moodContent = newContent
            }
            update(currentMood)
        }

        _navigationToMoodTracker.value = true


    }

    private suspend fun update(mood: Moods) {
        withContext(Dispatchers.IO) {
            database.update(mood)
        }
    }

    private fun onContentChanged(newContent: String): String {
        moodContents = newContent
        return moodContents
    }

    fun onClickDoneButton(): Boolean {

        when {
            qualityInt < 0 && moodContents == "" -> _showToast.value = true
            qualityInt < 0 -> _showToast.value = true
            moodContents == "" -> _showToast2.value = true
            else -> buttonbol = true
        }

        setData()
        return buttonbol
    }
}