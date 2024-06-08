package sa.halalah.hala_now_library.pay_later.models

data class ConfirmPayLaterOrderResponse(
    val additionalFields: AdditionalFields,
    val amount: Double,
    val approved: Boolean,
    val attachment: String,
    val brandLogoUrl: String,
    val brandNameAr: String,
    val brandNameEn: String,
    val createdOn: String,
    val id: String,
    val memo: String,
    val paymentDueOn: String,
    val salesmanEntityQRCode: String,
    val status: Int,
    val statusColor: String,
    val statusDescription: String,
    val trx: Any,
    val trxOn: String
)