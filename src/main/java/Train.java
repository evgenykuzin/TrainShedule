import java.util.ArrayList;
import java.util.List;

public class Train {
    private String name;
    private String getDepartureTime;
    private String endStation;
    private List<String> midStations;

    Train(String name, String departureTime, String endStation) {
        this.name = name;
        this.getDepartureTime = departureTime;
        this.endStation = endStation;
        this.midStations = new ArrayList<String>();
        this.midStations.add(midStations.size(), endStation);
    }

    void setDepartureStation(String name) {
        this.midStations.add(0, name);
    }

    //midStation - промежуточная станция
    public void addMidStation(String midName) {
        if (midStations.contains(midName))
            throw new IllegalArgumentException("Не удалось добавить станцию. Такая станция уже существует!");
        midStations.add(midStations.size() - 1, midName);
    }

    public void deleteMidStation(String midName) {
        if (!midStations.contains(midName))
            throw new IllegalArgumentException("Не удалось удалить станицю. Такой станции не существует!");
        midStations.remove(midName);
    }

    public String getName(){
        return this.name;
    }

    public String getDepartureTime(){
        return this.getDepartureTime;
    }

    public String getEndStation(){
        return this.endStation;
    }

    public List getMidStations(){
        return this.midStations;
    }

    @Override
    public String toString() {
        return "[ " + name + ", " + getDepartureTime + ", " + endStation + " ]";
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (obj != this && obj.getClass() != this.getClass()) return false;
        Train t = (Train) obj;
        return this.name.equals(t.name) && this.getDepartureTime.equals(t.getDepartureTime)
                && this.endStation.equals(t.endStation) && this.midStations.equals(t.midStations);
    }

    @Override
    public int hashCode() {
        return 64 * (this.midStations.hashCode() +
                this.name.hashCode() -
                this.name.hashCode() /
                        this.getDepartureTime.hashCode());
    }
}
