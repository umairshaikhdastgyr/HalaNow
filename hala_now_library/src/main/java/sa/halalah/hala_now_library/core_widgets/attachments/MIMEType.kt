package sa.halalah.hala_now_library.core_widgets.attachments

enum class MIMEType(val type: String) {
    IMAGE("image/*"),
    PDF("application/pdf"),
    DOC_OR_DOCX("application/msword"),
    ANY("*/*"),
}
