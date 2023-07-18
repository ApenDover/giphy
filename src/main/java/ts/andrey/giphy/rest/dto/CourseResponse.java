package ts.andrey.giphy.rest.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class CourseResponse {

    private String disclaimer;

    private String license;

    private long timestamp;

    private String base;

    private HashMap<String, Double> rates;

}
