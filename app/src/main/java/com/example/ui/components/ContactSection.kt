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
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.AthleteProfile
import com.example.data.CoachingService
import com.example.data.FitnessSampleData
import com.example.ui.theme.AthleticRed
import com.example.ui.theme.AthleticRedDark
import com.example.ui.theme.AthleticRedLight
import com.example.ui.theme.GoldMilestone
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceCardElevated
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun ContactSection(
    profile: AthleteProfile,
    onInstagramClick: () -> Unit,
    onWhatsAppClick: () -> Unit,
    modifier: Modifier = Modifier,
    services: List<CoachingService> = FitnessSampleData.coachingServices
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var selectedCategoryIndex by remember { mutableIntStateOf(0) }
    val categories = listOf("1-on-1 Coaching", "Workout Program", "Nutrition Blueprint", "Sponsorship / Collab")

    var nameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var messageError by remember { mutableStateOf(false) }
    var submissionSuccessDialog by remember { mutableStateOf(false) }

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
                text = "COACHING & INQUIRIES",
                color = AthleticRedLight,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "LET'S CONNECT.",
            color = TextPrimary,
            fontSize = 26.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = (-0.5).sp
        )

        Text(
            text = "Ready to build your ultimate physique? Select a coaching tier or send a direct inquiry.",
            color = TextSecondary,
            fontSize = 13.sp,
            modifier = Modifier.padding(top = 4.dp, bottom = 18.dp)
        )

        // Coaching Packages
        Text(
            text = "PERSONAL TRAINING & COACHING PACKAGES",
            color = TextMuted,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            services.forEach { pkg ->
                CoachingServiceCard(service = pkg, onSelect = {
                    val idx = categories.indexOfFirst { it.contains("Coaching", ignoreCase = true) }
                    if (idx >= 0) selectedCategoryIndex = idx
                })
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Direct Social Media Action Strip
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = onInstagramClick,
                colors = ButtonDefaults.buttonColors(containerColor = SurfaceCard),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .testTag("contact_instagram_button")
            ) {
                Icon(Icons.Default.CameraAlt, contentDescription = null, tint = AthleticRedLight, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Instagram", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = onWhatsAppClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E3A24)),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF2E6A3B)),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .testTag("contact_whatsapp_button")
            ) {
                Icon(Icons.Default.Phone, contentDescription = null, tint = Color(0xFF25D366), modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("WhatsApp", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Contact Form Card
        Card(
            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
            shape = RoundedCornerShape(16.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = "Direct Athlete Inquiry Form",
                    color = TextPrimary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Fill in your details below to schedule an onboarding consultation.",
                    color = TextSecondary,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 2.dp, bottom = 14.dp)
                )

                val tfColors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AthleticRed,
                    unfocusedBorderColor = SurfaceBorder,
                    errorBorderColor = AthleticRedLight,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary,
                    cursorColor = AthleticRed
                )

                // Name Field
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        if (it.isNotBlank()) nameError = false
                    },
                    isError = nameError,
                    label = { Text("Your Full Name *", color = if (nameError) AthleticRedLight else TextSecondary) },
                    supportingText = {
                        if (nameError) Text("Please enter your name", color = AthleticRedLight, fontSize = 11.sp)
                    },
                    colors = tfColors,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("contact_name_input")
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Email Field
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        if (it.contains("@") && it.contains(".")) emailError = false
                    },
                    isError = emailError,
                    label = { Text("Email Address *", color = if (emailError) AthleticRedLight else TextSecondary) },
                    supportingText = {
                        if (emailError) Text("Please enter a valid email address", color = AthleticRedLight, fontSize = 11.sp)
                    },
                    colors = tfColors,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("contact_email_input")
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Inquiry Category
                Text("Inquiry Category:", color = TextSecondary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    categories.forEachIndexed { index, cat ->
                        Surface(
                            color = if (selectedCategoryIndex == index) AthleticRed else SurfaceCardElevated,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.clickable { selectedCategoryIndex = index }
                        ) {
                            Text(
                                text = cat,
                                color = if (selectedCategoryIndex == index) Color.White else TextSecondary,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Message Field
                OutlinedTextField(
                    value = message,
                    onValueChange = {
                        message = it
                        if (it.isNotBlank()) messageError = false
                    },
                    isError = messageError,
                    label = { Text("Your Goals & Training Background *", color = if (messageError) AthleticRedLight else TextSecondary) },
                    supportingText = {
                        if (messageError) Text("Please share a brief message about your goals", color = AthleticRedLight, fontSize = 11.sp)
                    },
                    maxLines = 4,
                    colors = tfColors,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("contact_message_input")
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val isNameValid = name.isNotBlank()
                        val isEmailValid = email.isNotBlank() && email.contains("@") && email.contains(".")
                        val isMessageValid = message.isNotBlank()

                        nameError = !isNameValid
                        emailError = !isEmailValid
                        messageError = !isMessageValid

                        if (isNameValid && isEmailValid && isMessageValid) {
                            submissionSuccessDialog = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = AthleticRed),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("contact_submit_button")
                ) {
                    Icon(Icons.Default.Send, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("SEND COACHING INQUIRY", fontWeight = FontWeight.Black, color = Color.White, letterSpacing = 0.8.sp)
                }
            }
        }
    }

    // Success Confirmation Dialog
    if (submissionSuccessDialog) {
        Dialog(onDismissRequest = { submissionSuccessDialog = false }) {
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = SurfaceCard,
                border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = Color(0xFF00E676),
                        modifier = Modifier.size(52.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Inquiry Sent Successfully!",
                        color = TextPrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Thank you, $name! Your inquiry regarding '${categories[selectedCategoryIndex]}' has been recorded. ${profile.athleteName} will review your application and respond to $email within 24 hours.",
                        color = Color(0xFFCCCCCC),
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Button(
                        onClick = {
                            submissionSuccessDialog = false
                            name = ""
                            email = ""
                            message = ""
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = AthleticRed),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Done", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun CoachingServiceCard(
    service: CoachingService,
    onSelect: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(
            if (service.isFeatured) 1.5.dp else 1.dp,
            if (service.isFeatured) AthleticRed else SurfaceBorder
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = service.title,
                    color = TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                if (service.isFeatured) {
                    Surface(
                        color = AthleticRedDark,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Icon(Icons.Default.Star, contentDescription = null, tint = GoldMilestone, modifier = Modifier.size(12.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("MOST POPULAR", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Black)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = service.price,
                    color = AthleticRedLight,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = " / ${service.period}",
                    color = TextSecondary,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 3.dp)
                )
            }

            Text(
                text = service.description,
                color = Color(0xFFC0C0C4),
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 6.dp, bottom = 12.dp)
            )

            service.features.forEach { feat ->
                Row(modifier = Modifier.padding(vertical = 3.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = AthleticRedLight, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = feat, color = Color(0xFFDDDDDF), fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = onSelect,
                shape = RoundedCornerShape(10.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, if (service.isFeatured) AthleticRed else SurfaceBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (service.isFeatured) "SELECT 1-ON-1 COACHING" else "INQUIRE ABOUT THIS PLAN",
                    color = if (service.isFeatured) AthleticRedLight else Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
