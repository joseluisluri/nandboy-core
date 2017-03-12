package cpu.register;

import lombok.Data;

@Data
class DefaultEightBitRegister implements EightBitRegister {
	private byte value;
}