package com.restful.account.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.restful.account.data.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
	public Account findByUserId(Long userId);

	public List<Account> findAllByUserId(Long userId);
}
