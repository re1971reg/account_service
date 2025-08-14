package faang.school.accountservice.enums;

import lombok.Getter;

@Getter
public enum AccountOwnerType {
    PROJECT("PROJECT"),
    HUMAN("HUMAN");

    private final String name;

    AccountOwnerType(String name) {
        this.name = name;
    }
}
