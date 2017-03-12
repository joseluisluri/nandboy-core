package cpu;

import cpu.exceptions.NoSuchOpcodeException;
import cpu.register.EightBitRegister;
import lombok.NonNull;

import java.lang.reflect.Method;

class ProcessorHelper {

    static void increment(@NonNull EightBitRegister register) {
        byte newValue = (byte) (register.getValue() + 1);
        register.setValue(newValue);

        if (newValue == 0x00) {
            Processor.F.setZeroFlag(true); // is set when the result of the operation is zero
            Processor.F.setCarryFlag(true); // is set if a carry ocurred last math operation (old value 0xFF)
        }

        if ((newValue & 0x0F) == 0x00) {
            Processor.F.setHalfCarryFlag(true); // is set if a carry ocurred from the lower nibble
        }
    }

    static void decrement(@NonNull EightBitRegister register) {
        byte newValue = (byte) (register.getValue() - 1);
        register.setValue(newValue);
    }

    static short getCyclesFromOpcode(byte opcode) throws NoSuchOpcodeException {
        String opcodeHex = String.format("0x%02d", (short) opcode);
        try {
            Method method = Processor.class.getDeclaredMethod("opcode" + opcodeHex);
            Timing annotation = method.getAnnotation(Timing.class);
            return annotation.cycles();
        } catch (NoSuchMethodException e) {
            throw new NoSuchOpcodeException("Could not retrieve cycles from opcode:" + opcodeHex, e);
        }
    }

    static void load(@NonNull EightBitRegister register, @NonNull byte value) {
        register.setValue(value);
    }
}