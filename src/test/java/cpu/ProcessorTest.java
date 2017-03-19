package cpu;

import org.junit.Before;
import org.junit.Test;

import static core.Datatype.parseDWord;
import static core.Datatype.parseWord;
import static org.junit.Assert.assertEquals;

public class ProcessorTest {
    Processor processor = Processor.getInstance();

    @Before
    public void setUp() {
        processor.reset();
    }

    @Test
    public void _1() {
        processor.A.setValue(parseWord(0x01));
        processor.B.setValue(parseWord(0x02));
        processor.C.setValue(parseWord(0x03));
        processor.D.setValue(parseWord(0x04));
        processor.E.setValue(parseWord(0x05));
        processor.F.setValue(parseWord(0x06));
        processor.H.setValue(parseWord(0x07));
        processor.L.setValue(parseWord(0x08));
        processor.SP.setValue(parseDWord(0x009));
        processor.PC.setValue(parseDWord(0x000A));

        assertEquals("A must be 0x01", parseWord(0x01).getValue(), processor.A.getValue());
        assertEquals("B must be 0x02", parseWord(0x02).getValue(), processor.B.getValue());
        assertEquals("C must be 0x03", parseWord(0x03).getValue(), processor.C.getValue());
        assertEquals("D must be 0x04", parseWord(0x04).getValue(), processor.D.getValue());
        assertEquals("E must be 0x05", parseWord(0x05).getValue(), processor.E.getValue());
        assertEquals("F must be 0x06", parseWord(0x06).getValue(), processor.F.getValue());
        assertEquals("H must be 0x07", parseWord(0x07).getValue(), processor.H.getValue());
        assertEquals("L must be 0x08", parseWord(0x08).getValue(), processor.L.getValue());
        assertEquals("AF must be 0x0106", parseDWord(0x0106), processor.AF);
        assertEquals("BC must be 0x0203", parseDWord(0x0203), processor.BC);
        assertEquals("DE must be 0x0405", parseDWord(0x0405), processor.DE);
        assertEquals("HL must be 0x0708", parseDWord(0x0708), processor.HL);
        assertEquals("SP must be 0x0009", parseDWord(0x0009), processor.SP);
        assertEquals("PC must be 0x000A", parseDWord(0x000A), processor.PC);
    }
}
