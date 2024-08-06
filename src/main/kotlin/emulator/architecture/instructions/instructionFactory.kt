package emulator.architecture.instructions

import emulator.architecture.instructions.set.*

class InstructionFactory {
    @OptIn(ExperimentalUnsignedTypes::class)
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
    fun createInstruction(nibble0: UByte, nibble1: UByte, nibble2: UByte, nibble3: UByte): Instruction {
        val instructionConstructor = instructions[nibble0.toInt()]

        //val instruction = instructionConstructor(ubyteArrayOf(nibble1, nibble2, nibble3))
        //return instruction
        return instructionConstructor(ubyteArrayOf(nibble1, nibble2, nibble3))
    }
}