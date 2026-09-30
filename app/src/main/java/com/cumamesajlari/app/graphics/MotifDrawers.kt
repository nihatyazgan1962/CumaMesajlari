package com.cumamesajlari.app.graphics

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import com.cumamesajlari.app.model.MotifType
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

object MotifDrawers {

    fun drawMotif(
        scope: DrawScope,
        motifType: MotifType,
        color: Color,
        accentColor: Color,
        intensity: Float = 1.0f
    ) {
        when (motifType) {
            MotifType.SELJUK_STAR -> drawSeljukStarPattern(scope, color, accentColor, intensity)
            MotifType.TEZHIP_CORNERS -> drawTezhipCornerMotifs(scope, color, accentColor, intensity)
            MotifType.MOSQUE_SILHOUETTE -> drawMosqueSilhouettePattern(scope, color, accentColor, intensity)
            MotifType.HANGING_LANTERNS -> drawHangingLanternsPattern(scope, color, accentColor, intensity)
            MotifType.OTTOMAN_FLORAL -> drawOttomanFloralPattern(scope, color, accentColor, intensity)
            MotifType.GOLDEN_FRAME -> drawGoldenFoilFrame(scope, color, accentColor, intensity)
            MotifType.ARCH_MIHRAB -> drawArchMihrabPattern(scope, color, accentColor, intensity)
        }
    }

