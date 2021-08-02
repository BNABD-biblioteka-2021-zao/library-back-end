package pl.weeia.library.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.weeia.library.model.DTOs.AuthenticationRequest;
import pl.weeia.library.model.DTOs.AuthenticationResponse;
import pl.weeia.library.model.DTOs.RefreshTokenRequest;
import pl.weeia.library.model.DTOs.UserInsertModel;
import pl.weeia.library.model.entities.LibraryUser;
import pl.weeia.library.security.JwtUtil;
import pl.weeia.library.security.LibraryUserDetailService;
import pl.weeia.library.services.LibraryUserService;

@AllArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final LibraryUserDetailService userDetailService;
    private final JwtUtil jwtTokenUtil;
    private final LibraryUserService userService;

    @PostMapping("/register")
    public Long registerLibraryUser(@RequestBody UserInsertModel user) throws Exception {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> crateAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        System.out.println("auth running" + authenticationRequest   );
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
//                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), passwordEncoder.encode(authenticationRequest.getPassword())));
            System.out.println("manager");
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password");
        }
        System.out.println("au");
        final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        final String refreshToken = jwtTokenUtil.generateRefreshToken();
        userService.saveRefreshToken(userDetails.getUsername(), refreshToken);
        return ResponseEntity.ok(new AuthenticationResponse(jwt,60 * 60L, refreshToken));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(Authentication authentication, @RequestBody RefreshTokenRequest tokenBody) throws Exception {
        LibraryUser user= userService.getUserByEmail(authentication.getName());
//        String tokenId = jwtTokenUtil.extractId(token.getRefreshToken()).toString();
        System.out.println(user.getRefreshToken());
        if (tokenBody.getRefreshToken().equals(user.getRefreshToken())){
            final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            final String jwt = jwtTokenUtil.generateToken(userDetails);
            final String refreshToken = jwtTokenUtil.generateRefreshToken();
            userService.saveRefreshToken(userDetails.getUsername(), refreshToken);
            return ResponseEntity.ok(new AuthenticationResponse(jwt,60 * 60L, refreshToken));
        } else {
            throw new Exception("Incorrect refresh token");
        }
    }
}
