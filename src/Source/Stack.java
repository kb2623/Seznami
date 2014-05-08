package Source;

import java.util.NoSuchElementException;
import Nodes.StackNode;

public class Stack<T> {

	private StackNode<T> top;

	public Stack() {
		this.top = null;
	}

	public StackNode<T> getTopNode() {
		return this.top;
	}

	public void push(T e) {
		this.top = new StackNode<>(e, this.top);
	}

	public T pop() {
		if(this.isEmpty()) {
			throw new NoSuchElementException();
		}
		T ret = this.top.data;
		this.top = this.top.prev;
		return ret;
	}

	public T peek() {
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
		for(StackNode<T> tmp = this.top; tmp != null; tmp = tmp.prev) {
			count++;
		}
		return count;
	}
}
