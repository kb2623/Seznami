package Source;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import Nodes.StackNode;

public class Sklad<T> extends Stack<T> implements Seznam<T> {
	
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
					T dataTmp = tmp.prev.data;
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
		List<T> newList = new ArrayList<>(this.size());
		for(StackNode<T> tmp = super.getTopNode(); tmp != null; tmp = tmp.prev) {
			newList.add(tmp.data);
		}
		return newList;
	}

	@Override
	public void print() {
		if(this.isEmpty()) {
			return;
		}
		for(StackNode<T> tmp = super.getTopNode(); tmp != null; tmp = tmp.prev) {
			System.out.print(tmp.data);
			if(tmp.prev != null) {
				System.out.print('\t');
			}
		}
		System.out.println();
	}

	@Override
	public void save(OutputStream outputStream) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(outputStream);
		out.writeByte(0);
		out.writeInt(this.size());
		for(StackNode<T> node = super.getTopNode(); node != null; node = node.prev) {
			out.writeObject(node.data);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void restore(InputStream inputStream) throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(inputStream);
		if(in.readByte() == 0) {
			Stack<T> stack = new Stack<>();
			for(int i = in.readInt(); i > 0; i--) {
				stack.push((T) in.readObject());
			}
			while(!stack.isEmpty()) {
				this.add(stack.pop());
			}
		} else {
			int count = in.readInt();
			for (int i = 0; i < count; i++) {
				this.add((T) in.readObject());
			}
		}
	}
}
