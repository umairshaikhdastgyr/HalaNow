package sa.halalah.hala_now_library.utils


import android.app.Activity
import android.view.View
import org.aviran.cookiebar2.CookieBar
import sa.halalah.hala_now_library.R


object UIUtil {

    @JvmStatic
    fun showError(rootView: View?, message: String) {
        rootView?.context?.let { context ->
            if (context is Activity) {
                CookieBar.dismiss(context)
                CookieBar.build(context)
                    .setMessage(message)
                    .setDuration(3000)
                    .setBackgroundColor(R.color.error_100)
                    .show()
            }
        }
    }

    @JvmStatic
    fun showError(activity: Activity?, message: String) {
        activity?.let {
            CookieBar.dismiss(it)
            CookieBar.build(it)
                .setMessage(message)
                .setDuration(3000)
                .setBackgroundColor(R.color.error_100)
                .show()
        }
    }

    @JvmStatic
    fun showWarning(rootView: View?, message: String) {
        rootView?.context?.let { context ->
            if (context is Activity) {
                CookieBar.dismiss(context)
                CookieBar.build(context)
                    .setMessage(message)
                    .setDuration(3000)
                    .setBackgroundColor(R.color.error_100)
                    .show()
            }
        }
    }

    @JvmStatic
    fun showSuccess(rootView: View?, message: String) {
        rootView?.context?.let { context ->
            if (context is Activity) {
                CookieBar.dismiss(context)
                CookieBar.build(context)
                    .setMessage(message)
                    .setDuration(3000)
                    .setBackgroundColor(R.color.error_100)
                    .show()
            }
        }
    }
}
