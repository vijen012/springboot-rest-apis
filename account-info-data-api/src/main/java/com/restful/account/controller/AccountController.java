package com.restful.account.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restful.account.data.Account;
import com.restful.account.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> getAccountDetails(@RequestParam(value = "userId", required = true) Long userId) {
		return new ResponseEntity<Account>(accountService.findAccount(userId), HttpStatus.OK);
	}

	@PostMapping(path = "/accounts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> createAccount(@RequestBody @Valid Account account) {
		return new ResponseEntity<Account>(accountService.saveAccount(account), HttpStatus.CREATED);
	}
}
