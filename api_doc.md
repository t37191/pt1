* 这玩意不一定哪天哪行就变了（我尽量避免），关注diff文件吧

* /bbs_topic

      "topic_id",
      "user_id",
      "topic_name",
      "user_name",
      "reply_number",
      "vote_number"

eg:

/bbs_topic   返回这个的数组

/bbs_topic/1 根据tipic_id查询单条

* /bbs_reply

      "reply_id"，
      "user_id",
      "user_name",
      "r_topic_id",
      "reply_content",
      "r_reply_id",
      "reply_time",
      "r_reply_user_name",
      "r_reply_user_id"

结构应该和数据库一样，但是格式详见https://github.com/typicode/json-server，有一些查询参数，类似下面这种

/bbs_reply?r_topic_id=1&r_reply_id=1&_sort=reply_time&_limit=10

这里应该会用到_sort, _limit, _start, 话说这些玩意可能莫名其妙的地方就用到了，最好有什么方法弄个函数处理掉。不行再说吧

除了本条评论的用户信息，还需要知道回复的评论的用户信息

* /bbs_reply/count

"reply_number"

为了用/bbs_reply/count?r_reply_id=1 这种东西获取回复数量。如果觉得这种方法太扯麻烦联系我。。。

* /paper

      {
        "id": "FHBA_GoBkHD19ZAOxDmY",
        "title": "gaodengshuxue",
        "abstr": "gaodengshuxue的魅力",
        "authors": [
            {
                "id": "aaabbbcccdddeeefffgg",
                "name": "王泽"
            }
        ],
        "keywords": [
            "数学",
            "科学"
        ],
        "pdf": "[图片]www.4399.com",
        "year": 2019
        "cnt" : 24
    }

      
author和keyword都是数组，因为可能不止一个。

* /search

      {
        "id": "FHBA_GoBkHD19ZAOxDmY",
        "title": "gaodengshuxue",
        "abstr": "gaodengshuxue的魅力",
        "authors": [
            {
                "id": "aaabbbcccdddeeefffgg",
                "name": "王泽"
            }
        ],
        "keywords": [
            "数学",
            "科学"
        ],
        "pdf": "[图片]www.4399.com",
        "year": 2019
        "cnt" : 24
    }

      
* /search/count

这两个除了上面三个，还应该有搜索类型_type还有搜索内容_content

eg：/search/count?type=fulltext&_contenet=asadsasdad&_start=0&_limit=10

搜索类型先默认全文检索吧

有来源就写一下，没有就全为空就行了。。

* /user/login

      "token",
      "user_id",
      "user_name"
      
* /user/signup-user

      "token",
      "user_id",
      "user_name"

* /user/refreshtoken

传入旧token，返回新token或null
eg./user/refreshtoken?_token=baskdlakjdfk


* /expert

获得专家详细信息，其中：

pubs.r : 第几作者

pubs.i : 论文id

tags.t : 领域名

tags.w : 领域权重

      {
        "id": "562d4f6e45ce1e59678685fc",
        "name": "Skerritt Geoff C",
        "n_pubs": 2,
        "pubs": [
          {
            "r": 3,
            "i": "55a463a265ce31bc8778f692"
          },
          {
            "r": 8,
            "i": "55a4192965ce5cd7b3c40ae6"
          }
        ],
        "tags": [
          {
            "t": "Diagnosis",
            "w": 1
          },
          {
            "t": "Magnetic Resonance Imaging",
            "w": 1
          },
          {
            "t": "Helminths",
            "w": 1
          },
          {
            "t": "Central Nervous System",
            "w": 1
          }
        ]
      }

* /hotpapers
    
    返回点击量降序前100篇论文

* /admin
TODO
      