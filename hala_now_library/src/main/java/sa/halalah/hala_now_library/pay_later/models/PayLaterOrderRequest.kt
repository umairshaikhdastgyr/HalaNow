package sa.halalah.hala_now_library.pay_later.models

data class PayLaterOrderRequest(
    val additionalFields: HashMap<String, String>,
    val amount: Double,
    val attachment: String,
    val notes: String,
    val salesmanEntityId: String,
    val requestId:  String? = null,
    val supplierId: String,
)