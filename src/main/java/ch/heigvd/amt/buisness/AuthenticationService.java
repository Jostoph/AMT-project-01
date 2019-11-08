package ch.heigvd.amt.buisness;

import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;

@Stateless
public class AuthenticationService implements  IAuthenticationService {
    @Override
    public String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    @Override
    public boolean checkPassword(String plainTextPassword, String hashedPassword) {
        try {
            // TODO remove
            System.out.println("checked psHash : " + plainTextPassword);
            System.out.println("checked hash : " + hashedPassword);
            boolean correct = BCrypt.checkpw(plainTextPassword, hashedPassword);
            return correct;
        } catch (Exception e) {
            return false;
        }
    }
}
