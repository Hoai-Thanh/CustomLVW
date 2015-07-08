package com.custom.customlvw.model;

public class Product {

	private String image;
	private String productName;
    private String price;
    private String description;
    private String longitude;
    private String latitude;

	public Product(String image, String productName, String price, String description, String longitude, String latitude) {
		this.setImage(image);
		this.setProductName(productName);
        this.setPrice(price);
        this.setDescription(description);
        this.setLongitude(longitude);
        this.setLatitude(latitude);
	}

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
