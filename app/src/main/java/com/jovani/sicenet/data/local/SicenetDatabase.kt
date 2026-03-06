package com.jovani.sicenet.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jovani.sicenet.data.local.dao.SicenetDao
import com.jovani.sicenet.data.local.entities.PerfilEntity

@Database(entities = [PerfilEntity::class], version = 1)
abstract class SicenetDatabase : RoomDatabase() {
    abstract fun sicenetDao(): SicenetDao
}