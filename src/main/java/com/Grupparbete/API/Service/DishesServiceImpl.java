package com.Grupparbete.API.Service;


import com.Grupparbete.API.DAO.DishesRepository;
import com.Grupparbete.API.Entities.Dishes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishesServiceImpl implements DishesService {
    private DishesRepository dishesRepository;
    private CurrencyConverter currencyConverter;

    @Autowired
    public DishesServiceImpl(DishesRepository dishesRepository, CurrencyConverter currencyConverter) {
        this.dishesRepository = dishesRepository;
        this.currencyConverter = currencyConverter;
    }

    @Override
    public Dishes addDish(Dishes dishes) {
        return dishesRepository.save(dishes);
    }

    @Override
    public List<Dishes> findAllDishes() {
        List<Dishes> dishes = dishesRepository.findAll();
        dishes.forEach(dish -> {
            double priceInSEK = dish.getSekPrice();
            double exchangeRate = currencyConverter.getSEKToYenExchangeRate();
            double priceInYEN = priceInSEK * exchangeRate;
            int priceInYENRoundedUp = (int) Math.ceil(priceInYEN);
            dish.setYenPrice(priceInYENRoundedUp);
        });

        return dishes;
    }


    @Override
    public Dishes findDishById(int id) {
        return dishesRepository.findById(id).orElse(null);
    }

    @Override
    public Dishes updateDish(Dishes dishes, int id) {
        if (dishesRepository.existsById(id)) {
            dishes.setId(id);
            return dishesRepository.save(dishes);
        } else {
            return null;
        }
    }

    @Override
    public void deleteDish(int id) {
        dishesRepository.deleteById(id);
    }
}
