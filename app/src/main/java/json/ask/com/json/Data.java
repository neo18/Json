package json.ask.com.json;

import java.util.ArrayList;

/**
 * Created by Naveen Rawat on 09-03-2016.
 */
// POJO class for data parsing
public class Data {

    private Result ResultSet;

    public Result getResultSet() {
        return ResultSet;
    }

    @Override
    public String toString() {
        return "Data{" +
                "ResultSet=" + ResultSet +
                '}';
    }

    public class Result {

        private int totalResultsReturned;

        private ArrayList<Item> items;

        @Override
        public String toString() {
            return "Result{" +
                    "totalResultsReturned=" + totalResultsReturned +
                    ", items=" + items +
                    '}';
        }

        public int getTotalResultsReturned() {
            return totalResultsReturned;
        }

        public ArrayList<Item> getItems() {
            return items;
        }
    }

    public class Item {
        private String Title, Summary;
        private double Price;

        @Override
        public String toString() {
            return "Item{" +
                    "Title='" + Title + '\'' +
                    ", Summary='" + Summary + '\'' +
                    ", Price=" + Price +
                    '}';
        }

        public String getTitle() {
            return Title;
        }

        public String getSummary() {
            return Summary;
        }

        public double getPrice() {
            return Price;
        }
    }
}