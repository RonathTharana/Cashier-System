/*Student Name : S.W.Ronath Tharana Wijayin
  UoW Number : w1986580
  IIT Number : 20221889*/

package FoodCenter;

import java.util.ArrayList;

public class FoodQueue {

    private int maxQueueSize;
    private ArrayList<Customer> customers;

    public FoodQueue (int maxQueueSize) {

        this.maxQueueSize = maxQueueSize;
        this.customers = new ArrayList<>(maxQueueSize);
    }

    public int getMaxQueueSize() {

        return maxQueueSize;
    }

    // Get the selected arraylist.
    public ArrayList<Customer> getCustomers() {

        return customers;
    }
}
