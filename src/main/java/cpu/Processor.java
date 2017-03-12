package cpu;


import cpu.register.*;

public class Processor {

	private static Processor singleton = null;

	public static final EightBitRegister A = new DefaultEightBitRegister();
	public static final EightBitRegister B = new DefaultEightBitRegister();
	public static final EightBitRegister C = new DefaultEightBitRegister();
	public static final EightBitRegister D = new DefaultEightBitRegister();
	public static final EightBitRegister E = new DefaultEightBitRegister();
	public static final FlagEightBitRegister F = new FlagEightBitRegister();
	public static final EightBitRegister H = new DefaultEightBitRegister();
	public static final EightBitRegister L = new DefaultEightBitRegister();

	public static final SixteenBitRegister SP = new DefaultSixteenBitRegister(); /*< Stack Pointer */
	public static final SixteenBitRegister PC = new DefaultSixteenBitRegister(); /*< Program Counter */
	public static final SixteenBitRegister AF = new ComposedSixteenBitRegister(A, F); /*< Accumulator & Flags */
	public static final SixteenBitRegister BC = new ComposedSixteenBitRegister(B, C);
	public static final SixteenBitRegister DE = new ComposedSixteenBitRegister(D, E);
	public static final SixteenBitRegister HL = new ComposedSixteenBitRegister(H, L);

	private Processor() {
	}

	public static Processor getInstance() {
		return singleton == null ? new Processor() : singleton;
	}



}
