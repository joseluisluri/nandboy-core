package cpu.opcode;

import cpu.Processor;
import cpu.register.DefaultEightBitRegister;
import cpu.register.EightBitRegister;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OpCodeHelperTest {

    @Before
    public void setUp() {
        Processor.AF.setValue((byte)0x0000);
        Processor.B.setValue((byte)0x00);
        Processor.C.setValue((byte)0x00);
        Processor.D.setValue((byte)0x00);
        Processor.E.setValue((byte)0x00);
        Processor.F.setValue((byte)0x00);
        Processor.H.setValue((byte)0x00);
        Processor.L.setValue((byte)0x00);
    }

    @Test
    public void incrementEightBitRegister_0x00_Returns0x01() {
        // arrange
        EightBitRegister eightBitRegister = new DefaultEightBitRegister();
        eightBitRegister.setValue((byte) 0x00);

        OpCodeHelper.increment(eightBitRegister); // act

        assertEquals((byte) 0x01, eightBitRegister.getValue());
        assertEquals("Flags have been changed", Processor.F.getValue(), (byte) 0x00);
    }

    @Test
    public void incrementEightBitRegister_0xFF_Returns0x00() {
        EightBitRegister eightBitRegister = new DefaultEightBitRegister(); // arrange
        eightBitRegister.setValue((byte) 0xFF);

        OpCodeHelper.increment(eightBitRegister); // act

        assertEquals((byte) 0x00, eightBitRegister.getValue());
        assertEquals("Expected: Z=1,N=0;H=1,C=1,0000", Processor.F.getValue(), (byte) 0xB0);
    }

    @Test
    public void decrementEightBitRegister_0x01_Returns0x00() {
        EightBitRegister eightBitRegister = new DefaultEightBitRegister();  // arrange
        eightBitRegister.setValue((byte) 0x01);

        OpCodeHelper.decrement(eightBitRegister); // act

        assertEquals((byte) 0x00, eightBitRegister.getValue());
    }

    @Test
    public void decrementEightBitRegister_0x00_Returns0xFF() {
        EightBitRegister eightBitRegister = new DefaultEightBitRegister(); // arrange
        eightBitRegister.setValue((byte) 0x00);

        OpCodeHelper.decrement(eightBitRegister); // act

        assertEquals((byte) 0xFF, eightBitRegister.getValue());
    }


}
