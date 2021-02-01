package app.money_task_kotlin.global

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import app.money_task_kotlin.R

object Navigator {
    fun loadFragment(
        activity: FragmentActivity,
        baseFrafment: Fragment?,
        containerId: Int,
        isStacked: Boolean,
        s: String?
    ) {
        if (!isStacked) {
            activity.supportFragmentManager
                .beginTransaction()
                .replace(containerId, baseFrafment!!)
                .commitAllowingStateLoss()
        } else {
            activity.supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(containerId, baseFrafment!!)
                .addToBackStack(s)
                .commit()
        }
    }
}