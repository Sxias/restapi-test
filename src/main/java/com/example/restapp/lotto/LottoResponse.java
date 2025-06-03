package com.example.restapp.lotto;

import lombok.Data;

import java.util.Set;

public class LottoResponse {
    @Data
    public static class DTO {
        private Set<Integer> lottoSet;

        public DTO(Set<Integer> lottoSet) {
            this.lottoSet = lottoSet;
        }
    }
}
