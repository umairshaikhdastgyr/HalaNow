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
import sa.halalah.hala_now_library.core_models.UserData
import sa.halalah.hala_now_library.core_models.UserDataHolder
import sa.halalah.hala_now_library.pay_later.models.SupplierInputField
import sa.halalah.hala_now_library.pay_later.models.SupplierProfile
import sa.halalah.hala_now_library.pay_later.ui.PayLaterActivity

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
                    val json =
                    Greeting("Android")
                    val user  = UserData("Ahmed", 386839, "1", "1", "1234567890", "1234",
                        "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkY4OUJCQjExMUNDOTM5ODcwMzM4QjA3MzY5RTYwMTdFOERCMzVDMEMiLCJ4NXQiOiItSnU3RVJ6Sk9ZY0RPTEJ6YWVZQmZvMnpYQXciLCJ0eXAiOiJhdCtqd3QifQ.eyJpc3MiOiJodHRwczovL2xvZ2luLXN0Zy5oYWxhLmNvbSIsIm5iZiI6MTcxODEzMTQxNiwiaWF0IjoxNzE4MTMxNDE2LCJleHAiOjE3MTgxMzE3MTYsImF1ZCI6WyJzY29wZV9sb29rdXBfZ3dfYXBpIiwic2NvcGVfbWVyY2hhbnRzX2d3X2FwaSIsInNjb3BlX3BheW1lbnRzX2d3X2FwaSIsInNjb3BlX3VzZXJfZ3dfYXBpIl0sInNjb3BlIjpbInNjb3BlX2xvb2t1cF9nd19hcGkiLCJzY29wZV9tZXJjaGFudHNfZ3dfYXBpIiwic2NvcGVfcGF5bWVudHNfZ3dfYXBpIiwic2NvcGVfdXNlcl9nd19hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsibW9iaWxlX290cCJdLCJjbGllbnRfaWQiOiJoYWxhX2dvbyIsInN1YiI6IjQwYjNlYzQ3LWNjNGQtNDAxNS04OGE0LTNjZWRkNDg2N2YzYyIsImF1dGhfdGltZSI6MTcxODEzMTQxNiwiaWRwIjoibG9jYWwiLCJzZXNzaW9uX2lkIjoiMDM1MmU5MGYtNmJmYS00ODcxLWFmOGQtNTVlYzI0NDZlZDQxIiwiZV90eXBlIjoiMiIsImVfc3ViX3R5cGUiOiI2IiwibmF0aW9uYWxfaWQiOiIxMTExMTExMTExIiwibW9iaWxlIjoiNTEyNjAwMDAyIiwidmFsdWUiOiJUZXN0IDIiLCJ0aXRsZSI6IjQwYjNlYzQ3LWNjNGQtNDAxNS04OGE0LTNjZWRkNDg2N2YzYyIsImRhdGEiOiIwMzUyZTkwZi02YmZhLTQ4NzEtYWY4ZC01NWVjMjQ0NmVkNDEiLCJkZXZpY2VVVUlEIjoiNWU1ODViMDg4NGQyYjE0OCIsIlVzZXJJRCI6IjM4NjgzOSIsIkVudGl0eUlEIjoiMzg2ODM5IiwiZW50aXR5X2lkIjoiMzg2ODM5In0.kLwygrXzQ6z8dpuLemK1N4egKuIu_xDTlhAAAzMiK0Pha0KQ9c6zsVvAKQMQD-OHbAX4bYANsOzWYmjFHbagP4jsBjpV73BG09e93sCfaGEExbkkKxXQysYnq21kh0Wa-h_rSxLHsjIy4N0u10LWTu-aOPfeujcial_F-4IWa1z2zlYe6BXyfxFYq9uAGbkvPXxjU2aLKqOe6rBuMNvvp5ud9p0-ub-myW7sPmrKPLWJ4saum68JpSVJrwh83elgXxZIKs2uKaxw3sb1OzTyTbnPbigDP5pQg-J-B0YU58Pi1yEyQDvbBxqQj98RqGPh8mImv5cc37U6u1MntyHJ80QPuy_pjE-yc10413WR8AxRJ1Jncenf8ci90C_uBKav0wACr_qKCb4fPGnJuZ740PjlieAscodDy6kcCFMfJNBRz9zKad7_4_FFI3h98j4SRYSsRcc1nGwVIrfyMgo30ffQgihWUWyjPF4uDCgFaYRl0hHq-8Wq6tuTkpnJw-fxaY7L9kKVECuSDUWZLADBZ-87P0Q0Uw_jjCuc9u4AJc2QIYU-R7HAxnDBZt1u-QgZrTSDI2AqrTKMBXPsfIJQkqL7WIEuyKnzOQ8YhBhbJdlemViYpQTk4hND3DJYWIQgKh32ZUnV5PU_NCMQPC3iSWVJH4OEs-4MXpca9TY7gjc",
                        100f, 1, true, false, false, false)
                    UserDataHolder.setUserData(user)
                }
            }
        }
    }
}

fun createSupplierProfile(): SupplierProfile {
    val inputFields = listOf(
        SupplierInputField(
            id = "64eec4c3c627304281cae0ae",
            label = "Mobile",
            hint = "59xxxxxxx",
            dataType = "Mobile",
            regex = null,
            isOptional = false,
            minLength = 9,
            maxLength = 9
        )
    )

    return SupplierProfile(
        id = "6436f63ca530e07cbb4838a4",
        isVisible = true,
        salesmanEntityId = "588094",
        companyEntityId = "588093",
        salesmanEntityQRCode = "SDEF8F0E779DF",
        brandNameAr = "CDS",
        brandNameEn = "CDS",
        brandLogoUrl = "https://cdn.hala.com/images/logos/cds2.png",
        enableDescriptionLink = false,
        descriptionAr = "",
        descriptionEn = "",
        descriptionLinkAndroid = "",
        confirmationFieldId = "64eec4c3c627304281cae0ae",
        payLaterAvailable = true,
        payLaterRemainingAmount = 100.0f,
        inputFields = listOf(),
        autoRechargeExternalIdFieldId = "64eec4c3c627304281cae0ae",
        autoRecharges = listOf()
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Text(
        text = "Hello $name!",
        modifier = modifier.clickable {
            val intent = PayLaterActivity.getIntent(context)
            intent.putExtra("entity", "")
            intent.putExtra("supplierProfile", createSupplierProfile())
            intent.putExtra("entityString", "")
            PayLaterActivity.start(context, intent)
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