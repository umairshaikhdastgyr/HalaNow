package sa.halalah.hala_now

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import sa.halalah.hala_now.ui.theme.HalaNowTheme
//import sa.halalah.hala_now_library.core_models.UserData
//import sa.halalah.hala_now_library.core_models.UserDataHolder
//import sa.halalah.hala_now_library.pay_later.models.SupplierInputField
//import sa.halalah.hala_now_library.pay_later.models.SupplierProfile
//import sa.halalah.hala_now_library.pay_later.ui.PayLaterActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HalaNowTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    val json =
//                    Greeting("Android")
//                    val user  = UserData("Ahmed", 386839, "1", "1", "1234567890", "1234",
//                        "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkY4OUJCQjExMUNDOTM5ODcwMzM4QjA3MzY5RTYwMTdFOERCMzVDMEMiLCJ4NXQiOiItSnU3RVJ6Sk9ZY0RPTEJ6YWVZQmZvMnpYQXciLCJ0eXAiOiJhdCtqd3QifQ.eyJpc3MiOiJodHRwczovL2xvZ2luLXN0Zy5oYWxhLmNvbSIsIm5iZiI6MTcxODE3NjYwOCwiaWF0IjoxNzE4MTc2NjA4LCJleHAiOjE3MTgxNzY5MDgsImF1ZCI6WyJzY29wZV9sb29rdXBfZ3dfYXBpIiwic2NvcGVfbWVyY2hhbnRzX2d3X2FwaSIsInNjb3BlX3BheW1lbnRzX2d3X2FwaSIsInNjb3BlX3VzZXJfZ3dfYXBpIl0sInNjb3BlIjpbInNjb3BlX2xvb2t1cF9nd19hcGkiLCJzY29wZV9tZXJjaGFudHNfZ3dfYXBpIiwic2NvcGVfcGF5bWVudHNfZ3dfYXBpIiwic2NvcGVfdXNlcl9nd19hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsibW9iaWxlX290cCJdLCJjbGllbnRfaWQiOiJoYWxhX2dvbyIsInN1YiI6IjQwYjNlYzQ3LWNjNGQtNDAxNS04OGE0LTNjZWRkNDg2N2YzYyIsImF1dGhfdGltZSI6MTcxODE3NjYwOCwiaWRwIjoibG9jYWwiLCJzZXNzaW9uX2lkIjoiNWY2MjQ0MWEtZmZhYS00ZDU3LTgwOGMtNmZkODFmNTEyNjJiIiwiZV90eXBlIjoiMiIsImVfc3ViX3R5cGUiOiI2IiwibmF0aW9uYWxfaWQiOiIxMTExMTExMTExIiwibW9iaWxlIjoiNTEyNjAwMDAyIiwidmFsdWUiOiJUZXN0IDIiLCJ0aXRsZSI6IjQwYjNlYzQ3LWNjNGQtNDAxNS04OGE0LTNjZWRkNDg2N2YzYyIsImRhdGEiOiI1ZjYyNDQxYS1mZmFhLTRkNTctODA4Yy02ZmQ4MWY1MTI2MmIiLCJkZXZpY2VVVUlEIjoiNWU1ODViMDg4NGQyYjE0OCIsIlVzZXJJRCI6IjM4NjgzOSIsIkVudGl0eUlEIjoiMzg2ODM5IiwiZW50aXR5X2lkIjoiMzg2ODM5In0.UtWkwbuoHUdV8_i2NTfoVrySEa3aVFDDfDmtdSp3JDr-LKBB7Mhu_SiMI0Pt976N0JMgGoHd3BXXRsqr42bs8W0SKEUVSwIAamSM6HfcSl7LMEWR_AyHhxzsca6_ncZToLbaUymRoGuxaWyRFsJUUDqAuJ75o4HOwr8AynAFxvGYQYLTCY8jLbSCNHrF1bJuDU7bWGYaKnkxcctrsixnBxjkIbc9o3UTY1W3wyBDa5KF-I2K7y_DU2gkbj6UXA45yh0GhnwSCxUDlBIi3sIRFHrdTNP4EaQiwiPqqlRXUKSMe0JV47dyCoyOOLt09YF2lg5bQkc3m1MCcRgZVOdPgpQfXyfGja8pQH0POif71OQuiS0-8jXa0yAcy4mK4O02lq6qURS-qmI9jdP41TZKjm5gHboQgPs9POW_ICsjPuk45QuSxs52THeMRdWhbBMCRLLpA0eLNlreihvO_C1D7XADzDYF0EF4vM0XycuG9VkLLbxrJ7xSl3XPvyvyMSRYMjR0IUR9S_8ejTiVU5P_-9XJ-mHjFiRG7sEaWBTblXHragUbsFNGb8mZkgqNSnrWrDvOD9eM0Pfb5vHXaEX8C_yuWLGgZ47aEz31mXo3vQAm5_8-H-R6qItIS6Q_PF08LbJcPs5J2K0BR0DfwXevA_l-iKc7jKdKL_434-A-XYU",
//                        100f, 1, true, false, false, false)
//                    UserDataHolder.setUserData(user)
                }
            }
        }
    }
}

//fun createSupplierProfile(): SupplierProfile {
//    val inputFields = listOf(
//        SupplierInputField(
//            id = "64eec4c3c627304281cae0ae",
//            label = "Mobile",
//            hint = "59xxxxxxx",
//            dataType = "Mobile",
//            regex = null,
//            isOptional = false,
//            minLength = 9,
//            maxLength = 9
//        )
//    )
//
//    return SupplierProfile(
//        id = "6436f63ca530e07cbb4838a4",
//        isVisible = true,
//        salesmanEntityId = "588094",
//        companyEntityId = "588093",
//        salesmanEntityQRCode = "SDEF8F0E779DF",
//        brandNameAr = "CDS",
//        brandNameEn = "CDS",
//        brandLogoUrl = "https://cdn.hala.com/images/logos/cds2.png",
//        enableDescriptionLink = false,
//        descriptionAr = "",
//        descriptionEn = "",
//        descriptionLinkAndroid = "",
//        confirmationFieldId = "64eec4c3c627304281cae0ae",
//        payLaterAvailable = true,
//        payLaterRemainingAmount = 100.0f,
//        inputFields = listOf(),
//        autoRechargeExternalIdFieldId = "64eec4c3c627304281cae0ae",
//        autoRecharges = listOf()
//    )
//}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Text(
        text = "Hello $name!",
        modifier = modifier.clickable {
//            val intent = PayLaterActivity.getIntent(context)
//            intent.putExtra("entity", "")
//            intent.putExtra("supplierProfile", createSupplierProfile())
//            intent.putExtra("entityString", "")
//            PayLaterActivity.start(context, intent)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HalaNowTheme {
        Greeting("Android")
    }
}