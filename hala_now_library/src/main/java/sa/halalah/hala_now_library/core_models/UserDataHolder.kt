package sa.halalah.hala_now_library.core_models

object UserDataHolder {
    private var userData: UserData = UserData()

    @JvmStatic
    fun getUserData() = userData

    @JvmStatic
    fun setUserData(userData: UserData) {
        this.userData = userData
    }
}

data class UserData(
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
