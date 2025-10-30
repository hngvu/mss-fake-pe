package com.mss301.msaccount_se18262.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "MSS301Summer25DBAccount")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SystemAccount {
    @Id
    int id;
    String username;
    String email;
    String password;
    int role;
    boolean isActive;

    public String getRoleName() {
        return switch (role) {
            case 1 -> "Administrator";
            case 2 -> "Moderator";
            case 3 -> "Developer";
            default -> "Member";
        };
    }
}
