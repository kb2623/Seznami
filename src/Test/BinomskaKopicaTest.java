package Test;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import Source.BinomskaKopica;

public class BinomskaKopicaTest {

	private BinomskaKopica<Integer> instance;

	@Before
	public void setUp() throws Exception {
		this.instance = new BinomskaKopica<Integer>();
	}

	@Test(timeout = 250)
	public void testAdd_One() {
		instance.add(23);
	}

	@Test(timeout = 250)
	public void testAdd_More() {
		instance.add(43);
		instance.add(63);
		instance.add(62);
		instance.add(32);
		instance.add(12);
		instance.add(90);
		instance.add(30);
		instance.add(16);
		instance.add(20);
		instance.add(55);
		instance.add(7);
		instance.add(1);
		instance.add(89);
	}

	@Test(timeout = 250, expected = java.util.NoSuchElementException.class)
	public void testRemoveFirst_Empty() {
		instance.removeFirst();
	}

	@Test(timeout = 250)
	public void testRemoveFirst_One() {
		instance.add(34);
		assertEquals(new Integer(34), instance.removeFirst());
	}

	@Test(timeout = 250)
	public void testRemoveFirst_MoreFirst() {
		instance.add(43);
		instance.add(63);
		instance.add(62);
		instance.add(32);
		instance.add(12);
		instance.add(90);
		instance.add(30);
		instance.add(16);
		instance.add(20);
		instance.add(55);
		instance.add(7);
		instance.add(1);
		instance.add(89);
		assertEquals(new Integer(90), instance.removeFirst());
		assertEquals(new Integer(89), instance.removeFirst());
		assertEquals(new Integer(63), instance.removeFirst());
		assertEquals(new Integer(62), instance.removeFirst());
		assertEquals(new Integer(55), instance.removeFirst());
		assertEquals(new Integer(43), instance.removeFirst());
		assertEquals(new Integer(32), instance.removeFirst());
		assertEquals(new Integer(30), instance.removeFirst());
		assertEquals(new Integer(20), instance.removeFirst());
		assertEquals(new Integer(16), instance.removeFirst());
		assertEquals(new Integer(12), instance.removeFirst());
		assertEquals(new Integer(7), instance.removeFirst());
		assertEquals(new Integer(1), instance.removeFirst());
	}

	@Test(timeout = 250)
	public void testRemoveFirst_MoreTwo() {
		for(int i = 0; i < 20; i++) {
			instance.add(i);
		}
		assertEquals(20, instance.size());
		for(int i = 19; i >= 0; i--) {
			assertEquals(new Integer(i), instance.removeFirst());
		}
	}

	@Test(timeout = 250, expected = java.util.NoSuchElementException.class)
	public void testGetFirst_Empty() {
		instance.getFirst();
	}

	@Test(timeout = 250)
	public void testGetFirst_One() {
		instance.add(100);
		assertEquals(new Integer(100), instance.getFirst());
	}

	@Test(timeout = 250)
	public void testGetFirst_More() {
		instance.add(43);
		instance.add(63);
		instance.add(62);
		instance.add(32);
		instance.add(12);
		instance.add(90);
		instance.add(30);
		instance.add(16);
		instance.add(20);
		instance.add(55);
		instance.add(7);
		instance.add(1);
		instance.add(89);
		assertEquals(new Integer(90), instance.getFirst());
	}

	@Test(timeout = 250)
	public void testSize_Empty() {
		assertEquals(0, instance.size());
	}

	@Test(timeout = 250)
	public void testSize_One() {
		instance.add(23);
		assertEquals(1, instance.size());
	}

	@Test(timeout = 250)
	public void testSize_Random() {
		int numOfEle = (int)(Math.random() * 100);
		for(int i = numOfEle; i > 0; i--) {
			instance.add(i);
		}
		assertEquals(numOfEle, instance.size());
	}

	@Test(timeout = 250)
	public void testDepth_Empty() {
		assertEquals(0, instance.depth());
	}

	@Test(timeout = 250)
	public void testDepth_One() {
		instance.add(42);
		assertEquals(0, instance.depth());
	}

	@Test(timeout = 250)
	public void testDepth_Two() {
		instance.add(32);
		instance.add(43);
		assertEquals(1, instance.depth());
	}

	@Test(timeout = 250)
	public void testDepth_More() {
		for(int i = 0; i < 20; i++) {
			instance.add(i);
		}
		assertEquals(4, instance.depth());
	}

	@Test
	public void testIsEmpty_True() {
		assertTrue(instance.isEmpty());
	}

	@Test(timeout = 250)
	public void testIsEmpty_False() {
		assertTrue(instance.isEmpty());
		instance.add(42);
		assertFalse(instance.isEmpty());
	}

	@Test(timeout = 250)
	public void testExists_Empty() {
		assertFalse(instance.exists(12));
	}

