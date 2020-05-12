package pl.adcom.teaithymeleafcarapi.service;

import org.springframework.stereotype.Service;
import pl.adcom.teaithymeleafcarapi.model.Car;
import pl.adcom.teaithymeleafcarapi.model.Color;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private List<Car> carList;

    public CarServiceImpl() {
        carList = new ArrayList<>();
        carList.add(new Car(1L, "Fiat", "126p", 2000, Color.WHITE));
        carList.add(new Car(2L, "Polonez", "Caro", 2006, Color.RED));
        carList.add(new Car(3L, "Ford", "Focus", 2010, Color.BLACK));
    }

    @Override
    public List<Car> getCars() {
        return carList;
    }

    @Override
    public Optional<Car> getCarById(long id) {
        Optional<Car> findedCar = carList.stream().filter(car -> car.getId() == id).findFirst();
        return findedCar;
    }

    @Override
    public boolean addCar(Car car) {
        long lastId = 0;

        if(!carList.isEmpty()){
            lastId = carList.stream().max(Comparator.comparing(v -> v.getId())).get().getId();
            lastId += 1;
        }else{
            lastId += 1;
        }
        car.setId(lastId);

        return carList.add(car);
    }

    @Override
    public boolean modCar(Car car) {
        Optional<Car> findedCar = carList.stream().filter(carId -> carId.getId() == car.getId()).findFirst();

        if(findedCar.isPresent()){
            findedCar.get().setMark(car.getMark());
            findedCar.get().setModel(car.getModel());
            findedCar.get().setYear(car.getYear());
            findedCar.get().setColor(car.getColor());
            return true;
        }
        return false;
    }

    @Override
    public boolean modCarByColor(Color color, long id) {
        Optional<Car> findedCar = carList.stream().filter(carId -> carId.getId() == id).findFirst();

        if(findedCar.isPresent()){
            findedCar.get().setColor(color);
            return true;
        }
        return false;
    }

    @Override
    public boolean delCarById(long id) {
        Optional<Car> delCar = carList.stream().filter(carId -> carId.getId() == id).findFirst();

        if(delCar.isPresent()){
            carList.remove(delCar.get());
            return true;
        }
        return false;
    }
}
