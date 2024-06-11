package sa.halalah.hala_now_library.authentication.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.hala.module_authentication.intent.ui.IntentWidgetCoordinator
import sa.halalah.hala_now_library.authentication.models.states.IntentViewState
import sa.halalah.hala_now_library.authentication.view_models.IntentViewModel

import sa.halalah.hala_now_library.core_widgets.HalaCircularProgressIndicator
import sa.halalah.hala_now_library.theme.HalaTheme
import sa.halalah.hala_now_library.utils.UIUtil

class IntentActivity : ComponentActivity() {

    private val viewModel: IntentViewModel by viewModels()

    private lateinit var mobileNumber: String
    private lateinit var requestID: String
    private var isLogin: Boolean = false  //TODO temp until backend fix it

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mobileNumber = intent.getStringExtra(MOBILE_NO).toString()
        requestID = intent.getStringExtra(REQUEST_ID).toString()
        isLogin = intent.getBooleanExtra(IS_LOGIN, false)
        val aa = this as IntentActivity
        setContent {
            val uiState by viewModel.uiState.collectAsState()
            HalaTheme {
                IntentWidgetCoordinator(
                        mobileNumber = mobileNumber,
                        reqID = requestID,
                        onCloseClicked = {
                            finish()
                        },
                        onNext = {
                            viewModel.checkRequestStatus(requestID, true)
                        })

                when (val state = uiState) {
                    IntentViewState.Done -> {
                        setResult(Activity.RESULT_OK, Intent().putExtra(REQUEST_ID, requestID))
                        finish()
                    }

                    is IntentViewState.Error -> {

//                        UIUtil.showError(
//                                context = this,
//                                message = state.message
//                        )

                        viewModel.clearState()
                    }

                    IntentViewState.Loading -> {
                        HalaCircularProgressIndicator()
                    }

                    null -> {}
                }
            }
        }
    }



    companion object {
        const val MOBILE_NO = "mobile_no"
        const val REQUEST_ID = "req_id"
        const val IS_LOGIN = "is_login"
        const val IS_RESET_PIN = "is_reset_pin"
        fun getIntent(
                context: Context,
                mobile: String,
                reqId: String,
                isLogin: Boolean = false,
                isReset: Boolean = false
        ): Intent {
            return Intent(context, IntentActivity::class.java).apply {
                putExtra(MOBILE_NO, mobile)
                putExtra(REQUEST_ID, reqId)
                putExtra(IS_LOGIN, isLogin)
                putExtra(IS_RESET_PIN, isReset)
            }
        }
    }
}