package one.digitalinnovation.lingeriestock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PieceFinish {

    //SOUTIEN
    NUM10("Acabamento Normal"),
    NUM11("Acabamento Detalhe Renda"),
    NUM12("Acabamento Estruturado"),
    NUM13("Acabamento Forrado"),
    NUM14("Acabamento Aro Fechado"),
    NUM15("Acabamento Aro Inteiriço"),
    NUM16("Acabamento Nadador"),
    NUM17("Acabamento Elástico Elastan"),
    NUM18("Acabamento Abotoador"),
    NUM19("Acabamento Strapppy"),

    //CALCINHA
    NUM20("Acabamento Normal"),
    NUM21("Acabamento String 1T"),
    NUM22("Acabamento String 2T"),
    NUM23("Acabamento String 3T"),
    NUM24("Acabamento Faixa Larga Cintura"),
    NUM25("Acabamento Forrado"),
    NUM27("Acabamento Elástico Elastan"),
    NUM28("Acabamento Strappy"),
    NUM29("Acabamento Galena"),

    //ROBE
    NUM30("Acabamento Curto"),
    NUM31("Acabamento Longo"),
    NUM32("Acabamento Reto"),
    NUM33("Acabamento Godé"),
    NUM34("Acabamento Manga Flare"),

    //BODY
    NUM40("Acabamento Normal"),
    NUM41("Acabamento Estruturado"),
    NUM42("Acabamento Curto"),
    NUM43("Acabamento Decote Frontal"),
    NUM44("Acabamento Decote nas Costas"),
    NUM45("Acabamento Cavado nas Laterais"),
    NUM46("Acabamento Det. em Elástico"),

    //CAMISOLA
    NUM51("Acabamento Infantil"),
    NUM52("Acabamento Juvenil"),
    NUM53("Acabamento Curto"),
    NUM54("Acabamento Chanel"),
    NUM56("Acabamento Amamentação"),
    NUM57("Acabamento Estruturado"),
    NUM59("Acabamento Strappy"),

    //BLUSA
    NUM60("Acabamento Infantil"),
    NUM61("Acabamento Juvenil"),
    NUM62("Acabamento Adulto"),
    NUM63("Acabamento Curto"),
    NUM64("Acabamento Chanel"),
    NUM65("Acabamento Longo"),
    NUM66("Acabamento Estruturado"),

    //CALÇA
    NUM70("Acabamento Infantil"),
    NUM71("Acabamento Juvenil"),
    NUM72("Acabamento Curto"),
    NUM73("Acabamento Capri"),
    NUM74("Acabamento Longo"),

    //CONJUNTO
    NUM80("Acabamento Normal"),

    //CUECA
    NUM90("Acabamento Infantil"),
    NUM91("Acabamento Juvenil"),
    NUM92("Acabamento Adulto"),

    //EXÓTICOS
    NUM00("Acabamento Normal"),
    NUM01("Acabamento Amarrar");


    private final String description;
}