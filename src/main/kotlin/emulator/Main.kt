package org.example

import emulator.architecture.Emulator

fun main() {
    val computer = Emulator()
    if (args.isNotEmpty()) {
        computer.run(args[0])
    } else {
        computer.run()
    }
}