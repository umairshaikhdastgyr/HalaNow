package sa.halalah.hala_now_library.pay_later.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import sa.halalah.hala_now_library.theme.HalaTheme

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import sa.halalah.hala_now_library.core_models.SDKDataHolder
import sa.halalah.hala_now_library.pay_later.view_models.FormViewModel
import sa.halalah.hala_now_library.utils.UtilCommon

class PayLaterActivity : ComponentActivity() {

    private var mainController: NavHostController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UtilCommon.changeLanguage(this, SDKDataHolder.getUserData().acceptLanguage)
        setContent {
            mainController = rememberNavController()
            HalaTheme {
                NavStack(mainController!!)
            }
        }
    }



    @Composable
    private fun NavStack(
        navController: NavHostController,
    ) {
        val startDestination = if (intent.getBooleanExtra(
                "fromPendingRequest",
                false
            )
        ) PaymentsFormActivityScreens.PaymentSummaryNoArg.route else PaymentsFormActivityScreens.PaymentsForm.route

        NavHost(
            navController = navController,
            startDestination = startDestination,
        ) {
            composable(PaymentsFormActivityScreens.PaymentsForm.route) {
                PaymentsForm(navController)
            }

            composable(
                PaymentsFormActivityScreens.PaymentSummary.route.plus("/{paymentSummary}/{imageAndNotes}"),
                arguments = listOf(navArgument("paymentSummary") {
                    type = NavType.StringType
                    nullable = true
                }, navArgument("imageAndNotes") {
                    type = NavType.StringType
                    nullable = true
                })
            ) {
                PaymentSummary(navController, it.arguments)
            }

            composable(
                PaymentsFormActivityScreens.PaymentSummaryNoArg.route
            ) {
                PaymentSummary(navController, null)
            }
        }
    }

    companion object {
        @JvmStatic
        fun getIntent(context: Context) =
            Intent(context, PayLaterActivity::class.java)

        @JvmStatic
        fun start(context: Context, intent: Intent) {
            context.startActivity(intent)
        }
    }
}


sealed class PaymentsFormActivityScreens(val route: String) {
    data object PaymentsForm : PaymentsFormActivityScreens("PaymentsForm")
    data object PaymentSummary : PaymentsFormActivityScreens("PaymentSummary")
    data object PaymentSummaryNoArg : PaymentsFormActivityScreens("PaymentSummaryNoArg")
}