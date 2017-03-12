package cpu.opcode;

import cpu.Processor;
import cpu.register.EightBitRegister;
import cpu.register.FlagEightBitRegister;

public class OpCodeHelper {

    public static void increment(EightBitRegister register) {
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

    public static void decrement(EightBitRegister register) {
        byte newValue = (byte) (register.getValue() - 1);
        register.setValue(newValue);
    }
}