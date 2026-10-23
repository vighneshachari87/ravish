package com.example.data

object FitnessSampleData {

    val defaultProfile = AthleteProfile()

    val transformation = TransformationRecord(
        id = "trans_1",
        title = "3-Year Natural Bodybuilding Journey",
        timeSpan = "36 Months",
        startWeight = "148 lbs (67 kg)",
        currentWeight = "194 lbs (88 kg)",
        bodyFatChange = "17% → 9.8%",
        beforeDate = "March 2023",
        afterDate = "October 2026",
        highlights = listOf(
            "+46 lbs total lean mass gained naturally",
            "Bench Press increased from 135 lbs to 335 lbs",
            "Deadlift increased from 185 lbs to 530 lbs",
            "Dramatic V-taper lat width & 3D shoulder cap development",
            "Sub-10% body fat maintained through calculated refeeds"
        ),
        description = "A testament to relentless consistency. No skipped workouts, tracking every single gram of macronutrients, progressive overload tracked weekly in the training log, and prioritizing 8 hours of sleep nightly."
    )

    val weeklySplit: List<WeeklySplitDay> = listOf(
        WeeklySplitDay("Monday", "Chest & Triceps", "Pectoral Upper/Lower fibers & Triceps Long Head", listOf("Incline Barbell Bench (4x8)", "Flat Dumbbell Press (3x10)", "Chest Dips Weighted (3x12)", "Overhead Cable Extension (4x12)", "Rope Pushdowns (3x15)")),
        WeeklySplitDay("Tuesday", "Back & Biceps", "Latissimus Dorsi width, Traps & Biceps Peak", listOf("Deadlifts Conventional (4x5)", "Weighted Neutral Grip Pull-Ups (4x8)", "Bent-Over Barbell Rows (4x10)", "Chest-Supported T-Bar Row (3x12)", "Incline Dumbbell Bicep Curls (4x10)")),
        WeeklySplitDay("Wednesday", "Legs & Abs", "Quadriceps, Hamstrings, Calves & Core Stability", listOf("Barbell Back Squats (5x6)", "Romanian Deadlifts (4x8)", "Leg Press Heavy (3x12)", "Seated Hamstring Curls (4x15)", "Hanging Leg Raises (4x15)")),
        WeeklySplitDay("Thursday", "Shoulders & Upper Body", "Anterior/Lateral/Posterior Delts & Upper Traps", listOf("Standing Barbell Overhead Press (4x8)", "Dumbbell Lateral Raises (5x15)", "Face Pulls on Cable (4x15)", "Incline Dumbbell Flyes (3x12)", "Close Grip Bench Press (3x10)")),
        WeeklySplitDay("Friday", "Chest & Back Antagonist", "Chest & Back High Volume Super-sets", listOf("Incline Dumbbell Press (4x10)", "Wide-Grip Lat Pulldowns (4x10)", "Cable Crossover Flyes (3x15)", "Single-Arm Dumbbell Rows (3x12)", "Cable Lat Pushdowns (3x15)")),
        WeeklySplitDay("Saturday", "Arms & Weak Points", "Biceps, Triceps, Calves & Rear Delts Detailing", listOf("Spider Curls with EZ Bar (4x10)", "Skull Crushers (4x10)", "Hammer Curls Heavy (4x10)", "Tricep Dips to Failure (3 sets)", "Standing Calf Raises (5x20)")),
        WeeklySplitDay("Sunday", "Active Rest & Recovery", "Mobility, Hydration, Foam Rolling & Mental Reset", listOf("20-min Foam Rolling & Hip Mobility", "45-min Outdoor Low-Intensity Walk", "Deliberate Contrast Showers / Sauna", "Full Macro Meal Prep for the Week", "8-9 Hours Quality Deep Sleep"))
    )

