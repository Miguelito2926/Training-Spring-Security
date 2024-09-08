package com.ednaldo.springsecurity6.dto;

import java.util.List;

public record FeedDTO(List<FeedItemDTO> feedItens,
                      int page,
                      int pageSize,
                      int totalPages,
                      Long totalElements) {
}
