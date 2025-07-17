package com.pt.ada.prueba.services.validations;

import com.pt.ada.prueba.common.exceptions.BusinessLogicException;
import com.pt.ada.prueba.domain.Company;

import java.util.Objects;

public class CompanyValidationsService {
    public static void validateCompany(Company company) {
        if(Objects.isNull(company.getNameCompany())) {
          throw new BusinessLogicException("El nombre del company no puede ser nulo");
        }
        if(Objects.isNull(company.getCodigoCompany())) {
          throw new BusinessLogicException("El codigo del company no puede ser nulo");
        }
    }

}
