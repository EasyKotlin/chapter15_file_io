package com.easy.kotlin.fileio

import java.io.File
import java.net.URL
import java.nio.charset.Charset
import java.util.regex.Pattern

/**
 * Created by jack on 2017/7/24.
 */

fun getUrlContent(url: String): String {
    return URL(url).readText(Charset.defaultCharset())
}

fun getUrlBytes(url: String): ByteArray {
    return URL(url).readBytes()
}

fun writeUrlBytesTo(filename: String, url: String) {
    val bytes = URL(url).readBytes()
    File(filename).writeBytes(bytes)
}


fun getImgUrls(word: String) {

    var pn = 30
    for (i in 1..100) {
        //val imgUrlQuery = "http://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&ct=201326592&is=&fp=result&queryWord=%E7%BE%8E%E5%A5%B3&cl=&lm=&ie=utf-8&oe=utf-8&adpicid=&st=&z=&ic=&word=%E7%BE%8E%E5%A5%B3&s=&se=&tab=&width=&height=&face=&istype=&qc=&nc=&fr=&cg=girl&pn=${pn}&rn=30&gsm=1e&1500916631851="
        val imgUrlQuery = "http://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&ct=201326592&is=&fp=result&queryWord=${word}C&cl=2&lm=-1&ie=utf-8&oe=utf-8&adpicid=&st=&z=&ic=&word=${word}&s=&se=&tab=&width=&height=&face=&istype=&qc=&nc=1&fr=&pn=${pn}&rn=30&gsm=1e&1500950950035="
        println("imgUrlQuery=${imgUrlQuery}")
        pn += 30
        val imgUrlJson = File("imgs.json")
        imgUrlJson.writeBytes(getUrlBytes(imgUrlQuery))

        // \{"objURL":(.+),"FromURL":
        val objImgUrlRegex = "\\{\"ObjURL\":(.+),\"FromURL\""
        val imgUrlJsonString = getUrlContent(imgUrlQuery)
        println(imgUrlJsonString)

        val p = Pattern.compile(objImgUrlRegex)
        val lines = KFileUtil.getFileLines("imgs.json")

        val 美女文件名 = "美女.html"
        val 美女文件所有行 = KFileUtil.getFileLines(美女文件名)
        美女文件所有行.forEach({
            println(it)
        })

        lines.forEach {
            val m = p.matcher(it)
            while (m.find()) {
                try {
                    val result = m.group()
                    val startIndex = result.indexOf("{\"ObjURL\":\"") + "{\"ObjURL\":\"".length
                    val endIndex = result.indexOf("\",\"FromURL\"")
                    var imgUrl = result.substring(startIndex, endIndex)
                    imgUrl = imgUrl.replace("\\", "")
                    println(imgUrl)
                    val urlConnection = URL(imgUrl).openConnection()
                    urlConnection.connectTimeout = 1000
                    val size = urlConnection.contentLength
                    println(size)
                    val line = "<img sloth-img=\"${imgUrl}\" width=\"100%\">"
                    if (!美女文件所有行.contains(line) && size > 100 && imgUrl.endsWith(".jpg")) { // 重复的 url 不写
                        KFileUtil.首行插入写文件(line, 美女文件名)
                    }
                } catch (ex: Exception) {

                }
            }
        }

    }


}

fun main(args: Array<String>) {
    println(getUrlContent("https://www.baidu.com"))
    val urlBytes = getUrlBytes("https://www.baidu.com")
    val byteStr = urlBytes.joinToString(separator = " ")
    print(byteStr)
    val f = File("百度一下.html")
    f.writeBytes(urlBytes)
    writeUrlBytesTo("图片.jpg", "http://n.sinaimg.cn/default/4_img/uplaod/3933d981/20170622/2fIE-fyhfxph6601959.jpg")

    val words = arrayOf("90后美女", "00后小美女", "嫩模", "性感", "诱惑", "清纯", "校花", "林允儿", "欧阳娜娜", "林允", "美女", "尤物")

    getImgUrls("欧阳娜娜")

}
