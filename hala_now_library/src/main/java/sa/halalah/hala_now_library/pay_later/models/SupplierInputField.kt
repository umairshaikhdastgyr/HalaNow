package sa.halalah.hala_now_library.pay_later.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SupplierInputField(
        val id: String,
        val label: String = "",
        val hint: String? = "",
        val dataType: String = "",
        val regex: String? = "",
        val isOptional: Boolean = false,
        val minLength: Int = 0,
        val maxLength: Int = 0
) : Parcelable
