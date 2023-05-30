package com.ecommerce.ECommerce.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ECommerce.api.model.LoginBody;
import com.ecommerce.ECommerce.api.model.RegistrationBody;
import com.ecommerce.ECommerce.exception.EmailFailed;
import com.ecommerce.ECommerce.exception.UserAlreadyExistException;
import com.ecommerce.ECommerce.exception.UserNotVerifiedException;
import com.ecommerce.ECommerce.model.LocalUser;
import com.ecommerce.ECommerce.model.VerificationToken;
import com.ecommerce.ECommerce.model.dto.LocalUserDTO;
import com.ecommerce.ECommerce.model.dto.VereficationTokenDTO;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private LocalUserDTO localUserDTO;

    @Autowired
    private EncryptionService es;

    @Autowired
    private VereficationTokenDTO vereficationTokenDTO;

    @Autowired
    private JwtService jw;

    @Autowired
    private EmailService emailService;

    // Create a User and
    public LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistException, EmailFailed {
        if (localUserDTO.findByUserName(registrationBody.getUserName()).isPresent()
                || localUserDTO.findByEmail(registrationBody.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("User already exists or Email already exists");
        }

        LocalUser user = new LocalUser();

        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setEmail(registrationBody.getEmail());
        user.setUserName(registrationBody.getUserName());
        // Encryption Password
        user.setPassWord(es.encryptPassword(registrationBody.getPassword()));
        VerificationToken verificationToken = verificationToken(user);
        emailService.sendVerificationEmail(verificationToken);
        vereficationTokenDTO.save(verificationToken);

        return localUserDTO.save(user);
    }

    public String loginUser(LoginBody loginBody) throws UserNotVerifiedException, EmailFailed {
        Optional<LocalUser> user = localUserDTO.findByUserName(loginBody.getUserName());

        if (user.isPresent()) {
            LocalUser user2 = user.get();
            if (es.verifyPassword(loginBody.getPassword(), user2.getPassWord())) {
                if (user2.getEmailVerified()) {
                    return jw.getAlgorithmJWT(user2);
                } else {
                    List<VerificationToken> verificationTokens = user2.getVerificationTokens();
                    boolean resend = verificationTokens.size() == 0 || verificationTokens.get(0).getCreateTimestamp()
                            .before(new Timestamp(System.currentTimeMillis() - (60 * 60 * 1000)));
                    if (resend) {
                        VerificationToken verificationToken = verificationToken(user2);
                        vereficationTokenDTO.save(verificationToken);
                        emailService.sendVerificationEmail(verificationToken);
                    }
                    throw new UserNotVerifiedException(resend);
                }

            }
        }

        return null;
    }

    private VerificationToken verificationToken(LocalUser user) {
        VerificationToken verificationToken2 = new VerificationToken();
        verificationToken2.setToken(jw.generateVerificaitonJWT(user));
        verificationToken2.setCreateTimestamp(new Timestamp(System.currentTimeMillis()));
        verificationToken2.setUser(user);
        user.getVerificationTokens().add(verificationToken2);

        return verificationToken2;

    }

    @Transactional
    public boolean verifyUser(String token) {
        Optional<VerificationToken> verificationToken = vereficationTokenDTO.findByToken(token);

        if (verificationToken.isPresent()) {
            VerificationToken verificationToken2 = verificationToken.get();
            LocalUser user = verificationToken2.getUser();

            if (!user.getEmailVerified()) {
                user.setEmailVerified(true);
                localUserDTO.save(user);
                vereficationTokenDTO.deleteByUser(user);

                return true;
            }

        }

        return false;

    }

}