package gbc.ca.comp3095teamcrackers.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;

/*
Comp3095-Team-Crackers-Assignment-2
        Team Members:
        Safa Aru :     101331910
        Hakan Inel :   101213098
        Onur Korkmaz : 101303363
        Ahmet Buyukbas: 101304595
*/
@Service
public class UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    public LinkedHashMap<String,String> createAccount(User newUserDetail){

        LinkedHashMap<String,String> response = new LinkedHashMap<>();

        User user = userRepository.findByUsername(newUserDetail.getUsername());

        if(user == null){
            if (validatePassword(newUserDetail.getPassword())){
                newUserDetail.setPassword(this.passwordEncoder.encode(newUserDetail.getPassword()));
                newUserDetail.setRoles(Arrays.asList("USER"));
                newUserDetail.setStatus(User.STATUS_ACTIVE);
                newUserDetail.setActive(true);
                userRepository.save(newUserDetail);
                response.put("status","200");
                response.put("fullName",newUserDetail.getFirstName()+" "+newUserDetail.getLastName());
                response.put("message","Your account has been created successfully!");
            }else {
                response.put("status","400");
                response.put("message","Password must contain at least 8 characters");
            }
        }else {
            response.put("status","400");
            response.put("message","User already exist with email: "+newUserDetail.getUsername());
        }
        return response;
    }

    private boolean validatePassword(String password) {
        Boolean isValid = false;
        if (password.length()>6){
            isValid = true;
        }
        return isValid;
    }

    public User getLoggedInUserProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername().toLowerCase();
        return userRepository.findByUsername(username);
    }

    public LinkedHashMap<String, String> updateUser(User userNewInfo) {

        LinkedHashMap<String,String> responses = new LinkedHashMap<>();

        User user = userRepository.findByUsername(userNewInfo.getUsername());

        if(user == null){
            if (validatePassword(userNewInfo.getPassword())){
                userNewInfo.setFirstName(userNewInfo.getFirstName());
                userNewInfo.setLastName(userNewInfo.getLastName());
                userNewInfo.setUsername(userNewInfo.getUsername());

                userNewInfo.setPassword(this.passwordEncoder.encode(userNewInfo.getPassword()));
                userNewInfo.setRoles(Arrays.asList("USER"));
                userNewInfo.setStatus(User.STATUS_ACTIVE);
                userNewInfo.setActive(true);
                userRepository.save(userNewInfo);

            }else {
                responses.put("status","You need to log out");
                responses.put("message","Password must contain at least 8 characters");
            }
        }

        return responses;
    }


}
