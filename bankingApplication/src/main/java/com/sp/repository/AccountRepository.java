package com.sp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
