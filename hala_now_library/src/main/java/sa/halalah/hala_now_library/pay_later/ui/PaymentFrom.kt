package sa.halalah.hala_now_library.pay_later.ui

import Border
import android.net.Uri
import android.os.Build
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import sa.halalah.hala_now_library.core_widgets.inputfields.InputField
import sa.halalah.hala_now_library.core_widgets.inputfields.MobileInputField
import sa.halalah.hala_now_library.core_widgets.inputfields.SuffixTransformation
import sa.halalah.hala_now_library.R
import sa.halalah.hala_now_library.core_widgets.HalaButton
import sa.halalah.hala_now_library.core_widgets.inputfields.NoBackgroundTextField
import sa.halalah.hala_now_library.core_widgets.loadingModal
import sa.halalah.hala_now_library.pay_later.models.DynamicInputFieldInputType
import sa.halalah.hala_now_library.pay_later.models.SupplierInputField
import sa.halalah.hala_now_library.pay_later.models.SupplierProfile
import sa.halalah.hala_now_library.pay_later.view_models.FormViewModel
import sa.halalah.hala_now_library.theme.MyTypography
import sa.halalah.hala_now_library.utils.amountToString
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun PaymentsForm(
    navController: NavHostController,
    viewModel: FormViewModel = viewModel()
) {

    // Accessing the current context and activity
    val context = LocalContext.current
    val activity = context as PayLaterActivity

    // Regular expression for validating input
    val regex = "(\\d{0,6})|(\\d{0,6}\\.\\d{0,2})".toRegex()

    // Retrieving supplier profile from intent
    val supplierProfile: SupplierProfile =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            activity.intent.getParcelableExtra("supplierProfile", SupplierProfile::class.java)
                ?: SupplierProfile()
        } else {
            activity.intent.getParcelableExtra("supplierProfile") ?: SupplierProfile()
        }

    // Mutable state for amount and notes
    var amount by remember { mutableStateOf("0") }
    var notes by remember { mutableStateOf("") }

    // Additional fields
    val additionalFields: HashMap<String, String> = remember { HashMap() }

    // State for invoice image
    var inVoiceImage: Uri? by remember { mutableStateOf(null) }
    var inVoiceUri: Uri? by remember { mutableStateOf(null) }

    val loading = loadingModal(false)

    // Focus manager
    val focusManager = LocalFocusManager.current


    // Composable UI structure
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
        ) {
            // Back arrow
            Image(
                painter = painterResource(id = R.drawable.back_arrow_new),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 10.dp)
                    .size(18.dp)
                    .clickable {
                        activity.finish()
                    }
            )
            // Header text
            Text(
                text = stringResource(id = R.string.amount_field_enter_amount),
                style = MyTypography.caption,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.text_2),
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Amount input field
        BasicTextField(
            value = amount,
            onValueChange = {
                if (it.matches(regex)) {
                    amount = if (it == "") "" else {
                        if (amount == "0") {
                            var newValue = it
                            newValue = newValue.replace("0", "")
                            newValue = newValue.replace(".", "")
                            if (newValue == "") {
                                ""
                            } else {
                                newValue
                            }
                        } else {
                            it
                        }
                    }
                }
            },

            textStyle = MaterialTheme.typography.h4.copy(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.text_10)
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 20.dp)
                .onFocusChanged {
                    if (amount == "") {
                        amount = "0"
                    }
                },
            visualTransformation = SuffixTransformation(" ${stringResource(id = R.string.currency)}"),
        )

        // Remaining balance section
        // Note: Implement PayLaterRemainingAmount class and its methods accordingly
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier
                    .padding(0.dp, 20.dp)
                    .background(
                        colorResource(id = R.color.surface_3),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(25.dp, 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    // Display remaining balance
                    val text = buildAnnotatedString {
                        pushStyle(SpanStyle(fontWeight = FontWeight.Medium, fontSize = 18.sp))
                        append(supplierProfile.payLaterRemainingAmount.amountToString())
                        pushStyle(SpanStyle(fontWeight = FontWeight.Medium, fontSize = 13.sp))
                        append(" " + stringResource(id = R.string.currency))
                    }
                    Text(
                        text = text,
                        style = MyTypography.subtitle1,
                        fontWeight = FontWeight.Medium,
                        color = colorResource(id = R.color.text_2),
                        modifier = Modifier
                    )
                    // Text indicating available pay later balance
                    Text(
                        text = stringResource(R.string.available_pay_later_balance),
                        style = MyTypography.subtitle2,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.text_2),
                        modifier = Modifier
                    )
                }
                // Wallet icon
                Image(
                    painter = painterResource(id = R.drawable.wallet),
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                )
            }

            // Additional input fields
            Column {
                supplierProfile.inputFields.forEach { it ->
                    DynamicInputs(it) { value ->
                        additionalFields[it.label] = value
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            ) {
                val disablePicker = remember { mutableStateOf(false) }
//                AddAttachments(
//                    label = stringResource(id = R.string.add_invoice_optional),
//                    isMultipleDocuments = false,
//                    disablePickerButton = disablePicker,
//                    attachments = if (inVoiceUri != null) listOf(inVoiceUri!!) else emptyList<Uri>(),
//                    mimeTypes = listOf(MIMEType.IMAGE),
//                    onAttachmentsAdded = { uriList, isFile ->
//                        loading(true)
//                        paymentFormViewModel.upload(uriList[0], isFile) { isUploaded, uri ->
//                            loading(false)
//                            if (isUploaded) {
//                                inVoiceUri = uriList[0]
//                                inVoiceImage = uri
//                            } else {
//                                UIUtil.showError(
//                                    activity = activity,
//                                    message = context.resources.getString(R.string.something_went_wrong)
//                                )
//                            }
//                        }
//                        disablePicker.value = true
//                    },
//                    onAttachmentRemoved = {
//                        inVoiceImage = null
//                        disablePicker.value = false
//                    }
//                )
            }


            // Notes section
            Text(
                text = stringResource(R.string.notes_optional),
                style = MyTypography.subtitle1,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.text_2),
                modifier = Modifier.padding(top = 10.dp, start = 5.dp)
            )

            // Text field for entering notes
            Row(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .background(
                        colorResource(id = R.color.surface_3),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(3.dp, 3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NoBackgroundTextField(stringResource(R.string.enter_your_notes), notes) {
                    notes = it
                }
            }

        }

        Border()
        HalaButton(
            state = true,
            text = stringResource(R.string.continuee),
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(top = 15.dp),
            onClick = {
                focusManager.clearFocus()
//
//
//                val (isValid, id, arg) = paymentFormViewModel.validateInput(
//                    amount,
//                    supplierProfile,
//                    additionalFields
//                )
//
//                if (!isValid) {
//                    UIUtil.showError(
//                        activity = activity,
//                        message = if(arg != "") context.getString(id, arg) else context.getString(id)
//                    )
//                } else {
//                    val payLaterOrderRequest = PayLaterOrderRequest(
//                        additionalFields = additionalFields,
//                        amount = amount.toDouble(),
//                        attachment = (inVoiceImage ?: Uri.EMPTY).toString(),
//                        notes = notes,
//                        salesmanEntityId = supplierProfile.salesmanEntityId,
//                        supplierId = supplierProfile.id
//                    )
//                    loading(true)
//                    paymentFormViewModel.submitPayLater(payLaterOrderRequest) { response ->
//                        loading(false)
//                        if (response == null) {
//                            UIUtil.showError(
//                                activity = activity,
//                                message = context.resources.getString(R.string.something_went_wrong)
//                            )
//                        } else {
//                            val responseJson = Cbor.encodeToHexString(response)
//                            val imageAndNotes = Cbor.encodeToHexString(
//                                ImageAndNotes(
//                                    notes,
//                                    inVoiceUri?.toString() ?: ""
//                                )
//                            )
//                            navController.navigate(
//                                PaymentsFormActivityScreens.PaymentSummary.route.plus(
//                                    "/${responseJson}/${imageAndNotes}"
//                                )
//                            )
//                        }
//                    }
//                }
            },
        )
    }
}

