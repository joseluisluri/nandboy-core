package cpu.register;

import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;

@Setter
@Getter
public class ComposedSixteenBitRegister extends DefaultSixteenBitRegister {
	private final EightBitRegister highRegister;
	private final EightBitRegister lowRegister;

	public ComposedSixteenBitRegister(@NonNull EightBitRegister highRegister, @NonNull EightBitRegister lowRegister) {
		this.highRegister = highRegister;
		this.lowRegister = lowRegister;
	}

	public void setValue(byte[] data) {
		assert (data.length == 2);
		setHighByte(data[0]);
		setLowByte(data[1]);
	}

	public byte[] getValue() {
		return new byte[]{highRegister.getValue(), lowRegister.getValue()};
	}
}
