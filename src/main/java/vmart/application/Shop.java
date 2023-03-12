package vmart.application;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import vmart.exception.ColorNotFoundException;
import vmart.exception.GenderNotFoundException;
import vmart.exception.SizeNotFoundException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class Shop {
    Scanner scan;
    static List<Map<Object, Object>> wareHouse;
    static Map<Object, Object> item;
    public void readFiles() {
        String path1 = getPath("Adidas.csv");
        String path2 = getPath("Nike.csv");
        String path3 = getPath("Puma.csv");
        File file1 = new File(path1);
        File file2 = new File(path2);
        File file3 = new File(path3);

        try {
            Reader reader1 = new FileReader(file1);
            Reader reader2 = new FileReader(file2);
            Reader reader3 = new FileReader(file3);

            CSVParser parser1 = CSVParser.parse(reader1, CSVFormat.DEFAULT);
            CSVParser parser2 = CSVParser.parse(reader2, CSVFormat.DEFAULT);
            CSVParser parser3 = CSVParser.parse(reader3, CSVFormat.DEFAULT);

            wareHouse = new ArrayList<>();
            Map<Object, Object> data;

            for (CSVRecord record : parser1) {
                if (record.getRecordNumber() == 1) continue;
                data = addTShirt(record.values());
                wareHouse.add(data);

            }
            for (CSVRecord record : parser2) {
                if (record.getRecordNumber() == 1) continue;
                data = addTShirt(record.values());
                wareHouse.add(data);
            }
            for (CSVRecord record : parser3) {
                if (record.getRecordNumber() == 1) continue;
                data = addTShirt(record.values());
                wareHouse.add(data);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getPath(String fileName) {
        return "//Users/chetan/Desktop/Naehas/src/main/resources/vmart/" + fileName;
    }

    static Map<Object, Object> addTShirt(String[] values) {
        item = new HashMap<>();
        Info info = new Info(values[1], values[2], values[3], values[4], values[5], values[6], values[7]);
        TShirt tShirt = new TShirt(values[0], info);
        item.put(tShirt, info);
        return item;
    }

    public String printData(Object t, Object i) {
        TShirt tShirt = (TShirt) t;
        Info info = (Info) i;

        return ("\nT-Shirt Id : " + tShirt.getId()) +
        ("\nT-Shirt Name : " + info.getName()) +
        ("\nT-Shirt Color : " + info.getColor()) +
        ("\nT-Shirt Gender : " + info.getGender()) +
        ("\nT-Shirt Size : " + info.getSize()) +
        ("\nT-Shirt Price : " + info.getPrice()) +
        ("\nT-Shirt Rating : " + info.getRating()) +
        ("\nT-Shirt Availability : " + info.getAvailability());
    }

    public void preferences() {
        while (true) {
            scan = new Scanner(System.in);
            System.out.println("Enter 1 for Color");
            System.out.println("Enter 2 for Size");
            System.out.println("Enter 3 for Gender");
            System.out.println("Enter 4 for Output Preferences");
            System.out.println("Enter 5 for All T-Shirts");
            System.out.println("Enter 6 for Exit");
            System.out.print("Enter your choice : ");
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    while (true) {
                        try {
                            scan = new Scanner(System.in);
                            System.out.print("\nEnter your color: ");
                            String color = scan.next();
                            if (getTShirtByColor(color.toUpperCase())) break;
                            else throw new Exception();
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                    }
                    System.out.println();
                    break;
                case 2:
                    while (true) {
                        try {
                            scan = new Scanner(System.in);
                            System.out.print("\nEnter your size: ");
                            String size = scan.next();
                            if (getTShirtBySize(size.toUpperCase())) break;
                            else throw new Exception();
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                    }
                    System.out.println();
                    break;
                case 3:
                    while (true) {
                        try {
                            scan = new Scanner(System.in);
                            System.out.print("\nEnter your gender: ");
                            char gender = scan.next().charAt(0);
                            if (getTShirtByGender(gender)) break;
                            else throw new Exception();
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                    }
                    System.out.println();
                    break;
                case 4:
                    scan = new Scanner(System.in);
                    System.out.println("Enter 1 for sorted by price");
                    System.out.println("Enter 2 for sorted by rating");
                    System.out.println("Enter 3 for sorted by both price and rating");
                    getTShirtByOutputPreferences(scan.nextInt());
                    break;
                case 5:
                    getAllData();
                    System.out.println();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("you have entered an invalid key, Please try again.\n");
            }
        }
    }

    private boolean getTShirtByColor(String color) throws ColorNotFoundException {
        boolean flag = false;
        for (Map<Object, Object> map : wareHouse) {
            for (Map.Entry<Object, Object> pair : map.entrySet()) {
                TShirt tShirt = (TShirt) pair.getKey();
                Info info = (Info) pair.getValue();
                if (info.getColor().toUpperCase().contains(color)) {
                    System.out.println(printData(tShirt, info));
                    flag = true;
                }
            }
        }
        if (!flag) throw new ColorNotFoundException("Color not in stock.");
        return true;
    }

    private boolean getTShirtBySize(String size) throws SizeNotFoundException {
        boolean flag = false;
        for (Map<Object, Object> map : wareHouse) {
            for (Map.Entry<Object, Object> pair : map.entrySet()) {
                TShirt tShirt = (TShirt) pair.getKey();
                Info info = (Info) pair.getValue();
                if (info.getSize().toUpperCase().contains(size)) {
                    System.out.println(printData(tShirt, info));
                    flag = true;
                }
            }
        }
        if (!flag) throw new SizeNotFoundException("Size not in stock.");
        return true;
    }

    private boolean getTShirtByGender(char gender) throws GenderNotFoundException {
        boolean flag = false;
        for (Map<Object, Object> map : wareHouse) {
            for (Map.Entry<Object, Object> pair : map.entrySet()) {
                TShirt tShirt = (TShirt) pair.getKey();
                Info info = (Info) pair.getValue();
                if (info.getGender() == gender) {
                    System.out.println(printData(tShirt, info));
                    flag = true;
                }
            }
        }
        if (!flag) throw new GenderNotFoundException("Invalid Gender, Please select either 'M' for male or 'F' for female or 'U' for unisex.");
        return true;
    }

    private void getTShirtByOutputPreferences(int preference) {
        while (true) {
            scan = new Scanner(System.in);
            switch (preference) {
                case 1:
                    getSortedByPrice();
                    break;
                case 2:
                    getSortedByRating();
                    break;
                case 3:
                    getSortedByBoth();
                    break;
                default:
                    System.out.println("You have entered an invalid input, Please try again");
            }
        }
    }

    private void getSortedByPrice() {

    }

    private void getSortedByRating() {
    }

    private void getSortedByBoth() {
    }

    private void getAllData() {
        for (Map<Object, Object> map: wareHouse) {
            for (Map.Entry<Object, Object> pair: map.entrySet()) {
                TShirt tShirt = (TShirt) pair.getKey();
                Info info = (Info) pair.getValue();
                System.out.println(printData(tShirt, info));
            }
        }
    }
}


