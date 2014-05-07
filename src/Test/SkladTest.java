package Test;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.List;

import Source.Sklad;

public class SkladTest {

	private Sklad<String> instance;

	@Before
	public void setUp() {
		this.instance = new Sklad<String>();
	}

	@Test
	public void testAdd() {
		instance.add("a");
	}

	@Test
	public void testRemoveFirst_One() {
		instance.add("a");
		assertEquals("a", instance.removeFirst());
	}

	@Test
	public void testRemoveFirst_Two() {
		instance.add("123");
		instance.add("12");
		assertFalse(instance.removeFirst().equals("123"));
		assertTrue(instance.removeFirst().equals("123"));
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testRemoveFirst_Empty() {
		instance.removeFirst();
	}

	@Test
	public void testIsEmpty_OnEmpty() {
		assertTrue(instance.isEmpty());
	}

	@Test
	public void testIsEmpty_OnFull() {
		instance.add("A");
		assertFalse(instance.isEmpty());
	}

	@Test
	public void testGetFirst_One() {
		instance.add("do re mi");
		assertFalse(instance.getFirst().equals("test"));
		assertTrue(instance.getFirst().equals("do re mi"));
	}

	@Test
	public void testGetFirst_Two() {
		instance.add("Hello");
		instance.add("World");
		assertEquals("World", instance.getFirst());
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testGetFirst_Empty() {
		instance.getFirst();
	}

	@Test
	public void testAddRemoveFirstGetFirst_Zero() {
		instance.add("b");
		instance.add("a");
		assertEquals("a", instance.getFirst());
		assertTrue(instance.removeFirst().equals("a"));
		assertEquals("b", instance.getFirst());
		assertEquals("b", instance.removeFirst());
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testAddRemoveFirstGetFirst_One() {
		instance.add("b");
		instance.add("a");
		assertEquals("a", instance.getFirst());
		assertEquals("a", instance.removeFirst());
		assertEquals("b", instance.removeFirst());
		instance.removeFirst();
	}

	@Test
	public void testSize_Zero() {
		assertEquals(0, instance.size());
	}

	@Test
	public void testDepth_Zero() {
		assertEquals(0, instance.depth());
	}

	@Test
	public void testSize_One() {
		instance.add("a");
		assertEquals(1, instance.size());
	}

	@Test
	public void testDepth_One() {
		instance.add("a");
		assertEquals(1, instance.depth());
	}

	@Test
	public void testSize_Two() {
		instance.add("a");
		instance.add("b");
		assertEquals(2, instance.size());
	}

	@Test
	public void testDepth_Two() {
		instance.add("a");
		instance.add("b");
		assertEquals(2, instance.depth());
	}

	@Test
	public void testSize_Random() {
		int numOfEle = (int)(Math.random()*100);
		for(int i = numOfEle; i > 0; i--) {
				instance.add("a");
		}
		assertEquals(numOfEle, instance.size());
	}

	@Test
	public void testDepth_Random() {
		int numOfEle = (int)(Math.random()*100);
		for(int i = numOfEle; i > 0; i--) {
			instance.add("a");
		}
		assertEquals(numOfEle, instance.depth());
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testRemove_Empty() {
		instance.remove("Hello");
	}

	@Test
	public void testRemove_One() {
		instance.add("hello");
		instance.add("world");
		instance.add("Hello");
		assertEquals("Hello", instance.remove("Hello"));
	}

	@Test
	public void testRemove_Two() {
		instance.add("hello");
		instance.add("world");
		instance.add("Hello");
		assertEquals("world", instance.remove("world"));
		assertEquals("hello", instance.remove("hello"));
		assertEquals("Hello", instance.remove("Hello"));
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testRemove_BadElement() {
		instance.add("hello");
		instance.add("world");
		instance.add("Hello");
		instance.remove("hello world");
	}

	@Test
	public void testExists_Empty() {
		assertFalse(instance.exists("test"));
	}

	@Test
	public void testExists_True() {
		instance.add("test");
		instance.add("TEST");
		instance.add("HeLo WoRlD");
		assertTrue(instance.exists("HeLo WoRlD"));
	}

	@Test
	public void testExists_False() {
		instance.add("test");
		instance.add("TEST");
		instance.add("HeLo WoRlD");
		assertFalse(instance.exists("Hello World"));
	}

	@Test
	public void testExists_Mix() {
		instance.add("TeSi");
		instance.add("Msi");
		instance.add("Hello");
		instance.add("Who did that?");
		assertFalse(instance.exists("Who am I??"));
		assertTrue(instance.exists("TeSi"));
	}

	@Test
	public void testAsList_Empty() {
		assertTrue(instance.isEmpty());
		assertEquals(null, instance.asList());
	}

	@Test
	public void testAsList_One() {
		List<String> testList;
		StringBuilder testString = new StringBuilder();
		instance.add("Hello");
		testList = instance.asList();
		for(String s : testList) {
			testString.append(s).append(' ');
		}
		assertEquals("Hello ", testString.toString());
	}

	@Test
	public void testAsList_MoreStrings() {
		List<String> testList;
		StringBuilder testString = new StringBuilder();
		instance.add("Hello");
		instance.add("world");
		instance.add("from");
		instance.add("Java");
		instance.add("How are you?");
		instance.add("New");
		testList = instance.asList();
		for(String s : testList) {
			testString.append(s).append(' ');
		}
		assertEquals("New How are you? Java from world Hello ", testString.toString());
	}
}
