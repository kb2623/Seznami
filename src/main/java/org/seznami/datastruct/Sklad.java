package org.seznami.datastruct;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

import Nodes.StackNode;

import UserInterface.Seznam;

public class Sklad<Element extends Comparable<Element>> extends Stack<Element> implements Seznam<Element> {
	
	public Sklad() {
		super();
	}

	@Override
	public boolean isEmpty() {
		return super.isEmpty();
	}

	public int searchObject(Element ele) {
		int i = 0;
		for(StackNode<Element> tmp = super.getTopNode(); tmp != null; tmp = tmp.prev) {
			if(ele.equals(tmp.data)) {
				return i;
			} else {
				i++;
			}
		}
		return -1;
	}

	@Override
	public void add(Element e) {
		super.push(e);
	}

	@Override
	public Element removeFirst() {
		return super.pop();
	}

	@Override
	public Element getFirst() {
		return super.peek();
	}

	@Override
	public int size() {
		return super.count();
	}

	@Override
	public int depth() {
		return super.count();
	}

	@Override
	public boolean exists(Element e) {
		return (this.searchObject(e) != -1);
	}

	@Override
	public Element remove(Element e) {
		if(this.isEmpty()) {
			throw new java.util.NoSuchElementException();
		} else if(super.getTopNode().data.equals(e)) {
			return this.removeFirst();
		} else {
			for(StackNode<Element> tmp = super.getTopNode(); tmp.prev != null; tmp = tmp.prev) {
				if(tmp.prev.data.equals(e)) {
					Element dataElementmp = tmp.prev.data;
					tmp.prev = tmp.prev.prev;
					return dataElementmp;
				}
			}
			throw new java.util.NoSuchElementException();
		}
	}

	@Override
	public List<Element> asList() {
		if(this.isEmpty()) {
			return null;
		}
		List<Element> newList = new ArrayList<>(this.size());
		for(StackNode<Element> tmp = super.getTopNode(); tmp != null; tmp = tmp.prev) {
			newList.add(tmp.data);
		}
		return newList;
	}

	@Override
	public String print() {
		if(this.isEmpty()) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for(StackNode<Element> tmp = super.getTopNode(); tmp != null; tmp = tmp.prev) {
			builder.append(tmp.data);
			if(tmp.prev != null) {
				builder.append('\t');
			}
		}
		return builder.toString();
	}

	@Override
	public void save(OutputStream outputStream) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(outputStream);
		out.writeByte(0);
		out.writeInt(this.size());
		for(StackNode<Element> node = super.getTopNode(); node != null; node = node.prev) {
			out.writeObject(node.data);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void restore(InputStream inputStream) throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(inputStream);
		if(in.readByte() == 0) {
			Stack<Element> stack = new Stack<>();
			for(int i = in.readInt(); i > 0; i--) {
				stack.push((Element) in.readObject());
			}
			while(!stack.isEmpty()) {
				this.add(stack.pop());
			}
		} else {
			int count = in.readInt();
			for (int i = 0; i < count; i++) {
				this.add((Element) in.readObject());
			}
		}
	}

	@Override
	public void setComparator(Comparator<Element> comparator) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
