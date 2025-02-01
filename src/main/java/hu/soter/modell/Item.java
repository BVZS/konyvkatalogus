package hu.soter.modell;

import java.util.Random;

public abstract class Item implements CatalogItem {
    public int id;
    public String title;

    public Item(String title) {
        this.id = egyediId();
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private int egyediId() {
        Random random = new Random();
        return 10000 + random.nextInt(90000);
    }

    public String getItemInfo() {
        return "ID: " + id + ", Cím: " + title;
    }

    public boolean matches(String keyword) {
        return title.toLowerCase().contains(keyword.toLowerCase());
    }

    public abstract int compareTo(Book other);
}
