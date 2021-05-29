package one.digitalinnovation.lingeriestock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PieceGrade {


    NUM1("PP - P - M"),
    NUM2("PP - P - M - G"),
    NUM3("P - M - G"),
    NUM4("P - M - G - GG"),
    NUM5("P - M - G - GG - EG - XG"),
    NUM6("M - G - GG - EG - XG"),
    NUM7("G - GG - EG - XG"),
    NUM8("GG - EG - XG"),
    NUM9("GG - EG - XG - SG - LG");

    private final String description;
}
