package com.example.rule.dash.ui.activities.gallery

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import android.view.KeyEvent
import android.view.WindowManager
import android.widget.ImageView
import com.example.rule.dash.R
import com.example.rule.dash.ui.activities.base.BaseActivity
import com.example.rule.dash.ui.widget.TouchImageView
import kotterknife.bindView
import com.example.rule.dash.utils.Consts.URL_IMAGE
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class ViewPhoto : BaseActivity(R.layout.activity_view_photo) {

    private val imgPhoto: TouchImageView by bindView(R.id.img_view_photo)
    private val toolbar : Toolbar by bindView(R.id.toolbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionBar()
        setImage()
    }

    private fun setActionBar(){
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = ""
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener { supportFinishAfterTransition() }
    }

    override fun onResume() {
        super.onResume()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
    }

    private fun setImage() {
        val url = intent.getStringExtra(URL_IMAGE)
        supportPostponeEnterTransition()
        if (url != null) {
            imgPhoto.setImageUrl(url){ supportStartPostponedEnterTransition() }
        }
    }

    private fun ImageView.setImageUrl(url:String, onLoadingFinished:()->Unit){
        Picasso.get().load(url)
                .into(this,object :Callback{
                    override fun onSuccess() { onLoadingFinished() }
                    override fun onError(e: Exception?) { onLoadingFinished() }
                })
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            supportFinishAfterTransition()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}