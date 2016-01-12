package nl.dao;

import nl.model.User;

/**
 *
 * @author Hugo van Rijswijk
 */
public interface UserDao {

    User findByUserName(String username);
}
