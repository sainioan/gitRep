package mybudgetapp.dao;

import java.sql.SQLException;
import mybudgetapp.domain.User;
import java.util.List;

/**
 *
 * @author ralahtin
 */
public interface UserDao {

    User create(User user) throws SQLException;

    User findByUsername(String username);

    User findOne(String username) throws SQLException;

    List<User> getAll() throws SQLException;

}
