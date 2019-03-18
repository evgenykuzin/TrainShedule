import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TrainShedule {
    private String departureStation;
    private List<Train> trainsList;

    TrainShedule(String departureStation) {
        this.departureStation = departureStation;
        trainsList = new ArrayList<Train>();
    }


    public void addTrain(Train train) {
        if (trainsList.contains(train))
            throw new IllegalArgumentException("Не удалось добавить поезд. Такой поезд уже существует!");
        train.setDepartureStation(departureStation);
        trainsList.add(train);
    }

    public void deleteTrain(String name) {
        if (trainsList.contains(getTrain(name)))
            throw new IllegalArgumentException("Не удалось удалить поезд. Такого поезда не существует!");
        Train train = getTrain(name);
        for (int i = 0; i < trainsList.size(); i++) {
            if (trainsList.get(i).equals(train)) {
                trainsList.remove(i);
            }
        }

    }


    public Train getTrain(int index) {
        try {
            return trainsList.get(index);
        } catch (Exception e) {
            throw new IllegalArgumentException("Такого поезда не существует!");
        }
    }

    public Train getTrain(String name) {
        try {
            Train t = new Train();
            for (int i = 0; i < trainsList.size(); i++) {
                if (name.equals(trainsList.get(i).name)) {
                    t = trainsList.get(i);
                }
            }
            return t;
        } catch (Exception e) {
            throw new IllegalArgumentException("Такого поезда не существует!");
        }
    }

    public String findNearestTrain(String endStation) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        try {
            Date remDate = dateFormat.parse(getTrain(0).departureTime);
            Train remTrain = getTrain(0);
            for (Train train : trainsList) {
                Date d = dateFormat.parse(train.departureTime);
                if (train.endStation.equals(endStation)) {
                    if (d.before(date) && d.after(remDate)) {
                        remDate = d;
                        remTrain = train;
                    }
                } else throw new IllegalArgumentException(
                        "Не удалось найтти ближайший поезд к данной станции. Такой станции не существует!");

            }
            return remTrain.toString();

        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Train train : trainsList) {
            output.append(train.toString()).append('\n');
        }
        return output.toString().substring(0, output.toString().lastIndexOf("\n"));
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!obj.equals(this)) return false;
        if (obj.getClass() != this.getClass()) return false;
        TrainShedule t = (TrainShedule) obj;
        return t.trainsList == this.trainsList && t.departureStation.equals(this.departureStation);
    }

    @Override
    public int hashCode() {
        return 32 * (this.trainsList.hashCode() + this.departureStation.hashCode());
    }

}
