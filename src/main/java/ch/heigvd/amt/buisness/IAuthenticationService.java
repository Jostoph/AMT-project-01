package ch.heigvd.amt.buisness;

import javax.ejb.Local;

@Local
public interface IAuthenticationService {

    public String hashPassword(String plainTextPassword);
    public boolean checkPassword(String plainTextPassword, String hashedPassword);
}