package com.example.data

enum class MuscleGroup(val displayName: String) {
    ALL("All Muscles"),
    CHEST("Chest"),
    BACK("Back"),
    SHOULDERS("Shoulders"),
    BICEPS("Biceps"),
    TRICEPS("Triceps"),
    LEGS("Legs"),
    ABS("Abs & Core"),
    FULL_BODY("Full Body")
}

enum class NutritionCategory(val displayName: String) {
    ALL("All Foods"),
    HIGH_PROTEIN("High Protein"),
    COMPLEX_CARBS("Complex Carbs"),
    HEALTHY_FATS("Healthy Fats"),
    VEGETARIAN("Vegetarian")
}

enum class GalleryCategory(val displayName: String) {
    ALL("All Photos"),
    GYM_PHOTO("Gym Photography"),
    PHYSIQUE("Physique"),
    TRAINING("Training Sessions"),
    LIFESTYLE("Lifestyle"),
    TRANSFORMATION("Transformations")
}

data class AthleteStat(
    val label: String,
    val value: String,
    val unit: String = ""
)

data class AthleteProfile(
    val brandName: String = "APEX PHYSIQUE",
    val athleteName: String = "Alex Vance",
    val tagline: String = "BUILD YOUR BODY. MASTER YOUR MIND.",
    val secondaryTagline: String = "DISCIPLINE. CONSISTENCY. TRANSFORMATION.",
    val instagramHandle: String = "@alexvance.fit",
    val instagramUrl: String = "https://instagram.com",
    val youtubeHandle: String = "@ApexPhysiqueOfficial",
    val whatsappNumber: String = "+1 (555) 839-4400",
    val email: String = "coaching@apexphysique.fit",
    val trainingExperience: String = "6+ Years",
    val primaryGoal: String = "Muscle Hypertrophy & Low Body Fat",
    val preferredStyle: String = "Push-Pull-Legs & Progressive Overload",
    val favoriteMuscles: String = "Upper Chest, Delts & Lats",
    val height: String = "6'1\" (185 cm)",
    val weight: String = "194 lbs (88 kg)",
    val bodyFat: String = "9.5% Contest / 12% Off-season",
    val bio: String = "Fitness is a lifelong commitment to continuous improvement. Through consistency, structured progressive training, disciplined nutrition, and relentless recovery, I strive every day to build not just a world-class physique, but an unbreakable mindset.",
    val philosophy: String = "Every rep has a purpose. Every setback is a lesson. Every single day is another opportunity to prove your dedication.",
    val stats: List<AthleteStat> = listOf(
        AthleteStat("Years of Training", "6+", "Years"),
        AthleteStat("Workouts Completed", "1,240+", "Sessions"),
        AthleteStat("Bench Press PR", "335", "lbs (152 kg)"),
        AthleteStat("Squat PR", "445", "lbs (202 kg)"),
        AthleteStat("Deadlift PR", "530", "lbs (240 kg)"),
        AthleteStat("Athletes Coached", "45+", "Clients")
    )
)

data class TransformationRecord(
    val id: String,
    val title: String,
    val timeSpan: String,
    val startWeight: String,
    val currentWeight: String,
    val bodyFatChange: String,
    val beforeDate: String,
    val afterDate: String,
    val highlights: List<String>,
    val description: String
)

data class WorkoutItem(
    val id: String,
    val name: String,
    val category: MuscleGroup,
    val difficulty: String,
    val targetMuscle: String,
    val sets: Int,
    val reps: String,
    val restSeconds: Int,
    val equipment: String,
    val instructions: List<String>,
    val commonMistakes: List<String>,
    val proTip: String
)

data class WeeklySplitDay(
    val day: String,
    val title: String,
    val focus: String,
    val exercises: List<String>
)

data class FoodItem(
    val id: String,
    val name: String,
    val category: NutritionCategory,
    val servingSize: String,
    val calories: Int,
    val proteinGrams: Float,
    val carbsGrams: Float,
    val fatsGrams: Float,
    val benefits: String
)

data class DailyMeal(
    val mealNumber: String,
    val title: String,
    val time: String,
    val items: List<String>,
    val totalCalories: Int,
    val proteinGrams: Int,
    val carbsGrams: Int,
    val fatsGrams: Int,
    val notes: String
)

data class GalleryPhoto(
    val id: String,
    val title: String,
    val category: GalleryCategory,
    val subtitle: String,
    val date: String,
    val description: String,
    val cameraAngle: String,
    val visualType: Int // 0 to 5 identifier for custom stylized procedural artwork
)

data class CoachingService(
    val id: String,
    val title: String,
    val price: String,
    val period: String,
    val isFeatured: Boolean,
    val description: String,
    val features: List<String>
)
