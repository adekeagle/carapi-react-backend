package pl.adcom.teaithymeleafcarapi.service;

import pl.adcom.teaithymeleafcarapi.model.Car;
import pl.adcom.teaithymeleafcarapi.model.Color;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> getCars();
    Optional<Car> getCarById(long id);
    boolean addCar(Car car);
    boolean modCar(Car car);
    boolean modCarByColor(Color color, long id);
    boolean delCarById(long id);
}
