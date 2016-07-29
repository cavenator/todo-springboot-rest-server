package hello.dao;

import hello.domain.Todo;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface TodoDao extends CrudRepository<Todo, Integer> {}
