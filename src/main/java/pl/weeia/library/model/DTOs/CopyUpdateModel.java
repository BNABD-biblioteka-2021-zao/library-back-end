package pl.weeia.library.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CopyUpdateModel {

    private Long id;
    private String ISBN;
    private LocalDate publishDate;
    private Long pageAmount;
    private String publisher;

}
