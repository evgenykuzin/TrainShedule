import java.util.ArrayList;
import java.util.List;

public class Train {
    String name;
    String departureTime;
    String endStation;
    List<String> midStations;
    Train(String name, String departureTime, String endStation){
        this.name = name;
        this.departureTime = departureTime;
        this.endStation = endStation;
        this.midStations = new ArrayList<String>();
        this.midStations.add(midStations.size(), endStation);
    }
    void setDepartureStation(String name){
        this.midStations.add(0, name);
    }
//midStation - промежуточная станция
    public void addMidStation(String midName) {
        midStations.add(midStations.size() - 1, midName);
    }

    public void deleteMidStation(String midName) {
        midStations.remove(midName);
    }

    @Override
    public String toString() {
        return "[ " + name + ", " + departureTime + ", " + endStation + " ]";
    }


    public boolean equals(Train t) {
        return this.name.equals(t.name) && this.departureTime.equals(t.departureTime)
                && this.endStation.equals(t.endStation);
    }
}
