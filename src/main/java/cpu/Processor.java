package cpu;

import cpu.register.*;

import java.util.HashMap;
import java.util.Map;

public class Processor {

	private static Map<Byte, Runnable> isa = new HashMap<>(); /*< Instruction Set Architecture */

	private static Processor singleton = null;

	static final EightBitRegister A = EightBitRegister.newInstance();
	static final EightBitRegister B = EightBitRegister.newInstance();
	static final EightBitRegister C = EightBitRegister.newInstance();
	static final EightBitRegister D = EightBitRegister.newInstance();
	static final EightBitRegister E = EightBitRegister.newInstance();
	static final EightBitRegister H = EightBitRegister.newInstance();
	static final EightBitRegister L = EightBitRegister.newInstance();

	static final FlagEightBitRegister F = FlagEightBitRegister.newInstance();

	static final SixteenBitRegister SP = SixteenBitRegister.newInstance(); /*< Stack Pointer */
	static final SixteenBitRegister PC = SixteenBitRegister.newInstance(); /*< Program Counter */
	static final SixteenBitRegister AF = SixteenBitRegister.newInstance(A, F); /*< Accumulator & Flags */
	static final SixteenBitRegister BC = SixteenBitRegister.newInstance(B, C);
	static final SixteenBitRegister DE = SixteenBitRegister.newInstance(D, E);
	static final SixteenBitRegister HL = SixteenBitRegister.newInstance(H, L);

	public static Processor getInstance() {
		return singleton == null ? new Processor() : singleton;
	}

	private Processor() {
		isa.put((byte) 0x00, this::opcode0x00);
		//isa.put((byte) 0x01, this::opcode0x01);
	}

	@Timing(cycles = 1)
	void opcode0x00() {
		// NOP
	}

	/**
	 * Opcode: 06
	 * Instruction: LD B,M
	 * Puts M into B.
	 */
	@Timing(cycles = 8)
	void opcode0x06() {
		//TODO
		/* En principio en la dirección que apunta PC debe estar el valor inmediato que hay que cargar en B
		https://github.com/drhelius/Gearboy/blob/master/src/opcodes.cpp
		https://github.com/drhelius/Gearboy/blob/master/src/Processor_inline.h
		 */
		B.setValue(PC.getLowByte());
	}
}
