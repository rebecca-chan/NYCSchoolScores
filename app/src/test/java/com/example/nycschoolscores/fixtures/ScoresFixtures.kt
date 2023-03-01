package com.example.nycschoolscores.fixtures

import com.example.nycschoolscores.data.SATScores

class ScoresFixtures {

   companion object {
       val SCORES_1 = SATScores(
           id = "1",
           name = "High School 1",
           reading = "100",
           writing = "100",
           math = "0"
       )
       val SCORES_2 = SATScores(
           id ="2",
           name = "High School 2",
           reading = "3000",
           writing = "3",
           math = "2"
       )
       val listOfOneScore = listOf(SCORES_1)
       val listOfManyScores = listOf(SCORES_1, SCORES_2)
   }
}