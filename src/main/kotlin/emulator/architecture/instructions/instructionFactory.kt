package emulator.architecture.instructions

class instructionFactory {
    private val instructions = arrayOf(
        ::Add,
    )


    companion object {
        fun createInstruction(nibble0: Byte, nibble1: Byte, nibble2: Byte, nibble3: Byte, nibble31: Byte): Instruction {
            val instructionConstructor = instructions[nibble0.toInt()]

            //val instruction = instructionConstructor(byteArrayOf(nibble1, nibble2, nibble3))
            return instructionConstructor(byteArrayOf(nibble1, nibble2, nibble3))
        }
    }
}