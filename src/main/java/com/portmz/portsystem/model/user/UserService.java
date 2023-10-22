package com.portmz.portsystem.model.user;

import com.portmz.portsystem.exceptionHandler.BusinesException;
import com.portmz.portsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    //PRIMEIRO METODO
    public static User authenticated() {
        try {
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Task with ID " + id + " not found"));
    }

    // Método para salvar o refreshToken associado a um usuário
    public void saveRefreshToken(String username, String refreshToken) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            // Atualiza o refreshToken do usuário
            user.setRefreshToken(refreshToken);
            userRepository.save(user);
        }
    }

    // Método para obter o ID do usuário logado
    public Long getLoggedInUserId() {
        User user = authenticated();
        if (user == null) {
            throw new IllegalStateException("Usuário não autenticado.");
        }
        return user.getId();
    }


    public List<User> findAllUser() {
        User userSpringSecurity = authenticated();
        if (userSpringSecurity == null) {
            throw new RuntimeException("Usuário não autenticado.");
        }

        UserRole userRole = userSpringSecurity.getRole();

        // Allow access for both ADMIN and USER roles
        if (userRole == UserRole.ADMIN || userRole == UserRole.USER) {
            return userRepository.findAll();
        } else {
            throw new AccessDeniedException("Acesso não autorizado.");
        }
    }

    private boolean isAdmin(User user) {
        return user != null && user.getRole() == UserRole.ADMIN;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BusinesException("ID nao Encontrado"));
    }
}
