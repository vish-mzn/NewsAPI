package com.stackroute.userservice.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.services.SecurityTokenGenerator;
import com.stackroute.userservice.services.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService service;
	
	@MockBean
	private SecurityTokenGenerator securityTokenGenerator;
	
	private User user;

	@InjectMocks
	private UserController userController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		user = new User("vish123", "Vishesh", "Pahuja", "pass123", new Date());
	}
	
	@Test
	public void testRegisterUser() throws Exception {
		when(service.saveUser(user)).thenReturn(true);
		mockMvc.perform(post("/api/user/register").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(jsonToString(user))).andExpect(status()
						.isCreated()).andDo(print());
		verify(service, times(1)).saveUser(Mockito.any(User.class));
		verifyNoMoreInteractions(service);
	}
	
	@Test
	public void testLoginUser() throws Exception {
		
		String userId = "vish123";
		String password = "pass123";
		when(service.saveUser(user)).thenReturn(true);
		when(service.findByUserIdAndPassword(userId, password)).thenReturn(user);

		mockMvc.perform(post("/api/user/login").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user))).andExpect(status().isOk());

		verify(service, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
		verifyNoMoreInteractions(service);
	}	
	
	// Parsing JSON format data into String format
	private String jsonToString(final Object object) {
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			result = "Json processing error";
		}
		return result;
	}

}