    val exercises: List<WorkoutItem> = listOf(
        WorkoutItem(
            id = "ex_1",
            name = "Incline Dumbbell Bench Press",
            category = MuscleGroup.CHEST,
            difficulty = "Intermediate",
            targetMuscle = "Clavicular Pectoralis (Upper Chest)",
            sets = 4,
            reps = "8 - 10",
            restSeconds = 90,
            equipment = "Adjustable Bench (30-45 deg), Dumbbells",
            instructions = listOf(
                "Set bench to approximately 30 degrees incline to maximize upper chest activation.",
                "Retract and depress scapulae, digging upper back firmly into the bench.",
                "Lower dumbbells with elbows tucked at roughly a 45-degree angle to the torso.",
                "Feel a deep stretch at bottom position without flaring elbows.",
                "Press upward along a slight arc, contracting the upper pecs forcefully at top."
            ),
            commonMistakes = listOf(
                "Setting the bench incline too high (>45 degrees) which shifts load onto front deltoids.",
                "Bouncing the dumbbells or failing to control the 3-second eccentric tempo.",
                "Flaring elbows wide (90 degrees) which places high stress on the rotator cuff."
            ),
            proTip = "Keep your chest high like a proud pigeon throughout the set to maintain scapular retraction."
        ),
        WorkoutItem(
            id = "ex_2",
            name = "Barbell Romanian Deadlift",
            category = MuscleGroup.LEGS,
            difficulty = "Intermediate",
            targetMuscle = "Hamstrings & Gluteus Maximus",
            sets = 4,
            reps = "8 - 10",
            restSeconds = 120,
            equipment = "Olympic Barbell, Weight Plates, Lifting Straps",
            instructions = listOf(
                "Stand hip-width apart holding barbell with an overhand grip just outside thighs.",
                "Maintain soft bend in knees and brace core with a deep diaphragmatic breath.",
                "Hinge at the hips by pushing your buttocks backward toward the wall behind you.",
                "Keep bar grazing shins and thighs as you descend until you feel full hamstring tension.",
                "Drive through the midfoot and squeeze glutes forward to lock out."
            ),
            commonMistakes = listOf(
                "Rounding the lower back instead of hinging strictly from the pelvis.",
                "Bending the knees excessively turning the movement into a squat.",
                "Allowing the barbell to drift away from the legs creating unwanted leverage."
            ),
            proTip = "Imagine closing a car door behind you using only your hips."
        ),
        WorkoutItem(
            id = "ex_3",
            name = "Weighted Neutral-Grip Pull-Ups",
            category = MuscleGroup.BACK,
            difficulty = "Advanced",
            targetMuscle = "Latissimus Dorsi & Teres Major",
            sets = 4,
            reps = "6 - 8",
            restSeconds = 120,
            equipment = "Pull-up Station, Dip Belt / Dumbbell",
            instructions = listOf(
                "Grip parallel neutral handles with palms facing each other.",
                "Hang in full dead hang to achieve complete lat stretch.",
                "Initiate pull by depressing shoulder blades downward.",
                "Drive elbows directly toward your hip pockets rather than pulling with biceps.",
                "Pull until upper chest touches or crosses handle height, pause for 1 second."
            ),
            commonMistakes = listOf(
                "Kicking legs or using kipping momentum.",
                "Cutting range of motion at bottom without full lat stretch.",
                "Rounding shoulders forward at top position."
            ),
            proTip = "Think about pulling the bar down to you rather than pulling yourself up."
        ),
        WorkoutItem(
            id = "ex_4",
            name = "Leaning Dumbbell Lateral Raises",
            category = MuscleGroup.SHOULDERS,
            difficulty = "Beginner to Intermediate",
            targetMuscle = "Lateral Deltoid (Shoulder Cap)",
            sets = 4,
            reps = "12 - 15",
            restSeconds = 60,
            equipment = "Dumbbell, Power Rack post to lean from",
            instructions = listOf(
                "Hold upright post with non-working arm, lean out slightly at 15-20 degrees.",
                "Hold dumbbell with a neutral to slight overhand grip in the scapular plane.",
                "Raise the dumbbell with elbows slightly bent, leading with the elbow.",
                "Raise until arm is parallel to the floor, feeling the side delt contract.",
                "Control descent slowly for a 2-second negative."
            ),
            commonMistakes = listOf(
                "Using torso swinging or shrugging upper traps to heave weight.",
                "Raising hands higher than elbows, shifting tension to anterior delts.",
                "Using too heavy weights sacrificing pure deltoid isolation."
            ),
            proTip = "Pour water from a pitcher metaphor: keep pinky slightly elevated or neutral."
        ),
        WorkoutItem(
            id = "ex_5",
            name = "Overhead Cable Triceps Extension",
            category = MuscleGroup.TRICEPS,
            difficulty = "Beginner",
            targetMuscle = "Long Head of Triceps",
            sets = 4,
            reps = "12 - 15",
            restSeconds = 60,
            equipment = "Cable Machine, Rope Attachment",
            instructions = listOf(
                "Set cable pulley to mid-chest or hip height with dual ropes.",
                "Turn away, take a staggered stance, and bring rope overhead with elbows high.",
                "Keep elbows stationary and pinned close to ears.",
                "Extend forearms forward and flare rope apart at peak contraction.",
                "Slowly return to full stretch behind your head."
            ),
            commonMistakes = listOf(
                "Allowing elbows to flare excessively wide or drop down during reps.",
                "Arching lower spine instead of maintaining braced core."
            ),
            proTip = "The long head of the triceps is only fully stretched when your shoulder is in flexion overhead."
        ),
        WorkoutItem(
            id = "ex_6",
            name = "Incline Dumbbell Bicep Curl",
            category = MuscleGroup.BICEPS,
            difficulty = "Intermediate",
            targetMuscle = "Biceps Brachii (Long Head & Peak)",
            sets = 3,
            reps = "10 - 12",
            restSeconds = 75,
            equipment = "Incline Bench (45-60 deg), Pair of Dumbbells",
            instructions = listOf(
                "Sit on an incline bench angled at 45 to 60 degrees.",
                "Let arms hang straight down behind your torso for maximum long head stretch.",
                "Curl dumbbells up while supinating wrists (turning palms up and slightly out).",
                "Contract biceps hard at top without swinging elbows forward.",
                "Lower with complete control to dead hang stretch."
            ),
            commonMistakes = listOf(
                "Lifting shoulders or swinging elbows forward at top to cheat the lift.",
                "Not achieving full extension at bottom of each rep."
            ),
            proTip = "Supinate hard at the top: drive your pinky finger toward your outer shoulder."
        ),
        WorkoutItem(
            id = "ex_7",
            name = "Hanging Garhammer / Leg Raises",
            category = MuscleGroup.ABS,
            difficulty = "Intermediate",
            targetMuscle = "Rectus Abdominis & Deep Core",
            sets = 4,
            reps = "12 - 15",
            restSeconds = 60,
            equipment = "Pull-up Bar or Captain's Chair",
            instructions = listOf(
                "Hang from bar with overhand grip and engage lats to prevent swinging.",
                "Posteriorly tilt pelvis to flatten lower spine curve.",
                "Curl pelvis upward toward your sternum, bringing knees or toes toward chest.",
                "Focus on spinal flexion (crunching abs) rather than just hip flexion.",
                "Lower slowly under control without swinging."
            ),
            commonMistakes = listOf(
                "Using momentum and swinging like a pendulum.",
                "Only lifting legs with hip flexors without curling pelvis."
            ),
            proTip = "Imagine bringing your belt buckle to your chest bone."
        ),
        WorkoutItem(
            id = "ex_8",
            name = "Barbell Back Squat",
            category = MuscleGroup.FULL_BODY,
            difficulty = "Advanced",
            targetMuscle = "Quadriceps, Glutes, Adductors & Spinal Erectors",
            sets = 5,
            reps = "5 - 8",
            restSeconds = 150,
            equipment = "Power Rack, Barbell, Weight Plates",
            instructions = listOf(
                "Step under bar, resting bar across mid-traps (high bar) or rear delts (low bar).",
                "Unrack with tight upper back, take 2 deliberate steps back.",
                "Breathe deep into belly and create 360-degree intra-abdominal pressure.",
                "Break at knees and hips simultaneously, driving knees out in line with toes.",
                "Descend to parallel or below, drive up through midfoot maintaining upright chest."
            ),
            commonMistakes = listOf(
                "Knees caving inward (valgus collapse) on the ascent.",
                "Weight shifting forward onto toes lifting heels.",
                "Losing abdominal brace and collapsing chest forward (good-morning squat)."
            ),
            proTip = "Screw your feet into the floor like you are trying to rip a paper towel in half between your feet."
        )
    )

