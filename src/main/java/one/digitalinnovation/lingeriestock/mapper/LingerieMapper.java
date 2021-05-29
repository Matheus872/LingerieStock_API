package one.digitalinnovation.lingeriestock.mapper;

import one.digitalinnovation.lingeriestock.dto.PieceDTO;
import one.digitalinnovation.lingeriestock.entity.Piece;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LingerieMapper {

    LingerieMapper INSTANCE = Mappers.getMapper(LingerieMapper.class);

    Piece toModel(PieceDTO pieceDTO);

    PieceDTO toDTO(Piece piece);
}
