package com.example.paper_proj.Domain.ESIndexDomain;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.List;

@Data
@Document(indexName = "authors",type = "_doc",replicas = 0)
public class AuthorIndex {
    @Id
    protected String id;

    @Field(type = FieldType.Text )
    protected String name;

    @Field(type = FieldType.Integer)
    protected Integer n_pubs;

    @Field(type = FieldType.Nested)
    protected List<Tag> tags;

    @Field(type = FieldType.Nested)
    protected List<Pub> pubs;

}
