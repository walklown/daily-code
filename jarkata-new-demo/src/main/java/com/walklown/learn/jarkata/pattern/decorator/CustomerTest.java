package com.walklown.learn.jarkata.pattern.decorator;

import com.walklown.learn.jarkata.pattern.decorator.old.CanteenService;
import com.walklown.learn.jarkata.pattern.decorator.old.CanteenServiceImpl;
import com.walklown.learn.jarkata.pattern.decorator.update.HotelService;
import com.walklown.learn.jarkata.pattern.decorator.update.HotelServiceImpl;

public class CustomerTest {
    public static void main(String[] args) {
        CanteenService canteenService = new CanteenServiceImpl();
        canteenService.meal();

        HotelService hotelService = new HotelServiceImpl(canteenService);
        hotelService.meal();
        hotelService.PutUp();
    }
}
