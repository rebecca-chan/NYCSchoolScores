package com.example.nycschoolscores.data

import com.google.gson.annotations.SerializedName

/**
 * Model for School average scores, both as network response model and domain model
 */
data class School(
    @SerializedName("dbn") val id: String,
    @SerializedName("school_name") val name: String,
    @SerializedName("neighborhood") val neighborhood: String
)
