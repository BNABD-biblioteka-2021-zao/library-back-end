package pl.weeia.library.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {

    private final String accessToken;
    private final Long expiresInSeconds;
    private final String refreshToken;

}
