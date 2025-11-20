package com.cithirearchy.cithirearchy.service;

import com.cithirearchy.cithirearchy.entity.Coordinator;
import com.cithirearchy.cithirearchy.repository.CoordinatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CoordinatorService {
    
    @Autowired
    private CoordinatorRepository coordinatorRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Coordinator registerCoordinator(Coordinator coordinator) {
        return coordinatorRepository.save(coordinator);
    }

    public Optional<Coordinator> getCoordinatorById(Long id) {
        return coordinatorRepository.findById(id);
    }

    public Optional<Coordinator> getCoordinatorByEmail(String email) {
        return coordinatorRepository.findByEmail(email); // Use findByEmail instead of findByCoordinatorEmail
    }

    public List<Coordinator> getAllCoordinators() {
        return coordinatorRepository.findAll();
    }

    public Coordinator updateCoordinatorProfile(Long id, Coordinator coordinatorDetails) {
        Optional<Coordinator> optionalCoordinator = coordinatorRepository.findById(id);
        if (optionalCoordinator.isPresent()) {
            Coordinator coordinator = optionalCoordinator.get();
            coordinator.setCoordinatorName(coordinatorDetails.getCoordinatorName());
            coordinator.setEmail(coordinatorDetails.getEmail());
            coordinator.setCoordinatorDepartment(coordinatorDetails.getCoordinatorDepartment());
            return coordinatorRepository.save(coordinator);
        }
        return null;
    }
    
    public Coordinator updateCoordinatorPassword(Long id, String newPassword) {
        Optional<Coordinator> optionalCoordinator = coordinatorRepository.findById(id);
        if (optionalCoordinator.isPresent()) {
            Coordinator coordinator = optionalCoordinator.get();
            coordinator.setPassword(passwordEncoder.encode(newPassword));
            return coordinatorRepository.save(coordinator);
        }
        return null;
    }
}