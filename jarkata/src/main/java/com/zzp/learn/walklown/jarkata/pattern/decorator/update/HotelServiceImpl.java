package com.zzp.learn.walklown.jarkata.pattern.decorator.update;

import com.zzp.pattern.decorator.old.CanteenService;

public class HotelServiceImpl implements HotelService {
    private CanteenService canteenService;

    public HotelServiceImpl(CanteenService canteenService) {
        this.canteenService = canteenService;
    }

    @Override
    public void PutUp() {
        System.out.println("住宿");
    }

    @Override
    public void meal() {
        canteenService.meal();
    }
}
