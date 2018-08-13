package com.restful.address.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.restful.address.data.Address;
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
	public List<Address> findAllAddressByUserId(Long userId) {
		Optional<User> userOptional = userRepositroy.findById(userId);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("UserId " + userId + " doesn't exist !!");
		}
		return userOptional.get().getAddressList();
	}

	@Override
	public Address findAddress(Long addressId) {
		Optional<Address> addressOptional = addressRepository.findById(addressId);
		if (!addressOptional.isPresent()) {
			throw new AddressNotFoundException("AddressId " + addressId + " doesn't exist !!");
		}
		return addressOptional.get();
	}

	@Override
	public Address saveAddress(Long userId, Address address) {
		Optional<User> userOptional = userRepositroy.findById(userId);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("UserId " + userId + " doesn't exist !!");
		}
		User user = userOptional.get();
		address.setUser(user);
		return addressRepository.save(address);
	}

	@Override
	public Address updateAddress(Long userId, Long addressId, Address address) {
		Optional<User> userOptional = userRepositroy.findById(userId);
		if (userOptional.isPresent()) {
			Optional<Address> addressOptional = addressRepository.findById(addressId);
			if (addressOptional.isPresent()) {
				User user = userOptional.get();
				address.setUser(user);
				return addressRepository.save(address);
			} else {
				throw new AddressNotFoundException("AddressId " + addressId + "doesn't exist for user" + userId);
			}
		} else {
			throw new UserNotFoundException("UserId " + userId + " doesn't exist !!");
		}
	}

	@Override
	public List<Address> updateAllAddress(Long userId, List<Address> addressList) {
		Optional<User> userOptional = userRepositroy.findById(userId);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("UserId " + userId + " doesn't exist !!");
		}
		User user = userOptional.get();
		addressList.forEach(post -> post.setUser(user));
		return (List<Address>) addressRepository.saveAll(addressList);
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
