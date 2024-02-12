package com.example.rule.dash.ui.fragments.maps

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.example.rule.dash.data.model.Location
import com.example.rule.dash.data.rxFirebase.InterfaceFirebase
import com.example.rule.dash.ui.activities.base.BaseInteractor
import com.example.rule.dash.utils.Consts.CHILD_GPS
import com.example.rule.dash.utils.Consts.CHILD_PERMISSION
import com.example.rule.dash.utils.Consts.DATA
import com.example.rule.dash.utils.Consts.LOCATION
import com.example.rule.dash.utils.Consts.PARAMS
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class InteractorMaps<V : InterfaceViewMaps> @Inject constructor(supportFragment: FragmentManager, context: Context,
                                                                firebase: InterfaceFirebase) : BaseInteractor<V>(supportFragment, context, firebase), InterfaceInteractorMaps<V> {

    override fun valueEventLocation() {
        getView()!!.addDisposable(firebase().valueEventModel("$LOCATION/$DATA", Location::class.java)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ if (getView() != null) getView()!!.setLocation(it) },
                        { if (getView() != null) getView()!!.showError(it.message.toString()) }))
    }

    override fun valueEventEnableGps() {
        getView()!!.addDisposable(firebase().valueEvent("$LOCATION/$PARAMS/$CHILD_GPS")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { if (getView() != null) getView()!!.setValueState(it) })
    }

    override fun valueEventEnablePermission() {
        getView()!!.addDisposable(firebase().valueEvent("$LOCATION/$PARAMS/$CHILD_PERMISSION")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { if (getView() != null) getView()!!.setValuePermission(it) })
    }
}