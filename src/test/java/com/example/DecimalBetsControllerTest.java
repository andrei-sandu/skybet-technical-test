package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DecimalBetsControllerTest {

    private static final HttpStatus ERROR_STATUS = HttpStatus.I_AM_A_TEAPOT;
    private static final String VALID_JSON_RESPONSE = "valid json";
    @Mock
    private RestTemplate mockTemplate;
    @Mock
    private ObjectMapper mockMapper;
    @Mock
    private ResponseEntity responseEntity;

    @InjectMocks
    private DecimalBetsController controller;
    private FractionalBet fractionalBet;
    private DecimalBet mockedDecimalBet;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAvailableHandlesBetServiceErrors() throws Exception {
        when(responseEntity.getStatusCode()).thenReturn(ERROR_STATUS);
        setupMockedGetAction();

        assertThat(controller.getAvailable().getStatusCode(), is(ERROR_STATUS));
    }


    @Test
    public void testGetAvailableHandlesJacksonErrors() throws Exception {
        when(responseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        doThrow(new IOException()).when(mockMapper).readValue(anyString(), any(Class.class));
        setupMockedGetAction();

        assertThat(controller.getAvailable().getStatusCode(), is(HttpStatus.UNPROCESSABLE_ENTITY));
    }

    @Test
    public void testGetAvailableReturnsCorrectData() throws IOException {
        setupMockedBets();
        when(responseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        when(responseEntity.getBody()).thenReturn(VALID_JSON_RESPONSE);
        when(mockMapper.readValue(VALID_JSON_RESPONSE, FractionalBet[].class)).thenReturn(new FractionalBet[] {fractionalBet});
        setupMockedGetAction();

        ResponseEntity<List<DecimalBet>> result = controller.getAvailable();

        assertTrue(result.getBody().contains(mockedDecimalBet));
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void testPostBetsHandlesBetServiceErrors() throws Exception {
        when(responseEntity.getStatusCode()).thenReturn(ERROR_STATUS);
        setupMockedPostRequest();
        DecimalStake stake = mock(DecimalStake.class);
        assertThat(controller.postBets(stake).getStatusCode(), is(ERROR_STATUS));
    }

    @Test
    public void testPostBetsHandlesJacksonErrors() throws Exception {
        when(responseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        doThrow(new IOException()).when(mockMapper).readValue(anyString(), any(Class.class));
        setupMockedPostRequest();
        DecimalStake stake = mock(DecimalStake.class);

        assertThat(controller.postBets(stake).getStatusCode(), is(HttpStatus.UNPROCESSABLE_ENTITY));
    }

    @Test
    public void testPostBetsReturnsCorrectData() throws IOException {
        setupMockedBets();
        when(responseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        when(responseEntity.getBody()).thenReturn(VALID_JSON_RESPONSE);
        when(mockMapper.readValue(VALID_JSON_RESPONSE, FractionalBet.class)).thenReturn(fractionalBet);
        setupMockedPostRequest();
        DecimalStake stake = mock(DecimalStake.class);
        ResponseEntity<DecimalBet> result = controller.postBets(stake);

        assertThat(result.getBody(), is(mockedDecimalBet));
        assertThat(result.getStatusCode(), is(HttpStatus.CREATED));
    }

    // HELPERS
    private void setupMockedPostRequest() {
        when(mockTemplate.postForEntity(anyString(), any(), any())).thenReturn(responseEntity);
    }

    private void setupMockedBets() {
        fractionalBet = mock(FractionalBet.class);
        mockedDecimalBet = mock(DecimalBet.class);
        when(fractionalBet.convertOdds()).thenReturn(mockedDecimalBet);
    }

    private void setupMockedGetAction() {
        when(mockTemplate.getForEntity(anyString(), any())).thenReturn(responseEntity);
    }
}
