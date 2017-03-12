package cpu.register;

public interface SixteenBitRegister {
	void setValue(byte[] b);
	byte[] getValue();

	void setHighByte(byte b);
	byte getHighByte();

	void setLowByte(byte b);
	byte getLowByte();

	static SixteenBitRegister newInstance() {
		return new DefaultSixteenBitRegister();
	}

	static SixteenBitRegister newInstance(EightBitRegister highRegister, EightBitRegister lowRegister) {
		return new ComposedSixteenBitRegister(highRegister, lowRegister);
	}
}
