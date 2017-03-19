package cpu;

import core.DWord;
import core.Datatype;
import core.MemoryAddr;
import core.Word;
import memory.MemoryHelper;
import memory.MemoryMap;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static core.Datatype.*;

public class Processor {

	private static Processor singleton = null;

	public static Processor getInstance() {
		if (singleton == null) {
			singleton = new Processor(MemoryMap.getInstance());
		}
		return singleton;
	}

	protected final MemoryMap memoryMap;

	/**
	 * Registers
	 */
	protected final WRegister A = WRegister.newInstance(); // Accumulator
	protected final WRegister B = WRegister.newInstance();
	protected final WRegister C = WRegister.newInstance();
	protected final WRegister D = WRegister.newInstance();
	protected final WRegister E = WRegister.newInstance();
	protected final WRegister F = WRegister.newInstance(); // Flags
	protected final WRegister H = WRegister.newInstance();
	protected final WRegister L = WRegister.newInstance();

	protected final DWRegister AF = DWRegister.fromPair(A, F);
	protected final DWRegister BC = DWRegister.fromPair(B, C);
	protected final DWRegister DE = DWRegister.fromPair(D, E);
	protected final DWRegister HL = DWRegister.fromPair(H, L);
	protected final DWRegister SP = DWRegister.newInstance(); //Stack Pointer
	protected final DWRegister PC = DWRegister.newInstance(); // Program Counter

	/**
	 * Program Counter is initialized to 0x0100 when the GameBoy is turned on.
	 */
	private static final DWord PC_INIT_VALUE = Datatype.parseDWord(0x0100);


