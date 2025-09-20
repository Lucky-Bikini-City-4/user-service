package com.dayaeyak.user.domain.user;

import com.dayaeyak.user.common.entity.BaseEntity;
import com.dayaeyak.user.domain.user.enums.ProviderType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_socials")

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSocial extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_social_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProviderType providerType;

    @Column(nullable = false)
    private String providerId;

    public UserSocial(User user, ProviderType providerType, String providerId) {
        this.user = user;
        this.providerType = providerType;
        this.providerId = providerId;
    }
}