    val foods: List<FoodItem> = listOf(
        FoodItem("f_1", "Skinless Chicken Breast", NutritionCategory.HIGH_PROTEIN, "200g Cooked", 330, 62.0f, 0.0f, 7.2f, "High biological value, rich in niacin and vitamin B6 with virtually zero carbohydrates."),
        FoodItem("f_2", "Wild Alaskan Salmon", NutritionCategory.HIGH_PROTEIN, "180g Cooked", 360, 40.0f, 0.0f, 22.0f, "Loaded with EPA/DHA Omega-3 fatty acids for joint recovery and muscle protein synthesis."),
        FoodItem("f_3", "Whole Eggs & Egg Whites Combo", NutritionCategory.HIGH_PROTEIN, "3 Whole + 3 Whites", 310, 32.0f, 2.0f, 15.0f, "Golden standard protein score with choline, vitamin D, and lutein."),
        FoodItem("f_4", "Whey Isolate Protein", NutritionCategory.HIGH_PROTEIN, "1 Scoop (30g)", 120, 27.0f, 1.0f, 0.5f, "Rapidly absorbed leucine bomb ideal immediately post-workout."),
        FoodItem("f_5", "Organic Rolled Oats", NutritionCategory.COMPLEX_CARBS, "100g Dry", 380, 13.0f, 66.0f, 7.0f, "Beta-glucan soluble fiber delivers steady, sustained glycogen replenishment."),
        FoodItem("f_6", "Baked Sweet Potatoes", NutritionCategory.COMPLEX_CARBS, "250g Baked", 230, 4.0f, 54.0f, 0.4f, "Rich in potassium to combat muscle cramps, vitamin A, and low glycemic carbs."),
        FoodItem("f_7", "Jasmine White Rice", NutritionCategory.COMPLEX_CARBS, "200g Cooked", 260, 5.0f, 57.0f, 0.5f, "Fast-digesting clean carbohydrate ideal for pre- and post-training meals without bloating."),
        FoodItem("f_8", "Ripe Hass Avocado", NutritionCategory.HEALTHY_FATS, "1 Medium (150g)", 240, 3.0f, 12.0f, 22.0f, "Monounsaturated oleic acid supports healthy testosterone production and nutrient absorption."),
        FoodItem("f_9", "Raw Almonds & Walnuts", NutritionCategory.HEALTHY_FATS, "40g (Handful)", 240, 7.0f, 7.0f, 20.0f, "High in vitamin E, magnesium for muscle relaxation, and healthy plant fats."),
        FoodItem("f_10", "Extra Virgin Olive Oil", NutritionCategory.HEALTHY_FATS, "1 Tablespoon (15ml)", 120, 0.0f, 0.0f, 14.0f, "Powerful antioxidant polyphenols and cardiac protection."),
        FoodItem("f_11", "Organic Firm Tofu", NutritionCategory.VEGETARIAN, "200g Pan-seared", 220, 24.0f, 6.0f, 11.0f, "Complete vegetarian amino acid profile, calcium-rich, and versatile."),
        FoodItem("f_12", "Greek Yogurt 0% Fat", NutritionCategory.VEGETARIAN, "250g Serving", 150, 25.0f, 9.0f, 0.0f, "Slow-digesting micellar casein protein, rich in probiotics for gut health.")
    )

