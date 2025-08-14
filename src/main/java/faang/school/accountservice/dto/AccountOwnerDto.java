package faang.school.accountservice.dto;

import faang.school.accountservice.enums.AccountOwnerType;

public record AccountOwnerDto(
    Long foreignId,
    AccountOwnerType foreignType
) {
}
