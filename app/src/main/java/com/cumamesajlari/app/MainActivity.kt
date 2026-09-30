package com.cumamesajlari.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CorporateFare
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cumamesajlari.app.model.CardCustomizationState
import com.cumamesajlari.app.ui.screens.CardStudioScreen
import com.cumamesajlari.app.ui.screens.FavoritesScreen
import com.cumamesajlari.app.ui.screens.FridayGuideScreen
import com.cumamesajlari.app.ui.screens.MessageCatalogScreen
import com.cumamesajlari.app.ui.theme.CumaMesajlariTheme
import com.cumamesajlari.app.ui.theme.GoldPrimary

sealed class Screen(val title: String, val icon: ImageVector) {
    object Studio : Screen("Kart Tasarla", Icons.Default.AutoAwesome)
    object Catalog : Screen("Hazır Mesajlar", Icons.AutoMirrored.Filled.Message)
    object Favorites : Screen("Favorilerim", Icons.Default.Favorite)
    object Guide : Screen("Cuma Rehberi", Icons.AutoMirrored.Filled.MenuBook)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CumaMesajlariTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainAppContent()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppContent() {
    var selectedScreenIndex by remember { mutableIntStateOf(0) }
    var cardState by remember { mutableStateOf(CardCustomizationState()) }
    var showAboutDialog by remember { mutableStateOf(false) }
    var favoriteIds by remember {
        mutableStateOf(
            setOf("ayet_1", "dua_1", "kisa_1", "samimi_1")
        )
    }

    val screens = listOf(
        Screen.Studio,
        Screen.Catalog,
        Screen.Favorites,
        Screen.Guide
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "🕌 Cuma Mesajları",
                            style = MaterialTheme.typography.titleMedium,
                            color = GoldPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { showAboutDialog = true }) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Hakkında",
                            tint = GoldPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                screens.forEachIndexed { index, screen ->
                    val isSelected = selectedScreenIndex == index
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { selectedScreenIndex = index },
                        icon = {
                            Icon(
                                screen.icon,
                                contentDescription = screen.title,
                                tint = if (isSelected) GoldPrimary else Color.Gray
                            )
                        },
                        label = {
                            Text(
                                screen.title,
                                fontSize = 11.sp,
                                color = if (isSelected) GoldPrimary else Color.Gray
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = GoldPrimary.copy(alpha = 0.15f)
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            when (selectedScreenIndex) {
                0 -> CardStudioScreen(
                    state = cardState,
                    onStateChange = { cardState = it },
                    onNavigateToCatalog = { selectedScreenIndex = 1 }
                )
                1 -> MessageCatalogScreen(
                    onSelectForCard = { text, source ->
                        cardState = cardState.copy(
                            messageText = text,
                            signature = source ?: cardState.signature
                        )
                        selectedScreenIndex = 0
                    },
                    favoriteIds = favoriteIds,
                    onToggleFavorite = { id ->
                        favoriteIds = if (favoriteIds.contains(id)) {
                            favoriteIds - id
                        } else {
                            favoriteIds + id
                        }
                    }
                )
                2 -> FavoritesScreen(
                    favoriteIds = favoriteIds,
                    onToggleFavorite = { id ->
                        favoriteIds = if (favoriteIds.contains(id)) {
                            favoriteIds - id
                        } else {
                            favoriteIds + id
                        }
                    },
                    onSelectForCard = { text, source ->
                        cardState = cardState.copy(
                            messageText = text,
                            signature = source ?: cardState.signature
                        )
                        selectedScreenIndex = 0
                    }
                )
                3 -> FridayGuideScreen(
                    onShowAbout = { showAboutDialog = true }
                )
            }
        }
    }

    if (showAboutDialog) {
        AboutAppDialog(onDismiss = { showAboutDialog = false })
    }
}

@Composable
fun AboutAppDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = null,
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(GoldPrimary.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.CorporateFare,
                        contentDescription = null,
                        tint = GoldPrimary,
                        modifier = Modifier.size(36.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Cuma Mesajları & Kart Tasarım",
                    style = MaterialTheme.typography.titleMedium,
                    color = GoldPrimary,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Sürüm 1.0.0",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
                )

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Geliştirici & Tasarım",
                            fontSize = 11.sp,
                            color = Color.LightGray
                        )
                        Text(
                            text = "Yazgan Bilişim",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = GoldPrimary,
                            fontFamily = FontFamily.Serif
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Bu uygulama, sevdiklerinizle en güzel Cuma ve tebrik mesajlarını geleneksel İslami motifler, tezhip ve zarif desenlerle süsleyip görsel olarak paylaşmanız için Yazgan Bilişim tarafından özenle tasarlanmıştır.",
                    fontSize = 13.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "© 2026 Yazgan Bilişim. Tüm hakları saklıdır.",
                    fontSize = 11.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GoldPrimary,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Tamam", fontWeight = FontWeight.Bold)
            }
        },
        containerColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(20.dp)
    )
}