@Composable
fun DynamicInputs(
    inputField: SupplierInputField,
    onTextChanged: (value: String) -> Unit
) {
    val text = remember {
        mutableStateOf("")
    }
    val isFocused = remember {
        mutableStateOf(false)
    }

    val errorText =
        if (text.value.length < inputField.minLength && text.value.isNotEmpty()) {
            stringResource(id = R.string.entry_shouldbe_at_least) + " ${inputField.minLength} " + stringResource(
                id = R.string.in_length
            )
        } else {
            ""
        }

    if (inputField.dataType == DynamicInputFieldInputType.MOBILE.type) {

        Text(
            text = inputField.label,
            style = MaterialTheme.typography.subtitle1,
            color = colorResource(id = R.color.text_3),
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        MobileInputField(
            inputValue = text.value,
            placeholder = inputField.hint ?: "",
            errorText = "",
            validateMobileNumber = {
                if (it.length <= inputField.maxLength) {
                    text.value = it
                    onTextChanged(it)
                }
            },
            onFocusChanged = {
                isFocused.value = it
            }
        )

    } else {
        InputField(
            text = text.value,
            labelText = inputField.label,
            placeholderText = (inputField.hint
                ?: "").ifEmpty { stringResource(id = R.string.enter) + " ${inputField.label}" },
            errorText = "",
            onValueChange = {
                if (it.length <= inputField.maxLength) {
                    text.value = it
                    onTextChanged(it)
                }
            },
            modifier = Modifier.onFocusChanged {
                isFocused.value = it.isFocused
            },
            keyboardOptions =
            when (inputField.dataType) {
                DynamicInputFieldInputType.TEXT.type -> KeyboardOptions(keyboardType = KeyboardType.Text)
                DynamicInputFieldInputType.NUMBER.type -> KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )

                DynamicInputFieldInputType.MOBILE.type -> KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )

                DynamicInputFieldInputType.EMAIL.type -> KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )

                else -> KeyboardOptions.Default
            }
        )
    }
    if (errorText.isNotEmpty() && !isFocused.value) {
        WaringMessage(errorText)
    }
}

@Composable
fun WaringMessage(error: String) {
    Row(
        modifier = Modifier
            .padding(5.dp, 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_warning),
            contentDescription = "",
            modifier = Modifier
                .size(14.dp)
        )

        Text(
            text = error,
            style = MyTypography.subtitle1,
            fontWeight = FontWeight.Normal,
            color = colorResource(id = R.color.error_50),
            modifier = Modifier.padding(start = 7.dp)
        )
    }
}



