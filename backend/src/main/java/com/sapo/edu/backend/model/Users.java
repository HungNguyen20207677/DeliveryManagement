package com.sapo.edu.backend.model;

import com.sapo.edu.backend.model.enumclasses.Roles;
import com.sapo.edu.backend.model.enumclasses.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "users")
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    Roles role;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    UserStatus status;

    @Column(name = "so_tien_du_no")
    private double soTienDuNo = 0;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_checked_at")
    private Date lastCheckedAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "updated_at")
    private Date updatedAt;

    // Automatically update the lastCheckedAt field to the current date
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }


    // implement User Detail interface's method

    // returns a list of roles that the user has
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
