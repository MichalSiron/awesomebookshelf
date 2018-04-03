package proc;

import dao.PopularAuthor;
import db.EntityReaderWriter;
import entity.Author;
import entity.Reader;
import exception.ApplicationException;

import java.util.*;

public class Bookshelf {

    // Main

    public static void main(String[] args) {
        Bookshelf bookshelf = new Bookshelf();

        try(Scanner scanner = new Scanner(System.in)) {
            System.out.print(Bookshelf.welcomMsg);
            System.out.print(Bookshelf.separator);
            int maxMenuChoice = 4; // 3 menu options and 1 exit option
            int menuUserChoice;
            do {
                menuUserChoice = bookshelf.askForNumber(scanner, Bookshelf.menu, maxMenuChoice);
                switch (menuUserChoice) {
                    case 1:
                        bookshelf.printAllAuthorsWithAtLeaseOneBook();
                        break;
                    case 2:
                        bookshelf.printNumberOfReadersInEachYear();
                        break;
                    case 3:
                        bookshelf.printMostPopularAuthors();
                        break;
                    case 4:
                        break;
                }

            }while (menuUserChoice < maxMenuChoice);
        }catch (ApplicationException ae){
            ae.printStackTrace();
        }finally {
            bookshelf.readerWriter.closeEm();
            bookshelf.readerWriter.closeEmf();
        }
    }

    private static String welcomMsg = "Welcome to bookshelf application.\n";
    private static String separator = "***************************************************************************************\n";
    private static String menu = "\t 1 - print 'All authors that wrote at least one book.' \n" +
            "\t 2 - print 'Number of readers born in each year' \n" +
            "\t 3 - print '3 the most popular authors with their popularity' \n" +
            "\t 4 - exit. \n";

    private Bookshelf(){}

    private EntityReaderWriter readerWriter = new EntityReaderWriter();

    private void printAllAuthorsWithAtLeaseOneBook() throws ApplicationException{
        for (Author author : readerWriter.withAtLeastOneBook()){
            System.out.println(author);
        }
    }

    private void printNumberOfReadersInEachYear(){
        System.out.println("LIST OF YEAR's...");
        Map<Integer, ArrayList<Reader>> years = new HashMap<>();

        for (Reader reader : readerWriter.getAllReaders()){
            Calendar cal = Calendar.getInstance();
            cal.setTime(reader.getBirth());
            ArrayList<Reader> readers = years.get(cal.get(Calendar.YEAR));
            if(readers != null){
                readers.add(reader);
            }else {
                ArrayList<Reader> mappedArray= new ArrayList<>();
                mappedArray.add(reader);
                years.put(cal.get(Calendar.YEAR), mappedArray);
            }
        }

        for (Map.Entry<Integer, ArrayList<Reader>> entry : years.entrySet()){
            System.out.println("Year: " + entry.getKey() + ", size: " +entry.getValue().size());
        }
    }

    private void printMostPopularAuthors() throws ApplicationException{
        for (PopularAuthor popularAuthor : readerWriter.printStats()){
            System.out.printf("Author name: %s, popilarity: %d \n", popularAuthor.getName(), popularAuthor.getPopularity());
        }
    }

    private int askForNumber(Scanner scanner, String menu, int maxOptions){
        System.out.print("Choose from following items: \n");
        System.out.print(menu);
        int value = scanner.nextInt();

        while (value < 1 || value > maxOptions){
            System.out.println("You choosed wrong menu item, please try again.");
            value = scanner.nextInt();
        }

        return value;
    }
}
