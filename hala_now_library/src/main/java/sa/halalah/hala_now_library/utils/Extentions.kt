package sa.halalah.hala_now_library.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Resources.getSystem
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Outline
import android.graphics.Picture
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
import android.text.Editable
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.Px
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.launch
import sa.halalah.hala_now_library.R
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

fun EditText.showKeyboard(activity: Activity) {
    this.post {
        this.requestFocus()
        val runnable = Runnable {
            val keyboard =
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            keyboard.showSoftInput(this, 0)
        }
        this.postDelayed(runnable, 50)
    }
}

fun Float.amountToString(): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale.US)
    numberFormat.minimumFractionDigits = 2
    numberFormat.maximumFractionDigits = 2
    numberFormat.currency = Currency.getInstance("SAR")
    var enAmount: String = numberFormat.format(this)
    if (enAmount.takeLast(3) == ".00") {
        enAmount = enAmount.dropLast(3)
    }
    enAmount.replace("٠".toRegex(), "0").replace("٫".toRegex(), ".").replace("١".toRegex(), "1")
            .replace("٢".toRegex(), "2").replace("٣".toRegex(), "3").replace("٤".toRegex(), "4")
            .replace("٥".toRegex(), "5").replace("٦".toRegex(), "6").replace("٧".toRegex(), "7")
            .replace("٨".toRegex(), "8").replace("٩".toRegex(), "9")
    return enAmount
}

fun Float.amountToString(context: Context): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale.US)
    numberFormat.minimumFractionDigits = 2
    numberFormat.maximumFractionDigits = 2
    numberFormat.currency = Currency.getInstance("SAR")
   var enAmount: String = numberFormat.format(this)
    if (enAmount.takeLast(3) == ".00") {
        enAmount = enAmount.dropLast(3)
    }
    enAmount.replace("٠".toRegex(), "0").replace("٫".toRegex(), ".").replace("١".toRegex(), "1")
            .replace("٢".toRegex(), "2").replace("٣".toRegex(), "3").replace("٤".toRegex(), "4")
            .replace("٥".toRegex(), "5").replace("٦".toRegex(), "6").replace("٧".toRegex(), "7")
            .replace("٨".toRegex(), "8").replace("٩".toRegex(), "9")
    return enAmount + " " + context.resources.getString(R.string.currency)
}

fun Int.amountToString(context: Context): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale.US)
    numberFormat.minimumFractionDigits = 2
    numberFormat.maximumFractionDigits = 2
    numberFormat.currency = Currency.getInstance("SAR")
   var enAmount: String = numberFormat.format(this)
    if (enAmount.takeLast(3) == ".00") {
        enAmount = enAmount.dropLast(3)
    }
    enAmount.replace("٠".toRegex(), "0").replace("٫".toRegex(), ".").replace("١".toRegex(), "1")
            .replace("٢".toRegex(), "2").replace("٣".toRegex(), "3").replace("٤".toRegex(), "4")
            .replace("٥".toRegex(), "5").replace("٦".toRegex(), "6").replace("٧".toRegex(), "7")
            .replace("٨".toRegex(), "8").replace("٩".toRegex(), "9")
    return enAmount + " " + context.resources.getString(R.string.currency)
}

fun Double.amountToString(context: Context): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale.US)
    numberFormat.minimumFractionDigits = 2
    numberFormat.maximumFractionDigits = 2
    numberFormat.currency = Currency.getInstance("SAR")
   var enAmount: String = numberFormat.format(this)
    if (enAmount.takeLast(3) == ".00") {
        enAmount = enAmount.dropLast(3)
    }
    enAmount.replace("٠".toRegex(), "0").replace("٫".toRegex(), ".").replace("١".toRegex(), "1")
            .replace("٢".toRegex(), "2").replace("٣".toRegex(), "3").replace("٤".toRegex(), "4")
            .replace("٥".toRegex(), "5").replace("٦".toRegex(), "6").replace("٧".toRegex(), "7")
            .replace("٨".toRegex(), "8").replace("٩".toRegex(), "9")
    return enAmount + " " + context.resources.getString(R.string.currency)
}

