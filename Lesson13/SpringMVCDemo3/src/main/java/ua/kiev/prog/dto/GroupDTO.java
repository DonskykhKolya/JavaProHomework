package ua.kiev.prog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GroupDTO {
    private long id;
    private String name;
    private int count;

    public GroupDTO(long id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }
}
