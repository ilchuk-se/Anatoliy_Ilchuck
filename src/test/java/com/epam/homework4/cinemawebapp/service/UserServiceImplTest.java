package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.dto.UserDto;
import com.epam.homework4.cinemawebapp.mapper.UserMapper;
import com.epam.homework4.cinemawebapp.model.User;
import com.epam.homework4.cinemawebapp.repository.IUserRepository;
import com.epam.homework4.cinemawebapp.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private IUserRepository userRepository;

    private final Long id = Long.parseLong("1");
    private final String login = "login";
    private final String password = "password";

    @Test
    void getUserByIdTest(){
        //given
        User expectedUser = new User();
        expectedUser.setId(id);
        Optional<User> expectedUserOptional = Optional.of(expectedUser);

        when(userRepository.findById(id)).thenReturn(expectedUserOptional);

        UserDto expectedUserDto = UserDto.builder().id(id).build();
        //when
        UserDto actualUserDto = userService.getUserById(id);

        //then
        assertEquals(expectedUserDto, actualUserDto);
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void getUserAuthorizedTest(){
        //given
        User userToAuth = new User();
        userToAuth.setLogin(login);
        userToAuth.setPassword(password);

        UserDto expectedUserDto = UserDto.builder().login(login).build();

        when(userRepository.findByLoginAndPassword(login, password)).thenReturn(userToAuth);

        //when
        UserDto actualUserDto = userService.getUserAuthorized(userToAuth);

        //then
        assertEquals(expectedUserDto, actualUserDto);
        verify(userRepository, times(1)).findByLoginAndPassword(login, password);
    }

    @Test
    void listUsersTest(){
        //given
        User user = new User();
        user.setId(id);

        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(user);

        user.setId(Long.parseLong("2"));
        expectedUsers.add(user);

        List<UserDto> expectedUserDtos = UserMapper.INSTANCE.mapUserDtos(expectedUsers);

        Iterable<User> expectedUserIterable = expectedUsers;

        when(userRepository.findAll()).thenReturn(expectedUserIterable);

        //when
        List<UserDto> actualUserDtos = userService.listUsers();

        //then
        assertEquals(actualUserDtos, expectedUserDtos);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void createUserTest(){
        //given
        User userToCreate = new User();
        userToCreate.setLogin(login);
        userToCreate.setPassword(password);

        when(userRepository.save(userToCreate)).thenReturn(userToCreate);

        //when
        UserDto createdUserDto = userService.createUser(userToCreate);

        //then
        verify(userRepository, times(1)).save(userToCreate);
        assertEquals(createdUserDto, UserMapper.INSTANCE.mapUserDto(userToCreate));
    }

    @Test
    void updateUserTest(){
        //given
        User userInDb = new User();
        userInDb.setId(id);
        Optional<User> userInDbOptional = Optional.of(userInDb);

        when(userRepository.findById(id)).thenReturn(userInDbOptional);

        UserDto dataToUpdate = UserDto
                .builder()
                .login("newlogin")
                .build();

        User updatedUser = new User();
        updatedUser.setId(id);
        updatedUser.setLogin("newlogin");

        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        //when
        UserDto createdUserDto = userService.updateUser(id, dataToUpdate);

        //then
        verify(userRepository, times(1)).save(updatedUser);
    }

    @Test
    void updateUserTest_WhenUserNotExist(){
        //given
        when(userRepository.findById(id)).thenReturn(Optional.empty());
        UserDto dataToUpdate = UserDto.builder().build();

        //then
        assertThrows(NoSuchElementException.class, () -> userService.updateUser(id, dataToUpdate));
    }

    @Test
    void deleteUserTest(){
        //given
        doNothing().when(userRepository).deleteById(id);

        //when
        userService.deleteUser(id);

        //then
        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteUser_WithExceptionTest() {
        doThrow(RuntimeException.class).when(userRepository).deleteById(any());

        assertThrows(RuntimeException.class,
                () -> userService.deleteUser(id));
    }
}
