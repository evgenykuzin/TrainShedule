import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TrainShedule {
    private String departureStation;

    TrainShedule(String departureStation) {
        this.departureStation = departureStation;
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
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private List<Train> trainsList = new ArrayList<Train>();

    void addTrain(Train train) {
        train.setDepartureStation(departureStation);
        trainsList.add(train);
    }

    void deleteTrain(String name) {
        Train train = getTrain(name);
        for (int i = 0; i < trainsList.size(); i++) {
            if (trainsList.get(i).equals(train)) {
                trainsList.remove(i);
            }
        }

    }


    Train getTrain(int index) {
        return trainsList.get(index);
    }

    Train getTrain(String name) {
        Train nullTrain = new Train("", "", "");
        for (int i = 0; i < trainsList.size(); i++) {
            if (name.equals(trainsList.get(i).name)) {
                return trainsList.get(i);
            }
        }
        return nullTrain;
    }

    ArrayList<Train> getTrains(String endStation) {

        ArrayList<Train> sortList = new ArrayList<Train>();
        for (Train train : trainsList) {
            if (train.endStation.equals(endStation)) {
                sortList.add(train);
            }
        }
        if (sortList.isEmpty()) {
            throw new IllegalArgumentException("нет такой станции");
        }
        return sortList;
    }

    public List getMidStationsOf(Train train) {
        return train.midStations;
    }

    String findNearestTrain(String endStation, String date) {
        ArrayList<Train> sortedTrains = getTrains(endStation);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);

        try {
            Date realDate = (Date) dateFormat.parse(date);
            Train remTrain = new Train(sortedTrains.get(0));
            Date remDate = (Date) dateFormat.parse(remTrain.departureTime);

            for (int i = 0; i < sortedTrains.size(); i++) {
                Train train = new Train(sortedTrains.get(i));
                Date d = dateFormat.parse(train.departureTime);
                if (d.before(realDate) && d.after(remDate)) {
                    remDate.setTime(d.getTime());
                    System.out.println(remTrain.toString());
                    remTrain = new Train(train);
                    System.out.println(remTrain.toString());
                }

            }
            return remTrain.toString();


        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
