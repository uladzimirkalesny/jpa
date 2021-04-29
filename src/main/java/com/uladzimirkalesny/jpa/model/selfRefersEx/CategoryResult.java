package com.uladzimirkalesny.jpa.model.selfRefersEx;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class CategoryResult {

    private final Category category;
    private final String path;
    private final Long level;

}
