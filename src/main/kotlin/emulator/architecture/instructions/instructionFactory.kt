package emulator.architecture.instructions

class instructionFactory {
    private val instructions = arrayOf(
        ::Add,
    )

    fun createInstruction(nibble0: Byte, nibble1: Byte, nibble2: Byte, nibble3: Byte): Instruction {
        val instructionConstructor = instructions[nibble0.toInt()]

        val instruction = instructionConstructor(byteArrayOf(nibble1, nibble2, nibble3))
        return instruction
    }
}