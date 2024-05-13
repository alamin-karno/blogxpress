package com.alaminkarno.blogxpress.payloads;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer categoryId;

    @NotEmpty(message = "Category name can not be empty or null.")
    private String categoryName;

    @NotEmpty(message = "Category description can not be empty or null.")
    private String description;

}
