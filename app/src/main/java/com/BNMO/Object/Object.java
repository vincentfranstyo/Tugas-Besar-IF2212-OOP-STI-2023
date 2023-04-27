package com.BNMO.Object;

public abstract class Object {
    private String name;
    private String type;
    private int price;

    public Object(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toString() {
        return "Name: " + name + ", Type: " + type;
    }

    public boolean equals(Object o) {
        if (o instanceof Object) {
            Object object = (Object) o;
            return object.getName().equals(name) && object.getType().equals(type);
        }
        return false;
    }

}
