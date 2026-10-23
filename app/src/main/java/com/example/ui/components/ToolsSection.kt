package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AthleticRed
import com.example.ui.theme.AthleticRedLight
import com.example.ui.theme.GoldMilestone
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceCardElevated
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import java.util.Locale

@Composable
fun ToolsSection(modifier: Modifier = Modifier) {
    var selectedToolIndex by remember { mutableIntStateOf(0) }
    val toolTabs = listOf("BMI Calculator", "Calorie Estimator", "1RM Estimator")

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        // Section Header
        Surface(
            color = AthleticRed.copy(alpha = 0.15f),
            shape = RoundedCornerShape(6.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, AthleticRed.copy(alpha = 0.5f))
        ) {
            Text(
                text = "CALCULATORS & SCIENCE",
                color = AthleticRedLight,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "ATHLETE TOOLS & PERFORMANCE",
            color = TextPrimary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = (-0.5).sp
        )

        Text(
            text = "Accurate, science-based athletic calculators for body composition, energetic demands, and strength benchmarks.",
            color = TextSecondary,
            fontSize = 13.sp,
            modifier = Modifier.padding(top = 4.dp, bottom = 18.dp)
        )

        // Subtabs
        TabRow(
            selectedTabIndex = selectedToolIndex,
            containerColor = SurfaceCardElevated,
            contentColor = AthleticRed,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedToolIndex]),
                    color = AthleticRed,
                    height = 3.dp
                )
            },
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .border(1.dp, SurfaceBorder, RoundedCornerShape(12.dp))
        ) {
            toolTabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedToolIndex == index,
                    onClick = { selectedToolIndex = index },
                    text = {
                        Text(
                            text = title,
                            fontSize = 12.sp,
                            fontWeight = if (selectedToolIndex == index) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedToolIndex == index) Color.White else TextSecondary
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (selectedToolIndex) {
            0 -> BmiCalculatorCard()
            1 -> CalorieEstimatorCard()
            2 -> OneRepMaxCalculatorCard()
        }
    }
}

