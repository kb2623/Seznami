import DataStruct.Bst;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import Comparators.*;

public class BstTest {

	private Bst<String> bst;
	private Bst<Integer> instance;

	private void setUpComparable() {
		this.bst = new Bst<>();
		this.instance = new Bst<>();
	}
	
	private void setUpComparator() {
		this.bst = new Bst<>(new CompareString());
		this.instance = new Bst<>(new CompareInteger());
	}
	
	@Test
	public void testBst() {
		//Urejenost z uporabo >> Comparable
		setUpComparable(); testBst_One();
		setUpComparable(); testBst_Two();
		try {
			setUpComparable(); testBst_Three(); assert false;
		} catch(java.util.NoSuchElementException e) {
			assert true;
		} catch(Exception e) {
			assert false;
		}
		//Urejenost z uporabo >> Comparator
		setUpComparator(); testBst_One();
		setUpComparator(); testBst_Two();
		try {
			setUpComparator(); testBst_Three(); assert false;
		} catch(java.util.NoSuchElementException e) {
			assert true;
		} catch(Exception e) {
			assert false;
		}
	}
	
	private void testBst_One() {
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

	private void testBst_Two() {
		instance.add(89);
		instance.add(32);
		instance.add(109);
		instance.add(90);
		instance.add(100);
		assertFalse(instance.exists(101));
		assertTrue(instance.exists(32));
	}
	
	private void testBst_Three() {
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
	public void testAsList() {
		//Urejenost z uporabo >> Comparable
		setUpComparable(); testAsList_Empty();
		setUpComparable(); testAsList_One();
		setUpComparable(); testAsList_MoreString_One();
		setUpComparable(); testAsList_MoreStrings_Two();
		//Urejenost z uporabo >> Comparator
		setUpComparator(); testAsList_Empty();
		setUpComparator(); testAsList_One();
		setUpComparator(); testAsList_MoreString_One();
		setUpComparator(); testAsList_MoreStrings_Two();
	}

	private void testAsList_Empty() {
		assertTrue(instance.isEmpty());
		assertEquals(null, instance.asList());
	}

	private void testAsList_One() {
		StringBuilder testString = new StringBuilder();
		List<Integer> testList;
		instance.add(32);
		testList = instance.asList();
		for(Integer i : testList) {
			testString.append(i).append(' ');
		}
		assertEquals("32 ", testString.toString());
	}

	private void testAsList_MoreString_One() {
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
	
	private void testAsList_MoreStrings_Two() {
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
	
	@Test
	public void testAdd() {
		//Urejenost z uporabo >> Comparable
		setUpComparable(); testAdd_One();
		setUpComparable(); testAdd_Basic();
		try{
			setUpComparable(); testAdd_Exception(); assert false;
		} catch(java.lang.IllegalArgumentException e) {
			assert true;
		} catch(Exception e) {
			assert false;
		}
		//Urejenost z uporabo >> Comparator
		setUpComparator(); testAdd_One();
		setUpComparator(); testAdd_Basic();
		try{
			setUpComparator(); testAdd_Exception(); assert false;
		} catch(java.lang.IllegalArgumentException e) {
			assert true;
		} catch(Exception e) {
			assert false;
		}
	}

	private void testAdd_Basic() {
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

	private void testAdd_Exception() {
		instance.add(53);
		instance.add(89);
		instance.add(23);
		instance.add(22);
		instance.add(100);
		instance.add(89);
	}
	
	private void testAdd_One() {
		bst.add("Test");
		assertEquals(1, bst.size());
		assertEquals(1, bst.depth());
	}
	
	@Test
	public void testRemoveFirst() {
		//Urejenost z uporabo >> Comparable
		setUpComparable(); testRemoveFirst_Basic();
		try {
			setUpComparable(); testRemoveFirst_Exception(); assert false;
		} catch(java.util.NoSuchElementException e) {
			assert true;
		} catch(Exception e) {
			assert false;
		}
		//Urejenost z uporabo >> Comparator
		setUpComparator(); testRemoveFirst_Basic();
		try {
			setUpComparator(); testRemoveFirst_Exception(); assert false;
		} catch(java.util.NoSuchElementException e) {
			assert true;
		} catch(Exception e) {
			assert false;
		}
	}

	private void testRemoveFirst_Basic() {
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

	private void testRemoveFirst_Exception() {
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
		//Urejenost z uporabo >> Comparable
		setUpComparable(); testRemove_Basic();
		try {
			setUpComparable(); testRemove_Exception(); assert false;
		} catch(java.util.NoSuchElementException e) {
			assert true;
		} catch(Exception e) {
			assert false;
		}
		try {
			setUpComparable(); testRemove_Exception_Empty(); assert false;
		} catch(java.util.NoSuchElementException e) {
			assert true;
		} catch(Exception e) {
			assert false;
		}
		//Urejenost z uporabo >> Comparator
		setUpComparator(); testRemove_Basic();
		try {
			setUpComparator(); testRemove_Exception(); assert false;
		} catch(java.util.NoSuchElementException e) {
			assert true;
		} catch(Exception e) {
			assert false;
		}
		try {
			setUpComparator(); testRemove_Exception_Empty(); assert false;
		} catch(java.util.NoSuchElementException e) {
			assert true;
		} catch(Exception e) {
			assert false;
		}
	}

	private void testRemove_Basic() {
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

	private void testRemove_Exception() {
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

	private void testRemove_Exception_Empty() {
		instance.remove(31);
	}
	
	@Test
	public void testGetFirst() {
		//Urejenost z uporabo >> Comparable
		setUpComparable(); testGetFirst_One();
		setUpComparable(); testGetFirst_Multiple();
		setUpComparable(); testGetFirst_Basic();
		setUpComparable(); testGetFirst_Mix();
		try {
			setUpComparable(); testGetFirst_Exception(); assert false;
		} catch(java.util.NoSuchElementException e) {
			assert true;
		} catch(Exception e) {
			assert false;
		}
		try {
			setUpComparable(); testGetFirst_Empty(); assert false;
		} catch(java.util.NoSuchElementException e) {
			assert true;
		} catch(Exception e) {
			assert false;
		}
		//Urejenost z uporabo >> Comparator
		setUpComparator(); testGetFirst_One();
		setUpComparator(); testGetFirst_Multiple();
		setUpComparator(); testGetFirst_Basic();
		setUpComparator(); testGetFirst_Mix();
		try {
			setUpComparator(); testGetFirst_Exception(); assert false;
		} catch(java.util.NoSuchElementException e) {
			assert true;
		} catch(Exception e) {
			assert false;
		}
		try {
			setUpComparator(); testGetFirst_Empty(); assert false;
		} catch(java.util.NoSuchElementException e) {
			assert true;
		} catch(Exception e) {
			assert false;
		}
	}
	
	private void testGetFirst_One() {
		bst.add("Test");
		assertEquals("Test", bst.getFirst());
		assertEquals(1, bst.size());
		assertEquals(1, bst.depth());
	}

	private void testGetFirst_Multiple() {
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

	private void testGetFirst_Basic() {
		instance.add(31);
		instance.add(10);
		instance.add(42);
		instance.add(13);
		instance.add(8);
		instance.add(3);
		instance.add(51);
		assertEquals(new Integer(31), instance.getFirst());
	}

	private void testGetFirst_Mix() {
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

	private void testGetFirst_Exception() {
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

	private void testGetFirst_Empty() {
		instance.getFirst();
	}
	
	@Test
	public void testDepth() {
		//Urejenost z uporabo >> Comparable
		setUpComparable(); testDepth_Empty();
		setUpComparable(); testDepth_Basic();
		setUpComparable(); testDepth_Mix();
		//Urejenost z uporabo >> Comparator
		setUpComparator(); testDepth_Empty();
		setUpComparator(); testDepth_Basic();
		setUpComparator(); testDepth_Mix();
	}

	private void testDepth_Empty() {
		assertEquals(0, instance.depth());
	}

	private void testDepth_Basic() {
		instance.add(31);
		instance.add(10);
		instance.add(42);
		instance.add(13);
		instance.add(8);
		instance.add(3);
		instance.add(51);
		assertEquals(4, instance.depth());
	}

	private void testDepth_Mix() {
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
		//Urejenost z uporabo >> Comparable
		setUpComparable(); testIsEmpty_One();
		setUpComparable(); testIsEmpty_Empty();
		setUpComparable(); testIsEmpty_NotEmpty();
		setUpComparable(); testIsEmpty_Mix();
		//Urejenost z uporabo >> Comparator
		setUpComparator(); testIsEmpty_One();
		setUpComparator(); testIsEmpty_Empty();
		setUpComparator(); testIsEmpty_NotEmpty();
		setUpComparator(); testIsEmpty_Mix();
	 }
	
	private void testIsEmpty_One() {
		assertTrue(bst.isEmpty());
		bst.add("Test");
		assertFalse(bst.isEmpty());
	}

	private void testIsEmpty_Empty() {
		assertTrue(instance.isEmpty());
	}

	private void testIsEmpty_NotEmpty() {
		instance.add(12);
		instance.add(41);
		instance.add(43);
		instance.add(14);
		instance.add(61);
		assertFalse(instance.isEmpty());
	}

	private void testIsEmpty_Mix() {
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
	public void testSize() {
		//Urejenost z uporabo >> Comparable
		setUpComparable(); testSize_Empty();
		setUpComparable(); testSize_Basic();
		setUpComparable(); testSize_Mix();
		//Urejenost z uporabo >> Comparator
		setUpComparator(); testSize_Empty();
		setUpComparator(); testSize_Basic();
		setUpComparator(); testSize_Mix();
	}

	private void testSize_Empty() {
		assertEquals(0, instance.size());
	}

	private void testSize_Basic() {
		instance.add(42);
		instance.add(89);
		instance.add(93);
		instance.add(14);
		instance.add(62);
		instance.add(41);
		assertEquals(6, instance.size());
	}

	private void testSize_Mix() {
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
	public void testExists() {
		//Urejenost z uporabo >> Comparable
		setUpComparable(); testExists_Basic();
		setUpComparable(); testExists_Empty();
		setUpComparable(); testExists_Mix();
		setUpComparable(); testExists_My();
		setUpComparable(); testExists_One();
		//Urejenost z uporabo >> Comparator
		setUpComparator(); testExists_Basic();
		setUpComparator(); testExists_Empty();
		setUpComparator(); testExists_Mix();
		setUpComparator(); testExists_My();
		setUpComparator(); testExists_One();
	}

	private void testExists_Empty() {
		assertFalse(instance.exists(10));
	}
	
	private void testExists_One() {
		bst.add("Test");
		assertTrue(bst.exists("Test"));
		assertEquals(1, bst.size());
		assertEquals(1, bst.depth());
		bst.removeFirst();
		assertFalse(bst.exists("Test"));
		assertEquals(0, bst.size());
		assertEquals(0, bst.depth());
	}
	
	private void testExists_My() {
		instance.add(89);
		instance.add(32);
		instance.add(109);
		instance.add(90);
		instance.add(100);
		assertFalse(instance.exists(101));
		assertTrue(instance.exists(32));
	}

	private void testExists_Basic() {
		instance.add(20);
		instance.add(200);
		instance.add(52);
		instance.add(32);
		instance.add(27);
		assertFalse(instance.exists(100));
		assertTrue(instance.exists(200));
	}

	private void testExists_Mix() {
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