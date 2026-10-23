package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AthleteProfile
import com.example.ui.theme.AthleticRed
import com.example.ui.theme.AthleticRedDark
import com.example.ui.theme.AthleticRedLight
import com.example.ui.theme.BackgroundDarkCharcoal
import com.example.ui.theme.BackgroundDeepBlack
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun FooterSection(
    profile: AthleteProfile,
    onNavigate: (NavSection) -> Unit,
    onBackToTop: () -> Unit,
    onInstagramClick: () -> Unit,
    onWhatsAppClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = BackgroundDarkCharcoal,
        border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            // Brand emblem & headline
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(AthleticRedDark),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.FitnessCenter,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = profile.brandName.uppercase(),
                            color = TextPrimary,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "STRONGER EVERY DAY.",
                            color = AthleticRedLight,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                    }
                }

                // Back to top action button
                Surface(
                    color = Color(0xFF222226),
                    shape = CircleShape,
                    modifier = Modifier
                        .clickable { onBackToTop() }
                        .testTag("footer_back_to_top_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Back to top",
                        tint = Color.White,
                        modifier = Modifier.padding(8.dp).size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = profile.secondaryTagline,
                color = TextSecondary,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Quick navigation links
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("PORTFOLIO", color = TextMuted, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("About", color = TextSecondary, fontSize = 12.sp, modifier = Modifier.clickable { onNavigate(NavSection.ABOUT) }.padding(vertical = 3.dp))
                    Text("Transformation", color = TextSecondary, fontSize = 12.sp, modifier = Modifier.clickable { onNavigate(NavSection.TRANSFORMATION) }.padding(vertical = 3.dp))
                    Text("Gallery", color = TextSecondary, fontSize = 12.sp, modifier = Modifier.clickable { onNavigate(NavSection.GALLERY) }.padding(vertical = 3.dp))
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("TRAINING", color = TextMuted, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("Workouts", color = TextSecondary, fontSize = 12.sp, modifier = Modifier.clickable { onNavigate(NavSection.WORKOUTS) }.padding(vertical = 3.dp))
                    Text("Nutrition Fuel", color = TextSecondary, fontSize = 12.sp, modifier = Modifier.clickable { onNavigate(NavSection.NUTRITION) }.padding(vertical = 3.dp))
                    Text("Calculators", color = TextSecondary, fontSize = 12.sp, modifier = Modifier.clickable { onNavigate(NavSection.TOOLS) }.padding(vertical = 3.dp))
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Social Buttons
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedButton(
                    onClick = onInstagramClick,
                    shape = RoundedCornerShape(8.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder)
                ) {
                    Icon(Icons.Default.CameraAlt, contentDescription = null, tint = AthleticRedLight, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Instagram", color = Color.White, fontSize = 11.sp)
                }

                OutlinedButton(
                    onClick = onWhatsAppClick,
                    shape = RoundedCornerShape(8.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder)
                ) {
                    Icon(Icons.Default.Phone, contentDescription = null, tint = Color(0xFF00E676), modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("WhatsApp", color = Color.White, fontSize = 11.sp)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            HorizontalDivider(color = SurfaceBorder)
            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "© 2026 ${profile.brandName}. All rights reserved. Built for athletic performance and aesthetic discipline.",
                color = TextMuted,
                fontSize = 11.sp,
                lineHeight = 16.sp
            )
        }
    }
}
