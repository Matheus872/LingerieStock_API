package one.digitalinnovation.lingeriestock.service;

import one.digitalinnovation.lingeriestock.builder.LingerieDTOBuilder;
import one.digitalinnovation.lingeriestock.dto.PieceDTO;
import one.digitalinnovation.lingeriestock.entity.Piece;
import one.digitalinnovation.lingeriestock.exception.LingerieAlreadyRegisteredException;
import one.digitalinnovation.lingeriestock.exception.LingerieNotFoundException;
import one.digitalinnovation.lingeriestock.exception.LingerieStockCantBeNegativeException;
import one.digitalinnovation.lingeriestock.exception.LingerieStockExceededException;
import one.digitalinnovation.lingeriestock.mapper.LingerieMapper;
import one.digitalinnovation.lingeriestock.repository.LingerieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PieceServiceTest {

    private static final long INVALID_LINGERIE_ID = 1L;

    @Mock
    private LingerieRepository lingerieRepository;

    final private LingerieMapper lingerieMapper = LingerieMapper.INSTANCE;

    @InjectMocks
    private LingerieService lingerieService;

    @Test
    void whenLingerieInformedThenItShouldBeCreated() throws LingerieAlreadyRegisteredException {
        // given
        PieceDTO expectedPieceDTO = LingerieDTOBuilder.builder().build().toLingerieDTO();
        Piece expectedSavedPiece = lingerieMapper.toModel(expectedPieceDTO);

        // when
        when(lingerieRepository.findByName(expectedPieceDTO.getName())).thenReturn(Optional.empty());
        when(lingerieRepository.save(expectedSavedPiece)).thenReturn(expectedSavedPiece);

        //then
        PieceDTO createdPieceDTO = lingerieService.createLingerie(expectedPieceDTO);

        assertThat(createdPieceDTO.getId(), is(equalTo(expectedPieceDTO.getId())));
        assertThat(createdPieceDTO.getName(), is(equalTo(expectedPieceDTO.getName())));
        assertThat(createdPieceDTO.getQuantity(), is(equalTo(expectedPieceDTO.getQuantity())));
    }

    @Test
    void whenAlreadyRegisteredLingerieInformedThenAnExceptionShouldBeThrown() {
        // given
        PieceDTO expectedPieceDTO = LingerieDTOBuilder.builder().build().toLingerieDTO();
        Piece duplicatedPiece = lingerieMapper.toModel(expectedPieceDTO);

        // when
        when(lingerieRepository.findByName(expectedPieceDTO.getName())).thenReturn(Optional.of(duplicatedPiece));

        // then
        assertThrows(LingerieAlreadyRegisteredException.class, () -> lingerieService.createLingerie(expectedPieceDTO));
    }

    @Test
    void whenValidLingerieNameIsGivenThenReturnALingerie() throws LingerieNotFoundException {
        // given
        PieceDTO expectedFoundPieceDTO = LingerieDTOBuilder.builder().build().toLingerieDTO();
        Piece expectedFoundPiece = lingerieMapper.toModel(expectedFoundPieceDTO);

        // when
        when(lingerieRepository.findByName(expectedFoundPiece.getName())).thenReturn(Optional.of(expectedFoundPiece));

        // then
        PieceDTO foundPieceDTO = lingerieService.findByName(expectedFoundPieceDTO.getName());

        assertThat(foundPieceDTO, is(equalTo(expectedFoundPieceDTO)));
    }

    @Test
    void whenNotRegisteredLingerieNameIsGivenThenThrowAnException() {
        // given
        PieceDTO expectedFoundPieceDTO = LingerieDTOBuilder.builder().build().toLingerieDTO();

        // when
        when(lingerieRepository.findByName(expectedFoundPieceDTO.getName())).thenReturn(Optional.empty());

        // then
        assertThrows(LingerieNotFoundException.class, () -> lingerieService.findByName(expectedFoundPieceDTO.getName()));
    }

    @Test
    void whenListLingerieIsCalledThenReturnAListOfLingeries() {
        // given
        PieceDTO expectedFoundPieceDTO = LingerieDTOBuilder.builder().build().toLingerieDTO();
        Piece expectedFoundPiece = lingerieMapper.toModel(expectedFoundPieceDTO);

        //when
        when(lingerieRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundPiece));

        //then
        List<PieceDTO> foundListLingeriesDTO = lingerieService.listAll();

        assertThat(foundListLingeriesDTO, is(not(empty())));
        assertThat(foundListLingeriesDTO.get(0), is(equalTo(expectedFoundPieceDTO)));
    }

    @Test
    void whenListLingerieIsCalledThenReturnAnEmptyListOfLingeries() {
        //when
        when(lingerieRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        //then
        List<PieceDTO> foundListLingeriesDTO = lingerieService.listAll();

        assertThat(foundListLingeriesDTO, is(empty()));
    }

    @Test
    void whenExclusionIsCalledWithValidIdThenALingerieShouldBeDeleted() throws LingerieNotFoundException {
        // given
        PieceDTO expectedDeletedPieceDTO = LingerieDTOBuilder.builder().build().toLingerieDTO();
        Piece expectedDeletedPiece = lingerieMapper.toModel(expectedDeletedPieceDTO);

        // when
        when(lingerieRepository.findById(expectedDeletedPieceDTO.getId())).thenReturn(Optional.of(expectedDeletedPiece));
        doNothing().when(lingerieRepository).deleteById(expectedDeletedPieceDTO.getId());

        // then
        lingerieService.deleteById(expectedDeletedPieceDTO.getId());

        verify(lingerieRepository, times(1)).findById(expectedDeletedPieceDTO.getId());
        verify(lingerieRepository, times(1)).deleteById(expectedDeletedPieceDTO.getId());
    }

    @Test
    void whenIncrementIsCalledThenIncrementLingerieStock() throws LingerieNotFoundException, LingerieStockExceededException, LingerieStockCantBeNegativeException {
        //given
        PieceDTO expectedPieceDTO = LingerieDTOBuilder.builder().build().toLingerieDTO();
        Piece expectedPiece = lingerieMapper.toModel(expectedPieceDTO);

        //when
        when(lingerieRepository.findById(expectedPieceDTO.getId())).thenReturn(Optional.of(expectedPiece));
        when(lingerieRepository.save(expectedPiece)).thenReturn(expectedPiece);

        int quantityToIncrement = 10;
        int actualQuantity = 0;
        int expectedQuantityAfterIncrement = expectedPieceDTO.getQuantity() + quantityToIncrement;

        // then
        PieceDTO incrementedPieceDTO = lingerieService.increment(expectedPieceDTO.getId(), quantityToIncrement, Integer.valueOf(actualQuantity));

        assertThat(expectedQuantityAfterIncrement, equalTo(incrementedPieceDTO.getQuantity()));
        assertThat(expectedQuantityAfterIncrement, lessThan(expectedPieceDTO.getMax()));
    }

    @Test
    void whenIncrementIsGreatherThanMaxThenThrowException() {
        PieceDTO expectedPieceDTO = LingerieDTOBuilder.builder().build().toLingerieDTO();
        Piece expectedPiece = lingerieMapper.toModel(expectedPieceDTO);

        when(lingerieRepository.findById(expectedPieceDTO.getId())).thenReturn(Optional.of(expectedPiece));

        int quantityToIncrement = 80;
        assertThrows(LingerieStockExceededException.class, () -> lingerieService.increment(expectedPieceDTO.getId(), quantityToIncrement, Integer.valueOf(expectedPieceDTO.getQuantity())));
    }

    @Test
    void whenIncrementAfterSumIsGreatherThanMaxThenThrowException() {
        PieceDTO expectedPieceDTO = LingerieDTOBuilder.builder().build().toLingerieDTO();
        Piece expectedPiece = lingerieMapper.toModel(expectedPieceDTO);

        when(lingerieRepository.findById(expectedPieceDTO.getId())).thenReturn(Optional.of(expectedPiece));

        int quantityToIncrement = 600;
        assertThrows(LingerieStockExceededException.class, () -> lingerieService.increment(expectedPieceDTO.getId(), quantityToIncrement, Integer.valueOf(expectedPieceDTO.getQuantity())));
    }

    @Test
    void whenIncrementIsCalledWithInvalidIdThenThrowException() {
        int quantityToIncrement = 10;
        int actualQuantity = 0;

        when(lingerieRepository.findById(INVALID_LINGERIE_ID)).thenReturn(Optional.empty());

        assertThrows(LingerieNotFoundException.class, () -> lingerieService.increment(INVALID_LINGERIE_ID, quantityToIncrement, Integer.valueOf(actualQuantity)));
    }
//
//    @Test
//    void whenDecrementIsCalledThenDecrementLingerieStock() throws LingerieNotFoundException, LingerieStockExceededException {
//        LingerieDTO expectedLingerieDTO = LingerieDTOBuilder.builder().build().toLingerieDTO();
//        Lingerie expectedLingerie = LingerieMapper.toModel(expectedLingerieDTO);
//
//        when(LingerieRepository.findById(expectedLingerieDTO.getId())).thenReturn(Optional.of(expectedLingerie));
//        when(LingerieRepository.save(expectedLingerie)).thenReturn(expectedLingerie);
//
//        int quantityToDecrement = 5;
//        int expectedQuantityAfterDecrement = expectedLingerieDTO.getQuantity() - quantityToDecrement;
//        LingerieDTO incrementedLingerieDTO = LingerieService.decrement(expectedLingerieDTO.getId(), quantityToDecrement);
//
//        assertThat(expectedQuantityAfterDecrement, equalTo(incrementedLingerieDTO.getQuantity()));
//        assertThat(expectedQuantityAfterDecrement, greaterThan(0));
//    }
//
//    @Test
//    void whenDecrementIsCalledToEmptyStockThenEmptyLingerieStock() throws LingerieNotFoundException, LingerieStockExceededException {
//        LingerieDTO expectedLingerieDTO = LingerieDTOBuilder.builder().build().toLingerieDTO();
//        Lingerie expectedLingerie = LingerieMapper.toModel(expectedLingerieDTO);
//
//        when(LingerieRepository.findById(expectedLingerieDTO.getId())).thenReturn(Optional.of(expectedLingerie));
//        when(LingerieRepository.save(expectedLingerie)).thenReturn(expectedLingerie);
//
//        int quantityToDecrement = 10;
//        int expectedQuantityAfterDecrement = expectedLingerieDTO.getQuantity() - quantityToDecrement;
//        LingerieDTO incrementedLingerieDTO = LingerieService.decrement(expectedLingerieDTO.getId(), quantityToDecrement);
//
//        assertThat(expectedQuantityAfterDecrement, equalTo(0));
//        assertThat(expectedQuantityAfterDecrement, equalTo(incrementedLingerieDTO.getQuantity()));
//    }
//
//    @Test
//    void whenDecrementIsLowerThanZeroThenThrowException() {
//        LingerieDTO expectedLingerieDTO = LingerieDTOBuilder.builder().build().toLingerieDTO();
//        Lingerie expectedLingerie = LingerieMapper.toModel(expectedLingerieDTO);
//
//        when(LingerieRepository.findById(expectedLingerieDTO.getId())).thenReturn(Optional.of(expectedLingerie));
//
//        int quantityToDecrement = 80;
//        assertThrows(LingerieStockExceededException.class, () -> LingerieService.decrement(expectedLingerieDTO.getId(), quantityToDecrement));
//    }
//
//    @Test
//    void whenDecrementIsCalledWithInvalidIdThenThrowException() {
//        int quantityToDecrement = 10;
//
//        when(LingerieRepository.findById(INVALID_Lingerie_ID)).thenReturn(Optional.empty());
//
//        assertThrows(LingerieNotFoundException.class, () -> LingerieService.decrement(INVALID_Lingerie_ID, quantityToDecrement));
//    }
}
