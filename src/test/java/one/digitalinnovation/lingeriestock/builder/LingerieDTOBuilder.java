package one.digitalinnovation.lingeriestock.builder;

import lombok.Builder;
import one.digitalinnovation.lingeriestock.dto.PieceDTO;
import one.digitalinnovation.lingeriestock.enums.PieceCloth;
import one.digitalinnovation.lingeriestock.enums.PieceColor;
import one.digitalinnovation.lingeriestock.enums.PieceGrade;
import one.digitalinnovation.lingeriestock.enums.PieceType;

@Builder
public class LingerieDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String reference = "000000000";

    @Builder.Default
    private String name = "Peça";

    @Builder.Default
    private int quantity = 3;

    @Builder.Default
    private int max = 50;

    @Builder.Default
    private PieceType type = PieceType.NUM10;

    @Builder.Default
    private PieceCloth cloth = PieceCloth.NUM0;

    @Builder.Default
    private PieceGrade grade = PieceGrade.NUM1;

    @Builder.Default
    private String color = "Branco #FFFFFF";

    public PieceDTO toLingerieDTO() {
        return new PieceDTO(id,
                reference,
                name,
                quantity,
                max,
                type,
                cloth,
                grade,
                color
        );
    }
    
    
    
    
    
    
    
    
    
    

}
