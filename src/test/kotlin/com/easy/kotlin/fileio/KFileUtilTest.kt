package com.easy.kotlin.fileio

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by jack on 2017/7/24.
 */
@RunWith(JUnit4::class)
class KFileUtilTest {
    val filename = "唐诗三百首.txt"

    @Test fun testGetFileContent() {
        val content = KFileUtil.getFileContent(filename)
        println(content)
    }

    @Test fun testGetFileLines() {
        val lines = KFileUtil.getFileLines(filename)

        lines.forEach {
            println(it)
        }

        println("文件行数：${lines.size}")
    }

    @Test fun testGetFileBytes() {
        val bytes = KFileUtil.getFileBytes(filename)
        bytes.forEach {
            print("${it} ")
        }
    }


    @Test fun testWriteFile() {
        val srcContent = KFileUtil.getFileContent(filename)
        KFileUtil.writeFile(srcContent, destFile = "唐诗.txt")
    }


    @Test fun testAppendFile() {
        val srcContent = KFileUtil.getFileContent(filename)
        KFileUtil.appendFile(srcContent, destFile = "唐诗.txt")
    }

    @Test fun testTraverseFileTree() {
        KFileUtil.traverseFileTree(".")
    }

    @Test fun testGetFileSequenceBy() {
        val fileSequence1 = KFileUtil.getFileSequenceBy(".", {
            it.isDirectory
        })
        fileSequence1.forEach { println("fileSequence1: ${it.absoluteFile} ") }

        val fileSequence2 = KFileUtil.getFileSequenceBy(".", {
            it.isFile
        })
        fileSequence2.forEach { println("fileSequence2: ${it.absoluteFile} ") }

        val fileSequence3 = KFileUtil.getFileSequenceBy(".", {
            it.extension == "kt"
        })
        fileSequence3.forEach { println("fileSequence3: ${it.absoluteFile} ") }
    }


    @Test fun testCountSummary() {
        println(KFileUtil.countSummary(filename))
    }

    @Test fun testCountChineseWordsFreq() {
        println(KFileUtil.countChineseWordFreq(filename, 1))
        println(KFileUtil.countChineseWordFreq(filename, 2))
        println(KFileUtil.countChineseWordFreq(filename, 3))
        println(KFileUtil.countChineseWordFreq(filename, 4))
        println(KFileUtil.countChineseWordFreq(filename, 5))
        println(KFileUtil.countChineseWordFreq(filename, 6))
        println(KFileUtil.countChineseWordFreq(filename, 7))
    }

    @Test fun testCountEnglishWordsFreq() {
        println(KFileUtil.countEnglishWordFreq(filename, 1))
        println(KFileUtil.countEnglishWordFreq(filename, 2))
        println(KFileUtil.countEnglishWordFreq(filename, 3))
        println(KFileUtil.countEnglishWordFreq(filename, 4))
        println(KFileUtil.countEnglishWordFreq(filename, 5))
        println(KFileUtil.countEnglishWordFreq(filename, 6))
        println(KFileUtil.countEnglishWordFreq(filename, 7))
    }
}
