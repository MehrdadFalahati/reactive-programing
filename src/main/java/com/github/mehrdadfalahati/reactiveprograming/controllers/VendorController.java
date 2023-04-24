package com.github.mehrdadfalahati.reactiveprograming.controllers;

import com.github.mehrdadfalahati.reactiveprograming.domain.Vendor;
import com.github.mehrdadfalahati.reactiveprograming.domain.services.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class VendorController {
    private final VendorService service;

    @GetMapping("/vendors")
    public ResponseEntity<Flux<Vendor>> list() {
        return ResponseEntity.ok(service.vendorList());
    }

    @GetMapping("vendors/{id}")
    public ResponseEntity<Mono<Vendor>> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.loadById(id));
    }
}
