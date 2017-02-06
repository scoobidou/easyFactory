package mybatis.mappers;

import java.util.List;

import beans.AvaibleService;

public interface AvaibleServiceMapper {

	public List<AvaibleService> getAllAvaibleServices();
	public List<AvaibleService> getAllAvaibleServicesByType(String type);
	public List<AvaibleService> getAvaibleServicesById(List<Long> idList);
	public void insert(AvaibleService avaibleService);
	
}
