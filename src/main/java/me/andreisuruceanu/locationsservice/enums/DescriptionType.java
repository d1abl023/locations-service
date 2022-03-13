package me.andreisuruceanu.locationsservice.enums;

public enum DescriptionType {
    SHORT("SHORT"),
    ADVANCED("ADVANCED"),
    FULL("FULL");

    private String description;

    DescriptionType(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
