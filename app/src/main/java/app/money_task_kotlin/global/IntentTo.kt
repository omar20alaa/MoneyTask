package app.money_task_kotlin.global

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import app.money_task_kotlin.R
import app.money_task_kotlin.view.fragment.AddAccountFragment
import app.money_task_kotlin.view.fragment.HomeFragment

object IntentTo {
    // todo goToHome
    fun goToHome(activity: FragmentActivity?, isStacked: Boolean?) {
        val fragment: Fragment = HomeFragment()
        Navigator.loadFragment(activity!!, fragment, R.id.fragment_container, isStacked!!, "")
    }

    // todo goTo Add new account
    fun goToAddAccount(activity: Context?, budget_id: String) {
        val fragment = AddAccountFragment()
        val bundle = Bundle()
        bundle.putString("budget_id", budget_id)
        fragment.arguments = bundle
        GlobalFunctions.showLog("sendBudget_id --> $budget_id")
        Navigator.loadFragment(
            activity!! as FragmentActivity,
            fragment,
            R.id.fragment_container,
            true,
            ""
        )
    }
}