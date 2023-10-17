package com.bartosztanski.mailservice.model;

import java.util.Map;

import lombok.Data;

@Data

public class TemplateModel {
	private String template;
	private Map<String,Object> variables;
	
}