fun Double.amountToString(): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale.US)
    numberFormat.minimumFractionDigits = 2
    numberFormat.maximumFractionDigits = 2
    numberFormat.currency = Currency.getInstance("SAR")
   var enAmount: String = numberFormat.format(this)
    if (enAmount.takeLast(3) == ".00") {
        enAmount = enAmount.dropLast(3)
    }
    enAmount.replace("٠".toRegex(), "0").replace("٫".toRegex(), ".").replace("١".toRegex(), "1")
        .replace("٢".toRegex(), "2").replace("٣".toRegex(), "3").replace("٤".toRegex(), "4")
        .replace("٥".toRegex(), "5").replace("٦".toRegex(), "6").replace("٧".toRegex(), "7")
        .replace("٨".toRegex(), "8").replace("٩".toRegex(), "9")
    return enAmount
}

fun Double.amountToStringWithoutCurrency(): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale.US)
    numberFormat.minimumFractionDigits = 2
    numberFormat.maximumFractionDigits = 2
    numberFormat.currency = Currency.getInstance("SAR")
   var enAmount: String = numberFormat.format(this)
    if (enAmount.takeLast(3) == ".00") {
        enAmount = enAmount.dropLast(3)
    }
    enAmount.replace("٠".toRegex(), "0").replace("٫".toRegex(), ".").replace("١".toRegex(), "1")
            .replace("٢".toRegex(), "2").replace("٣".toRegex(), "3").replace("٤".toRegex(), "4")
            .replace("٥".toRegex(), "5").replace("٦".toRegex(), "6").replace("٧".toRegex(), "7")
            .replace("٨".toRegex(), "8").replace("٩".toRegex(), "9")
    return enAmount
}

fun NavController.navigateUpOrFinish(activity: AppCompatActivity): Boolean {
    return if (popBackStack())
        true
    else {
        activity.finish()
        true
    }
}

fun Context.getAppName(): String {
    var appName = ""
    val applicationInfo = applicationInfo
    val stringId = applicationInfo.labelRes
    appName = if (stringId == 0) {
        applicationInfo.nonLocalizedLabel.toString()
    } else {
        getString(stringId)
    }
    return appName
}


fun View.avoidDoubleClicks() {
    val delayTime: Long = 900
    if (!this.isClickable) {
        return
    }
    this.isClickable = false
    this.postDelayed({ this.isClickable = true }, delayTime)
}

fun View.disableView() {
    this.isClickable = false
    this.isFocusable = false
    this.isFocusableInTouchMode = false
}

fun View.enableView() {
    this.isClickable = true
    this.isFocusable = true
    this.isFocusableInTouchMode = true
}


@SuppressLint("Range")
fun Uri.getFileNameFromUri(activity: Activity): String? {
    val fileName: String?
    val cursor = activity.applicationContext.contentResolver.query(this, null, null, null, null)
    cursor?.moveToFirst()
    fileName = cursor?.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
    cursor?.close()
    return fileName
}

fun PackageManager.getPackageInfoCompat(packageName: String, flags: Int = 0): PackageInfo =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(flags.toLong()))
        } else {
            @Suppress("DEPRECATION") getPackageInfo(packageName, flags)
        }

val Int.px: Int get() = (this * getSystem().displayMetrics.density).toInt()

val String.color
    get() = Color.parseColor(this)

fun Dialog.setFullScreen(
        @Px cornerRadius: Int = 0,
        skipCollapsed: Boolean = true
) {
    check(this is BottomSheetDialog) {
        "Dialog must be a BottomSheetBottomSheetDialog."
    }

    lifecycleScope.launch {
        whenStarted {
            val bottomSheetLayout =
                    findViewById<ViewGroup>(com.google.android.material.R.id.design_bottom_sheet)
                            ?: return@whenStarted
            with(bottomSheetLayout) {
                updateLayoutParams {
                    height = ViewGroup.LayoutParams.MATCH_PARENT
                }
                clipToOutline = true
                outlineProvider = object : ViewOutlineProvider() {
                    override fun getOutline(view: View, outline: Outline) {
                        outline.setRoundRect(
                                0,
                                0,
                                view.width,
                                view.height + cornerRadius,
                                cornerRadius.toFloat()
                        )
                    }
                }
            }
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.skipCollapsed = skipCollapsed
            behavior.isDraggable = false
        }
    }
}

