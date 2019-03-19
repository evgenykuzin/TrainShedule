import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrainSheduleTest {
    private TrainShedule trainShedule = new TrainShedule("Челябинск");

    @Before
    public void addAll() {
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
        System.out.println(trainShedule.toString());
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
                        "[ грузоперевозки-3, 06:30, Лондон ]\n", trainShedule.toString());
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
                        "[ грузоперевозки-3, 06:30, Лондон ]\n", trainShedule.toString());
        try {
            trainShedule.deleteTrain("миг");
        } catch (IllegalArgumentException iae) {
            assertEquals("Не удалось удалить поезд. Такого поезда не существует!", iae.getMessage());
        }
        try {
            trainShedule.addTrain(new Train("голубой вагон", "13:10", "Сочи"));
        } catch (Exception e) {
            assertEquals("Не удалось добавить поезд. Такой поезд уже существует!", e.getMessage());
        }


    }

    @Test
    public void addOrDeleteMidStationTest() {
        Train testTrain = trainShedule.getTrain("сапсан-1");
        testTrain.addMidStation("Самара");
        testTrain.addMidStation("Анапа");
        testTrain.addMidStation("Краснодар");
        testTrain.addMidStation("Туапсе");
        assertEquals("[Челябинск, Самара, Анапа, Краснодар, Туапсе, Сочи]", testTrain.getMidStations().toString());
        testTrain.deleteMidStation("Самара");
        assertEquals("[Челябинск, Анапа, Краснодар, Туапсе, Сочи]", testTrain.getMidStations().toString());
        try {
            testTrain.addMidStation("Самара");
        } catch (Exception e) {
            assertEquals("Не удалось добавить станцию. Такая станция уже существует!", e.getMessage());
        }
        try {
            testTrain.deleteMidStation("110101010");
        } catch (Exception e) {
            assertEquals("Не удалось удалить станицю. Такой станции не существует!", e.getMessage());
        }
    }

    @Test
    public void findNearestTrainTest() {
        String nearestTrain = trainShedule.findNearestTrain("Москва");
        assertEquals("[ миг, 20:30, Москва ]", nearestTrain);
        System.out.println(nearestTrain);
        try {
            trainShedule.findNearestTrain("10101010");
        } catch (IllegalArgumentException iae) {
            assertEquals(
                    "Не удалось найти ближайший поезд к данной станции. " +
                            "Такой станции не существует!", iae.getMessage());

        }
    }


}
