package cpu.register;

public interface SixteenBitRegister {
	void setValue(byte[] b);
	byte[] getValue();

	void setHighByte(byte b);
	byte getHighByte();

	void setLowByte(byte b);
	byte getLowByte();
}
