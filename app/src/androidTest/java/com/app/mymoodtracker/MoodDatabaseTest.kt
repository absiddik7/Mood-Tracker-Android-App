package com.app.mymoodtracker

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.mymoodtracker.database.MoodDatabase
import com.app.mymoodtracker.database.MoodDatabaseDao
import com.app.mymoodtracker.database.Moods
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MoodDatabaseTest {

    private lateinit var moodDao: MoodDatabaseDao
    private lateinit var db: MoodDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, MoodDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        moodDao = db.moodDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetMood() {
        val night = Moods()
        moodDao.insert(night)
        val tonight = moodDao.getCurrentMood()
        assertEquals(tonight?.moodQuality,-1)
    }
}