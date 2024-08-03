package emulator.architecture

data class CPU(
    val registers: ByteArray = ByteArray(8),
    var programCounter: Int = 0,
    var time: Byte = 0,
    var address: Int = 0,
    var memoryFlag: Boolean = false
) {
}

/*
The computer executes instructions at 500hz (500 times per second)
The computer reads 2 bytes from ROM every cycle at P and P + 1 and gives them to the CPU to execute. These bytes form the instruction.
This means the P should always be an even number; operations that increment the program counter should increment it by 2
 */