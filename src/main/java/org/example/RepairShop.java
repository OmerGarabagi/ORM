package org.example;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "repair_shops")
public class RepairShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String address;
    private String phoneNumber;

    @ManyToMany
    @JoinTable(
            name = "repair_shop_owners",
            joinColumns = @JoinColumn(name = "repair_shop_id"),
            inverseJoinColumns = @JoinColumn(name = "owner_id")
    )
    private List<Owner> shopOwners;

    @ManyToMany(mappedBy = "repairShops")
    private List<Car> cars;

    public RepairShop() { }

    public RepairShop(String address, String phoneNumber) {
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Owner> getShopOwners() {
        return shopOwners;
    }

    public void setShopOwners(List<Owner> shopOwners) {
        this.shopOwners = shopOwners;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }


}
