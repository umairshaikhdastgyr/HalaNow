package sa.halalah.hala_now_library.pay_later.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AutoRecharge(
        val id: String = "",
        val rechargeAmount: String = "",
        val rechargeLimit: String = "0.0",
        val externalId: String = ""
) : Parcelable
