package com.webshop.main.dtos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class ReviewDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Min(value = 0)
	private int rating;
	@NotBlank
	@Size(max = 200, message="Comment too long!")
	private String comment;
	
	public ReviewDto(Long id, @NotBlank int rating, @NotBlank @Size(max = 200, message = "Comment too long!") String comment) {
		super();
		this.id = id;
		this.rating = rating;
		this.comment = comment;
	}
	
}
