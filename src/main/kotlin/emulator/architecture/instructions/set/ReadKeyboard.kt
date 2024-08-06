package emulator.architecture.instructions.set

import emulator.architecture.fundamentals.PauseTimer
import emulator.architecture.instructions.Instruction
import emulator.architecture.registers.RX
import emulator.architecture.registers.RXManager.r

class ReadKeyboard(
    nibbles: UByteArray
) : Instruction(nibbles) {
    lateinit var x: RX

    override fun processNibbles() {
        val xValue = nibbles[0].toUByte().toInt()
        x = r[xValue]
    }

    override fun performOperation() {
        PauseTimer.pauseTimer.set(true)

        println("Enter up to 2 hexadecimal digits (0-F): ")
        val input = readln().trim().uppercase()

        val byte = parseHexInput(input)

        x.write(byteArrayOf(byte))

        PauseTimer.pauseTimer.set(false)
    }
    private fun parseHexInput(input: String): Byte {
        if (input.isEmpty() || !input.matches(Regex("^[0-9A-F]*$"))) {
            return 0
        }

        return try {
            val hexString = input.take(2)
            hexString.toInt(16).toByte()
        } catch (e: NumberFormatException) { 0 }
    }
}