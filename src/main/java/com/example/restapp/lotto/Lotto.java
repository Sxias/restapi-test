package com.example.restapp.lotto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Getter
@Table(name = "lotto_tb")
@Entity
public class Lotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number_1;
    private int number_2;
    private int number_3;
    private int number_4;
    private int number_5;
    private int number_6;

    @Builder
    public Lotto(int number_1, int number_2, int number_3, int number_4, int number_5, int number_6) {
        this.number_1 = number_1;
        this.number_2 = number_2;
        this.number_3 = number_3;
        this.number_4 = number_4;
        this.number_5 = number_5;
        this.number_6 = number_6;
    }

    public Set<Integer> toSet() {
        return Set.of(number_1, number_2, number_3, number_4, number_5, number_6);
    }
}
