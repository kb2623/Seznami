package Test;

import Source.Bst;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import Comparators.*;

public class BstTest {

	private Bst<String> bst;
	private Bst<Integer> instance;

	@Before
	public void setUp() {
		this.bst = new Bst<>(new CompareString());
		this.instance = new Bst<>(new CompareInteger());
	}
	
	@Test
	public void testBst() {
		assertTrue(instance.isEmpty());
		instance.add(8);
		instance.add(3);
		instance.add(10);
		instance.add(1);
		assertFalse(instance.isEmpty());
		instance.add(6);
		instance.add(14);
		assertEquals(new Integer(8), instance.getFirst());
		instance.add(4);
		instance.add(7);
		assertEquals(8, instance.size());
		instance.add(13);
		assertEquals(4, instance.depth());
		assertEquals(9, instance.size());
		assertEquals(new Integer(8), instance.removeFirst());
		assertEquals(4, instance.depth());
		assertEquals(8, instance.size());
		assertEquals(new Integer(10), instance.getFirst());
		assertFalse(instance.exists(100));
		assertEquals(new Integer(4), instance.remove(4));
		assertEquals(new Integer(7), instance.remove(7));
		assertTrue(instance.exists(14));
		assertFalse(instance.isEmpty());
		assertEquals(3, instance.depth());
		assertEquals(6, instance.size());
		assertEquals(new Integer(10), instance.removeFirst());
		assertEquals(new Integer(13), instance.removeFirst());
		assertEquals(new Integer(14), instance.removeFirst());
		assertFalse(instance.isEmpty());
		assertEquals(3, instance.size());
		assertEquals(2, instance.depth());
		assertEquals(new Integer(3), instance.removeFirst());
		assertEquals(new Integer(6), instance.removeFirst());
		assertEquals(new Integer(1), instance.removeFirst());
		assertTrue(instance.isEmpty());
	}

	@Test
	public void testTwo() {
		instance.add(89);
		instance.add(32);
		instance.add(109);
		instance.add(90);
		instance.add(100);
		assertFalse(instance.exists(101));
		assertTrue(instance.exists(32));
	}

	@Test
	public void testAsList_Empty() {
		assertTrue(instance.isEmpty());
		assertEquals(null, instance.asList());
	}

	@Test
	public void testAsList_One() {
		StringBuilder testString = new StringBuilder();
		List<Integer> testList;
		instance.add(32);
		testList = instance.asList();
		for(Integer i : testList) {
			testString.append(i).append(' ');
		}
		assertEquals("32 ", testString.toString());
	}

	@Test
	public void testAsList_MoreString_One() {
		StringBuilder testString = new StringBuilder();
		List<Integer> testList;
		instance.add(23);
		instance.add(12);
		instance.add(89);
		instance.add(45);
		instance.add(32);
		instance.add(3);
		instance.add(1);
		instance.add(19);
		testList = instance.asList();
		for(Integer i : testList) {
			testString.append(i).append(' ');
		}
		assertEquals("1 3 12 19 23 32 45 89 ", testString.toString());
	}
	
