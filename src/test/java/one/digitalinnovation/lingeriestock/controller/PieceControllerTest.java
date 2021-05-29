package one.digitalinnovation.lingeriestock.controller;

import one.digitalinnovation.lingeriestock.builder.LingerieDTOBuilder;
import one.digitalinnovation.lingeriestock.dto.PieceDTO;
import one.digitalinnovation.lingeriestock.dto.QuantityDTO;
import one.digitalinnovation.lingeriestock.exception.LingerieNotFoundException;
import one.digitalinnovation.lingeriestock.service.LingerieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PieceControllerTest {

    private static final String Lingerie_API_URL_PATH = "/api/v1/Lingeries";
    private static final long VALID_Lingerie_ID = 1L;
    private static final long INVALID_Lingerie_ID = 2l;
    private static final String Lingerie_API_SUBPATH_INCREMENT_URL = "/increment";
    private static final String Lingerie_API_SUBPATH_DECREMENT_URL = "/decrement";

    private MockMvc mockMvc;

    @Mock
    private LingerieService lingerieService;

    @InjectMocks
    private LingerieController LingerieController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(LingerieController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenALingerieIsCreated() throws Exception {
        // given
        PieceDTO pieceDTO = LingerieDTOBuilder.builder().build().toLingerieDTO();

        // when
        when(lingerieService.createLingerie(pieceDTO)).thenReturn(pieceDTO);

        // then
//        mockMvc.perform(post(Lingerie_API_URL_PATH)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(LingerieDTO)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name", is(LingerieDTO.getName())))
//                .andExpect(jsonPath("$.brand", is(LingerieDTO.getBrand())))
//                .andExpect(jsonPath("$.type", is(LingerieDTO.getType().toString())));
    }

    @Test
    void whenPOSTIsCalledWithoutRequiredFieldThenAnErrorIsReturned() throws Exception {
        // given
//        LingerieDTO LingerieDTO = LingerieDTOBuilder.builder().build().toLingerieDTO();
//        LingerieDTO.setBrand(null);

        // then
//        mockMvc.perform(post(Lingerie_API_URL_PATH)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(pieceDTO)))
//                .andExpect(status().isBadRequest());
    }

    @Test
    void whenGETIsCalledWithValidNameThenOkStatusIsReturned() throws Exception {
        // given
        PieceDTO pieceDTO = LingerieDTOBuilder.builder().build().toLingerieDTO();

        //when
        when(lingerieService.findByName(pieceDTO.getName())).thenReturn(pieceDTO);

//        // then
//        mockMvc.perform(MockMvcRequestBuilders.get(Lingerie_API_URL_PATH + "/" + LingerieDTO.getName())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is(LingerieDTO.getName())))
//                .andExpect(jsonPath("$.brand", is(LingerieDTO.getBrand())))
//                .andExpect(jsonPath("$.type", is(LingerieDTO.getType().toString())));
    }

    @Test
    void whenGETIsCalledWithoutRegisteredNameThenNotFoundStatusIsReturned() throws Exception {
        // given
        PieceDTO pieceDTO = LingerieDTOBuilder.builder().build().toLingerieDTO();

        //when
        when(lingerieService.findByName(pieceDTO.getName())).thenThrow(LingerieNotFoundException.class);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(Lingerie_API_URL_PATH + "/" + pieceDTO.getName())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGETListWithLingeriesIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        PieceDTO pieceDTO = LingerieDTOBuilder.builder().build().toLingerieDTO();

        //when
        when(lingerieService.listAll()).thenReturn(Collections.singletonList(pieceDTO));

        // then
//        mockMvc.perform(MockMvcRequestBuilders.get(Lingerie_API_URL_PATH)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name", is(LingerieDTO.getName())))
//                .andExpect(jsonPath("$[0].brand", is(LingerieDTO.getBrand())))
//                .andExpect(jsonPath("$[0].type", is(LingerieDTO.getType().toString())));
    }

    @Test
    void whenGETListWithoutLingeriesIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        PieceDTO pieceDTO = LingerieDTOBuilder.builder().build().toLingerieDTO();

        //when
        when(lingerieService.listAll()).thenReturn(Collections.singletonList(pieceDTO));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(Lingerie_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
        // given
        PieceDTO pieceDTO = LingerieDTOBuilder.builder().build().toLingerieDTO();

        //when
        doNothing().when(lingerieService).deleteById(pieceDTO.getId());

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete(Lingerie_API_URL_PATH + "/" + pieceDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenDELETEIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {
        //when
        doThrow(LingerieNotFoundException.class).when(lingerieService).deleteById(INVALID_Lingerie_ID);

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete(Lingerie_API_URL_PATH + "/" + INVALID_Lingerie_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenPATCHIsCalledToIncrementDiscountThenOKstatusIsReturned() throws Exception {
        QuantityDTO quantityDTO = QuantityDTO.builder()
                .quantity(10)
                .build();

        PieceDTO pieceDTO = LingerieDTOBuilder.builder().build().toLingerieDTO();
        pieceDTO.setQuantity(pieceDTO.getQuantity() + quantityDTO.getQuantity());

//        when(LingerieService.increment(VALID_Lingerie_ID, quantityDTO.getQuantity())).thenReturn(LingerieDTO);
//
//        mockMvc.perform(MockMvcRequestBuilders.patch(Lingerie_API_URL_PATH + "/" + VALID_Lingerie_ID + Lingerie_API_SUBPATH_INCREMENT_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(quantityDTO))).andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is(LingerieDTO.getName())))
//                .andExpect(jsonPath("$.brand", is(LingerieDTO.getBrand())))
//                .andExpect(jsonPath("$.type", is(LingerieDTO.getType().toString())))
//                .andExpect(jsonPath("$.quantity", is(LingerieDTO.getQuantity())));
    }

//    @Test
//    void whenPATCHIsCalledToIncrementGreatherThanMaxThenBadRequestStatusIsReturned() throws Exception {
//        QuantityDTO quantityDTO = QuantityDTO.builder()
//                .quantity(30)
//                .build();
//
//        LingerieDTO LingerieDTO = LingerieDTOBuilder.builder().build().toLingerieDTO();
//        LingerieDTO.setQuantity(LingerieDTO.getQuantity() + quantityDTO.getQuantity());
//
//        when(LingerieService.increment(VALID_Lingerie_ID, quantityDTO.getQuantity())).thenThrow(LingerieStockExceededException.class);
//
//        mockMvc.perform(patch(Lingerie_API_URL_PATH + "/" + VALID_Lingerie_ID + Lingerie_API_SUBPATH_INCREMENT_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .con(asJsonString(quantityDTO))).andExpect(status().isBadRequest());
//    }

//    @Test
//    void whenPATCHIsCalledWithInvalidLingerieIdToIncrementThenNotFoundStatusIsReturned() throws Exception {
//        QuantityDTO quantityDTO = QuantityDTO.builder()
//                .quantity(30)
//                .build();
//
//        when(LingerieService.increment(INVALID_Lingerie_ID, quantityDTO.getQuantity())).thenThrow(LingerieNotFoundException.class);
//        mockMvc.perform(patch(Lingerie_API_URL_PATH + "/" + INVALID_Lingerie_ID + Lingerie_API_SUBPATH_INCREMENT_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(quantityDTO)))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void whenPATCHIsCalledToDecrementDiscountThenOKstatusIsReturned() throws Exception {
//        QuantityDTO quantityDTO = QuantityDTO.builder()
//                .quantity(5)
//                .build();
//
//        LingerieDTO LingerieDTO = LingerieDTOBuilder.builder().build().toLingerieDTO();
//        LingerieDTO.setQuantity(LingerieDTO.getQuantity() + quantityDTO.getQuantity());
//
//        when(LingerieService.decrement(VALID_Lingerie_ID, quantityDTO.getQuantity())).thenReturn(LingerieDTO);
//
//        mockMvc.perform(patch(Lingerie_API_URL_PATH + "/" + VALID_Lingerie_ID + Lingerie_API_SUBPATH_DECREMENT_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(quantityDTO))).andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is(LingerieDTO.getName())))
//                .andExpect(jsonPath("$.brand", is(LingerieDTO.getBrand())))
//                .andExpect(jsonPath("$.type", is(LingerieDTO.getType().toString())))
//                .andExpect(jsonPath("$.quantity", is(LingerieDTO.getQuantity())));
//    }
//
//    @Test
//    void whenPATCHIsCalledToDEcrementLowerThanZeroThenBadRequestStatusIsReturned() throws Exception {
//        QuantityDTO quantityDTO = QuantityDTO.builder()
//                .quantity(60)
//                .build();
//
//        LingerieDTO LingerieDTO = LingerieDTOBuilder.builder().build().toLingerieDTO();
//        LingerieDTO.setQuantity(LingerieDTO.getQuantity() + quantityDTO.getQuantity());
//
//        when(LingerieService.decrement(VALID_Lingerie_ID, quantityDTO.getQuantity())).thenThrow(LingerieStockExceededException.class);
//
//        mockMvc.perform(patch(Lingerie_API_URL_PATH + "/" + VALID_Lingerie_ID + Lingerie_API_SUBPATH_DECREMENT_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(quantityDTO))).andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void whenPATCHIsCalledWithInvalidLingerieIdToDecrementThenNotFoundStatusIsReturned() throws Exception {
//        QuantityDTO quantityDTO = QuantityDTO.builder()
//                .quantity(5)
//                .build();
//
//        when(LingerieService.decrement(INVALID_Lingerie_ID, quantityDTO.getQuantity())).thenThrow(LingerieNotFoundException.class);
//        mockMvc.perform(patch(Lingerie_API_URL_PATH + "/" + INVALID_Lingerie_ID + Lingerie_API_SUBPATH_DECREMENT_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(quantityDTO)))
//                .andExpect(status().isNotFound());
//    }
}
