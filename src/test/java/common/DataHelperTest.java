package common;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataHelperTest {
	/**
	 * inc Word
	 */
	@Test
	public void incWordLowNibble() {
		Word word = DataHelper.parseWord(0x03);
		DataHelper.inc(word, DataHelper.parseWord(0x2));
		assertEquals("Value must be 0x05", DataHelper.parseWord(0x05), word);
	}

	@Test
	public void incWordHighNibble() {
		Word word = DataHelper.parseWord(0x0D);
		DataHelper.inc(word, DataHelper.parseWord(0x05));
		assertEquals("Value must be 0x12", DataHelper.parseWord(0x12), word);
	}

	@Test
	public void incWordOverflow() {
		Word word = DataHelper.parseWord(0xFF);
		DataHelper.inc(word, DataHelper.parseWord(0x2));
		assertEquals("Value must be 0x01", DataHelper.parseWord(0x01), word);
	}

	/**
	 * dec Word
	 */
	@Test
	public void decWordLowNibble() {
		Word word = DataHelper.parseWord(0x0C);
		DataHelper.dec(word, DataHelper.parseWord(0x2));
		assertEquals("Value must be 0x0A", DataHelper.parseWord(0x0A), word);
	}

	@Test
	public void decWordHighNibble() {
		Word word = DataHelper.parseWord(0x1B);
		DataHelper.dec(word, DataHelper.parseWord(0x0F));
		assertEquals("Value must be 0x0C", DataHelper.parseWord(0x0C), word);
	}

	@Test
	public void decWordUnderflow() {
		Word word = DataHelper.parseWord(0x01);
		DataHelper.dec(word, DataHelper.parseWord(0x2));
		assertEquals("Value must be 0xFF", DataHelper.parseWord(0xFF), word);
	}

	/**
	 * inc DWord
	 */
	@Test
	public void incDwordLowWord() {
		DWord dword = DataHelper.parseDWord(0x0001);
		DataHelper.inc(dword, DataHelper.parseWord(0x01));
		assertEquals("Value must be 0x0002", DataHelper.parseDWord(0x0002), dword);
	}

	@Test
	public void incDwordHighWord() {
		DWord dword = DataHelper.parseDWord(0x00FF);
		DataHelper.inc(dword, DataHelper.parseWord(0xFF));
		assertEquals("Value must be 0x01FE", DataHelper.parseDWord(0x01FE), dword);
	}

	@Test
	public void incDwordOverFlow() {
		DWord dword = DataHelper.parseDWord(0xFFFF);
		DataHelper.inc(dword, DataHelper.parseWord(0x02));
		assertEquals("Value must be 0x0001", DataHelper.parseDWord(0x0001), dword);
	}

	/**
	 * dec DWord
	 */
	@Test
	public void decDwordLowBytePart() {
		DWord dword = DataHelper.parseDWord(0x0005);
		DataHelper.dec(dword, DataHelper.parseWord(0x03));
		assertEquals("Value must be 0x0002", DataHelper.parseDWord(0x0002), dword);
	}

	@Test
	public void decDwordHighBytePart() {
		DWord dword = DataHelper.parseDWord(0x100);
		DataHelper.dec(dword, DataHelper.parseWord(0xFF));
		assertEquals("Value must be 0x0001", DataHelper.parseDWord(0x0001), dword);
	}

	@Test
	public void decDwordUnderFlow() {
		DWord dword = DataHelper.parseDWord(0xAAAA);
		DataHelper.dec(dword, DataHelper.parseWord(0xFF));
		assertEquals("Value must be 0xA9AB", DataHelper.parseDWord(0xA9AB), dword);
	}
}
