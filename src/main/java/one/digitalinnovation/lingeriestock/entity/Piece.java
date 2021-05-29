package one.digitalinnovation.lingeriestock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.lingeriestock.enums.PieceCloth;
import one.digitalinnovation.lingeriestock.enums.PieceGrade;
import one.digitalinnovation.lingeriestock.enums.PieceType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data //Anotação do lombock, Gera getters, setters, equals e hashcode
@Entity // Mapeamento do JPA
@NoArgsConstructor
@AllArgsConstructor
public class Piece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reference;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int max;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private PieceType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private PieceCloth cloth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private PieceGrade grade;

    @Column(nullable = true)
    private String color;

}
