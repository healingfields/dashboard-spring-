package ma.showmaker.dashboard.repo;

import ma.showmaker.dashboard.model.User;
import ma.showmaker.dashboard.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepoTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findUserByUsername_HappyPath_ShouldReturn1User() throws Exception {
        User user = new User();
        user.setUsername("idriss");
        user.setPassword("idriss123");
        user.setRole("USER");
        testEntityManager.persist(user);
        testEntityManager.flush();

        User actual = userRepository.findUserByUsername("idriss");

        assertThat(actual).isEqualTo(user);
    }


    @Test
    public void save_HappyPath_shouldSave1User(){

        User user = new User();
        user.setUsername("ali");
        user.setPassword("ali123");
        user.setRole("USER");

        User actual = userRepository.save(user);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotNull();
    }

}
