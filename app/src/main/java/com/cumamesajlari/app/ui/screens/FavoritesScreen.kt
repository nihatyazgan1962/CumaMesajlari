package com.cumamesajlari.app.ui.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cumamesajlari.app.data.MessageRepository
import com.cumamesajlari.app.ui.theme.GoldPrimary

@Composable
fun FavoritesScreen(
    favoriteIds: Set<String>,
    onToggleFavorite: (String) -> Unit,
    onSelectForCard: (String, String?) -> Unit
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    val favMessages = MessageRepository.messages.filter { favoriteIds.contains(it.id) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Favorite, contentDescription = null, tint = Color.Red, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.size(8.dp))
            Column {
                Text(
                    text = "Favori Mesajlarım",
                    style = MaterialTheme.typography.titleLarge,
                    color = GoldPrimary
                )
                Text(
                    text = "${favMessages.size} adet kayıtlı mesaj",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.LightGray
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (favMessages.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            Icons.Default.SentimentDissatisfied,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Henüz Favori Eklenmedi",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Katalog ekranındaki kalp simgelerine dokunarak beğendiğiniz mesajları buraya kaydedebilirsiniz.",
                            color = Color.LightGray,
                            fontSize = 13.sp,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(favMessages, key = { it.id }) { message ->
                    MessageCardItem(
                        message = message,
                        isFavorite = true,
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
}