fun Context.getActivity(): AppCompatActivity? = when (this) {
    is AppCompatActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

fun String.parseServerDate(): String {
    val inputFormat = if (this.contains(".")) {
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS", Locale.US)
    } else {
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
    }
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")

    val date: Date
    try {
        date = inputFormat.parse(this)!!
    } catch (e: ParseException) {
        e.printStackTrace()
        return ""
    }

    val cal = Calendar.getInstance()
    cal.time = date

    val day = cal.get(Calendar.DAY_OF_MONTH)
    val month = SimpleDateFormat("MMM", Locale.US).format(date)
    val hour = SimpleDateFormat("HH", Locale.US).format(date)
    val minute = SimpleDateFormat("mm", Locale.US).format(date)

    val dayWithSuffix = getDayWithSuffix(day)

    return "$dayWithSuffix $month, $hour:$minute"
}

fun getDayWithSuffix(day: Int): String {
    if (day in 11..13) {
        return "${day}th"
    }
    return when (day % 10) {
        1 -> "${day}st"
        2 -> "${day}nd"
        3 -> "${day}rd"
        else -> "${day}th"
    }
}

fun String.capitalizeFirstLetter(): String {
    val name = StringBuilder()
    for (i in this.indices) {
        if (i == 0) name.append(this[0].toString().uppercase(Locale.getDefault())) else name.append(this[i].toString().lowercase(Locale.getDefault()))
    }
    return name.toString()
}

fun String.toSecuredName(): String {
    var securedName = ""
    var securedFirstName = ""
    var securedSecondName = ""
    if (this.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().size <= 1) {
        var firstName = ""
        if (this.isNotEmpty()) firstName = this.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
        securedName = firstName[0].toString() + firstName.substring(1).replace("(1|2|3|4|5|6|7|8|9|a|A|b|B|C|c|D|d|E|e|F|f|G|g|H|h|I|i|J|j|K|k|L|l|M|m|N|n|O|o|P|p|Q|q|R|r|S|s|T|t|U|u|V|v|W|w|X|x|Y|y|Z|z|ا|ب|ت|ث|ج|ح|خ|د|ذ|ر|ز|س|ش|ص|ض|ط|ظ|ع|غ|ف|ق|ك|ل|م|ن|ه|و|ن|ي|ى|ئ|ة|إ|أ|ؤ|آ)".toRegex(), "*")
        return securedName
    } else if (this.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().size > 1) {
        var firstName = ""
        if (this.isNotEmpty()) firstName = this.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
        securedFirstName = firstName[0].toString() + firstName.substring(1).replace("(1|2|3|4|5|6|7|8|9|a|A|b|B|C|c|D|d|E|e|F|f|G|g|H|h|I|i|J|j|K|k|L|l|M|m|N|n|O|o|P|p|Q|q|R|r|S|s|T|t|U|u|V|v|W|w|X|x|Y|y|Z|z|ا|ب|ت|ث|ج|ح|خ|د|ذ|ر|ز|س|ش|ص|ض|ط|ظ|ع|غ|ف|ق|ك|ل|م|ن|ه|و|ن|ي|ى|ئ|ة|إ|أ|ؤ|آ)".toRegex(), "*")
        var secondName = ""
        secondName = this.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[this.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().size - 1]
        securedSecondName = secondName[0].toString() + secondName.substring(1).replace("(1|2|3|4|5|6|7|8|9|a|A|b|B|C|c|D|d|E|e|F|f|G|g|H|h|I|i|J|j|K|k|L|l|M|m|N|n|O|o|P|p|Q|q|R|r|S|s|T|t|U|u|V|v|W|w|X|x|Y|y|Z|z|ا|ب|ت|ث|ج|ح|خ|د|ذ|ر|ز|س|ش|ص|ض|ط|ظ|ع|غ|ف|ق|ك|ل|م|ن|ه|و|ن|ي|ى|ئ|ة|إ|أ|ؤ|آ)".toRegex(), "*")
        return "$securedFirstName $securedSecondName"
    }
    return securedName
}


fun Picture.createBitmapFromPicture(): Bitmap {
    // [START android_compose_draw_into_bitmap_convert_picture]
    val bitmap = Bitmap.createBitmap(
            this.width,
            this.height,
            Bitmap.Config.ARGB_8888
    )
    val canvas = android.graphics.Canvas(bitmap)
    canvas.drawColor(Color.WHITE)
    canvas.drawPicture(this)

    // [END android_compose_draw_into_bitmap_convert_picture]
    return bitmap
}
