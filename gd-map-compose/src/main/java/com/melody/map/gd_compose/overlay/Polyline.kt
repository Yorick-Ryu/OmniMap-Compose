// MIT License
//
// Copyright (c) 2022 被风吹过的夏天
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

package com.melody.map.gd_compose.overlay

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.currentComposer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.amap.api.maps.model.BitmapDescriptor
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.Polyline
import com.amap.api.maps.model.PolylineOptions
import com.melody.map.gd_compose.MapApplier
import com.melody.map.gd_compose.MapNode
import com.amap.api.maps.model.PolylineOptions.LineJoinType
import com.amap.api.maps.model.PolylineOptions.LineCapType
import com.melody.map.gd_compose.model.GDMapComposable

internal class PolylineNode(
    val polyline: Polyline,
    var onPolylineClick: (Polyline) -> Unit
) : MapNode {
    override fun onRemoved() {
        polyline.remove()
    }
}

/**
 * A composable for a polyline on the map.
 *
 * @param points the points comprising the polyline
 * @param color the color of the polyline
 * @param geodesic specifies whether to draw the polyline as a geodesic
 * @param visible the visibility of the polyline
 * @param isDottedLine the isDottedLine of the polyline
 * @param isAboveMaskLayer the isAboveMaskLayer of the polyline
 * @param useGradient the useGradient of the polyline
 * @param lineCustomTexture the lineCustomTexture of the polyline
 * @param lineJoinType the lineJoinType of the polyline
 * @param lineCapType the lineCapType of the polyline
 * @param width the width of the polyline in screen pixels
 * @param zIndex the z-index of the polyline
 * @param onClick a lambda invoked when the polyline is clicked
 */
@Composable
@GDMapComposable
fun Polyline(
    points: List<LatLng>,
    color: Color = Color.Black,
    geodesic: Boolean = false,
    visible: Boolean = true,
    isDottedLine: Boolean = false,
    isAboveMaskLayer: Boolean = false,
    useGradient: Boolean = false,
    lineCustomTexture: BitmapDescriptor? = null,
    lineJoinType: LineJoinType = LineJoinType.LineJoinBevel,
    lineCapType: LineCapType = LineCapType.LineCapRound,
    width: Float = 10f,
    zIndex: Float = 0f,
    onClick: (Polyline) -> Unit = {}
) {
    val mapApplier = currentComposer.applier as MapApplier?
    ComposeNode<PolylineNode, MapApplier>(
        factory = {
            val polyline = mapApplier?.map?.addPolyline (
                PolylineOptions().apply {
                    addAll(points)
                    color(color.toArgb())
                    geodesic(geodesic)
                    setDottedLine(isDottedLine)
                    lineJoinType(lineJoinType)
                    lineCapType(lineCapType)
                    aboveMaskLayer(isAboveMaskLayer)
                    customTexture = lineCustomTexture
                    useGradient(useGradient)
                    visible(visible)
                    width(width)
                    zIndex(zIndex)
                }) ?: error("Error adding Polyline")
            PolylineNode(polyline, onClick)
        },
        update = {
            update(onClick) { this.onPolylineClick = it }

            set(points) { this.polyline.points = it }
            set(lineJoinType) { this.polyline.options.lineJoinType(it) }
            set(lineCapType) { this.polyline.options.lineCapType(it) }
            set(color) { this.polyline.color = it.toArgb() }
            set(geodesic) { this.polyline.isGeodesic = it }
            set(isDottedLine) { this.polyline.isDottedLine = it }
            set(isAboveMaskLayer) { this.polyline.setAboveMaskLayer(it) }
            set(lineCustomTexture) { this.polyline.setCustomTexture(it) }
            set(visible) { this.polyline.isVisible = it }
            set(width) { this.polyline.width = it }
            set(zIndex) { this.polyline.zIndex = it }
        }
    )
}