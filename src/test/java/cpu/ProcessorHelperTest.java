package cpu;

import cpu.register.EightBitRegister;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProcessorHelperTest {

    @Before
    public void setUp() {
        Processor.A.setValue((byte)0x00);
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
        EightBitRegister eightBitRegister = EightBitRegister.newInstance();
        eightBitRegister.setValue((byte) 0x00);

        ProcessorHelper.increment(eightBitRegister); // act

        assertEquals((byte) 0x01, eightBitRegister.getValue());
        assertEquals("Flags have been changed", Processor.F.getValue(), (byte) 0x00);
    }

    @Test
    public void incrementEightBitRegister_0xFF_Returns0x00() {
        EightBitRegister eightBitRegister = EightBitRegister.newInstance(); // arrange
        eightBitRegister.setValue((byte) 0xFF);

        ProcessorHelper.increment(eightBitRegister); // act

        assertEquals((byte) 0x00, eightBitRegister.getValue());
        assertEquals("Expected: Z=1,N=0;H=1,C=1,0000", Processor.F.getValue(), (byte) 0xB0);
    }

    @Test
    public void decrementEightBitRegister_0x01_Returns0x00() {
        EightBitRegister eightBitRegister = EightBitRegister.newInstance();  // arrange
        eightBitRegister.setValue((byte) 0x01);

        ProcessorHelper.decrement(eightBitRegister); // act

        assertEquals((byte) 0x00, eightBitRegister.getValue());
    }

    @Test
    public void decrementEightBitRegister_0x00_Returns0xFF() {
        EightBitRegister eightBitRegister = EightBitRegister.newInstance(); // arrange
        eightBitRegister.setValue((byte) 0x00);

        ProcessorHelper.decrement(eightBitRegister); // act

        assertEquals((byte) 0xFF, eightBitRegister.getValue());
    }


}
