package cpu.register;

public interface EightBitRegister {
    byte getValue();

    void setValue(byte b);

    static EightBitRegister newInstance() {
        return new DefaultEightBitRegister();
    }
}
