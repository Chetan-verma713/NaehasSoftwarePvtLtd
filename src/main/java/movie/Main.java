package movie;


import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


class Info {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private String genre;
    private String leadStudio;
    private int audienceScorePercentile;
    private double profitability;
    private int rottenTomatoes;
    private String worldWideGross;
    private int year;

    public Info(String genre, String leadStudio, String audienceScorePercentile, String profitability, String rottenTomatoes, String worldWideGross, String year) {
        setGenre(genre);
        setLeadStudio(leadStudio);
        setAudienceScorePercentile(Integer.parseInt(audienceScorePercentile));
        setProfitability(Double.parseDouble(df.format(Double.parseDouble(profitability))));
        setRottenTomatoes(Integer.parseInt(rottenTomatoes));
        setWorldWideGross(worldWideGross);
        setYear(Integer.parseInt(year));
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLeadStudio() {
        return leadStudio;
    }

    public void setLeadStudio(String leadStudio) {
        this.leadStudio = leadStudio;
    }

    public int getAudienceScorePercentile() {
        return audienceScorePercentile;
    }

    public void setAudienceScorePercentile(int audienceScorePercentile) {
        this.audienceScorePercentile = audienceScorePercentile;
    }

    public double getProfitability() {
        return profitability;
    }

    public void setProfitability(double profitability) {
        this.profitability = profitability;
    }

    public int getRottenTomatoes() {
        return rottenTomatoes;
    }

    public void setRottenTomatoes(int rottenTomatoes) {
        this.rottenTomatoes = rottenTomatoes;
    }

    public String getWorldWideGross() {
        return worldWideGross;
    }

    public void setWorldWideGross(String worldWideGross) {
        this.worldWideGross = worldWideGross;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

class Film {
    private String filmName;
    private Info info;

    public Film(String filmName, Info info) {
        setFilmName(filmName);
        setInfo(info);
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}

class Stats {
    static Scanner scan;
    static List<Map<Object, Object>> container;
    static Map<Object, Object> map;
    static HashMap<Object, Object> insertData(String line) {
        map = new HashMap<>();
        line = line.substring(1, line.length()-1);
        String[] data = line.split(", ");
        Info info = new Info(data[1], data[2], data[3], data[4], data[5], data[6], data[7]);
        Film film = new Film(data[0], info);
        map.put(film, info);
        return (HashMap<Object, Object>) map;
    }

    static void readFile(String path) {
        try {
            Reader file = new FileReader(path);
            CSVParser parser = CSVParser.parse(file, CSVFormat.DEFAULT);
            container = new ArrayList<>();
            Map<Object, Object> data;

            for (CSVRecord record : parser) {
                if (record.getRecordNumber() == 1) continue;
                data = insertData(Arrays.toString(record.values()));
                container.add(data);
            }

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void ask() {
        System.out.println("\nEnter 1 for search by 'Genre'");
        System.out.println("Enter 2 for search by 'Lead Studio'");
        System.out.println("Enter 3 for search by 'Year'");
        System.out.println("Enter 4 for all the data");
        System.out.println("Enter 5 for exit");

        scan = new Scanner(System.in);

        try {
            System.out.print("\nEnter your choice :   ");
            operateFile(scan.nextInt());
        } catch (InputMismatchException e) {
            System.err.println("You entered an invalid input.");
        }
    }

    static void operateFile(int input) {
//        genres, studio, gross, year
        switch(input) {
            case 1:
                System.out.println("\nThanks for selecting genre");
                operateGenre();
                System.out.println("\nGenre has printed successfully\n");
                ask();
                break;
            case 2:
                System.out.println("Thanks for selecting lead studio");
                operateLeadStudio();
                System.out.println("\nLead Studio has printed successfully\n");
                ask();
                break;
            case 3:
                System.out.println("Thanks for selection year");
                operateYear();
                System.out.println("\nYear has printed successfully\n");
                ask();
                break;
            case 4:
                System.out.println("Thanks for selecting whole file");
                operateWholeData();
                System.out.println("\nWhole data has printed successfully\n");
                ask();
                break;
            case 5:
                System.out.println("Thanks for visiting us, See you again");
                System.exit(0);
            default:
                System.out.println("Please enter a valid input");
                System.out.print("\nEnter your choice :   ");
                operateFile(scan.nextInt());
        }
    }

    static  {
        readFile("/Users/chetan/IdeaProjects/Assignment/src/main/resources/movies.csv");
        System.out.println("File has been read successfully, You may start operations on file!!!");
    }

    static void printData(Object f, Object i) {
        Film film = (Film) f;
        Info info = (Info) i;
        System.out.println("Film : " +  film.getFilmName());
        System.out.println("Genre : " + info.getGenre());
        System.out.println("Lead Studio : " + info.getLeadStudio());
        System.out.println("Audience Score : " + info.getAudienceScorePercentile() + "% ");
        System.out.println("Profitability : " + info.getProfitability());
        System.out.println("Rotten Tomatoes : " + info.getRottenTomatoes() + "% ");
        System.out.println("Worldwide Gross : " + info.getWorldWideGross());
        System.out.println("Year : " + info.getYear());
        System.out.println();
    }

    static void operateGenre() {
        System.out.print("Enter the name of genre:    ");
        String str = scan.next();
        System.out.println();
        for (Map<Object, Object> map: container) {
            for (Map.Entry<Object, Object> pair : map.entrySet()) {
                Film film = (Film) pair.getKey();
                Info info = (Info) pair.getValue();
                if ((info.getGenre().toLowerCase()).contains(str.toLowerCase())) {
                    printData(film, info);
                }
            }
        }
    }

    static void operateLeadStudio() {
        System.out.print("Enter the name of Lead Studio:    ");
        String str = scan.next();
        for (Map<Object, Object> map : container) {
            for (Map.Entry<Object, Object> pair : map.entrySet()) {
                Film film = (Film) pair.getKey();
                Info info = (Info) pair.getValue();
                if ((info.getLeadStudio().toLowerCase()).contains(str)) {
                    printData(film, info);
                }
            }
        }
    }

    static void operateYear() {
        System.out.print("Enter the year:    ");
        int year = scan.nextInt();
        for (Map<Object, Object> map : container){
            for (Map.Entry<Object, Object> pair : map.entrySet()) {
                Film film = (Film) pair.getKey();
                Info info = (Info) pair.getValue();
                if (year == info.getYear()) {
                    printData(film, info);
                }
            }
        }
    }

    static void operateWholeData() {
        for (Map<Object, Object> map : container) {
            for (Map.Entry<Object, Object> pair : map.entrySet()) {
                Film film = (Film) pair.getKey();
                Info info = (Info) pair.getValue();
                printData(film, info);
            }
        }
    }
}

//package time
public class Main {
    public static void main(String[] args) {
        Stats stats = new Stats();
        stats.ask();
    }
}
