package ai.semfila.api.service;

import ai.semfila.api.DTO.company.CompanyUpdateRequest;
import ai.semfila.api.model.Company;
import ai.semfila.api.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CompanyService {
    private CompanyRepository repository;

    public Page<Company> findAll(Pageable pageable){
        return  this.repository.findAll(pageable);
    }


    public Company save(Company company) {
       return this.repository.save(company);
    }

    public Optional<Company> findById(UUID id) {
        return this.repository.findById(id);
    }

    public Optional<Company> findByCnpj(String cnpj) {
        return this.repository.findByCnpj(cnpj);
    }

    @Transactional
    public Optional<Company> update(UUID id, CompanyUpdateRequest companyUpdateRequest) {
        var company = this.repository.findById(id);
        if(company.isEmpty()){
            return company;
        }
        company.get().update(companyUpdateRequest);
        return company;
    }

    public void delete(UUID id) {
        this.repository.delete(id);
    }
}
