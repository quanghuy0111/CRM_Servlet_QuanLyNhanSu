package cybersoft.java09.service;

import cybersoft.java09.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import cybersoft.java09.dto.UserDto;
import cybersoft.java09.entity.User;
public class UserService {
	private UserRepository userRepository;
	
	
	public UserService() {
		userRepository = new UserRepository();
	}
	
	public List<UserDto> getAll(){   //Chuyển data từ entity sang DTO
		List<User> usersEntity = userRepository.getAlluser();
		List<UserDto> usersDto = new ArrayList<UserDto>();
		for(User user : usersEntity)
		{
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setFullName(user.getFullName());
			usersDto.add(userDto);
		
		}
		
	
		
		return usersDto;
	}
	
	
	

	public List<UserDto> getAllUserRole(){ //Chuyển data từ entity sang DTO
		return userRepository.findAllUserRole();
	}
	
}
