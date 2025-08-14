package faang.school.accountservice.repository;

import faang.school.accountservice.entity.Account;
import faang.school.accountservice.enums.AccountOwnerType;
import faang.school.accountservice.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByCode(String code);

    @Modifying
    @Query(nativeQuery = true, value = """
        update account set status = :status where id = :accountId
        """)
    void setAccountStatus(
        @Param("accountId") Long accountId,
        @Param("status") AccountStatus status
    );

    @Query(nativeQuery = true, value = """
        select a.* from account a 
         where a.ownerId = (
                 select id from account_owner ao 
                  where ao.foreignId = :foreignId 
                    and ao.foreign_type = :foreignType 
               )
        """)
    void findByOwner(
        @Param("foreignId") Long foreignId,
        @Param("foreignType") AccountOwnerType accountOwnerType
    );
}
