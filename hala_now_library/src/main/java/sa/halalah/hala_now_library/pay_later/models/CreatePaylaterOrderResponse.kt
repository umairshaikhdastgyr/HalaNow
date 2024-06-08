package sa.halalah.hala_now_library.pay_later.models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class CreatePaylaterOrderResponse(
    val Supplier: Supplier?,
    val notes: String,
    val orderId: String,
    val payLaterOrderDetails: PayLaterOrderDetails?,
    val payLaterOrderInstallments: List<PayLaterOrderInstallment>?,
    val paymentDueOn: String
) :  Parcelable

@Parcelize
@Serializable
data class Supplier(
    val agentName: String,
    val contact: String,
    val iconURL: String,
    val name: String
):  Parcelable

@Parcelize
@Serializable
data class PayLaterOrderDetails(
    val amount: Double,
    val numberOfInstallments: Int,
    val orderEnd: String,
    val orderStart: String,
    val paymentMethod: String
) :  Parcelable

@Parcelize
@Serializable
data class PayLaterOrderInstallment(
    val amount: Double,
    val date: String
) : Parcelable

@Parcelize
@Serializable
data class ImageAndNotes(
    val notes: String,
    val image: String
) : Parcelable