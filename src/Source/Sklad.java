package Source;

import java.util.ArrayList;
import java.util.List;
import Nodes.StackNode;

public class Sklad<T extends Comparable<T>> extends Stack<T> implements Seznam<T> {

	public Sklad() {
		super();
	}

	@Override
	public boolean isEmpty() {
		return super.isEmpty();
	}

	public int searchObject(T ele) {
		int i = 0;
		for(StackNode<T> tmp = super.getTopNode(); tmp != null; tmp = tmp.prev) {
			if(ele.equals(tmp.data)) {
				return i;
			} else {
				i++;
			}
		}
		return -1;
	}

	@Override
	public void add(T e) {
		super.push(e);		
	}

	@Override
	public T removeFirst() {
		return super.pop();
	}

	@Override
	public T getFirst() {
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
	public boolean exists(T e) {
		return (this.searchObject(e) != -1);
	}

	@Override
	public T remove(T e) {
		if(this.isEmpty()) {
			throw new java.util.NoSuchElementException();
		} else if(super.getTopNode().data.equals(e)) {
			return this.removeFirst();
		} else {
			for(StackNode<T> tmp = super.getTopNode(); tmp.prev != null; tmp = tmp.prev) {
				if(tmp.prev.data.equals(e)) {
					T dataTmp = (T) tmp.prev.data;
					tmp.prev = tmp.prev.prev;
					return dataTmp;
				}
			}
			throw new java.util.NoSuchElementException();
		}
	}

	@Override
	public List<T> asList() {
		if(this.isEmpty()) {
			return null;
		}
		List<T> newList = new ArrayList<T>(this.size());
		for(StackNode<T> tmp = super.getTopNode(); tmp != null; tmp = tmp.prev) {
			newList.add(tmp.data);
		}
		return newList;
	}
}