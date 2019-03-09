import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrainSheduleTest {
    private TrainShedule trainShedule = new TrainShedule("Челябинск");

    void addAll() {
        trainShedule.addTrain(new Train("голубой вагон", "13:10", "Сочи"));
        trainShedule.addTrain(new Train("поезд", "15:00", "Сочи"));
        trainShedule.addTrain(new Train("сапсан-1", "22:00", "Сочи"));
        trainShedule.addTrain(new Train("сапсан-2", "00:20", "Москва"));
        trainShedule.addTrain(new Train("миг", "20:30", "Москва"));
        trainShedule.addTrain(new Train("ржд-1", "18:40", "Москва"));
        trainShedule.addTrain(new Train("ржд-2", "16:00", "Лененград"));
        trainShedule.addTrain(new Train("грузоперевозки-1", "00:00", "Грозный"));
        trainShedule.addTrain(new Train("грузоперевозки-2", "12:00", "Грозный"));
        trainShedule.addTrain(new Train("грузоперевозки-3", "06:30", "Лондон"));
    }

    @Test
    public void addOrDeleteTrainTest() {
        addAll();
        assertEquals(
                "[ голубой вагон, 13:10, Сочи ]\n" +
                        "[ поезд, 15:00, Сочи ]\n" +
                        "[ сапсан-1, 22:00, Сочи ]\n" +
                        "[ сапсан-2, 00:20, Москва ]\n" +
                        "[ миг, 20:30, Москва ]\n" +
                        "[ ржд-1, 18:40, Москва ]\n" +
                        "[ ржд-2, 16:00, Лененград ]\n" +
                        "[ грузоперевозки-1, 00:00, Грозный ]\n" +
                        "[ грузоперевозки-2, 12:00, Грозный ]\n" +
                        "[ грузоперевозки-3, 06:30, Лондон ]", trainShedule.toString());
        trainShedule.deleteTrain("миг");
        trainShedule.deleteTrain("поезд");
        assertEquals(
                "[ голубой вагон, 13:10, Сочи ]\n" +
                        "[ сапсан-1, 22:00, Сочи ]\n" +
                        "[ сапсан-2, 00:20, Москва ]\n" +
                        "[ ржд-1, 18:40, Москва ]\n" +
                        "[ ржд-2, 16:00, Лененград ]\n" +
                        "[ грузоперевозки-1, 00:00, Грозный ]\n" +
                        "[ грузоперевозки-2, 12:00, Грозный ]\n" +
                        "[ грузоперевозки-3, 06:30, Лондон ]", trainShedule.toString());
    }

    @Test
    public void addOrDeleteMidStationTest() {
        addAll();
        Train testTrain = trainShedule.getTrain("сапсан-1");
        testTrain.addMidStation("Самара");
        testTrain.addMidStation("Анапа");
        testTrain.addMidStation("Краснодар");
        testTrain.addMidStation("Туапсе");
        assertEquals("[Челябинск, Самара, Анапа, Краснодар, Туапсе, Сочи]", testTrain.midStations.toString());
        testTrain.deleteMidStation("Самара");
        assertEquals("[Челябинск, Анапа, Краснодар, Туапсе, Сочи]", testTrain.midStations.toString());
    }

    @Test
    public void findNearestTrainTest() {
        addAll();
        String date = "18:00";
        String nearestTrain1 = trainShedule.findNearestTrain("Москва", date);

        assertEquals("[ ржд-1, 18:40, Москва ]", nearestTrain1);
        System.out.println(nearestTrain1);
        String nearesttrain2 = trainShedule.findNearestTrain("Сочи", "14:26");
        assertEquals("[ поезд, 15:00, Сочи ]", nearesttrain2);
        System.out.println(nearesttrain2);
    }


}
