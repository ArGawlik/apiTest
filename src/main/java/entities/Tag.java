package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
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
