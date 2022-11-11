package com.app.mymoodtracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MoodDatabaseDao {

    @Insert
    fun insert(mood: Moods)

    @Update
    fun update(mood: Moods)

    @Query("SELECT * from daily_mood_quality_table WHERE moodId = :key")
    fun getId(key: Long):Moods

    @Query("DELETE FROM daily_mood_quality_table")
    fun clear()

    @Query("SELECT * FROM daily_mood_quality_table ORDER BY moodId DESC")
    fun getAllMoods(): LiveData<List<Moods>>

    @Query("SELECT * FROM daily_mood_quality_table ORDER BY moodId DESC LIMIT 1")
    fun getMoodContent():Moods?

    @Query("SELECT * FROM daily_mood_quality_table ORDER BY moodId DESC LIMIt 1")
    fun getCurrentMood(): Moods?

    @Query("SELECT * From daily_mood_quality_table WHERE moodId = :key")
    fun getMoodWithId(key:Long): LiveData<Moods>

}