import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            Companies c = new Companies();
            c.readInput(new File("input.csv"));
            c.menu();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Something went wrong. Please check files");
        }
    }
}
