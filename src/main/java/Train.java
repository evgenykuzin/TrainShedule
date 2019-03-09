import java.util.ArrayList;
import java.util.List;

public class Train {
    String name;
    String departureTime;
    String endStation;
    List<String> midStations;

    Train(String name, String departureTime, String endStation) {
        this.name = name;
        this.departureTime = departureTime;
        this.endStation = endStation;
        this.midStations = new ArrayList<String>();
        this.midStations.add(midStations.size(), endStation);
    }
    Train(Train train) {
        this.endStation = train.endStation;
        this.departureTime = train.departureTime;
        this.name = train.name;
        this.midStations = new ArrayList<String>(train.midStations);
    }
    void setDepartureStation(String name) {
        this.midStations.add(0, name);
    }

    //midStation - промежуточная станция
    void addMidStation(String midName) {
        midStations.add(midStations.size() - 1, midName);
    }

    void deleteMidStation(String midName) {
        midStations.remove(midName);
    }

    List<String> getMidStations(){
        return this.midStations;
    }

    @Override
    public String toString() {
        return "[ " + name + ", " + departureTime + ", " + endStation + " ]";
    }


    boolean equals(Train t) {
        return this.name.equals(t.name) && this.departureTime.equals(t.departureTime)
                && this.endStation.equals(t.endStation);
    }
}
