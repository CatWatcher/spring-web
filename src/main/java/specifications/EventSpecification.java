package specifications;


import com.example.springweb.entity.Event;
import org.springframework.data.jpa.domain.Specification;

public class EventSpecification {
    // событие по названию
    public static Specification<Event> eventByTitle (String title) {


        // рут и квери спринг создает автоматически
        return (Specification<Event>) (root, criteriaQuery, criteriaBuilder) ->
        criteriaBuilder.equal(root.get("eventTitle"), title);
    };
}
