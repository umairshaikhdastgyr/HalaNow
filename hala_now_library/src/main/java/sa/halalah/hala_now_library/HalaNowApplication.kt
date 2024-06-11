package sa.halalah.hala_now_library

import android.app.Application
import android.content.Context


class HalaNowApplication : Application() {

    init {
        instance = this
    }
    companion object {
        private var instance: HalaNowApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }

    }
}