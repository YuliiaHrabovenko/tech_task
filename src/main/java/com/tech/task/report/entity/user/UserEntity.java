package com.tech.task.report.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class UserEntity {
    @Id
    private String email;
    private String password;
    private UserRole role;

    public UserEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
