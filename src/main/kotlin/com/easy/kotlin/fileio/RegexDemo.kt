package com.easy.kotlin.fileio

/**
 * Created by jack on 2017/7/26.
 */

fun main(args: Array<String>) {
    val regex1 = "\\{\"ObjURL\":(.+),\"FromURL\"".toRegex()
    val content = KFileUtil.getFileContent("imgs.json")
    val lines = KFileUtil.getFileLines("imgs.json")
    println(regex1.toPattern())

    lines.forEach {
        println(regex1.containsMatchIn(it))
        regex1.findAll(it).forEach {
            println(it.value)
        }
    }


    val r1 = Regex("[a-z]+")
    val r2 = Regex("[a-z]+", RegexOption.IGNORE_CASE)



    val re = Regex("[0-9]+")
    re.matchEntire("456")
    re.matchEntire("456")?.value
    re.matchEntire("456")?.groupValues
    re.containsMatchIn("456-ABC")
    re.matches("456")

    re.find("9876-ABCD-1234")?.value
    re.findAll("9876-ABCD-1234").forEach {
        println(it.value)
    }

    re.replace("99999ABCD", "0")
    re.replace("9ABCD8", {
        println(it.value)
        (it.value.toInt() * it.value.toInt()).toString()
    })

    val p = re.toPattern()
    val m = p.matcher("888ABC999")
    while (m.find()) {
        val d = m.group()
        println(d)
    }


}
