package com.cumamesajlari.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CorporateFare
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cumamesajlari.app.ui.theme.GoldPrimary

@Composable
fun FridayGuideScreen(
    onShowAbout: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.AutoMirrored.Filled.MenuBook, contentDescription = null, tint = GoldPrimary, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "Cuma Gününün Faziletleri",
                    style = MaterialTheme.typography.titleLarge,
                    color = GoldPrimary
                )
                Text(
                    text = "Cuma sünnetleri, icabet saati ve faziletli ameller",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.LightGray
                )
            }
        }

        // Section 1: İcabet Saati (Dua Vakti)
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = GoldPrimary, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "İcabet Saati (Duaların Kabul Vakti)",
                        fontWeight = FontWeight.Bold,
                        color = GoldPrimary,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Peygamber Efendimiz (s.a.v.) buyurmuştur:\n\"Cuma gününde öyle bir vakit vardır ki, müslüman bir kul o vakte rastlayıp Allah'tan hayırlı bir şey dilerse, Allah ona dilediğini mutlaka verir.\"",
                    fontFamily = FontFamily.Serif,
                    fontSize = 14.sp,
                    color = Color.White,
                    lineHeight = 20.sp
                )
            }
        }

        // Section 2: Cuma Gününün Sünnetleri & Edepleri
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Cuma Gününün Sünnet ve Adabı",
                    fontWeight = FontWeight.Bold,
                    color = GoldPrimary,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(12.dp))

                val sunnahs = listOf(
                    "Gusül abdesti almak ve güzel kokular sürünmek",
                    "Temiz ve özenli elbiseler giymek",
                    "Cuma namazına erken gitmek ve hutbeyi can kulağıyla dinlemek",
                    "Peygamber Efendimiz'e (s.a.v.) bolca salavat-ı şerife getirmek",
                    "Kehf Sûresi'ni okumak veya dinlemek",
                    "Akraba, dost ve kardeşlerimize hayır dualarında bulunmak, tebrikleşmek",
                    "İhtiyaç sahiplerine sadaka vermek ve gönülleri sevindirmek"
                )

                sunnahs.forEach { sunnah ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = GoldPrimary,
                            modifier = Modifier
                                .size(18.dp)
                                .padding(top = 2.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = sunnah,
                            fontSize = 13.sp,
                            color = Color.LightGray,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }

        // Section 3: Salavat-ı Şerife
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Bookmark, contentDescription = null, tint = GoldPrimary, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Salavat-ı Şerife",
                        fontWeight = FontWeight.Bold,
                        color = GoldPrimary,
                        fontSize = 15.sp
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "\"Allâhümme salli alâ seyyidinâ Muhammedin ve alâ âli seyyidinâ Muhammed\"",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color(0xFFFFD54F),
                    lineHeight = 20.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Anlamı: Allah'ım! Efendimiz Muhammed'e ve onun âline salât ve selâm eyle.",
                    fontSize = 12.sp,
                    color = Color.LightGray
                )
            }
        }

        // Section 4: Hakkında & Yazgan Bilişim
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.CorporateFare, contentDescription = null, tint = GoldPrimary, modifier = Modifier.size(22.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Uygulama Hakkında",
                        fontWeight = FontWeight.Bold,
                        color = GoldPrimary,
                        fontSize = 15.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Geliştirici: Yazgan Bilişim",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.White
                )
                Text(
                    text = "Cuma Mesajları & Motifli Kart Tasarım Uygulaması",
                    fontSize = 12.sp,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = onShowAbout,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GoldPrimary,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Default.Info, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Hakkında Detayları", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
