package com.example.nycschoolscores.data

import com.google.gson.annotations.SerializedName

/**
 * Model for School's SAT average scores, serves as both network response model and domain model
 */
data class SATScores(
    @SerializedName("dbn") val id: String,
    @SerializedName("school_name") val name: String,
    @SerializedName("sat_critical_reading_avg_score") val reading: String,
    @SerializedName("sat_writing_avg_score") val writing: String,
    @SerializedName("sat_math_avg_score") val math: String
)
