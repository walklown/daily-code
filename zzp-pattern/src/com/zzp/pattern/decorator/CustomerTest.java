package com.zzp.pattern.decorator;

import com.zzp.pattern.decorator.old.CanteenService;
import com.zzp.pattern.decorator.old.CanteenServiceImpl;
import com.zzp.pattern.decorator.update.HotelService;
import com.zzp.pattern.decorator.update.HotelServiceImpl;

public class CustomerTest {
    public static void main(String[] args) {
        CanteenService canteenService = new CanteenServiceImpl();
        canteenService.meal();

        HotelService hotelService = new HotelServiceImpl(canteenService);
        hotelService.meal();
        hotelService.PutUp();
    }
}
