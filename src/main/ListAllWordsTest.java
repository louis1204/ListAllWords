package main;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListAllWordsTest {

	ListAllWords law;
	
	public ListAllWordsTest() {
	}

	@Before
	public void setUp() throws Exception {
		law = new ListAllWords("");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void ContructorTest() {
		assertNotNull(law);
	}
	
	@Test
	public void GetterTest()
	{
		assertEquals("", law.getWord());
	}

	@Test
	public void SetterTest()
	{
		law.setWord("abcdefghijklmnopqrstuvwxyz");
		assertEquals("abcdefghijklmnopqrstuvwxyz", law.getWord());
	}
	
	@Test
	public void GetSetCountTest()
	{
		law.setWord("helium");
		law.getWords();
		assertEquals(law.getSetCount(), 1956);
	}
	
	@Test
	public void DictionaryTest()
	{
		assertEquals(law.checkDictionary("lkjasdfl"), false);
		assertEquals(law.checkDictionary("hello"), true);
	}
	
	@Test
	public void GetWordsTest()
	{
		law.setWord("helio");
		law.getWords();
		assertEquals(law.getSetCount(), 325);
	}
}
