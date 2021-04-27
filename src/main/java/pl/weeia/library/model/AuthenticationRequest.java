package pl.weeia.library.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    private String username;
    private String password;

    protected boolean canEqual(final Object other) {
        return other instanceof AuthenticationRequest;
    }

}
