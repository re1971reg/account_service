package faang.school.accountservice.dto;

import faang.school.accountservice.enums.AccountCodeType;
import faang.school.accountservice.enums.AccountOwnerType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateAccountRequestDto(
    /*
     * todo: проверить подстановку текста ошибки из messages.properties
     */
    // @NotNull(message = "#{${dto.account.codeType.isEmpty}}")
    @NotNull(message = "type of account code is empty")
    AccountCodeType accountCodeType,
    // @NotNull(message = "#{${dto.account.owner.isEmpty}}")
    @NotNull(message = "owner is empty")
    Long ownerId,
    // @NotNull(message = "#{${dto.account.ownerType.isEmpty}}")
    @NotNull(message = "owner type is empty")
    AccountOwnerType accountOwnerType
) {
}
