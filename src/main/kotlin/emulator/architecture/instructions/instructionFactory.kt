package emulator.architecture.instructions

import emulator.architecture.instructions.set.Add

class instructionFactory {
    private val instructions = arrayOf(
        ::Add,
        ::Sub,
        ::Read,
        ::Write,
        ::Jump,
        ::ReadKeyboard,
        ::SwitchMemory,
        ::SkipEqual,
        ::SkipNotEqual,
        ::SetA,
        ::SetT,
        ::ReadT,
        ::ConvertToBase10,
        ::ConvertByteToAscii,
        ::Draw

    )


    companion object {
        fun createInstruction(nibble0: Byte, nibble1: Byte, nibble2: Byte, nibble3: Byte, nibble31: Byte): Instruction {
            val instructionConstructor = instructions[nibble0.toInt()]

            val instruction = instructionConstructor(byteArrayOf(nibble1, nibble2, nibble3))
            return instructionConstructor(byteArrayOf(nibble1, nibble2, nibble3))
        }
    }
}