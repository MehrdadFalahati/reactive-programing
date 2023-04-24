package com.github.mehrdadfalahati.reactiveprograming.bootstrap;

import com.github.mehrdadfalahati.reactiveprograming.domain.Category;
import com.github.mehrdadfalahati.reactiveprograming.domain.Vendor;
import com.github.mehrdadfalahati.reactiveprograming.repositories.CategoryRepository;
import com.github.mehrdadfalahati.reactiveprograming.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class Bootstrap implements CommandLineRunner {
    private final VendorRepository vendorRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (isCategoryEmpty()) {
            log.info("#### LOADING DATA ON BOOTSTRAP ####");
            saveCategories();
            log.info("Loading Category data: {}", categoryRepository.count().block());
            saveVendors();
            log.info("Loading Vendor data: {}", vendorRepository.count().block());
        }
    }

    private void saveVendors() {
        saveVendor("Mehrdad", "Falahati");

        saveVendor("Mehdi", "Falahati");

        saveVendor("Hadi", "Falahati");

        saveVendor("Erfan", "Goldozian");
    }

    private void saveVendor(String first, String last) {
        vendorRepository.save(Vendor.builder()
                .firstName(first)
                .lastName(last)
                .build()).block();
    }

    private void saveCategories() {
        saveCategory("Nuts");

        saveCategory("Fruits");

        saveCategory("Beards");

        saveCategory("Meats");

        saveCategory("Eggs");
    }

    private void saveCategory(String desc) {
        categoryRepository.save(Category.builder()
                .description(desc)
                .build()).block();
    }

    private boolean isCategoryEmpty() {
        return categoryRepository.count().block() == 0;
    }
}
