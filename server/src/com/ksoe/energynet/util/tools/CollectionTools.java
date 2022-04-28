package com.ksoe.energynet.util.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionTools {
	public static final <E> Collection<Collection<E>> partition(Collection<E> collection, int size) {
		if(collection == null) {
			throw new java.lang.NullPointerException("collection is null");
		}
		if(size <= 0) {
			throw new java.lang.IllegalArgumentException("size must be > 0");
		}
		List<E> full = new ArrayList<E>(collection);
		List<Collection<E>> result = new ArrayList<Collection<E>>();
		if(collection.size() == 0) {
			result.add(collection);
			return result;
		}
		
		int index = 0;
		
		while(index < full.size()) {
			List<E> part = null;
			if(index + size < full.size()) {
				part = full.subList(index, index + size);
			} else {
				part = full.subList(index, full.size());
			}
			result.add(part);
			index += size;
		}
		return result;
	}
	
	public static final boolean checkCollectionContainsStringIgnoreCase(Iterable<String> coll, String value) {
		if(coll == null || value == null) throw new java.lang.NullPointerException();
		for(String item : coll) {
			if(item.equalsIgnoreCase(value)) return true;
		}
		return false;
	}

}
