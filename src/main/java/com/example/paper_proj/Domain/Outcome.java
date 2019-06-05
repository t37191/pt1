package com.example.paper_proj.Domain;


import com.example.paper_proj.Domain.ESIndexDomain.Author;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.List;

@Data
@Document(indexName = "aminer",type = "_doc",replicas = 0)
public class Outcome {
    @Id
    protected String id;

    @Field(type = FieldType.Text )
    protected String title;

    @Field(type = FieldType.Text)
    protected String abstr;

    @Field(type = FieldType.Nested)
    protected List<Author> authors;

    @Field(type = FieldType.Text)
    protected List<String> keywords ;

    @Field(index = false, type=FieldType.Text)
    protected String pdf;

    @Field(type = FieldType.Integer)
    protected Integer year;

    @Field(type = FieldType.Text)
    protected List<String> url;

    @Field(type = FieldType.Text)
    protected String doi;

}
