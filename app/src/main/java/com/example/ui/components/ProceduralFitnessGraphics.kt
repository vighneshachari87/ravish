package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AthleticRed
import com.example.ui.theme.AthleticRedDark
import com.example.ui.theme.AthleticRedLight
import com.example.ui.theme.BackgroundDarkCharcoal
import com.example.ui.theme.BackgroundDeepBlack
import com.example.ui.theme.GoldMilestone
import com.example.ui.theme.SurfaceCard
import kotlin.math.roundToInt

@Composable
fun AthleteHeroVisual(
    modifier: Modifier = Modifier,
    athleteName: String = "ALEX VANCE"
) {
    val infiniteTransition = rememberInfiniteTransition(label = "hero_glow")
    val glowPulse by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(2400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow_alpha"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(260.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(BackgroundDarkCharcoal)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // Background ambient radial gradient in Athletic Red
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        AthleticRedDark.copy(alpha = 0.45f * glowPulse),
                        Color(0xFF1F0606).copy(alpha = 0.25f),
                        BackgroundDeepBlack
                    ),
                    center = Offset(w * 0.7f, h * 0.45f),
                    radius = w * 0.65f
                )
            )

            // Dynamic grid lines in background
            val gridStep = 40f
            var xPos = 0f
            while (xPos < w) {
                drawLine(
                    color = Color(0x15FFFFFF),
                    start = Offset(xPos, 0f),
                    end = Offset(xPos, h),
                    strokeWidth = 1f
                )
                xPos += gridStep
            }

            // Atmospheric diagonal spotlight beams
            val beamPath = Path().apply {
                moveTo(w * 0.5f, 0f)
                lineTo(w * 0.95f, 0f)
                lineTo(w * 0.7f, h)
                lineTo(w * 0.35f, h)
                close()
            }
            drawPath(
                path = beamPath,
                brush = Brush.linearGradient(
                    colors = listOf(
                        AthleticRedLight.copy(alpha = 0.15f * glowPulse),
                        Color.Transparent
                    ),
                    start = Offset(w * 0.7f, 0f),
                    end = Offset(w * 0.5f, h)
                )
            )

            // Stylized V-Taper Muscular Silhouette
            val centerX = w * 0.72f
            val baseHeadY = h * 0.22f

            // Head & Neck
            drawCircle(
                color = Color(0xFF2E2E32),
                radius = 26f,
                center = Offset(centerX, baseHeadY)
            )

            // Traps & Massive V-Taper Torso Path
            val torsoPath = Path().apply {
                // Left Trap
                moveTo(centerX - 16f, baseHeadY + 18f)
                lineTo(centerX - 65f, baseHeadY + 36f) // Left Shoulder Cap
                // Left Deltoid & Lat taper
                cubicTo(
                    centerX - 85f, baseHeadY + 70f,
                    centerX - 70f, baseHeadY + 110f,
                    centerX - 24f, h * 0.85f // Narrow Waist
                )
                // Bottom Waist Line
                lineTo(centerX + 24f, h * 0.85f)
                // Right Lat taper & Deltoid
                cubicTo(
                    centerX + 70f, baseHeadY + 110f,
                    centerX + 85f, baseHeadY + 70f,
                    centerX + 65f, baseHeadY + 36f // Right Shoulder Cap
                )
                lineTo(centerX + 16f, baseHeadY + 18f) // Right Trap
                close()
            }

            drawPath(
                path = torsoPath,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF3A3A40),
                        Color(0xFF1E1E22),
                        BackgroundDeepBlack
                    ),
                    startY = baseHeadY,
                    endY = h * 0.85f
                )
            )

            // Muscular definition rim lighting in Athletic Red
            drawPath(
                path = torsoPath,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        AthleticRed,
                        Color.Transparent,
                        AthleticRed
                    ),
                    startX = centerX - 85f,
                    endX = centerX + 85f
                ),
                style = Stroke(width = 3.5f, cap = StrokeCap.Round)
            )

            // Heavy Barbell Across Shoulders
            drawLine(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF888888),
                        Color(0xFFFFFFFF),
                        Color(0xFF888888)
                    )
                ),
                start = Offset(centerX - 130f, baseHeadY + 45f),
                end = Offset(centerX + 130f, baseHeadY + 45f),
                strokeWidth = 7f,
                cap = StrokeCap.Round
            )

            // Left Weight Plates
            drawRoundRect(
                color = AthleticRed,
                topLeft = Offset(centerX - 125f, baseHeadY + 15f),
                size = Size(12f, 60f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(4f)
            )
            drawRoundRect(
                color = Color(0xFFD0D0D4),
                topLeft = Offset(centerX - 110f, baseHeadY + 22f),
                size = Size(10f, 46f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(4f)
            )

            // Right Weight Plates
            drawRoundRect(
                color = Color(0xFFD0D0D4),
                topLeft = Offset(centerX + 100f, baseHeadY + 22f),
                size = Size(10f, 46f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(4f)
            )
            drawRoundRect(
                color = AthleticRed,
                topLeft = Offset(centerX + 113f, baseHeadY + 15f),
                size = Size(12f, 60f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(4f)
            )

            // Ground Floor Shadow / Reflection
            drawLine(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color.Transparent, Color(0x66FF3030), Color.Transparent)
                ),
                start = Offset(0f, h - 2f),
                end = Offset(w, h - 2f),
                strokeWidth = 3f
            )
        }

        // Left Overlay Text & Badges
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 20.dp, end = 120.dp)
        ) {
            Surface(
                color = AthleticRed.copy(alpha = 0.2f),
                shape = RoundedCornerShape(6.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, AthleticRed.copy(alpha = 0.6f))
            ) {
                Text(
                    text = "EST. ELITE PHYSIQUE",
                    color = AthleticRedLight,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = athleteName.uppercase(),
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = (-0.5).sp
            )

            Text(
                text = "HYPERTROPHY & CONDITIONING",
                color = Color(0xFFCCCCCC),
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.2.sp
            )
        }
    }
}

