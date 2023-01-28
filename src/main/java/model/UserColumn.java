package model;

public enum UserColumn {
    ID("id"),
    EMAIL("email"),
    PASSWORD("password"),
    FULLNAME("fullname"),
    PHONE("phone"),
    ADDRESS("address");

    private String value;
    UserColumn(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
