package com.nekofar.milad.intellij.nextjs

import com.intellij.remoterobot.RemoteRobot
import com.intellij.remoterobot.search.locators.byXpath
import com.intellij.remoterobot.stepsProcessing.step
import com.intellij.remoterobot.utils.keyboard
import com.intellij.remoterobot.utils.waitFor
import com.nekofar.milad.intellij.nextjs.pages.dialog
import com.nekofar.milad.intellij.nextjs.pages.idea
import com.nekofar.milad.intellij.nextjs.pages.welcomeFrame
import com.nekofar.milad.intellij.nextjs.utils.RemoteRobotExtension
import com.nekofar.milad.intellij.nextjs.utils.StepsLogger
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.awt.event.KeyEvent.*
import java.time.Duration.ofMinutes

@ExtendWith(RemoteRobotExtension::class)
class NextUITest {
    init {
        StepsLogger.init()
    }

    @AfterEach
    fun closeProject(remoteRobot: RemoteRobot) = with(remoteRobot) {
        idea {
            if (remoteRobot.isMac()) {
                keyboard {
                    hotKey(VK_SHIFT, VK_META, VK_A)
                    enterText("Close Project")
                    enter()
                }
            } else {
                menuBar.select("File", "Close Project")
            }
        }
    }

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
        idea {
            waitFor(ofMinutes(5)) { isDumbMode().not() }
            step("Find config file") {
                with(projectViewTree) {
                    if (hasText("next.config.js").not()) {
                        findText(projectName).doubleClick()
                        waitFor { hasText("next.config.js") }
                    }
                    findText("next.config.js").click()
                }
            }
        }
    }
}