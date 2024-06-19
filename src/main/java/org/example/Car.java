package org.example;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String licensePlate;
    private double price;

    @Column(name = "manufacturing_year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private Picture picture;

    @ManyToMany
    @JoinTable(
            name = "car_repair_shops",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "repair_shop_id")
    )
    private List<RepairShop> repairShops;

    public Car() { }

    public Car(String licensePlate, double price, int year, Owner owner) {
        this.licensePlate = licensePlate;
        this.price = price;
        this.year = year;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public List<RepairShop> getRepairShops() {
        return repairShops;
    }

    public void setRepairShops(List<RepairShop> repairShops) {
        this.repairShops = repairShops;
    }
}
