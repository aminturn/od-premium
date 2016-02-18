package com.trubeacon.cloverandroidapi.common;

import java.util.Collection;

public interface AlternateIdentifiable<T> extends Identifiable {
	public Collection<AlternateIdentifier> getAlternateIds();
	public boolean alternateEquals(T target);
}
