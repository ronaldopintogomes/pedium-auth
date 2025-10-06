package com.pedium.auth.infrastructure.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@ConfigurationProperties(prefix="jwt")
public class JwtProperties {

    private long dateExpiration;
    private Resource privateKey;
    private Resource publicKey;

    public long getDateExpiration() {
        return dateExpiration;
    }
    public void setDateExpiration(long dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
    public Resource getPrivateKey() {
        return privateKey;
    }
    public void setPrivateKey(Resource privateKey) {
        this.privateKey = privateKey;
    }
    public Resource getPublicKey() {
        return publicKey;
    }
    public void setPublicKey(Resource publicKey) {
        this.publicKey = publicKey;
    }
}