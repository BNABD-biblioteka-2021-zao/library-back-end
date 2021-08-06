package pl.weeia.library.services;

import pl.weeia.library.model.DTOs.UserInsertModel;
import pl.weeia.library.model.entities.LibraryUser;

import java.util.UUID;

public interface LibraryUserService {

    void saveRefreshToken(String email, String refreshToken);
    Long saveUser(UserInsertModel user) throws Exception;

    LibraryUser getUserByEmail(String name);

    String deleteMyAccount(String name);

    LibraryUser findByEmail(String name);
}
