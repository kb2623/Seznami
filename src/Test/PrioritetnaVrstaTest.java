package Test;

import Source.PrioritetnaVrsta;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PrioritetnaVrstaTest {

	private PrioritetnaVrsta<String> pv;
	private PrioritetnaVrsta<Integer> instance;

	@Before
	public void setUp() {
		this.pv = new PrioritetnaVrsta<String>(10);
		this.instance = new PrioritetnaVrsta<Integer>();
	}
	
	@Test
	public void testPrioritetnaVrsta_One() {
	StringBuilder buff = new StringBuilder();
		for(int i = 0; i < 20; i++) {
			instance.add(i);
		}
		for(int i = 0; i < 20; i++) {
			buff.append(instance.removeFirst()).append(' ');
		}
		assertEquals("19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1 0 ", buff.toString());
	}

	@Test
	public void testPrioritetnaVrsta_Two() {
		StringBuilder buff = new StringBuilder();
		assertTrue(instance.isEmpty());
		instance.add(73);
		instance.add(6);
		instance.add(57);
		assertFalse(instance.isEmpty());
		instance.add(88);
		instance.add(60);
		assertTrue(instance.exists(57));
		instance.add(34);
		instance.add(83);
		instance.add(72);
		instance.add(48);
		instance.add(85);
		assertEquals(4, instance.depth());
		assertEquals(10, instance.size());
		assertEquals(new Integer(88), instance.getFirst());
		buff.append(instance.removeFirst()).append(' ');
		assertFalse(instance.exists(12));
		assertFalse(instance.exists(88));
		assertEquals(new Integer(85), instance.getFirst());
		buff.append(instance.removeFirst()).append(' ');
		assertEquals(new Integer(83), instance.getFirst());
		buff.append(instance.removeFirst()).append(' ');
		assertTrue(instance.exists(48));
		assertEquals(3, instance.depth());
		assertEquals(7, instance.size());
		for(int i = 0; i < 7; i++) {
			buff.append(instance.removeFirst()).append(' ');
		}
		assertEquals("88 85 83 73 72 60 57 48 34 6 ", buff.toString());
		assertFalse(instance.exists(6));
		assertTrue(instance.isEmpty());
	}

	@Test
	public void testPrioritetnaVrsta_Three() {
		StringBuilder buff = new StringBuilder();
		assertTrue(instance.isEmpty());
		assertFalse(instance.exists(8));
		instance.add(43);
		instance.add(32);
		instance.add(89);
		instance.add(100);
		assertTrue(instance.exists(32));
		instance.add(19);
		instance.add(84);
		instance.add(5);
		assertEquals(3, instance.depth());
		assertFalse(instance.isEmpty());
		instance.add(27);
		instance.add(14);
		instance.add(73);
		assertEquals(10, instance.size());
		assertEquals(4, instance.depth());
		assertEquals(new Integer(100), instance.getFirst());
		buff.append(instance.removeFirst()).append(' ');
		assertEquals(new Integer(32), instance.remove(32));
		assertEquals(8, instance.size());
		assertEquals(4, instance.depth());
		assertEquals(new Integer(43), instance.remove(43));
		assertEquals(7, instance.size());
		assertEquals(3, instance.depth());
		for(int i = 0; i < 7; i++) {
			buff.append(instance.removeFirst()).append(' ');
		}
		assertEquals("100 89 84 73 27 19 14 5 ", buff.toString());
		assertEquals(0, instance.depth());
		assertEquals(0, instance.size());
		assertTrue(instance.isEmpty());
	}
	
	@Test
	public void testAdd_One() {
		instance.add(12);
	}

	@Test
	public void testAdd_Multiple() {
		pv.add("Test1");
		pv.add("Test2");
	}

	@Test
	public void testAdd_Overflow() {
		pv = new PrioritetnaVrsta<String>(2);
		pv.add("Test1");
		pv.add("Test2");
		pv.add("Test3");
	}

	@Test
	public void testAsList_Empty() {
		assertTrue(instance.isEmpty());
		assertEquals(null, instance.asList());
	}

	@Test
	public void testAsList_One() {
		StringBuilder buff = new StringBuilder();
		List<Integer> list;
		instance.add(32);
		list = instance.asList();
		for(Integer i : list) {
			buff.append(i).append(' ');
		}
		assertEquals("32 ", buff.toString());
	}

	@Test
	public void testAsList_More() {
		StringBuilder sb = new StringBuilder();
		List<Integer> list;
		for(int i = 0; i < 20; i++) {
			instance.add(i);
		}
		list = instance.asList();
		for(Integer i : list) {
			sb.append(i).append(' ');
		}
		assertEquals("19 18 13 16 17 10 12 9 15 8 7 1 5 4 11 0 6 3 14 2 ", sb.toString());
	}
	
	@Test(expected = java.util.NoSuchElementException.class)
	public void testRemoveFirst() {
		instance.add(32);
		instance.add(23);
		instance.add(15);
		instance.add(63);
		instance.add(86);
		instance.add(9);
		instance.add(3);
		instance.add(99);
		assertEquals(new Integer(99), instance.removeFirst());
		assertEquals(new Integer(86), instance.removeFirst());
		assertEquals(new Integer(63), instance.removeFirst());
		assertEquals(new Integer(32), instance.removeFirst());
		assertEquals(new Integer(23), instance.removeFirst());
		assertEquals(new Integer(15), instance.removeFirst());
		assertEquals(new Integer(9), instance.removeFirst());
		assertEquals(new Integer(3), instance.removeFirst());
		instance.removeFirst();
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testRemoveFirst_Empty() {
		instance.removeFirst();
	}
	
	@Test
	public void testRemoveFirst_One() {
		pv.add("Test");
		assertEquals("Test", pv.removeFirst());
	}

	@Test
	public void testRemoveFirst_Multiple() {
		pv.add("Test1");
		pv.add("Test5");
		pv.add("Test2");
		pv.add("Test4");
		pv.add("Test3");
		assertEquals("Test5", pv.removeFirst());
		assertEquals("Test4", pv.removeFirst());
		assertEquals("Test3", pv.removeFirst());
		assertEquals("Test2", pv.removeFirst());
		assertEquals("Test1", pv.removeFirst());
	}
	
	@Test(expected = java.util.NoSuchElementException.class)
	public void testGetFirst() {
		instance.add(41);
		instance.add(13);
		instance.add(89);
		instance.add(32);
		instance.add(18);
		instance.add(9);
		instance.add(100);
		instance.add(48);
		assertEquals(new Integer(100), instance.getFirst());
		instance.removeFirst();
		assertEquals(new Integer(89), instance.getFirst());
		instance.removeFirst();
		assertEquals(new Integer(48), instance.getFirst());
		instance.removeFirst();
		assertEquals(new Integer(41), instance.getFirst());
		instance.removeFirst();
		assertEquals(new Integer(32), instance.getFirst());
		instance.removeFirst();
		assertEquals(new Integer(18), instance.getFirst());
		instance.removeFirst();
		assertEquals(new Integer(13), instance.getFirst());
		instance.removeFirst();
		assertEquals(new Integer(9), instance.getFirst());
		instance.removeFirst();
		instance.getFirst();
	}
	
	@Test(expected = java.util.NoSuchElementException.class)
	public void testGetFirst_Empty() {
		instance.getFirst();
	}
	
	@Test
	public void testGetFirst_One() {
		pv.add("Test");
		assertEquals("Test", pv.getFirst());
	}

	@Test
	public void testGetFirst_Multiple() {
		pv.add("Test1");
		assertEquals("Test1", pv.getFirst());
		pv.add("Test3");
		pv.add("Test2");
		assertEquals("Test3", pv.getFirst());
		assertEquals("Test3", pv.getFirst());
	}
	
	@Test
	public void testSize() {
		int size = (int)(Math.random() * 100) + 1;
		for(int i = 0; i < size; i++) {
			instance.add((int)(Math.random() * 1000) + 1);
		}
		assertEquals(size, instance.size());
	}

	@Test
	public void testSize_Empty() {
		assertEquals(0, instance.size());
	}
	
	@Test
	public void testSize_One() {
		pv.add("Test");
		assertEquals(1, pv.size());
	}
	
	@Test
	public void testSize_Multiple() {
		assertEquals(0, pv.size());
		pv.add("Test");
		assertEquals(1, pv.size());
		pv.add("Test1");
		assertEquals(2, pv.size());
		pv.add("Test2");
		assertEquals(3, pv.size());
	}
	
	@Test
	public void testDepth() {
		int size = (int)(Math.random() * 7) + 1;
		for(int i = 0; i < size; i++) {
			instance.add((int)(Math.random() * 1000) + 1);
		}
		assertEquals((int)(Math.log(size) / Math.log(2)) + 1, instance.depth());
	}

	@Test
	public void testDepth_Empty() {
		assertEquals(0, instance.depth());
	}
	
	@Test
	public void testDepth_One() {
		pv.add("Test1");
		assertEquals(1, pv.depth());
	}

	@Test
	public void testDepth_Multiple() {
		pv.add("Test1");
		assertEquals(1, pv.depth());
		pv.add("Test5");
		assertEquals(2, pv.depth());
		pv.add("Test2");
		assertEquals(2, pv.depth());
		pv.add("Test4");
		assertEquals(3, pv.depth());
		pv.add("Test3");
		assertEquals(3, pv.depth());
		pv.add("Test6");
		assertEquals(3, pv.depth());
		pv.add("Test8");
		assertEquals(3, pv.depth());
		pv.add("Test7");
		assertEquals(4, pv.depth());
	}
	
	@Test
	public void testIsEmpty() {
		assertTrue(instance.isEmpty());
		instance.add(32);
		instance.add(89);
		instance.add(43);
		assertFalse(instance.isEmpty());
		instance.removeFirst();
		assertFalse(instance.isEmpty());
		instance.removeFirst();
		instance.removeFirst();
		assertTrue(instance.isEmpty());
	}
	
	@Test
	public void testIsEmpty_Empty() {
		assertTrue(instance.isEmpty());
	}
	
	@Test
	public void testIsEmpty_One() {
		pv.add("Test");
		assertFalse(pv.isEmpty());
	}

	@Test
	public void testIsEmpty_Multiple() {
		pv.add("Test");
		pv.add("Test1");
		pv.add("Test2");
		assertFalse(pv.isEmpty());
	}
	
	@Test
	public void testExists() {
		instance.add(43);
		instance.add(32);
		instance.add(76);
		instance.add(52);
		instance.add(42);
		instance.add(8);
		instance.add(91);
		instance.add(64);
		assertTrue(instance.exists(8));
		assertTrue(instance.exists(64));
		assertFalse(instance.exists(100));
		assertTrue(instance.exists(52));
		assertFalse(instance.exists(9));
	}

	@Test
	public void testExists_Empty() {
		assertFalse(instance.exists(12));
	}

	@Test
	public void testExists_One() {
		instance.add(123);
		assertTrue(instance.exists(123));
		assertFalse(instance.exists(45));
	}

	@Test
	public void testExists_BadInput() {
		instance.add(32);
		instance.add(23);
		instance.add(89);
		instance.add(40);
		assertFalse(instance.exists(11));
		assertFalse(instance.exists(100));
		assertFalse(instance.exists(36));
	}

	@Test
	public void testExists_GoodInput() {
		instance.add(13);
		instance.add(42);
		instance.add(56);
		instance.add(43);
		instance.add(52);
		assertTrue(instance.exists(13));
		assertTrue(instance.exists(43));
		assertTrue(instance.exists(56));
		assertTrue(instance.exists(52));
		assertTrue(instance.exists(42));
	}
	
	@Test(expected = java.util.NoSuchElementException.class)
	public void testRemove() {
		instance.add(34);
		instance.add(18);
		assertEquals(new Integer(18), instance.remove(18));
		instance.add(100);
		instance.add(36);
		instance.add(78);
		assertEquals(new Integer(36), instance.remove(36));
		instance.add(90);
		assertEquals(new Integer(100), instance.remove(100));
		instance.add(10);
		instance.add(18);
		assertEquals(new Integer(78), instance.remove(78));
		assertEquals(new Integer(10), instance.remove(10));
		assertEquals(new Integer(18), instance.remove(18));
		assertEquals(new Integer(90), instance.remove(90));
		assertEquals(new Integer(34), instance.remove(34));
		instance.remove(10);
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testRemove_Empty() {
		instance.remove(21);
	}

	@Test
	public void testRemove_One() {
		instance.add(32);
		instance.remove(32);
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testRemove_BadInput_One() {
		instance.add(23);
		instance.remove(33);
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testRemove_BadInput_Two() {
		instance.add(23);
		instance.add(73);
		instance.add(10);
		instance.add(53);
		instance.remove(6);
	}

	@Test
	public void testRemove_GoodInput() {
		instance.add(89);
		instance.add(43);
		instance.add(432);
		instance.add(42);
		instance.add(14);
		instance.add(8);
		assertEquals(new Integer(8), instance.remove(8));
		assertEquals(new Integer(42), instance.remove(42));
		assertEquals(new Integer(432), instance.remove(432));
		assertEquals(new Integer(43), instance.remove(43));
		assertEquals(new Integer(14), instance.remove(14));
		assertEquals(new Integer(89), instance.remove(89));
	}
}