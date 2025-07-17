package com.pt.ada.prueba.services;

import com.pt.ada.prueba.domain.Company;

import java.util.List;
import java.util.Optional;

public interface ICompanyService {
    List<Company> getAllCompanies();
    Optional<Company> getCompanyById(Long id);
    Optional<Company> createCompany(Company company);
    Optional<Company> updateCompany(Company company);
    Boolean deleteCompany(Long id);
}
