package com.github.mehrdadfalahati.reactiveprograming.controllers;

import com.github.mehrdadfalahati.reactiveprograming.domain.Vendor;
import com.github.mehrdadfalahati.reactiveprograming.domain.services.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

class VendorControllerTest {

    private WebTestClient webTestClient;
    private VendorService service;
    private VendorController controller;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(VendorService.class);
        controller = new VendorController(service);
        webTestClient = WebTestClient.bindToController(controller).build();
    }

    @Test
    public void testList() {
        BDDMockito.given(service.vendorList())
                .willReturn(Flux.just(Vendor.builder().firstName("M").lastName("F").build(),
                        Vendor.builder().firstName("H").lastName("F").build()));

        webTestClient.get().uri("/api/v1/vendors")
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);
    }

    @Test
    public void testGetById() {
        BDDMockito.given(service.loadById(anyString()))
                .willReturn(Mono.just(Vendor.builder().firstName("M").lastName("F").build()));

        webTestClient.get().uri("/api/v1/vendors/1")
                .exchange()
                .expectBody(Vendor.class);
    }
}