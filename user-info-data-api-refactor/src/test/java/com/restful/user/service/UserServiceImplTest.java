package com.restful.user.service;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

//	@Mock
//	private UserRepository userRepository;
//
//	@Mock
//	private AccountProxyService accountProxyService;
//
//	@Autowired
//	@InjectMocks
//	private UserServiceImpl userService;
//
//	private MockTestData mockTestData;
//
//	@Before
//	public void setUp() {
//		// Not needed if we have used @RunWith(MockitoJUnitRunner.class) - Enable
//		// Mockito annotations
//		// MockitoAnnotations.initMocks(this);
//		mockTestData = new MockTestData();
//	}
//
//	@Test
//	public void findAllUser_ShouldReturnAllTheUsersWhenRecordsAreExist() {
//		List<User> userList = mockTestData.getUserList();
//		Pageable pageable = PageRequest.of(0, 3, new Sort(Direction.ASC, "firstName"));
//		Page<User> pageUsers = new PageImpl<>(userList, pageable, userList.size());
//		when(userRepository.findAll(pageable)).thenReturn(pageUsers);
//		when(accountProxyService.getAccountDetail(anyLong())).thenReturn(mockTestData.getAccountResponseData());
//		assertThat(userService.findAllUser(0, 3)).hasSize(3);
//		verify(userRepository, times(1)).findAll(pageable);
//		verify(accountProxyService, times(3)).getAccountDetail(anyLong());
//	}
//
//	@Test
//	public void findAllUser_ShouldReturnEmptyUserListWhenThereAreNotRecordInDatabase() {
//		List<User> userList = new ArrayList<>();
//		Pageable pageable = PageRequest.of(0, 3, new Sort(Direction.ASC, "firstName"));
//		Page<User> pageUsers = new PageImpl<>(userList, pageable, userList.size());
//		when(userRepository.findAll(pageable)).thenReturn(pageUsers);
//		assertThat(userService.findAllUser(0, 3)).hasSize(0);
//		verify(userRepository, times(1)).findAll(pageable);
//	}
//
//	@Test
//	public void findUser_ShouldReturnTheUserWhenUserIdIsValidAndAccountObjectIsNotNull() {
//		AccountResponseData accountResponseData = mockTestData.getAccountResponseData();
//		Optional<User> userOptional = Optional.of(mockTestData.getUser());
//		when(userRepository.findById(anyLong())).thenReturn(userOptional);
//		when(accountProxyService.getAccountDetail(anyLong())).thenReturn(accountResponseData);
//		assertThat(userService.findUser(101L)).isEqualTo(userOptional.get());
//		assertThat(userService.findUser(101L).getAccount()).isEqualTo(accountResponseData);
//		verify(userRepository, times(2)).findById(anyLong());
//		verify(accountProxyService, times(2)).getAccountDetail(anyLong());
//	}
//
//	@Test
//	public void findUser_ShouldReturnTheUserWhenUserIdIsValidAndAccountObjectIsNull() {
//		Optional<User> userOptional = Optional.of(mockTestData.getUser());
//		when(userRepository.findById(anyLong())).thenReturn(userOptional);
//		when(accountProxyService.getAccountDetail(anyLong())).thenReturn(null);
//		assertThat(userService.findUser(101L)).isEqualTo(userOptional.get());
//		assertThat(userService.findUser(101L).getAccount()).isEqualTo(null);
//		verify(userRepository, times(2)).findById(anyLong());
//		verify(accountProxyService, times(2)).getAccountDetail(anyLong());
//	}
//
//	@Test(expected = UserNotFoundException.class)
//	public void findUser_ShouldThrowTheUserNotFoundExceptionWhenUserDoesNotExist() {
//		Optional<User> value = Optional.empty();
//		when(userRepository.findById(anyLong())).thenReturn(value);
//		userService.findUser(101L);
//	}
//
//	@Test
//	public void saveUser_ShouldReturnSameUserInstanceAfterSavedWhenNoAddressesAssociateWithUserInstance() {
//		User user = mockTestData.getUser();
//		when(userRepository.save(any(User.class))).thenReturn(user);
//		assertThat(userService.saveUser(user)).isEqualTo(user);
//		verify(userRepository, times(1)).save(user);
//	}
//
//	@Test
//	public void saveUser_ShouldReturnSameUserInstanceAfterSavedWhenAddressesAssociateWithUserInstance() {
//		User user = mockTestData.getUser();
//		user.setAddrssList(mockTestData.getAddressList());
//		when(userRepository.save(any(User.class))).thenReturn(user);
//		assertThat(userService.saveUser(user)).isEqualTo(user);
//		verify(userRepository, times(1)).save(user);
//	}
//
//	@Test
//	public void deleteUser_ShouldReturnHttpStatusNoContentWhenUserIdIsValid() {
//		User user = mockTestData.getUser();
//		Optional<User> userOptional = Optional.of(user);
//		when(userRepository.findById(101L)).thenReturn(userOptional);
//		doNothing().when(userRepository).deleteById(anyLong());
//		assertThat(userService.deleteUser(101L)).isEqualTo(HttpStatus.NO_CONTENT);
//		verify(userRepository, times(1)).findById(anyLong());
//		verify(userRepository, times(1)).deleteById(anyLong());
//	}
//
//	@Test
//	public void deleteUser_ShouldReturnHttpStatusAcceptedWhenUserIdIsInvalid() {
//		Optional<User> userOptional = Optional.empty();
//		when(userRepository.findById(101L)).thenReturn(userOptional);
//		// doThrow(EmptyResultDataAccessException.class).when(userRepository).deleteById(anyLong());
//		assertThat(userService.deleteUser(101L)).isEqualTo(HttpStatus.ACCEPTED);
//		verify(userRepository, times(0)).deleteById(anyLong());
//	}
//
//	@Test
//	public void updateUser_ShouldReturnSameUserInstanceAfterSavedWhenNoAddressesAssociateWithUserInstance() {
//		User user = mockTestData.getUser();
//		Optional<User> userOptional = Optional.of(user);
//		when(userRepository.findById(101L)).thenReturn(userOptional);
//		when(userRepository.save(any(User.class))).thenReturn(user);
//		assertThat(userService.updateUser(101L, user)).isEqualTo(user);
//		verify(userRepository, times(1)).findById(anyLong());
//		verify(userRepository, times(1)).save(user);
//	}
//
//	@Test
//	public void updateUser_ShouldReturnSameUserInstanceAfterSavedWhenAddressesAssociateWithUserInstance() {
//		User user = mockTestData.getUser();
//		Optional<User> userOptional = Optional.of(user);
//		user.setAddrssList(mockTestData.getAddressList());
//		when(userRepository.findById(101L)).thenReturn(userOptional);
//		when(userRepository.save(any(User.class))).thenReturn(user);
//		assertThat(userService.updateUser(101L, user)).isEqualTo(user);
//		verify(userRepository, times(1)).findById(anyLong());
//		verify(userRepository, times(1)).save(user);
//	}
//
//	@Test(expected = UserNotFoundException.class)
//	public void updateUser_ShouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {
//		Optional<User> userOptional = Optional.empty();
//		when(userRepository.findById(101L)).thenReturn(userOptional);
//		userService.updateUser(101L, mockTestData.getUser());
//		verify(userRepository, times(1)).findById(anyLong());
//	}
}
