package com.services.user.Service;

import com.services.user.Entity.ConfirmationToken;
import com.services.user.Repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository repository;

    void deleteConfirmationToken(Long id){
        repository.deleteById(id);
    }

    void saveConfirmationToken(ConfirmationToken confirmationToken) {
        repository.save(confirmationToken);
    }

    public Optional<ConfirmationToken> findConfirmationTokenByToken(String token) {
        return repository.findConfirmationTokenByConfirmationToken(token);
    }
}
