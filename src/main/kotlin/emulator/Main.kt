package org.example

import emulator.Emulator

fun main() {
    val computerEmulator = Emulator()
    if (args.isNotEmpty()) {
        computerEmulator.run(args[0])
    } else {
        computerEmulator.run()
    }
}