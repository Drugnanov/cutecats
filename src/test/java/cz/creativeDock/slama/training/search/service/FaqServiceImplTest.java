package cz.creativeDock.slama.training.search.service;

import cz.creativeDock.slama.training.search.repository.FaqRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

//ToDo Service tests
@RunWith(MockitoJUnitRunner.class)
public class FaqServiceImplTest {

    @Mock
    private FaqRepository mockedFaqRepository;

    private FaqService FaqService;

    @Before
    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        userService = new UserServiceDefault(mockedUserRepository, mockedPasswordEncoder);
    }
//
//    @Test
//    public void testGetAllfaqs() {
//        when(mockedUserRepository.findAllByOrderBySurnameAsc()).thenReturn(initUsers);
//
//        List<User> actualUsers = userService.getAllUsers();
//
//        assertThat(actualUsers, containsInAnyOrder(initUsers.toArray()));
//    }
}