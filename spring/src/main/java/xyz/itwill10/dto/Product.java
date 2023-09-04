package xyz.itwill10.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class Product {
	// @NotEmpty(message = "채워")
	@NotEmpty
	private String productCode;
	@NotEmpty(message = "채워")
	private String productName;
	@Max(value = 100, message = "줄여")
	@Min(value = 1, message = "늘려")
	private int productQty;
	
}
