package common.utils;

public enum User {
    NAME("TestUser_1"),
    PASSWORD("TestPassw0rd@123!$"),
    EMAIL("automation_test+1609707521@gmail.com");

    private final String value;

    User(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
