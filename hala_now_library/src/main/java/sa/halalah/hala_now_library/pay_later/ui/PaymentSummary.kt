package sa.halalah.hala_now_library.pay_later.ui

import Border
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import sa.halalah.hala_now_library.core_widgets.inputfields.KeyValueText
import kotlinx.serialization.cbor.Cbor
import kotlinx.serialization.decodeFromHexString
import sa.halalah.hala_now_library.R
import sa.halalah.hala_now_library.authentication.ui.IntentActivity
import sa.halalah.hala_now_library.core_models.UserDataHolder
import sa.halalah.hala_now_library.core_widgets.HalaButton
import sa.halalah.hala_now_library.core_widgets.InformationModal
import sa.halalah.hala_now_library.core_widgets.TitleBar
import sa.halalah.hala_now_library.core_widgets.loadingModal
import sa.halalah.hala_now_library.pay_later.models.ConfirmPaylaterRequest
import sa.halalah.hala_now_library.pay_later.models.ConfirmationPayLaterViewState
import sa.halalah.hala_now_library.pay_later.models.CreatePaylaterOrderResponse
import sa.halalah.hala_now_library.pay_later.models.ImageAndNotes
import sa.halalah.hala_now_library.pay_later.models.PayLaterOrderDetails
import sa.halalah.hala_now_library.pay_later.models.PayLaterOrderInstallment
import sa.halalah.hala_now_library.pay_later.models.Supplier
import sa.halalah.hala_now_library.pay_later.view_models.FormViewModel
import sa.halalah.hala_now_library.pay_later.view_models.PaymentSummaryViewModel
import sa.halalah.hala_now_library.pay_later.widgets.PaymentsCoinsView
import sa.halalah.hala_now_library.theme.MyTypography
import sa.halalah.hala_now_library.utils.DateUtils.formatDate
import sa.halalah.hala_now_library.utils.amountToString


@Composable
fun PaymentSummary(
    navController: NavHostController, args: Bundle?,
    paymentSummaryViewModel: PaymentSummaryViewModel = viewModel()
) {

    val context = LocalContext.current
    val activity = context as PayLaterActivity
    val confirmPayLaterState by paymentSummaryViewModel.confirmPayLaterRes.collectAsState()


    val payLaterOrdersResponse: CreatePaylaterOrderResponse? = if (args != null) {
        val paymentSummary = args.getString("paymentSummary") ?: ""
        if (paymentSummary != "") Cbor.decodeFromHexString<CreatePaylaterOrderResponse>(
            paymentSummary
        ) else null
    } else {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            activity.intent.getParcelableExtra(
                "orderResponse",
                CreatePaylaterOrderResponse::class.java
            )
        } else {
            context.intent.getParcelableExtra("orderResponse")
        }
    }

    val intentAuthLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val errorData = (confirmPayLaterState as ConfirmationPayLaterViewState.IntentAuthRequired).response
                val payload =
                    ConfirmPaylaterRequest(orderId = payLaterOrdersResponse?.orderId ?: "",   reqId = errorData.reqId)
                paymentSummaryViewModel.confirmPayLater(payload)
            }else{
                paymentSummaryViewModel.resetConfirmPayLateState()
            }
        }

    val imageAndNotesArgs = args?.getString("imageAndNotes") ?: ""
    val imageAndNotes = if (imageAndNotesArgs != "") Cbor.decodeFromHexString<ImageAndNotes>(
        imageAndNotesArgs
    ) else  ImageAndNotes("", "")

    Column {
        TitleBar(title = "Payment Summary") {
            if (args == null) {
                activity.finish()
            } else {
                navController.popBackStack();
            }
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
                .background(color = colorResource(id = R.color.overlay_5))
                .verticalScroll(rememberScrollState()),
        ) {

            if (payLaterOrdersResponse == null) {
                return
            }

            if (!payLaterOrdersResponse.payLaterOrderInstallments.isNullOrEmpty()) EasyInstallments(
                payLaterOrdersResponse.payLaterOrderInstallments
            )
            if (payLaterOrdersResponse.payLaterOrderDetails != null) PaymentDetails(
                payLaterOrdersResponse.payLaterOrderDetails
            )
            if (payLaterOrdersResponse.Supplier != null) SupplierDetails(payLaterOrdersResponse.Supplier)
            if (imageAndNotes.notes != "" || imageAndNotes.image != "" ) NotesAttachment(
                imageAndNotes
            )
            Spacer(modifier = Modifier.height(15.dp))
        }

        Border()


        when (confirmPayLaterState) {
            is ConfirmationPayLaterViewState.Data -> {
                val intent = Intent("UPDATE_Suppliers_Home")
                LocalBroadcastManager
                    .getInstance(context)
                    .sendBroadcast(intent)
                InformationModal(
                    true,
                    stringResource(R.string.payment_successful),
                    "",
                    R.raw.done_animation,
                    onClose = {
                        paymentSummaryViewModel.resetConfirmPayLateState()
                        activity.finish()
                    }
                ) {
                    paymentSummaryViewModel.resetConfirmPayLateState()
                    activity.finish()
                }
            }

            is ConfirmationPayLaterViewState.Error -> {
                InformationModal(
                    true,
                    stringResource(R.string.error),
                    (confirmPayLaterState as ConfirmationPayLaterViewState.Error).message,
                    R.raw.error_animation
                ) {
                    paymentSummaryViewModel.resetConfirmPayLateState()
                }
            }

            is ConfirmationPayLaterViewState.IntentAuthRequired -> {
                val errorData = (confirmPayLaterState as ConfirmationPayLaterViewState.IntentAuthRequired).response
                intentAuthLauncher.launch(
                    IntentActivity.getIntent(context = activity, mobile = UserDataHolder.getUserData().mobileNumber, reqId = errorData.reqId)
                )
            }

            ConfirmationPayLaterViewState.Loading -> {
                loadingModal(true)
            }

            else -> {
            }
        }


        HalaButton(
            state = true,
            text = stringResource(R.string.confirm),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 10.dp)
                .align(Alignment.CenterHorizontally),
            onClick = {
                val payload =
                    ConfirmPaylaterRequest(payLaterOrdersResponse?.orderId ?: "")
                paymentSummaryViewModel.confirmPayLater(payload)
            },
        )

    }
}


