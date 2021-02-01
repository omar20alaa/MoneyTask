package app.money_task_kotlin.global

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import es.dmoral.toasty.Toasty
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

 object  GlobalFunctions {

    //============================= TODO show normal Toast ====================================================================================================
    fun showNormalToast(context: Context?, message: String?) {
        Toasty.normal(context!!, message!!, Toast.LENGTH_LONG).show()
    }

    //============================= TODO show success Toast ====================================================================================================
    fun showSuccessToast(context: Context?, message: String?) {
        Toasty.success(context!!, message!!, Toast.LENGTH_LONG).show()
    }

    //============================= TODO show error Toast ====================================================================================================
    fun showErrorToast(context: Context?, message: String?) {
        Toasty.error(context!!, message!!, Toast.LENGTH_SHORT).show()
    }

    //============================= TODO show warning Toast ====================================================================================================
    fun showWarningToast(context: Context?, message: String?) {
        Toasty.warning(context!!, message!!, Toast.LENGTH_LONG).show()
    }

    //============================= TODO showLog ====================================================================================================
    fun  showLog(message: String?) {
        Log.d(MoneyTaskConstants.tag, message!!)
    }

    //============================= TODO setTextView ====================================================================================================
    fun setTextView(textView: TextView, entered_text: String?) {
        textView.text = entered_text
    }

    //============================= TODO initBack ====================================================================================================
    fun initBack(activity: FragmentActivity) {
        if (activity.supportFragmentManager.backStackEntryCount == 1) {
            activity.finish()
        } else {
            activity.onBackPressed()
        }
    } // init back

    //============================= TODO  showImage ====================================================================================================
    fun showImage(
        activity: FragmentActivity,
        image_path: String?,
        imageView: ImageView?,
        place_holder: Int
    ) {
        Glide.with(activity.applicationContext).load(image_path)
            .apply(
                RequestOptions()
                    .placeholder(place_holder)
                    .error(place_holder)
                    .centerCrop()
            )
            .dontAnimate()
            .into(imageView!!)
    }

    //============================= TODO  clearStack ====================================================================================================
    fun clearStack(fragment: FragmentActivity) {
        val fm = fragment.supportFragmentManager
        showLog("Stack before --> " + fm.backStackEntryCount)
        for (i in 0 until fm.backStackEntryCount) {
            fm.popBackStack()
        }
        showLog("Stack after --> " + fm.backStackEntryCount)
    }

    //============================= TODO convertDateToString ====================================================================================================
    fun convertDateToString(date: String): String {
        var date1 = ""
        val dateFormatter1 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
        dateFormatter1.timeZone = TimeZone.getTimeZone("UTC")
        val dateFormatter2 = SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH)
        val index = date.lastIndexOf('/')
        try {
            date1 = dateFormatter2.format(dateFormatter1.parse(date.substring(index + 1)))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date1
    } // convertDateToString
}