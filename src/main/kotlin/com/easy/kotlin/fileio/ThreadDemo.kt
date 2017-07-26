package com.easy.kotlin.fileio

import kotlin.concurrent.thread

/**
 * Created by jack on 2017/7/26.
 */


fun main(args: Array<String>) {

    object : Thread() {
        override fun run() {
            Thread.sleep(3000)
            println("A 使用 Thread 对象表达式: ${Thread.currentThread()}")
        }
    }.start()


    Thread({
        Thread.sleep(2000)
        println("B 使用 Lambda 表达式: ${Thread.currentThread()}")
    }).start()

    val t = Thread({
        Thread.sleep(2000)
        println("C 使用 Lambda 表达式:${Thread.currentThread()}")
    })
    t.isDaemon = false
    t.name = "CThread"
    t.priority = 3
    t.start()

    thread(start = true, isDaemon = false, name = "DThread", priority = 3) {
        Thread.sleep(1000)
        println("D 使用 Kotlin 封装的函数 thread(): ${Thread.currentThread()}")
    }


}


@Volatile private var running = false
fun start() {
    running = true
    thread(start = true) {
        while (running) {
            println("Still running: ${Thread.currentThread()}")
        }
    }
}

fun stop() {
    running = false
    println("Stopped: ${Thread.currentThread()}")
}

