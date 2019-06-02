package com.example.paper_proj;

import com.example.paper_proj.Domain.ESIndexDomain.Author;
import com.example.paper_proj.Domain.ESIndexDomain.AuthorIndex;
import com.example.paper_proj.Domain.Outcome;
import com.example.paper_proj.Domain.Repository.OutcomeRepository;
import com.google.common.collect.Lists;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DdemoApplication.class)
public class EsDemoApplicationTests {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private OutcomeRepository outcomeRepository;

    @Test
    public void testCreateIndex() {
        elasticsearchTemplate.createIndex(AuthorIndex.class);
    }

    @Test
    public void testCreateIndex2() {
        elasticsearchTemplate.createIndex(Outcome.class);
    }

    @Test
    public void analyserTest() throws IOException, JSONException {
        String word = "nature";
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200)));
        QueryBuilder matchQuery = QueryBuilders.matchQuery("title",word).analyzer("standard");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(matchQuery);
        sourceBuilder.from(0);
        sourceBuilder.size(10000);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("aminer");
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        List<Outcome> outcomes = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        int cnt = 0;
        for(SearchHit hit : searchHits){
            if(cnt++ > 10)
                break;
            Map source = hit.getSourceAsMap();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",(String)source.get("id"));
            jsonObject.put("keywords",(List<String>)source.get("keywords"));
            jsonObject.put("authors",(List<Author>)source.get("authors"));
            jsonArray.add(jsonObject);
        }
        System.out.println(jsonArray.toString());
        System.out.println(outcomes.size());
    }

    @Test
    public void getByTitleOrAbstr(){
        QueryBuilder matchQuery = QueryBuilders.matchQuery("title","nature of sports").analyzer("standard");
        Iterable<Outcome> outputs = outcomeRepository.search(matchQuery);
        List<Outcome> list = Lists.newArrayList(outputs);
        Set<String> set = new HashSet<>();
        for(Outcome outcome:list){
            set.add(outcome.getId());
        }
        List<Outcome> output = new ArrayList<>();
        for(Object id : set.toArray()){
            output.add(outcomeRepository.findById(id.toString()).get());
        }
    }

    @Test
    public void jsonTest(){
        List<String> list = new ArrayList<>();
        list.add("{1}");
        list.add("{2}");
        System.out.println(list.toString());
    }
}
