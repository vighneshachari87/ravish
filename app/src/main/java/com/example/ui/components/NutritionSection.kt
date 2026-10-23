package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.DailyMeal
import com.example.data.FitnessSampleData
import com.example.data.FoodItem
import com.example.data.NutritionCategory
import com.example.ui.theme.AthleticRed
import com.example.ui.theme.AthleticRedDark
import com.example.ui.theme.AthleticRedLight
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.GoldMilestone
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceCardElevated
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun NutritionSection(
    modifier: Modifier = Modifier,
    foods: List<FoodItem> = FitnessSampleData.foods,
    sampleMeals: List<DailyMeal> = FitnessSampleData.sampleDailyMeals
) {
    var selectedTab by remember { mutableIntStateOf(0) } // 0: Whole Food Sources, 1: Sample Meal Plan, 2: Principles
    var selectedCategory by remember { mutableStateOf(NutritionCategory.ALL) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        // Section Header Tag
        Surface(
            color = AthleticRed.copy(alpha = 0.15f),
            shape = RoundedCornerShape(6.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, AthleticRed.copy(alpha = 0.5f))
        ) {
            Text(
                text = "NUTRITION & MACRONUTRIENTS",
                color = AthleticRedLight,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "FUEL YOUR PROGRESS.",
            color = TextPrimary,
            fontSize = 26.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = (-0.5).sp
        )

        Text(
            text = "Clean whole-food fuel calibrated for muscle hypertrophy, athletic recovery, and body composition.",
            color = TextSecondary,
            fontSize = 13.sp,
            modifier = Modifier.padding(top = 4.dp, bottom = 18.dp)
        )

        // View Mode Switcher
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = SurfaceCardElevated,
            contentColor = AthleticRed,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = AthleticRed,
                    height = 3.dp
                )
            },
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .border(1.dp, SurfaceBorder, RoundedCornerShape(12.dp))
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = {
                    Text(
                        "Food Sources",
                        fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal,
                        color = if (selectedTab == 0) Color.White else TextSecondary
                    )
                }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = {
                    Text(
                        "Sample Meal Plan",
                        fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal,
                        color = if (selectedTab == 1) Color.White else TextSecondary
                    )
                }
            )
            Tab(
                selected = selectedTab == 2,
                onClick = { selectedTab = 2 },
                text = {
                    Text(
                        "Principles",
                        fontWeight = if (selectedTab == 2) FontWeight.Bold else FontWeight.Normal,
                        color = if (selectedTab == 2) Color.White else TextSecondary
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (selectedTab) {
            0 -> {
                // Category Filter Chips
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    NutritionCategory.values().forEach { cat ->
                        FilterChip(
                            selected = selectedCategory == cat,
                            onClick = { selectedCategory = cat },
                            label = { Text(cat.displayName, fontSize = 12.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = AthleticRed,
                                selectedLabelColor = Color.White,
                                containerColor = SurfaceCard,
                                labelColor = TextSecondary
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = selectedCategory == cat,
                                borderColor = SurfaceBorder,
                                selectedBorderColor = AthleticRed
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                val filteredFoods = foods.filter { food ->
                    selectedCategory == NutritionCategory.ALL || food.category == selectedCategory
                }

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    filteredFoods.forEach { food ->
                        FoodItemCard(food = food)
                    }
                }
            }
            1 -> {
                // Sample Daily Meal Plan
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    sampleMeals.forEach { meal ->
                        DailyMealCard(meal = meal)
                    }
                }
            }
            2 -> {
                // Nutrition Principles & Recovery
                NutritionPrinciplesCards()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mandatory Medical & Individual Variation Disclaimer
        Surface(
            color = Color(0xFF1F1414),
            shape = RoundedCornerShape(12.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF4A2020)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.Top) {
                Icon(Icons.Default.Info, contentDescription = null, tint = AthleticRedLight, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Important Note: Sample meal plans and macronutrient breakdowns are provided for illustrative educational reference only. Nutritional requirements, caloric needs, and food tolerances vary significantly across individuals based on metabolic health, training volume, and medical history. Always consult a registered dietitian or physician before undertaking extreme dietary interventions.",
                    color = Color(0xFFFFCCCC),
                    fontSize = 11.sp,
                    lineHeight = 16.sp
                )
            }
        }
    }
}

@Composable
fun FoodItemCard(food: FoodItem) {
    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        shape = RoundedCornerShape(14.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = food.name,
                    color = TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Surface(
                    color = SurfaceCardElevated,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "${food.calories} kcal",
                        color = AthleticRedLight,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Text(
                text = "Serving: ${food.servingSize} • ${food.category.displayName}",
                color = TextSecondary,
                fontSize = 11.sp,
                modifier = Modifier.padding(top = 2.dp, bottom = 10.dp)
            )

            // Macronutrient horizontal breakdown
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceCardElevated, RoundedCornerShape(10.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("PROTEIN", color = TextMuted, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    Text("${food.proteinGrams}g", color = AthleticRedLight, fontSize = 14.sp, fontWeight = FontWeight.Black)
                }
                Column {
                    Text("CARBS", color = TextMuted, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    Text("${food.carbsGrams}g", color = GoldMilestone, fontSize = 14.sp, fontWeight = FontWeight.Black)
                }
                Column {
                    Text("FATS", color = TextMuted, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    Text("${food.fatsGrams}g", color = ElectricBlue, fontSize = 14.sp, fontWeight = FontWeight.Black)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = food.benefits,
                color = Color(0xFFC0C0C4),
                fontSize = 11.sp,
                lineHeight = 15.sp
            )
        }
    }
}

@Composable
fun DailyMealCard(meal: DailyMeal) {
    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        shape = RoundedCornerShape(14.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = AthleticRed.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "${meal.mealNumber.uppercase()} • ${meal.time}",
                        color = AthleticRedLight,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.8.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
                Text(
                    text = "${meal.totalCalories} kcal",
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = meal.title,
                color = TextPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            meal.items.forEach { item ->
                Row(modifier = Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(AthleticRed))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = item, color = Color(0xFFD6D6DA), fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Macro summary
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceCardElevated, RoundedCornerShape(8.dp))
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("P: ${meal.proteinGrams}g", color = AthleticRedLight, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                Text("C: ${meal.carbsGrams}g", color = GoldMilestone, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                Text("F: ${meal.fatsGrams}g", color = ElectricBlue, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text(meal.notes, color = TextMuted, fontSize = 11.sp, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
        }
    }
}

@Composable
fun NutritionPrinciplesCards() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Card(
            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
            shape = RoundedCornerShape(14.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder)
        ) {
            Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.Top) {
                Icon(Icons.Default.Restaurant, contentDescription = null, tint = AthleticRed, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text("Muscle Protein Synthesis & Leucine", color = TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Text(
                        "Aim for 1.8 to 2.2 grams of protein per kilogram of bodyweight distributed across 4 to 5 meals. Each meal should contain at least 2.5 to 3 grams of leucine to trigger the mTOR pathway.",
                        color = TextSecondary,
                        fontSize = 12.sp,
                        lineHeight = 17.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }

        Card(
            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
            shape = RoundedCornerShape(14.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder)
        ) {
            Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.Top) {
                Icon(Icons.Default.LocalDrink, contentDescription = null, tint = ElectricBlue, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text("Intracellular Hydration & Electrolytes", color = TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Text(
                        "Drink 3.5 to 5 liters of water daily. Skeletal muscle is ~75% water. Adequate sodium, potassium, and magnesium ensure muscular fullness, neural drive, and intracellular osmotic pressure.",
                        color = TextSecondary,
                        fontSize = 12.sp,
                        lineHeight = 17.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }

        Card(
            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
            shape = RoundedCornerShape(14.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder)
        ) {
            Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.Top) {
                Icon(Icons.Default.Bedtime, contentDescription = null, tint = GoldMilestone, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text("Deep Sleep & Anabolic Recovery", color = TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Text(
                        "Training stimulates growth, but recovery produces the muscle tissue. Over 70% of natural human growth hormone (HGH) is released during slow-wave non-REM deep sleep. Target 8+ hours nightly.",
                        color = TextSecondary,
                        fontSize = 12.sp,
                        lineHeight = 17.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}
