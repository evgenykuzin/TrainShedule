import java.util.ArrayList;
import java.util.List;

public class Train {
    String name;
    String departureTime;
    String endStation;
    List<String> midStations;

    Train(){ }
    Train(String name, String departureTime, String endStation) {
        this.name = name;
        this.departureTime = departureTime;
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

    @Override
    public String toString() {
        return "[ " + name + ", " + departureTime + ", " + endStation + " ]";
    }

    @Override
    public boolean equals(Object obj) {
        Train t = (Train) obj;
        if (t == null) return false;
        if (t != this && t.getClass() != this.getClass()) return false;

        return this.name.equals(t.name) && this.departureTime.equals(t.departureTime)
                && this.endStation.equals(t.endStation) && this.midStations.equals(t.midStations);
    }

    @Override
    public int hashCode() {
        return 64 * (this.midStations.hashCode() +
                this.name.hashCode() -
                this.name.hashCode() /
                        this.departureTime.hashCode());
    }
}
