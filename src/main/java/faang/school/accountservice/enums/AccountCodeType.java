package faang.school.accountservice.enums;

import lombok.Getter;

import java.util.List;

public enum AccountCodeType {
    PAYMENT_ACCOUNT_FIZ("PAYMENT_ACCOUNT_FIZ"),
    CURRENCY_ACCOUNT_FIZ("CURRENCY_ACCOUNT_FIZ"),
    PAYMENT_ACCOUNT_JUR("PAYMENT_ACCOUNT_JUR"),
    CURRENCY_ACCOUNT_JUR("CURRENCY_ACCOUNT_JUR");

    private final String name;

    AccountCodeType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static List<AccountCodeType> getAll() {
        return List.of(AccountCodeType.values());
    }
}
