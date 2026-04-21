package com.example.feature_add_donation.utils

import android.content.ContentResolver
import android.net.Uri

fun Uri.toByteArray(contentResolver: ContentResolver): ByteArray? {
    return contentResolver.openInputStream(this)?.use { it.readBytes() }
}