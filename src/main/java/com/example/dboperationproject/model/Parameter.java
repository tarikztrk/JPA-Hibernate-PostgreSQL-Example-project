package com.example.dboperationproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parameters")
public class Parameter {
	
	public Parameter() {
	}

	public Parameter(String commandName, String className, String methodName) {
		super();
		this.commandName = commandName;
		this.className = className;
		this.methodName = methodName;
	}

	@Id
	@Column(name = "command_name")
	private String commandName;

	@Column(name = "class_name")
	private String className;
	
	@Column(name = "method_name")
	private String methodName;

	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}


}

