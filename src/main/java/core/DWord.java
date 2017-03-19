package core;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode
public class DWord {
    protected final String STRING_FORMAT = "0x%02X%02X";

    protected Word highWord = new Word();
    protected Word lowWord = new Word();

    protected DWord() {}

    protected DWord(DWord dWord) {
        setValue(dWord);
    }

    protected DWord(Word highWord, Word lowWord) {
        this.highWord.copy(highWord);
        this.lowWord.copy(lowWord);
    }

    public void setHighWord(@NonNull Word word) {
        highWord.copy(word);
    }

    public void setLowWord(@NonNull Word word) {
        lowWord.copy(word);
    }

    public Word getHighWord() {
        return highWord.clone();
    }

    public Word getLowWord() {
        return lowWord.clone();
    }

    public void setValue(@NonNull DWord dWord) {
        copy(dWord);
    }

    public void copy(@NonNull DWord dWord) {
        highWord.copy(dWord.highWord);
        lowWord.copy(dWord.lowWord);
    }

    @Override
    public DWord clone() {
        return new DWord(this);
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT, highWord.getValue(), lowWord.getValue());
    }

}
