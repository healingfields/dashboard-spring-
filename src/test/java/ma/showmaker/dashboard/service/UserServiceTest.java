package ma.showmaker.dashboard.service;

import ma.showmaker.dashboard.model.User;
import ma.showmaker.dashboard.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void init(){
        userService = new UserService(userRepository);
    }

    @Test
    public void getUserByUsername_HappyMonth_ShouldReturn1User(){

        User user = new User();
        user.setUsername("idriss Test");
        user.setPassword("idriss1234");
        user.setRole("USER");

        when(userRepository.findUserByUsername("idriss Test")).thenReturn(user);

        UserDetails actual = userService.loadUserByUsername("idriss Test");

        verify(userRepository, times(1)).findUserByUsername("idriss Test");
    }
}
