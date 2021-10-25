package boot.demo.request;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class EditBookRequest {

    @NotNull
    private String title;

}
