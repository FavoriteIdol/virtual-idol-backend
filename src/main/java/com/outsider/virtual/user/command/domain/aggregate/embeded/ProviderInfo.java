package com.outsider.virtual.user.command.domain.aggregate.embeded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProviderInfo {

    @Column(name = "provider")
    private String provider; // This will store the provider name, e.g., EMAIL, GOOGLE

    @Column(name = "provider_id")
    private String providerId; // This will store the unique ID provided by the provider

    // Constructors
    public ProviderInfo() {}

    public ProviderInfo(String provider, String providerId) {
        this.provider = provider;
        this.providerId = providerId;
    }

    // Getters and Setters
    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    @Override
    public String toString() {
        return "Provider: " + provider + ", ProviderId: " + providerId;
    }

    // equals and hashCode methods for proper comparison and hashing
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProviderInfo that = (ProviderInfo) o;

        if (provider != null ? !provider.equals(that.provider) : that.provider != null) return false;
        return providerId != null ? providerId.equals(that.providerId) : that.providerId == null;
    }

    @Override
    public int hashCode() {
        int result = provider != null ? provider.hashCode() : 0;
        result = 31 * result + (providerId != null ? providerId.hashCode() : 0);
        return result;
    }
}
