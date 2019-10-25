package pl.bpiotrowski.tictactoe.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "board")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Status status;

    @Column(nullable = false)
    int row;

}
