package cpu;

import core.DWord;
import core.Datatype;
import core.Word;
import cpu.exceptions.NoSuchOpcodeException;
import lombok.NonNull;
import lombok.Setter;

import javax.xml.crypto.Data;

import static core.Datatype.parseDWord;
import static core.Datatype.parseInt;
import static core.Datatype.parseWord;

class ProcessorHelper {
    public static final byte ZERO_FLAG_BITMASK = (byte) 0x80; // 7th bit
    public static final byte SUBSTRACT_FLAG_BITMASK = (byte) 0x40; // 6th bit
    public static final byte HALF_CARRY_FLAG_BITMASK = (byte) 0x20; // 5th bit
    public static final byte CARRY_FLAG_BITMASK = (byte) 0x10; // 4th bit and 0-3 always zero

    static WRegister F = Processor.getInstance().F;
    static DWRegister PC = Processor.getInstance().PC;

    static void inc(@NonNull Word word, @NonNull Word i) {
        word.copy(parseWord(word.getValue() + i.getValue()));
    }

    static void dec(@NonNull Word word, @NonNull Word i) {
        word.copy(parseWord(word.getValue() - i.getValue()));
    }

    // Add i into dWord and returns the carry.
    static void inc(@NonNull DWord dWord, @NonNull Word i) {
        int result = parseInt(dWord) + (0xFF & i.getValue());
        dWord.setValue(parseDWord(result));
    }

    // Add i into dWord and returns the carry.
    static void dec(@NonNull DWord dWord, @NonNull Word i) {
        int result = parseInt(dWord) - (0xFF & i.getValue());
        dWord.setValue(parseDWord(result));
    }

    static void add(@NonNull WRegister register, @NonNull Word i) {
        int result = parseInt(register) + parseInt(i);
        setZeroFlag((0xFF & result) == 0);
        setHalfCarryFlag(0 != (0x10 & (0x0F & register.getValue()) + (0x0F & i.getValue())));
        setCarryFlag((result >> 8) == 1);
        register.setValue(parseWord(result));
    }

    static void add(@NonNull DWRegister r1, @NonNull DWRegister r2) {
        int result = parseInt(r1) + parseInt(r2);
        setZeroFlag((0xFFFF & result) == 0);
        setHalfCarryFlag(0 != (0x1000 & (0x00FF & parseInt(r1) + (0x00FF & parseInt(r2)))));
        setCarryFlag((result >> 16) == 1);
    }

    static Instruction getInstructionFromOpcode(byte opcode) throws NoSuchOpcodeException {
        if (Processor.isa.containsKey(opcode)) {
            return Processor.isa.get(opcode);
        } else {
            throw new NoSuchOpcodeException("Opcode does not exist");
        }
    }

    /*
     * Set the status of a flags
     */
    private static void setFlags(boolean status, byte bitmask) {
        F.setValue(parseWord((status ? bitmask | F.getValue() : (0xFF ^ bitmask) & F.getValue())));
    }

    static void setZeroFlag(boolean b) {
        setFlags(b, ZERO_FLAG_BITMASK);
    }

    static void setSubtractFlag(boolean b) {
        setFlags(b, SUBSTRACT_FLAG_BITMASK);
    }

    static void setHalfCarryFlag(boolean b) {
        setFlags(b, HALF_CARRY_FLAG_BITMASK);
    }

    static void setCarryFlag(boolean b) {
        setFlags(b, CARRY_FLAG_BITMASK);
    }

    /*
    * Query the status of a flags
    */
    private static boolean queryFlags(byte bitmask) {
        return (bitmask & F.getValue()) == bitmask;
    }

    static boolean getZeroFlag() {
        return queryFlags(ZERO_FLAG_BITMASK);
    }

    static boolean getCarryFlag() {
        return queryFlags(CARRY_FLAG_BITMASK);
    }

    static boolean getHalfCarryFlag() {
        return queryFlags(HALF_CARRY_FLAG_BITMASK);
    }

    static boolean getSubtractFlag() {
        return queryFlags(SUBSTRACT_FLAG_BITMASK);
    }
}