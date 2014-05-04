package Nodes;

public class StackNode<T> {	
	public T data;
	public StackNode<T> prev;

	public StackNode(T data, StackNode<T> prev) {
		this.data = data;
		this.prev = prev;
	}
}
