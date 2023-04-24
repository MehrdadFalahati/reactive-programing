package com.github.mehrdadfalahati.reactiveprograming.domain.services;

import com.github.mehrdadfalahati.reactiveprograming.domain.Vendor;
import com.github.mehrdadfalahati.reactiveprograming.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class VendorService {
    private final VendorRepository repository;

    public Flux<Vendor> vendorList() {
        return repository.findAll();
    }

    public Mono<Vendor> loadById(String id) {
        return repository.findById(id);
    }
}
