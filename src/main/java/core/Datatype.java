package core;

import lombok.NonNull;

public class Datatype {
    /**
     * Word - (8-bits)
     */
    public static Word createWord() {
        return new Word();
    }

    public static Word parseWord(int i) {
        return new Word((byte) (0xFF & i));
    }

    public static Word parseWord(byte b) {
        return new Word(b);
    }

    /**
     * DWord - Double word (16-bits)
     */
    public static DWord createDWord() {
        return new DWord();
    }

    public static DWord createDWord(@NonNull Word highWord, @NonNull Word lowWord) {
        return new DWord(highWord, lowWord);
    }

    public static DWord parseDWord(int i) {
        return new DWord(parseWord(0xFF & (i >> 8)), parseWord(0xFF & i));
    }

    /**
     * MemoryAddr - Memory Address (16-bits)
     */
    public static MemoryAddr createMemoryAddr() {
        return new MemoryAddr();
    }

    public static MemoryAddr parseMemoryAddr(int i) {
        return new MemoryAddr(parseDWord(i));
    }

    public static MemoryAddr parseMemoryAddr(DWord dWord) {
        return new MemoryAddr(dWord);
    }

    public static int parseInt(DWord dWord) {
        return ((0x0FF & dWord.getHighWord().getValue()) << 8) + (0x0FF & dWord.getLowWord().getValue());
    }

    public static int parseInt(Word word) {
        return 0xFF & word.getValue();
    }


}
