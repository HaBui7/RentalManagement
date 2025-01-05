package hoangvaha.frontend.Models;

public class CommercialProperty { // Commercial property class
    private final String owner;
    private final String host;
    private final String address;
    private final String parkingSlots;
    private final String floors;

    public CommercialProperty(String owner, String host, String address, String parkingSlots, String floors) {
        this.owner = owner;
        this.host = host;
        this.address = address;
        this.parkingSlots = parkingSlots;
        this.floors = floors;
    }

    public String getOwner() {
        return owner;
    }

    public String getHost() {
        return host;
    }

    public String getAddress() {
        return address;
    }

    public String getParkingSlots() {
        return parkingSlots;
    }

    public String getFloors() {
        return floors;
    }
}

