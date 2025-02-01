package hu.soter.modell;

public interface CatalogItem {
    int getId();
    String getTitle();
    String getItemInfo();
    boolean matches(String keyword);
}