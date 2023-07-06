package com.harshit.blogapi.payloads;

import java.util.ArrayList;
import java.util.List;

import com.harshit.blogapi.entities.Post;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Integer categoryId;
	
	@NotBlank
	@Size(min = 4 , message= "Minimum size must be greater than 4")
	private String categoryTitle;
	
	@NotBlank
	@Size(min = 10 , message= "minimum size must be greater than 10")
	private String categoryDescription;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();
	
	
}
