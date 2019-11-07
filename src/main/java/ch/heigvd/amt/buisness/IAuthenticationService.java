package ch.heigvd.amt.buisness;

import javax.ejb.Local;

@Local
public interface IAuthenticationService {

    String hashPassword(String plainTextPassword);
    boolean checkPassword(String plainTextPassword, String hashedPassword);
}
