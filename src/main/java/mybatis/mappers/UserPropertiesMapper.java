package mybatis.mappers;

import beans.UserProperties;

public interface UserPropertiesMapper {

	public void insertUserProperties(UserProperties userProperties);

	public UserProperties getUserProperties(Long id);
	
	public UserProperties getUserPropertiesWithoutPassword(Long id);

	public void deleteUserProperties(Long id);

	public UserProperties getUserPropertiesByUser(Long idUser);
	
	public UserProperties getUserPropertiesByUserWithoutPassword(Long idUser);
}
