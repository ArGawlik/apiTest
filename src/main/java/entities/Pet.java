package entities;

import entities.enums.PetStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Pet {
    private int id;
    private Category category;
    private String name;
    private String[] photoUrls;
    private List<Tag> tags;
    private PetStatus status;
}