/**
 * Interactive Before & After comparison slider component.
 * Allows the user to touch and drag horizontally to reveal the before and after states.
 */
@Composable
fun InteractiveTransformationSlider(
    modifier: Modifier = Modifier,
    beforeWeight: String = "148 lbs",
    beforeBF: String = "17% BF",
    afterWeight: String = "194 lbs",
    afterBF: String = "9.8% BF"
) {
    var sliderFraction by remember { mutableFloatStateOf(0.5f) }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(290.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(SurfaceCard)
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    change.consume()
                    val newX = change.position.x
                    sliderFraction = (newX / size.width.toFloat()).coerceIn(0.05f, 0.95f)
                }
            }
    ) {
        val widthPx = constraints.maxWidth.toFloat()
        val heightPx = constraints.maxHeight.toFloat()
        val splitPx = widthPx * sliderFraction

        // Base Canvas rendering the two physiques
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // 1. Right side (AFTER: Aesthetic, 3D V-Taper, Athletic Red Ambient)
            drawRect(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF140707), Color(0xFF1F0808)),
                    startX = 0f,
                    endX = w
                )
            )

            // After physique details on the right
            val afterCenterX = w * 0.72f
            // Wide aesthetic back & delts
            val afterTorso = Path().apply {
                moveTo(afterCenterX - 20f, h * 0.22f)
                lineTo(afterCenterX - 85f, h * 0.36f) // Wide delts
                cubicTo(
                    afterCenterX - 100f, h * 0.50f,
                    afterCenterX - 85f, h * 0.65f,
                    afterCenterX - 28f, h * 0.85f // Taper waist
                )
                lineTo(afterCenterX + 28f, h * 0.85f)
                cubicTo(
                    afterCenterX + 85f, h * 0.65f,
                    afterCenterX + 100f, h * 0.50f,
                    afterCenterX + 85f, h * 0.36f
                )
                lineTo(afterCenterX + 20f, h * 0.22f)
                close()
            }
            drawPath(
                path = afterTorso,
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF45454C), Color(0xFF28282E)),
                    startY = h * 0.2f,
                    endY = h * 0.85f
                )
            )
            // Athletic red rim glow
            drawPath(
                path = afterTorso,
                color = AthleticRed,
                style = Stroke(width = 3f)
            )
            drawCircle(
                color = Color(0xFF38383E),
                radius = 28f,
                center = Offset(afterCenterX, h * 0.17f)
            )

            // 2. Left side (BEFORE: Narrower beginner build, subtle grey ambient)
            // Clipped to [0, splitPx]
            drawRect(
                color = BackgroundDeepBlack,
                topLeft = Offset(0f, 0f),
                size = Size(splitPx, h)
            )

            val beforeCenterX = w * 0.28f
            // Narrower beginner torso
            val beforeTorso = Path().apply {
                moveTo(beforeCenterX - 12f, h * 0.24f)
                lineTo(beforeCenterX - 45f, h * 0.36f) // Sloped narrow shoulders
                lineTo(beforeCenterX - 35f, h * 0.85f) // No taper
                lineTo(beforeCenterX + 35f, h * 0.85f)
                lineTo(beforeCenterX + 45f, h * 0.36f)
                lineTo(beforeCenterX + 12f, h * 0.24f)
                close()
            }

            if (splitPx > (beforeCenterX - 50f)) {
                drawPath(
                    path = beforeTorso,
                    color = Color(0xFF26262B)
                )
                drawCircle(
                    color = Color(0xFF2C2C32),
                    radius = 24f,
                    center = Offset(beforeCenterX, h * 0.19f)
                )
                drawPath(
                    path = beforeTorso,
                    color = Color(0xFF555560),
                    style = Stroke(width = 2f)
                )
            }

            // Divider vertical line
            drawLine(
                color = Color.White,
                start = Offset(splitPx, 0f),
                end = Offset(splitPx, h),
                strokeWidth = 3f
            )
        }

        // Before & After Overlay Badges
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(14.dp)
        ) {
            Surface(
                color = Color.Black.copy(alpha = 0.75f),
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x33FFFFFF))
            ) {
                Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) {
                    Text(
                        text = "BEFORE (DAY 1)",
                        color = Color(0xFFB0B0B8),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "$beforeWeight • $beforeBF",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(14.dp)
        ) {
            Surface(
                color = AthleticRedDark.copy(alpha = 0.85f),
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, AthleticRedLight)
            ) {
                Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) {
                    Text(
                        text = "AFTER (STAGE/PEAK)",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "$afterWeight • $afterBF",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }

        // Draggable Knob in Center of Divider
        val knobX = (widthPx * sliderFraction).roundToInt()
        Box(
            modifier = Modifier
                .offset { IntOffset(knobX - 22.dp.roundToPx(), (heightPx / 2 - 22.dp.roundToPx()).roundToInt()) }
                .size(44.dp)
                .clip(CircleShape)
                .background(AthleticRed)
                .padding(2.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.SwapHoriz,
                    contentDescription = "Drag to compare before and after",
                    tint = AthleticRed,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Hint bar at the bottom
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp),
            color = Color.Black.copy(alpha = 0.65f),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = "◀ Drag slider to compare transformation ▶",
                color = Color(0xFFD0D0D4),
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 4.dp)
            )
        }
    }
}

