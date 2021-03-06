package org.seznami.datastruct;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

import Nodes.BstNode;

import UserInterface.Seznam;

public class Bst<T extends Comparable<T>> implements Seznam<T> {

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

	private BstNode<T> rootNode;
	private Comparator<T> cmp;
	private T minNodeValue;

	public Bst(Comparator<T> comparator) {
		this.rootNode = null;
		this.cmp = comparator;
	}
	
	public Bst() {
		this(null);
	}
	
	@Override
	public void setComparator(Comparator<T> comparator) {
		List<T> list = this.asList();
		this.rootNode = null;
		this.cmp = comparator;
		for(T t : list) {
			this.add(t);
		}
	}
	
	private int compare(T o1, T o2) {
		if(this.cmp == null) {
			return o1.compareTo(o2);
		} else {
			return this.cmp.compare(o1, o2);
		}
	}

	private boolean member(T e) {
		return this.member(e, this.rootNode);
	}

	private boolean member(T e, BstNode<T> node) {
		if (node == null) {
			return false;
		} else if (this.compare(e, node.value) == 0) {
			return true;
		} else if (this.compare(e, node.value) < 0) {
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
			node = new BstNode<>(e);
		} else if (this.compare(e, node.value) < 0) {
			node.left = this.insertLeaf(e, node.left);
		} else if (this.compare(e, node.value) > 0) {
			node.right = this.insertLeaf(e, node.right);
		} else {
			throw new java.lang.IllegalArgumentException();
		}
		return node;
	}

	private BstNode<T> delete(T e, BstNode<T> node) {
		if (node != null) {
			if (this.compare(e, node.value) == 0) {
				if (node.left == null) {
					node = node.right;
				} else if (node.right == null) {
					node = node.left;
				} else {
					node.right = this.deleteMin(node.right);
					node.value = this.minNodeValue;
				}
			} else if (this.compare(e, node.value) < 0) {
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
		return (1 + this.countNodes(node.left) + this.countNodes(node.right));
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
		return this.countNodes(this.rootNode);
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
		Stack<BstNode<T>> stack = new Stack<>();
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

	@Override
	public String print() {
		StringBuilder builder = new StringBuilder();
		this.print(this.rootNode, 0, builder);
		return builder.toString();
	}
	
	private void print(BstNode<T> node, int numTabs, StringBuilder builder) {
		if(node != null) {
			this.print(node.right, numTabs+1, builder);
			for(int i = 0; i < numTabs; i++) {
				builder.append('\t');
			}
			builder.append(node.value);
			this.print(node.left, numTabs+1, builder);
		}
	}

	@Override
	public void save(OutputStream outputStream) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(outputStream);
		out.writeByte(2);
		out.writeInt(this.size());
		this.save(this.rootNode, out);
	}
	
	private void save(BstNode<T> node, ObjectOutputStream out) throws IOException {
		if(node != null) {
			this.save(node.left, out);
			out.writeObject(node.value);
			this.save(node.right, out);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void restore(InputStream inputStream) throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(inputStream);
		if(in.readByte() != 2) {
			int count = in.readInt();
			this.rootNode = new BstNode<>((T) in.readObject());
			for(int i = 1; i < count; i++) {
				try {
					this.add((T) in.readObject());
				} catch (IllegalArgumentException e) {
					System.out.println("Error: element exists");
				}
			}
		} else {
			int count = in.readInt();
			this.rootNode = this.restore(in, count);
		}
	}

	@SuppressWarnings("unchecked")
	private BstNode<T> restore(ObjectInputStream in, int count) throws IOException, ClassNotFoundException {
		if(count == 0) {
			return null;
		}
		BstNode<T> nodeLeft = this.restore(in, count/2);
		BstNode<T> node = new BstNode<>((T) in.readObject());
		node.left = nodeLeft;
		node.right = this.restore(in, (count-1)/2);
		return node;
	}
}
