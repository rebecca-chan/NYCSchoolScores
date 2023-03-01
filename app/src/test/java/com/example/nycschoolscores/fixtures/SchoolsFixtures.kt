package com.example.nycschoolscores.fixtures

import com.example.nycschoolscores.data.School

class SchoolsFixtures {

    companion object {
        val SCHOOL_1 = School(
            id = "1",
            name = "High School 1",
            neighborhood = "Chelsea"
        )
        val SCHOOL_2 = School(
            id= "2",
            name = "High School 2",
            neighborhood = "Two Bridges"
        )

        val schools = listOf(SCHOOL_1, SCHOOL_2)
    }
}