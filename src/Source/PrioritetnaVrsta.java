package Source;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
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

	@Override
	public List<T> asList() {
		if(this.isEmpty()) {
			return null;
		} else {
			List<T> newList = new ArrayList<>(this.size());
			for(int i = 0; i < this.size(); i++) {
				newList.add((T) this.heap[i]);
			}
			return newList;
		}
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(OutputStream outputStream) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restore(InputStream inputStream) throws IOException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}
}
