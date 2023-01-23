import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter the time to speak it out: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println(ClockReader.readClock(scanner.next()));
        System.out.println("**********************");
    }
}