    /**
     * Selçuklu 8 Köşeli Yıldızı ve Geometrik Kesişimler
     */
    private fun drawSeljukStarPattern(
        scope: DrawScope,
        color: Color,
        accentColor: Color,
        intensity: Float
    ) {
        val w = scope.size.width
        val h = scope.size.height

        // Top Header Star
        drawEightPointedStar(
            scope = scope,
            center = Offset(w / 2, 85f),
            outerRadius = 42f,
            innerRadius = 24f,
            fillColor = accentColor.copy(alpha = 0.25f * intensity),
            strokeColor = color.copy(alpha = 0.9f * intensity),
            strokeWidth = 2.5f
        )

        // Bottom Star
        drawEightPointedStar(
            scope = scope,
            center = Offset(w / 2, h - 85f),
            outerRadius = 36f,
            innerRadius = 20f,
            fillColor = accentColor.copy(alpha = 0.2f * intensity),
            strokeColor = color.copy(alpha = 0.85f * intensity),
            strokeWidth = 2f
        )

        // 4 Corner Stars with geometric connector lines
        val cornerOffset = 45f
        listOf(
            Offset(cornerOffset, cornerOffset),
            Offset(w - cornerOffset, cornerOffset),
            Offset(cornerOffset, h - cornerOffset),
            Offset(w - cornerOffset, h - cornerOffset)
        ).forEach { pos ->
            drawEightPointedStar(
                scope = scope,
                center = pos,
                outerRadius = 22f,
                innerRadius = 12f,
                fillColor = accentColor.copy(alpha = 0.35f * intensity),
                strokeColor = color.copy(alpha = 0.8f * intensity),
                strokeWidth = 1.8f
            )
        }

        // Geometric corner frame lines
        val inset = 30f
        val path = Path().apply {
            // Top-left
            moveTo(inset + 40f, inset)
            lineTo(w - inset - 40f, inset)
            // Top-right
            moveTo(w - inset, inset + 40f)
            lineTo(w - inset, h - inset - 40f)
            // Bottom-right
            moveTo(w - inset - 40f, h - inset)
            lineTo(inset + 40f, h - inset)
            // Bottom-left
            moveTo(inset, h - inset - 40f)
            lineTo(inset, inset + 40f)
        }

        scope.drawPath(
            path = path,
            color = color.copy(alpha = 0.45f * intensity),
            style = Stroke(
                width = 1.5f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 6f), 0f)
            )
        )
    }

    private fun drawEightPointedStar(
        scope: DrawScope,
        center: Offset,
        outerRadius: Float,
        innerRadius: Float,
        fillColor: Color,
        strokeColor: Color,
        strokeWidth: Float
    ) {
        val path = Path()
        val numPoints = 8
        val totalVertices = numPoints * 2
        val step = (2 * PI / totalVertices).toFloat()

        for (i in 0 until totalVertices) {
            val r = if (i % 2 == 0) outerRadius else innerRadius
            val angle = i * step - (PI / 2).toFloat()
            val x = center.x + r * cos(angle)
            val y = center.y + r * sin(angle)
            if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }
        path.close()

        scope.drawPath(path = path, color = fillColor, style = Fill)
        scope.drawPath(
            path = path,
            color = strokeColor,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)
        )

        // Center dot or inner rosette
        scope.drawCircle(
            color = strokeColor,
            radius = innerRadius * 0.35f,
            center = center,
            style = Fill
        )
    }

    /**
     * Klasik Tezhip Köşe & Hat Bordürleri
     */
    private fun drawTezhipCornerMotifs(
        scope: DrawScope,
        color: Color,
        accentColor: Color,
        intensity: Float
    ) {
        val w = scope.size.width
        val h = scope.size.height

        val cornerSize = 90f
        val inset = 24f

        // Draw 4 ornate corner rumi arabesques
        drawOrnateCorner(scope, Offset(inset, inset), 0f, cornerSize, color, accentColor, intensity)
        drawOrnateCorner(scope, Offset(w - inset, inset), 90f, cornerSize, color, accentColor, intensity)
        drawOrnateCorner(scope, Offset(w - inset, h - inset), 180f, cornerSize, color, accentColor, intensity)
        drawOrnateCorner(scope, Offset(inset, h - inset), 270f, cornerSize, color, accentColor, intensity)

        // Outer ornate frame with dual lines
        val frameRect = Rect(inset + 12f, inset + 12f, w - inset - 12f, h - inset - 12f)
        scope.drawRect(
            color = color.copy(alpha = 0.55f * intensity),
            topLeft = Offset(frameRect.left, frameRect.top),
            size = Size(frameRect.width, frameRect.height),
            style = Stroke(width = 1.2f)
        )

        val innerRect = Rect(inset + 20f, inset + 20f, w - inset - 20f, h - inset - 20f)
        scope.drawRect(
            color = accentColor.copy(alpha = 0.35f * intensity),
            topLeft = Offset(innerRect.left, innerRect.top),
            size = Size(innerRect.width, innerRect.height),
            style = Stroke(
                width = 1.0f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 4f), 0f)
            )
        )
    }

    private fun drawOrnateCorner(
        scope: DrawScope,
        origin: Offset,
        angleDeg: Float,
        size: Float,
        color: Color,
        accentColor: Color,
        intensity: Float
    ) {
        scope.rotate(degrees = angleDeg, pivot = origin) {
            val path = Path().apply {
                moveTo(origin.x, origin.y)
                cubicTo(
                    origin.x + size * 0.4f, origin.y,
                    origin.x + size * 0.8f, origin.y + size * 0.2f,
                    origin.x + size, origin.y + size * 0.5f
                )
                cubicTo(
                    origin.x + size * 0.6f, origin.y + size * 0.4f,
                    origin.x + size * 0.3f, origin.y + size * 0.3f,
                    origin.x + size * 0.5f, origin.y + size
                )
                cubicTo(
                    origin.x + size * 0.2f, origin.y + size * 0.8f,
                    origin.x, origin.y + size * 0.4f,
                    origin.x, origin.y
                )
                close()
            }

            drawPath(
                path = path,
                color = accentColor.copy(alpha = 0.22f * intensity),
                style = Fill
            )
            drawPath(
                path = path,
                color = color.copy(alpha = 0.85f * intensity),
                style = Stroke(width = 1.8f, cap = StrokeCap.Round, join = StrokeJoin.Round)
            )

            // Inner floral leaf
            val innerPath = Path().apply {
                moveTo(origin.x + 8f, origin.y + 8f)
                quadraticTo(origin.x + size * 0.45f, origin.y + 12f, origin.x + size * 0.55f, origin.y + size * 0.55f)
                quadraticTo(origin.x + 12f, origin.x + size * 0.45f, origin.x + 8f, origin.y + 8f)
                close()
            }
            drawPath(
                path = innerPath,
                color = accentColor.copy(alpha = 0.7f * intensity),
                style = Fill
            )
        }
    }

    /**
     * Cami Kubbesi, Minareler ve Hilal Silüeti
     */
    private fun drawMosqueSilhouettePattern(
        scope: DrawScope,
        color: Color,
        accentColor: Color,
        intensity: Float
    ) {
        val w = scope.size.width
        val h = scope.size.height

        // Top Crescent & Glowing Star
        val crescentCenter = Offset(w / 2, 70f)
        drawCrescentWithStar(scope, crescentCenter, 28f, accentColor, intensity)

        // Bottom Mosque Skyline Silhouette
        val baseY = h - 35f
        val skylinePath = Path().apply {
            moveTo(20f, baseY)

            // Left Minaret
            lineTo(w * 0.18f, baseY)
            lineTo(w * 0.18f, baseY - 65f)
            lineTo(w * 0.195f, baseY - 85f) // Spire
            lineTo(w * 0.21f, baseY - 65f)
            lineTo(w * 0.21f, baseY)

            // Small Dome Left
            lineTo(w * 0.28f, baseY)
            cubicTo(w * 0.28f, baseY - 35f, w * 0.38f, baseY - 35f, w * 0.38f, baseY)

            // Grand Central Dome
            lineTo(w * 0.38f, baseY)
            cubicTo(w * 0.38f, baseY - 75f, w * 0.62f, baseY - 75f, w * 0.62f, baseY)

            // Small Dome Right
            lineTo(w * 0.62f, baseY)
            cubicTo(w * 0.62f, baseY - 35f, w * 0.72f, baseY - 35f, w * 0.72f, baseY)

            // Right Minaret
            lineTo(w * 0.79f, baseY)
            lineTo(w * 0.79f, baseY - 65f)
            lineTo(w * 0.805f, baseY - 85f) // Spire
            lineTo(w * 0.82f, baseY - 65f)
            lineTo(w * 0.82f, baseY)

            lineTo(w - 20f, baseY)
            lineTo(w - 20f, h - 15f)
            lineTo(20f, h - 15f)
            close()
        }

        // Dome Alem (Finial)
        scope.drawLine(
            color = accentColor.copy(alpha = 0.9f * intensity),
            start = Offset(w / 2, baseY - 75f),
            end = Offset(w / 2, baseY - 90f),
            strokeWidth = 2.2f,
            cap = StrokeCap.Round
        )
        scope.drawCircle(
            color = accentColor.copy(alpha = 0.9f * intensity),
            radius = 3.5f,
            center = Offset(w / 2, baseY - 92f)
        )

        scope.drawPath(
            path = skylinePath,
            color = color.copy(alpha = 0.35f * intensity),
            style = Fill
        )
        scope.drawPath(
            path = skylinePath,
            color = color.copy(alpha = 0.8f * intensity),
            style = Stroke(width = 1.8f, join = StrokeJoin.Round)
        )
    }

    private fun drawCrescentWithStar(
        scope: DrawScope,
        center: Offset,
        radius: Float,
        color: Color,
        intensity: Float
    ) {
        // Crescent
        val outerPath = Path().apply {
            addOval(Rect(center.x - radius, center.y - radius, center.x + radius, center.y + radius))
        }
        val innerPath = Path().apply {
            val innerR = radius * 0.85f
            val shift = radius * 0.4f
            addOval(Rect(center.x - innerR + shift, center.y - innerR - shift * 0.2f, center.x + innerR + shift, center.y + innerR - shift * 0.2f))
        }

        scope.drawCircle(
            color = color.copy(alpha = 0.85f * intensity),
            radius = radius,
            center = center,
            style = Stroke(width = 2.5f)
        )

        // 5-pointed star near crescent
        drawFivePointStar(
            scope = scope,
            center = Offset(center.x + radius * 0.55f, center.y - radius * 0.2f),
            outerRadius = radius * 0.32f,
            innerRadius = radius * 0.14f,
            color = color.copy(alpha = 0.95f * intensity)
        )
    }

    private fun drawFivePointStar(
        scope: DrawScope,
        center: Offset,
        outerRadius: Float,
        innerRadius: Float,
        color: Color
    ) {
        val path = Path()
        val numPoints = 5
        val totalVertices = numPoints * 2
        val step = (2 * PI / totalVertices).toFloat()

        for (i in 0 until totalVertices) {
            val r = if (i % 2 == 0) outerRadius else innerRadius
            val angle = i * step - (PI / 2).toFloat()
            val x = center.x + r * cos(angle)
            val y = center.y + r * sin(angle)
            if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }
        path.close()
        scope.drawPath(path = path, color = color, style = Fill)
    }

    /**
     * Nurlu Asma Kandiller ve Işıltılar
     */
    private fun drawHangingLanternsPattern(
        scope: DrawScope,
        color: Color,
        accentColor: Color,
        intensity: Float
    ) {
        val w = scope.size.width
        val topY = 24f

        // 3 Hanging lanterns at varying heights
        drawSingleLantern(scope, Offset(w * 0.22f, topY), chainLength = 65f, lanternWidth = 26f, lanternHeight = 44f, color, accentColor, intensity)
        drawSingleLantern(scope, Offset(w * 0.5f, topY), chainLength = 95f, lanternWidth = 32f, lanternHeight = 54f, color, accentColor, intensity)
        drawSingleLantern(scope, Offset(w * 0.78f, topY), chainLength = 65f, lanternWidth = 26f, lanternHeight = 44f, color, accentColor, intensity)

        // Subtle stars/sparks around lanterns
        listOf(
            Offset(w * 0.35f, 90f),
            Offset(w * 0.65f, 100f),
            Offset(w * 0.15f, 130f),
            Offset(w * 0.85f, 125f)
        ).forEach { pos ->
            drawSparkle(scope, pos, radius = 7f, color = accentColor.copy(alpha = 0.75f * intensity))
        }
    }

    private fun drawSingleLantern(
        scope: DrawScope,
        topPoint: Offset,
        chainLength: Float,
        lanternWidth: Float,
        lanternHeight: Float,
        color: Color,
        accentColor: Color,
        intensity: Float
    ) {
        val lanternTop = topPoint.y + chainLength
        val lanternCenter = Offset(topPoint.x, lanternTop + lanternHeight / 2)

        // Chain line
        scope.drawLine(
            color = color.copy(alpha = 0.7f * intensity),
            start = topPoint,
            end = Offset(topPoint.x, lanternTop),
            strokeWidth = 1.6f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(4f, 4f), 0f)
        )

        // Glowing backdrop behind lantern glass
        scope.drawCircle(
            color = accentColor.copy(alpha = 0.28f * intensity),
            radius = lanternWidth * 0.9f,
            center = lanternCenter
        )

        // Lantern Body Path
        val hw = lanternWidth / 2
        val path = Path().apply {
            // Cap
            moveTo(lanternCenter.x - hw * 0.6f, lanternTop)
            lineTo(lanternCenter.x + hw * 0.6f, lanternTop)
            lineTo(lanternCenter.x + hw, lanternTop + lanternHeight * 0.25f)
            // Waist & Glass
            lineTo(lanternCenter.x + hw * 0.7f, lanternTop + lanternHeight * 0.75f)
            // Base
            lineTo(lanternCenter.x + hw * 0.4f, lanternTop + lanternHeight)
            lineTo(lanternCenter.x - hw * 0.4f, lanternTop + lanternHeight)
            lineTo(lanternCenter.x - hw * 0.7f, lanternTop + lanternHeight * 0.75f)
            lineTo(lanternCenter.x - hw, lanternTop + lanternHeight * 0.25f)
            close()
        }

        scope.drawPath(path = path, color = color.copy(alpha = 0.25f * intensity), style = Fill)
        scope.drawPath(
            path = path,
            color = color.copy(alpha = 0.9f * intensity),
            style = Stroke(width = 1.8f, join = StrokeJoin.Round)
        )

        // Candle flame inside
        val flamePath = Path().apply {
            moveTo(lanternCenter.x, lanternCenter.y - 8f)
            quadraticTo(lanternCenter.x + 5f, lanternCenter.y, lanternCenter.x, lanternCenter.y + 6f)
            quadraticTo(lanternCenter.x - 5f, lanternCenter.y, lanternCenter.x, lanternCenter.y - 8f)
            close()
        }
        scope.drawPath(path = flamePath, color = accentColor.copy(alpha = 0.95f * intensity), style = Fill)
    }

    private fun drawSparkle(scope: DrawScope, center: Offset, radius: Float, color: Color) {
        val path = Path().apply {
            moveTo(center.x, center.y - radius)
            quadraticTo(center.x, center.y, center.x + radius, center.y)
            quadraticTo(center.x, center.y, center.x, center.y + radius)
            quadraticTo(center.x, center.y, center.x - radius, center.y)
            quadraticTo(center.x, center.y, center.x, center.y - radius)
            close()
        }
        scope.drawPath(path = path, color = color, style = Fill)
    }

    /**
     * Klasik Osmanlı Lalesi ve Çiçek Bordürü
     */
    private fun drawOttomanFloralPattern(
        scope: DrawScope,
        color: Color,
        accentColor: Color,
        intensity: Float
    ) {
        val w = scope.size.width
        val h = scope.size.height

        // Top stylized tulip header
        drawStylizedTulip(scope, Offset(w / 2, 75f), scale = 1.2f, color, accentColor, intensity)

        // Bottom stylized tulip footer
        scope.rotate(degrees = 180f, pivot = Offset(w / 2, h - 75f)) {
            drawStylizedTulip(scope, Offset(w / 2, h - 75f), scale = 1.0f, color, accentColor, intensity)
        }

        // Side floral vines
        val inset = 28f
        val sidePath = Path().apply {
            // Left curve
            moveTo(inset, 120f)
            cubicTo(inset + 20f, h * 0.35f, inset - 10f, h * 0.65f, inset, h - 120f)
            // Right curve
            moveTo(w - inset, 120f)
            cubicTo(w - inset - 20f, h * 0.35f, w - inset + 10f, h * 0.65f, w - inset, h - 120f)
        }
        scope.drawPath(
            path = sidePath,
            color = color.copy(alpha = 0.6f * intensity),
            style = Stroke(width = 1.6f, cap = StrokeCap.Round)
        )
    }

    private fun drawStylizedTulip(
        scope: DrawScope,
        center: Offset,
        scale: Float,
        color: Color,
        accentColor: Color,
        intensity: Float
    ) {
        val s = 24f * scale
        val path = Path().apply {
            moveTo(center.x, center.y + s * 1.2f)
            // Left outer petal
            cubicTo(center.x - s * 1.2f, center.y + s * 0.3f, center.x - s * 1.5f, center.y - s * 0.5f, center.x - s * 0.8f, center.y - s * 1.3f)
            // Curve down to inner left
            cubicTo(center.x - s * 0.5f, center.y - s * 0.3f, center.x - s * 0.2f, center.y + s * 0.2f, center.x, center.y - s * 0.8f)
            // Central tall point
            lineTo(center.x, center.y - s * 1.5f)
            lineTo(center.x, center.y - s * 0.8f)
            // Right inner to outer petal
            cubicTo(center.x + s * 0.2f, center.y + s * 0.2f, center.x + s * 0.5f, center.y - s * 0.3f, center.x + s * 0.8f, center.y - s * 1.3f)
            cubicTo(center.x + s * 1.5f, center.y - s * 0.5f, center.x + s * 1.2f, center.y + s * 0.3f, center.x, center.y + s * 1.2f)
            close()
        }

        scope.drawPath(path = path, color = accentColor.copy(alpha = 0.25f * intensity), style = Fill)
        scope.drawPath(
            path = path,
            color = color.copy(alpha = 0.9f * intensity),
            style = Stroke(width = 2.0f, cap = StrokeCap.Round, join = StrokeJoin.Round)
        )
    }

    /**
     * Altın Varaklı Çerçeve ve Köşe Taşları
     */
    private fun drawGoldenFoilFrame(
        scope: DrawScope,
        color: Color,
        accentColor: Color,
        intensity: Float
    ) {
        val w = scope.size.width
        val h = scope.size.height

        val m1 = 20f
        val m2 = 30f

        // Outer crisp rectangle
        scope.drawRect(
            color = color.copy(alpha = 0.85f * intensity),
            topLeft = Offset(m1, m1),
            size = Size(w - 2 * m1, h - 2 * m1),
            style = Stroke(width = 2.5f)
        )

        // Inner dotted rectangle
        scope.drawRect(
            color = accentColor.copy(alpha = 0.55f * intensity),
            topLeft = Offset(m2, m2),
            size = Size(w - 2 * m2, h - 2 * m2),
            style = Stroke(
                width = 1.2f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 6f), 0f)
            )
        )

        // 4 Luxury corner gems / rosettes
        val corners = listOf(
            Offset(m1, m1),
            Offset(w - m1, m1),
            Offset(w - m1, h - m1),
            Offset(m1, h - m1)
        )

        corners.forEach { pos ->
            scope.drawCircle(
                color = accentColor.copy(alpha = 0.95f * intensity),
                radius = 7f,
                center = pos,
                style = Fill
            )
            scope.drawCircle(
                color = color.copy(alpha = 0.95f * intensity),
                radius = 12f,
                center = pos,
                style = Stroke(width = 1.8f)
            )
        }
    }

    /**
     * Mihrap & Kemer Deseni
     */
    private fun drawArchMihrabPattern(
        scope: DrawScope,
        color: Color,
        accentColor: Color,
        intensity: Float
    ) {
        val w = scope.size.width
        val h = scope.size.height

        val inset = 26f
        val archHeight = 120f

        val archPath = Path().apply {
            // Left pillar
            moveTo(inset, h - inset)
            lineTo(inset, archHeight + inset)
            // Pointed Islamic arch (Mukarnas / Sivri Kemer)
            cubicTo(inset, inset + 40f, w / 2 - 40f, inset, w / 2, inset)
            cubicTo(w / 2 + 40f, inset, w - inset, inset + 40f, w - inset, archHeight + inset)
            // Right pillar
            lineTo(w - inset, h - inset)
            close()
        }

        scope.drawPath(
            path = archPath,
            color = color.copy(alpha = 0.65f * intensity),
            style = Stroke(width = 2.0f, cap = StrokeCap.Round, join = StrokeJoin.Round)
        )

        // Inner decorative arch
        val innerInset = inset + 12f
        val innerArch = Path().apply {
            moveTo(innerInset, h - innerInset)
            lineTo(innerInset, archHeight + innerInset)
            cubicTo(innerInset, innerInset + 40f, w / 2 - 30f, innerInset, w / 2, innerInset)
            cubicTo(w / 2 + 30f, innerInset, w - innerInset, innerInset + 40f, w - innerInset, archHeight + innerInset)
            lineTo(w - innerInset, h - innerInset)
        }
        scope.drawPath(
            path = innerArch,
            color = accentColor.copy(alpha = 0.4f * intensity),
            style = Stroke(
                width = 1.0f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 4f), 0f)
            )
        )
    }
}
