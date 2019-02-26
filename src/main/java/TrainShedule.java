import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TrainShedule {
private String departureStation;
    TrainShedule(String departureStation){
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

    List<Train> trainsList = new ArrayList<Train>();

    public void addTrain(Train train) {
        train.setDepartureStation(departureStation);
        trainsList.add(train);
    }

    public void deleteTrain(String name) {
        Train train = getTrain(name);
        for (int i = 0; i < trainsList.size(); i++) {
            if (trainsList.get(i).equals(train)) {
                trainsList.remove(i);
            }
        }

    }


    public Train getTrain(int index) {
        return trainsList.get(index);
    }
    public Train getTrain(String name){
        Train nullTrain = new Train("","","");
        for (int i = 0; i < trainsList.size(); i++){
            if (name.equals(trainsList.get(i).name)){
                return trainsList.get(i);
            }
        }
        return nullTrain;
    }

    public List getMidStationsOf(Train train) {
        return train.midStations;
    }

    public String findNearestTrain(String endStation) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        try {
            Date remDate = dateFormat.parse(getTrain(0).departureTime);
            Train remTrain = getTrain(0);
            for (Train train : trainsList) {
                //  if (train.departureTime <= realDate.toString())
                Date d = dateFormat.parse(train.departureTime);
                if (d.before(date) && d.after(remDate) && train.endStation.equals(endStation)){
                    remDate = d;
                    remTrain = train;
                }

            }
            return remTrain.toString();

        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
