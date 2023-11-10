package ai.semfila.api.DTO.user;

import ai.semfila.api.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
    String name,
    String phone,
    LocalDate birthday,
    LocalDateTime createAt

) {
    public UserResponse(User user){
        this(user.getId(),user.getName(), user.getPhone(), user.getBirthday(), user.getCreateAt());
    }
}
