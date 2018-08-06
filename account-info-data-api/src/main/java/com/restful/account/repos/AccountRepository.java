package com.restful.account.repos;

import org.springframework.data.repository.CrudRepository;

import com.restful.account.data.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
	public Account findByUserId(Long userId);
}
