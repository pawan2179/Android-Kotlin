package com.example.acedev.tasktracker.Entity
import androidx.room.*

@Entity(tableName = "TasksDatabase")
data class TasksEntity(

    @ColumnInfo(name = "title")
    val title : String,
    @ColumnInfo(name = "remarks")
    val remarks : String,
    @ColumnInfo(name = "priority")
    val priority : Int,
    @ColumnInfo(name = "duration")
    var duration : Long,
    @ColumnInfo(name = "finished")
    var finished : Boolean,
    @ColumnInfo(name = "isActive")
    val isActive : Boolean = false,
    @ColumnInfo(name = "elapsedTime")
    var elapsedTime : Long = 0,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int = -1
)