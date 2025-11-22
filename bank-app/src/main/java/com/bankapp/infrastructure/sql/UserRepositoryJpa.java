package com.bankapp.infrastructure.sql;

import com.bankapp.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepositoryJpa extends JpaRepository<User, UUID>{
}
