package com.trubeacon.cloverandroidapi.client.pagination;

import com.trubeacon.cloverandroidapi.client.param.Param;
import com.trubeacon.cloverandroidapi.client.param.QueryParam;

public class Pagination {

	public static final String OFFSET_KEY = "offset";
	public static final String LIMIT_KEY = "limit";
	
	private static final int MIN_LIMIT = 1;
	public static final int DEFAULT_LIMIT = 100;
	private static final int MAX_LIMIT = 1000;
	
	private int offset = 0;
	private int limit = 100;

	public static Pagination pagination(int offset, int limit) {
		return new Pagination(offset, limit);
	}
		
	public Pagination(int offset, int limit) {
		this.offset = offset;
		if (limit < MIN_LIMIT) limit = MIN_LIMIT;
		else if (limit > MAX_LIMIT) limit = MAX_LIMIT;
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public QueryParam toOffsetQueryParam() {
		return Param.query(OFFSET_KEY, this.offset);
	}
	
	public QueryParam toLimitQueryParam() {
		return Param.query(LIMIT_KEY, this.limit);
	}
	
}
