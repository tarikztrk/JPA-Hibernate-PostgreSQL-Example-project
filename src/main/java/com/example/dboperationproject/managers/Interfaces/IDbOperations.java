package com.example.dboperationproject.managers.Interfaces;

import java.util.List;
import java.util.Optional;

import com.example.dboperationproject.model.XBag;


public interface IDbOperations {
	XBag getAll(XBag bag);

	XBag getById(XBag bag);
	
	XBag create(XBag bag);
	
	XBag update(XBag bag);
	
	XBag delete(XBag bag);
}
