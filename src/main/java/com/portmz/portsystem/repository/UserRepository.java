package com.portmz.portsystem.repository;
import com.portmz.portsystem.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);  //deve fazer a busca por NOME DO USUARIO
    //userDetails porque vai ser usado pelo spring Security depois
}