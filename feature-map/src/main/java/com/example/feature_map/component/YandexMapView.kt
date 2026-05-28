package com.example.feature_map.component

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.data.remote.model.DonorPointModel
import com.example.feature_common.R
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

@Composable
fun YandexMapView(
    stations: List<DonorPointModel>,
    cameraTarget: Point?,
    onStationClick: (DonorPointModel) -> Unit,
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = false
) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    val cachedPlacemarks = remember { mutableListOf<PlacemarkMapObject>() }



    val markerCritical = remember(context) {
        getBitmapFromVector(context, R.drawable.ic_marker_critical, 48, 48)?.let {
            ImageProvider.fromBitmap(it)
        }
    }
    val markerNormal = remember(context) {
        getBitmapFromVector(context, R.drawable.ic_marker_normal, 48, 48)?.let {
            ImageProvider.fromBitmap(it)
        }
    }

    DisposableEffect(mapView) {
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
        onDispose {
            mapView.onStop()
            MapKitFactory.getInstance().onStop()
        }
    }

    LaunchedEffect(cameraTarget) {
        cameraTarget?.let { target ->
            mapView.mapWindow.map.move(
                CameraPosition(target, 11.0f, 0.0f, 0.0f),
                Animation(Animation.Type.SMOOTH, 0.5f),
                null
            )
        }
    }

    AndroidView(
        factory = { mapView },
        modifier = modifier
    ) { view ->
        val map = view.map
        map.isNightModeEnabled = isDarkTheme

        cachedPlacemarks.clear()
        map.mapObjects.clear()

        stations.forEach { station ->
            val lat = station.lat
            val lng = station.lng

            if (lat != null && lng != null) {
                val point = Point(lat, lng)
                val placemark = map.mapObjects.addPlacemark(point)

                val isCritical = station.bloodStatusMap.containsValue("critical")


                val markerIcon = if (isCritical) markerCritical else markerNormal

                if (markerIcon != null) {
                    placemark.setIcon(markerIcon)
                }

                placemark.addTapListener { _, _ ->
                    onStationClick(station)
                    true
                }

                cachedPlacemarks.add(placemark)
            }
        }
    }
}


private fun getBitmapFromVector(
    context: Context,
    vectorResId: Int,
    widthDp: Int,
    heightDp: Int
): Bitmap? {
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null


    val density = context.resources.displayMetrics.density
    val widthPx = (widthDp * density).toInt()
    val heightPx = (heightDp * density).toInt()

    val bitmap = Bitmap.createBitmap(widthPx, heightPx, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)

    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)

    return bitmap
}