package com.spring.aiwebapp.model.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private List<T> data;
    private int page;
    private int size;
    private int total;

}
