package com.example.santoshastagi.kotlinone

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.pinocchio.AnimationList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animateButton.setOnClickListener {
            AnimationList().move(strokedCircle2,400f).endWith(AnimationList().move(strokedCircle1,400f)).subscribe()
        }
    }

}
