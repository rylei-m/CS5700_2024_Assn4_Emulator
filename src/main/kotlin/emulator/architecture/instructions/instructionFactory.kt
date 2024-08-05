package emulator.architecture.instructions

import emulator.architecture.instructions.set.*

class InstructionFactory {
    private val instructions = arrayOf(
        ::Store,
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
    fun createInstruction(nibble0: Byte, nibble1: Byte, nibble2: Byte, nibble3: Byte): Instruction {
        val instructionConstructor = instructions[nibble0.toInt()]

        val instruction = instructionConstructor(byteArrayOf(nibble1, nibble2, nibble3))
        return instruction
    }
}