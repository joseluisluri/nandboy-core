package processor;

import common.DataHelper;
import processor.registers.WRegister;
import common.DWord;
import common.Word;
import org.junit.Before;
import org.junit.Test;

import static common.DataHelper.parseDWord;
import static common.DataHelper.parseWord;
import static org.junit.Assert.assertEquals;

public class ProcessorHelperTest {
    private static byte ALL_FLAGS_SET = (byte) 0xF0;

    private Processor processor = Processor.getInstance();

    @Before
    public void setUp() {
        processor.reset();
    }

    @Test
    public void setZeroFlagTrueSetsZeroFlag() {
        ProcessorHelper.setZeroFlag(true); // act
        assertEquals("ZeroFlag must be set in F", ProcessorHelper.ZERO_FLAG_BITMASK, processor.F.getValue());
        assertEquals("ZeroFlag must be true", true, ProcessorHelper.getZeroFlag());
    }

    @Test
    public void setZeroFlagFalseResetsZeroFlag() {
        // arrange
        processor.F.setValue(parseWord(ALL_FLAGS_SET));
        byte expectedResult = (byte) 0x70;
        // act
        ProcessorHelper.setZeroFlag(false);
        assertEquals("ZeroFlag must be reset in F", expectedResult, processor.F.getValue());
        assertEquals("ZeroFlag must be false", false, ProcessorHelper.getZeroFlag());
    }

    @Test
    public void setSubtractFlagTrueSetsSubtractFlag() {
        ProcessorHelper.setSubtractFlag(true); // act
        assertEquals("SubtractFlag must be set in F", ProcessorHelper.SUBSTRACT_FLAG_BITMASK, processor.F.getValue());
        assertEquals("SubtractFlag must be true", true, ProcessorHelper.getSubtractFlag());
    }

    @Test
    public void setSubtractFlagFlaseResetsSubtractFlag() {
        // arrange
        processor.F.setValue(parseWord(ALL_FLAGS_SET));
        byte expectedResult = (byte) 0xB0;
        // act
        ProcessorHelper.setSubtractFlag(false);
        assertEquals("SubtractFlag must be reset in F", expectedResult, processor.F.getValue());
        assertEquals("SubtractFlag must be false", false, ProcessorHelper.getSubtractFlag());
    }

    @Test
    public void setHalfCarryFlagTrueSetsHalfCarryFlag() {
        ProcessorHelper.setHalfCarryFlag(true); // act
        assertEquals("HalfCarryFlag must be set in F", ProcessorHelper.HALF_CARRY_FLAG_BITMASK, processor.F.getValue());
        assertEquals("HalfCarryFlag must be true", true, ProcessorHelper.getHalfCarryFlag());
    }

    @Test
    public void setHalfCarryFlagFalseResetsHalfCarryFlag() {
        // arrange
        processor.F.setValue(parseWord(ALL_FLAGS_SET));
        byte expectedResult = (byte) 0xD0;
        // act
        ProcessorHelper.setHalfCarryFlag(false);
        assertEquals("HalfCarryFlag must be reset in F", expectedResult, processor.F.getValue());
        assertEquals("HalfCarryFLag must be false", false, ProcessorHelper.getHalfCarryFlag());
    }

    @Test
    public void setCarryFlagTrueSetsCarryFlag() {
        ProcessorHelper.setCarryFlag(true); // act
        assertEquals("CarryFlag must be set in F", ProcessorHelper.CARRY_FLAG_BITMASK, processor.F.getValue());
        assertEquals("CarryFlag must be true", true, ProcessorHelper.getCarryFlag());
    }

    @Test
    public void setCarryFlagFalseResetsCarryFlag() {
        // arrange
        processor.F.setValue(parseWord(ALL_FLAGS_SET));
        byte expectedResult = (byte) 0xE0;
        // act
        ProcessorHelper.setCarryFlag(false);
        assertEquals("CarryFlag must be reset in F", expectedResult, processor.F.getValue());
        assertEquals("CarryFLag must be false", false, ProcessorHelper.getCarryFlag());
    }

