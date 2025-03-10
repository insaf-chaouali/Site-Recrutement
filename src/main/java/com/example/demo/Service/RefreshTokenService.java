package com.example.demo.Service;

import com.example.demo.Model.RefreshToken;
import com.example.demo.Repository.RefreshTokenRepository;
import com.example.demo.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Value("${insaf.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;


    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }
        public RefreshToken createRefreshToken (Long userId){
            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setUtilisateur(utilisateurRepository.findById(userId).get());
            refreshToken.setExpiryDate(Instant.now().
                    plusMillis(refreshTokenDurationMs));
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken = refreshTokenRepository.save(refreshToken);
            return refreshToken;

        }
    }
