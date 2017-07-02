package processor;

import common.DataHelper;
import common.MemoryAddr;
import memory.Memory;
import memory.exceptions.MemoryException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import processor.registers.DWRegister;
import processor.registers.WRegister;

import static common.DataHelper.parseDWord;
import static common.DataHelper.parseMemoryAddr;
import static common.DataHelper.parseWord;
import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        IntegrationProcessorTest.RegisterTest.class,
        IntegrationProcessorTest.OperationTest.class
})
public class IntegrationProcessorTest {


    public static class RegisterTest {
        private Processor processor = Processor.getInstance();

        @Test
        public void init() {
            processor.A.setValue(DataHelper.parseWord(0x01));
            processor.B.setValue(DataHelper.parseWord(0x02));
            processor.C.setValue(DataHelper.parseWord(0x03));
            processor.D.setValue(DataHelper.parseWord(0x04));
            processor.E.setValue(DataHelper.parseWord(0x05));
            processor.F.setValue(DataHelper.parseWord(0x06));
            processor.H.setValue(DataHelper.parseWord(0x07));
            processor.L.setValue(DataHelper.parseWord(0x08));
            processor.SP.setValue(DataHelper.parseDWord(0x009));
            processor.PC.setValue(DataHelper.parseDWord(0x000A));

            assertEquals("A must be 0x01", DataHelper.parseWord(0x01).getValue(), processor.A.getValue());
            assertEquals("B must be 0x02", DataHelper.parseWord(0x02).getValue(), processor.B.getValue());
            assertEquals("C must be 0x03", DataHelper.parseWord(0x03).getValue(), processor.C.getValue());
            assertEquals("D must be 0x04", DataHelper.parseWord(0x04).getValue(), processor.D.getValue());
            assertEquals("E must be 0x05", DataHelper.parseWord(0x05).getValue(), processor.E.getValue());
            assertEquals("F must be 0x06", DataHelper.parseWord(0x06).getValue(), processor.F.getValue());
            assertEquals("H must be 0x07", DataHelper.parseWord(0x07).getValue(), processor.H.getValue());
            assertEquals("L must be 0x08", DataHelper.parseWord(0x08).getValue(), processor.L.getValue());
            assertEquals("AF must be 0x0106", DataHelper.parseDWord(0x0106), processor.AF);
            assertEquals("BC must be 0x0203", DataHelper.parseDWord(0x0203), processor.BC);
            assertEquals("DE must be 0x0405", DataHelper.parseDWord(0x0405), processor.DE);
            assertEquals("HL must be 0x0708", DataHelper.parseDWord(0x0708), processor.HL);
            assertEquals("SP must be 0x0009", DataHelper.parseDWord(0x0009), processor.SP);
            assertEquals("PC must be 0x000A", DataHelper.parseDWord(0x000A), processor.PC);
        }
    }

    public static class OperationTest {
        Processor processor = Processor.getInstance();
        private Memory memory = Memory.getInstance();

        @Before
        public void setUp() {
            initMocks(this);
            memory.clear();
            processor = new Processor(memory);
        }

        /*
		 * LD r8, r'8
		 */
        @Test
        public void LD_RR() {
            // arrange
            WRegister r1 = WRegister.newInstance();
            WRegister r2 = WRegister.newInstance();
            r2.setValue(parseWord(0xFF));

            // act
            processor.LD_RR(r1, r2);

            // assert
            assertEquals("r1 value must be 0xFF", (byte) 0xFF, r1.getValue());
        }

        /*
		 * LD r8, (r16)
		 */
        @Test
        public void LD_RM() throws MemoryException {
            // arrange
            WRegister r1 = WRegister.newInstance();
            DWRegister r2 = DWRegister.newInstance();

            MemoryAddr addr = parseMemoryAddr(0x1111);
            memory.loadWord(addr, parseWord(0xAA));
            r2.setValue(addr.getAddress());

            // act
            processor.LD_RM(r1, r2);

            // assert
            assertEquals("r1 value must be 0xAA", (byte) 0xAA, r1.getValue());
        }

        /*
		 * LD (r16), r'8
		 */
        @Test
        public void LD_MR() throws MemoryException {
            // arrange
            DWRegister r1 = DWRegister.newInstance();
            WRegister r2 = WRegister.newInstance();

            MemoryAddr addr = parseMemoryAddr(0x1111);
            r1.setValue(addr.getAddress());
            r2.setValue(parseWord(0xFF));

            // act
            processor.LD_MR(r1, r2);

            // assert
            assertEquals("Word in 0x1111 must be 0xFF", (byte) 0xFF, memory.retrieveWord(addr).getValue());
        }

        /*
		 * LD (r16), imm8
		 */
        @Test
        public void LD_MI() {
            // arrange
            processor.PC.setValue(parseDWord(0x0000));
            MemoryAddr immAddr = parseMemoryAddr(0x0001);
            memory.loadWord(immAddr, parseWord(10));

            DWRegister r1 = DWRegister.newInstance();
            MemoryAddr r1Addr = parseMemoryAddr(0x1111);
            r1.setValue(r1Addr.getAddress());

            // act
            processor.LD_MI(r1);

            // assert
            assertEquals("Immediate in 0x1111 must be 10", (byte) 10, memory.retrieveWord(r1Addr).getValue());
        }
    }







}
