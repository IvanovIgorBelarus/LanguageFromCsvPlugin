package com.igevmi.languagefromcsv

import java.io.File

fun parce(basePath: String) {
    val csvFile = File(basePath+CSV_FILE_PATH)

    csvFile.bufferedReader().use { reader ->
        val headerRow = reader.readLine().split("^")
        val languageCodes = headerRow.subList(1, headerRow.size)
//        reader.readLine()
        val line = reader.readLines()
        languageCodes.forEachIndexed { indexCode, s ->
            val languageSuffix = s
                .replace("pt-BR", "pt-rBR")
                .replace("zh-Hans", "zh-rCN")
                .replace("zh-Hant", "zh-rHK")
                .replace("*", "")
            val file = File(basePath + PATH_SAVE + languageSuffix)
            if (!file.exists()) {
                file.mkdirs()
            }

            File(file, fileName).bufferedWriter().use { writer ->
                writer.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n")
                writer.write("<resources>\n")
                line.forEachIndexed { index, s ->
                    val row = s.split("^")
                    val stringName = row[0]
                        .replace(".", "_")
                        .replace("-", "_")
                    val stringValue = row[indexCode + 1].trim()
                        .replace("&", "&amp;")
                        .replace("'", "\\'")
                    if (stringValue.isNotEmpty()) {
                        writer.write("<string name=\"$stringName\">$stringValue</string>\n")
                    }
                }
                writer.write("</resources>\n")
            }
        }
    }
}

private const val CSV_FILE_PATH = "\\app\\src\\main\\res\\raw\\reposter_localization.csv"
private const val PATH_SAVE = "\\app\\src\\main\\res\\values-"
private const val fileName = "strings.xml"
