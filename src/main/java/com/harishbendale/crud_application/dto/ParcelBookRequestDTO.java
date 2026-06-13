package com.harishbendale.crud_application.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParcelBookRequestDTO {
	@NotNull(message = "Description is required")
	private String description;
	
	@NotNull(message = "Weight is required")
	private BigDecimal weight;
}
