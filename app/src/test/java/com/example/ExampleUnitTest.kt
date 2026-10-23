package com.example

import com.example.data.FitnessSampleData
import com.example.data.MuscleGroup
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun testBmiCalculation() {
        val heightMeters = 1.80
        val weightKg = 82.0
        val bmi = weightKg / (heightMeters * heightMeters)
        assertEquals(25.31, bmi, 0.05)
    }

    @Test
    fun testOneRepMaxBrzyckiFormula() {
        val weight = 225.0
        val reps = 5.0
        val oneRm = weight * (36.0 / (37.0 - reps))
        // 225 * (36 / 32) = 225 * 1.125 = 253.125
        assertEquals(253.125, oneRm, 0.01)
    }

    @Test
    fun testFitnessSampleDataIntegrity() {
        assertTrue("Exercises library must not be empty", FitnessSampleData.exercises.isNotEmpty())
        assertTrue("7-Day split must have 7 days", FitnessSampleData.weeklySplit.size == 7)
        assertTrue("Foods list must not be empty", FitnessSampleData.foods.isNotEmpty())
        assertTrue("Coaching services must not be empty", FitnessSampleData.coachingServices.isNotEmpty())
        assertNotNull("Transformation record must be present", FitnessSampleData.transformation)
    }
}
