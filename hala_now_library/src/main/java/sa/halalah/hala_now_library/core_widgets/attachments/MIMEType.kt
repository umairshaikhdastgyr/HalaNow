package com.hala.module_core.compose.attachments

enum class MIMEType(val type: String) {
    IMAGE("image/*"),
    PDF("application/pdf"),
    DOC_OR_DOCX("application/msword"),
    ANY("*/*"),
}
