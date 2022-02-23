package com.nekofar.milad.intellij.nextjs

import com.intellij.remoterobot.RemoteRobot
import com.intellij.remoterobot.search.locators.byXpath
import com.nekofar.milad.intellij.nextjs.pages.dialog
import com.nekofar.milad.intellij.nextjs.pages.welcomeFrame
import com.nekofar.milad.intellij.nextjs.utils.RemoteRobotExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(RemoteRobotExtension::class)
class NextUITest {

    @Test
    fun createNewProject(remoteRobot: RemoteRobot) = with(remoteRobot) {
        welcomeFrame {
            createNewProjectLink.click()
            dialog("New Project") {
                findText("JavaScript").click()
                jList(
                    byXpath(
                        "//div[contains(@visible_text_keys, 'create.react.app.name')]"
                    )
                ).clickItem("Next.js")
                button("Next").click()
                button("Finish").click()
            }
        }
    }
}