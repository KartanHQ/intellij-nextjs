package com.nekofar.milad.intellij.nextjs.execution

import com.intellij.ide.actions.runAnything.activity.RunAnythingCommandLineProvider
import com.intellij.openapi.actionSystem.DataContext
import com.nekofar.milad.intellij.nextjs.NextBundle
import com.nekofar.milad.intellij.nextjs.NextIcons

@Suppress("UnstableApiUsage")
class NextRunAnythingProvider : RunAnythingCommandLineProvider() {
    override fun getIcon(value: String) = NextIcons.RunAnything

    override fun getHelpGroupTitle() = "Next"

    override fun getCompletionGroupTitle(): String = NextBundle.message("next.run.anything.completion.group")

    override fun getHelpCommandPlaceholder(): String = "next <command> <--option-name...>"

    override fun getHelpCommand() = "next --help"

    override fun getHelpIcon() = NextIcons.RunAnything

    override fun getCommand(value: String) = value

    override fun getMainListItem(dataContext: DataContext, value: String) =
        RunAnythingNextItem(getCommand(value), getIcon(value))

    override fun run(dataContext: DataContext, commandLine: CommandLine): Boolean {
        TODO("Not yet implemented")
    }

    override fun suggestCompletionVariants(dataContext: DataContext, commandLine: CommandLine): Sequence<String> {
        TODO("Not yet implemented")
    }
}