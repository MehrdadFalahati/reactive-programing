package com.github.mehrdadfalahati.reactiveprograming.repositories;

import com.github.mehrdadfalahati.reactiveprograming.domain.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface VendorRepository extends ReactiveMongoRepository<Vendor, String> {
}
