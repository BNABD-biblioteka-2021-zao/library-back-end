package pl.weeia.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.weeia.library.model.entities.LibraryUser;

@Repository
public interface LibraryUserRepository extends JpaRepository<LibraryUser, Long> {
    LibraryUser findByName(String name);

    LibraryUser findByEmail(String email);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE LibraryUser u SET u.refreshToken = ?2, u.name = 'kappa' WHERE u.email = ?1")
    Integer updateUserByEmail(String email, String UUID);
}
