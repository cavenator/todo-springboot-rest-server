package hello.domain;

import org.springframework.util.StringUtils;

public class TodoDto {

    public String title;
    public String description;

    public TodoDto(){}

    public TodoDto(String title, String description){
        this.title = title;
        this.description = description;
    }

    public boolean isValid(){
        return !StringUtils.isEmpty(title) && !StringUtils.isEmpty(description);
    }
}

