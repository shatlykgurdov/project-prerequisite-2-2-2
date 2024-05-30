package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    private final List<Car> carList;

    public CarService() {
        carList = new ArrayList<>();
        carList.add(new Car("Toyota", "Camry", 2020));
        carList.add(new Car("Honda", "Civic", 2019));
        carList.add(new Car("Ford", "Fusion", 2018));
        carList.add(new Car("Chevrolet", "Malibu", 2017));
        carList.add(new Car("Nissan", "Altima", 2016));
    }

    public List<Car> getCars(int count) {
        if (count <= 0) {
            return new ArrayList<>();
        }
        if (count >= carList.size()) {
            return new ArrayList<>(carList);
        } else {
            return new ArrayList<>(carList.subList(0, count));
        }
    }
}
