package mybatis.mappers;

import beans.User;

public interface UserMapper {

	public void insert(User user);
	public User getByMail(String userMail);
}
