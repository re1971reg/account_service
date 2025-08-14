package faang.school.accountservice.controller;

import faang.school.accountservice.dto.AccountResponseDto;
import faang.school.accountservice.dto.CreateAccountRequestDto;
import faang.school.accountservice.dto.AccountOwnerDto;
import faang.school.accountservice.enums.AccountCodeType;
import faang.school.accountservice.enums.AccountOwnerType;
import faang.school.accountservice.enums.Currency;
import faang.school.accountservice.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/accounts")
@Tag(name = "Application Service API", description = "API for Post Service")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    /*
     * TODO:
     *  - открыть/создать счет;
     *  - получить один счет по id;
     *  - получить все счета пользователя/проекта
     *  - заблокировать/заморозить счета
     *  - закрыть счет
     */

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Create new account",
        description = "Create new account to human or project",
        parameters = {
            @Parameter(
                name = "x-user-id", in = ParameterIn.HEADER,
                description = "user id", required = true,
                schema = @Schema(type = "string"))
        }
    )
    public AccountResponseDto createAccount(
        @Valid @RequestBody CreateAccountRequestDto requestDto
    ) {
        log.debug("create new account request: {}", requestDto);
        return accountService.createAccount(requestDto);
    }

    @GetMapping("/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "Get account by id",
        description = "Method return account info by id",
        parameters = {
            @Parameter(
                name = "x-user-id", in = ParameterIn.HEADER,
                description = "user id", required = true,
                schema = @Schema(type = "string"))
        }
    )
    public AccountResponseDto getAccountById(@PathVariable("accountId") Long accountId) {
        log.debug("get account by id: {}", accountId);
        return accountService.getAccountDtoById(accountId);
    }

    @GetMapping("/{ownerType}/{ownerId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "Get all accounts of human or project",
        description = "Method return all accounts of human or projects",
        parameters = {
            @Parameter(
                name = "x-user-id", in = ParameterIn.HEADER,
                description = "user id", required = true,
                schema = @Schema(type = "string"))
        }
    )
    public List<AccountResponseDto> getAllAccounts(
        @Valid @PathVariable("ownerType") String ownerType,
        @PathVariable("ownerId") Long ownerId
    ) {
        log.debug("get all owner accounts. owner is {}, owner type is: {}", ownerId, ownerType);
        AccountOwnerDto accountOwnerDto = new AccountOwnerDto(ownerId, AccountOwnerType.valueOf(ownerType));
        return accountService.getAccountByOwner(accountOwnerDto);
    }

    @GetMapping("/{accountId}/lock")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "Lock account by id",
        description = "Method lock account by id",
        parameters = {
            @Parameter(
                name = "x-user-id", in = ParameterIn.HEADER,
                description = "user id", required = true,
                schema = @Schema(type = "string"))
        }
    )
    public void lockAccount(@PathVariable Long accountId) {
        log.debug("lock account by id: {}", accountId);
        accountService.setAccountLocked(accountId);
    }

    @GetMapping("/{accountId}/open")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "Open account by id",
        description = "Method open account by id",
        parameters = {
            @Parameter(
                name = "x-user-id", in = ParameterIn.HEADER,
                description = "user id", required = true,
                schema = @Schema(type = "string"))
        }
    )
    public void openAccount(@PathVariable Long accountId) {
        log.debug("open account by id: {}", accountId);
        accountService.setAccountOpened(accountId);
    }

    @GetMapping("/{accountId}/close")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "Close account by id",
        description = "Method close account by id",
        parameters = {
            @Parameter(
                name = "x-user-id", in = ParameterIn.HEADER,
                description = "user id", required = true,
                schema = @Schema(type = "string"))
        }
    )
    public void closeAccount(@PathVariable Long accountId) {
        log.debug("close account by id: {}", accountId);
        accountService.setAccountClosed(accountId);
    }

    private AccountResponseDto getAccountResponseDto() {
        return AccountResponseDto.builder()
            .id(1L)
            .code("new code")
            .accountType(AccountCodeType.CURRENCY_ACCOUNT_FIZ.getName())
            .currency(Currency.RUB.getName())
            .ownerId(10L)
            .ownerType(AccountOwnerType.HUMAN.getName())
            .build();
    }


}
