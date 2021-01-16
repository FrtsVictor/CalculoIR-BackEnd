package com.alterdata.calculo.irpf.User;

import com.alterdata.calculo.irpf.models.User;
import com.alterdata.calculo.irpf.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("Executando testes com mokito sem a tag @WebMVC")
class UserControllerTest {

    MockMvc mockMvc;
    ObjectMapper objectMapper;
    UserRepository userRepo;

//    @BeforeEach
//    void setUp() {
//        userRepo = mock(UserRepository.class);
//        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userRepo)).build();
//        objectMapper = new ObjectMapper();
//    }

    @Test
    void testandoCriarUsuario() throws Exception {
        User usr = new User();
        usr.setUsername("Test Username");
        usr.setPassword("123321");
        usr.setNome("Victor");
        this.mockMvc.perform(post("/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usr)))
                .andExpect(status().isCreated());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(usr.getNome()));
//                .andExpect(content().json("{'nome': 'Victor'}"));
//                .andExpect(jsonPath("email", is(usr.getEmail())))
//                .andExpect(jsonPath("username", is(usr.getUsername())));
//                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(content().json(objectMapper.writeValueAsString(usr)));
        }

    }