@Composable
fun BmiCalculatorCard() {
    var heightCmStr by remember { mutableStateOf("180") }
    var weightKgStr by remember { mutableStateOf("82") }
    var calculatedBmi by remember { mutableStateOf<Double?>(null) }

    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Calculate, contentDescription = null, tint = AthleticRed)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Body Mass Index (BMI)", color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(14.dp))

            val tfColors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AthleticRed,
                unfocusedBorderColor = SurfaceBorder,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary,
                cursorColor = AthleticRed
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = heightCmStr,
                    onValueChange = { heightCmStr = it },
                    label = { Text("Height (cm)", color = TextSecondary) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = tfColors,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("bmi_height_input")
                )
                OutlinedTextField(
                    value = weightKgStr,
                    onValueChange = { weightKgStr = it },
                    label = { Text("Weight (kg)", color = TextSecondary) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = tfColors,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("bmi_weight_input")
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = {
                    val h = heightCmStr.toDoubleOrNull()
                    val w = weightKgStr.toDoubleOrNull()
                    if (h != null && w != null && h > 0) {
                        val m = h / 100.0
                        calculatedBmi = w / (m * m)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = AthleticRed),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("calculate_bmi_button")
            ) {
                Text("Calculate BMI", fontWeight = FontWeight.Bold, color = Color.White)
            }

            calculatedBmi?.let { bmi ->
                Spacer(modifier = Modifier.height(16.dp))
                Surface(
                    color = SurfaceCardElevated,
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = String.format(Locale.US, "Calculated BMI: %.1f", bmi),
                            color = AthleticRedLight,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Black
                        )
                        val status = when {
                            bmi < 18.5 -> "Underweight"
                            bmi < 25.0 -> "Normal Weight"
                            bmi < 30.0 -> "Overweight"
                            else -> "Class 1+ Obese"
                        }
                        Text(
                            text = "Standard Classification: $status",
                            color = TextPrimary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier
                                .background(Color(0xFF26180C), RoundedCornerShape(8.dp))
                                .padding(10.dp)
                        ) {
                            Icon(Icons.Default.Info, contentDescription = null, tint = GoldMilestone, modifier = Modifier.padding(end = 6.dp))
                            Text(
                                text = "Important Athlete Limitation: BMI does not differentiate between skeletal muscle and adipose fat mass. Bodybuilders and strength athletes often index as 'Overweight' or 'Obese' despite possessing single-digit body fat and exceptional cardiovascular conditioning.",
                                color = Color(0xFFFFD580),
                                fontSize = 11.sp,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CalorieEstimatorCard() {
    var ageStr by remember { mutableStateOf("26") }
    var heightCmStr by remember { mutableStateOf("182") }
    var weightKgStr by remember { mutableStateOf("86") }
    var activityLevel by remember { mutableIntStateOf(2) } // 0: sedentary, 1: light, 2: moderate, 3: heavy, 4: athlete
    var goalIndex by remember { mutableIntStateOf(0) } // 0: Lean Bulk, 1: Maintenance, 2: Fat Loss Cut

    val activities = listOf(
        "Light (1-2x/wk)" to 1.375,
        "Moderate (3-5x/wk)" to 1.55,
        "Heavy (6-7x/wk)" to 1.725,
        "Elite Athlete (2x/day)" to 1.9
    )
    val goals = listOf("Lean Bulk (+300)", "Maintenance (0)", "Fat Loss Cut (-400)")

    var resultCalories by remember { mutableStateOf<Int?>(null) }

    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocalFireDepartment, contentDescription = null, tint = AthleticRed)
                Spacer(modifier = Modifier.width(8.dp))
                Text("TDEE & Caloric Demand Estimator", color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(14.dp))

            val tfColors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AthleticRed,
                unfocusedBorderColor = SurfaceBorder,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary,
                cursorColor = AthleticRed
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = ageStr,
                    onValueChange = { ageStr = it },
                    label = { Text("Age", color = TextSecondary, fontSize = 12.sp) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = tfColors,
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = heightCmStr,
                    onValueChange = { heightCmStr = it },
                    label = { Text("Height (cm)", color = TextSecondary, fontSize = 12.sp) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = tfColors,
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = weightKgStr,
                    onValueChange = { weightKgStr = it },
                    label = { Text("Weight (kg)", color = TextSecondary, fontSize = 12.sp) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = tfColors,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text("Activity Level:", color = TextSecondary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(6.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                activities.forEachIndexed { index, (label, _) ->
                    Surface(
                        color = if (activityLevel == index) AthleticRed else SurfaceCardElevated,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .weight(1f)
                            .clickable { activityLevel = index }
                    ) {
                        Text(
                            text = label,
                            color = if (activityLevel == index) Color.White else TextSecondary,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
                            maxLines = 2
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text("Primary Goal:", color = TextSecondary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(6.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                goals.forEachIndexed { index, label ->
                    Surface(
                        color = if (goalIndex == index) AthleticRed else SurfaceCardElevated,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .weight(1f)
                            .clickable { goalIndex = index }
                    ) {
                        Text(
                            text = label,
                            color = if (goalIndex == index) Color.White else TextSecondary,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 8.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = {
                    val age = ageStr.toDoubleOrNull() ?: 25.0
                    val h = heightCmStr.toDoubleOrNull() ?: 180.0
                    val w = weightKgStr.toDoubleOrNull() ?: 80.0
                    // Mifflin-St Jeor: BMR = 10*w + 6.25*h - 5*age + 5
                    val bmr = 10 * w + 6.25 * h - 5 * age + 5
                    val multiplier = activities[activityLevel].second
                    val tdee = bmr * multiplier
                    val finalCals = when (goalIndex) {
                        0 -> tdee + 300 // bulk
                        1 -> tdee // maintenance
                        else -> tdee - 400 // cut
                    }
                    resultCalories = finalCals.toInt()
                },
                colors = ButtonDefaults.buttonColors(containerColor = AthleticRed),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("calculate_calories_button")
            ) {
                Text("Estimate Daily Target Calories", fontWeight = FontWeight.Bold, color = Color.White)
            }

            resultCalories?.let { cals ->
                Spacer(modifier = Modifier.height(14.dp))
                Surface(
                    color = SurfaceCardElevated,
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "$cals kcal / day",
                            color = AthleticRedLight,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            text = "Estimated Daily Intake Target (${goals[goalIndex]})",
                            color = TextPrimary,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        val w = weightKgStr.toFloatOrNull() ?: 80f
                        val proteinG = (w * 2.2f).toInt()
                        val fatG = (w * 0.9f).toInt()
                        val carbCals = cals - (proteinG * 4 + fatG * 9)
                        val carbG = (carbCals / 4).coerceAtLeast(50)

                        Text(
                            text = "Macro Target Split: ${proteinG}g Protein (4kcal/g) • ${carbG}g Carbs (4kcal/g) • ${fatG}g Healthy Fats (9kcal/g)",
                            color = TextSecondary,
                            fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Disclaimer: Calculations are physiological approximations. Actual metabolic adaptation requires weekly scale monitoring and visual check-ins.",
                            color = TextMuted,
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun OneRepMaxCalculatorCard() {
    var weightLiftedStr by remember { mutableStateOf("225") }
    var repsPerformedStr by remember { mutableStateOf("5") }
    var estimated1RM by remember { mutableStateOf<Double?>(null) }

    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.FitnessCenter, contentDescription = null, tint = AthleticRed)
                Spacer(modifier = Modifier.width(8.dp))
                Text("One-Rep Max (1RM) Estimator", color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(14.dp))

            val tfColors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AthleticRed,
                unfocusedBorderColor = SurfaceBorder,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary,
                cursorColor = AthleticRed
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = weightLiftedStr,
                    onValueChange = { weightLiftedStr = it },
                    label = { Text("Weight Lifted (lbs/kg)", color = TextSecondary) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = tfColors,
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = repsPerformedStr,
                    onValueChange = { repsPerformedStr = it },
                    label = { Text("Reps (1-10)", color = TextSecondary) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = tfColors,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = {
                    val w = weightLiftedStr.toDoubleOrNull()
                    val r = repsPerformedStr.toDoubleOrNull()
                    if (w != null && r != null && r in 1.0..15.0) {
                        // Brzycki formula
                        estimated1RM = w * (36.0 / (37.0 - r))
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = AthleticRed),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("calculate_1rm_button")
            ) {
                Text("Calculate Estimated 1RM", fontWeight = FontWeight.Bold, color = Color.White)
            }

            estimated1RM?.let { oneRm ->
                Spacer(modifier = Modifier.height(14.dp))
                Surface(
                    color = SurfaceCardElevated,
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = String.format(Locale.US, "Estimated 1RM: %.1f", oneRm),
                            color = AthleticRedLight,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Training Intensity Percentages:",
                            color = TextPrimary,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(String.format(Locale.US, "90%%: %.1f", oneRm * 0.90), color = TextSecondary, fontSize = 11.sp)
                            Text(String.format(Locale.US, "80%%: %.1f", oneRm * 0.80), color = TextSecondary, fontSize = 11.sp)
                            Text(String.format(Locale.US, "70%%: %.1f", oneRm * 0.70), color = TextSecondary, fontSize = 11.sp)
                            Text(String.format(Locale.US, "60%%: %.1f", oneRm * 0.60), color = TextSecondary, fontSize = 11.sp)
                        }
                    }
                }
            }
        }
    }
}
