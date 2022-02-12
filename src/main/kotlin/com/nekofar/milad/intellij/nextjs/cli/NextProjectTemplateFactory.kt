package com.nekofar.milad.intellij.nextjs.cli

import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.lang.javascript.boilerplate.JavaScriptNewTemplatesFactoryBase

class NextProjectTemplateFactory : JavaScriptNewTemplatesFactoryBase() {
    override fun createTemplates(context: WizardContext?) = arrayOf(NextCliProjectGenerator())
}