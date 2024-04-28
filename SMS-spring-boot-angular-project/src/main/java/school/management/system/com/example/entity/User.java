package school.management.system.com.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import school.management.system.com.example.enums.UserRole;

@Data
@Entity
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole role;
}
