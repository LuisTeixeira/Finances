package com.lfmteixeira.composefinances.ui.chart.pie

import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import com.lfmteixeira.composefinances.ui.chart.pie.renderer.SimpleSliceDrawer
import com.lfmteixeira.composefinances.ui.chart.pie.renderer.SliceDrawer
import com.lfmteixeira.composefinances.ui.chart.util.simpleChartAnimation
import com.lfmteixeira.composefinances.ui.chart.pie.PieChartUtils.calculateAngle

@Composable
fun PieChart(
    pieChartData: PieChartData,
    modifier: Modifier = Modifier,
    animation: AnimationSpec<Float> = simpleChartAnimation(),
    sliceDrawer: SliceDrawer = SimpleSliceDrawer()
) {
    val transitionProgress = remember(pieChartData.slices) { Animatable(initialValue = 0f) }

    // When slices value changes we want to re-animate the chart.
    LaunchedEffect(pieChartData.slices) {
        transitionProgress.animateTo(1f, animationSpec = animation)
    }

    DrawChart(
        pieChartData = pieChartData,
        modifier = modifier.fillMaxSize(),
        progress = transitionProgress.value,
        sliceDrawer = sliceDrawer
    )
}

@Composable
private fun DrawChart(
    pieChartData: PieChartData,
    modifier: Modifier,
    progress: Float,
    sliceDrawer: SliceDrawer
) {
    val slices = pieChartData.slices

    Canvas(modifier = modifier.pointerInput(Unit) {
        detectTapGestures(
            onTap = {
//                val x = it.x - radius
//                val y = it.y - radius
//                var touchAngle = Math.toDegrees(Math.atan2(y.toDouble(), x.toDouble()))
//                if (x < 0 && y < 0 || x > 0 && y < 0) {
//                    touchAngle += 360
//                }
//                val position = getPositionFromAngle(touchAngle = touchAngle, angles = angles)
//                Toast.makeText(context, "onTap: $position", Toast.LENGTH_SHORT).show()
            }
        )
    } ) {
        drawIntoCanvas {
            var startArc = 0f

            slices.forEach { slice ->
                val arc = calculateAngle(
                    sliceLength = slice.value,
                    totalLength = pieChartData.totalSize,
                    progress = progress
                )

                sliceDrawer.drawSlice(
                    drawScope = this,
                    canvas = drawContext.canvas,
                    area = size,
                    startAngle = startArc,
                    sweepAngle = arc,
                    slice = slice
                )

                startArc += arc
            }
        }
    }
}

@Preview
@Composable
fun PieChartPreview() = PieChart(
    pieChartData = PieChartData(
        slices = listOf(
            PieChartData.Slice(25f, MaterialTheme.colors.primary),
            PieChartData.Slice(42f, MaterialTheme.colors.secondary),
            PieChartData.Slice(23f, MaterialTheme.colors.secondaryVariant)
        )
    )
)