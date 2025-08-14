package faang.school.accountservice.dto;

import lombok.Builder;

@Builder
public record AccountResponseDto(
    Long id,
    String code,
    String accountType,
    String currency,
    Long ownerId,
    String ownerType,
    String status
) {
}
