package com.oxd.parkingcontrol.services;

import com.oxd.parkingcontrol.models.ResponsibleModel;
import com.oxd.parkingcontrol.repositories.ResponsibleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResponsibleService {

    final ResponsibleRepository responsibleRepository;

    public ResponsibleService(ResponsibleRepository responsibleRepository) {
        this.responsibleRepository = responsibleRepository;
    }
    @Transactional
    public ResponsibleModel save(ResponsibleModel responsibleModel) {
        return responsibleRepository.save(responsibleModel);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block){
        return responsibleRepository.existsByApartmentAndBlock(apartment, block);
    }

    public Optional<ResponsibleModel> findById(UUID id){
       return responsibleRepository.findById(id);
    }

    public Optional<ResponsibleModel> findByEmail(String email){ return responsibleRepository.findByEmail(email);}

    public Optional<ResponsibleModel> findByApartmentAndBlock(String apartment, String block){return responsibleRepository.findByApartmentAndBlock(apartment, block);}
    public Page<ResponsibleModel> findAll(Pageable pageable) {
        return responsibleRepository.findAll(pageable);
    }

    @Transactional
    public void delete(ResponsibleModel responsibleModel) {
        responsibleRepository.delete(responsibleModel);
    }
}
