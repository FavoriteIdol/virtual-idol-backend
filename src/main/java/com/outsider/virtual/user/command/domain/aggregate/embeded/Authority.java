package com.outsider.virtual.user.command.domain.aggregate.embeded;

public enum Authority {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String authority;

    Authority(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public static Authority fromString(String authority) {
        for (Authority auth : Authority.values()) {
            if (auth.authority.equalsIgnoreCase(authority)) {
                return auth;
            }
        }
        throw new IllegalArgumentException("No enum constant for authority: " + authority);
    }
}