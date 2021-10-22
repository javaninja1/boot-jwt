package boot.demo.domain.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class ObjectIdMapper {

    public String objectIdToString(Long id) {
        return id.toString();
    }

    public Long stringToObjectId(String string) {
        return Long.parseLong(string);
    }

}
