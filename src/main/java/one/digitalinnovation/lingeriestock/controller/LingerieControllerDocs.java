package one.digitalinnovation.lingeriestock.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import one.digitalinnovation.lingeriestock.dto.PieceDTO;
import one.digitalinnovation.lingeriestock.dto.QuantityDTO;
import one.digitalinnovation.lingeriestock.exception.LingerieAlreadyRegisteredException;
import one.digitalinnovation.lingeriestock.exception.LingerieNotFoundException;
import one.digitalinnovation.lingeriestock.exception.LingerieStockCantBeNegativeException;
import one.digitalinnovation.lingeriestock.exception.LingerieStockExceededException;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api("Manages lingerie stock")
public interface LingerieControllerDocs {

    @ApiOperation(value = "Lingerie creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success lingerie creation"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
    })
    PieceDTO createLingerie(PieceDTO pieceDTO) throws LingerieAlreadyRegisteredException;

    @ApiOperation(value = "Returns lingerie found by a given name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success lingerie found in the system"),
            @ApiResponse(code = 404, message = "Lingerie with given name not found.")
    })
    PieceDTO findByName(@PathVariable String name) throws LingerieNotFoundException;

    @ApiOperation(value = "Returns lingerie found by a given id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success lingerie found in the system"),
            @ApiResponse(code = 404, message = "Lingerie with given id not found.")
    })
    PieceDTO findByID(@PathVariable Long id) throws LingerieNotFoundException;


    @ApiOperation(value = "Returns a list of all lingerie registered in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all lingeries registered in the system"),
    })
    List<PieceDTO> listLingeries();

    @ApiOperation(value = "Delete a lingeries found by a given valid Id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success lingerie deleted in the system"),
            @ApiResponse(code = 404, message = "Lingerie with given id not found.")
    })
    void deleteById(@PathVariable Long id) throws LingerieNotFoundException;

    @ApiOperation(value = "Increment lingerie quantity in stock")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success lingerie stock incremented"),
            @ApiResponse(code = 404, message ="Lingerie with given id not found."),

    })
    PieceDTO increment(Long id, Integer actualQuantity, QuantityDTO quantityDTO) throws LingerieStockCantBeNegativeException,
                                                                                    LingerieNotFoundException,
                                                                                    LingerieStockExceededException;
}