    val sampleDailyMeals: List<DailyMeal> = listOf(
        DailyMeal(
            "Meal 1",
            "Anabolic Power Breakfast",
            "07:30 AM",
            listOf("3 Whole Organic Eggs + 150ml Liquid Egg Whites Scramble", "100g Rolled Oats cooked in water with cinnamon & blueberries", "1 Tablespoon Natural Peanut Butter", "Black Coffee + 500ml Filtered Water"),
            totalCalories = 680,
            proteinGrams = 52,
            carbsGrams = 68,
            fatsGrams = 22,
            notes = "Breaks nocturnal fast with high-leucine protein and low-GI complex carbs for sustained physical & cognitive energy."
        ),
        DailyMeal(
            "Meal 2",
            "Pre-Training Fuel",
            "11:30 AM",
            listOf("180g Grilled Chicken Breast strips", "220g Steamed Jasmine White Rice", "100g Steamed Green Asparagus or Broccoli", "Pink Himalayan Salt for vascular hydration"),
            totalCalories = 540,
            proteinGrams = 54,
            carbsGrams = 64,
            fatsGrams = 6,
            notes = "Eaten 90-120 minutes prior to training. Low in fat and fiber to optimize gastric emptying and intra-workout pumps."
        ),
        DailyMeal(
            "Meal 3",
            "Post-Workout Anabolic Window",
            "03:00 PM",
            listOf("1 Scoop Whey Isolate shaken with cold water", "1 Large Ripe Banana (fast glucose for glycogen)", "5g Creatine Monohydrate"),
            totalCalories = 240,
            proteinGrams = 28,
            carbsGrams = 32,
            fatsGrams = 1,
            notes = "Immediate post-lift replenishment to kickstart muscle protein synthesis and replenish cellular ATP stores."
        ),
        DailyMeal(
            "Meal 4",
            "Recovery & Growth Dinner",
            "06:30 PM",
            listOf("200g Wild Salmon Fillet pan-seared with lemon", "250g Roasted Sweet Potato cubes", "Large Mixed Greens Salad with 1 tbsp Extra Virgin Olive Oil"),
            totalCalories = 660,
            proteinGrams = 44,
            carbsGrams = 56,
            fatsGrams = 28,
            notes = "Omega-3 rich dinner to dampen exercise-induced systemic inflammation and nourish muscle tissue overnight."
        ),
        DailyMeal(
            "Meal 5",
            "Nocturnal Casein Sustenance",
            "09:30 PM",
            listOf("200g 0% Greek Yogurt or Micellar Casein", "20g Crushed Raw Almonds", "Dash of pure cinnamon & stevia"),
            totalCalories = 260,
            proteinGrams = 26,
            carbsGrams = 10,
            fatsGrams = 12,
            notes = "Slow-digesting curd provides steady amino acid drip during sleep while growth hormone peaks."
        )
    )

