package com.restful.address.service;

import java.util.List;
import java.util.Optional;

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

	@Override
	public List<AddressResponseData> findAllAddressByUserId(Long userId) {
		AddressDataMapper addressDataMapper = new AddressDataMapper();
		Optional<User> userOptional = userRepositroy.findById(userId);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("UserId " + userId + " doesn't exist !!");
		}
		List<AddressResponseData> addressResponseDataList = addressDataMapper
				.getAddressResponseDataList(userOptional.get().getAddressList());
		return addressResponseDataList;
	}

	@Override
	public AddressResponseData findAddress(Long addressId) {
		AddressDataMapper addressDataMapper = new AddressDataMapper();
		Optional<Address> addressOptional = addressRepository.findById(addressId);
		if (!addressOptional.isPresent()) {
			throw new AddressNotFoundException("AddressId " + addressId + " doesn't exist !!");
		}
		AddressResponseData addressResponseData = addressDataMapper.getAddressResponseData(addressOptional.get());
		return addressResponseData;
	}

	@Override
	public AddressResponseData saveAddress(Long userId, AddressRequestData addressRequestData) {
		AddressDataMapper addressDataMapper = new AddressDataMapper();
		Optional<User> userOptional = userRepositroy.findById(userId);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("UserId " + userId + " doesn't exist !!");
		}
		User user = userOptional.get();
		Address address = addressDataMapper.getAddress(addressRequestData);
		address.setUser(user);
		AddressResponseData addressResponseData = addressDataMapper
				.getAddressResponseData(addressRepository.save(address));
		return addressResponseData;
	}

	@Override
	public AddressResponseData updateAddress(Long userId, Long addressId, AddressRequestData addressRequestData) {
		AddressDataMapper addressDataMapper = new AddressDataMapper();
		Optional<User> userOptional = userRepositroy.findById(userId);
		if (userOptional.isPresent()) {
			Optional<Address> addressOptional = addressRepository.findById(addressId);
			if (addressOptional.isPresent()) {
				User user = userOptional.get();
				Address address = addressDataMapper.getAddress(addressRequestData);
				address.setUser(user);
				AddressResponseData addressResponseData = addressDataMapper
						.getAddressResponseData(addressRepository.save(address));
				return addressResponseData;
			} else {
				throw new AddressNotFoundException("AddressId " + addressId + "doesn't exist for user" + userId);
			}
		} else {
			throw new UserNotFoundException("UserId " + userId + " doesn't exist !!");
		}
	}

	@Override
	public List<AddressResponseData> updateAllAddress(Long userId, List<AddressRequestData> addressRequestDataList) {
		AddressDataMapper addressDataMapper = new AddressDataMapper();
		Optional<User> userOptional = userRepositroy.findById(userId);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("UserId " + userId + " doesn't exist !!");
		}
		User user = userOptional.get();
		List<Address> addresses = addressDataMapper.getAddressList(addressRequestDataList);
		addresses.forEach(post -> post.setUser(user));
		addresses = (List<Address>) addressRepository.saveAll(addresses);
		List<AddressResponseData> addressResponseDataList = addressDataMapper.getAddressResponseDataList(addresses);
		return addressResponseDataList;
	}

	@Override
	public HttpStatus deleteAddress(Long addressId) {
		Optional<Address> addressOptional = addressRepository.findById(addressId);
		if (!addressOptional.isPresent()) {
			return HttpStatus.ACCEPTED;
		}
		// Address address = addressOptional.get();
		// address.setUser(null);
		addressRepository.deleteById(addressId);
		return HttpStatus.NO_CONTENT;
	}

	@Override
	public HttpStatus deleteAllAddress(Long userId) {
		Optional<User> userOptional = userRepositroy.findById(userId);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("UserId " + userId + " doesn't exist !!");
		} else if (userOptional.get().getAddressList() == null || userOptional.get().getAddressList().isEmpty()) {
			return HttpStatus.ACCEPTED;
		}
		User user = userOptional.get();
		user.getAddressList().forEach(address -> address.setUser(null));
		addressRepository.deleteAll(user.getAddressList());
		return HttpStatus.NO_CONTENT;
	}

}
