package com.nekofar.milad.intellij.nextjs.cli

import com.intellij.ide.util.projectWizard.WebTemplateNewProjectWizard
import com.intellij.ide.wizard.GeneratorNewProjectWizardBuilderAdapter

class NextCliProjectModuleBuilder :
    GeneratorNewProjectWizardBuilderAdapter(WebTemplateNewProjectWizard(NextCliProjectGenerator()))