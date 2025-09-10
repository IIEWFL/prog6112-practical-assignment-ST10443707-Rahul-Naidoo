public class Book extends Item {
    private final String author;
    private final String genre;

    public Book(int id, String title, int year, String author, String genre) {
        super(id, title, year);
        this.author = author;
        this.genre = genre;
    }

    public String getAuthor() { return author; }
    public String getGenre() { return genre; }

    @Override
    public String getDetails() {
        return "Book by " + author + " [" + genre + "]";
    }
}
