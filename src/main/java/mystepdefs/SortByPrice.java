package mystepdefs;

public enum SortByPrice {
    Поумолчанию("101"),
    Дешевле("1"),
    Дороже("2"),
    Подате("104");

    private final String val;

    SortByPrice(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }
}





