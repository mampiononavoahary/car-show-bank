package com.fresh.coding.carshow.dtos.responses;

import java.util.List;

public record Paginate<T>(
        List<T> data,
        Long total,
        Integer page,
        Integer perPage
) {
}
