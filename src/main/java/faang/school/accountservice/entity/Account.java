package faang.school.accountservice.entity;

import faang.school.accountservice.enums.AccountStatus;
import faang.school.accountservice.enums.Currency;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // todo: проверить как отработает проверка на {min}/{max}
    @Column(name = "code", nullable = false)
    @Size(min = 12, max = 20, message = "{account.code.size}")
    private String code;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private AccountOwner owner;

    @ManyToOne
    @JoinColumn(name = "account_type", nullable = false)
    private AccountType accountType;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    @Column(name = "modified_by", nullable = false)
    private Long modifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "closed_at")
    private LocalDateTime closedAt;

    @Column(name = "closed_by")
    private Long closedBy;

    /**
     * При наличии @Version, при обработке обновлений в сервисе можно поймать исключение:
     * <pre><code>
     *     try {
     *         productRepository.save(product);
     *     } catch (OptimisticLockException e) {
     *         // Обработка конфликта обновления
     *         throw new RuntimeException("Данные были изменены другим пользователем. Обновите и повторите попытку.");
     *     }
     *     </code></pre>
     */
    @Column(name = "version")
    @Version // поле версии для отслеживания изменений
    private Integer version;
}
