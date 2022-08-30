package com.oxd.parkingcontrol.controllers;

import com.oxd.parkingcontrol.dtos.CarDto;
import com.oxd.parkingcontrol.models.CarModel;
import com.oxd.parkingcontrol.services.CarService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/cars")
public class CarController {

    final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/{id}/responsible")
    public ResponseEntity<Object> saveCar(@RequestBody @Valid CarDto carDto, @PathVariable UUID id){
        if(carService.existsByLicensePlate(carDto.getLicensePlate())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate is already in use");
        }
        var carModel = new CarModel();
        BeanUtils.copyProperties(carDto, carModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.save(carModel, id));
    }

    @GetMapping
    public ResponseEntity<List<CarModel>> getAllCars(){
        return ResponseEntity.status(HttpStatus.OK).body(carService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCar(@PathVariable UUID id){
        Optional<CarModel> carModel = carService.findById(id);
        if(!carModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(carModel.get());
    }

    @GetMapping("/{licensePlate}/licensePlate")
    public ResponseEntity<Object> getOneCarByLicensePlate(@PathVariable String licensePlate){
        Optional<CarModel> carModel =carService.findByLicensePlate(licensePlate);
        if(!carModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(carModel.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable UUID id){
        Optional<CarModel> carModel = carService.findById(id);
        if(!carModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Responsible not found.");
        }
        carService.delete(carModel.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable UUID id, @RequestBody @Valid CarDto carDto){
        Optional<CarModel> carModelOptional = carService.findById(id);
        if(!carModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found.");
        }
        var carModel = new CarModel();
        BeanUtils.copyProperties(carDto, carModel);
        carModel.setId(carModelOptional.get().getId());
        carModel.setResponsible(carModelOptional.get().getResponsible());
        return ResponseEntity.status(HttpStatus.OK).body(carService.update(carModel));
    }

}
