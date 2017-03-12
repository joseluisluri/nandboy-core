package cpu.register;

import lombok.Getter;
import lombok.Setter;

public class FlagEightBitRegister implements EightBitRegister {
	private final int zeroFlagBitmask = 0x80; // 7th bit
	private final int subtractBitmask = 0x40; // 6th bit
	private final int halfCarryBitmask = 0x20; // 5th bit
	private final int carryFlagBitmask = 0x10; // 4th bit and 0-3 always zero

	@Setter
	@Getter
	private byte value;

	public boolean getZeroFlag() {
		return (zeroFlagBitmask & value) == zeroFlagBitmask;
	}

	public void setZeroFlag(boolean b) {
		value = (byte) (b ? (zeroFlagBitmask | value) : (zeroFlagBitmask ^ value));
	}

	public boolean getSubtractFlag() {
		return (subtractBitmask & value) == subtractBitmask;
	}

	public void setSubtractFlag(boolean b) {
		value = (byte) (b ? (subtractBitmask | value) : (subtractBitmask ^ value));
	}

	public boolean getHalfCarryFlag() {
		return (halfCarryBitmask & value) == halfCarryBitmask;
	}

	public void setHalfCarryFlag(boolean b) {
		value = (byte) (b ? (halfCarryBitmask | value) : (halfCarryBitmask ^ value));
	}

	public boolean getCarryFlag() {
		return (carryFlagBitmask & value) == carryFlagBitmask;
	}

	public void setCarryFlag(boolean b) {
		value = (byte) (b ? (carryFlagBitmask | value) : (carryFlagBitmask ^ value));
	}
}