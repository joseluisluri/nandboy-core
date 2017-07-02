package processor.registers;

import common.Word;
import lombok.NonNull;

public class WRegister extends Word {
    protected WRegister() {
    }

    public static WRegister newInstance() {
        return new WRegister();
    }

    public void copy(@NonNull WRegister register) {
        super.copy(register);
    }
}
