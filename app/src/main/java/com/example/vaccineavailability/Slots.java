package com.example.vaccineavailability;

public class Slots {
    String name;
    String vaccine;
    String available_capacity;
    String available_capacity_dose1;
    String available_capacity_dose2;
    String min_age_limit;
    String address;
    String fee;
    Slots(String name,String address,String vaccine,String available_capacity,String available_capacity_dose1,
          String available_capacity_dose2, String min_age_limit,String fee){
        this.name=name;
        this.vaccine=vaccine;
        this.available_capacity=available_capacity;
        this.available_capacity_dose1=available_capacity_dose1;
        this.available_capacity_dose2=available_capacity_dose2;
        this.min_age_limit=min_age_limit;
        this.address=address;
        this.fee=fee;
    }
}
