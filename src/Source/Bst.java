package Source;

import java.util.ArrayList;
import java.util.List;
import Nodes.BstNode;

public class Bst<T extends Comparable<T>> implements Seznam<T> {
	private BstNode<T> rootNode;
	private T minNodeValue;

	public Bst() {
		this.rootNode = null;
	}

	private boolean member(T e) {
		return this.member(e, this.rootNode);
	}


	private boolean member(T e, BstNode<T> node) {
		if (node == null) {
			return false;
		} else if (e.compareTo(node.value) == 0) {
			return true;
		} else if (e.compareTo(node.value) < 0) {
			return this.member(e, node.left);
		} else {
			return this.member(e, node.right);
		}
	}

	private void insert(T e) {
		this.rootNode = this.insertLeaf(e, this.rootNode);
	}

	private void delete(T e) {
		this.rootNode = this.delete(e, this.rootNode);
	}

	private BstNode<T> insertLeaf(T e, BstNode<T> node) {
		if (node == null) {
			node = new BstNode<T>(e);
		} else if (e.compareTo(node.value) < 0) {
			node.left = this.insertLeaf(e, node.left);
		} else if (e.compareTo(node.value) > 0) {
			node.right = this.insertLeaf(e, node.right);
		} else {
			throw new java.lang.IllegalArgumentException();
		}
		return node;
	}

	private BstNode<T> delete(T e, BstNode<T> node) {
		if (node != null) {
			if (e.compareTo(node.value) == 0) {
				if (node.left == null) {
					node = node.right;
				} else if (node.right == null) {
					node = node.left;
				} else {
					node.right = this.deleteMin(node.right);
					node.value = this.minNodeValue;
				}
			} else if (e.compareTo(node.value) < 0) {
				node.left = this.delete(e, node.left);
			} else {
				node.right = this.delete(e, node.right);
			}
		}
		return node;
	}

	private BstNode<T> deleteMin(BstNode<T> node) {
		if (node.left != null) {
			node.left = this.deleteMin(node.left);
			return node;
		} else {
			this.minNodeValue = node.value;
			return node.right; 
		} 
	}

	private int getDepth(BstNode<T> node) {
		if (node == null) {
			return 0;
		}
		return 1 + Math.max(getDepth(node.left), this.getDepth(node.right));
	}

	private int countNodes(BstNode<T> node) {
		if (node == null) {
			return 0;
		}
		int i = 1 + this.countNodes(node.left) + this.countNodes(node.right);
		return i;
	}

	@Override
	public void add(T e) {
		this.insert(e);
	}

	@Override
	public T removeFirst() {
		if (this.isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		T el = this.rootNode.value;
		this.delete(this.rootNode.value);
		return el;
	}

	@Override
	public T remove(T e) {
		if (!this.exists(e)) {
			throw new java.util.NoSuchElementException();
		} else {
			this.delete(e);
		}
		return e;
	}

	@Override
	public T getFirst() {
		if (this.isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		return this.rootNode.value;
	}

	@Override
	public int depth() {
		return this.getDepth(this.rootNode);
	}

	@Override
	public boolean isEmpty() {
		return (this.rootNode == null);
	}

	@Override
	public int size() {
		int i = this.countNodes(this.rootNode);
		return i;
	}

	@Override
	public boolean exists(T e) {
		return this.member(e);
	}

	@Override
	public List<T> asList() {
		if(this.isEmpty()) {
			return null;
		}
		List<T> list = new ArrayList<>(this.size());
		Stack<BstNode<T>> stack = new Stack<BstNode<T>>();
		BstNode<T> curr = this.rootNode;
		while(curr != null || !stack.isEmpty()) {
			if(curr != null) {
				stack.push(curr);
				curr = curr.left;
			} else {
				list.add((curr = stack.pop()).value);
				curr = curr.right;
			}
		}
		return list;		
	}
}