package app.money_task_kotlin.view.activity

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import app.money_task_kotlin.R
import app.money_task_kotlin.databinding.ActivityMainBinding
import app.money_task_kotlin.global.GlobalFunctions.initBack
import app.money_task_kotlin.global.IntentTo.goToHome
import app.money_task_kotlin.view.activity.MainActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(
            layoutInflater
        )
        val view: View = binding!!.root
        setContentView(view)
        backClicked()
        goToHome(this, true)
    }

    // todo init back clicked
    private fun backClicked() {
        binding!!.toolbar.imgBack.setOnClickListener { initBack(this@MainActivity) }
    } // initialize back

    // todo init back button in mobile
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            initBack(this)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    companion object {
        // todo vars
        var binding: ActivityMainBinding? = null
    }
}