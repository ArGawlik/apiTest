package entities;

import com.store.CategoriesStore;
import com.store.TagsStore;
import entities.enums.PetStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class Pet {
    private String id;
    private Category category;
    private String name;
    private String[] photoUrls;
    private Tag[] tags;
    private PetStatus status;

    public Pet (List<String> dataList) {
        CategoriesStore categoriesStore = CategoriesStore.getInstance();
        TagsStore tagsStore = TagsStore.getInstance();
        this.id = dataList.get(0);
        this.category = categoriesStore.getCategoryFromStore(dataList.get(1));
        this.name = dataList.get(2);
        this.photoUrls = dataList.get(3).split(", ");
        this.tags = Arrays.stream(dataList.get(4).split(", "))
                .map(tagsStore::getTagFromStore).toArray(Tag[]::new);
        this.status = PetStatus.valueOf(dataList.get(5));
    }
}
