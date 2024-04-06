package com.igevmi.languagefromcsv

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

class ConvertAction : AnAction() {

    override fun actionPerformed(actionEvent: AnActionEvent) {
        val project = actionEvent.project

        val message = "name = ${project?.name}\n" +
                "basePath = ${project?.basePath}\n" +
                "projectFilePath = ${project?.projectFilePath}\n" +
                "projectFileName = ${project?.projectFile?.name}\n" +
                "place = ${actionEvent.place}"
        project?.basePath?.let { parce(it) }
        Messages.showMessageDialog(
            project,
            message,
            "Greeting",
            Messages.getInformationIcon()
        )
    }
}