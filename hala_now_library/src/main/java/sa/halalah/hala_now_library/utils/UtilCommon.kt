package sa.halalah.hala_now_library.utils

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.ui.res.stringResource
import okhttp3.ResponseBody
import org.json.JSONObject
import sa.halalah.hala_now_library.R
import java.security.AccessController.getContext
import java.util.Locale


object UtilCommon {

    @JvmStatic
    fun parseErrorMessage(errorBody: ResponseBody?): String {
        errorBody?.let {
            try {
                val jObjError = JSONObject(it.string())
                if(jObjError.has("message")){
                    return jObjError.getString("message")
                }else if(jObjError.has("title")){
                    return jObjError.getString("title")
                }else{
                    return "Something went wrong. Please try again!"
                }
            } catch (e: Exception) {
                return "Something went wrong. Please try again!"
            }
        }
        return "Something went wrong. Please try again!"
    }

    @JvmStatic
    fun changeLanguage(context: Context, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }
}
