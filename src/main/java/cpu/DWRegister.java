package cpu;

import core.DWord;
import lombok.NonNull;

public class DWRegister extends DWord {
    protected DWRegister() {
        super();
    }

    protected DWRegister(WRegister highWord, WRegister lowWord) {
        this.highWord = highWord;
        this.lowWord = lowWord;
    }

    public static DWRegister newInstance() {
        return new DWRegister();
    }

    public static DWRegister fromPair(@NonNull WRegister high, @NonNull WRegister low) {
        return new DWRegister(high, low);
    }

    public DWord getValue() {
        return this;
    }

}
