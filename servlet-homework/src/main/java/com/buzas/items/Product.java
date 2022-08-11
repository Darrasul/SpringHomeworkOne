package com.buzas.items;

public class Product {
    private Long id;
    private String title;
    private String cost;

    public Product(Long id, String title, String cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public Product(Long id, String title, int cost) {
        this.id = id;
        this.title = title;
        this.cost = cost + " rubles";
    }

    public Product(String title, String cost) {
        this.title = title;
        this.cost = cost;
    }

    public Product(String title, int cost) {
        this.title = title;
        this.cost = cost + " rubles";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
