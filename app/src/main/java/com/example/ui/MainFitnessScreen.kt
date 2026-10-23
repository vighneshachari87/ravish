package com.example.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.AthleteProfile
import com.example.data.FitnessSampleData
import com.example.ui.components.AboutSection
import com.example.ui.components.ContactSection
import com.example.ui.components.EditBrandDialog
import com.example.ui.components.FooterSection
import com.example.ui.components.GallerySection
import com.example.ui.components.HeaderNavbar
import com.example.ui.components.HeroSection
import com.example.ui.components.NavSection
import com.example.ui.components.NutritionSection
import com.example.ui.components.ToolsSection
import com.example.ui.components.TransformationSection
import com.example.ui.components.WorkoutSection
import com.example.ui.theme.AthleticRed
import com.example.ui.theme.BackgroundDeepBlack
import kotlinx.coroutines.launch

@Composable
fun MainFitnessScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    var profile by remember { mutableStateOf(FitnessSampleData.defaultProfile) }
    var showEditBrandDialog by remember { mutableStateOf(false) }
    var currentNavSection by remember { mutableStateOf(NavSection.HOME) }

    val showBackToTopButton by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 1 }
    }

    // Map section enum to LazyColumn index
    val sectionIndexMap = mapOf(
        NavSection.HOME to 0,
        NavSection.ABOUT to 1,
        NavSection.TRANSFORMATION to 2,
        NavSection.WORKOUTS to 3,
        NavSection.NUTRITION to 4,
        NavSection.GALLERY to 5,
        NavSection.TOOLS to 6,
        NavSection.COACHING to 7
    )

    fun scrollToSection(section: NavSection) {
        currentNavSection = section
        val targetIdx = sectionIndexMap[section] ?: 0
        coroutineScope.launch {
            listState.animateScrollToItem(targetIdx)
        }
    }

    fun openUrl(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Opening: $url", Toast.LENGTH_SHORT).show()
        }
    }

    fun openInstagram() {
        val handle = profile.instagramHandle.removePrefix("@")
        openUrl("https://instagram.com/$handle")
    }

    fun openWhatsApp() {
        val cleanNumber = profile.whatsappNumber.replace(Regex("[^0-9+]"), "")
        openUrl("https://wa.me/$cleanNumber")
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundDeepBlack),
        topBar = {
            HeaderNavbar(
                brandName = profile.brandName,
                athleteName = profile.athleteName,
                currentSection = currentNavSection,
                onSectionSelected = { section -> scrollToSection(section) },
                onEditBrandClick = { showEditBrandDialog = true },
                onInstagramClick = { openInstagram() }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(BackgroundDeepBlack)
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("fitness_main_scroll_list")
            ) {
                item(key = "hero") {
                    HeroSection(
                        profile = profile,
                        onExploreJourney = { scrollToSection(NavSection.ABOUT) },
                        onFollowInstagram = { openInstagram() }
                    )
                }

                item(key = "about") {
                    AboutSection(
                        profile = profile,
                        onEditProfile = { showEditBrandDialog = true }
                    )
                }

                item(key = "transformation") {
                    TransformationSection()
                }

                item(key = "workouts") {
                    WorkoutSection()
                }

                item(key = "nutrition") {
                    NutritionSection()
                }

                item(key = "gallery") {
                    GallerySection()
                }

                item(key = "tools") {
                    ToolsSection()
                }

                item(key = "contact") {
                    ContactSection(
                        profile = profile,
                        onInstagramClick = { openInstagram() },
                        onWhatsAppClick = { openWhatsApp() }
                    )
                }

                item(key = "footer") {
                    FooterSection(
                        profile = profile,
                        onNavigate = { section -> scrollToSection(section) },
                        onBackToTop = { scrollToSection(NavSection.HOME) },
                        onInstagramClick = { openInstagram() },
                        onWhatsAppClick = { openWhatsApp() }
                    )
                }
            }

            // Floating Back to Top Button
            AnimatedVisibility(
                visible = showBackToTopButton,
                enter = fadeIn(),
                exit = fadeOut(),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(20.dp)
                    .navigationBarsPadding()
            ) {
                FloatingActionButton(
                    onClick = { scrollToSection(NavSection.HOME) },
                    containerColor = AthleticRed,
                    contentColor = Color.White,
                    shape = CircleShape,
                    elevation = FloatingActionButtonDefaults.elevation(6.dp),
                    modifier = Modifier
                        .size(48.dp)
                        .testTag("floating_back_to_top_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Scroll back to top",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }

    if (showEditBrandDialog) {
        EditBrandDialog(
            currentProfile = profile,
            onDismiss = { showEditBrandDialog = false },
            onSave = { updatedProfile ->
                profile = updatedProfile
                showEditBrandDialog = false
                Toast.makeText(context, "Brand & Profile updated!", Toast.LENGTH_SHORT).show()
            }
        )
    }
}