	protected Processor(MemoryMap memoryMap) {

		this.memoryMap = memoryMap;

		PC.setValue(PC_INIT_VALUE);

        Method[] methods = Processor.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Opcode.class)) {
                Opcode opcode = method.getAnnotation(Opcode.class);
                isa.put((byte) opcode.code(), new Instruction(opcode, method));
            }
        }
    }

	final static Map<Byte, Instruction> isa = new HashMap<>(); /*< Instruction Set Architecture */

    @Opcode(code = 0x00, cycles = 1) void x00() { /* NOP */ }

	// Not @Opcode(code = 0x36)

	/**
	 * 8-Bit Loads
	 */
    // LD A, r8
	@Opcode(code = 0x7F, cycles = 4) void x7F() { LD_RR(A, A); }
	@Opcode(code = 0x78, cycles = 4) void x78() { LD_RR(A, B); }
	@Opcode(code = 0x79, cycles = 4) void x79() { LD_RR(A, C); }
	@Opcode(code = 0x7A, cycles = 4) void x7A() { LD_RR(A, D); }
	@Opcode(code = 0x7B, cycles = 4) void x7B() { LD_RR(A, E); }
	@Opcode(code = 0x7C, cycles = 4) void x7C() { LD_RR(A, H); }
	@Opcode(code = 0x7D, cycles = 4) void x7D() { LD_RR(A, L); }

	// LD A, (r16)
	@Opcode(code = 0x0A, cycles = 8) void x0A() { LD_RM(A, BC); }
	@Opcode(code = 0x1A, cycles = 8) void x1A() { LD_RM(A, DE); }
	@Opcode(code = 0x7E, cycles = 8) void x7E() { LD_RM(A, HL); }

	// LD A, (ii)
	@Opcode(code = 0xFA, cycles = 16) void xFA() { LD_RMI(A); }

	// LD (ii), A
	@Opcode(code = 0x02, cycles = 8) void x02() { LD_MR(BC, A); }
	@Opcode(code = 0x12, cycles = 8) void x12() { LD_MR(DE, A); }
	@Opcode(code = 0x77, cycles = 8) void x77() { LD_MR(HL, A); }
	@Opcode(code = 0xEA, cycles = 16) void xEA() { LD_MIR(A); }

	// LD A, (C)
	@Opcode(code = 0xF2, cycles = 8) void xF2() {
		DWord address = parseDWord(0xFF00);
		ProcessorHelper.inc(address, C);
		A.setValue(memoryMap.retrieveWord(parseMemoryAddr(address)));
	}

	// LD (C), A
	@Opcode(code = 0xF2, cycles = 8) void xE2() {
		DWord address = parseDWord(0xFF00);
		ProcessorHelper.inc(address, C);
		memoryMap.loadWord(parseMemoryAddr(address), A);
	}

	// LD B,
	@Opcode(code = 0x47, cycles = 4) void x47() { LD_RR(B, A); }
	@Opcode(code = 0x40, cycles = 4) void x40() { LD_RR(B, B); }
	@Opcode(code = 0x41, cycles = 4) void x41() { LD_RR(B, C); }
	@Opcode(code = 0x42, cycles = 4) void x42() { LD_RR(B, D); }
	@Opcode(code = 0x43, cycles = 4) void x43() { LD_RR(B, E); }
	@Opcode(code = 0x44, cycles = 4) void x44() { LD_RR(B, H); }
	@Opcode(code = 0x45, cycles = 4) void x45() { LD_RR(B, L); }

	// LD B, (r16)
	@Opcode(code = 0x46, cycles = 8) void x46() { LD_RM(B, HL); }

	// LD C, r8
	@Opcode(code = 0x4F, cycles = 4) void x4F() { LD_RR(C, A); }
	@Opcode(code = 0x48, cycles = 4) void x48() { LD_RR(C, B); }
	@Opcode(code = 0x49, cycles = 4) void x49() { LD_RR(C, C); }
	@Opcode(code = 0x4A, cycles = 4) void x4A() { LD_RR(C, D); }
	@Opcode(code = 0x4B, cycles = 4) void x4B() { LD_RR(C, E); }
	@Opcode(code = 0x4C, cycles = 4) void x4C() { LD_RR(C, H); }
	@Opcode(code = 0x4D, cycles = 4) void x4D() { LD_RR(C, L); }

	// LD C, (r16)
	@Opcode(code = 0x4E, cycles = 8) void x4E() { LD_RM(C, HL); }

	// LD D, r8
	@Opcode(code = 0x57, cycles = 4) void x57() { LD_RR(D, A); }
	@Opcode(code = 0x50, cycles = 4) void x50() { LD_RR(D, B); }
	@Opcode(code = 0x51, cycles = 4) void x51() { LD_RR(D, C); }
	@Opcode(code = 0x52, cycles = 4) void x52() { LD_RR(D, D); }
	@Opcode(code = 0x53, cycles = 4) void x53() { LD_RR(D, E); }
	@Opcode(code = 0x54, cycles = 4) void x54() { LD_RR(D, H); }
	@Opcode(code = 0x55, cycles = 4) void x55() { LD_RR(D, L); }

	// LD D, (r16)
	@Opcode(code = 0x56, cycles = 8) void x56() { LD_RM(D, HL); }

	// LD E, r8
	@Opcode(code = 0x5F, cycles = 4) void x5F() { LD_RR(E, A); }
	@Opcode(code = 0x58, cycles = 4) void x58() { LD_RR(E, B); }
	@Opcode(code = 0x59, cycles = 4) void x59() { LD_RR(E, C); }
	@Opcode(code = 0x5A, cycles = 4) void x5A() { LD_RR(E, D); }
	@Opcode(code = 0x5B, cycles = 4) void x5B() { LD_RR(E, E); }
	@Opcode(code = 0x5C, cycles = 4) void x5C() { LD_RR(E, H); }
	@Opcode(code = 0x5D, cycles = 4) void x5D() { LD_RR(E, L); }

	// LD E, (r16)
	@Opcode(code = 0x5E, cycles = 8) void x5E() { LD_RM(E, HL); }

	// LD H, r8
	@Opcode(code = 0x67, cycles = 4) void x67() { LD_RR(H, A); }
	@Opcode(code = 0x60, cycles = 4) void x60() { LD_RR(H, B); }
	@Opcode(code = 0x61, cycles = 4) void x61() { LD_RR(H, C); }
	@Opcode(code = 0x62, cycles = 4) void x62() { LD_RR(H, D); }
	@Opcode(code = 0x63, cycles = 4) void x63() { LD_RR(H, E); }
	@Opcode(code = 0x64, cycles = 4) void x64() { LD_RR(H, H); }
	@Opcode(code = 0x65, cycles = 4) void x65() { LD_RR(H, L); }

	// LD H, (r16)
	@Opcode(code = 0x66, cycles = 8) void x66() { LD_RM(H, HL); }

	// LD L, r8
	@Opcode(code = 0x6F, cycles = 4) void x6F() { LD_RR(L, A); }
	@Opcode(code = 0x68, cycles = 4) void x68() { LD_RR(L, B); }
	@Opcode(code = 0x69, cycles = 4) void x69() { LD_RR(L, C); }
	@Opcode(code = 0x6A, cycles = 4) void x6A() { LD_RR(L, D); }
	@Opcode(code = 0x6B, cycles = 4) void x6B() { LD_RR(L, E); }
	@Opcode(code = 0x6C, cycles = 4) void x6C() { LD_RR(L, H); }
	@Opcode(code = 0x6D, cycles = 4) void x6D() { LD_RR(L, L); }

	// LD L, (r16)
	@Opcode(code = 0x6E, cycles = 8) void x6E() { LD_RM(L, HL); }

	// LD (HL), r8
	@Opcode(code = 0x70, cycles = 8) void x70() { LD_MR(HL, B); }
	@Opcode(code = 0x71, cycles = 8) void x71() { LD_MR(HL, C); }
	@Opcode(code = 0x72, cycles = 8) void x72() { LD_MR(HL, D); }
	@Opcode(code = 0x73, cycles = 8) void x73() { LD_MR(HL, E); }
	@Opcode(code = 0x74, cycles = 8) void x74() { LD_MR(HL, H); }
	@Opcode(code = 0x75, cycles = 8) void x75() { LD_MR(HL, L); }

	// LD (HL), i
	@Opcode(code = 0x36, cycles = 12) void x036() { LD_MI(HL); }

	// ADD A, r8
	@Opcode(code = 0x87, cycles = 4) void x87() { ADD_RR(A, A); }
	@Opcode(code = 0x80, cycles = 4) void x80() { ADD_RR(A, B); }
	@Opcode(code = 0x81, cycles = 4) void x81() { ADD_RR(A, C); }
	@Opcode(code = 0x82, cycles = 4) void x82() { ADD_RR(A, D); }
	@Opcode(code = 0x83, cycles = 4) void x83() { ADD_RR(A, E); }
	@Opcode(code = 0x84, cycles = 4) void x84() { ADD_RR(A, H); }
	@Opcode(code = 0x85, cycles = 4) void x85() { ADD_RR(A, L); }

	// ADD A, (r16)
	@Opcode(code = 0x83, cycles = 8) void x86() { ADD_RM(A, HL); }

	@Opcode(code = 0xC6, cycles = 8) void xC6() { ADD_RI(A); }


	// LD r8, i
	@Opcode(code = 0x3E, cycles = 0) void x3E() { LD_RI(A); }
	@Opcode(code = 0x06, cycles = 8) void x06() { LD_RI(B); }
	@Opcode(code = 0x0E, cycles = 8) void x0E() { LD_RI(C); }
	@Opcode(code = 0x16, cycles = 8) void x16() { LD_RI(D); }
	@Opcode(code = 0x1E, cycles = 8) void x1E() { LD_RI(E); }
	@Opcode(code = 0x26, cycles = 8) void x26() { LD_RI(H); }
	@Opcode(code = 0x2E, cycles = 8) void x2E() { LD_RI(L); }

	// LDD(HL),A

	/**
	 * 8-Bit ALU
	 */
	@Opcode(code = 0x8F, cycles = 4) void x8F() { ADC_RR(A, A); }
	@Opcode(code = 0x88, cycles = 4) void x88() { ADC_RR(A, B); }
	@Opcode(code = 0x89, cycles = 4) void x89() { ADC_RR(A, C); }
	@Opcode(code = 0x8A, cycles = 4) void x8A() { ADC_RR(A, D); }
	@Opcode(code = 0x8B, cycles = 4) void x8B() { ADC_RR(A, E); }
	@Opcode(code = 0x8C, cycles = 4) void x8C() { ADC_RR(A, H); }
	@Opcode(code = 0x8D, cycles = 4) void x8D() { ADC_RR(A, L); }
	// @Opcode(code = 8E
	// @Opcode(code = CE

	/**
	 * 16-Bit Airthmetic
	 */
	// INC r16
	@Opcode(code = 0x03, cycles = 8) void x03() { ProcessorHelper.inc(BC, parseWord(1)); }
	@Opcode(code = 0x13, cycles = 8) void x13() { ProcessorHelper.inc(DE, parseWord(1)); }
	@Opcode(code = 0x23, cycles = 8) void x23() { ProcessorHelper.inc(HL, parseWord(1)); }
	@Opcode(code = 0x33, cycles = 8) void x33() { ProcessorHelper.inc(SP, parseWord(1)); }

	// DEC r16
	@Opcode(code = 0x0B, cycles = 8) void x0B() { ProcessorHelper.dec(BC, parseWord(1)); }
	@Opcode(code = 0x1B, cycles = 8) void x1B() { ProcessorHelper.dec(DE, parseWord(1)); }
	@Opcode(code = 0x2B, cycles = 8) void x2B() { ProcessorHelper.dec(HL, parseWord(1)); }
	@Opcode(code = 0x3B, cycles = 8) void x3B() { ProcessorHelper.dec(SP, parseWord(1)); }

	/**
	 * LD r1, i8
	 * Put i into r1. (i is an 8-bit inmmediate value located in PC+1)
	 */
	protected void LD_RI(WRegister r1) {
		MemoryAddr i8 = MemoryHelper.getNextFrom(parseMemoryAddr(PC));
		r1.setValue(memoryMap.retrieveWord(i8));
		ProcessorHelper.inc(PC, parseWord(1));
	}

	/**
	 * LD r1, r2
	 * Put r2 into r1.
	 */
	protected void LD_RR(WRegister r1, WRegister r2) { A.setValue(r2); }

	/**
	 * LD r1, (r2)
	 * Put word located in memory address r2 into r1.
	 */
	protected void LD_RM(WRegister r1, DWRegister r2) {
		r1.setValue(memoryMap.retrieveWord(parseMemoryAddr(r2)));
	}

	/**
	 * LD (r1), r2
	 * Put r2 into memory address r1.
	 */
	protected void LD_MR(DWRegister r1, WRegister r2) {
		memoryMap.loadWord(parseMemoryAddr(r1), r2);
	}

	/**
	 * LD (r1), i8
	 * Put inmmediate into memory address located in r1.
	 */
	protected void LD_MI(DWRegister r1) {
		MemoryAddr r1Addr = parseMemoryAddr(r1);
		MemoryAddr i8Addr = MemoryHelper.getNextFrom(parseMemoryAddr(PC));
		memoryMap.loadWord(r1Addr, memoryMap.retrieveWord(i8Addr));
		ProcessorHelper.inc(PC, parseWord(1));
	}

	/**
	 * LD r1, (ii)
	 * ii = Two byte inmmediate value (LS byte first)
	 */
	protected void LD_RMI(WRegister r1) {
		MemoryAddr lowWordAddr = MemoryHelper.getNextFrom(parseMemoryAddr(PC)); // LS byte first
		MemoryAddr highWordAddr = MemoryHelper.getNextFrom(lowWordAddr);

		Word lowWord = memoryMap.retrieveWord(lowWordAddr);
		Word highWord = memoryMap.retrieveWord(highWordAddr);
		DWord address = createDWord(highWord, lowWord);

		MemoryAddr memoryAddr = parseMemoryAddr(address);
		r1.setValue(memoryMap.retrieveWord(memoryAddr));
	}

	/**
	 * LD (ii), r1
	 * Put value a into (ii)
	 * ii = Two byte inmmediate value (LS byte first)
	 */
	protected void LD_MIR(WRegister r1) {
		MemoryAddr lowWordAddr = MemoryHelper.getNextFrom(parseMemoryAddr(PC)); // LS byte first
		MemoryAddr highWordAddr = MemoryHelper.getNextFrom(lowWordAddr);

		Word lowWord = memoryMap.retrieveWord(lowWordAddr);
		Word highWord = memoryMap.retrieveWord(highWordAddr);
		DWord address = createDWord(highWord, lowWord);

		MemoryAddr memoryAddr = parseMemoryAddr(address);
		memoryMap.loadWord(memoryAddr, r1);
	}

	/**
	 * ADD r1, r2
	 * Add r2 into r1.
	 * Flags can be affected: Z, H, C.
	 */
	protected void ADD_RR(WRegister r1, WRegister r2) {
		ProcessorHelper.add(r1, r2);
	}

	protected void ADD_RM(WRegister r1, DWRegister r2) {
		MemoryAddr r2Addr = parseMemoryAddr(r2);
		ProcessorHelper.add(r1, memoryMap.retrieveWord(r2Addr));
	}

	/**
	 * ADD r1, (i16)
	 * Add (ii) into r1.
	 * Flags can be affected: Z, H, C.
	 */
	protected void ADD_RI(WRegister r1) {
		MemoryAddr i16Addr = MemoryHelper.getNextFrom(parseMemoryAddr(PC));
		ProcessorHelper.add(r1, memoryMap.retrieveWord(i16Addr));
	}

	/**
	 * ADC r1, r2 + CarryFlag
	 */
	protected void ADC_RR(WRegister r1, WRegister r2) {
		Word n = r2.clone();
		if (ProcessorHelper.getCarryFlag()) {
			ProcessorHelper.inc(n, parseWord(1));
		}
		ProcessorHelper.add(r1, n);
	}

	/**
	 * ADC r1, (r2) + CarryFlag
	 * Put word located in memory address r2 into r1.
	 */
	protected void ADC_RR(WRegister r1, DWRegister r2) {
		Word n = memoryMap.retrieveWord(parseMemoryAddr(r2));
		if (ProcessorHelper.getCarryFlag()) {
			ProcessorHelper.inc(n, parseWord(1));
		}
		ProcessorHelper.add(r1, n);
	}

	protected void reset() {
		DWord zero = Datatype.parseDWord(0);
		AF.copy(zero);
		BC.copy(zero);
		DE.copy(zero);
		HL.copy(zero);
		SP.copy(zero);
		PC.copy(zero);
	}
}
