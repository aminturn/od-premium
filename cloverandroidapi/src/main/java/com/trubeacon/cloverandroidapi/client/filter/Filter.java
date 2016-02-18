package com.trubeacon.cloverandroidapi.client.filter;

import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.client.param.QueryParam;

public class Filter {

	public static final char WILDCARD = '*';
	private static final char FIND_WILDCARD = '%';
	
	private static final String FILTER_KEY = "filter";
	private static final String FIND_KEY = "find";
	
	private Object rval;
	private Comparator comparator;
	private Object lval;
	
	public static Filter filter(Object lval, Comparator comparator, Object rval) {
		return new Filter(lval, comparator, rval);
	}
	
	public Filter(Object lval, Comparator comparator, Object rval) {
		this.lval = lval;
		this.comparator = comparator;
		this.rval = rval;
	}
	
	public Object getRval() {
		return rval;
	}
	public Comparator getComparator() {
		return comparator;
	}
	public Object getLval() {
		return lval;
	}
	
	public enum Comparator {
		
		EQUAL_TO("=="),
		LIKE("like"),
		LESS_THAN_EQUAL_TO("<="),
		LESS_THAN("<"),
		GREATER_THAN_EQUAL_TO(">="),
		GREATER_THAN(">"),
		NOT_EQUAL_TO("!=");
		
		private String comparatorString;
		private Comparator(String comparatorString) {
			this.comparatorString = comparatorString;
		}
		
		public static Comparator fromString(String comparatorString) {
			Comparator comparator = null;
			for (Comparator c : Comparator.values()) {
				if (c.toString().equalsIgnoreCase(comparatorString)) {
					comparator = c;
					break;
				}
			}
			return comparator;
		}
		
		@Override
		public String toString() {
			return comparatorString;
		}
		
	}
	
	public QueryParam toQueryParam() {
		String key = FILTER_KEY;
		String filter = "";
		if (this.lval != null && this.comparator != null && this.rval != null) {
			filter = lval.toString() + comparator.toString() + rval.toString().replace(WILDCARD, FIND_WILDCARD); 
			key = (comparator == Comparator.LIKE) ? FIND_KEY : FILTER_KEY; 
		}
		return Param.query(key, filter); 
	}
	
}
