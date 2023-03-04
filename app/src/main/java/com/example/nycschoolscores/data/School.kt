package com.example.nycschoolscores.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Model for School average scores, both as network response model and domain model
 */
@Entity(tableName = "schools")
data class School(
    @PrimaryKey @SerializedName("dbn") val id: String,
    @ColumnInfo(name = "school_name") @SerializedName("school_name") val name: String,
    @SerializedName("neighborhood") val neighborhood: String
)
