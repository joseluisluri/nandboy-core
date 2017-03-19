package core;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
public class Word {
    protected final String STRING_FORMAT = "0x%02X";

    protected byte value;

    protected Word() {
    }

    protected Word(byte value) {
        this.value = value;
    }

    protected Word(Word word) {
        copy(word);
    }

    public void setValue(Word word) {
        value = word.value;
    }

    public void copy(@NonNull Word word) {
        value = word.value;
    }

    public Word clone() {
        return new Word(this);
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT, (int) value);
    }
}
