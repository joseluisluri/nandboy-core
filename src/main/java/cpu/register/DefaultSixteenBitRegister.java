package cpu.register;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DefaultSixteenBitRegister implements SixteenBitRegister {
	private byte highByte;
	private byte lowByte;

	public byte[] getValue() {
		return new byte[]{highByte, lowByte};
	}

	public void setValue(byte[] b) {
		assert (b.length == 2);
		highByte = b[0];
		lowByte = b[1];
	}
}
