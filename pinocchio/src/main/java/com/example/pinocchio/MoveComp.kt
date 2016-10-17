package com.example.pinocchio

import android.support.v4.view.ViewCompat
import android.view.View
import android.view.animation.Interpolator
import rx.Completable

class MoveComp(private val view: View,
               private val translationX: Float,
               private val translationY: Float,
               private val duration: Long,
               private val interpolator: Interpolator): Completable.CompletableOnSubscribe {

    override fun call(subscriber: Completable.CompletableSubscriber) {

            ViewCompat.animate(view).translationX(translationX).setInterpolator(interpolator).setDuration(duration).withEndAction { subscriber.onCompleted() }

    }
}
