package one.digitalinnovation.lingeriestock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PieceCloth {

    //MALHAS
    NUM10("Malha Lisa Penteada"),
    NUM11("Malha Trabalhada Penteada"),
    NUM12("Malha Canelada Lisa"),
    NUM15("Malha Estampada Penteada"),
    NUM16("Malha Trabalhada Estampada"),
    NUM17("Malha Canelada Estampada"),
    NUM19("Malha Compose"),

    //COTTON 10
    NUM20("Cotton 10 Liso"),
    NUM21("Cotton 10 Liso Renda"),
    NUM22("Cotton 10 Liso Tule"),
    NUM25("Cotton 10 Estampado"),
    NUM26("Cotton 10 Estampado Renda"),
    NUM27("Cotton 10 Estampado Tule"),
    NUM29("Cotton 10 Compose"),

    //BAMBU
    NUM30("Bambu Liso"),
    NUM31("Bambu Liso Renda"),
    NUM32("Bambu Liso Tule"),
    NUM35("Bambu Estampado"),
    NUM36("Bambu Estampado Renda"),
    NUM37("Bambu Estampado Tule"),
    NUM39("Bambu Compose"),

    //MODAL
    NUM40("Modal Liso"),
    NUM41("Modal Liso Renda"),
    NUM42("Modal Liso Tule"),
    NUM45("Modal Estampado"),
    NUM46("Modal Estampado Renda"),
    NUM47("Modal Estampado Tule"),
    NUM49("Modal Compose"),

    //MICROFIBRA
    NUM50("Microfibra Poliamida Lisa"),
    NUM51("Microfibra Poliamida Lisa Renda"),
    NUM52("Microfibra Poliamida Lisa Tule"),
    NUM55("Microfibra Poliamida Estampada"),
    NUM56("Microfibra Poliamida Estampada Renda"),
    NUM57("Microfibra Poliamida Estampada Tule"),
    NUM59("Microfibra Poliamida Compose"),

    //POLIÉSTER
    NUM60("Poliester Liso"),
    NUM61("Poliester Liso Renda"),
    NUM62("Poliester Liso Tule"),
    NUM65("Poliester Estampado"),
    NUM66("Poliester Estampado Renda"),
    NUM67("Poliester Estampado Tule"),
    NUM69("Poliester Compose"),

    //LIGANETE
    NUM70("Liganete Poliamida Lisa"),
    NUM71("Liganete Poliamida Lisa Renda"),
    NUM72("Liganete Poliamida Lisa Tule"),
    NUM73("Liganete Poliamida Lisa Compose"),
    NUM75("Liganete Poliamida Estampada"),
    NUM76("Liganete Poliamida Estampada Renda"),
    NUM77("Liganete Poliamida Estampada Tule"),
    NUM78("Liganete Estampada Compose"),

    //CETINETTE
    NUM80("Cetinete Lisa"),
    NUM81("Cetinete Lisa Renda"),
    NUM82("Cetinete Lisa Tule"),
    NUM85("Cetinete Power"),
    NUM86("Cetinete Power Renda"),
    NUM87("Cetinete Power Tule"),
    NUM89("Cetinete Compose"),

    //Tule
    NUM90("Tule Liso"),
    NUM91("Tule Liso Renda"),
    NUM92("Tule Liso Tule Bordado"),
    NUM93("Tule Trabalhado"),
    NUM94("Tule Trabalhado Renda"),
    NUM95("Tule Estampado"),
    NUM96("Tule Estampado Renda"),
    NUM97("Tule Renda Lycra"),
    NUM98("Tule Laise Rendada"),
    NUM99("Tule Trabalhado Compose"),

    //OUTROS
    NUM0("Outros");

    private final String description;
}
