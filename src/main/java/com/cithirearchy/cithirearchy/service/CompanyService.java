package com.cithirearchy.cithirearchy.service;

import com.cithirearchy.cithirearchy.entity.Company;
import com.cithirearchy.cithirearchy.entity.InternshipListing;
import com.cithirearchy.cithirearchy.repository.CompanyRepository;
import com.cithirearchy.cithirearchy.repository.CoordinatorRepository;
import com.cithirearchy.cithirearchy.repository.InternshipListingRepository;
import com.cithirearchy.cithirearchy.util.DepartmentCourseMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CoordinatorRepository coordinatorRepository;
    
    @Autowired
    private InternshipListingRepository listingRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<InternshipListing> getListingsForCoordinatorDepartment(String department) {
        // Get all courses in this department
        List<String> departmentCourses = DepartmentCourseMapper.getCoursesForDepartment(department);
        
        // Find listings that have any of these courses
        List<InternshipListing> allListings = listingRepository.findAll();
        
        return allListings.stream()
            .filter(listing -> listing.getCourses() != null)
            .filter(listing -> listing.getCourses().stream()
                .anyMatch(course -> DepartmentCourseMapper.isCourseInDepartment(course, department)))
            .collect(Collectors.toList());
    }

    public List<InternshipListing> getPendingListingsForDepartment(String department) {
        List<InternshipListing> departmentListings = getListingsForCoordinatorDepartment(department);
        return departmentListings.stream()
            .filter(listing -> "pending".equals(listing.getStatus()))
            .collect(Collectors.toList());
    }

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
}