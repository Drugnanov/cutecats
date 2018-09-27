package cz.creativeDock.slama.training.search.controller;

import cz.creativeDock.slama.training.search.repository.FaqRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

//ToDo controller test
@RunWith(MockitoJUnitRunner.class)
public class FaqControllerTest {

    @Mock
    private FaqRepository mockedFaqRepository;

    @Before
    public void setUp() {
        //
    }

    @Test
    public void testGetAllUsers() {
//        when(mockedUserRepository.findAllByOrderBySurnameAsc()).thenReturn(initUsers);
//
//        List<UserDto> actualUsers = userController.getAllUsers();
//
//        List<UserDto> expectedUsers = userConvertor.convertToDto(initUsers);
//        assertThat(actualUsers, containsInAnyOrder(expectedUsers.toArray()));
    }
}