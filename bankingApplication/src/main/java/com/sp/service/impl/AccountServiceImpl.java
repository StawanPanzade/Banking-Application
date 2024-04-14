package com.sp.service.impl;

import java.text.Collator;
import java.util.List;
import java.util.stream.Collectors;

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
	
	
	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account Does not Exits.."));
		return AccountMapper.mapToAccountDto(account);
	}
	
	
	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account Does not Exits.."));
		
		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account saveAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(saveAccount);
	}



	@Override
	public AccountDto withdraw(Long id, double amount) {
		
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account Does not Exits.."));
		
		if(account.getBalance() < amount) {
			throw new RuntimeException("Insufficient Amount..");
		}
		
		double total = account.getBalance() - amount;
		account.setBalance(total);
		Account saveAccount = accountRepository.save(account); // save to the database
		
		return AccountMapper.mapToAccountDto(saveAccount);
	}


	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
				.collect(Collectors.toList());
		
	}


	@Override
	public void deleteAccount(Long id) {
		
		// condition if the given id account is dosn't Exits in database then show this error
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account Does not Exits.."));
		
		accountRepository.deleteById(id);
		
	}
	
	

}
