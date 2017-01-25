package mybatis.mappers;

import java.util.List;

import beans.AvaibleService;

public interface AvaibleServiceMapper {

	public List<AvaibleService> getAllAvaibleServices();
	public void insert(AvaibleService avaibleService);
	
}
