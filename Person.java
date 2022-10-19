

/**
 * Superclass for passenger, creates a person
 */
public class Person {

    // FIELDS
    public String firstName;
    public String lastName;
    public String address;
    public String city;
    public int zipcode;
    public int cellPhoneNum;

    // DEFAULT CONSTRUCTOR
    /**
     * Constructs new default person
     */
    public Person() {}

    // CONSTRUCTOR
    /**
     * Constructs new person
     * @param fName first name
     * @param lName last name
     * @param addy address
     * @param cityName city
     * @param zip zip
     * @param cellNum cell phone number
     */
    public Person(String fName, String lName, String addy, String cityName, int zip, int cellNum)
    {
        firstName = fName;
        lastName = lName;
        address = addy;
        city = cityName;
        zipcode = zip;
        cellPhoneNum = cellNum;
    }

    // ACCESSORS
    /**
     * Gets first name
     * @return passenger first name
     */
    public String getFirstName() { return firstName; }
    /**
     * Gets last name
     * @return passenger last name
     */
    public String getLastName() { return lastName; } 
    /**
     * Gets address
     * @return passenger address
     */
    public String getAddress() { return address; }
    /**
     * Gets city
     * @return passenger city
     */
    public String getCity() { return city; }
    /**
     * Gets zip
     * @return passenger zip
     */
    public int getZip() { return zipcode; }
    /**
     * Get cell phone number
     * @return passenger cell phone number
     */
    public int getCellPhoneNum() { return cellPhoneNum; }

    // MUTATORS
    /**
     * Set first name 
     * @param fName first name
     */
    public void setFirstName(String fName) { firstName = fName; }
    /**
     * Set last name
     * @param lName last name
     */
    public void setLastName(String lName) { lastName = lName; }
    /**
     * Set address
     * @param addy address
     */
    public void setAddress(String addy) { address = addy; }
    /**
     * Set city
     * @param cityName city 
     */
    public void setCity(String cityName) { city = cityName; }
    /**
     * Set zip 
     * @param zip zip
     */
    public void setZip(int zip) { zipcode = zip; }
    /**
     * Set cell phone number
     * @param cellNum cell phone number
     */
    public void setCellPhoneNum(int cellNum) { cellPhoneNum = cellNum; }

    public String toString() 
    {
        return "First Name = " + firstName + ", Last Name = " + lastName + ", Address = " + address 
                + ", City = " + city + ", Zip = " + zipcode + ", Cell Phone Number = " + cellPhoneNum;
    }
    
}
