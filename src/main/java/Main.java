import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        TrainShedule trainShedule = new TrainShedule("Челябинск");
        trainShedule.addTrain(new Train("голубой вагон", "13:10", "Сочи"));
        trainShedule.addTrain(new Train("поезд", "15:00", "Сочи"));
        trainShedule.addTrain(new Train("сапсан-1", "22:00", "Сочи"));
        trainShedule.addTrain(new Train("сапсан-2", "00:20", "Москва"));
        trainShedule.addTrain(new Train("миг", "20:30", "Москва"));
        trainShedule.addTrain(new Train("ржд-1", "18:40", "Москва"));
        trainShedule.addTrain(new Train("тополь-м", "16:00", "Лененград"));
        trainShedule.addTrain(new Train("грузоперевозки", "14:00", "Грозный"));
        trainShedule.addTrain(new Train("грузоперевозки", "10:00", "Грозный"));
        trainShedule.addTrain(new Train("грузоперевозки", "06:30", "Лондон"));
        System.out.println(trainShedule.toString());

        Train testTrain = trainShedule.getTrain("сапсан-1");
        testTrain.addMidStation("Самара");
        testTrain.addMidStation("Анапа");
        testTrain.addMidStation("Краснодар");
        testTrain.addMidStation("Туапсе");
System.out.println(testTrain.midStations.toString());
System.out.println(trainShedule.findNearestTrain("Москва"));

    }
}
