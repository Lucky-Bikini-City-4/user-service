package com.dayaeyak.user.domain.user.jpa;

import com.dayaeyak.user.domain.user.User;
import com.dayaeyak.user.domain.user.UserSocial;
import com.dayaeyak.user.domain.user.enums.ProviderType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSocialJpaRepository extends JpaRepository<UserSocial, Long> {

    boolean existsByUserAndProviderTypeAndProviderIdAndDeletedAtIsNull(User user, ProviderType providerType, String providerId);
}
