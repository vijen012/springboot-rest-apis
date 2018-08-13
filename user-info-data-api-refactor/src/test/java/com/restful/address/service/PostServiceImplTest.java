package com.restful.address.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.restful.address.data.Address;
import com.restful.address.data.AddressRequestData;
import com.restful.address.data.AddressResponseData;
import com.restful.address.repos.AddressRepository;
import com.restful.exception.AddressNotFoundException;
import com.restful.exception.UserNotFoundException;
import com.restful.mock.MockTestData;
import com.restful.user.data.User;
import com.restful.user.repos.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceImplTest {

	@Mock
	private AddressRepository addressRepository;

	@Mock
	private UserRepository userRepository;

	@Autowired
	@InjectMocks
	private AddressServiceImpl addressService;

	private MockTestData mockTestData;

	@Before
	public void setUp() throws Exception {
		// MockitoAnnotations.initMocks(this);
		mockTestData = new MockTestData();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void findAllAddressByUserId_ShouldReturnAllTheAddressForTheUserWhenUserIsExistAndAddressIsNotNullOrEmpty() {
		User user = mockTestData.getUser();
		List<Address> addressList = mockTestData.getAddressList();
		user.setAddrssList(addressList);
		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		assertThat(addressService.findAllAddressByUserId(anyLong())).hasSize(2);
		verify(userRepository, times(1)).findById(anyLong());
	}

	@Test
	public void findAllAddressByUserId_ShouldReturnEmptyListForTheUserWhenUserIsExistAndAddressIsNullOrEmpty() {
		User user = mockTestData.getUser();
		List<Address> addressList = new ArrayList<>();
		user.setAddrssList(addressList);
		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		assertThat(addressService.findAllAddressByUserId(anyLong())).hasSize(0);
		verify(userRepository, times(1)).findById(anyLong());
	}

	@Test(expected = UserNotFoundException.class)
	public void findAllAddressByUserId_ShouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {
		Optional<User> optionalUser = Optional.empty();
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		addressService.findAllAddressByUserId(anyLong());
		verify(userRepository, times(1)).findById(anyLong());
	}

	@Test
	public void findAddress_ShouldReturnTheAddressWhenAddressIsExist() {
		AddressResponseData addressResponseData = mockTestData.getAddressResponseData();
		Optional<Address> optionalAddress = Optional.of(mockTestData.getAddress());
		when(addressRepository.findById(anyLong())).thenReturn(optionalAddress);
		assertThat(addressService.findAddress(anyLong()).toString()).isEqualTo(addressResponseData.toString());
		verify(addressRepository, times(1)).findById(anyLong());
	}

	@Test(expected = AddressNotFoundException.class)
	public void findAddress_ShouldThrowAddressNotFoundExceptionWhenAddressIdDoesNotExist() {
		Optional<Address> optionalAddress = Optional.empty();
		when(addressRepository.findById(anyLong())).thenReturn(optionalAddress);
		addressService.findAddress(anyLong());
		verify(addressRepository, times(1)).findById(anyLong());
	}

	@Test
	public void saveAddress_ShouldReturnSavedAddressWhenUserIdIsExist() {
		User user = mockTestData.getUser();
		Address address = mockTestData.getAddress();
		AddressRequestData addressRequestData = mockTestData.getAddressRequestData();
		AddressResponseData addressResponseData = mockTestData.getAddressResponseData();

		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		when(addressRepository.save(any(Address.class))).thenReturn(address);
		assertThat(addressService.saveAddress(anyLong(), addressRequestData).toString())
				.isEqualTo(addressResponseData.toString());
		verify(userRepository, times(1)).findById(anyLong());
		verify(addressRepository, times(1)).save(any(Address.class));
	}

	@Test(expected = UserNotFoundException.class)
	public void saveAddress_ShouldThrowUserNotFoundWhenUserIdDoesNotExist() {
		Address address = mockTestData.getAddress();
		AddressRequestData addressRequestData = mockTestData.getAddressRequestData();
		Optional<User> optionalUser = Optional.empty();
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		addressService.saveAddress(anyLong(), addressRequestData);
		verify(userRepository, times(1)).findById(anyLong());
	}

	@Test
	public void updateAddress_ShouldReturnUpdatedAddressWhenUserIdAndAddressIdExist() {
		User user = mockTestData.getUser();
		Address address = mockTestData.getAddress();
		AddressRequestData addressRequestData = mockTestData.getAddressRequestData();
		AddressResponseData addressResponseData = mockTestData.getAddressResponseData();
		Optional<User> optionalUser = Optional.of(user);
		Optional<Address> optionalPost = Optional.of(address);
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		when(addressRepository.findById(anyLong())).thenReturn(optionalPost);
		when(addressRepository.save(any(Address.class))).thenReturn(address);

		assertThat(addressService.updateAddress(101L, 201L, addressRequestData).toString())
				.isEqualTo(addressResponseData.toString());
		verify(userRepository, times(1)).findById(anyLong());
		verify(addressRepository, times(1)).findById(anyLong());
		verify(addressRepository, times(1)).save(any(Address.class));
	}

	@Test(expected = UserNotFoundException.class)
	public void updateAddress_ShouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {
		AddressRequestData addressRequestData = mockTestData.getAddressRequestData();
		Optional<User> optionalUser = Optional.empty();
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		addressService.updateAddress(101L, 201L, addressRequestData);
		verify(userRepository, times(1)).findById(anyLong());
	}

	@Test
	public void updateAllAddress_ShouldReturnAllTheUpdatedAddressWhenUserIsExist() {
		User user = mockTestData.getUser();
		List<Address> addressList = mockTestData.getAddressList();
		List<AddressRequestData> addressRequestDataList = mockTestData.getAddressRequstDataList();
		List<AddressResponseData> addressResponseDataList = mockTestData.getAddressResponseDataList();

		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		when(addressRepository.saveAll(anyIterable())).thenReturn(addressList);

		assertThat(addressService.updateAllAddress(anyLong(), addressRequestDataList).toString())
				.isEqualTo(addressResponseDataList.toString());
		verify(userRepository, times(1)).findById(anyLong());
		verify(addressRepository, times(1)).saveAll(anyIterable());
	}

	@Test(expected = UserNotFoundException.class)
	public void updateAllAddress_ShouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {
		List<AddressRequestData> addressRequestDataList = mockTestData.getAddressRequstDataList();
		Optional<User> optionalUser = Optional.empty();
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		addressService.updateAllAddress(anyLong(), addressRequestDataList);
		verify(userRepository, times(1)).findById(anyLong());
	}

	@Test
	public void deleteAddress_ShouldReturnHttpStatusNoContentWhenAddressIsExist() {
		Address address = mockTestData.getAddress();
		Optional<Address> optionalAddress = Optional.of(address);
		when(addressRepository.findById(anyLong())).thenReturn(optionalAddress);
		doNothing().when(addressRepository).deleteById(anyLong());
		assertThat(addressService.deleteAddress(anyLong())).isEqualTo(HttpStatus.NO_CONTENT);
		verify(addressRepository, times(1)).findById(anyLong());
		verify(addressRepository, times(1)).deleteById(anyLong());
	}

	@Test
	public void deleteAddress_ShouldReturnHttpStatusAcceptedWhenAddressDoesNotExist() {
		Optional<Address> optionalAddress = Optional.empty();
		when(addressRepository.findById(anyLong())).thenReturn(optionalAddress);
		assertThat(addressService.deleteAddress(anyLong())).isEqualTo(HttpStatus.ACCEPTED);
		verify(addressRepository, times(1)).findById(anyLong());
	}

	@Test
	public void deleteAllAddress_ShouldReturnHttpStatusNoContentWhenUserIsExistAndAddressListIsNotEmpty() {
		User user = mockTestData.getUser();
		List<Address> addressList = mockTestData.getAddressList();
		user.setAddrssList(addressList);
		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		doNothing().when(addressRepository).deleteAll(anyIterable());
		assertThat(addressService.deleteAllAddress(anyLong())).isEqualTo(HttpStatus.NO_CONTENT);
		verify(userRepository, times(1)).findById(anyLong());
		verify(addressRepository, times(1)).deleteAll(anyIterable());
	}

	@Test
	public void deleteAllAddress_ShouldReturnHttpStatusAcceptedWhenUserIsExistButAddressListIsEmpty() {
		User user = mockTestData.getUser();
		List<Address> addressList = new ArrayList<>();
		user.setAddrssList(addressList);
		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		assertThat(addressService.deleteAllAddress(anyLong())).isEqualTo(HttpStatus.ACCEPTED);
		verify(userRepository, times(1)).findById(anyLong());
	}

	@Test(expected = UserNotFoundException.class)
	public void deleteAllAddress_ShouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {
		Optional<User> optionalUser = Optional.empty();
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		addressService.deleteAllAddress(anyLong());
		verify(userRepository, times(1)).findById(anyLong());
	}

}
