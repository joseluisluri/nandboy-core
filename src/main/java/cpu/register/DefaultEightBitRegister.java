package cpu.register;

import lombok.Data;

@Data
public class DefaultEightBitRegister implements EightBitRegister {
	private byte value;
}