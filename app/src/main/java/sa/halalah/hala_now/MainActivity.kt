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
import sa.halalah.hala_now_library.pay_later.ui.PaymentsFormActivity

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
                    Greeting("Android")
                    val user  = UserData("Ahmed", 1, "1", "1", "1234567890", "1234", "1234567890", 100f, 1, true, false, false, false)
                    UserDataHolder.setUserData(user)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Text(
        text = "Hello $name!",
        modifier = modifier.clickable {
            PaymentsFormActivity.start(context)
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