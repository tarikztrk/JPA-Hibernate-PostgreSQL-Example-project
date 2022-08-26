package com.example.dboperationproject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class XBag {

	private List<NameValue> basket = new ArrayList<NameValue>();

	public List<NameValue> getBasket() {
		return basket;
	}

	public void setBasket(List<NameValue> basket) {
		this.basket = basket;
	}
	
	public void put(String name,Object value,XBag bag) {
		bag.basket.add(new NameValue(name,value));
	}
	
	public NameValue findByKey(String key, XBag bag) {
		return  bag.getBasket().stream()
				.filter(nameValue -> nameValue.getName().equalsIgnoreCase(key)).findAny().get();
	}
	
}
