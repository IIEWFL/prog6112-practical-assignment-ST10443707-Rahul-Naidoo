package tvseries;

import java.util.*;

public class SeriesManager {
    private static Scanner sc = new Scanner(System.in);
    private static List<Series> seriesList = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nPlease select one of the following menu items:");
            System.out.println("(1) Capture a new series");
            System.out.println("(2) Search for a series");
            System.out.println("(3) Update series age restriction");
            System.out.println("(4) Delete a series");
            System.out.println("(5) Print series report - 2025");
            System.out.println("(6) Exit Application");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: captureSeries(); break;
                case 2: searchSeries(); break;
                case 3: updateSeries(); break;
                case 4: deleteSeries(); break;
                case 5: seriesReport(); break;
                case 6: exitSeriesApplication(); return;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void captureSeries() {
        System.out.print("Enter series id: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter series name: ");
        String name = sc.nextLine();
        System.out.print("Enter age restriction: ");
        int age = sc.nextInt();
        System.out.print("Enter number of episodes: ");
        int episodes = sc.nextInt(); sc.nextLine();
        seriesList.add(new Series(id, name, age, episodes));
        System.out.println("Series captured successfully.");
    }

    public static Series searchSeries() {
        System.out.print("Enter series id to search: ");
        int id = sc.nextInt(); sc.nextLine();
        for (Series s : seriesList) {
            if (s.getId() == id) {
                System.out.println("Series found:\n" + s);
                return s;
            }
        }
        System.out.println("Series not found.");
        return null;
    }

    public static boolean updateSeries() {
        System.out.print("Enter the series id to update: ");
        int id = sc.nextInt(); sc.nextLine();
        for (Series s : seriesList) {
            if (s.getId() == id) {
                System.out.print("Enter the series name: ");
                s.setName(sc.nextLine());
                System.out.print("Enter the age restriction: ");
                s.setAgeRestriction(sc.nextInt());
                System.out.print("Enter the number of episodes: ");
                s.setEpisodes(sc.nextInt()); sc.nextLine();
                System.out.println("Series updated successfully.");
                return true;
            }
        }
        System.out.println("Series not found.");
        return false;
    }

    public static boolean deleteSeries() {
        System.out.print("Enter the series id to delete: ");
        int id = sc.nextInt(); sc.nextLine();
        Iterator<Series> iterator = seriesList.iterator();
        while (iterator.hasNext()) {
            Series s = iterator.next();
            if (s.getId() == id) {
                System.out.print("Are you sure you want to delete series " + id + "? (y/n): ");
                String confirm = sc.nextLine();
                if (confirm.equalsIgnoreCase("y")) {
                    iterator.remove();
                    System.out.println("Series with ID " + id + " was deleted.");
                    return true;
                }
            }
        }
        System.out.println("Series not found.");
        return false;
    }

    public static void seriesReport() {
        if (seriesList.isEmpty()) {
            System.out.println("No series captured yet.");
            return;
        }
        int counter = 1;
        for (Series s : seriesList) {
            System.out.println("Series " + counter++);
            System.out.println("--------------------------------");
            System.out.println(s);
        }
    }

    public static void exitSeriesApplication() {
        System.out.println("Exiting application...");
    }

    // Helper methods for unit tests
    public static void addSeries(Series s) { seriesList.add(s); }
    public static void clearSeries() { seriesList.clear(); }
    public static List<Series> getSeriesList() { return seriesList; }
}
