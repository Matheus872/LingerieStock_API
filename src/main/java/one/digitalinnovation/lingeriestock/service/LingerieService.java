package one.digitalinnovation.lingeriestock.service;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import one.digitalinnovation.lingeriestock.dto.PieceDTO;
import one.digitalinnovation.lingeriestock.entity.Piece;
import one.digitalinnovation.lingeriestock.exception.LingerieAlreadyRegisteredException;
import one.digitalinnovation.lingeriestock.exception.LingerieNotFoundException;
import one.digitalinnovation.lingeriestock.exception.LingerieStockCantBeNegativeException;
import one.digitalinnovation.lingeriestock.exception.LingerieStockExceededException;
import one.digitalinnovation.lingeriestock.mapper.LingerieMapper;
import one.digitalinnovation.lingeriestock.repository.LingerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // Indica que essa classe será gerenciada pelo spring
@AllArgsConstructor(onConstructor = @__(@Autowired)) // Lombock: inclui construtor
public class LingerieService {

    private final LingerieRepository lingerieRepository;
    private final LingerieMapper lingerieMapper = LingerieMapper.INSTANCE;

    public PieceDTO createLingerie(PieceDTO pieceDTO) throws LingerieAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(pieceDTO.getName());
        Piece piece = lingerieMapper.toModel(pieceDTO);
        Piece savedPiece = lingerieRepository.save(piece);
        return lingerieMapper.toDTO(savedPiece);
    }

    public PieceDTO findByName(String name) throws LingerieNotFoundException {
        Piece foundPiece = lingerieRepository.findByName(name)
                .orElseThrow(() -> new LingerieNotFoundException(name));
        return lingerieMapper.toDTO(foundPiece);
    }

    public PieceDTO findByID(Long id) throws LingerieNotFoundException {
        Piece foundPiece = lingerieRepository.findById(id)
                .orElseThrow(() -> new LingerieNotFoundException(id));
        return lingerieMapper.toDTO(foundPiece);
    }

    public List<PieceDTO> listAll() {
        return lingerieRepository.findAll()
                .stream()
                .map(lingerieMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) throws LingerieNotFoundException {
        verifyIfExists(id);
        lingerieRepository.deleteById(id);
    }

    private void verifyIfIsAlreadyRegistered(String name) throws LingerieAlreadyRegisteredException {
        Optional<Piece> optSavedBeer = lingerieRepository.findByName(name);
        if (optSavedBeer.isPresent()) {
            throw new LingerieAlreadyRegisteredException(name);
        }
    }

    private Piece verifyIfExists(Long id) throws LingerieNotFoundException {
        return lingerieRepository.findById(id)
                .orElseThrow(() -> new LingerieNotFoundException(id));
    }

    public PieceDTO increment(Long id, int quantityToIncrement, Integer actualQuantity) throws LingerieNotFoundException, LingerieStockExceededException,
                                                                                            LingerieStockCantBeNegativeException{
        Piece pieceToIncrementStock = verifyIfExists(id);

        int quantityAfterIncrement = quantityToIncrement + pieceToIncrementStock.getQuantity();

        if ( quantityAfterIncrement<0){
            throw new LingerieStockCantBeNegativeException(id,quantityToIncrement, actualQuantity);
        }

        if (quantityAfterIncrement <= pieceToIncrementStock.getMax()) {
            pieceToIncrementStock.setQuantity(pieceToIncrementStock.getQuantity() + quantityToIncrement);
            Piece incrementedPieceStock = lingerieRepository.save(pieceToIncrementStock);
            return lingerieMapper.toDTO(incrementedPieceStock);
        }
        throw new LingerieStockExceededException(id, quantityToIncrement);
    }
}
