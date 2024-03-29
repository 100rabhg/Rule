package com.example.rule.dash.utils.hiddenCameraServiceUtils

import android.content.Context
import com.example.rule.dash.utils.hiddenCameraServiceUtils.config.CameraFacing
import com.example.rule.dash.utils.hiddenCameraServiceUtils.config.CameraImageFormat
import com.example.rule.dash.utils.hiddenCameraServiceUtils.config.CameraResolution
import com.example.rule.dash.utils.hiddenCameraServiceUtils.config.CameraRotation
import com.example.rule.dash.utils.hiddenCameraServiceUtils.HiddenCameraUtils.getFileName

import java.io.File

class CameraConfig {

    private var context: Context? = null

    @CameraResolution.SupportedResolution
    @get:CameraResolution.SupportedResolution
    internal var resolution = CameraResolution.MEDIUM_RESOLUTION
        private set

    @CameraFacing.SupportedCameraFacing
    @get:CameraFacing.SupportedCameraFacing
    internal var facing = CameraFacing.FRONT_FACING_CAMERA
        private set

    @CameraImageFormat.SupportedImageFormat
    @get:CameraImageFormat.SupportedImageFormat
    internal var imageFormat = CameraImageFormat.FORMAT_JPEG
        private set

    @CameraRotation.SupportedRotation
    @get:CameraRotation.SupportedRotation
    internal var imageRotation = CameraRotation.ROTATION_0
        private set

    internal var imageFile: File?=null
            private set

    fun builder(context: Context): Builder {
        this.context = context
        return Builder()
    }

    inner class Builder {

        fun setCameraFacing(@CameraFacing.SupportedCameraFacing cameraFacing: Int): Builder {
            facing = cameraFacing
            return this
        }

        fun setImageRotation(@CameraRotation.SupportedRotation rotation: Int): Builder {

            imageRotation = if (rotation != CameraRotation.ROTATION_0
                && rotation != CameraRotation.ROTATION_90
                && rotation != CameraRotation.ROTATION_180
                && rotation != CameraRotation.ROTATION_270)
                CameraRotation.ROTATION_0
            else rotation
            return this
        }

        fun build(): CameraConfig {
            if (imageFile==null) imageFile = File(context!!.getFileName())
            return this@CameraConfig
        }
    }
}
