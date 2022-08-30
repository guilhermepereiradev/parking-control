package com.oxd.parkingcontrol.services;

import com.oxd.parkingcontrol.models.CarModel;
import com.oxd.parkingcontrol.models.ResponsibleModel;
import com.oxd.parkingcontrol.repositories.CarRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarService {

    final CarRepository carRepository;
    final ResponsibleService responsibleService;

    public CarService(CarRepository carRepository, ResponsibleService responsibleService) {
        this.carRepository = carRepository;
        this.responsibleService = responsibleService;
    }

    @Transactional
    public CarModel save(CarModel carModel, UUID id){
        Optional<ResponsibleModel> responsibleModel = responsibleService.findById(id);
        responsibleModel.get().getCars().add(carModel);
        carModel.setResponsible(responsibleModel.get());
        return carRepository.save(carModel);
    }

    @Transactional
    public CarModel update(CarModel carModel){
        return carRepository.save(carModel);
    }

    public boolean existsByLicensePlate(String licensePlate){
        return carRepository.existsByLicensePlate(licensePlate);
    }
    public Optional<CarModel> findById(UUID id){
        return carRepository.findById(id);
    }

    public Optional<CarModel> findByLicensePlate(String licensePlate){ return carRepository.findByLicensePlate(licensePlate);}
    public List<CarModel> findAll() {
        return carRepository.findAll();
    }

    public void delete(CarModel carModel) {
        carRepository.delete(carModel);
    }
}
