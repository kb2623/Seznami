package org.seznami.userinterface;

import java.util.Comparator;

public interface AbstractFacrory<Element> {
	/**
	 * 
	 * @param string
	 * @return
	 */
	public Element makeElement(String string);
	/**
	 * 
	 * @param string
	 * @return
	 */
	public Comparator<Element> makeComparator(String string);
}
