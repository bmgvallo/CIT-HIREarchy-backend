package com.cithirearchy.cithirearchy.service;

import com.cithirearchy.cithirearchy.entity.Company;
import com.cithirearchy.cithirearchy.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    
    @Autowired
    private CompanyRepository companyRepository;

    public Company registerCompany(Company company) {
        return companyRepository.save(company);
    }

    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company updateCompanyStatus(Long id, String status) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            company.setCompanyStatus(status);
            return companyRepository.save(company);
        }
        return null;
    }

    public Optional<Company> loginCompany(String email, String password) {
        Optional<Company> company = companyRepository.findByEmail(email);
        if (company.isPresent()) {
            return company;
        }
        return Optional.empty();
    }
    
    public List<Company> getPendingCompanies() {
        return companyRepository.findByCompanyStatus("Pending");
    }
    
    public List<Company> getApprovedCompanies() {
        return companyRepository.findByCompanyStatus("Approved");
    }
    
    public List<Company> getCompaniesByCoordinator(Long coordinatorId) {
        return companyRepository.findByCoordinatorId(coordinatorId);
    }
}