package hu.dornyayse.liveresultat_viewer.data.model;

public enum Status {
    OK(0), DNS(1), DNF(2), MP(3), DSQ(4), OT(5), NSY(9), NSY2(10), WO(11), MU(12);

    private int value;

    Status(int _value) {
        value = _value;
    }

    public int getValue() {
        return value;
    }
}
