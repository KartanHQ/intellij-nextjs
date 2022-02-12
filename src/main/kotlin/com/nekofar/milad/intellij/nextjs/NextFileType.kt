package com.nekofar.milad.intellij.nextjs

import com.intellij.lang.javascript.JavascriptLanguage
import com.intellij.openapi.fileTypes.LanguageFileType

object NextFileType : LanguageFileType(JavascriptLanguage.INSTANCE) {
    override fun getIcon() = NextIcons.FileType
    override fun getName() = NextBundle.message("next.file.type.name")
    override fun getDescription() = NextBundle.message("next.file.type.description")
    override fun getDefaultExtension() = "js"
}
