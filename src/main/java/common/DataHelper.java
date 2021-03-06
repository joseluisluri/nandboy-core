package common;

import lombok.NonNull;

public class DataHelper {
    /*
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

    /*
     * DWord - Double word (16-bits)
     */
    public static DWord createDWord() {
        return new DWord();
    }

    public static DWord createDWord(@NonNull Word highWord, @NonNull Word lowWord) {
        return new DWord(highWord, lowWord);
    }

    public static DWord parseDWord(int i) {
        return new DWord(parseWord(0xFF & (i >> 8)), parseWord(i));
    }

    /*
     * MemoryAddr - Memory Address (16-bits)
     */
    public static MemoryAddr createMemoryAddr() {
        return new MemoryAddr();
    }

    public static MemoryAddr parseMemoryAddr(int i) {
        return new MemoryAddr(parseDWord(i));
    }

    public static MemoryAddr parseMemoryAddr(@NonNull DWord dWord) {
        return new MemoryAddr(dWord);
    }

    /*
     * Integer
     */
    public static int parseInt(@NonNull DWord dWord) {
        return ((0x0FF & dWord.getHighWord().getValue()) << 8) + (0x0FF & dWord.getLowWord().getValue());
    }

    public static int parseInt(@NonNull Word word) {
        return 0xFF & word.getValue();
    }

    /*
     * Increment
     */
    public static void inc(@NonNull Word word, @NonNull Word i) {
        word.copy(parseWord(word.getValue() + i.getValue()));
    }

    public static void inc(Word word, int i) {
        inc(word, parseWord(i));
    }

	public static void inc(@NonNull DWord dWord, @NonNull Word i) {
		int result = parseInt(dWord) + (0xFF & i.getValue());
		dWord.setValue(parseDWord(result));
	}

	public static void inc(DWord dWord, int i) {
		inc(dWord, parseWord(i));
	}

	/*
	 * Decrement
	 */
    public static void dec(@NonNull Word word, @NonNull Word i) {
        word.copy(parseWord(word.getValue() - i.getValue()));
    }

    public static void dec(Word word, int i) {
        dec(word, parseWord(i));
    }

    public static void dec(@NonNull DWord dWord, @NonNull Word i) {
        int result = parseInt(dWord) - (0xFF & i.getValue());
        dWord.setValue(parseDWord(result));
    }

    public static void dec(DWord dWord, int i) {
    	dec(dWord, parseWord(i));
    }
}