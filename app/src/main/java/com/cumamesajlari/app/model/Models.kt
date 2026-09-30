package com.cumamesajlari.app.model

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.cumamesajlari.app.R

enum class MessageCategory(val displayName: String, val iconName: String) {
    ALL("Tümü", "All"),
    KURAN_AYETLERI("Kur'an-ı Kerim", "Book"),
    HADISLER("Hadis-i Şerifler", "Hadith"),
    RISALE_I_NUR("Risale-i Nur'dan", "Light"),
    DUALI("Dualı Mesajlar", "Hands"),
    KISA_OZ("Kısa & Anlamlı", "Sparkle"),
    SAMIMI("Dost & Akraba", "Heart"),
    KANDIL_BAYRAM("Kandil & Özel", "Moon")
}

data class MessageItem(
    val id: String,
    val text: String,
    val category: MessageCategory,
    val source: String? = null,
    val isFavorite: Boolean = false
)

enum class MotifType(val title: String, val description: String) {
    SELJUK_STAR("Selçuklu Yıldızı", "8 köşeli geometrik Selçuklu motifi ve yıldız geçişleri"),
    TEZHIP_CORNERS("Klasik Tezhip", "Zarif altın varaklı köşe rumi süslemeleri"),
    MOSQUE_SILHOUETTE("Cami & Hilal", "Görkemli kubbe, minareler ve parlayan hilal"),
    HANGING_LANTERNS("Nurlu Kandiller", "Zarif asma kandiller ve ışıltılı parıltılar"),
    OTTOMAN_FLORAL("Osmanlı Lalesi", "Klasik hat ve lale-karanfil bordür bezemesi"),
    GOLDEN_FRAME("Altın Varak Çerçeve", "Lüks çift hatlı altın bordür ve köşe taşları"),
    ARCH_MIHRAB("Mihrap & Kemer", "Mukarnas ve kemer hatları süslemesi")
}

data class CardTheme(
    val id: String,
    val name: String,
    val backgroundBrush: Brush,
    val textColor: Color,
    val accentColor: Color,
    val motifColor: Color,
    val signatureColor: Color,
    val previewColor: Color,
    val bgImageResId: Int? = null
)

object CardThemes {
    val EmeraldGold = CardTheme(
        id = "emerald_gold",
        name = "Zümrüt & Altın",
        backgroundBrush = Brush.verticalGradient(
            colors = listOf(Color(0xFF022B1E), Color(0xFF004D36), Color(0xFF011C13))
        ),
        textColor = Color(0xFFFFF7E6),
        accentColor = Color(0xFFFFD700),
        motifColor = Color(0xFFE5C158),
        signatureColor = Color(0xFFFFE082),
        previewColor = Color(0xFF004D36)
    )

    val NightMosqueArt = CardTheme(
        id = "night_mosque_art",
        name = "Cami & Gece Sanatı",
        backgroundBrush = Brush.verticalGradient(
            colors = listOf(Color(0xFF08121E), Color(0xFF10253E), Color(0xFF050A10))
        ),
        textColor = Color(0xFFFFFFFF),
        accentColor = Color(0xFFFFD54F),
        motifColor = Color(0xFFFFE082),
        signatureColor = Color(0xFFFFECB3),
        previewColor = Color(0xFF1B3B6F),
        bgImageResId = R.drawable.bg_night_mosque
    )

    val GoldTezhipArt = CardTheme(
        id = "gold_tezhip_art",
        name = "Tezhip & Altın Varak",
        backgroundBrush = Brush.verticalGradient(
            colors = listOf(Color(0xFF012318), Color(0xFF013B29), Color(0xFF011A11))
        ),
        textColor = Color(0xFFFFFDF5),
        accentColor = Color(0xFFFFD700),
        motifColor = Color(0xFFD4AF37),
        signatureColor = Color(0xFFFFE082),
        previewColor = Color(0xFF0A4D34),
        bgImageResId = R.drawable.bg_gold_tezhip
    )

    val SpiritualLanternArt = CardTheme(
        id = "spiritual_lantern_art",
        name = "Nurlu Fenerler",
        backgroundBrush = Brush.verticalGradient(
            colors = listOf(Color(0xFF071B2C), Color(0xFF0A3150), Color(0xFF040F18))
        ),
        textColor = Color(0xFFFFFFFF),
        accentColor = Color(0xFFFFCA28),
        motifColor = Color(0xFFFFE082),
        signatureColor = Color(0xFFFFECB3),
        previewColor = Color(0xFF0E3A5F),
        bgImageResId = R.drawable.bg_spiritual_lantern
    )

