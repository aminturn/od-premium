package com.trubeacon.cloverandroidapi.client.sort;

import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.client.param.QueryParam;

public class Sort {

	private static final String SORT_KEY = "orderBy";
	
	private String field;
	private Direction direction = Direction.DESC;
	
	public static Sort sort(String field) {
		return new Sort(field);
	}
	
	public static Sort sort(String field, Direction direction) {
		return new Sort(field, direction);
	}
	
	public Sort(String field) {
		this.field = field;
		this.direction = Direction.DESC;
	}
	
	public Sort(String field, Direction direction) {
		this.field = field;
		this.direction = direction;
	}
	
	public enum Direction {
		
		ASC("ASC"),
		DESC("DESC");
		
		private String dirString;
		private Direction(String dirString) {
			this.dirString = dirString;
		}
		
		public static Direction fromString(String directionString) {
			Direction direction = null;
			for (Direction d : Direction.values()) {
				if (d.toString().equalsIgnoreCase(directionString)) {
					direction = d;
					break;
				}
			}
			return direction;
		}
		
		@Override
		public String toString() {
			return dirString;
		}
		
	}
	
	public QueryParam toQueryParam() {
		String sort = this.field + " " + this.direction.toString();
		return Param.query(SORT_KEY, sort);
	}
	
}
