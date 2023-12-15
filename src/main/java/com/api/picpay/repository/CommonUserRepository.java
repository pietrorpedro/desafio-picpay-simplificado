package com.api.picpay.repository;

import com.api.picpay.model.CommonUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonUserRepository extends JpaRepository<CommonUser, Long> {
}
