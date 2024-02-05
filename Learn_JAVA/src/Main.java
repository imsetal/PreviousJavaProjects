import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        List<user> users = new ArrayList<>();

        user user_1 = new user("chen", "123");
        user user_2 = new user("chen1", "1234");

        users.add(user_1);
        users.add(user_2);
        for (int i=0;i< users.size();i++){
            System.out.println(users.get(i).getName());
        }
    }
}