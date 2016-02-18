package com.trubeacon.cloverandroidapi.discount;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape=JsonFormat.Shape.OBJECT)
public class Discounts implements List<Discount> {

	@JsonProperty("discounts")
	private List<RawDiscount> raw;
	@JsonProperty
	private Meta meta;
	
	public List<RawDiscount> getRaw() {
		return raw;
	}
	public void setRaw(List<RawDiscount> raw) {
		this.raw = raw;
	}
	
	public Meta getMeta() {
		return meta;
	}
	public void setMeta(Meta meta) {
		this.meta = meta;
	}
	
	public static class Meta {
		
		@JsonProperty
		private String modifiedBefore;

		public String getModifiedBefore() {
			return modifiedBefore;
		}

		public void setModifiedBefore(String modifiedBefore) {
			this.modifiedBefore = modifiedBefore;
		}
		
	}
	
	private List<Discount> discounts;
	private synchronized List<Discount> getDiscounts() {
		if (this.discounts == null) {
			this.discounts = Discount.fromRawDiscounts(raw);
		}
		return this.discounts;
	}
	
	@Override
    public int size() {
	    return getDiscounts().size();
    }
	@Override
    public boolean isEmpty() {
	    return getDiscounts().isEmpty();
    }
	@Override
    public boolean contains(Object o) {
	    return getDiscounts().contains(o);
    }
	@Override
    public Iterator<Discount> iterator() {
	    return getDiscounts().iterator();
    }
	@Override
    public Object[] toArray() {
	    return getDiscounts().toArray();
    }
	@Override
    public <Discount> Discount[] toArray(Discount[] a) {
	    return getDiscounts().toArray(a);
    }
	@Override
    public boolean add(Discount e) {
	    return getDiscounts().add(e);
    }
	@Override
    public boolean remove(Object o) {
	    return getDiscounts().remove(o);
    }
	@Override
    public boolean containsAll(Collection<?> c) {
	    return getDiscounts().containsAll(c);
    }
	@Override
    public boolean addAll(Collection<? extends Discount> c) {
	    return getDiscounts().addAll(c);
    }
	@Override
    public boolean addAll(int index, Collection<? extends Discount> c) {
	    return getDiscounts().addAll(index, c);
    }
	@Override
    public boolean removeAll(Collection<?> c) {
	    return getDiscounts().removeAll(c);
    }
	@Override
    public boolean retainAll(Collection<?> c) {
	    return getDiscounts().retainAll(c);
    }
	@Override
    public void clear() {
		getDiscounts().clear();
    }
	@Override
    public Discount get(int index) {
	    return getDiscounts().get(index);
    }
	@Override
    public Discount set(int index, Discount element) {
	    return getDiscounts().set(index, element);
    }
	@Override
    public void add(int index, Discount element) {
		getDiscounts().add(index, element);
    }
	@Override
    public Discount remove(int index) {
	    return getDiscounts().remove(index);
    }
	@Override
    public int indexOf(Object o) {
	    return getDiscounts().indexOf(o);
    }
	@Override
    public int lastIndexOf(Object o) {
	    return getDiscounts().lastIndexOf(o);
    }
	@Override
    public ListIterator<Discount> listIterator() {
	    return getDiscounts().listIterator();
    }
	@Override
    public ListIterator<Discount> listIterator(int index) {
	    return getDiscounts().listIterator(index);
    }
	@Override
    public List<Discount> subList(int fromIndex, int toIndex) {
	    return getDiscounts().subList(fromIndex, toIndex);
    }

}
