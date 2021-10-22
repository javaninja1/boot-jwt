package boot.demo.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class EditBookRequest {


    @NotNull
    private String title;

}
