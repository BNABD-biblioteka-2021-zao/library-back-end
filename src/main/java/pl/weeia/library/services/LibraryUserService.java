package pl.weeia.library.services;

import pl.weeia.library.model.entities.LibraryUser;

import java.util.UUID;

public interface LibraryUserService {

    void saveRefreshToken(String email, UUID refreshToken);
    Long saveUser(LibraryUser user) throws Exception;

    LibraryUser getUserByEmail(String name);

    String deleteMyAccount(String name);
}
