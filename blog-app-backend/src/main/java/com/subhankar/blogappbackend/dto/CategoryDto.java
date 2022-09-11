package com.subhankar.blogappbackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer id;
    @NotNull
    @Size(min = 4, max = 50, message = "Size Must be between 4 and 50")
    private String categoryTitle;
    @NotNull
    @Size(min = 10, max = 200, message = "Size Must be between 10 and 200")
    private String categoryDescription;
}
