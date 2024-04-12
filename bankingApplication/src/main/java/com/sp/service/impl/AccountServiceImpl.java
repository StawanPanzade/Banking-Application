package com.sp.service.impl;

import org.springframework.stereotype.Service;

import com.sp.dto.AccountDto;
import com.sp.entity.Account;
import com.sp.mapper.AccountMapper;
import com.sp.repository.AccountRepository;
import com.sp.service.AccountService;


@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;
	
	
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}



	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

}