	@Test(timeout = 250)
	public void testExists_One() {
		instance.add(32);
		assertFalse(instance.exists(23));
		assertTrue(instance.exists(32));
	}

	@Test(timeout = 250)
	public void testExists_More() {
		instance.add(43);
		instance.add(63);
		instance.add(62);
		instance.add(32);
		instance.add(12);
		instance.add(90);
		instance.add(30);
		instance.add(16);
		instance.add(20);
		instance.add(55);
		instance.add(7);
		instance.add(1);
		instance.add(89);
		assertFalse(instance.exists(100));
		assertTrue(instance.exists(12));
		assertTrue(instance.exists(90));
		assertTrue(instance.exists(1));
		assertFalse(instance.exists(56));
		assertTrue(instance.exists(43));
		assertTrue(instance.exists(32));
		assertTrue(instance.exists(20));
		assertTrue(instance.exists(63));
		assertTrue(instance.exists(89));
		assertTrue(instance.exists(30));
		assertTrue(instance.exists(16));
		assertTrue(instance.exists(55));
		assertTrue(instance.exists(62));
		assertTrue(instance.exists(7));
	}

	@Test(timeout = 250, expected = java.util.NoSuchElementException.class)
	public void testRemove_Empty() {
		assertTrue(instance.isEmpty());
		instance.remove(89);
	}

	@Test(timeout = 250, expected = java.util.NoSuchElementException.class)
	public void testRemove_OneBad() {
		instance.add(43);
		instance.remove(34);
	}

	@Test(timeout = 250)
	public void testRemove_OneGood() {
		instance.add(34);
		assertEquals(new Integer(34), instance.remove(34));
	}

	@Test(timeout = 250)
	public void testRemove_MoreOne() {
		instance.add(43);
		instance.add(63);
		instance.add(62);
		instance.add(32);
		instance.add(12);
		instance.add(90);
		instance.add(30);
		instance.add(16);
		instance.add(20);
		instance.add(55);
		instance.add(7);
		instance.add(1);
		instance.add(89);
		assertEquals(new Integer(55), instance.remove(55));
		assertEquals(new Integer(32), instance.remove(32));
		assertEquals(new Integer(7), instance.remove(7));
		assertEquals(new Integer(89), instance.remove(89));
		assertEquals(new Integer(43), instance.remove(43));
		assertEquals(new Integer(62), instance.remove(62));
		assertEquals(new Integer(30), instance.remove(30));
		assertEquals(new Integer(1), instance.remove(1));
		assertEquals(new Integer(90), instance.remove(90));
		assertEquals(new Integer(12), instance.remove(12));
		assertEquals(new Integer(63), instance.remove(63));
		assertEquals(new Integer(16), instance.remove(16));
		assertEquals(new Integer(20), instance.remove(20));
	}

	@Test(timeout = 250, expected = java.util.NoSuchElementException.class)
	public void testRemove_MoreTwo() {
		instance.add(43);
		instance.add(87);
		instance.add(12);
		instance.add(77);
		instance.add(99);
		instance.add(3);
		instance.add(27);
		instance.add(59);
		instance.add(60);
		instance.add(68);
		instance.add(34);
		assertEquals(new Integer(12), instance.remove(12));
		assertEquals(new Integer(59), instance.remove(59));
		assertEquals(new Integer(34), instance.remove(34));
		instance.remove(100);
	}
	
	@Test
	public void testAsList_Empty() {
		assertEquals(null, instance.asList());
	}
	
	@Test
	public void testAsList_One() {
		instance.add(23);
		assertEquals(new Integer(23), instance.asList().get(0));
	}
	
	@Test
	public void testAsList_Two() {
		StringBuilder builder = new StringBuilder();
		List<Integer> list;
		instance.add(23);
		instance.add(55);
		list = instance.asList();
		for (Integer integer : list) {
			builder.append(integer).append(' ');
		}
		assertEquals("23 55 ", builder.toString());
	}

	@Test
	public void testAsList_Multi_One() {
		StringBuilder sb = new StringBuilder();
		instance.add(34);
		instance.add(23);
		instance.add(21);
		instance.add(55);
		instance.add(16);
		instance.add(7);
		List<Integer> list = instance.asList();
		for (Integer integer : list) {
			sb.append(integer).append(' ');
		}
		assertEquals("7 16 23 34 21 55 ", sb.toString());
	}
	
	@Test
	public void testAsList_Multi_Two() {
		StringBuilder builder = new StringBuilder();
		instance.add(9);
		instance.add(5);
		instance.add(17);
		instance.add(21);
		instance.add(99);
		instance.add(12);
		instance.add(23);
		instance.add(12);
		List<Integer> list = instance.asList();
		for (Integer integer : list) {
			builder.append(integer).append(' ');
		}
		assertEquals("5 9 17 21 12 23 12 99 ", builder.toString());
	}
}