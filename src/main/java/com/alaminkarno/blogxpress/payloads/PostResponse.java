package com.alaminkarno.blogxpress.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

    private int pageNumber;

    private int pageSize;

    private int totalPages;

    private long totalElements;

    private boolean lastPage;

    private List<PostDto> content;

}
