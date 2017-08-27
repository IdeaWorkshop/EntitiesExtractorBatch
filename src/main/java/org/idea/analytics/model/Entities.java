package org.idea.analytics.model;


import java.util.ArrayList;
import java.util.List;

public class Entities {
    private List<Entity> persons;
    private List<Entity> locations;
    private List<Entity> products;

    public Entities() {
        persons= new ArrayList<>();
        locations = new ArrayList<>();
        products = new ArrayList<>();
    }

    public List<Entity> getPersons() {
        return persons;
    }

    public void setPersons(List<Entity> persons) {
        this.persons = persons;
    }

    public List<Entity> getLocations() {
        return locations;
    }

    public void setLocations(List<Entity> locations) {
        this.locations = locations;
    }

    public List<Entity> getProducts() {
        return products;
    }

    public void setProducts(List<Entity> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("persons:");sb.append(persons);sb.append("\n");
        sb.append("products:");sb.append(products);sb.append("\n");
        sb.append("locations:");sb.append(locations);sb.append("\n");
        return sb.toString();
    }
}
