package model;

public abstract class RxObject {
    private String rxName;

    public RxObject(String rxName) {
        this.rxName = rxName;
    }

    public String getRxName() {
        return rxName;
    }
}
