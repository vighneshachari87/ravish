package com.example.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.FitnessSampleData
import com.example.data.GalleryCategory
import com.example.data.GalleryPhoto
import com.example.ui.theme.AthleticRed
import com.example.ui.theme.AthleticRedLight
import com.example.ui.theme.BackgroundDarkCharcoal
import com.example.ui.theme.BackgroundDeepBlack
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceCardElevated
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun GallerySection(
    modifier: Modifier = Modifier,
    photos: List<GalleryPhoto> = FitnessSampleData.galleryPhotos
) {
    var selectedCategory by remember { mutableStateOf(GalleryCategory.ALL) }
    var activeLightboxIndex by remember { mutableStateOf<Int?>(null) }

    val filteredPhotos = photos.filter { photo ->
        selectedCategory == GalleryCategory.ALL || photo.category == selectedCategory
    }

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
                text = "ATHLETE PORTFOLIO & PHOTOGRAPHY",
                color = AthleticRedLight,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "THE GRIND. THE GROWTH. THE RESULTS.",
            color = TextPrimary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = (-0.5).sp
        )

        Text(
            text = "High-impact visual documentation of prep stages, heavy lifting sessions, and bodybuilding aesthetics.",
            color = TextSecondary,
            fontSize = 13.sp,
            modifier = Modifier.padding(top = 4.dp, bottom = 18.dp)
        )

        // Category Filter Chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            GalleryCategory.values().forEach { cat ->
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

        Spacer(modifier = Modifier.height(16.dp))

        // Grid of photo cards (pairs of 2 or full width)
        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            filteredPhotos.forEachIndexed { index, photo ->
                GalleryPhotoCard(
                    photo = photo,
                    onClick = {
                        // find global index in photos list
                        val globalIdx = photos.indexOf(photo)
                        activeLightboxIndex = if (globalIdx >= 0) globalIdx else index
                    }
                )
            }
        }
    }

    // Lightbox Dialog
    activeLightboxIndex?.let { currentIdx ->
        val currentPhoto = photos.getOrNull(currentIdx)
        if (currentPhoto != null) {
            GalleryLightboxDialog(
                photo = currentPhoto,
                currentIndex = currentIdx,
                totalCount = photos.size,
                onPrevious = {
                    val prevIdx = if (currentIdx > 0) currentIdx - 1 else photos.size - 1
                    activeLightboxIndex = prevIdx
                },
                onNext = {
                    val nextIdx = if (currentIdx < photos.size - 1) currentIdx + 1 else 0
                    activeLightboxIndex = nextIdx
                },
                onDismiss = { activeLightboxIndex = null }
            )
        }
    }
}

@Composable
fun GalleryPhotoCard(
    photo: GalleryPhoto,
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column {
            // Visual Artwork
            Box {
                StylizedGalleryArtwork(
                    visualType = photo.visualType,
                    categoryName = photo.category.displayName,
                    modifier = Modifier.fillMaxWidth().height(190.dp)
                )

                // Magnify zoom indicator overlay
                Surface(
                    color = Color.Black.copy(alpha = 0.6f),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ZoomIn,
                        contentDescription = "View photo in lightbox",
                        tint = Color.White,
                        modifier = Modifier.padding(6.dp).size(18.dp)
                    )
                }
            }

            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = photo.title,
                        color = TextPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = photo.date,
                        color = TextMuted,
                        fontSize = 11.sp
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = photo.subtitle,
                    color = AthleticRedLight,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = photo.description,
                    color = TextSecondary,
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )
            }
        }
    }
}

@Composable
fun GalleryLightboxDialog(
    photo: GalleryPhoto,
    currentIndex: Int,
    totalCount: Int,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackgroundDeepBlack.copy(alpha = 0.96f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Top control bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${currentIndex + 1} / $totalCount",
                        color = TextSecondary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close Lightbox", tint = Color.White, modifier = Modifier.size(28.dp))
                    }
                }

                // Center visual card
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    StylizedGalleryArtwork(
                        visualType = photo.visualType,
                        categoryName = photo.category.displayName,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp)
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = photo.title,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black
                    )

                    Text(
                        text = "${photo.subtitle} • ${photo.date}",
                        color = AthleticRedLight,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
                    )

                    Text(
                        text = photo.description,
                        color = Color(0xFFC8C8CC),
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                // Bottom Navigation controls
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = SurfaceCardElevated,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.clickable { onPrevious() }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                        ) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Previous image", tint = Color.White, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Previous", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Surface(
                        color = AthleticRed,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.clickable { onNext() }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                        ) {
                            Text("Next", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next image", tint = Color.White, modifier = Modifier.size(18.dp))
                        }
                    }
                }
            }
        }
    }
}
