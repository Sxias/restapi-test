package com.example.restapp.lotto.winner;


import com.example.restapp.lotto.Lotto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "winner_tb")
@Entity
public class Winner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Lotto lotto;

    private int rank;

    public Winner(Lotto lotto, int rank) {
        this.rank = rank;
        this.lotto = lotto;
    }
}
