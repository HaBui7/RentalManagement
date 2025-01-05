package hoangvaha.frontend.Models;

public class ResidentialProperty {
    // Residential property class
    private final String owner;
    private final String host;
    private final String address;
    private final String availableBedrooms;
    private final String kitchenAvailability;
    private final String petFriendliness;

    public ResidentialProperty(String owner, String host, String address, String availableBedrooms, String kitchenAvailability, String petFriendliness) {
        this.owner = owner;
        this.host = host;
        this.address = address;
        this.availableBedrooms = availableBedrooms;
        this.kitchenAvailability = kitchenAvailability;
        this.petFriendliness = petFriendliness;
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

    public String getAvailableBedrooms() {
        return availableBedrooms;
    }

    public String getKitchenAvailability() {
        return kitchenAvailability;
    }

    public String getPetFriendliness() {
        return petFriendliness;
    }
}

