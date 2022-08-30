package com.oxd.parkingcontrol.controllers;

import com.oxd.parkingcontrol.configs.DateConfig;
import com.oxd.parkingcontrol.dtos.ParkingSpotDto;
import com.oxd.parkingcontrol.models.ParkingSpotModel;
import com.oxd.parkingcontrol.services.ParkingSpotService;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ReportAsSingleViolation;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    final ParkingSpotService parkingSpotService;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @PostMapping("/{id}/car")
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto, @PathVariable UUID id){
        if(parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use!");
        }
        var parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel, id));
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpotModel>> getAllParkingSpots(){
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable UUID id){
        Optional<ParkingSpotModel> parkingSpotModel = parkingSpotService.findById(id);
        if(!parkingSpotModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModel.get());
    }

    @GetMapping("/{carLicensePlate}/licensePlate")
    public ResponseEntity<Object> findByCarLicensePlate(@PathVariable String carLicensePlate){
        Optional<ParkingSpotModel> parkingSpotModel = parkingSpotService.findByCarLicensePlate(carLicensePlate);
        if(!parkingSpotModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking spot not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModel.get());
    }

    @GetMapping("/dateInterval")
    public ResponseEntity<Object> findByDateInterval(@RequestParam(value = "date1") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") Date d1, @RequestParam(value = "date2") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") Date d2){
        Optional<ParkingSpotModel> parkingSpotModel = parkingSpotService.findByDateInterval(d1, d2);
        if(!parkingSpotModel.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body("Parking spot not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModel.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable UUID id){
        Optional<ParkingSpotModel> parkingSpotModel = parkingSpotService.findById(id);
        if(!parkingSpotModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Responsible not found.");
        }
        parkingSpotService.delete(parkingSpotModel.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable UUID id, @RequestBody @Valid ParkingSpotDto parkingSpotDto){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if(!parkingSpotModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        var parkingSpot = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpot);
        parkingSpot.setId(parkingSpotModelOptional.get().getId());
        parkingSpot.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate());
        parkingSpot.setCar(parkingSpotModelOptional.get().getCar());
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.update(parkingSpot));
    }
}
