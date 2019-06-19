package cool.is.chu.stephen.codefellowship;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface PostRepository extends CrudRepository<Post, Long> {
}
