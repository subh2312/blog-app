package com.subhankar.blogappbackend.payloads;

import com.subhankar.blogappbackend.dto.PostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class GetAllResponse {
    private List<PostDto> content;
    private int pageNumber;
    private int pageSize;
    private int totalElements;
    private int totalPages;
    private boolean isLast;
}
