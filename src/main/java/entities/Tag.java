package entities;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Tag {
    private int id;
    private String name;

    @Override
    public String toString() {
        return "Tag{"+
                "id="+id+
                ", name="+name+
                "}";
    }
}
