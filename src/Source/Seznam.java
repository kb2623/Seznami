package Source;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface Seznam<T> {
	// Dodajanje elementa v podatkovno strukturo
	public void add(T e);
	// Odstranjevanje (in vračanje) prvega elementa iz pod. strukture
	public T removeFirst();
	// Vračanje prvega elementa iz pod. struk.
	public T getFirst();
	// Število elementov v podatkovni strukturi
	public int size();
	// Globina podatkovne strukture
	public int depth();
	// Ali je podakovna struktura prazna
	public boolean isEmpty();
	// Ali v strukturi obstja element e
	public boolean exists(T e);
	// Izbrisi prvi element
	public T remove(T e);
	// Vrne elemente podatkovne strukture kot seznam
	public List<T> asList();
	// Izpise strukturo
	public void print();
	// Shrani strukturo
	public void save(OutputStream outputStream) throws IOException;
	// Vrne strukturo v zadnje shranjeno stanje
	public void restore(InputStream inputStream) throws IOException, ClassNotFoundException;
}
