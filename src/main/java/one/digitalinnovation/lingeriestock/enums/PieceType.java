package one.digitalinnovation.lingeriestock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PieceType {

    //SOUTIEN
    NUM10("Soutien Normal"),
    NUM11("Soutien Plus Size"),
    NUM12("Soutien Amamentação"),
    NUM13("Soutien Meiia Taça"),
    NUM14("Soutien Estruturado"),
    NUM15("Soutien T Q C "),
    NUM16("Soutien Top"),
    NUM17("Soutien Corpete"),
    NUM8("Soutien Cortinão"),
    NUM19("Soutien Body"),

    //Calcinha
    NUM20("Calcinha Infantil"),
    NUM21("Calcinha Fio Dental"),
    NUM22("Calcinha Tanga String"),
    NUM23("Calcinha Biquini"),
    NUM24("Calcinha Cava Alta"),
    NUM25("Calcinha Caleçon"),
    NUM26("Calcinha Clássica"),
    NUM27("Calcinha Calçola"),
    NUM28("Calcinha Cinta"),
    NUM29("Calcinha Cinta Liga"),


    //ROBE
    NUM30("Robe Manga Curta"),
    NUM31("Robe Manga 3/4"),
    NUM32("Robe Manga 3/4 Flare"),
    NUM35("Robe Manga Longa"),
    NUM36("Robe Manga Longa Flare"),

    //BODY
    NUM40("Body Manga Longa"),
    NUM41("Body Manga Curta"),
    NUM42("Body Alcinha"),
    NUM45("Body Regata"),

    //CAMISOLA
    NUM50("Camisola Alcinha"),
    NUM51("Camisola Nadador"),
    NUM52("Camisola Manga 3/4"),
    NUM55("Camisola Manga Longa"),
    NUM56("Camisola Decote Costas"),

    //BLUSA
    NUM60("Blusa Alcinha"),
    NUM61("Blusa Regata"),
    NUM62("Blusa Manga Curta"),
    NUM63("Blusa Manga 3/4"),
    NUM64("Blusa Fem Manga Longa"),
    NUM65("Blusa Masc M curta"),
    NUM66("Blusa Masc M Longa"),
    NUM67("Blusa Nadador"),
    NUM68("Blusa Cropped Manga Curta"),

    //CALÇA
    NUM70("Shorts Feminino"),
    NUM71("Shorts Masculino"),
    NUM75("Calça Feminina"),
    NUM76("Calça Masculina"),

    //CONJUNTOS
    NUM80("Conjunto Espartilho"),
    NUM81("Conjunto Pijama de Frio"),
    NUM82("Conjunto Grippir Microfibra Dupla"),
    NUM83("Conjunto Top sem Bojo Elástico Lurex"),
    NUM84("Conjunto Baby doll Tule e Renda"),

    //CUECA
    NUM90("Tradicional"),
    NUM91("Sungão"),
    NUM92("Boxer"),
    NUM93("Samba Canção"),

    //EXÓTICOS
    NUM00("Saia Longa Tule Maternidade"),
    NUM01("Toper Maternidade Ciganinha"),
    NUM02("Meias para Espartilho");

    private final String description;
}
