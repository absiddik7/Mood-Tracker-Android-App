package com.app.mymoodtracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Moods::class], version = 2, exportSchema = false)
abstract class MoodDatabase: RoomDatabase(){

    abstract val moodDatabaseDao: MoodDatabaseDao


    companion object {
        @Volatile
        private var INSTANCE: MoodDatabase? = null

        fun getInstance(context: Context): MoodDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MoodDatabase::class.java,
                        "mood_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}