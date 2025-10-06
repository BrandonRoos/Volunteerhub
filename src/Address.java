/**
 * An auxiliary class that represents a physical address.
 * @author Brandon Roos
 */
public class Address {
    /** The street name and number. */
    private String street;
    /** The city. */
    private String city;
    /** The state. */
    private String state;
    /** The zip code. */
    private String zipCode;

    /**
     * Constructs a new Address object.
     *
     * @param street the street name and number.
     * @param city the city.
     * @param state the state.
     * @param zipCode the zip code.
     */
    public Address(String street, String city, String state, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    /**
     * Formats the address into a single string.
     *
     * @return a formatted address string.
     */
    public String formatAddress() {
        return String.format("%s, %s, %s %s", street, city, state, zipCode);
    }

    /**
     * Gets the street name and number.
     * @return The street.
     */
    public String getStreet() { return street; }
    /**
     * Gets the city.
     * @return The city.
     */
    public String getCity() { return city; }
    /**
     * Gets the state.
     * @return The state.
     */
    public String getState() { return state; }
    /**
     * Gets the zip code.
     * @return The zip code.
     */
    public String getZipCode() { return zipCode; }
}