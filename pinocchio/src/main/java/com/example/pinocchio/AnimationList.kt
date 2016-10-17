package com.example.pinocchio

import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import rx.Completable

class AnimationList {
    fun move(view:View, tranX:Float): Completable =
            Completable.create(MoveComp(view, tranX, 0f, 1000, AccelerateDecelerateInterpolator()))
}
