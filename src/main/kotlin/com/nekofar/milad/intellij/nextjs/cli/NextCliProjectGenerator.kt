package com.nekofar.milad.intellij.nextjs.cli

import com.intellij.execution.filters.Filter
import com.intellij.ide.util.projectWizard.SettingsStep
import com.intellij.javascript.CreateRunConfigurationUtil
import com.intellij.lang.javascript.boilerplate.NpmPackageProjectGenerator
import com.intellij.lang.javascript.boilerplate.NpxPackageDescriptor
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ContentEntry
import com.intellij.openapi.util.Key
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.ProjectGeneratorPeer
import com.intellij.ui.components.JBCheckBox
import com.nekofar.milad.intellij.nextjs.NextBundle
import com.nekofar.milad.intellij.nextjs.NextIcons
import javax.swing.JPanel

class NextCliProjectGenerator : NpmPackageProjectGenerator() {
    private val packageName = "create-next-app"
    private val npxCommand = "create-next-app"

    private val typeScriptKey = Key.create<Boolean>("next.project.generator.typescript.project")
    private val typeScriptInitial = false

    override fun getIcon() = NextIcons.ProjectGenerator
    override fun getName() = NextBundle.message("next.project.generator.name")
    override fun getDescription() = NextBundle.message("next.project.generator.description")
    override fun packageName() = packageName
    override fun presentablePackageName() = NextBundle.message("next.project.generator.presentable.package.name")
    override fun getNpxCommands() = listOf(NpxPackageDescriptor.NpxCommand(packageName, npxCommand))
    override fun filters(project: Project, baseDir: VirtualFile): Array<Filter> = emptyArray()
    override fun customizeModule(baseDir: VirtualFile, entry: ContentEntry?) { /* Do nothing */ }

    override fun createPeer(): ProjectGeneratorPeer<Settings> {
        val typeScriptCheckbox = JBCheckBox(
            NextBundle.message("next.project.generator.typescript.checkbox"),
            typeScriptInitial
        )

        return object : NpmPackageGeneratorPeer() {
            override fun buildUI(settingsStep: SettingsStep) {
                super.buildUI(settingsStep)
                settingsStep.addSettingsComponent(typeScriptCheckbox)
            }

            override fun getSettings(): Settings {
                val settings = super.getSettings()
                settings.putUserData(
                    typeScriptKey,
                    typeScriptCheckbox.isSelected
                )
                return settings
            }

            override fun createPanel(): JPanel {
                val panel = super.createPanel()
                panel.add(typeScriptCheckbox)
                return panel
            }
        }
    }

    override fun generatorArgs(project: Project?, dir: VirtualFile?, settings: Settings?): Array<String> {
        val typeScript = settings?.getUserData(typeScriptKey) ?: typeScriptInitial
        return if(typeScript) arrayOf("--typescript", ".") else arrayOf(".")
    }

    override fun onGettingSmartAfterProjectGeneration(project: Project, baseDir: VirtualFile) {
        super.onGettingSmartAfterProjectGeneration(project, baseDir)
        CreateRunConfigurationUtil.npmConfiguration(project, "dev")
        CreateRunConfigurationUtil.npmConfiguration(project, "build")
        CreateRunConfigurationUtil.npmConfiguration(project, "start")
        CreateRunConfigurationUtil.npmConfiguration(project, "lint")
    }
}