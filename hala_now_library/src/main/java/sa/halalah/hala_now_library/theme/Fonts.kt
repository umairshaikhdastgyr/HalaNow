package sa.halalah.hala_now_library.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import sa.halalah.hala_now_library.R


val fontFamily = FontFamily(
        Font(R.font.thin, FontWeight.Thin), //100
        Font(R.font.extra_light, FontWeight.ExtraLight), //200
        Font(R.font.light, FontWeight.Light), //300
        Font(R.font.normal, FontWeight.Normal), //400
        Font(R.font.medium, FontWeight.Medium), //500
        Font(R.font.semi_bold, FontWeight.SemiBold), //600
        Font(R.font.bold, FontWeight.Bold), //700
        Font(R.font.black, FontWeight.Black) //900
)

val MyTypography = Typography(
        fontFamily,
        h1 = TextStyle(fontSize = 48.sp, platformStyle = PlatformTextStyle(includeFontPadding = false)),
        h2 = TextStyle(fontSize = 40.sp, platformStyle = PlatformTextStyle(includeFontPadding = false)),
        h3 = TextStyle(fontSize = 36.sp, platformStyle = PlatformTextStyle(includeFontPadding = false)),
        h4 = TextStyle(fontSize = 32.sp, platformStyle = PlatformTextStyle(includeFontPadding = false)),
        h5 = TextStyle(fontSize = 28.sp, platformStyle = PlatformTextStyle(includeFontPadding = false)),
        h6 = TextStyle(fontSize = 26.sp, platformStyle = PlatformTextStyle(includeFontPadding = false)),
        caption = TextStyle(
                fontSize = 24.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false)
        ), //This represents h7
        body1 = TextStyle(
                fontSize = 20.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false)
        ),
        body2 = TextStyle(
                fontSize = 18.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false)
        ),
        button = TextStyle(
                fontSize = 16.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false)
        ),
        subtitle1 = TextStyle(
                fontSize = 14.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false)
        ),
        subtitle2 = TextStyle(
                fontSize = 12.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false)
        ),
)