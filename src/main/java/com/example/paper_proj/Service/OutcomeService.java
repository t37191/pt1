package com.example.paper_proj.Service;

import com.example.paper_proj.Domain.ESIndexDomain.Author;
import com.example.paper_proj.Domain.ESIndexDomain.Pub;
import com.example.paper_proj.Domain.ESIndexDomain.Tag;
import com.example.paper_proj.Domain.Outcome;
import com.example.paper_proj.Domain.Repository.ClickRepository;
import com.example.paper_proj.Domain.Repository.OutcomeRepository;
import com.example.paper_proj.Domain.paperClick;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpHost;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.collect.Tuple;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class OutcomeService {

    @Autowired
    private OutcomeRepository outcomeRepository;

    @Autowired
    private ClickRepository clickRepository;

    private RestHighLevelClient client = null;

    private Map<Tuple<String,String>,JSONArray> buffer = null;

    public OutcomeService(){
        client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200)));
        buffer = new LinkedHashMap<>();
    }

    //buffer utils
    public Boolean checkBuffer(Tuple<String,String> key){
        return buffer.containsKey(key);
    }

    public void addBuffer(Tuple<String,String> key,JSONArray jsonArray) {
        if (buffer.size() >= 1000) {
            Tuple<String,String> obj = null;
            for (Map.Entry<Tuple<String, String>, JSONArray> entry : buffer.entrySet()) {
                obj = entry.getKey();
                if (obj != null) {
                    break;
                }
            }
            buffer.remove(buffer.get(obj));
        }
        buffer.put(key,jsonArray);
    }

    public JSONArray getBuffer(Tuple<String,String> key){
        JSONArray jsonArray = buffer.get(key);
        buffer.remove(key);
        return jsonArray;
    }

    //根据标题查找论文
    public JSONArray getByTitle(String word) throws IOException {
        Tuple<String,String> key = new Tuple<>("title",word);
        if(checkBuffer(key)){
            JSONArray array = getBuffer(key);
            addBuffer(key,array);
            return array;
        }
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
        JSONArray jsonArray = new JSONArray();
        for(SearchHit hit : searchHits){
            Map source = hit.getSourceAsMap();
            JSONObject jsonObject = new JSONObject();
            String paper_id = (String)source.get("id");
            jsonObject.put("id",paper_id);
            jsonObject.put("title",(String)source.get("title"));
            jsonObject.put("abstr",(String)source.get("abstr"));
            jsonObject.put("authors",(List<Author>)source.get("authors"));
            jsonObject.put("keywords",(List<String>)source.get("keywords"));
            jsonObject.put("pdf",(String)source.get("pdf"));
            jsonObject.put("year",(Integer)source.get("year"));
            if(clickRepository.existsByPaperid(paper_id))
                jsonObject.put("cnt",clickRepository.findByPaperid(paper_id).getClickcnt());
            else{
                jsonObject.put("cnt",0);
            }
            jsonArray.add(jsonObject);
        }
        addBuffer(key,jsonArray);
        return jsonArray;
    }

    //根据关键字查找论文
    public JSONArray getByKeyWords(String keywords) throws IOException {
        Tuple<String,String> key = new Tuple<>("keywords",keywords);
        if(checkBuffer(key)){
            JSONArray array = getBuffer(key);
            addBuffer(key,array);
            return array;
        }
        QueryBuilder matchQuery = QueryBuilders.matchQuery("keywords",keywords).analyzer("standard").fuzziness(Fuzziness.AUTO);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(matchQuery);
        sourceBuilder.from(0);
        sourceBuilder.size(10000);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("aminer");
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        JSONArray jsonArray = new JSONArray();
        for(SearchHit hit : searchHits){
            Map source = hit.getSourceAsMap();
            JSONObject jsonObject = new JSONObject();
            String paper_id = (String)source.get("id");
            jsonObject.put("id",paper_id);
            jsonObject.put("title",(String)source.get("title"));
            jsonObject.put("abstr",(String)source.get("abstr"));
            jsonObject.put("authors",(List<Author>)source.get("authors"));
            jsonObject.put("keywords",(List<String>)source.get("keywords"));
            jsonObject.put("pdf",(String)source.get("pdf"));
            jsonObject.put("year",(Integer)source.get("year"));
            if(clickRepository.existsByPaperid(paper_id))
                jsonObject.put("cnt",clickRepository.findByPaperid(paper_id).getClickcnt());
            else{
                jsonObject.put("cnt",0);
            }
            jsonArray.add(jsonObject);
        }
        addBuffer(key,jsonArray);
        return jsonArray;
    }

    //根据作者查找论文
    public JSONArray getByAuthor(String author) throws IOException {
        Tuple<String,String> key = new Tuple<>("author",author);
        if(checkBuffer(key)){
            JSONArray array = getBuffer(key);
            addBuffer(key,array);
            return array;
        }
        QueryBuilder authorQuery = QueryBuilders.nestedQuery("authors"
                ,QueryBuilders.matchPhraseQuery("authors.name",author)
                ,ScoreMode.Total);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(authorQuery);
        sourceBuilder.from(0);
        sourceBuilder.size(10000);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("aminer");
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        JSONArray jsonArray = new JSONArray();
        for(SearchHit hit : searchHits){
            Map source = hit.getSourceAsMap();
            JSONObject jsonObject = new JSONObject();
            String paper_id = (String)source.get("id");
            jsonObject.put("id",paper_id);
            jsonObject.put("title",(String)source.get("title"));
            jsonObject.put("abstr",(String)source.get("abstr"));
            jsonObject.put("authors",(List<Author>)source.get("authors"));
            jsonObject.put("keywords",(List<String>)source.get("keywords"));
            jsonObject.put("pdf",(String)source.get("pdf"));
            jsonObject.put("year",(Integer)source.get("year"));
            if(clickRepository.existsByPaperid(paper_id))
                jsonObject.put("cnt",clickRepository.findByPaperid(paper_id).getClickcnt());
            else{
                jsonObject.put("cnt",0);
            }
            jsonArray.add(jsonObject);
        }
        addBuffer(key,jsonArray);
        return jsonArray;
    }

    //获取单篇论文信息
    public String getOutcome(String id,boolean flag) throws IOException {
        QueryBuilder matchQuery = QueryBuilders.matchPhraseQuery("id",id);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(matchQuery);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("aminer");
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        Map source = searchHits[0].getSourceAsMap();
        JSONObject jsonObject = new JSONObject();
        String paper_id = (String)source.get("id");
        jsonObject.put("id",paper_id);
        jsonObject.put("title",(String)source.get("title"));
        jsonObject.put("abstr",(String)source.get("abstr"));
        jsonObject.put("authors",(List<Author>)source.get("authors"));
        jsonObject.put("keywords",(List<String>)source.get("keywords"));
        jsonObject.put("pdf",(String)source.get("pdf"));
        jsonObject.put("year",(Integer)source.get("year"));
        if(clickRepository.existsByPaperid(paper_id)){
            Integer cnt = clickRepository.findByPaperid(paper_id).getClickcnt();
            paperClick paper = clickRepository.findByPaperid(paper_id);
            if(flag) {
                jsonObject.put("cnt", cnt + 1);
                paper.setClickcnt(cnt + 1);
                clickRepository.save(paper);
            }
            else {
                jsonObject.put("cnt", cnt);
            }
        }
        else{
            jsonObject.put("cnt",1);
            paperClick paper = new paperClick();
            paper.setClickcnt(1);
            paper.setPaperid(paper_id);
            clickRepository.save(paper);
        }
        return jsonObject.toString();
    }

    //获得专家详细信息
    public String getExpert(String id) throws IOException{
        QueryBuilder matchQuery = QueryBuilders.matchPhraseQuery("id",id);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(matchQuery);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("authors");
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        Map source = searchHits[0].getSourceAsMap();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",(String)source.get("id"));
        jsonObject.put("name",(String)source.get("name"));
        jsonObject.put("n_pubs",(Integer)source.get("n_pubs"));
        jsonObject.put("pubs",(List<Pub>)source.get("pubs"));
        jsonObject.put("tags",(List<Tag>)source.get("tags"));
        return jsonObject.toString();
    }

    //返回点击量前100篇论文
    public String hotPaper() throws IOException {
        Sort.Order order = Sort.Order.desc("clickcnt");
        Sort sort = Sort.by(order);
        List<paperClick> list = clickRepository.findAll(sort);
        JSONArray jsonArray = new JSONArray();
        int cnt = 0;
        for(paperClick paper:list){
            jsonArray.add(getOutcome(paper.getPaperid(),false));
            if(cnt++ == 100)
                break;
        }
        return jsonArray.toString();
    }

    //未测试
    //增加论文
    public Outcome addOutcome(Outcome outcome){
        return outcomeRepository.save(outcome);
    }

    //修改论文
    public Outcome updateOutcome(Outcome outcome){
        return outcomeRepository.save(outcome);
    }

    //删除论文
    public void deleteOutcome(String id){
        outcomeRepository.deleteById(id);
    }
}