/**
 * Procedural stylized visual for Gallery cards matching specific categories.
 */
@Composable
fun StylizedGalleryArtwork(
    visualType: Int,
    categoryName: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFF141416))
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            when (visualType % 6) {
                0 -> {
                    // Front Double Bicep Silhouette
                    drawRect(
                        brush = Brush.radialGradient(
                            colors = listOf(Color(0xFF330C0C), Color(0xFF101012)),
                            center = Offset(w * 0.5f, h * 0.5f),
                            radius = w * 0.6f
                        )
                    )
                    // Bicep Flex Silhouette
                    val bicepPath = Path().apply {
                        moveTo(w * 0.5f - 14f, h * 0.28f)
                        lineTo(w * 0.5f - 55f, h * 0.35f)
                        lineTo(w * 0.5f - 75f, h * 0.20f) // Left flexed bicep
                        lineTo(w * 0.5f - 60f, h * 0.42f)
                        lineTo(w * 0.5f - 22f, h * 0.85f)
                        lineTo(w * 0.5f + 22f, h * 0.85f)
                        lineTo(w * 0.5f + 60f, h * 0.42f)
                        lineTo(w * 0.5f + 75f, h * 0.20f) // Right flexed bicep
                        lineTo(w * 0.5f + 55f, h * 0.35f)
                        lineTo(w * 0.5f + 14f, h * 0.28f)
                        close()
                    }
                    drawPath(path = bicepPath, color = Color(0xFF2C2C32))
                    drawPath(path = bicepPath, color = AthleticRed, style = Stroke(width = 3f))
                    drawCircle(color = Color(0xFF3C3C42), radius = 20f, center = Offset(w * 0.5f, h * 0.2f))
                }
                1 -> {
                    // Heavy Barbell Deadlift
                    drawRect(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF1A1A1E), Color(0xFF0C0C0E))
                        )
                    )
                    // Barbell line
                    drawLine(
                        color = Color.White,
                        start = Offset(w * 0.15f, h * 0.65f),
                        end = Offset(w * 0.85f, h * 0.65f),
                        strokeWidth = 6f
                    )
                    // Stack of 45lb plates
                    for (i in 0..2) {
                        drawRoundRect(
                            color = AthleticRed,
                            topLeft = Offset(w * 0.2f + i * 16f, h * 0.35f),
                            size = Size(12f, h * 0.55f),
                            cornerRadius = androidx.compose.ui.geometry.CornerRadius(4f)
                        )
                        drawRoundRect(
                            color = AthleticRed,
                            topLeft = Offset(w * 0.8f - (i + 1) * 16f, h * 0.35f),
                            size = Size(12f, h * 0.55f),
                            cornerRadius = androidx.compose.ui.geometry.CornerRadius(4f)
                        )
                    }
                }
                2 -> {
                    // Heavy Dumbbell Incline
                    drawRect(
                        brush = Brush.radialGradient(
                            colors = listOf(Color(0xFF241010), Color(0xFF0F0F12))
                        )
                    )
                    // Dual heavy dumbbells
                    val dbW = 60f
                    val dbH = 26f
                    // Left DB
                    drawRoundRect(color = Color(0xFFCCCCCC), topLeft = Offset(w * 0.25f, h * 0.4f), size = Size(dbW, dbH), cornerRadius = androidx.compose.ui.geometry.CornerRadius(4f))
                    drawCircle(color = AthleticRed, radius = 28f, center = Offset(w * 0.25f, h * 0.4f + dbH / 2))
                    drawCircle(color = AthleticRed, radius = 28f, center = Offset(w * 0.25f + dbW, h * 0.4f + dbH / 2))
                    // Right DB
                    drawRoundRect(color = Color(0xFFCCCCCC), topLeft = Offset(w * 0.55f, h * 0.4f), size = Size(dbW, dbH), cornerRadius = androidx.compose.ui.geometry.CornerRadius(4f))
                    drawCircle(color = AthleticRed, radius = 28f, center = Offset(w * 0.55f, h * 0.4f + dbH / 2))
                    drawCircle(color = AthleticRed, radius = 28f, center = Offset(w * 0.55f + dbW, h * 0.4f + dbH / 2))
                }
                3 -> {
                    // Outdoor / Conditioning Sunrise
                    drawRect(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFF3B1515), Color(0xFF141418))
                        )
                    )
                    // Sun / Horizon
                    drawCircle(color = GoldMilestone, radius = 40f, center = Offset(w * 0.5f, h * 0.5f))
                    // Mountain peaks
                    val mtn = Path().apply {
                        moveTo(0f, h)
                        lineTo(w * 0.35f, h * 0.45f)
                        lineTo(w * 0.65f, h * 0.65f)
                        lineTo(w * 0.9f, h * 0.4f)
                        lineTo(w, h)
                        close()
                    }
                    drawPath(path = mtn, color = Color(0xFF0E0E12))
                }
                4 -> {
                    // Lat Spread V-Taper
                    drawRect(brush = Brush.radialGradient(colors = listOf(Color(0xFF280B0B), Color(0xFF0A0A0C))))
                    val backPath = Path().apply {
                        moveTo(w * 0.5f - 18f, h * 0.25f)
                        lineTo(w * 0.5f - 90f, h * 0.45f) // Lat wing flare
                        lineTo(w * 0.5f - 24f, h * 0.85f)
                        lineTo(w * 0.5f + 24f, h * 0.85f)
                        lineTo(w * 0.5f + 90f, h * 0.45f)
                        lineTo(w * 0.5f + 18f, h * 0.25f)
                        close()
                    }
                    drawPath(path = backPath, color = Color(0xFF26262B))
                    drawPath(path = backPath, color = Color.White.copy(alpha = 0.4f), style = Stroke(width = 2.5f))
                }
                else -> {
                    // Coaching clipboard / blueprint
                    drawRect(brush = Brush.linearGradient(colors = listOf(Color(0xFF1C1C22), Color(0xFF101014))))
                    drawRoundRect(
                        color = AthleticRed.copy(alpha = 0.25f),
                        topLeft = Offset(w * 0.3f, h * 0.2f),
                        size = Size(w * 0.4f, h * 0.6f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(8f)
                    )
                    for (row in 0..3) {
                        drawLine(
                            color = Color(0x66FFFFFF),
                            start = Offset(w * 0.35f, h * 0.35f + row * 22f),
                            end = Offset(w * 0.65f, h * 0.35f + row * 22f),
                            strokeWidth = 3f
                        )
                    }
                }
            }
        }

        // Category Pill overlay
        Surface(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(10.dp),
            color = Color.Black.copy(alpha = 0.7f),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(
                text = categoryName.uppercase(),
                color = AthleticRedLight,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.8.sp,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
            )
        }
    }
}
