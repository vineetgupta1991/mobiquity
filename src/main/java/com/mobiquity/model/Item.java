package com.mobiquity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
	
	private Integer index;
	private Double weight;
	private Integer value;

}