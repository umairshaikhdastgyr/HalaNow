package sa.halalah.hala_now_library.pay_later.view_models

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import sa.halalah.hala_now_library.R
import sa.halalah.hala_now_library.core_models.SDKDataHolder
import sa.halalah.hala_now_library.pay_later.models.CreatePaylaterOrderResponse
import sa.halalah.hala_now_library.pay_later.models.PayLaterOrderRequest
import sa.halalah.hala_now_library.pay_later.models.ProfileSupplier
import sa.halalah.hala_now_library.pay_later.repository.PayLaterRepository
import sa.halalah.hala_now_library.utils.amountToString

class FormViewModel : ViewModel() {


    private val _tweets = MutableStateFlow<List<String>>(emptyList())
    val tweets: StateFlow<List<String>>
        get() = _tweets

    private val payLaterAPIs = PayLaterRepository.payLateApiService()

    fun submitPayLater(
        payLaterOrderRequest: PayLaterOrderRequest,
        callback: (response: CreatePaylaterOrderResponse?) -> Unit
    ) {
        viewModelScope.launch {
            val result =  payLaterAPIs.submitPayLaterOrder(payLaterOrderRequest, entityId=SDKDataHolder.getUserData().entityId.toString())

            if(result.isSuccessful){
                callback(result.body())
            }else{
                callback(null)
            }
        }
    }


    fun upload(fileUri: Uri, isFile: Boolean, onUploadCompleted: (isUploaded: Boolean, uri: Uri?) -> Unit) {
//        StorageService.instance()?.upload(
//            file = fileUri,
//            path = StorageServiceInterface.Path.valueOf(StorageServiceInterface.Path.BNPLAttachments.name),
//            isFile = isFile,
//            entityId = UserDataHolder.getUserData().entityId.toString(),
//            callBacks = object : StorageServiceInterface.Callbacks {
//                override fun onUploadCompleted(uri: Uri) {
//                    onUploadCompleted(true,uri)
//                }
//
//                override fun onUploadFailed(error: String) {
//                    onUploadCompleted(false,null)
//                }
//
//                override fun onProgress(progress: Double) {
//
//                }
//            }
//        )
    }


    fun validateInput(
        amount: String,
        profileSupplier: ProfileSupplier,
        additionalFields: HashMap<String, String>,
    ): Triple<Boolean, Int, String> {

        val isError = profileSupplier.inputFields.any {
            val text = additionalFields[it.label].orEmpty()
            (it.isOptional && text.length < it.minLength) || (!it.isOptional && text.length < it.minLength)
        }

        if (amount == "" || amount.last() == '.') {
            return Triple(false, R.string.please_enter_a_valid_amount, "")
        } else if (amount == "0") {
            return Triple(false,R.string.amount_should_be_more_than,
                profileSupplier.payLaterRemainingAmount.amountToString())
        } else if (amount.toDouble() > profileSupplier.payLaterRemainingAmount) {
            return Triple(false,  R.string.amount_should_be_more_than,
                profileSupplier.payLaterRemainingAmount.amountToString()
            )
        } else if (isError) {
            return Triple(false, R.string.please_fill_all_mandatory_fields, "")
        } else {
            return Triple(true, 0,"")
        }
    }
}