    val galleryPhotos: List<GalleryPhoto> = listOf(
        GalleryPhoto("g_1", "Apex Conditioning Stage Prep", GalleryCategory.PHYSIQUE, "Peak Week Shreds", "October 2026", "Sub-10% body fat silhouette captured under gym stage spotlights.", "Front Double Bicep Silhouette", 0),
        GalleryPhoto("g_2", "530 lbs Deadlift Lockout", GalleryCategory.TRAINING, "Powerlifting Primal Session", "September 2026", "Locking out 240kg on the competition platform with dead-stop focus.", "Side Platform Low Angle", 1),
        GalleryPhoto("g_3", "Heavy Dumbbell Incline Press", GalleryCategory.GYM_PHOTO, "Upper Chest Hypertrophy", "August 2026", "Working sets with 110-lb dumbbells under atmospheric rim lighting.", "Head-on Cinematic", 2),
        GalleryPhoto("g_4", "Golden Hour Outdoor Cardio", GalleryCategory.LIFESTYLE, "High Altitude Sprints", "July 2026", "Conditioning outside the weight room with sprints and hill climbs.", "Dynamic Action Shot", 3),
        GalleryPhoto("g_5", "Lat Spread V-Taper Dimension", GalleryCategory.PHYSIQUE, "Back Thickness & Symmetry", "June 2026", "Rear lat spread showing spinal erector depth and wing width.", "Rear Silhouette", 4),
        GalleryPhoto("g_6", "Coaching & Form Breakdown", GalleryCategory.TRAINING, "Mentoring Future Champions", "May 2026", "Hands-on bar path coaching with an amateur physique competitor.", "Coaching Perspective", 5)
    )

    val coachingServices: List<CoachingService> = listOf(
        CoachingService(
            id = "pkg_1",
            title = "1-on-1 Elite Coaching",
            price = "$149",
            period = "per month",
            isFeatured = true,
            description = "The all-inclusive transformation experience. Fully customized weekly programming, macro management, and direct messaging access.",
            features = listOf(
                "Custom Hypertrophy & Strength Periodization",
                "Personalized Macro & Caloric Target Blueprint",
                "Weekly Video Check-ins & Form Critiques",
                "Direct WhatsApp / Telegram Priority Messaging",
                "Supplementation & Recovery Protocol",
                "Continuous Adjustments Based on Biofeedback"
            )
        ),
        CoachingService(
            id = "pkg_2",
            title = "Custom Workout Routine",
            price = "$79",
            period = "one-time",
            isFeatured = false,
            description = "A customized 12-week training split tailored to your exact gym equipment, training experience, and muscle priority weaknesses.",
            features = listOf(
                "12-Week Periodized Workout Program",
                "Exercise Video Guides & Cue Breakdowns",
                "RPE / Reps-in-Reserve Target System",
                "Warm-up & Mobility Activation Guide",
                "Equipment Adaptation Alternative List"
            )
        ),
        CoachingService(
            id = "pkg_3",
            title = "Nutrition & Macro Consultation",
            price = "$99",
            period = "one-time",
            isFeatured = false,
            description = "Complete audit of your metabolic rate, caloric requirements, and a structured flexible dieting roadmap for cutting or lean bulking.",
            features = listOf(
                "Individual BMR & TDEE Calibrated Calculation",
                "Target Macros for Training vs Rest Days",
                "Grocery Shopping Master Checklist",
                "Dining Out & Travel Survival Strategies",
                "1-Hour Video Strategy Consultation"
            )
        )
    )
}
