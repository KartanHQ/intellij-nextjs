package com.nekofar.milad.intellij.nextjs

import com.automation.remarks.junit5.Video
import com.intellij.remoterobot.RemoteRobot
import com.intellij.remoterobot.search.locators.byXpath
import com.intellij.remoterobot.stepsProcessing.step
import com.intellij.remoterobot.utils.waitFor
import com.nekofar.milad.intellij.nextjs.fixtures.terminal
import com.nekofar.milad.intellij.nextjs.pages.dialog
import com.nekofar.milad.intellij.nextjs.pages.idea
import com.nekofar.milad.intellij.nextjs.pages.welcomeFrame
import com.nekofar.milad.intellij.nextjs.utils.RemoteRobotExtension
import com.nekofar.milad.intellij.nextjs.utils.StepsLogger
import org.junit.jupiter.api.*
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.extension.ExtendWith
import java.time.Duration.ofSeconds

@TestMethodOrder(OrderAnnotation::class)
@ExtendWith(RemoteRobotExtension::class)
class NextUITest {
    init {
        StepsLogger.init()
    }

    @AfterEach
    fun closeProject(remoteRobot: RemoteRobot) = with(remoteRobot) {
        idea {
            menuBar.select("File", "Close Project")
        }
    }

    @Test
    @Video
    @Order(1)
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
            waitForFinishBackgroundTasks()
            step("Find terminal") {
                terminal().click()
            }
            step("Find config file") {
                with(projectViewTree) {
                    if (hasText("next.config.js").not()) {
                        findText(projectName).doubleClick()
                        waitFor(ofSeconds(10)) { hasText("next.config.js") }
                    }
                    findText("next.config.js").click()
                }
            }
        }
    }

    @Test
    @Video
    @Order(2)
    fun createNewTypeScriptProject(remoteRobot: RemoteRobot) = with(remoteRobot) {
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
                checkBox("Create TypeScript project").select()
                button("Finish").click()
            }
        }
        idea {
            waitForFinishBackgroundTasks()
            step("Find config file") {
                with(projectViewTree) {
                    if (hasText("next.config.js").not()) {
                        findText(projectName).doubleClick()
                        waitFor(ofSeconds(10)) { hasText("next.config.js") }
                    }
                    findText("next.config.js").click()
                    findText("next-env.d.ts").click()
                }
            }
        }
    }
}