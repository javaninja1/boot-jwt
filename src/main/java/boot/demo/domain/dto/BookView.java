package boot.demo.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookView {

    private String id;

    private UserView creator;

    private String title;


}
