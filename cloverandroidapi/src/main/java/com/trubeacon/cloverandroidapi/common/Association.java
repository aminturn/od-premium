package com.trubeacon.cloverandroidapi.common;

public abstract class Association {

	protected Reference reference1;
	protected Reference reference2;
	
	// marked abstract so json can be properly set
	public abstract Reference getReference1();		
	public void setReference1(Reference reference1) {
		this.reference1 = reference1;
	}
	// marked abstract so json can be properly set
	public abstract Reference getReference2();
	public void setReference2(Reference reference2) {
		this.reference2 = reference2;
	}
	
}
