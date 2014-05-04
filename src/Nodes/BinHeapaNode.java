package Nodes;

public class BinHeapaNode<T> {	
	public T data;
	public BinHeapaNode<T> parent;
	public BinHeapaNode<T> sibling;
	public BinHeapaNode<T> child;
	public int depth;

	public BinHeapaNode(T data, BinHeapaNode<T> parent, BinHeapaNode<T> sibling, BinHeapaNode<T> child, int depth) {
		this.data = data;
		this.parent = parent;
		this.sibling = sibling;
		this.child = child;
		this.depth = depth;
	}
}