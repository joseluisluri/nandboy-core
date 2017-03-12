package cpu.opcode;

public interface OpCode {
	short getCycles();
	void execute();

	static OpCode fromByte(byte opCode) throws OpCodeException {
		try {
			String className = String.format("%s0x%02d", OpCode.class.getCanonicalName(), opCode);
			Class opCodeClass = Class.forName(className);
			return (OpCode) opCodeClass.newInstance();
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			throw new OpCodeException("OpCode implementation cannot be loaded: " + e, e);
		}
	}
}
