package sa.halalah.hala_now_library.pay_later.widgets

import HorizontalDashedBorder
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import sa.halalah.hala_now_library.R
import sa.halalah.hala_now_library.theme.MyTypography

@Composable
fun PaymentsCoinsView(items: List<Triple<String, String, String>>, noView: Boolean = false) {

    val updateList = if (items.count() <= 4) {
        items
    } else {

        listOf(
            Triple(items[0].first, stringResource(R.string._1st_payment), items[0].third),
            Triple("", "+ ${items.count() - 2}", stringResource(R.string.installments)),
            Triple(items.last().first, stringResource(R.string.last_payment), items.last().third)
        )
    }
    Box(modifier = Modifier.padding(bottom = 15.dp), contentAlignment = Alignment.BottomCenter) {
        HorizontalDashedBorder(
            color = colorResource(id = R.color.text_10),
            strokeWidth = 1.dp,
            dashWidth = 5.dp,
            dashGap = 4.dp,
            height = 2.dp,
            modifier = Modifier.padding(start = 5.dp, bottom = 48.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            updateList.forEachIndexed { index, element ->
                Column(
                    modifier = Modifier.padding(top = 10.dp),
                    horizontalAlignment = if (index == 0) Alignment.Start else if (index == updateList.lastIndex) Alignment.End else Alignment.CenterHorizontally
                ) {
                    if (element.first != "") {
                        Text(
                            text = element.first,
                            style = MyTypography.subtitle1,
                            fontWeight = FontWeight.Medium,
                            color = colorResource(id = R.color.text_10),
                            modifier = Modifier.padding(bottom = if (index == updateList.lastIndex) 7.dp else 20.dp)
                        )
                    }
                    var mm: Modifier = Modifier
                    if (index == updateList.lastIndex) {
                        mm = mm
                            .height(36.dp)
                            .width(40.dp)
                    } else if (index == 0) {
                        mm = mm.height(20.dp)
                    } else {
                        mm = mm.height(20.dp)
                    }

                    Image(
                        painter = painterResource(id = if (element.third == stringResource(R.string.installments)) R.drawable.coins_riyal else if (index == updateList.lastIndex) R.drawable.payment_success else R.drawable.coin_riyal),
                        contentDescription = "",
                        modifier = mm
                    )
                    Text(
                        text = element.second,
                        style = MyTypography.subtitle1,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.text_2),
                        modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
                    )
                    Text(
                        text = element.third,
                        style = MyTypography.subtitle1,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.text_2),
                        modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 0.dp)
                    )
                }
            }
        }
    }

    if (noView) {
        Box(
            modifier = Modifier.background(
                color = colorResource(id = R.color.secondary_100),
                shape = RoundedCornerShape(40.dp)
            )
        ) {
            Text(
                text = stringResource(R.string.no_interest_no_fee),
                style = MyTypography.subtitle1,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.text_2),
                modifier = Modifier.padding(20.dp, 10.dp)
            )
        }
    }
}



