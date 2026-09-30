package com.cumamesajlari.app.graphics

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.RadialGradient
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.widget.Toast
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.core.content.FileProvider
import com.cumamesajlari.app.model.CardCustomizationState
import com.cumamesajlari.app.model.CardFont
import com.cumamesajlari.app.model.MotifType
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

object BitmapExporter {

    private const val EXPORT_WIDTH = 1080
    private const val EXPORT_HEIGHT = 1350

    fun generateCardBitmap(context: Context, state: CardCustomizationState): Bitmap {
        val bitmap = Bitmap.createBitmap(EXPORT_WIDTH, EXPORT_HEIGHT, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        val w = EXPORT_WIDTH.toFloat()
        val h = EXPORT_HEIGHT.toFloat()

        // 1. Background Art or Gradient
        if (state.theme.bgImageResId != null) {
            try {
                val bgBitmap = BitmapFactory.decodeResource(context.resources, state.theme.bgImageResId)
                if (bgBitmap != null) {
                    val srcRect = Rect(0, 0, bgBitmap.width, bgBitmap.height)
                    val dstRect = Rect(0, 0, EXPORT_WIDTH, EXPORT_HEIGHT)
                    canvas.drawBitmap(bgBitmap, srcRect, dstRect, Paint(Paint.FILTER_BITMAP_FLAG))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            // Dark spiritual gradient overlay for high contrast text readability
            val overlayPaint = Paint().apply {
                isAntiAlias = true
                shader = LinearGradient(
                    0f, 0f, 0f, h,
                    intArrayOf(0xAA000000.toInt(), 0x77000000.toInt(), 0xCC000000.toInt()),
                    floatArrayOf(0.0f, 0.5f, 1.0f),
                    Shader.TileMode.CLAMP
                )
            }
            canvas.drawRect(0f, 0f, w, h, overlayPaint)
        } else {
            val bgPaint = Paint().apply {
                isAntiAlias = true
                shader = LinearGradient(
                    0f, 0f, 0f, h,
                    intArrayOf(
                        state.theme.previewColor.toArgb(),
                        state.theme.previewColor.toArgb(),
                        0xFF050505.toInt()
                    ),
                    floatArrayOf(0.0f, 0.4f, 1.0f),
                    Shader.TileMode.CLAMP
                )
            }
            canvas.drawRect(0f, 0f, w, h, bgPaint)

            // Radial lighting center glow
            val glowPaint = Paint().apply {
                isAntiAlias = true
                shader = RadialGradient(
                    w / 2, h * 0.45f, w * 0.7f,
                    state.theme.accentColor.toArgb() and 0x22FFFFFF,
                    0x00000000,
                    Shader.TileMode.CLAMP
                )
            }
            canvas.drawCircle(w / 2, h * 0.45f, w * 0.7f, glowPaint)
        }

        val motifColorInt = state.theme.motifColor.toArgb()
        val accentColorInt = state.theme.accentColor.toArgb()
        val textColorInt = state.theme.textColor.toArgb()
        val sigColorInt = state.theme.signatureColor.toArgb()

        // 2. Draw Motifs & Borders on Canvas
        if (state.showMotif || state.showBorder) {
            drawCanvasMotif(canvas, state.motif, motifColorInt, accentColorInt, w, h, state.decorativeIntensity)
        }

        // 3. Header Text ("HAYIRLI CUMALAR")
        var currentY = 160f
        if (state.showHeader && state.headerTitle.isNotBlank()) {
            val headerPaint = TextPaint().apply {
                isAntiAlias = true
                color = accentColorInt
                textSize = 48f
                typeface = Typeface.create(Typeface.SERIF, Typeface.BOLD)
                textAlign = Paint.Align.CENTER
                setShadowLayer(8f, 0f, 4f, 0xAA000000.toInt())
            }
            canvas.drawText(state.headerTitle.uppercase(), w / 2, currentY, headerPaint)

            // Header underline ornament
            val linePaint = Paint().apply {
                isAntiAlias = true
                color = accentColorInt
                strokeWidth = 2f
                style = Paint.Style.STROKE
            }
            canvas.drawLine(w / 2 - 140f, currentY + 25f, w / 2 + 140f, currentY + 25f, linePaint)
            canvas.drawCircle(w / 2, currentY + 25f, 5f, Paint().apply {
                isAntiAlias = true
                color = accentColorInt
                style = Paint.Style.FILL
            })

            currentY += 120f
        } else {
            currentY += 80f
        }

        // 4. Main Body Text
        val contentPadding = 120
        val textWidth = EXPORT_WIDTH - (contentPadding * 2)

        val bodyTypeface = when (state.font) {
            CardFont.SERIF_ELEGANT -> Typeface.create(Typeface.SERIF, Typeface.NORMAL)
            CardFont.SERIF_ITALIC -> Typeface.create(Typeface.SERIF, Typeface.ITALIC)
            CardFont.SANS_MODERN -> Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)
            CardFont.SANS_BOLD -> Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
            CardFont.CURSIVE_STYLE -> Typeface.create(Typeface.SERIF, Typeface.ITALIC)
        }

        val bodyPaint = TextPaint().apply {
            isAntiAlias = true
            color = textColorInt
            textSize = state.fontSizeSp * 2.2f
            typeface = bodyTypeface
            setShadowLayer(6f, 0f, 3f, 0xCC000000.toInt())
        }

        val layoutAlignment = when (state.textAlign) {
            TextAlign.Left, TextAlign.Start -> Layout.Alignment.ALIGN_NORMAL
            TextAlign.Right, TextAlign.End -> Layout.Alignment.ALIGN_OPPOSITE
            else -> Layout.Alignment.ALIGN_CENTER
        }

        val staticLayout = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StaticLayout.Builder.obtain(state.messageText, 0, state.messageText.length, bodyPaint, textWidth)
                .setAlignment(layoutAlignment)
                .setLineSpacing(12f, 1.2f)
                .setIncludePad(true)
                .build()
        } else {
            @Suppress("DEPRECATION")
            StaticLayout(
                state.messageText,
                bodyPaint,
                textWidth,
                layoutAlignment,
                1.2f,
                12f,
                true
            )
        }

        // Center vertically in available middle space
        val availableHeight = (h - 260f) - currentY
        val textY = currentY + (availableHeight - staticLayout.height) / 2f

        canvas.save()
        canvas.translate(contentPadding.toFloat(), textY.coerceAtLeast(currentY))
        staticLayout.draw(canvas)
        canvas.restore()

        // 5. Signature ("Dualarda Buluşmak Üzere")
        if (state.showSignature && state.signature.isNotBlank()) {
            val sigPaint = TextPaint().apply {
                isAntiAlias = true
                color = sigColorInt
                textSize = 34f
                typeface = Typeface.create(Typeface.SERIF, Typeface.ITALIC)
                textAlign = Paint.Align.CENTER
                setShadowLayer(6f, 0f, 3f, 0x88000000.toInt())
            }
            canvas.drawText(state.signature, w / 2, h - 140f, sigPaint)
        }

        return bitmap
    }

    private fun drawCanvasMotif(
        canvas: Canvas,
        motif: MotifType,
        color: Int,
        accent: Int,
        w: Float,
        h: Float,
        intensity: Float
    ) {
        val strokePaint = Paint().apply {
            isAntiAlias = true
            this.color = color
            style = Paint.Style.STROKE
            strokeWidth = 3f
            alpha = (200 * intensity).toInt().coerceIn(0, 255)
        }

        val accentPaint = Paint().apply {
            isAntiAlias = true
            this.color = accent
            style = Paint.Style.FILL
            alpha = (180 * intensity).toInt().coerceIn(0, 255)
        }

        // Outer ornate frame
        val m = 40f
        canvas.drawRect(m, m, w - m, h - m, strokePaint)
        canvas.drawRect(m + 15f, m + 15f, w - m - 15f, h - m - 15f, Paint(strokePaint).apply {
            strokeWidth = 1.5f
            alpha = (120 * intensity).toInt().coerceIn(0, 255)
        })

        when (motif) {
            MotifType.SELJUK_STAR -> {
                drawCanvasEightStar(canvas, w / 2, 85f, 40f, 22f, strokePaint, accentPaint)
                drawCanvasEightStar(canvas, w / 2, h - 85f, 35f, 18f, strokePaint, accentPaint)
                drawCanvasEightStar(canvas, 75f, 75f, 25f, 14f, strokePaint, accentPaint)
                drawCanvasEightStar(canvas, w - 75f, 75f, 25f, 14f, strokePaint, accentPaint)
                drawCanvasEightStar(canvas, 75f, h - 75f, 25f, 14f, strokePaint, accentPaint)
                drawCanvasEightStar(canvas, w - 75f, h - 75f, 25f, 14f, strokePaint, accentPaint)
            }
            MotifType.MOSQUE_SILHOUETTE -> {
                // Crescent top
                canvas.drawCircle(w / 2, 75f, 28f, strokePaint)
                canvas.drawCircle(w / 2 + 10f, 70f, 6f, accentPaint)
            }
            MotifType.HANGING_LANTERNS -> {
                canvas.drawLine(w * 0.3f, 40f, w * 0.3f, 110f, strokePaint)
                canvas.drawCircle(w * 0.3f, 125f, 18f, accentPaint)

                canvas.drawLine(w * 0.5f, 40f, w * 0.5f, 130f, strokePaint)
                canvas.drawCircle(w * 0.5f, 150f, 24f, accentPaint)

                canvas.drawLine(w * 0.7f, 40f, w * 0.7f, 110f, strokePaint)
                canvas.drawCircle(w * 0.7f, 125f, 18f, accentPaint)
            }
            else -> {
                // Corner rosettes
                val r = 16f
                canvas.drawCircle(m, m, r, accentPaint)
                canvas.drawCircle(w - m, m, r, accentPaint)
                canvas.drawCircle(m, h - m, r, accentPaint)
                canvas.drawCircle(w - m, h - m, r, accentPaint)
            }
        }
    }

    private fun drawCanvasEightStar(
        canvas: Canvas,
        cx: Float,
        cy: Float,
        outerR: Float,
        innerR: Float,
        strokePaint: Paint,
        fillPaint: Paint
    ) {
        val path = android.graphics.Path()
        val points = 16
        val step = (2 * PI / points).toFloat()
        for (i in 0 until points) {
            val r = if (i % 2 == 0) outerR else innerR
            val angle = i * step - (PI / 2).toFloat()
            val x = cx + r * cos(angle)
            val y = cy + r * sin(angle)
            if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }
        path.close()
        canvas.drawPath(path, fillPaint)
        canvas.drawPath(path, strokePaint)
    }

    fun shareCard(context: Context, state: CardCustomizationState) {
        try {
            val bitmap = generateCardBitmap(context, state)
            val cachePath = File(context.cacheDir, "cards")
            cachePath.mkdirs()
            val file = File(cachePath, "cuma_mesaji_${System.currentTimeMillis()}.png")
            val stream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.flush()
            stream.close()

            val contentUri: Uri = FileProvider.getUriForFile(
                context,
                "com.cumamesajlari.app.fileprovider",
                file
            )

            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "image/png"
                putExtra(Intent.EXTRA_STREAM, contentUri)
                putExtra(Intent.EXTRA_TEXT, "${state.headerTitle}\n\n${state.messageText}\n\n${state.signature}")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            context.startActivity(Intent.createChooser(shareIntent, "Cuma Mesajını Paylaş"))
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Paylaşım hatası: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
        }
    }

    fun saveToGallery(context: Context, state: CardCustomizationState) {
        try {
            val bitmap = generateCardBitmap(context, state)
            val filename = "Cuma_Karti_${System.currentTimeMillis()}.png"
            var outputStream: OutputStream? = null

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/CumaMesajlari")
                }
                val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                if (uri != null) {
                    outputStream = context.contentResolver.openOutputStream(uri)
                }
            } else {
                val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                val appDir = File(imagesDir, "CumaMesajlari")
                if (!appDir.exists()) appDir.mkdirs()
                val imageFile = File(appDir, filename)
                outputStream = FileOutputStream(imageFile)
            }

            outputStream?.use {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
                Toast.makeText(context, "Kart galeriye başarıyla kaydedildi ✨", Toast.LENGTH_LONG).show()
            } ?: run {
                Toast.makeText(context, "Kayıt dosyası oluşturulamadı", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Kaydetme hatası: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
        }
    }
}
