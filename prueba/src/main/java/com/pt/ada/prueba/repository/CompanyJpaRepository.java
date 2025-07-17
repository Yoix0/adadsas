package com.pt.ada.prueba.repository;

import com.pt.ada.prueba.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyJpaRepository extends JpaRepository<Company, Long> {
   List<Company> findAllByOrderByNameCompanyAsc();
}