@Composable
fun EasyInstallments(payLaterOrderInstallments: List<PayLaterOrderInstallment>) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 20.dp)
            .background(
                color = colorResource(id = R.color.surface_3),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(20.dp),


        ) {

        Text(
            text = stringResource(
                R.string.pay_in_easy_installments,
                payLaterOrderInstallments.count()
            ),
            style = MyTypography.subtitle1,
            fontWeight = FontWeight.Normal,
            color = colorResource(id = R.color.text_1),
            modifier = Modifier
        )

        val coinViewData = payLaterOrderInstallments.map {
            Triple(
                it.amount.amountToString(context),
                stringResource(R.string.due_on),
                formatDate(it.date, "dd MMM")
            )
        }
        PaymentsCoinsView(coinViewData, true)

    }

}


@Composable
fun PaymentDetails(payLaterOrderDetails: PayLaterOrderDetails) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.surface_3),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(20.dp),


        ) {
        Text(
            text = stringResource(R.string.payment_details),
            style = MyTypography.subtitle1,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.text_1),
            modifier = Modifier
        )
        TwoText(
            text = stringResource(R.string.payment_method),
            text2 = payLaterOrderDetails.paymentMethod
        )
        TwoText(
            text = stringResource(R.string.total_amount),
            text2 = payLaterOrderDetails.amount.amountToString(LocalContext.current)
        )
        TwoText(
            text = stringResource(R.string.numbers_of_installment),
            text2 = payLaterOrderDetails.numberOfInstallments.toString()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            TwoText(
                text = stringResource(R.string.loan_start_date),
                text2 = formatDate(payLaterOrderDetails.orderStart),
                modifier = Modifier.weight(0.6f)
            )
            TwoText(
                text = stringResource(R.string.loan_end_date),
                text2 = formatDate(payLaterOrderDetails.orderEnd),
                modifier = Modifier.weight(0.4f)
            )
        }

    }

}


@Composable
fun SupplierDetails(payLaterOrdersResponse: Supplier) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .background(
                color = colorResource(id = R.color.surface_3),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(20.dp),


        ) {
        Text(
            text = stringResource(R.string.supplier_details),
            style = MyTypography.subtitle1,
            fontWeight = FontWeight.Normal,
            color = colorResource(id = R.color.text_1),
            modifier = Modifier.padding(bottom = 3.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            TwoText(
                text = stringResource(R.string.supplier_name),
                text2 = payLaterOrdersResponse.name
            )
            AsyncImage(
                model = payLaterOrdersResponse.iconURL,
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 10.dp)
                    .size(40.dp)
                    .clip(CircleShape)

            )
        }
        if (payLaterOrdersResponse.contact != "") TwoText(
            text = stringResource(R.string.agent_phone),
            text2 = payLaterOrdersResponse.contact
        )
    }

}


@Composable
fun NotesAttachment(imageAndNotes: ImageAndNotes) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
            .background(
                color = colorResource(id = R.color.surface_3),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(20.dp),


        ) {
        Text(
            text = stringResource(id = R.string.payment_details),
            style = MyTypography.subtitle1,
            fontWeight = FontWeight.Normal,
            color = colorResource(id = R.color.text_1),
        )
        if (imageAndNotes.notes != "") TwoText(
            text = stringResource(R.string.notes),
            text2 = imageAndNotes.notes
        )

        if (imageAndNotes.image != "") {
            Text(
                text = stringResource(R.string.attachment),
                style = MyTypography.subtitle1,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.text_3),
                modifier = Modifier.padding(top = 10.dp)
            )
            val image = rememberAsyncImagePainter(model = imageAndNotes.image.toUri())
            Image(
                painter = image,
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 10.dp)
                    .size(60.dp)

            )
        }

    }

}


@Composable
fun TwoText(text: String, text2: String, modifier: Modifier = Modifier) {

    KeyValueText(
        modifier = modifier.padding(top = 16.dp),
        key = text,
        keyTextColor = colorResource(id = R.color.text_3),
        keyTextStyle = MyTypography.subtitle1,
        value = text2,
        valueTextColor = colorResource(id = R.color.text_1),
        valueTextStyle = MyTypography.subtitle1,
    )

}
