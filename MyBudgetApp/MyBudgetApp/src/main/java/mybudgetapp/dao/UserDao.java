package mybudgetapp.dao;

import java.sql.SQLException;
import mybudgetapp.domain.User;

/**
 *
 * @author ralahtin
 */
public interface UserDao {

    User create(User user) throws SQLException;

    User findByUsername(String username);

    User findOne(String username) throws SQLException;

}
