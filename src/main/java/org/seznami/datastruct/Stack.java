package org.seznami.datastruct;

import java.util.NoSuchElementException;
import Nodes.StackNode;

public class Stack<Element> {

	public class StackNode<T> {	
		public T data;
		public StackNode<T> prev;
		public StackNode(T data, StackNode<T> prev) {
			this.data = data;
			this.prev = prev;
		}
	}


	private StackNode<Element> top;

	public Stack() {
		this.top = null;
	}

	public StackNode<Element> getTopNode() {
		return this.top;
	}

	public void push(Element e) {
		this.top = new StackNode<>(e, this.top);
	}

	public Element pop() {
		if(this.isEmpty()) {
			throw new NoSuchElementException();
		}
		Element ret = this.top.data;
		this.top = this.top.prev;
		return ret;
	}

	public Element peek() {
		if(this.isEmpty()) {
			throw new NoSuchElementException();
		}
		return this.top.data;
	}
	
	public boolean isEmpty() {
		return (this.top == null);
	}

	public int count() {
		int count = 0;
		for(StackNode<Element> tmp = this.top; tmp != null; tmp = tmp.prev) {
			count++;
		}
		return count;
	}
}
