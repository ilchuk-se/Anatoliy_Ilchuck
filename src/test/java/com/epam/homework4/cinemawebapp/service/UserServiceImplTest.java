package com.epam.homework4.cinemawebapp.service;

import com.epam.homework4.cinemawebapp.dto.UserDto;
import com.epam.homework4.cinemawebapp.mapper.UserMapper;
import com.epam.homework4.cinemawebapp.model.User;
import com.epam.homework4.cinemawebapp.repository.UserRepository;
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
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

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
        //when
        User actualUser = userService.getById(id);

        //then
        assertEquals(expectedUser, actualUser);
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void getUserAuthorizedTest(){
        //given
        User userToAuth = new User();
        userToAuth.setLogin(login);
        userToAuth.setPassword(password);

        when(userRepository.findByLoginAndPassword(login, password)).thenReturn(userToAuth);

        //when
        User actualUser = userService.getAuthorized(login, password);

        //then
        assertEquals(actualUser, userToAuth);
        verify(userRepository, times(1)).findByLoginAndPassword(login, password);
    }

    @Test
    void listUsersTest(){
        //given
        User user = new User();
        user.setId(id);

        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(user);

        Iterable<User> expectedUserIterable = expectedUsers;

        when(userRepository.findAll()).thenReturn(expectedUserIterable);

        //when
        List<User> actualUsers = userService.getAll();

        //then
        assertEquals(actualUsers, expectedUsers);
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
        User createdUser = userService.create(userToCreate);

        //then
        assertEquals(createdUser, userToCreate);
        verify(userRepository, times(1)).save(userToCreate);
    }

    @Test
    void updateUserTest(){
        //given
        User userInDb = new User();
        userInDb.setId(id);
        Optional<User> userInDbOptional = Optional.of(userInDb);

        when(userRepository.findById(id)).thenReturn(userInDbOptional);

        User userDataToUpdate = new User();
        userDataToUpdate.setId(id);
        userDataToUpdate.setLogin("newlogin");

        when(userRepository.save(userDataToUpdate)).thenReturn(userDataToUpdate);

        //when
        User updatedUser = userService.update(id, userDataToUpdate);

        //then
        verify(userRepository, times(1)).findById(id);
        verify(userRepository, times(1)).save(userDataToUpdate);
        assertEquals(userDataToUpdate, updatedUser);
    }

    @Test
    void updateUserTest_WhenUserNotExist(){
        //given
        when(userRepository.findById(id)).thenReturn(Optional.empty());
        User userDataToUpdate = new User();

        //then
        assertThrows(NoSuchElementException.class, () -> userService.update(id, userDataToUpdate));
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void deleteUserTest(){
        //given
        doNothing().when(userRepository).deleteById(id);

        //when
        userService.delete(id);

        //then
        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteUser_WithExceptionTest() {
        doThrow(RuntimeException.class).when(userRepository).deleteById(any());

        assertThrows(RuntimeException.class,
                () -> userService.delete(id));
    }
}
