package com.oxd.parkingcontrol.services;

import com.oxd.parkingcontrol.models.ResponsibleModel;
import com.oxd.parkingcontrol.repositories.ResponsibleRepository;
import org.springframework.stereotype.Service;

@Service
public class ResponsibleService {

    final ResponsibleRepository responsibleRepository;

    public ResponsibleService(ResponsibleRepository responsibleRepository) {
        this.responsibleRepository = responsibleRepository;
    }

}
