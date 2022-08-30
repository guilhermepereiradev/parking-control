package com.oxd.parkingcontrol.controllers;

import com.oxd.parkingcontrol.dtos.ResponsibleDto;
import com.oxd.parkingcontrol.models.ResponsibleModel;
import com.oxd.parkingcontrol.services.ResponsibleService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/responsibles")
public class ResponsibleController {

   final ResponsibleService responsibleService;

    public ResponsibleController(ResponsibleService responsibleService) {
        this.responsibleService = responsibleService;
    }

    @PostMapping
    public ResponseEntity<Object> saveResponsible(@RequestBody @Valid ResponsibleDto responsibleDto){
        if(responsibleService.existsByApartmentAndBlock(responsibleDto.getApartment(), responsibleDto.getBlock())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Apartment or Block is already in use");
        }
        var responsibleModel = new ResponsibleModel();
        BeanUtils.copyProperties(responsibleDto, responsibleModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(responsibleService.save(responsibleModel));
    }

    @GetMapping
    public ResponseEntity<Page<ResponsibleModel>> getAllResponsibles(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pegeable){
        return ResponseEntity.status(HttpStatus.OK).body(responsibleService.findAll(pegeable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneResponsible(@PathVariable UUID id){
        Optional<ResponsibleModel> responsibleModel = responsibleService.findById(id);
        if(!responsibleModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Responsible not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(responsibleModel.get());
    }
    @GetMapping("/{email}/email")
    public ResponseEntity<Object> getOneResponsibleByEmail(@PathVariable String email){
        Optional<ResponsibleModel> responsibleModel = responsibleService.findByEmail(email);
        if(!responsibleModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Responsible not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(responsibleModel.get());
    }

    @GetMapping("/address")
    public ResponseEntity<Object> getOneByApartmentAndBlock(@RequestParam("apartment") String apartment, @RequestParam("block") String block){
        Optional<ResponsibleModel> responsibleModel = responsibleService.findByApartmentAndBlock(apartment, block);
        if(!responsibleModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Responsible not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(responsibleModel.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteResponsible(@PathVariable UUID id){
        Optional<ResponsibleModel> responsibleModel = responsibleService.findById(id);
        if(!responsibleModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Responsible not found.");
        }
        responsibleService.delete(responsibleModel.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateResponsible(@PathVariable UUID id, @RequestBody @Valid ResponsibleDto responsibleDto){
        Optional<ResponsibleModel> responsibleModelOptional = responsibleService.findById(id);
        if(!responsibleModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Responsible not found.");
        }
        var responsibleModel = new ResponsibleModel();
        BeanUtils.copyProperties(responsibleDto, responsibleModel);
        responsibleModel.setId(responsibleModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(responsibleService.save(responsibleModel));
    }
}
