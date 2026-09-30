package com.cumamesajlari.app.ui.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cumamesajlari.app.data.MessageRepository
import com.cumamesajlari.app.model.MessageCategory
import com.cumamesajlari.app.model.MessageItem
import com.cumamesajlari.app.ui.theme.GoldPrimary

@Composable
fun MessageCatalogScreen(
    onSelectForCard: (String, String?) -> Unit,
    favoriteIds: Set<String>,
    onToggleFavorite: (String) -> Unit
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    var selectedCategory by remember { mutableStateOf(MessageCategory.ALL) }
    var searchQuery by remember { mutableStateOf("") }

    val filteredMessages = remember(selectedCategory, searchQuery, favoriteIds) {
        MessageRepository.getByCategory(selectedCategory).filter {
            if (searchQuery.isBlank()) true
            else it.text.contains(searchQuery, ignoreCase = true) ||
                    (it.source?.contains(searchQuery, ignoreCase = true) == true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        // Top Title
        Text(
            text = "Hazır Cuma Mesajları",
            style = MaterialTheme.typography.titleLarge,
            color = GoldPrimary
        )
        Text(
            text = "Kategoriye göre seçin, düzenleyin veya paylaşın",
            style = MaterialTheme.typography.bodySmall,
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Mesaj veya ayet ara...", fontSize = 13.sp) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = GoldPrimary) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GoldPrimary,
                unfocusedBorderColor = Color.DarkGray
            ),
            shape = RoundedCornerShape(14.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Categories Scroll
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MessageCategory.values().forEach { category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = { selectedCategory = category },
                    label = { Text(category.displayName, fontSize = 12.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = GoldPrimary,
                        selectedLabelColor = Color.Black,
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        labelColor = Color.LightGray
                    ),
                    shape = RoundedCornerShape(10.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Messages List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredMessages, key = { it.id }) { message ->
                val isFav = favoriteIds.contains(message.id)
                MessageCardItem(
                    message = message,
                    isFavorite = isFav,
                    onToggleFavorite = { onToggleFavorite(message.id) },
                    onSelectForCard = { onSelectForCard(message.text, message.source) },
                    onShareText = {
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, "${message.text}\n\n${message.source ?: "Hayırlı Cumalar"}")
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "Cuma Mesajını Paylaş"))
                    },
                    onCopyText = {
                        clipboardManager.setText(AnnotatedString("${message.text}\n\n${message.source ?: "Hayırlı Cumalar"}"))
                        Toast.makeText(context, "Mesaj kopyalandı 📋", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}

@Composable
fun MessageCardItem(
    message: MessageItem,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onSelectForCard: () -> Unit,
    onShareText: () -> Unit,
    onCopyText: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Category Badge & Favorite
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(GoldPrimary.copy(alpha = 0.15f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = message.category.displayName,
                        color = GoldPrimary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                IconButton(onClick = onToggleFavorite, modifier = Modifier.size(32.dp)) {
                    Icon(
                        if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favori",
                        tint = if (isFavorite) Color.Red else Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Text
            Text(
                text = message.text,
                color = Color.White,
                fontSize = 15.sp,
                fontFamily = FontFamily.Serif,
                lineHeight = 22.sp
            )

            if (message.source != null) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "— ${message.source}",
                    color = GoldPrimary.copy(alpha = 0.8f),
                    fontSize = 12.sp,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onSelectForCard,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GoldPrimary,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Kart Yap", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }

                IconButton(
                    onClick = onShareText,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Icon(Icons.Default.Share, contentDescription = "Paylaş", tint = GoldPrimary, modifier = Modifier.size(18.dp))
                }

                IconButton(
                    onClick = onCopyText,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Icon(Icons.Default.ContentCopy, contentDescription = "Kopyala", tint = GoldPrimary, modifier = Modifier.size(18.dp))
                }
            }
        }
    }
}
