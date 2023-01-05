import java.util.Date;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String , String> test = new HashMap<>();
        final String REGEX = "^[A-Za-z1-9]{5}";
        boolean check = true;
        while (check) {
            System.out.println("Enter account name: ");
            String name = scanner.nextLine();
            Pattern pattern = Pattern.compile(REGEX);
            Matcher matcher = pattern.matcher(name);
            if (matcher.matches()) {
                if (name.equalsIgnoreCase("admin")) {
                    System.out.println("Can't create with this name");
                }else {
                    check =false;
                }
//                if (checkAccount(name) != null) {
//                    System.out.println("This account has already existed.");
//                }
            } else {
                System.out.println("Account must be at least 5 characters and no special characters");
            }
        }

    }
}
