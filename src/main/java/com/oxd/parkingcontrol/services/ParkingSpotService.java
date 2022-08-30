package com.oxd.parkingcontrol.services;

import com.oxd.parkingcontrol.models.CarModel;
import com.oxd.parkingcontrol.models.ParkingSpotModel;
import com.oxd.parkingcontrol.repositories.ParkingSpotRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotService {

    final ParkingSpotRepository parkingSpotRepository;
    final CarService carService;
    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository, CarService carService) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.carService = carService;
    }
    @Transactional
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel, UUID id){
        Optional<CarModel> carModel = carService.findById(id);
        parkingSpotModel.setCar(carModel.get());
        return parkingSpotRepository.save(parkingSpotModel);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public List<ParkingSpotModel> findAll() {
        return parkingSpotRepository.findAll();
    }

    public Optional<ParkingSpotModel> findById(UUID id) {
        return parkingSpotRepository.findById(id);
    }

    public Optional<ParkingSpotModel> findByCarLicensePlate(String carLicensePlate){return parkingSpotRepository.findByCarLicensePlate(carLicensePlate);}

    public Optional<ParkingSpotModel> findByDateInterval(Date d1, Date d2){ return parkingSpotRepository.findByDateInterval(d1, d2);}
    public void delete(ParkingSpotModel parkingSpotModel) {
        parkingSpotRepository.delete(parkingSpotModel);
    }

    @Transactional
    public ParkingSpotModel update(ParkingSpotModel parkingSpotModel){
        return parkingSpotRepository.save(parkingSpotModel);
    }
}
