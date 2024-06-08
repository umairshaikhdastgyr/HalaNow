package sa.halalah.hala_now_library.pay_later.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class SupplierProfile(
        val id: String = "",
        val isVisible: Boolean = false,
        val salesmanEntityId: String = "",
        val companyEntityId: String = "",
        val salesmanEntityQRCode: String = "",
        val brandNameAr: String = "",
        val brandNameEn: String = "",
        val brandLogoUrl: String = "",
        val enableDescriptionLink: Boolean = false,
        val descriptionAr: String = "",
        val descriptionEn: String = "",
        val descriptionLinkAndroid: String = "",
        val confirmationFieldId: String? = "",
        val payLaterAvailable: Boolean = false,
        var payLaterRemainingAmount: Float = 0.0f,
        val inputFields: List<SupplierInputField> = listOf(),
        val autoRechargeExternalIdFieldId: String? = "",
        val autoRecharges: List<AutoRecharge> = listOf()
) : Parcelable


