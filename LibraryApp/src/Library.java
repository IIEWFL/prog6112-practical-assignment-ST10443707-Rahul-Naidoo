import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Library {
    private final ArrayList<Item> items;
    private int nextId = 1;

    public Library() {
        items = new ArrayList<>();
        seedData();
    }

    private int getNextId() {
        return nextId++;
    }

    private void seedData() {
        addItem(new Book(getNextId(), "The Hobbit", 1937, "J.R.R. Tolkien", "Fantasy"));
        addItem(new Book(getNextId(), "1984", 1949, "George Orwell", "Dystopia"));
        addItem(new DVD(getNextId(), "Inception", 2010, "Christopher Nolan", 148));
        addItem(new Book(getNextId(), "Clean Code", 2008, "Robert C. Martin", "Programming"));
        addItem(new DVD(getNextId(), "The Matrix", 1999, "The Wachowskis", 136));
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean removeItem(int id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id) {
                items.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean borrowItem(int id) {
        for (Item it : items) {
            if (it.getId() == id) {
                if (!it.isBorrowed()) {
                    it.setBorrowed(true);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean returnItem(int id) {
        for (Item it : items) {
            if (it.getId() == id) {
                if (it.isBorrowed()) {
                    it.setBorrowed(false);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public List<Item> getAllItems() {
        return Collections.unmodifiableList(items);
    }

    public void generateReport() {
        System.out.println("===== LIBRARY REPORT =====");
        System.out.println("Total items: " + items.size());
        long borrowedCount = items.stream().filter(Item::isBorrowed).count();
        System.out.println("Borrowed items: " + borrowedCount);
        System.out.println();

        Item[] arr = items.toArray(new Item[0]);
        Arrays.sort(arr, Comparator.comparing(Item::getTitle));

        System.out.println("Items (sorted by title):");
        for (Item it : arr) {
            System.out.println(it.toString());
        }
        System.out.println();

        showGenreBorrowMatrix();
        System.out.println("==========================");
    }

    private void showGenreBorrowMatrix() {
        ArrayList<String> genres = new ArrayList<>();
        for (Item it : items) {
            if (it instanceof Book) {
                String g = ((Book) it).getGenre();
                if (!genres.contains(g)) genres.add(g);
            }
        }
        if (genres.isEmpty()) {
            System.out.println("No book genres available.");
            return;
        }

        int[][] matrix = new int[genres.size()][2];
        for (Item it : items) {
            if (it instanceof Book) {
                Book b = (Book) it;
                int idx = genres.indexOf(b.getGenre());
                if (b.isBorrowed()) matrix[idx][1]++;
                else matrix[idx][0]++;
            }
        }

        System.out.println("Genre availability matrix:");
        for (int i = 0; i < genres.size(); i++) {
            System.out.printf("%s -> Available: %d | Borrowed: %d%n", genres.get(i), matrix[i][0], matrix[i][1]);
        }
    }
}
