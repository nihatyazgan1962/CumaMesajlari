package com.cumamesajlari.app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cumamesajlari.app.graphics.MotifDrawers
import com.cumamesajlari.app.model.CardCustomizationState

@Composable
fun CardPreview(
    state: CardCustomizationState,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(20.dp),
                spotColor = state.theme.accentColor.copy(alpha = 0.4f),
                ambientColor = Color.Black
            )
            .aspectRatio(4f / 5f), // Standard Instagram/Story ratio
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(state.theme.backgroundBrush)
                .clip(RoundedCornerShape(20.dp))
        ) {
            // 1. Background Image (if theme has artwork)
            if (state.theme.bgImageResId != null) {
                Image(
                    painter = painterResource(id = state.theme.bgImageResId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                // Dark spiritual overlay for high contrast text readability
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color(0x99000000),
                                    Color(0x77000000),
                                    Color(0xBB000000)
                                )
                            )
                        )
                )
            } else {
                // Subtle Center Radial Lighting Glow for gradient themes
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                state.theme.accentColor.copy(alpha = 0.18f),
                                Color.Transparent
                            ),
                            center = Offset(size.width / 2, size.height * 0.45f),
                            radius = size.width * 0.65f
                        )
                    )
                }
            }

            // 2. Custom Islamic Vector Motifs & Frames (Canvas)
            if (state.showMotif || state.showBorder) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    MotifDrawers.drawMotif(
                        scope = this,
                        motifType = state.motif,
                        color = state.theme.motifColor,
                        accentColor = state.theme.accentColor,
                        intensity = state.decorativeIntensity
                    )
                }
            }

            // 3. Card Content (Header, Text, Signature)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 28.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header Title
                if (state.showHeader && state.headerTitle.isNotBlank()) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = state.headerTitle.uppercase(),
                        color = state.theme.accentColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )

                    // Small Golden Accent Line
                    Box(
                        modifier = Modifier
                            .padding(top = 4.dp, bottom = 8.dp)
                            .size(width = 48.dp, height = 2.dp)
                            .background(
                                Brush.horizontalGradient(
                                    listOf(Color.Transparent, state.theme.accentColor, Color.Transparent)
                                )
                            )
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                // Main Message Text
                Text(
                    text = state.messageText,
                    color = state.theme.textColor,
                    fontSize = state.fontSizeSp.sp,
                    fontFamily = state.font.fontFamily,
                    fontWeight = state.font.fontWeight,
                    fontStyle = state.font.fontStyle,
                    textAlign = state.textAlign,
                    lineHeight = (state.fontSizeSp * 1.45f).sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                )

                Spacer(modifier = Modifier.weight(1.1f))

                // Signature / Footer
                if (state.showSignature && state.signature.isNotBlank()) {
                    Text(
                        text = state.signature,
                        color = state.theme.signatureColor,
                        fontSize = 13.sp,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.Serif,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                }
            }
        }
    }
}
