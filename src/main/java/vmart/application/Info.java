package vmart.application;

public class Info {
    private String name;
    private String color;
    private char gender;
    private String size;
    private double price;
    private double rating;
    private char availability;

    public Info(String name, String color, String gender, String size, String price, String rating, String availability) {
        setName(name);
        setColor(color);
        setGender(gender.toUpperCase());
        setSize(size.toUpperCase());
        setPrice(price);
        setRating(rating);
        setAvailability(availability.toUpperCase());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender.charAt(0);
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = Double.parseDouble(price);
    }

    public double getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = Double.parseDouble(rating);
    }

    public char getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability.charAt(0);
    }
}
