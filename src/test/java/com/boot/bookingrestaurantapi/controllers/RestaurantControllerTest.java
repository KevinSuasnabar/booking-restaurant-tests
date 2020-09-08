package com.boot.bookingrestaurantapi.controllers;

import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.RestaurantRest;
import com.boot.bookingrestaurantapi.jsons.TurnRest;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import com.boot.bookingrestaurantapi.services.RestaurantService;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class RestaurantControllerTest {

    private static final Long RESTAURANT_ID = 1L;
    private static final String RESTAURANT_NAME = "Burger";
    private static final String RESTAURANT_DESCR = "Todo tipo de hamburguesa";
    private static final String RESTAURANT_ADDRESS = "Av. Galindo";
    private static final String RESTAURANT_IMAGE = "www.image.com";

    private static final String SUCCESS_STATUS = "Succes";
    private static final String SUCCESS_CODE = "200 OK";
    private static final String OK = "OK";

    public static final RestaurantRest RESTAURANT_REST = new RestaurantRest();
    public static final List<TurnRest> TURN_REST_LIST = new ArrayList<>();
    public static final List<RestaurantRest> RESTAURANT_REST_LIST = new ArrayList<>();


    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    RestaurantController restaurantController;

    @Before
    public void init() throws BookingException {
        MockitoAnnotations.initMocks(this);

        //para getRestaurantByIdTest()
        RESTAURANT_REST.setName(RESTAURANT_NAME);
        RESTAURANT_REST.setDescription(RESTAURANT_DESCR);
        RESTAURANT_REST.setAddress(RESTAURANT_ADDRESS);
        RESTAURANT_REST.setId(RESTAURANT_ID);
        RESTAURANT_REST.setImage(RESTAURANT_IMAGE);
        RESTAURANT_REST.setTurns(TURN_REST_LIST);

        Mockito.when(this.restaurantService.getRestaurantById(RESTAURANT_ID)).thenReturn(RESTAURANT_REST);
    }

    @Test
    public void getRestaurantByIdTest() throws BookingException {
        final BookingResponse<RestaurantRest> response = this.restaurantController.getRestaurantById(RESTAURANT_ID);
        assertEquals(response.getStatus(), SUCCESS_STATUS);
        assertEquals(response.getCode(), SUCCESS_CODE);
        assertEquals(response.getMessage(), OK);
        assertEquals(response.getData(), RESTAURANT_REST);
    }

    @Test
    public void getRestaurantsTest() throws BookingException {
    final BookingResponse<List<RestaurantRest>> response= this.restaurantController.getRestaurants();

        assertEquals(response.getStatus(), SUCCESS_STATUS);
        assertEquals(response.getCode(), SUCCESS_CODE);
        assertEquals(response.getMessage(), OK);
        assertEquals(response.getData().size(), 0);

    }
}
