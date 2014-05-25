package Test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Source.Stack;

import Nodes.StackNode;

public class StackTest {
	
	private Stack<String> instance;

	@Before
	public void setUp() throws Exception {
		this.instance = new Stack<>();
	}

	@Test
	public void testPush() {
		instance.push("a");
	}
	
	@Test
	public void testPop_One() {
		instance.push("a");
		assertEquals("a", instance.pop());
	}
	
	@Test
	public void testPop_Two() {
		instance.push("b");
		instance.push("a");
		assertEquals("a", instance.pop());
		assertEquals("b", instance.pop());
	}
	
	@Test(expected = java.util.NoSuchElementException.class)
	public void testPop_Empty() {
		instance.pop();
	}
	
	@Test
	public void testIsEmpty_OnEmpty() {
		assertTrue(instance.isEmpty());
	}

	@Test
	public void testIsEmpty_OnFull() {
		instance.push("A");
		assertFalse(instance.isEmpty());
	}
	
	@Test
	public void testPeek_One() {
		instance.push("a");
		assertEquals("a", instance.peek());
	}
	
	@Test
	public void testPeek_Two() {
		instance.push("b");
		instance.push("a");
		assertEquals("a", instance.peek());
	}
	
	@Test(expected = java.util.NoSuchElementException.class)
	public void testPeek_Empty() {
		instance.peek();
	}
	
	@Test
	public void testPushPeekPop_Zero() {
		instance.push("b");
		instance.push("a");
		assertEquals("a", instance.peek());
		assertEquals("a", instance.pop());
		assertEquals("b", instance.peek());
		assertEquals("b", instance.pop());
	}
	
	@Test(expected = java.util.NoSuchElementException.class)
	public void testPushPeekPop_One() {
		instance.push("b");
		instance.push("a");
		assertEquals("a", instance.peek());
		assertEquals("a", instance.pop());
		assertEquals("b", instance.pop());
		instance.peek();
	}
	
	@Test
	public void testCount_Zero() {
		assertEquals(0, instance.count());
	}
	
	@Test
	public void testCount_One() {
		instance.push("a");
		assertEquals(1, instance.count());
	}
	
	@Test
	public void testCount_Two() {
		instance.push("test");
		instance.push("b");
		assertEquals(2, instance.count());
	}
	
	@Test
	public void testCount_Random() {
		int numOfEle = (int)(Math.random()*100);
		for(int i = numOfEle; i > 0; i--) {
			instance.push("a");
		}
		assertEquals(numOfEle, instance.count());
	}
	
	@Test
	public void testGetTopNode_Empty() {
		assertEquals(null, instance.getTopNode());
	}
	
	@Test
	public void testGetTopNode_One() {
		instance.push("Hello");
		assertEquals("Hello", instance.getTopNode().data);
	}
	
	@Test
	public void testGetTopNode() {
		instance.push("Hello");
		instance.push("world");
		instance.push("from");
		instance.push("Java");
		instance.push("!");
		StackNode<String> tmp = instance.getTopNode();
		assertEquals("!", tmp.data);
		instance.pop();
		tmp = instance.getTopNode();
		assertEquals("Java", tmp.data);
		instance.pop();
		tmp = instance.getTopNode();
		assertEquals("from", tmp.data);
		instance.pop();
		tmp = instance.getTopNode();
		assertEquals("world", tmp.data);
		instance.pop();
		tmp = instance.getTopNode();
		assertEquals("Hello", tmp.data);
	}
}
