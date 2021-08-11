package backend.datenbasis;

import java.util.List;
import java.util.Optional;

public interface EntityManager<E> {
	
	public Optional<E> get(String id);
	
	public void delete(String id);
	
	public void save(E e);
	
	public void update(String id, String [] params);
	
	List<E> getAll();
}
