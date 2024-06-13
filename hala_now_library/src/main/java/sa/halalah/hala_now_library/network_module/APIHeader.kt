package sa.halalah.business.ivr.data.models.request

data class APIHeader(
    val contentType:String = "application/json",
    val packageName:String = "sa.halalah.business",
    val ipAddress:String = "",
    val androidBuildNumber:String = "",
    val AndroidAppVersion:String = "",
    val userAgent:String = "",
)
