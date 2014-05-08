package Source;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import Nodes.BinHeapaNode;

public class BinomskaKopica<T extends Comparable<T>> implements Seznam<T> {
	
	private BinHeapaNode<T> topNode;

    public BinomskaKopica() {
        this.topNode = null;
    }

	@Override
	public void add(T e) {
		if(this.isEmpty()) {
			this.topNode = new BinHeapaNode<>(e, null, null, null, 0);
		} else {
			this.topNode = new BinHeapaNode<>(e, null, this.topNode, null, 0);
			while(this.topNode.sibling != null && this.topNode.depth == this.topNode.sibling.depth) {
				this.topNode = this.merge(this.topNode, this.topNode.sibling);
			}
		}
	}

	private BinHeapaNode<T> merge(BinHeapaNode<T> prevNode, BinHeapaNode<T> nextNode) {
		if(prevNode.data.compareTo(nextNode.data) < 0) {
			prevNode.parent = nextNode;
			prevNode.sibling = nextNode.child;
			nextNode.child = prevNode;
			nextNode.depth = 1 + prevNode.depth;
			return nextNode;
		} else {
			prevNode.sibling = nextNode.sibling;
			nextNode.parent = prevNode;
			nextNode.sibling = prevNode.child;
			prevNode.child = nextNode;
			prevNode.depth = 1 + nextNode.depth;
			return prevNode;
		}
	}

	@Override
	public T removeFirst(){
		if(this.isEmpty()) {
			throw new java.util.NoSuchElementException();
		} else {
			BinHeapaNode<T> prevMax = null, currMax = this.topNode;
			BinHeapaNode<T> curr = this.topNode;
			while(curr.sibling != null) {
				if(curr.sibling.data.compareTo(currMax.data) > 0) 	{
					currMax = curr.sibling;
					prevMax = curr;
				}
				curr = curr.sibling;
			}
			if(prevMax != null) {
				prevMax.sibling = currMax.sibling;
			} else { 
				this.topNode = this.topNode.sibling;
			}
			this.removeNode(currMax);
			return currMax.data;
		}
	}

	private BinHeapaNode<T> fixAll(BinHeapaNode<T> node) {
		if(node.sibling == null) {
			return node;
		} else if(node.depth == node.sibling.depth) {
			while(node.sibling != null && node.depth == node.sibling.depth) {
				node = this.merge(node, node.sibling);
			}
			return node;
		} else {
			node.sibling = fixAll(node.sibling);
			return node;
		}
	}

	@Override
	public T getFirst() {
		if(this.isEmpty()) {
			throw new java.util.NoSuchElementException();
		} else {
			T max = this.topNode.data;
			BinHeapaNode<T> curr = this.topNode;
			while(curr != null) {
				if(max.compareTo(curr.data) < 0) {
					max = curr.data;
				}
				curr = curr.sibling;
			}
			return max;
		}
	}

	@Override
	public int size() {
		if(this.isEmpty()) {
			return 0;
		} else {
			return this.size(this.topNode);
		}
	}

	private int size(BinHeapaNode<T> node) {
		if(node == null) {
			return 0;
		} else {
			return 1 + this.size(node.child) + this.size(node.sibling);
		}
	}

	@Override
	public int depth() {
		if(this.isEmpty()) {
			return 0;
		} else {
			BinHeapaNode<T> tmp = this.topNode;
			while(tmp.sibling != null) {
				tmp = tmp.sibling;
			}
			return tmp.depth;
		}
	}

	@Override
	public boolean isEmpty() {
		return (this.topNode == null);
	}

	@Override
	public boolean exists(T e) {
		if(this.isEmpty()) {
			return false;
		} else {
			return (this.findNode(e) != null);
		}
	}

