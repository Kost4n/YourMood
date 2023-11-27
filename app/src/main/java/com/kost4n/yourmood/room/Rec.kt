package com.kost4n.yourmood.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "recs")
data class Rec(
    @ColumnInfo(name = "mood") val mood: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "reason") var reason: String = ""
    ) {
    @PrimaryKey(autoGenerate = true) var id: Long = 0L
}
