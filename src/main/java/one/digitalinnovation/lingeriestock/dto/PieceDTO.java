package one.digitalinnovation.lingeriestock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.lingeriestock.enums.PieceCloth;
import one.digitalinnovation.lingeriestock.enums.PieceColor;
import one.digitalinnovation.lingeriestock.enums.PieceGrade;
import one.digitalinnovation.lingeriestock.enums.PieceType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PieceDTO {

    private Long id;

    @NotNull
    @Size(min = 9, max = 15)
    private String reference;

    @NotNull
    @Size(min = 1, max = 200)
    private String name;

    @NotNull
    @Max(100)
    private Integer quantity;

    @NotNull
    @Max(500)
    private Integer max;

    @Enumerated(EnumType.STRING)
    @NotNull
    private PieceType type;

    @Enumerated(EnumType.STRING)
    @NotNull
    private PieceCloth cloth;

    @Enumerated(EnumType.STRING)
    @NotNull
    private PieceGrade grade;

    @NotNull
    @Size(min = 1, max = 50)
    private String color;

}
