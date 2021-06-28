package one.digitalinnovation.lingeriestock.controller;

import lombok.AllArgsConstructor;
import one.digitalinnovation.lingeriestock.dto.PieceDTO;
import one.digitalinnovation.lingeriestock.dto.QuantityDTO;
import one.digitalinnovation.lingeriestock.exception.LingerieAlreadyRegisteredException;
import one.digitalinnovation.lingeriestock.exception.LingerieNotFoundException;
import one.digitalinnovation.lingeriestock.exception.LingerieStockCantBeNegativeException;
import one.digitalinnovation.lingeriestock.exception.LingerieStockExceededException;
import one.digitalinnovation.lingeriestock.service.LingerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController // Indica que é uma classe controladora voltada para API REST(somente troca de dados, sem visuaização ou parte gráfica)
@RequestMapping("/api/v1/lingeries")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LingerieController implements LingerieControllerDocs {

    private final LingerieService lingerieService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PieceDTO createLingerie(@RequestBody @Valid PieceDTO pieceDTO) throws LingerieAlreadyRegisteredException{
        return lingerieService.createLingerie(pieceDTO);
    }

    @GetMapping("/name/{name}")
    public PieceDTO findByName(@PathVariable String name) throws LingerieNotFoundException {
        return lingerieService.findByName(name);
    }

    @GetMapping("/id/{id}")
    public PieceDTO findByID(@PathVariable Long id) throws LingerieNotFoundException {
        return lingerieService.findByID(id);
    }

    @GetMapping
    public List<PieceDTO> listLingeries() {
        return lingerieService.listAll();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws LingerieNotFoundException {
        lingerieService.deleteById(id);
    }

    @PatchMapping("/increment/{id}")
    public PieceDTO increment(@PathVariable Long id, Integer actualQuantity, @RequestBody @Valid QuantityDTO quantityDTO) throws LingerieStockCantBeNegativeException, LingerieNotFoundException, LingerieStockExceededException {
        PieceDTO objeto = findByID(id);
       Integer quantidadeAtual = Integer.valueOf(objeto.getQuantity());
        return lingerieService.increment(id, quantityDTO.getQuantity(), quantidadeAtual);
    }

}
