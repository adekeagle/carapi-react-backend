package pl.adcom.teaithymeleafcarapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.adcom.teaithymeleafcarapi.model.Car;
import pl.adcom.teaithymeleafcarapi.model.Color;
import pl.adcom.teaithymeleafcarapi.service.CarService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
@CrossOrigin
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAllCars(){
        return carService.getCars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable long id){
        Optional<Car> carById = carService.getCarById(id);

        if(carById.isPresent()){
            return new ResponseEntity<>(carById.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public String addNewCar(@RequestBody Car car){
        boolean carAdded = carService.addCar(car);

        if(carAdded){
            return "Pojazd został dodany";
        }
        return "Pojazd nie został dodany";
    }

    @PutMapping
    public String modCar(@RequestBody Car car){
        boolean modCar = carService.modCar(car);

        if(modCar){
            return "Pojazd został zmieniony";
        }

        return "Pojazd nie został zmieniony";
    }

    @PatchMapping("/{id}")
    public String modCarByColor(@PathVariable long id, @RequestParam Color color){
        boolean modColor = carService.modCarByColor(color, id);

        if(modColor){
            return "Kolor pojazdu został zmodyfikowany";
        }
        return "Kolor pojazdu nie został zmodyfikowany";
    }

    @DeleteMapping("/{id}")
    public String delCarById(@PathVariable long id){
        boolean delCar = carService.delCarById(id);

        if(delCar){
            return "Pojazd został usunięty";
        }
        return "Pojazd nie został usunięty";
    }
}
