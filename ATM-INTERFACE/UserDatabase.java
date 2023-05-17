import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private List<User> users;

    public UserDatabase() {
        users = new ArrayList<>();
        // Add sample users to the database
        users.add(new User("user1", "1234", "John Doe", 1000.0));
        users.add(new User("user2", "5678", "Jane Smith", 500.0));
    }

    public User authenticateUser(String userId, String userPin) {
        for (User user : users) {
            if (user.getUserId().equals(userId) && user.authenticate(userPin)) {
                return user;
            }
        }
        return null;
    }
}
