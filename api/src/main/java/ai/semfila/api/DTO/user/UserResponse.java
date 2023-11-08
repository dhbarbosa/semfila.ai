
import ai.semfila.api.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserResponse(
    String name,
    String phone,
    LocalDate birthday,
    LocalDateTime createAt

) {
    public UserResponse(User user){
        this(user.getName(), user.getPhone(), user.getBirthday(), user.getCreateAt());
    }
}
