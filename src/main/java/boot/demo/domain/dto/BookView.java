package boot.demo.domain.dto;

import lombok.Data;

@Data
public class BookView {

    private String id;

    private UserView creator;

    private String title;


}
