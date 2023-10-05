package com.bizlog.rms.entities.role;

public enum Roles {
    ROLE_ADMIN("ROLE_ADMIN"), ROLE_SUPER_ADMIN("ROLE_SUPER_ADMIN");

    private final String role;

    Roles(String string) {
        this.role = string;
    }

    public String getRoleString() {
        return role;
    }
}
