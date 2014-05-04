package Nodes;

public class BstNode<T> {
	public T value;
	public BstNode<T> left;
	public BstNode<T> right;

	public BstNode(T e) {
		this(e, null, null);
	}

	public BstNode(T e, BstNode<T> left, BstNode<T> right) {
		this.value = e;
		this.left = left;
		this.right = right;
	}
}
