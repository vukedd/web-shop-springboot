package com.webshop.main.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordererInfo")
public class OrdererInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String firstAndLastName;
	@NotBlank
	private String email;
	@NotBlank
	private String city;
	@NotBlank
	private String zIPCode;
	@NotBlank
	private String shippingAddress;
	@NotBlank
	private String phoneNumber;
	
	@OneToOne
	private Order order;
}