    /**
     * Case 1: 0x01 + 0x01 = 0x02 -> Z=0 N=0 H=0 C=0
     * Case 2: 0x00 + 0x00 = 0x00 -> Z=1 N=0 H=0 C=0
     * Case 3: 0x0D + 0x03 = 0x10 -> Z=0 N=0 H=1 C=0
     * Case 4: 0xC0 + 0x50 = 0x10 -> Z=0 N=0 H=0 C=1
     * Case 5: 0xFF + 0x02 = 0x01 -> Z=0 N=0 H=1 C=1
     * Case 4: 0xFE + 0x02 = 0x00 -> Z=1 N=0 H=1 C=1
     */
    @Test
    public void addTestCase1() {
        // arrange
        Word op1 = parseWord(0x01);
        Word op2 = parseWord(0x01);
        WRegister register = WRegister.newInstance();
        register.setValue(op1);
        // act
        ProcessorHelper.add(register, op2);
        // assert
        assertEquals("Result must be 0x02", parseWord(0x02).getValue(), register.getValue());
        assertEquals("ZeroFlag must be false", false, ProcessorHelper.getZeroFlag());
        assertEquals("SubtractFlag must be false", false, ProcessorHelper.getSubtractFlag());
        assertEquals("HalfCarryFlag must be false", false, ProcessorHelper.getHalfCarryFlag());
        assertEquals("CarryFlag must be false", false, ProcessorHelper.getCarryFlag());
    }

    @Test
    public void addTestCase2() {
        // arrange
        Word op1 = parseWord(0x00);
        Word op2 = parseWord(0x00);
        WRegister register = WRegister.newInstance();
        register.setValue(op1);
        // act
        ProcessorHelper.add(register, op2);
        // assert
        assertEquals("Result must be 0x00", parseWord(0x00).getValue(), register.getValue());
        assertEquals("ZeroFlag must be true", true, ProcessorHelper.getZeroFlag());
        assertEquals("SubtractFlag must be false", false, ProcessorHelper.getSubtractFlag());
        assertEquals("HalfCarryFlag must be false", false, ProcessorHelper.getHalfCarryFlag());
        assertEquals("CarryFlag must be false", false, ProcessorHelper.getCarryFlag());
    }

    @Test
    public void addTestCase3() {
        // arrange
        Word op1 = parseWord(0x0D);
        Word op2 = parseWord(0x03);
        WRegister register = WRegister.newInstance();
        register.setValue(op1);
        // act
        ProcessorHelper.add(register, op2);
        // assert
        assertEquals("Result must be 0x10", parseWord(0x10).getValue(), register.getValue());
        assertEquals("ZeroFlag must be false", false, ProcessorHelper.getZeroFlag());
        assertEquals("SubtractFlag must be false", false, ProcessorHelper.getSubtractFlag());
        assertEquals("HalfCarryFlag must be true", true, ProcessorHelper.getHalfCarryFlag());
        assertEquals("CarryFlag must be false", false, ProcessorHelper.getCarryFlag());
    }

    @Test
    public void addTestCase4() {
        // arrange
        Word op1 = parseWord(0xC0);
        Word op2 = parseWord(0x50);
        WRegister register = WRegister.newInstance();
        register.setValue(op1);
        // act
        ProcessorHelper.add(register, op2);
        // assert
        assertEquals("Result must be 0x10", parseWord(0x10).getValue(), register.getValue());
        assertEquals("ZeroFlag must be false", false, ProcessorHelper.getZeroFlag());
        assertEquals("SubtractFlag must be false", false, ProcessorHelper.getSubtractFlag());
        assertEquals("HalfCarryFlag must be false", false, ProcessorHelper.getHalfCarryFlag());
        assertEquals("CarryFlag must be true", true, ProcessorHelper.getCarryFlag());
    }

    @Test
    public void addTestCase5() {
        // arrange
        Word op1 = parseWord(0xFF);
        Word op2 = parseWord(0x02);
        WRegister register = WRegister.newInstance();
        register.setValue(op1);
        // act
        ProcessorHelper.add(register, op2);
        // assert
        assertEquals("Result must be 0x01", parseWord(0x01).getValue(), register.getValue());
        assertEquals("ZeroFlag must be false", false, ProcessorHelper.getZeroFlag());
        assertEquals("SubtractFlag must be false", false, ProcessorHelper.getSubtractFlag());
        assertEquals("HalfCarryFlag must be true", true, ProcessorHelper.getHalfCarryFlag());
        assertEquals("CarryFlag must be true", true, ProcessorHelper.getCarryFlag());
    }

