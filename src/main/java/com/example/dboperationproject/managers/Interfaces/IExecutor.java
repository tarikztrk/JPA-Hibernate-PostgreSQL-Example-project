package com.example.dboperationproject.managers.Interfaces;

import com.example.dboperationproject.model.XBag;

public interface IExecutor {

	XBag execute(String commandName,XBag basket);
	
}
