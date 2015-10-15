package UserInterface;

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Comparator;
import java.util.List;

public interface Seznam<Element> {
	// Dodajanje elementa v podatkovno strukturo
	public void add(Element e);
	// Odstranjevanje (in vračanje) prvega elementa iz pod. strukture
	public Element removeFirst();
	// Vračanje prvega elementa iz pod. struk.
	public Element getFirst();
	// Število elementov v podatkovni strukturi
	public int size();
	// Globina podatkovne strukture
	public int depth();
	// Ali je podakovna struktura prazna
	public boolean isEmpty();
	// Ali v strukturi obstja element e
	public boolean exists(Element e);
	// Izbrisi prvi element
	public Element remove(Element e);
	// Vrne elemente podatkovne strukture kot seznam
	public List<Element> asList();
	// Izpise strukturo
	public String print();
	// Shrani strukturo
	public void save(OutputStream outputStream) throws IOException;
	// Vrne strukturo v zadnje shranjeno stanje
	public void restore(InputStream inputStream) throws IOException, ClassNotFoundException;
	// Metoda, ki nastavi/zamenja comparator
	public void setComparator(Comparator<Element> comparator);
}
