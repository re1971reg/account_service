package faang.school.accountservice.mapper;

import faang.school.accountservice.dto.AccountResponseDto;
import faang.school.accountservice.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {

    @Mapping(target = "id", source = "account.id")
    @Mapping(target = "code", source = "account.code")
    @Mapping(target = "accountType", expression = "java(account.getAccountType().getCode().getName())")
    @Mapping(target = "currency", expression = "java(account.getCurrency().getName())")
    @Mapping(target = "ownerId", expression = "java(account.getOwner().getForeignId())")
    @Mapping(target = "ownerType", expression = "java(account.getOwner().getForeignType().getName())")
    @Mapping(target = "status", expression = "java(account.getStatus().getName())")
    AccountResponseDto toAccountResponseDto(Account account);
}
