package nl.inholland.myfirstapi.dao;

import nl.inholland.myfirstapi.models.ApiKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiKeyRepository extends CrudRepository<ApiKey, String> {
}
