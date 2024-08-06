package org.example

import emulator.Emulator

fun main(args: Array<String>) {
    val computer = Emulator()
    if (args.isNotEmpty()) {
        computer.run(args[0])
    } else {
        computer.run()
    }
}