public abstract class Item {
    private final int id;
    private final String title;
    private final int year;
    private boolean borrowed;

    public Item(int id, String title, int year) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.borrowed = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getYear() { return year; }
    public boolean isBorrowed() { return borrowed; }

    void setBorrowed(boolean borrowed) { this.borrowed = borrowed; }

    public abstract String getDetails();

    @Override
    public String toString() {
        return String.format("ID:%d | %s (%d) | Borrowed: %s | %s",
                id, title, year, borrowed ? "Yes" : "No", getDetails());
    }
}
