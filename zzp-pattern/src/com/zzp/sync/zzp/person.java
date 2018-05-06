package com.zzp.sync.zzp;

public abstract class person {
    private boolean baole;

    private String name;

    public boolean isBaole() {
        return baole;
    }

    public void setBaole(boolean baole) {
        this.baole = baole;
    }

    public abstract void setName();

    public abstract void getName();
}
