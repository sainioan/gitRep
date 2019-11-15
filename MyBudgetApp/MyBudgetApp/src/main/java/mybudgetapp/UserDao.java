
package mybudgetapp;
import java.util.List;
/**
 *
 * @author ralahtin
 */
public interface UserDao {
    User create(User user) throws Exception;
    User findByUsername(String username);
    List<User>getAll();
}
