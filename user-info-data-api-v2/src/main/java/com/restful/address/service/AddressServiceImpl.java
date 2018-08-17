package com.restful.address.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.restful.address.data.Address;
import com.restful.address.data.AddressDataMapper;
import com.restful.address.data.AddressRequestData;
import com.restful.address.data.AddressResponseData;
import com.restful.address.repos.AddressRepository;
import com.restful.exception.AddressNotFoundException;
import com.restful.exception.UserNotFoundException;
import com.restful.user.data.User;
import com.restful.user.repos.UserRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepositroy;

	@Autowired
	private Logger logger;

	@Override
	public List<AddressResponseData> findAllAddressByUserId(Long userId) {
		logger.trace(userId + " Enter --->");
		AddressDataMapper addressDataMapper = new AddressDataMapper();
		logger.info("fetching user details for UserId: " + userId);
		Optional<User> userOptional = userRepositroy.findById(userId);
		if (!userOptional.isPresent()) {
			UserNotFoundException userNotFoundException = new UserNotFoundException(
					"UserId " + userId + " doesn't exist !!");
			logger.error(userNotFoundException + "<--- Exit");
			throw userNotFoundException;
		}
		List<AddressResponseData> addressResponseDataList = addressDataMapper
				.getAddressResponseDataList(userOptional.get().getAddressList());
		logger.trace(userId + " <--- Exit");
		return addressResponseDataList;
	}

	@Override
	public AddressResponseData findAddress(Long addressId) {
		logger.trace(addressId + " Enter --->");
		AddressDataMapper addressDataMapper = new AddressDataMapper();
		logger.info("fetching address details for addressId: " + addressId);
		Optional<Address> addressOptional = addressRepository.findById(addressId);
		if (!addressOptional.isPresent()) {
			AddressNotFoundException addressNotFoundException = new AddressNotFoundException(
					"AddressId " + addressId + " doesn't exist !!");
			logger.error(addressNotFoundException + " <--- Exit");
			throw addressNotFoundException;
		}
		AddressResponseData addressResponseData = addressDataMapper.getAddressResponseData(addressOptional.get());
		logger.trace(addressId + " <--- Exit");
		return addressResponseData;
	}

	@Override
	public AddressResponseData saveAddress(Long userId, AddressRequestData addressRequestData) {
		logger.trace(userId + " Enter --->");
		AddressDataMapper addressDataMapper = new AddressDataMapper();
		logger.info("fetching the records for userId: " + userId);
		Optional<User> userOptional = userRepositroy.findById(userId);
		if (!userOptional.isPresent()) {
			UserNotFoundException userNotFoundException = new UserNotFoundException(
					"UserId " + userId + " doesn't exist !!");
			logger.error(userNotFoundException + " <--- Exit");
			throw userNotFoundException;
		}
		User user = userOptional.get();
		Address address = addressDataMapper.getAddress(addressRequestData);
		address.setUser(user);
		logger.info("persisting address records for userId: " + userId);
		Address savedAddress = addressRepository.save(address);
		AddressResponseData addressResponseData = addressDataMapper.getAddressResponseData(savedAddress);
		logger.trace(userId + " <--- Exit");
		return addressResponseData;
	}

	@Override
	public AddressResponseData updateAddress(Long userId, Long addressId, AddressRequestData addressRequestData) {
		logger.trace("UserId: " + userId + " AddressId: " + addressId + " Enter -->");
		AddressDataMapper addressDataMapper = new AddressDataMapper();
		logger.info("fetching the records for userId: " + userId);
		Optional<User> userOptional = userRepositroy.findById(userId);
		if (userOptional.isPresent()) {
			logger.info("fetching the records for addressId: " + addressId);
			Optional<Address> addressOptional = addressRepository.findById(addressId);
			if (addressOptional.isPresent()) {
				User user = userOptional.get();
				Address address = addressDataMapper.getAddress(addressRequestData);
				address.setUser(user);
				logger.info("persisting records for addressId: " + addressId);
				Address savedAddress = addressRepository.save(address);
				AddressResponseData addressResponseData = addressDataMapper.getAddressResponseData(savedAddress);
				logger.trace("UserId: " + userId + " AddressId: " + addressId + " <--- Exit");
				return addressResponseData;
			} else {
				AddressNotFoundException addressNotFoundException = new AddressNotFoundException(
						"AddressId " + addressId + "doesn't exist for user" + userId);
				logger.error(addressNotFoundException + " <--- Exit");
				throw addressNotFoundException;
			}
		} else {
			UserNotFoundException userNotFoundException = new UserNotFoundException(
					"UserId " + userId + " doesn't exist !!");
			logger.error(userNotFoundException + " <--- Exit");
			throw userNotFoundException;
		}
	}

	@Override
	public List<AddressResponseData> updateAllAddress(Long userId, List<AddressRequestData> addressRequestDataList) {
		logger.trace(userId + " Enter --->");
		AddressDataMapper addressDataMapper = new AddressDataMapper();
		logger.info("fetching the records for userId: " + userId);
		Optional<User> userOptional = userRepositroy.findById(userId);
		if (!userOptional.isPresent()) {
			UserNotFoundException userNotFoundException = new UserNotFoundException(
					"UserId " + userId + " doesn't exist !!");
			logger.error(userNotFoundException + " <--- Exit");
			throw userNotFoundException;
		}
		User user = userOptional.get();
		List<Address> addresses = addressDataMapper.getAddressList(addressRequestDataList);
		addresses.forEach(post -> post.setUser(user));
		logger.info("updating all the address for userId: " + userId);
		addresses = (List<Address>) addressRepository.saveAll(addresses);
		List<AddressResponseData> addressResponseDataList = addressDataMapper.getAddressResponseDataList(addresses);
		logger.trace(userId + " <--- Exit");
		return addressResponseDataList;
	}

	@Override
	public HttpStatus deleteAddress(Long addressId) {
		logger.trace(addressId + " Enter --->");
		logger.info("fetcing records for addressId: " + addressId);
		Optional<Address> addressOptional = addressRepository.findById(addressId);
		if (!addressOptional.isPresent()) {
			logger.trace(addressId + " <--- Exit");
			return HttpStatus.ACCEPTED;
		}
		// Address address = addressOptional.get();
		// address.setUser(null);
		logger.info("deleting records for addressId: " + addressId);
		addressRepository.deleteById(addressId);
		logger.trace(addressId + " <--- Exit");
		return HttpStatus.NO_CONTENT;
	}

	@Override
	public HttpStatus deleteAllAddress(Long userId) {
		logger.trace(userId + " Enter --->");
		logger.info("fetcing records for UserId: " + userId);
		Optional<User> userOptional = userRepositroy.findById(userId);
		if (!userOptional.isPresent()) {
			UserNotFoundException userNotFoundException = new UserNotFoundException(
					"UserId " + userId + " doesn't exist !!");
			logger.error(userNotFoundException + " <--- Exit");
			throw userNotFoundException;
		} else if (userOptional.get().getAddressList() == null || userOptional.get().getAddressList().isEmpty()) {
			logger.trace(userId + " <--- Exit");
			return HttpStatus.ACCEPTED;
		}
		User user = userOptional.get();
		user.getAddressList().forEach(address -> address.setUser(null));
		logger.info("deleting all the address records for userId: " + userId);
		addressRepository.deleteAll(user.getAddressList());
		logger.trace(userId + " <--- Exit");
		return HttpStatus.NO_CONTENT;
	}

}
