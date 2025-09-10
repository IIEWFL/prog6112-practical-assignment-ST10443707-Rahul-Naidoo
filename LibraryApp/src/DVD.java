public class DVD extends Item {
    private final String director;
    private final int durationMinutes;

    public DVD(int id, String title, int year, String director, int durationMinutes) {
        super(id, title, year);
        this.director = director;
        this.durationMinutes = durationMinutes;
    }

    public String getDirector() { return director; }
    public int getDurationMinutes() { return durationMinutes; }

    @Override
    public String getDetails() {
        return "DVD directed by " + director + " (" + durationMinutes + " mins)";
    }
}