    val MidnightSapphire = CardTheme(
        id = "midnight_sapphire",
        name = "Gece Safiri",
        backgroundBrush = Brush.verticalGradient(
            colors = listOf(Color(0xFF061121), Color(0xFF0E2442), Color(0xFF030A14))
        ),
        textColor = Color(0xFFF0F6FC),
        accentColor = Color(0xFF64B5F6),
        motifColor = Color(0xFF90CAF9),
        signatureColor = Color(0xFFBBDEFB),
        previewColor = Color(0xFF0E2442)
    )

    val RoyalRuby = CardTheme(
        id = "royal_ruby",
        name = "Kadife Bordo",
        backgroundBrush = Brush.verticalGradient(
            colors = listOf(Color(0xFF33040E), Color(0xFF5E0B1B), Color(0xFF240209))
        ),
        textColor = Color(0xFFFFF0F2),
        accentColor = Color(0xFFFFD180),
        motifColor = Color(0xFFE6AF2E),
        signatureColor = Color(0xFFFFE0B2),
        previewColor = Color(0xFF5E0B1B)
    )

    val ImperialBlackGold = CardTheme(
        id = "imperial_black_gold",
        name = "Asil Siyah & Varak",
        backgroundBrush = Brush.verticalGradient(
            colors = listOf(Color(0xFF141414), Color(0xFF1F1F1F), Color(0xFF0A0A0A))
        ),
        textColor = Color(0xFFFFFFFF),
        accentColor = Color(0xFFFFD700),
        motifColor = Color(0xFFD4AF37),
        signatureColor = Color(0xFFFFECB3),
        previewColor = Color(0xFF1A1A1A)
    )

    val TurquoisePearl = CardTheme(
        id = "turquoise_pearl",
        name = "Firuze Zarafeti",
        backgroundBrush = Brush.verticalGradient(
            colors = listOf(Color(0xFF00363A), Color(0xFF006064), Color(0xFF002529))
        ),
        textColor = Color(0xFFE0F7FA),
        accentColor = Color(0xFFFFD54F),
        motifColor = Color(0xFF80DEEA),
        signatureColor = Color(0xFFFFF9C4),
        previewColor = Color(0xFF006064)
    )

    val WarmParchment = CardTheme(
        id = "warm_parchment",
        name = "Antik Parşömen",
        backgroundBrush = Brush.verticalGradient(
            colors = listOf(Color(0xFF2C1B10), Color(0xFF422817), Color(0xFF1F120A))
        ),
        textColor = Color(0xFFFFF8E7),
        accentColor = Color(0xFFFFCC80),
        motifColor = Color(0xFFD7A15C),
        signatureColor = Color(0xFFFFE0B2),
        previewColor = Color(0xFF422817)
    )

    val all = listOf(
        EmeraldGold,
        GoldTezhipArt,
        NightMosqueArt,
        SpiritualLanternArt,
        MidnightSapphire,
        RoyalRuby,
        ImperialBlackGold,
        TurquoisePearl,
        WarmParchment
    )
}

enum class CardFont(
    val displayName: String,
    val fontFamily: FontFamily,
    val fontWeight: FontWeight,
    val fontStyle: FontStyle = FontStyle.Normal
) {
    SERIF_ELEGANT("Klasik Serif", FontFamily.Serif, FontWeight.SemiBold),
    SERIF_ITALIC("Zarif İtalik", FontFamily.Serif, FontWeight.Normal, FontStyle.Italic),
    SANS_MODERN("Modern Duru", FontFamily.SansSerif, FontWeight.Medium),
    SANS_BOLD("Belirgin Kalın", FontFamily.SansSerif, FontWeight.Bold),
    CURSIVE_STYLE("Sanatsal Akıcı", FontFamily.Cursive, FontWeight.Normal)
}

data class CardCustomizationState(
    val messageText: String = "Cumanız Mübarek Olsun.\nRabbim dualarınızı kabul, kalbinizi huzurla doldursun.",
    val headerTitle: String = "HAYIRLI CUMALAR",
    val signature: String = "Dualarda Buluşmak Üzere",
    val theme: CardTheme = CardThemes.EmeraldGold,
    val motif: MotifType = MotifType.SELJUK_STAR,
    val font: CardFont = CardFont.SERIF_ELEGANT,
    val fontSizeSp: Float = 20f,
    val textAlign: TextAlign = TextAlign.Center,
    val showHeader: Boolean = true,
    val showSignature: Boolean = true,
    val showMotif: Boolean = true,
    val showBorder: Boolean = true,
    val decorativeIntensity: Float = 1.0f
)
