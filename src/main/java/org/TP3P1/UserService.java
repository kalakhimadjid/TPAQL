package org.TP3P1;

public class UserService {

    private final UserRepository userRepository;

    // Constructeur pour injecter le UserRepository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Méthode pour récupérer un utilisateur par son ID
    public User getUserById(long id) {
        return userRepository.findUserById(id);
    }
}
