/*package cpu;

import java.util.Arrays;
import java.util.Collection;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Suite;
*/
/**
 * Thanks to: http://slack.net/~ant/old/gb-tests/
 *
 *
 * Normal instructions:
 *
 * 1,3,2,2,1,1,2,1,5,2,2,2,1,1,2,1, 0,3,2,2,1,1,2,1,3,2,2,2,1,1,2,1, 2,3,2,2,1,1,2,1,2,2,2,2,1,1,2,1,
 * 2,3,2,2,3,3,3,1,2,2,2,2,1,1,2,1, 1,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1, 1,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,
 * 1,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1, 2,2,2,2,2,2,0,2,1,1,1,1,1,1,2,1, 1,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,
 * 1,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1, 1,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1, 1,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,
 * 2,3,3,4,3,4,2,4,2,4,3,0,3,6,2,4, 2,3,3,0,3,4,2,4,2,4,3,0,3,0,2,4, 3,3,2,0,0,4,2,4,4,1,4,0,0,0,2,4,
 * 3,3,2,1,0,4,2,4,3,2,4,1,0,0,2,4
 *
 * CB-prefixed instructions:
 *
 * 2,2,2,2,2,2,4,2,2,2,2,2,2,2,4,2, 2,2,2,2,2,2,4,2,2,2,2,2,2,2,4,2, 2,2,2,2,2,2,4,2,2,2,2,2,2,2,4,2,
 * 2,2,2,2,2,2,4,2,2,2,2,2,2,2,4,2, 2,2,2,2,2,2,3,2,2,2,2,2,2,2,3,2, 2,2,2,2,2,2,3,2,2,2,2,2,2,2,3,2,
 * 2,2,2,2,2,2,3,2,2,2,2,2,2,2,3,2, 2,2,2,2,2,2,3,2,2,2,2,2,2,2,3,2, 2,2,2,2,2,2,4,2,2,2,2,2,2,2,4,2,
 * 2,2,2,2,2,2,4,2,2,2,2,2,2,2,4,2, 2,2,2,2,2,2,4,2,2,2,2,2,2,2,4,2, 2,2,2,2,2,2,4,2,2,2,2,2,2,2,4,2,
 * 2,2,2,2,2,2,4,2,2,2,2,2,2,2,4,2, 2,2,2,2,2,2,4,2,2,2,2,2,2,2,4,2, 2,2,2,2,2,2,4,2,2,2,2,2,2,2,4,2,
 * 2,2,2,2,2,2,4,2,2,2,2,2,2,2,4,2
 *//*
@RunWith(Suite.class)
@Suite.SuiteClasses({InstructionTest.CyclesTest.class})
public class InstructionTest {

	@RunWith(Parameterized.class)
	public static class CyclesTest {

		private short cycles;
		private byte opcode;

		@Parameterized.Parameters
		public static Collection<Object[]> common() {
			return Arrays.asList(new Object[][]{
				// normal instructions
				{(short) 1, (byte) 0x00}, //{(short) 3, (byte) 0x01}
			});
		}

		public CyclesTest(short cycles, byte opcode) {
			this.cycles = cycles;
			this.opcode = opcode;
			Processor.getInstance(); // init
		}

		@Test
		public void test() throws NoSuchOpcodeException {
			//assertEquals("Invalid number of cycles", cycles,
			//ProcessorHelper.getInstructionFromOpcode(opcode).));
		}
	}
}*/
