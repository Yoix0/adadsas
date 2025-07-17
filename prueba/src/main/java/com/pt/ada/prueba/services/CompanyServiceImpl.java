package com.pt.ada.prueba.services;

import com.pt.ada.prueba.domain.Company;
import com.pt.ada.prueba.repository.CompanyJpaRepository;
import com.pt.ada.prueba.services.validations.CompanyValidationsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements ICompanyService {
    private final CompanyJpaRepository companyJpaRepository;

    @Override
    public List<Company> getAllCompanies() {
        return this.companyJpaRepository.findAllByOrderByNameCompanyAsc();
    }

    @Override
    public Optional<Company> getCompanyById(Long id) {
        return this.companyJpaRepository.findById(id);
    }

    @Override
    public Optional<Company> createCompany(Company company) {
        //validacion basica
        CompanyValidationsService.validateCompany(company);
        this.companyJpaRepository.save(company);
        return Optional.of(company);  // retornamos company con su respectivo id
    }

    @Override
    public Optional<Company> updateCompany(Company company) {
        this.companyJpaRepository.save(company);

        Optional<Company> dataToUpdated = this.companyJpaRepository.findById(company.getId());
        if (dataToUpdated.isPresent()) {
            CompanyValidationsService.validateCompany(company);
            dataToUpdated.get().setDescriptionCompany(company.getDescriptionCompany());
            dataToUpdated.get().setNameCompany(company.getNameCompany());
            this.companyJpaRepository.save(dataToUpdated.get());
        }

        return dataToUpdated;
    }

    @Override
    public Boolean deleteCompany(Long id) {
        this.companyJpaRepository.deleteById(id);
        return true;
    }


}