    @Test
    public void addTestCase6() {
        // arrange
        Word op1 = parseWord(0xFE);
        Word op2 = parseWord(0x02);
        WRegister register = WRegister.newInstance();
        register.setValue(op1);
        // act
        ProcessorHelper.add(register, op2);
        // assert
        assertEquals("Result must be 0x00", parseWord(0x00).getValue(), register.getValue());
        assertEquals("ZeroFlag must be true", true, ProcessorHelper.getZeroFlag());
        assertEquals("SubtractFlag must be false", false, ProcessorHelper.getSubtractFlag());
        assertEquals("HalfCarryFlag must be true", true, ProcessorHelper.getHalfCarryFlag());
        assertEquals("CarryFlag must be true", true, ProcessorHelper.getCarryFlag());
    }

    /**
     * inc Word
     */
    @Test
    public void incWordLowNibble() {
        Word word = parseWord(0x03);
        DataHelper.inc(word, parseWord(0x2));
        assertEquals("Value must be 0x05", parseWord(0x05), word);
    }

    @Test
    public void incWordHighNibble() {
        Word word = parseWord(0x0D);
        DataHelper.inc(word, parseWord(0x05));
        assertEquals("Value must be 0x12", parseWord(0x12), word);
    }

    @Test
    public void incWordOverflow() {
        Word word = parseWord(0xFF);
        DataHelper.inc(word, parseWord(0x2));
        assertEquals("Value must be 0x01", parseWord(0x01), word);
    }

    /**
     * dec Word
     */
    @Test
    public void decWordLowNibble() {
        Word word = parseWord(0x0C);
        DataHelper.dec(word, parseWord(0x2));
        assertEquals("Value must be 0x0A", parseWord(0x0A), word);
    }

    @Test
    public void decWordHighNibble() {
        Word word = parseWord(0x1B);
        DataHelper.dec(word, parseWord(0x0F));
        assertEquals("Value must be 0x0C", parseWord(0x0C), word);
    }

    @Test
    public void decWordUnderflow() {
        Word word = parseWord(0x01);
        DataHelper.dec(word, parseWord(0x2));
        assertEquals("Value must be 0xFF", parseWord(0xFF), word);
    }

    /**
     * inc DWord
     */
    @Test
    public void incDwordLowWord() {
        DWord dword = parseDWord(0x0001);
        DataHelper.inc(dword, parseWord(0x01));
        assertEquals("Value must be 0x0002", parseDWord(0x0002), dword);
    }

    @Test
    public void incDwordHighWord() {
        DWord dword = parseDWord(0x00FF);
        DataHelper.inc(dword, parseWord(0xFF));
        assertEquals("Value must be 0x01FE", parseDWord(0x01FE), dword);
    }

    @Test
    public void incDwordOverFlow() {
        DWord dword = parseDWord(0xFFFF);
        DataHelper.inc(dword, parseWord(0x02));
        assertEquals("Value must be 0x0001", parseDWord(0x0001), dword);
    }

    /**
     * dec DWord
     */
    @Test
    public void decDwordLowBytePart() {
        DWord dword = parseDWord(0x0005);
        DataHelper.dec(dword, parseWord(0x03));
        assertEquals("Value must be 0x0002", parseDWord(0x0002), dword);
    }

    @Test
    public void decDwordHighBytePart() {
        DWord dword = parseDWord(0x100);
        DataHelper.dec(dword, parseWord(0xFF));
        assertEquals("Value must be 0x0001", parseDWord(0x0001), dword);
    }

    @Test
    public void decDwordUnderFlow() {
        DWord dword = parseDWord(0xAAAA);
        DataHelper.dec(dword, parseWord(0xFF));
        assertEquals("Value must be 0xA9AB", parseDWord(0xA9AB), dword);
    }
}