/*Student Name : S.W.Ronath Tharana Wijayin
  UoW Number : w1986580
  IIT Number : 20221889*/

package FoodCenter;

public class Customer {

    private String firstName;
    private String secondName;
    private int burgersNumber;

    public Customer(String firstName, String secondName, int burgersNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.burgersNumber = burgersNumber;
    }

    // Get the customer first name.
    public String getFirstName() {

        return firstName;
    }

    // Get the customer second name.
    public String getSecondName() {

        return secondName;
    }

    // Get the number of burgers customer required.
    public int getBurgersNumber() {

        return burgersNumber;
    }
}