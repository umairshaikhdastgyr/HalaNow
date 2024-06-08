package sa.halalah.hala_now_library.pay_later.models

data class ConfirmPaylaterRequest(
        val orderId: String,
        val reqId: String = ""
)
