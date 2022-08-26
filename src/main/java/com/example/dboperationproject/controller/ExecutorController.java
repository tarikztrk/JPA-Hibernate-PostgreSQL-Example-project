package com.example.dboperationproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dboperationproject.managers.Interfaces.IExecutor;
import com.example.dboperationproject.model.XBag;

@RestController
@RequestMapping("/api")
public class ExecutorController {

	@Autowired
    private IExecutor executor;
    
    @Autowired
    public ExecutorController(IExecutor executor) {
		super();
		this.executor = executor;
	}

	@PostMapping("/executor/{commandName}")
    public Object execute(@PathVariable String commandName, @RequestBody XBag basket) {
        return executor.execute(commandName,basket);
    }


}
