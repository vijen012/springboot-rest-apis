package com.restful.account.controller;

import java.util.List;

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

import com.restful.account.data.AccountRequestData;
import com.restful.account.data.AccountResponseData;
import com.restful.account.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;

	/*
	 * @GetMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
	 * public ResponseEntity<AccountResponseData> getAccountDetails(
	 * 
	 * @RequestParam(value = "userId", required = true) Long userId) { return new
	 * ResponseEntity<>(accountService.findAccount(userId), HttpStatus.OK); }
	 */

	@GetMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AccountResponseData>> getAccountsDetail(
			@RequestParam(value = "userId", required = true) Long userId) {
		return new ResponseEntity<>(accountService.findAllAccount(userId), HttpStatus.OK);
	}

	@PostMapping(path = "/accounts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountResponseData> createAccount(
			@RequestBody @Valid AccountRequestData accountRequestData) {
		return new ResponseEntity<>(accountService.saveAccount(accountRequestData), HttpStatus.CREATED);
	}
}
