package com.example.rule.dash.utils.hiddenCameraServiceUtils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.hardware.Camera
import android.provider.Settings
import androidx.annotation.WorkerThread
import androidx.core.net.toUri
import com.example.rule.dash.utils.ConstFun
import com.example.rule.dash.utils.Consts.ADDRESS_PHOTO
import com.example.rule.dash.utils.Consts.TAG
import com.example.rule.dash.utils.FileHelper.getFilePath

import com.example.rule.dash.utils.hiddenCameraServiceUtils.config.CameraImageFormat
import com.example.rule.dash.utils.hiddenCameraServiceUtils.config.CameraRotation
import com.pawegio.kandroid.e
import com.pawegio.kandroid.start

import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object HiddenCameraUtils {

    @SuppressLint("NewApi")
    fun Context.canOverDrawOtherApps(): Boolean {
        return Settings.canDrawOverlays(this)
    }

    fun Context.openDrawOverPermissionSetting() {
        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, "package:$packageName".toUri())
        intent.start(this)
    }

    fun Context.isFrontCameraAvailable(): Boolean {
        val numCameras = Camera.getNumberOfCameras()
        return numCameras > 0 && packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)
    }

    @Throws(Exception::class)
    fun Context.getFileName() : String {
        val file: File?
        try {
            file = File(getFilePath(), ADDRESS_PHOTO)

            if (!file.exists()) {
                file.mkdirs()
            }
        } catch (e: Exception) {
            throw Exception(e)
        }

        return "${file.absolutePath}/IMG_${ConstFun.getRandomNumeric()}.jpeg"
    }

    @WorkerThread
    internal fun Bitmap.rotateBitmap(@CameraRotation.SupportedRotation rotation: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(rotation.toFloat())
        return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
    }

    internal fun Bitmap.saveImageFromFile(fileToSave: File,
                                   @CameraImageFormat.SupportedImageFormat imageFormat: Int): Boolean {
        var out: FileOutputStream? = null
        var isSuccess:Boolean

        val compressFormat = if (imageFormat == CameraImageFormat.FORMAT_JPEG) Bitmap.CompressFormat.JPEG
        else Bitmap.CompressFormat.PNG

        try {
            if (!fileToSave.exists()) fileToSave.createNewFile()
            out = FileOutputStream(fileToSave)
            compress(compressFormat, 100, out)
            isSuccess = true
        } catch (e: Exception) {
            e(TAG,e.message.toString())
            isSuccess = false
        } finally {
            try {
                out?.close()
            } catch (e: IOException) {
                e(TAG,e.message.toString())
            }

        }
        return isSuccess
    }
}
