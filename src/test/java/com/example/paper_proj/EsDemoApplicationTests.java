package com.example.paper_proj;

import com.example.paper_proj.Domain.ESIndexDomain.Author;
import com.example.paper_proj.Domain.ESIndexDomain.AuthorIndex;
import com.example.paper_proj.Domain.Outcome;
import com.example.paper_proj.Domain.Repository.OutcomeRepository;
import com.google.common.collect.Lists;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200)));
        QueryBuilder matchQuery = QueryBuilders.matchQuery("title","nature of sports").analyzer("standard");
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
        long totalHits = hits.getTotalHits();
        JSONObject objects = new JSONObject();
        JSONArray array = new JSONArray();
        for(SearchHit hit : searchHits){
            System.out.println(hit.toString());
            Map<String,Object> sourceAsMap = hit.getSourceAsMap();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",(String)sourceAsMap.get("id"));
            jsonObject.put("title",(String) sourceAsMap.get("title"));
            jsonObject.put("abstr",(String)sourceAsMap.get("abstr"));
            jsonObject.put("authors",(List<Author>)sourceAsMap.get("authors"));
            jsonObject.put("keywords",(List<String>)sourceAsMap.get("keywords"));
            jsonObject.put("pdf",(String)sourceAsMap.get("pdf"));
            jsonObject.put("year",(Integer)sourceAsMap.get("year"));
            jsonObject.put("url",(List<String>)sourceAsMap.get("url"));
            jsonObject.put("doi",(String)sourceAsMap.get("doi"));
        }
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
}
