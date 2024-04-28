package school.management.system.com.example.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import school.management.system.com.example.dto.AuthenticationRequest;
import school.management.system.com.example.dto.AuthenticationResponse;
import school.management.system.com.example.utils.JwtUtils;

import java.io.IOException;

@RestController
public class AuthenticationController {
   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
   private UserDetailsService userDetailsService;

   @Autowired
   private JwtUtils jwtUtils;

   @PostMapping("/authenticate")
   public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException {
      try {
         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
      }catch (BadCredentialsException e){
         throw new BadCredentialsException("Incorrect username or password");

      }
      catch (DisabledException disabledException){
         response.sendError(HttpServletResponse.SC_NOT_FOUND,"User is created");
         return null;
      }
      final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
      final String jwt= jwtUtils.generateToken(userDetails.getUsername());
      return new AuthenticationResponse(jwt);
   }


}

