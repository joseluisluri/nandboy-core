package common;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import processor.registers.DWRegister;

@EqualsAndHashCode
public class DWord {
    protected final String STRING_FORMAT = "0x%02X%02X";

    protected final Word highWord;
    protected final Word lowWord;

    protected DWord() {
        highWord = new Word();
        lowWord = new Word();
    }

    protected DWord(@NonNull Word highWord, @NonNull Word lowWord) {
        this.highWord = highWord;
        this.lowWord = lowWord;
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
        return new DWord(getHighWord(), getLowWord());
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT, highWord.getValue(), lowWord.getValue());
    }
}