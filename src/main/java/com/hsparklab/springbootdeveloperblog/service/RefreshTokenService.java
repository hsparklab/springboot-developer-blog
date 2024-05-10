package com.hsparklab.springbootdeveloperblog.service;

import com.hsparklab.springbootdeveloperblog.domain.RefreshToken;
import com.hsparklab.springbootdeveloperblog.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    final private RefreshTokenRepository refreshTokenRepository;
    public RefreshToken findByRefreshToken(String refreshToken){
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()->new IllegalArgumentException("Unexpected token"));

    }
}
