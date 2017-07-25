package com.easy.kotlin.fileio

/**
 * Created by jack on 2017/7/25.
 */

object KShellUtil {
    fun String.execute():Process{
        val runtime = Runtime.getRuntime()
        return runtime.exec(this)
    }



}


fun main(args: Array<String>) {
    var result = ShellUtil.execute("ls -al")
    println(result)
    val cmds = arrayOf("/bin/sh", "-c", "ls -al .. | grep .kt$")
    ShellUtil.execute(cmds)

    val cmd = "ls -al"

}
