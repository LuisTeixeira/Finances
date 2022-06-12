package com.lfmteixeira.composefinances.ui.chart.line

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import com.lfmteixeira.composefinances.ui.chart.line.LineChartUtils.calculateDrawableArea
import com.lfmteixeira.composefinances.ui.chart.line.LineChartUtils.calculateFillPath
import com.lfmteixeira.composefinances.ui.chart.line.LineChartUtils.calculateLinePath
import com.lfmteixeira.composefinances.ui.chart.line.LineChartUtils.calculatePointLocation
import com.lfmteixeira.composefinances.ui.chart.line.LineChartUtils.calculateXAxisDrawableArea
import com.lfmteixeira.composefinances.ui.chart.line.LineChartUtils.calculateXAxisLabelDrawableArea
import com.lfmteixeira.composefinances.ui.chart.line.LineChartUtils.calculateYAxisDrawableArea
import com.lfmteixeira.composefinances.ui.chart.line.LineChartUtils.withProgress
import com.lfmteixeira.composefinances.ui.chart.line.renderer.line.*
import com.lfmteixeira.composefinances.ui.chart.line.renderer.point.FilledCircularPointDrawer
import com.lfmteixeira.composefinances.ui.chart.line.renderer.point.PointDrawer
import com.lfmteixeira.composefinances.ui.chart.line.renderer.xaxis.SimpleXAxisDrawer
import com.lfmteixeira.composefinances.ui.chart.line.renderer.xaxis.XAxisDrawer
import com.lfmteixeira.composefinances.ui.chart.line.renderer.yaxis.SimpleYAxisDrawer
import com.lfmteixeira.composefinances.ui.chart.line.renderer.yaxis.YAxisDrawer
import com.lfmteixeira.composefinances.ui.chart.util.simpleChartAnimation

@Composable
fun LineChart(
    lineChartData: LineChartData,
    modifier: Modifier = Modifier,
    animation: AnimationSpec<Float> = simpleChartAnimation(),
    pointDrawer: PointDrawer = FilledCircularPointDrawer(),
    lineDrawer: LineDrawer = SolidLineDrawer(),
    lineShader: LineShader = NoLineShader,
    xAxisDrawer: XAxisDrawer = SimpleXAxisDrawer(),
    yXAxisDrawer: YAxisDrawer = SimpleYAxisDrawer(),
    horizontalOffset: Float = 5f
) {
    check(horizontalOffset in 0f..25f) {
        "Horizontal offset is the % offset from slides, " +
                "and should be between 0%-25%"
    }

    val transitionAnimation = remember(lineChartData.points) { Animatable(initialValue = 0f) }

    LaunchedEffect(lineChartData.points) {
        transitionAnimation.snapTo(0f)
        transitionAnimation.animateTo(1f, animationSpec = animation)
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        drawIntoCanvas { canvas ->
            val yAxisDrawableArea = calculateYAxisDrawableArea(
                xAxisLabelSize =  xAxisDrawer.requiredHeight(this),
                size = size
            )
            val xAxisDrawableArea = calculateXAxisDrawableArea(
                yAxisWidth = yAxisDrawableArea.width,
                labelHeight = xAxisDrawer.requiredHeight(this),
                size = size
            )
            val xAxisLabelDrawableArea = calculateXAxisLabelDrawableArea(
                xAxisDrawableArea = xAxisDrawableArea,
                offset = horizontalOffset
            )
            val chartDrawableArea = calculateDrawableArea(
                xAxisDrawableArea = xAxisDrawableArea,
                yAxisDrawableArea = yAxisDrawableArea,
                size = size,
                offset = horizontalOffset
            )

            // Draw the chart line
            lineDrawer.drawLine(
                drawScope = this,
                canvas = canvas,
                linePath = calculateLinePath(
                    drawableArea = chartDrawableArea,
                    lineChartData = lineChartData,
                    transitionProgress = transitionAnimation.value
                )
            )

            lineShader.fillLine(
                drawScope = this,
                canvas = canvas,
                fillPath = calculateFillPath(
                    drawableArea = chartDrawableArea,
                    lineChartData = lineChartData,
                    transitionProgress = transitionAnimation.value
                )
            )

            lineChartData.points.forEachIndexed { index, point ->
                withProgress(
                    index = index,
                    lineChartData = lineChartData,
                    transitionProgress = transitionAnimation.value
                ) {
                    pointDrawer.drawPoint(
                        drawScope = this,
                        canvas = canvas,
                        center = calculatePointLocation(
                            drawableArea = chartDrawableArea,
                            lineChartData = lineChartData,
                            point = point,
                            index = index
                        )
                    )
                }
            }


        }
    }
}