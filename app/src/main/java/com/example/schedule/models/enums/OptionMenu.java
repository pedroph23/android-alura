package com.example.schedule.models.enums;

public enum OptionMenu {
    DELETE("Delete"),
    TEST("Test");

    private String nameOption;

    OptionMenu(String nameOption) {
        this.nameOption = nameOption;
    }

    public String getNameOption() {
        return nameOption;
    }

    public void setNameOption(String nameOption) {
        this.nameOption = nameOption;
    }
}