	@Test
	public void testAsList_MoreStrings_Two() {
		StringBuilder testString = new StringBuilder();
		List<Integer> testList;
		instance.add(32);
		instance.add(42);
		instance.add(74);
		instance.add(99);
		instance.add(23);
		instance.add(12);
		instance.add(67);
		instance.add(24);
		instance.add(46);
		instance.add(1);
		instance.add(6);
		instance.add(91);
		instance.add(88);
		instance.add(70);
		testList = instance.asList();
		for(Integer i : testList) {
			testString.append(i).append(' ');
		}
		assertEquals("1 6 12 23 24 32 42 46 67 70 74 88 91 99 ", testString.toString());
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testOne() {
		instance.add(89);
		instance.add(32);
		instance.add(109);
		instance.add(90);
		instance.add(100);
		assertEquals(new Integer(89), instance.removeFirst());
		assertEquals(new Integer(90), instance.getFirst());
		assertEquals(new Integer(100), instance.remove(100));
		assertEquals(new Integer(90), instance.removeFirst());
		assertEquals(new Integer(109), instance.getFirst());
		assertEquals(new Integer(32), instance.remove(32));
		assertEquals(new Integer(109), instance.getFirst());
		assertEquals(new Integer(109), instance.removeFirst());
		instance.removeFirst();
	}

	@Test
	public void testAdd() {
		instance.add(22);
		instance.add(100);
		assertTrue(instance.exists(22));
		instance.add(53);
		instance.add(89);
		instance.add(23);
		assertTrue(instance.exists(53));
		instance.add(42);
		assertTrue(instance.exists(42));
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testAdd_Exception() {
		instance.add(53);
		instance.add(89);
		instance.add(23);
		instance.add(22);
		instance.add(100);
		instance.add(89);
	}
	
	@Test
	public void testAdd_One() {
		bst.add("Test");
		assertEquals(1, bst.size());
		assertEquals(1, bst.depth());
	}

	@Test
	public void testRemoveFirst() {
		instance.add(53);
		instance.add(89);
		instance.add(23);
		instance.add(22);
		instance.add(100);
		assertEquals(new Integer(53), instance.removeFirst());
		assertEquals(new Integer(89), instance.removeFirst());
		assertEquals(new Integer(100), instance.removeFirst());
		assertEquals(new Integer(23), instance.removeFirst());
		assertEquals(new Integer(22), instance.removeFirst());
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testRemoveFirst_Exception() {
		instance.add(53);
		instance.add(89);
		instance.add(23);
		instance.add(22);
		instance.add(100);
		assertEquals(new Integer(53), instance.removeFirst());
		assertEquals(new Integer(89), instance.removeFirst());
		assertEquals(new Integer(100), instance.removeFirst());
		assertEquals(new Integer(23), instance.removeFirst());
		assertEquals(new Integer(22), instance.removeFirst());
		instance.removeFirst();
	}

	@Test
	public void testRemove() {
		instance.add(42);
		instance.add(31);
		instance.add(123);
		instance.add(3);
		instance.add(46);
		instance.add(98);
		instance.add(41);
		instance.add(13);
		assertEquals(new Integer(123), instance.remove(123));
		assertFalse(instance.exists(123));
		assertEquals(new Integer(98), instance.remove(98));
		assertTrue(instance.exists(46));
		assertEquals(new Integer(31), instance.remove(31));
		assertTrue(instance.exists(41));
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testRemove_Exception() {
		instance.add(42);
		instance.add(31);
		instance.add(123);
		instance.add(3);
		instance.add(46);
		instance.add(98);
		instance.add(41);
		instance.add(13);
		instance.remove(43);
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testRemove_Exception_Empty() {
		instance.remove(31);
	}
	
	@Test
	public void testGetFirstOne() {
		bst.add("Test");
		assertEquals("Test", bst.getFirst());
		assertEquals(1, bst.size());
		assertEquals(1, bst.depth());
	}

	@Test
	public void testGetFirstMultiple() {
		bst.add("Test2");
		assertEquals("Test2", bst.getFirst());
		assertEquals(1, bst.size());
		assertEquals(1, bst.depth());
		bst.add("Test3");
		bst.add("Test1");
		assertEquals("Test2", bst.getFirst());
		assertEquals("Test2", bst.getFirst());
		assertEquals(3, bst.size());
		assertEquals(2, bst.depth());
	}

	@Test
	public void testGetFirst_Basic() {
		instance.add(31);
		instance.add(10);
		instance.add(42);
		instance.add(13);
		instance.add(8);
		instance.add(3);
		instance.add(51);
		assertEquals(new Integer(31), instance.getFirst());
	}

	@Test
	public void testGetFirst_Mix() {
		instance.add(31);
		instance.add(10);
		instance.add(42);
		instance.add(13);
		instance.add(8);
		instance.add(3);
		instance.add(51);
		assertEquals(new Integer(31), instance.getFirst());
		instance.removeFirst();
		assertEquals(new Integer(42), instance.getFirst());
		instance.remove(10);
		assertEquals(new Integer(42), instance.getFirst());
		instance.removeFirst();
		assertEquals(new Integer(51), instance.getFirst());
		instance.remove(8);
		assertEquals(new Integer(51), instance.getFirst());
		instance.removeFirst();
		assertEquals(new Integer(13), instance.getFirst());
		instance.removeFirst();
		assertEquals(new Integer(3), instance.getFirst());
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testGetFirst_Exception() {
		instance.add(31);
		instance.add(10);
		instance.add(42);
		instance.add(13);
		instance.add(8);
		instance.add(3);
		instance.add(51);
		assertEquals(new Integer(31), instance.getFirst());
		instance.removeFirst();
		assertEquals(new Integer(42), instance.getFirst());
		instance.remove(10);
		assertEquals(new Integer(42), instance.getFirst());
		instance.removeFirst();
		assertEquals(new Integer(51), instance.getFirst());
		instance.remove(8);
		assertEquals(new Integer(51), instance.getFirst());
		instance.removeFirst();
		assertEquals(new Integer(13), instance.getFirst());
		instance.removeFirst();
		assertEquals(new Integer(3), instance.getFirst());
		instance.removeFirst();
		instance.getFirst();
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testGetFirst_Empty() {
		instance.getFirst();
	}

	@Test
	public void testDepth_Empty() {
		assertEquals(0, instance.depth());
	}

	@Test
	public void testDepth_Basic() {
		instance.add(31);
		instance.add(10);
		instance.add(42);
		instance.add(13);
		instance.add(8);
		instance.add(3);
		instance.add(51);
		assertEquals(4, instance.depth());
	}

	@Test
	public void testDepth_Mix() {
		instance.add(31);
		instance.add(10);
		instance.add(42);
		instance.add(13);
		instance.add(8);
		instance.add(3);
		instance.add(51);
		assertEquals(4, instance.depth());
		instance.removeFirst();
		assertEquals(4, instance.depth());
		instance.add(56);
		assertEquals(4, instance.depth());
		instance.remove(13);
		assertEquals(4, instance.depth());
		instance.removeFirst();
		instance.remove(10);
		assertEquals(3, instance.depth());
		instance.add(4);
		instance.add(7);
		assertEquals(5, instance.depth());
	}
	
	@Test
	public void testIsEmpty() {
		assertTrue(bst.isEmpty());
		bst.add("Test");
		assertFalse(bst.isEmpty());
	}

	@Test
	public void testIsEmpty_Empty() {
		assertTrue(instance.isEmpty());
	}

	@Test
	public void testIsEmpty_NotEmpty() {
		instance.add(12);
		instance.add(41);
		instance.add(43);
		instance.add(14);
		instance.add(61);
		assertFalse(instance.isEmpty());
	}

	@Test
	public void testIsEmpty_Mix() {
		instance.add(12);
		instance.add(41);
		instance.add(43);
		instance.add(14);
		instance.add(61);
		assertFalse(instance.isEmpty());
		instance.removeFirst();
		assertFalse(instance.isEmpty());
		instance.remove(61);
		instance.remove(14);
		instance.removeFirst();
		assertFalse(instance.isEmpty());
		instance.add(13);
		instance.add(12);
		assertFalse(instance.isEmpty());
		instance.removeFirst();
		instance.removeFirst();
		instance.removeFirst();
		assertTrue(instance.isEmpty());
	}

	@Test
	public void testSize_Empty() {
		assertEquals(0, instance.size());
	}

	@Test
	public void testSize_Basic() {
		instance.add(42);
		instance.add(89);
		instance.add(93);
		instance.add(14);
		instance.add(62);
		instance.add(41);
		assertEquals(6, instance.size());
	}

	@Test
	public void testSize_Mix() {
		instance.add(42);
		instance.add(89);
		instance.add(93);
		instance.add(14);
		instance.add(62);
		instance.add(41);
		assertEquals(6, instance.size());
		instance.add(52);
		instance.add(16);
		instance.add(61);
		instance.removeFirst();
		assertEquals(8, instance.size());
		instance.remove(93);
		instance.removeFirst();
		instance.removeFirst();
		assertEquals(5, instance.size());
		instance.removeFirst();
		instance.removeFirst();
		instance.removeFirst();
		instance.removeFirst();
		assertEquals(1, instance.size());
		instance.removeFirst();
		assertEquals(0, instance.size());
	}

	@Test
	public void testExists_Empty() {
		assertFalse(instance.exists(10));
	}
	
	@Test
	public void testExists() {
		bst.add("Test");
		assertTrue(bst.exists("Test"));
		assertEquals(1, bst.size());
		assertEquals(1, bst.depth());
		bst.removeFirst();
		assertFalse(bst.exists("Test"));
		assertEquals(0, bst.size());
		assertEquals(0, bst.depth());
	}
	
	@Test
	public void testExists_My() {
		instance.add(89);
		instance.add(32);
		instance.add(109);
		instance.add(90);
		instance.add(100);
		assertFalse(instance.exists(101));
		assertTrue(instance.exists(32));
	}

	@Test
	public void testExists_Basic() {
		instance.add(20);
		instance.add(200);
		instance.add(52);
		instance.add(32);
		instance.add(27);
		assertFalse(instance.exists(100));
		assertTrue(instance.exists(200));
	}

	@Test
	public void testExists_Mix() {
		instance.add(20);
		instance.add(200);
		instance.add(52);
		instance.add(32);
		instance.add(27);
		assertFalse(instance.exists(100));
		assertTrue(instance.exists(200));
		instance.removeFirst();
		assertFalse(instance.exists(20));
		instance.add(97);
		instance.add(61);
		instance.add(50);
		assertFalse(instance.exists(88));
		assertTrue(instance.exists(27));
		instance.removeFirst();
		assertFalse(instance.exists(200));
	}
}