package one.digitalinnovation.lingeriestock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PieceColor {

    PRETO("Preto: #000000"),
    BRANCO("Branco: #FFFFFF");

    private final String description;
}