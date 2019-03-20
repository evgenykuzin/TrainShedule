import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TrainShedule {
    private String departureStation;
    private Map<String, Train> trainsMap;
    TrainShedule(String departureStation) {
        this.departureStation = departureStation;
        trainsMap = new LinkedHashMap<>();
    }


    public void addTrain(Train train) {
        if (trainsMap.containsKey(train.getName()))
            throw new IllegalArgumentException("Не удалось добавить поезд. Такой поезд уже существует!");
        train.setDepartureStation(departureStation);
        trainsMap.put(train.getName(), train);
    }

    public void deleteTrain(String name) {
        if (!trainsMap.containsKey(name))
            throw new IllegalArgumentException("Не удалось удалить поезд. Такого поезда не существует!");
        trainsMap.remove(name);
    }

    public Train getTrain(String name) {
        if (!trainsMap.containsKey(name))
            throw new IllegalArgumentException("Такого поезда не существует!");
        return trainsMap.get(name);
    }

    public String findNearestTrain(String endStation) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        try {
            Map.Entry<String,Train> entry = trainsMap.entrySet().iterator().next();
            String key= entry.getKey();
            Train remTrain = getTrain(key);
            Date remDate = dateFormat.parse(remTrain.getDepartureTime());

            for (Train train : trainsMap.values()) {
                Date d = dateFormat.parse(train.getDepartureTime());
                if (train.getEndStation().equals(endStation)) {
                    if (d.before(date) && d.after(remDate)) {
                        remDate = d;
                        remTrain = train;
                    }
                }
            }
            return remTrain.toString();

        } catch (Exception e) {
            throw new IllegalArgumentException
                    ("Не удалось найти ближайший поезд к данной станции. Такой станции не существует!");
        }
    }

    @Override
    public String toString() {
        Stream<Train> stream = trainsMap.values().stream();
        return stream.map(train -> train.toString() + "\n").collect(Collectors.joining());
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!obj.equals(this)) return false;
        if (obj.getClass() != this.getClass()) return false;
        TrainShedule t = (TrainShedule) obj;
        return t.trainsMap == this.trainsMap && t.departureStation.equals(this.departureStation);
    }

    @Override
    public int hashCode() {
        return 32 * (this.trainsMap.hashCode() + this.departureStation.hashCode());
    }

}
