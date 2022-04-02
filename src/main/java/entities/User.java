package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class User {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String userStatus;

    public User(List<String> userData) {
        this.id = userData.get(0);
        this.username = userData.get(1);
        this.firstName = userData.get(2);
        this.lastName = userData.get(3);
        this.email = userData.get(4);
        this.password = userData.get(5);
        this.phone = userData.get(6);
        this.userStatus = userData.get(7);
    }
}
