package faang.school.accountservice.enums;

import lombok.Getter;

@Getter
public enum AccountStatus {
    NEW("NEW"),
    OPEN("OPEN"),
    LOCK("LOCK"),
    CLOSE("CLOSE");

    private final String name;

    AccountStatus(String name) {
        this.name = name;
    }

}
