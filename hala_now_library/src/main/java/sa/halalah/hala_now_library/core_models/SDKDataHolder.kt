package sa.halalah.hala_now_library.core_models

object SDKDataHolder {
    private var userData: UserInfo = UserInfo()

    @JvmStatic
    fun getUserData() = userData

    @JvmStatic
    fun setUserData(userData: UserInfo) {
        this.userData = userData
    }
}

data class UserInfo(
    val name: String = "",
    val entityId: Int = 0,
    val entityTypeId: String = "",
    val entitySubTypeId: String = "",
    val mobileNumber: String = "",
    val pin: String = "",
    val token: String = "",
    val availableBalance: Float = 0f,
    val isRaya: Int = 0,
    val isOperatorEnableViewTransactions: Boolean = false,
    val isCrExpired: Boolean = false,
    val isManagerNationalIdExpired: Boolean = false,
    val isCashierNationalIdExpired: Boolean = false
)
