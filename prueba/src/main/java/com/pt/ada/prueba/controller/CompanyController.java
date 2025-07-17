package com.pt.ada.prueba.controller;

import com.pt.ada.prueba.common.ResponseBs;
import com.pt.ada.prueba.common.UtilsBs;
import com.pt.ada.prueba.domain.Company;
import com.pt.ada.prueba.services.ICompanyService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/company")
public class CompanyController {
    private final ICompanyService companyService;

    @GetMapping("")
    public ResponseEntity<?> getCompanyById() {
        var result = companyService.getAllCompanies();
        return UtilsBs.response(Optional.of(result), "Obtener todas");
    }
    @GetMapping("/id/{idCompany}")
    public ResponseEntity<?> getCompanyById(@PathVariable Long idCompany) {
        var result = companyService.getCompanyById(idCompany);
        return UtilsBs.response(result, "Compañia localizada");
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> createCompany(@RequestBody Company company) {
        var result = companyService.createCompany(company);
        return UtilsBs.responseCreate(result, "registro");
    }

    @Transactional
    @PutMapping
    public ResponseEntity<?> updateCompany(@RequestBody Company company) {
        var result = companyService.updateCompany(company);
        return UtilsBs.responseCreate(result, "actualizacion");
    }


    @DeleteMapping("/delete//{idCompany}")
    public ResponseEntity<?> deleteCompany(@PathVariable Long idCompany) {
        var result = companyService.deleteCompany(idCompany);
        return UtilsBs.responseCreate(Optional.of(result), "Eliminacion");
    }

}
