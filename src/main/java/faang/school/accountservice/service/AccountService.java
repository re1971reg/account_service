package faang.school.accountservice.service;

import faang.school.accountservice.dto.AccountResponseDto;
import faang.school.accountservice.dto.CreateAccountRequestDto;
import faang.school.accountservice.dto.AccountOwnerDto;
import faang.school.accountservice.entity.Account;
import faang.school.accountservice.entity.AccountOwner;
import faang.school.accountservice.enums.AccountStatus;
import faang.school.accountservice.exception.AccountNotFoundException;
import faang.school.accountservice.mapper.AccountMapper;
import faang.school.accountservice.repository.AccountRepository;
import faang.school.accountservice.utils.Utils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    public static final String ACCOUNT_NOT_FOUND_BY_ID = "exception.account.notFound.byId";
    // public static final String ACCOUNT_NOT_FOUND_BY_CODE = "exception.account.notFound.byCode";

    private String accountNotFoundById;
    private String accountNotFoundByCode;

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final Utils utils;
    private final MessageSource messageSource;
    private final Locale locale;

    @PostConstruct
    public void init() {
        accountNotFoundById = messageSource.getMessage(ACCOUNT_NOT_FOUND_BY_ID, null, locale);
        // accountNotFoundByCode = messageSource.getMessage(ACCOUNT_NOT_FOUND_BY_CODE, null, locale);
        // log.debug("accountNotFoundById: {}, accountNotFoundByCode: {}", accountNotFoundById, accountNotFoundByCode);
        log.debug("accountNotFoundById: {}", accountNotFoundById);
    }

    @Transactional(readOnly = true)
    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
            .orElseThrow(() -> {
                String errorMessage = utils.format(accountNotFoundById, accountId);
                return new AccountNotFoundException(errorMessage);
            });
    }

    @Transactional(readOnly = true)
    public AccountResponseDto getAccountDtoById(Long accountId) {
        return accountMapper.toAccountResponseDto(getAccountById(accountId));
    }

    // @Transactional(readOnly = true)
    // public Account findAccountByCode(String code) {
    //     return accountRepository.findByCode(code)
    //         .orElseThrow(() -> {
    //             String errorMessage = utils.format(accountNotFoundByCode, code);
    //             return new AccountNotFoundException(errorMessage);
    //         });
    // }

    @Transactional
    public AccountResponseDto createAccount(CreateAccountRequestDto requestDto) {
        /* todo:
         *  - проверить наличие пользователя или проекта в системе.
         *      запрос для проверки наличия пользователя отправить в userService
         *      запрос для проверки наличия проекта в системе отправить в postService
         *  - заполнить объект Account и сохранить его
         */
        return null;
    }

    @Transactional(readOnly = true)
    public List<AccountResponseDto> getAccountByOwner(AccountOwnerDto accountOwnerDto) {
        /* todo:
         *   - проверить наличие пользователя или проекта в системе.
         *       запрос для проверки наличия пользователя отправить в userService
         *       запрос для проверки наличия проекта в системе отправить в postService
         *   - найти AccountOwner по foreignId и foreignType
         */
        accountRepository.findByOwner(accountOwnerDto.foreignId(), accountOwnerDto.foreignType());
        return null;
    }

    @Transactional
    public void setAccountLocked(Long accountId) {
        setAccountStatus(accountId, AccountStatus.LOCK);
    }

    @Transactional
    public void setAccountOpened(Long accountId) {
        setAccountStatus(accountId, AccountStatus.OPEN);
    }

    @Transactional
    public void setAccountClosed(Long accountId) {
        setAccountStatus(accountId, AccountStatus.CLOSE);
    }

    private void setAccountStatus(Long accountId, AccountStatus accountStatus) {
        accountRepository.setAccountStatus(accountId, accountStatus);
    }
}
