package com.trubeacon.cloverandroidapi.account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.merchant.Merchant;

@JsonFormat(shape=JsonFormat.Shape.OBJECT)
public class Merchants implements List<Merchant> {

	@JsonProperty("merchants")
	private List<Merchant> raw;

	public List<Merchant> getRaw() {
		return raw;
	}

	public void setRaw(List<Merchant> raw) {
		this.raw = raw;
	}

	private List<Merchant> merchants;
	private synchronized List<Merchant> getMerchants() {
		if (this.merchants == null) {
			this.merchants = new ArrayList<Merchant>(this.raw);
		}
		return this.merchants;
	}
	
	@Override
    public int size() {
	    return getMerchants().size();
    }
	@Override
    public boolean isEmpty() {
	    return getMerchants().isEmpty();
    }
	@Override
    public boolean contains(Object o) {
	    return getMerchants().contains(o);
    }
	@Override
    public Iterator<Merchant> iterator() {
	    return getMerchants().iterator();
    }
	@Override
    public Object[] toArray() {
	    return getMerchants().toArray();
    }
	@Override
    public <Merchant> Merchant[] toArray(Merchant[] a) {
	    return getMerchants().toArray(a);
    }
	@Override
    public boolean add(Merchant e) {
	    return getMerchants().add(e);
    }
	@Override
    public boolean remove(Object o) {
	    return getMerchants().remove(o);
    }
	@Override
    public boolean containsAll(Collection<?> c) {
	    return getMerchants().containsAll(c);
    }
	@Override
    public boolean addAll(Collection<? extends Merchant> c) {
	    return getMerchants().addAll(c);
    }
	@Override
    public boolean addAll(int index, Collection<? extends Merchant> c) {
	    return getMerchants().addAll(index, c);
    }
	@Override
    public boolean removeAll(Collection<?> c) {
	    return getMerchants().removeAll(c);
    }
	@Override
    public boolean retainAll(Collection<?> c) {
	    return getMerchants().retainAll(c);
    }
	@Override
    public void clear() {
		getMerchants().clear();
    }
	@Override
    public Merchant get(int index) {
	    return getMerchants().get(index);
    }
	@Override
    public Merchant set(int index, Merchant element) {
	    return getMerchants().set(index, element);
    }
	@Override
    public void add(int index, Merchant element) {
		getMerchants().add(index, element);
    }
	@Override
    public Merchant remove(int index) {
	    return getMerchants().remove(index);
    }
	@Override
    public int indexOf(Object o) {
	    return getMerchants().indexOf(o);
    }
	@Override
    public int lastIndexOf(Object o) {
	    return getMerchants().lastIndexOf(o);
    }
	@Override
    public ListIterator<Merchant> listIterator() {
	    return getMerchants().listIterator();
    }
	@Override
    public ListIterator<Merchant> listIterator(int index) {
	    return getMerchants().listIterator(index);
    }
	@Override
    public List<Merchant> subList(int fromIndex, int toIndex) {
	    return getMerchants().subList(fromIndex, toIndex);
    }
	
}
