package br.com.edwin.lima.utils;

import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class EncoderUtil {

    public static String encodePassword(String pass){
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        var pbkdf2Encoder = new Pbkdf2PasswordEncoder("",8,185000,Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
        encoders.put("pbkdf2", pbkdf2Encoder);
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);

        String resultEncoded = passwordEncoder.encode(pass);
        return resultEncoded;
    }
}
