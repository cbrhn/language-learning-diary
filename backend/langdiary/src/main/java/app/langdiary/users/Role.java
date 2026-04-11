package app.langdiary.users;

import java.util.Set;

public enum Role {
    USER(Set.of(Permissions.READ, Permissions.WRITE, Permissions.EDIT_VIEW, Permissions.DELETE)),
    VIEWER(Set.of(Permissions.READ, Permissions.EDIT_VIEW)), // read-only user
    ADMIN(Set.of(Permissions.READ, Permissions.WRITE, Permissions.EDIT_VIEW, Permissions.DELETE));

    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }
}
