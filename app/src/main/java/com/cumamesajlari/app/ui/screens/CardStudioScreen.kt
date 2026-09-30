package com.cumamesajlari.app.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.FormatAlignCenter
import androidx.compose.material.icons.filled.FormatAlignLeft
import androidx.compose.material.icons.filled.FormatAlignRight
import androidx.compose.material.icons.filled.FormatColorFill
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.filled.Yard
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cumamesajlari.app.graphics.BitmapExporter
import com.cumamesajlari.app.model.CardCustomizationState
import com.cumamesajlari.app.model.CardFont
import com.cumamesajlari.app.model.CardThemes
import com.cumamesajlari.app.model.MotifType
import com.cumamesajlari.app.ui.components.CardPreview
import com.cumamesajlari.app.ui.theme.GoldPrimary

@Composable
fun CardStudioScreen(
    state: CardCustomizationState,
    onStateChange: (CardCustomizationState) -> Unit,
    onNavigateToCatalog: () -> Unit
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabTitles = listOf("Metin & Yazı", "Tema & Renk", "Motif & Desen", "Yazı Tipi")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Header Info
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Cuma Kartı Tasarla",
                    style = MaterialTheme.typography.titleLarge,
                    color = GoldPrimary
                )
                Text(
                    text = "Mesajınızı özelleştirip görsel olarak paylaşın",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.LightGray
                )
            }

            OutlinedButton(
                onClick = {
                    // Randomize design
                    val randomTheme = CardThemes.all.random()
                    val randomMotif = MotifType.values().random()
                    val randomFont = CardFont.values().random()
                    onStateChange(
                        state.copy(
                            theme = randomTheme,
                            motif = randomMotif,
                            font = randomFont
                        )
                    )
                    Toast.makeText(context, "Yeni tasarım oluşturuldu ✨", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.outlinedButtonColors(contentColor = GoldPrimary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Rastgele", fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // LIVE CARD PREVIEW
        CardPreview(
            state = state,
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // QUICK ACTION BUTTONS (Share, Save, Copy)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    BitmapExporter.shareCard(context, state)
                },
                modifier = Modifier.weight(1.3f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GoldPrimary,
                    contentColor = Color(0xFF141414)
                ),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Görsel Paylaş", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }

            ElevatedButton(
                onClick = {
                    BitmapExporter.saveToGallery(context, state)
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = GoldPrimary
                ),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(Icons.Default.Download, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Kaydet", fontSize = 13.sp)
            }

            IconButton(
                onClick = {
                    val fullText = "${state.headerTitle}\n\n${state.messageText}\n\n${state.signature}"
                    clipboardManager.setText(AnnotatedString(fullText))
                    Toast.makeText(context, "Metin panoya kopyalandı 📋", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Icon(Icons.Default.ContentCopy, contentDescription = "Kopyala", tint = GoldPrimary)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // CUSTOMIZATION CONTROL PANEL
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                // TABS
                TabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = Color.Transparent,
                    contentColor = GoldPrimary,
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                            color = GoldPrimary
                        )
                    }
                ) {
                    tabTitles.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = {
                                Text(
                                    title,
                                    fontSize = 12.sp,
                                    fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                when (selectedTab) {
                    // 1. TAB: METİN & YAZI
                    0 -> {
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            // Header Input
                            OutlinedTextField(
                                value = state.headerTitle,
                                onValueChange = { onStateChange(state.copy(headerTitle = it)) },
                                label = { Text("Başlık (Örn: HAYIRLI CUMALAR)") },
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = GoldPrimary,
                                    unfocusedBorderColor = Color.Gray
                                ),
                                shape = RoundedCornerShape(12.dp),
                                singleLine = true
                            )

                            // Message Body Input
                            OutlinedTextField(
                                value = state.messageText,
                                onValueChange = { onStateChange(state.copy(messageText = it)) },
                                label = { Text("Cuma Mesajı Metni") },
                                modifier = Modifier.fillMaxWidth(),
                                minLines = 3,
                                maxLines = 6,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = GoldPrimary,
                                    unfocusedBorderColor = Color.Gray
                                ),
                                shape = RoundedCornerShape(12.dp)
                            )

                            // Signature Input
                            OutlinedTextField(
                                value = state.signature,
                                onValueChange = { onStateChange(state.copy(signature = it)) },
                                label = { Text("İmza / Kapanış (Örn: Selam ve Dua ile)") },
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = GoldPrimary,
                                    unfocusedBorderColor = Color.Gray
                                ),
                                shape = RoundedCornerShape(12.dp),
                                singleLine = true
                            )

                            // Pick From Catalog Shortcut
                            Button(
                                onClick = onNavigateToCatalog,
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                    contentColor = GoldPrimary
                                ),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Icon(Icons.Default.TextFields, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Hazır Mesajlar Kataloğundan Seç", fontSize = 13.sp)
                            }
                        }
                    }

                    // 2. TAB: TEMA & RENK
                    1 -> {
                        Column {
                            Text(
                                text = "Renk Paleti & Zemin",
                                style = MaterialTheme.typography.titleMedium,
                                color = GoldPrimary
                            )
                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                CardThemes.all.forEach { theme ->
                                    val isSelected = state.theme.id == theme.id
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier
                                            .clickable { onStateChange(state.copy(theme = theme)) }
                                            .padding(4.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(54.dp)
                                                .clip(CircleShape)
                                                .background(theme.previewColor)
                                                .border(
                                                    width = if (isSelected) 3.dp else 1.dp,
                                                    color = if (isSelected) GoldPrimary else Color.DarkGray,
                                                    shape = CircleShape
                                                )
                                        )
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Text(
                                            text = theme.name,
                                            fontSize = 11.sp,
                                            color = if (isSelected) GoldPrimary else Color.Gray,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // 3. TAB: MOTİF & DESEN
                    2 -> {
                        Column {
                            Text(
                                text = "Geleneksel Motif & Süslemeler",
                                style = MaterialTheme.typography.titleMedium,
                                color = GoldPrimary
                            )
                            Spacer(modifier = Modifier.height(10.dp))

                            MotifType.values().forEach { motif ->
                                val isSelected = state.motif == motif
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                        .clickable { onStateChange(state.copy(motif = motif)) },
                                    colors = CardDefaults.cardColors(
                                        containerColor = if (isSelected) MaterialTheme.colorScheme.surfaceVariant else Color(0xFF161E2E)
                                    ),
                                    shape = RoundedCornerShape(12.dp),
                                    border = if (isSelected) androidx.compose.foundation.BorderStroke(1.5.dp, GoldPrimary) else null
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(12.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(36.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(if (isSelected) GoldPrimary else Color.DarkGray),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                Icons.Default.Yard,
                                                contentDescription = null,
                                                tint = if (isSelected) Color.Black else Color.White,
                                                modifier = Modifier.size(20.dp)
                                            )
                                        }

                                        Spacer(modifier = Modifier.width(12.dp))

                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = motif.title,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 14.sp,
                                                color = if (isSelected) GoldPrimary else Color.White
                                            )
                                            Text(
                                                text = motif.description,
                                                fontSize = 11.sp,
                                                color = Color.LightGray,
                                                lineHeight = 14.sp
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // 4. TAB: YAZI TİPİ & BOYUT
                    3 -> {
                        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                            // Font Size Slider
                            Column {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("Yazı Boyutu", fontSize = 13.sp, color = Color.LightGray)
                                    Text("${state.fontSizeSp.toInt()} sp", fontSize = 13.sp, color = GoldPrimary, fontWeight = FontWeight.Bold)
                                }
                                Slider(
                                    value = state.fontSizeSp,
                                    onValueChange = { onStateChange(state.copy(fontSizeSp = it)) },
                                    valueRange = 14f..30f,
                                    colors = SliderDefaults.colors(
                                        thumbColor = GoldPrimary,
                                        activeTrackColor = GoldPrimary
                                    )
                                )
                            }

                            // Alignment
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                IconButton(
                                    onClick = { onStateChange(state.copy(textAlign = TextAlign.Start)) },
                                    modifier = Modifier.background(
                                        if (state.textAlign == TextAlign.Start) GoldPrimary.copy(alpha = 0.2f) else Color.Transparent,
                                        CircleShape
                                    )
                                ) {
                                    Icon(Icons.Default.FormatAlignLeft, contentDescription = null, tint = GoldPrimary)
                                }

                                IconButton(
                                    onClick = { onStateChange(state.copy(textAlign = TextAlign.Center)) },
                                    modifier = Modifier.background(
                                        if (state.textAlign == TextAlign.Center) GoldPrimary.copy(alpha = 0.2f) else Color.Transparent,
                                        CircleShape
                                    )
                                ) {
                                    Icon(Icons.Default.FormatAlignCenter, contentDescription = null, tint = GoldPrimary)
                                }

                                IconButton(
                                    onClick = { onStateChange(state.copy(textAlign = TextAlign.End)) },
                                    modifier = Modifier.background(
                                        if (state.textAlign == TextAlign.End) GoldPrimary.copy(alpha = 0.2f) else Color.Transparent,
                                        CircleShape
                                    )
                                ) {
                                    Icon(Icons.Default.FormatAlignRight, contentDescription = null, tint = GoldPrimary)
                                }
                            }

                            // Font Families
                            Text("Yazı Stili", style = MaterialTheme.typography.titleMedium, color = GoldPrimary)

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                CardFont.values().forEach { font ->
                                    FilterChip(
                                        selected = state.font == font,
                                        onClick = { onStateChange(state.copy(font = font)) },
                                        label = { Text(font.displayName, fontSize = 12.sp) },
                                        colors = FilterChipDefaults.filterChipColors(
                                            selectedContainerColor = GoldPrimary,
                                            selectedLabelColor = Color.Black
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}
