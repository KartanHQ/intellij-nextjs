package com.nekofar.milad.intellij.nextjs.cli

import com.intellij.execution.filters.Filter
import com.intellij.lang.javascript.boilerplate.NpmPackageProjectGenerator
import com.intellij.lang.javascript.boilerplate.NpxPackageDescriptor
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ContentEntry
import com.intellij.openapi.vfs.VirtualFile
import com.nekofar.milad.intellij.nextjs.NextBundle
import com.nekofar.milad.intellij.nextjs.NextIcons

class NextCliProjectGenerator: NpmPackageProjectGenerator() {
    private val packageName = "next"
    private val npxCommand = "next"

    override fun getIcon() = NextIcons.ProjectGenerator
    override fun getName() = NextBundle.message("next.project.generator.name")
    override fun getDescription() = NextBundle.message("next.project.generator.description")
    override fun packageName() = packageName
    override fun presentablePackageName() = NextBundle.message("next.project.generator.presentable.package.name")
    override fun getNpxCommands() = listOf(NpxPackageDescriptor.NpxCommand(packageName, npxCommand))
    override fun generatorArgs(project: Project?, dir: VirtualFile?, settings: Settings?) = arrayOf("init")
    override fun filters(project: Project, baseDir: VirtualFile): Array<Filter> = emptyArray()
    override fun customizeModule(p0: VirtualFile, p1: ContentEntry?) { /* Do nothing */ }
}