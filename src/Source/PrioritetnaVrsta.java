package Source;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PrioritetnaVrsta<T extends Comparable<T>> implements Seznam<T> {
	
	private Object[] heap;
	private int end;
	private static final byte ADD_SIZE = 2;

	public PrioritetnaVrsta(int maxSize) {
		this.heap = new Object[maxSize];
		this.end = 0;
	}

	public PrioritetnaVrsta() {
		this(100);
	}

	@Override
	public void add(T e) {
		if(this.heap.length <= this.end) {
			this.resize();
		}
		this.heap[this.end++] = e;
		this.bubbleUp();
	}

	private void resize() {
		Object newHeap[] = new Object[this.heap.length * PrioritetnaVrsta.ADD_SIZE];
		System.arraycopy(this.heap, 0, newHeap, 0, this.heap.length);
		this.heap = newHeap;
	}

	@SuppressWarnings("unchecked")
	private void bubbleUp() {
		for(int pIndex = this.end - 1; pIndex >= 0; pIndex = (pIndex % 2 == 1)?(pIndex - 1) / 2 : (pIndex - 2) / 2) {
			T pValue = (T) this.heap[pIndex];
			int cIndex = pIndex * 2 + 1;
			if(cIndex < this.end) {
				T cValue = (T) this.heap[cIndex];
				if(cIndex + 1 < this.end && cValue.compareTo((T) this.heap[cIndex + 1]) < 0) {
					cValue = (T) this.heap[++cIndex];
				}
				if(pValue.compareTo(cValue) >= 0) {
					return;
				}
				this.swap(pIndex, cIndex);
			}
		}
	}

	private void swap(int source, int target) {
		Object tmp = this.heap[target];
		this.heap[target] = this.heap[source];
		this.heap[source] = tmp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T removeFirst() {
		if(this.isEmpty()) {
			throw new NoSuchElementException();
		} else {
			T tmp = (T) this.heap[0];
			this.swap(0, --this.end);
			this.bubbleDown(0);
			return tmp;
		}
	}


	@SuppressWarnings("unchecked")
	private void bubbleDown(int start) {
		int pIndex = start, cIndex = pIndex * 2 + 1; 
		while(cIndex < this.end) {
			T cValue = (T) this.heap[cIndex] , pValue = (T) this.heap[pIndex];
			if(cIndex + 1 < this.end && cValue.compareTo((T) this.heap[cIndex + 1]) < 0) {
				cValue = (T) this.heap[++cIndex];
			}
			if(pValue.compareTo(cValue) >= 0) {
				return;
			}
			this.swap(pIndex, cIndex);
			pIndex = cIndex;
			cIndex = pIndex * 2 + 1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getFirst() {
		if (this.isEmpty()) {
			throw new java.util.NoSuchElementException();
		} else {
			return (T) this.heap[0];
		}
	}

	@Override
	public int size() {
		return this.end;
	}

	@Override
	public int depth() {
		if (this.end == 0) {
			return 0;
		}
		return (int) (Math.log(this.end) / Math.log(2)) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (this.end == 0);
	}

	@Override
	public boolean exists(T e) {
		if(this.isEmpty()) {
			return false;
		} else {
			return this.exists(e, 0);
		}
	}

	@SuppressWarnings("unchecked")
	private boolean exists(T e, int index) {
		if(index >= this.end) {
			return false;
		} else if(((T) this.heap[index]).compareTo(e) < 0) {
			return false;
		} else if(((T) this.heap[index]).compareTo(e) == 0) {
			return true;
		} else {
			if(this.exists(e, index * 2 + 1)) {
				return true;
			} else {
				return this.exists(e, index * 2 + 2);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T remove(T e) {
		int index = this.search(e, 0);
		if(this.isEmpty() || index == -1) {
			throw new java.util.NoSuchElementException();
		} else {
			T tmp = (T) this.heap[index];
			this.swap(index, --this.end);
			this.bubbleDown(index);
			return tmp;
		}
	}

	@SuppressWarnings("unchecked")
	private int search(T e, int index) {
		if(index >= this.end) {
			return -1;
		} else if(((T) this.heap[index]).compareTo(e) < 0) {
			return -1;
		} else if(((T) this.heap[index]).compareTo(e) == 0) {
			return index;
		} else {
			int left = this.search(e, index * 2 + 1);
			if(left > -1) {
				return left;
			} else {
				return this.search(e, index * 2 + 2);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> asList() {
		if(this.isEmpty()) {
			return null;
		} else {
			List<T> newList = new ArrayList<T>(this.size());
			for(int i = 0; i < this.size(); i++) {
				newList.add((T) this.heap[i]);
			}
			return newList;
		}
	}

	@Override
	public void print() {
		this.print(0, 0);
	}
	
	private void print(int ele, int numTabs) {
		if(ele < this.size()) {
			this.print(ele*2+1, numTabs+1);
			for(int i = 0; i < numTabs; i++) {
				System.out.print('\t');
			}
			System.out.println(this.heap[ele]);
			this.print(ele*2+2, numTabs+1);
		}
	}

	@Override
	public void save(OutputStream outputStream) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(outputStream);
		out.writeByte(1);
		out.writeInt(this.size());
		for(int i = 0; i < this.size(); i++) {
			out.writeObject(this.heap[i]);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void restore(InputStream inputStream) throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(inputStream);
		if(in.readByte() != 1) {
			int size = in.readInt();
			this.heap = new Object[size];
			for(int i = 0; i < size; i++) {
				this.add((T) in.readObject());
			}
		} else {
			int size = in.readInt();
			this.end = size;
			this.heap = new Object[size];
			for(int i = 0; i < size; i++) {
				this.heap[i] = in.readObject();
			}
		}
	}
}