	@Override
	public T remove(T e) {
		if(this.isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		BinHeapaNode<T> node, prev;
		if(null == (node = this.findNode(e))) {
			throw new java.util.NoSuchElementException();
		}
		T ret = node.data;
		while(node.parent != null) {
			node.data = node.parent.data;
			node = node.parent;
		}
		if(node == this.topNode) {
			this.topNode = this.topNode.sibling;
		} else {
			prev = this.topNode;
			while(prev.sibling != node) {
				prev = prev.sibling;
			}
			prev.sibling = node.sibling;
		}
		this.removeNode(node);
		return ret;
	}

	private BinHeapaNode<T> findNode(T e) {
		BinHeapaNode<T> curr = this.topNode;
		Stack<BinHeapaNode<T>> stack = new Stack<>();
		while(curr != null || !stack.isEmpty()) {
			if(curr == null) {
				curr = stack.pop().sibling;
			} else if(curr.data.compareTo(e) == 0) {
				break;
			} else if(curr.data.compareTo(e) > 0) {
				stack.push(curr);
				curr = curr.child;
			} else {
				curr = curr.sibling;
			}
		}
		return curr;
	}

	private void removeNode(BinHeapaNode<T> node) {
		if(null != node.child) {
			node.sibling = null;
			BinHeapaNode<T> curr, prev;
			Stack<BinHeapaNode<T>> stack = new Stack<>();
			curr = node.child;
			while(curr != null) {
				curr.parent = null;
				stack.push(curr);
				curr = curr.sibling;
			}
			curr = this.topNode;
			prev = null;
			while(curr != null && !stack.isEmpty()) {
				if(curr.depth == stack.peek().depth) {
					if(prev != null) {
						prev.sibling = this.merge(stack.pop(), curr);
						curr = prev.sibling.sibling;							
						prev = prev.sibling;
					} else {
						this.topNode = this.merge(stack.pop(), this.topNode);
						prev = this.topNode;
						curr = this.topNode.sibling;
					}
				} else {
					if(prev != null) {
						prev.sibling = stack.pop();
						prev.sibling.sibling = curr;
						prev = prev.sibling;
					} else {
						this.topNode = stack.pop();
						this.topNode.sibling = curr;
						prev = this.topNode;
					}
				}
			}
			while(!stack.isEmpty()) {
				if(prev != null) {
					prev.sibling = stack.pop();
					prev.sibling.sibling = null;
					prev = prev.sibling;
				} else {
					this.topNode = stack.pop();
					this.topNode.sibling = null;
					prev = this.topNode;
				}
			}
			if(curr != null) {
				this.topNode = fixAll(this.topNode);
			}
		}
	}

	@Override
	public List<T> asList() {
		if(this.isEmpty()) {
			return null;
		}
		List<T> list = new ArrayList<>(this.size());
		Stack<BinHeapaNode<T>> stack = new Stack<>();
		BinHeapaNode<T> curr = this.topNode;
		while(curr != null || !stack.isEmpty()) {
			if(curr == null) {
				curr = stack.pop();
				list.add(curr.data);
				curr = curr.sibling;
			} else if(curr.child != null) {
				stack.push(curr);
				curr = curr.child;
			} else {
				list.add(curr.data);
				curr = curr.sibling;
			}
		}
		return list;
	}

	@Override
	public void print() {
		int tabs = 0;
		BinHeapaNode<T> node = this.topNode;
		while(node != null) {
			if(node.child == null) {
				if(node.parent == null) {
					System.out.println(node.data);
					node = node.sibling;
				} else {
					for(int i = 1; i < tabs; i++) {
						System.out.print("\t");
					}
					System.out.println(node.parent.data+"\t"+node.data);
					node = node.parent.sibling;
					tabs--;
				}
			} else {
				node = node.child;
				tabs++;
			}
		}
	}

	@Override
	public void save(OutputStream outputStream) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(outputStream);
		out.writeByte(3);
        // TODO : Shrani strukturo v izhodni podatkovni "out"
	}

	@Override
	public void restore(InputStream inputStream) throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(inputStream);
		if(in.readByte() == 3) {
			// TODO : Imamo shranjeno strukturo, ki je BinomskaKopica
		} else {
            int size = in.readInt();
            this.topNode = null;
            for(int i = 0; i < size; i++) {
                this.add((T) in.readObject());
            }
		}
	}
}
