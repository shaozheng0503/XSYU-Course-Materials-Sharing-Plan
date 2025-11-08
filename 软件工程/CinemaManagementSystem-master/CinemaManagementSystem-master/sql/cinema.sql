-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 47.101.183.63    Database: cinema
-- ------------------------------------------------------
-- Server version	5.7.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `activity`
--

SET @@session.sql_mode =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity`
(
    `id`            int(11)      NOT NULL AUTO_INCREMENT,
    `activity_name` varchar(45)  NOT NULL,
    `a_description` varchar(255) NOT NULL,
    `end_time`      timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `coupon_id`     int(11)               DEFAULT NULL,
    `start_time`    timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `target_amount` float                 DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity`
    DISABLE KEYS */;
INSERT INTO `activity`
VALUES (1, '春季外卖节', '春季外卖节', '2019-04-23 17:55:59', 5, '2019-04-20 17:55:59', 200),
       (3, '春季外卖节', '春季外卖节', '2019-04-23 17:55:59', 6, '2019-04-20 17:55:59', 100),
       (4, '测试活动', '测试活动', '2019-04-26 16:00:00', 8, '2019-04-20 16:00:00', 100);
/*!40000 ALTER TABLE `activity`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_movie`
--

DROP TABLE IF EXISTS `activity_movie`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_movie`
(
    `activity_id` int(11) DEFAULT NULL,
    `movie_id`    int(11) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_movie`
--

LOCK TABLES `activity_movie` WRITE;
/*!40000 ALTER TABLE `activity_movie`
    DISABLE KEYS */;
INSERT INTO `activity_movie`
VALUES (2, 10),
       (2, 11),
       (2, 16),
       (4, 10);
/*!40000 ALTER TABLE `activity_movie`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coupon`
(
    `id`              int(11)   NOT NULL AUTO_INCREMENT,
    `description`     varchar(255)   DEFAULT NULL,
    `name`            varchar(45)    DEFAULT NULL,
    `target_amount`   float          DEFAULT NULL,
    `discount_amount` float          DEFAULT NULL,
    `start_time`      timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `end_time`        timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon`
    DISABLE KEYS */;
INSERT INTO `coupon`
VALUES (1, '测试优惠券', '春季电影节', 20, 1, '2019-04-20 17:47:54', '2019-07-23 17:47:59'),
       (5, '测试优惠券', '品质联盟', 30, 5, '2019-04-20 21:14:46', '2019-07-24 21:14:51'),
       (6, '春节电影节优惠券', '电影节优惠券', 40, 10, '2019-04-20 21:15:11', '2019-07-21 21:14:56'),
       (8, '测试优惠券', '123', 60, 20, '2019-04-20 16:00:00', '2019-07-26 16:00:00'),
       (9, '67优惠券', '67券', 60, 30, '2019-05-20 16:00:00', '2019-07-20 16:00:00'),
       (10, '掉发券', '软工二大作业', 50, 15 , '2019-05-20 16:00:00', '2019-07-20 16:00:00'),
       (11, '秃头券', '互联网计算大作业', 80, 40, '2019-05-20 16:00:00', '2019-07-20 16:00:00'),
       (12, '猝死券', 'Hackathon软件开发大赛', 100, 50, '2019-05-20 16:00:00', '2019-07-20 16:00:00'),
       (13, '生发券', '宿舍睡觉', 100, 60, '2019-05-20 16:00:00', '2019-07-20 16:00:00');
/*!40000 ALTER TABLE `coupon`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_user`
--

DROP TABLE IF EXISTS `coupon_user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coupon_user`
(
    `coupon_id` int(11) NOT NULL,
    `user_id`   int(11) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_user`
--

LOCK TABLES `coupon_user` WRITE;
/*!40000 ALTER TABLE `coupon_user`
    DISABLE KEYS */;
INSERT INTO `coupon_user`
VALUES (8, 15),
       (5, 15),
       (8, 15),
       (6, 15),
       (5, 15),
       (8, 15),
       (6, 15);
/*!40000 ALTER TABLE `coupon_user`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hall`
--

DROP TABLE IF EXISTS `hall`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hall`
(
    `id`    int(11) NOT NULL AUTO_INCREMENT,
    `name`  varchar(255)  DEFAULT NULL,
    `seats` varchar(1024) DEFAULT NULL,
    `scale` int(11)       DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hall`
--

LOCK TABLES `hall` WRITE;
/*!40000 ALTER TABLE `hall`
    DISABLE KEYS */;
INSERT INTO `hall`
VALUES (1, '1号厅',
        '[[0,-1,0,0,0,0,0,0,0,0],[0,-1,0,0,0,0,0,0,0,0],[0,-1,0,0,0,0,0,0,0,0],[0,-1,0,0,0,0,0,0,0,0],[0,-1,0,0,0,0,0,0,0,0]]',
        2),
       (2, '2号厅',
        '[[0,-1,0,0,0,0,0,0,0,0,-1,0],[0,-1,0,0,0,0,0,0,0,0,-1,0],[0,-1,0,0,0,0,0,0,0,0,-1,0],[0,-1,0,0,0,0,0,0,0,0,-1,0],[0,-1,0,0,0,0,0,0,0,0,-1,0],[0,-1,0,0,0,0,0,0,0,0,-1,0],[0,-1,0,0,0,0,0,0,0,0,-1,0],[0,-1,0,0,0,0,0,0,0,0,-1,0]]',
        1);
/*!40000 ALTER TABLE `hall`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie`
(
    `id`            int(11)      NOT NULL AUTO_INCREMENT,
    `poster_url`    varchar(255)          DEFAULT NULL,
    `director`      varchar(255)          DEFAULT NULL,
    `screen_writer` varchar(255)          DEFAULT NULL,
    `starring`      varchar(255)          DEFAULT NULL,
    `type`          varchar(255)          DEFAULT NULL,
    `country`       varchar(255)          DEFAULT NULL,
    `language`      varchar(255)          DEFAULT NULL,
    `length`        int(11)      NOT NULL,
    `start_date`    timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `name`          varchar(255) NOT NULL,
    `description`   text,
    `status`        int(11)               DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 19
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie`
    DISABLE KEYS */;
INSERT INTO `movie`
VALUES (6, 'https://p1.meituan.net/movie/d28b729ffe72353a72d1e7ef8a9b90591544978.jpg',
        '娜丁·拉巴基',
        '娜丁·拉巴基 / 吉哈德·霍加里 / 米歇尔·凯斯沃尼 / 乔治斯·哈巴兹 / 哈立德·穆扎纳',
        '赞恩·阿尔·拉菲亚 / 约丹诺斯·希费罗 / 博鲁瓦蒂夫·特雷杰·班科尔 / 卡萨尔·艾尔·哈达德 / 法迪·尤瑟夫',
        '剧情',
        '黎巴嫩 / 法国 / 美国', '阿拉伯语 / 阿姆哈拉语', 126, ' 2019-06-29 14:54:31',
        '何以为家 كفرناحوم ',
        '　　法庭上，十二岁的男孩赞恩向法官状告他的亲生父母，原因是，他们给了他生命。是什么样的经历让一个孩子做出如此不可思议的举动？故事中，赞恩的父母在无力抚养和教育的状况下依然不停生育，作为家中的长子赞恩，弱小的肩膀承担了无数生活的重压。当妹妹被强行卖给商贩为妻时，赞恩愤怒离家，之后遇到一对没有合法身份的母子，相互扶持勉强生活。然而生活并没有眷顾赞恩，重重磨难迫使他做出了令人震惊的举动……',
        0),
       (7, 'https://p0.meituan.net/movie/c0a02897fa49e5c529bfe3cead0f49652270427.jpg',
        '王晶 / 关智耀',
        '王晶 / 吕冠南',
        '梁家辉 / 古天乐 / 林家栋 / 邱意浓 / 叶相明',
        '剧情 / 动作 / 犯罪',
        '香港 / 中国大陆', '粤语 / 汉语普通话', 103, '2019-06-14 14:54:31',
        '追龙Ⅱ 追龍2：贼王',
        '　　悍匪龙志强，在香港回归前，趁香港英政府不作为，而屡犯巨案，先后绑架富豪利家及雷家之长子，勒索超过二十亿元，事主怕被报复, 交赎款后都不敢报警。中国公安部极为关注，与香港警方合力，派香港警员何天卧底潜入龙志强犯罪团伙，发现他正策划绑架澳门富豪贺不凡，最终陆港警察合力勇擒龙志强，救出贺不凡。',
        0),
       (8, 'https://p0.meituan.net/movie/71fba05fbe4912cb70d27b87c3c856393364925.jpg',
        '迈克尔·道赫蒂',
        '迈克尔·道赫蒂 / 扎克·希尔兹 / 麦克思·鲍伦斯坦',
        '凯尔·钱德勒 / 维拉·法米加 / 米莉·波比·布朗 / 章子怡 / 渡边谦',
        '动作 / 科幻 / 冒险 / 灾难',
        '美国', '英语', 132, '2019-06-30 14:54:31',
        '哥斯拉2：怪兽之王 Godzilla: King of the Monsters',
        '　　随着《哥斯拉》和《金刚：骷髅岛》在全球范围内取得成功，传奇影业和华纳兄弟影业联手开启了怪兽宇宙系列电影的新篇章—一部史诗级动作冒险巨制。在这部电影中，哥斯拉将和众多大家在流行文化中所熟知的怪兽展开较量。全新故事中，研究神秘动物学的机构“帝王组织”将勇敢直面巨型怪兽，其中强大的哥斯拉也将和魔斯拉、拉顿和它的死对头——三头王者基多拉展开激烈对抗。当这些只存在于传说里的超级生物再度崛起时，它们将展开王者争霸，人类的命运岌岌可危……',
        0),
       (9, 'https://p0.meituan.net/movie/47af2656af6cd0110057bc527b862c665484423.jpg',
        '西蒙·金伯格',
        '西蒙·金伯格 / 约翰·拜恩 / 克里斯·克雷蒙 / 戴夫·科克勒姆 / 杰克·科比 / 斯坦·李',
        '苏菲·特纳 / 詹姆斯·麦卡沃伊 / 迈克尔·法斯宾德 / 尼古拉斯·霍尔特 / 泰伊·谢里丹',
        '动作 / 科幻 / 冒险',
        '美国', '英语', 114, '2019-06-16 14:54:31',
        'X战警：黑凤凰 Dark Phoenix',
        '　　影片剧情围绕X战警中最受欢迎成员之一的琴·葛蕾展开，讲述她逐渐转化为黑凤凰的故事。在一次危及生命的太空营救行动中，琴被神秘的宇宙力量击中，成为最强大的变种人。此后琴·葛蕾不仅要设法掌控日益增长、极不稳定的力量，更要与自己内心的恶魔抗争，她的失控让整个X战警大家庭分崩离析，也让整个星球陷入毁灭的威胁之中。《X战警：黑凤凰》是迄今为止气氛最紧张、情感最丰富的一部《X战警》电影，是《X战警》系列20年来的集大成之作，大家非常熟悉和热爱的变种人大家庭即将面对最为强大的敌人——而她恰恰还是他们中的一员。',
        0),
       (10, 'http://n.sinaimg.cn/translate/640/w600h840/20190312/ampL-hufnxfm4278816.jpg', '大森贵弘 /伊藤秀樹', '',
        '神谷浩史 /井上和彦 /高良健吾 /小林沙苗 /泽城美雪', '动画', NULL, NULL, 120, '2019-06-14 14:54:31', '夏目友人帐',
        '在人与妖怪之间过着忙碌日子的夏目，偶然与以前的同学结城重逢，由此回忆起了被妖怪缠身的苦涩记忆。此时，夏目认识了在归还名字的妖怪记忆中出现的女性·津村容莉枝。和玲子相识的她，现在和独子椋雄一同过着平稳的生活。夏目通过与他们的交流，心境也变得平和。但这对母子居住的城镇，却似乎潜伏着神秘的妖怪。在调查此事归来后，寄生于猫咪老师身体的“妖之种”，在藤原家的庭院中，一夜之间就长成树结出果实。而吃掉了与自己形状相似果实的猫咪老师，竟然分裂成了3个',
        0),
       (11, 'https://p0.meituan.net/movie/034069fc367db8a7d9644717b416cc2c332883.jpg',
        '安娜·波顿 / 瑞安·弗雷克',
        '安娜·波顿 / 瑞安·弗雷克 / 吉内瓦·德沃莱特-罗宾森 / 尼科尔·帕尔曼 / 梅格·勒福夫',
        '布丽·拉尔森 / 裘德·洛 / 塞缪尔·杰克逊 / 本·门德尔森 / 安妮特·贝宁',
        '动作/冒险/科幻',
        '美国',
        '英语', 124, '2019-06-18 14:55:31', '惊奇队长 Captain Marvel',
        '　　在围剿斯克鲁人的战斗中，克里人星际战队成员弗斯（布丽·拉尔森 Brie Larson 饰）不幸成为对方的俘虏。斯克鲁人尝试探究弗斯的记忆，最终发现连弗斯本人都不知道的一段往事，进而也得知名为温迪•劳森博士的女子掌握着他们急于得到的时空引擎。趁对方不备，拥有强大超能力的弗斯摆脱束缚，逃到了代号为C-53的地球，而这里也正是她那段失落的记忆的发生地。未过多久，神盾局探员弗瑞特工（塞缪尔·杰克逊 Samuel L. Jackson 饰）找上门来，而紧随其后的斯克鲁人更是引发了地球人前所未见的大骚动。
　　在这一过程中，弗瑞特工意识到事态的严重性，并且帮助弗斯认清本来的自己。倔强的弗斯，终于找到了真正需要保护的东西……', 0),
       (12, 'https://p1.meituan.net/movie/c9b280de01549fcb71913edec05880585769972.jpg', '彼得·法雷里',
        '尼克·瓦莱隆加 / 布莱恩·海耶斯·库瑞 / 彼得·法雷里',
        '维果·莫腾森 / 马赫沙拉·阿里 / 琳达·卡德里尼 / 塞巴斯蒂安·马尼斯科 / 迪米特·D·马里诺夫', '剧情 / 喜剧 / 传记', '美国', '英语 / 意大利语 / 俄语 / 德语', 130,
        '2019-06-21 00:00:00', '绿皮书 Green Book', '　　托尼（维果·莫腾森 Viggo Mortensen 饰）是一个吊儿郎当游手好闲的混混，在一家夜总会做侍者。这间夜总会因故要停业几个月，可托尼所要支付的房租和生活费不会因此取消，所以他的当务之急是去寻找另一份工作来填补这几个月的空缺。在这个节骨眼上，一位名叫唐雪莉（马赫沙拉·阿里 Mahershala Ali 饰）的黑人钢琴家提出雇佣托尼。
　　唐雪莉即将开始为期八个星期的南下巡回演出，可是，那个时候南方对黑人的歧视非常的严重，于是托尼便成为了唐雪莉的司机兼保镖。一路上，两人迥异的性格使得他们之间产生了很多的矛盾，与此同时，唐雪莉在南方所遭受的种种不公平的对待也让托尼对种族歧视感到深恶痛绝',
        0),
       (13, 'https://p1.meituan.net/movie/c1aa1575630c1387bd6a0d3e7c2e26621907863.jpg', '乔丹·皮尔',
        '乔丹·皮尔',
        '露皮塔·尼永奥 / 温斯顿·杜克 / 伊丽莎白·莫斯 / 蒂姆·海德克 / 莎哈迪·赖特·约瑟夫', '惊悚 / 恐怖', '美国', '英语', 116,
        '2019-06-18 00:00:00', '我们 Us',
        '　　一对名为阿德莱德和盖博的夫妻带着孩子到海滨度假，这里曾经是阿莱德莱童年的娱乐场所。在海滨与泰勒一家玩了一天之后，阿德莱德愈发地无法从往昔的伤痛中走出，他越来越觉得自己的家庭将要发生灾难。到了晚上，他们一家甚至看到有四个人手拉手在行车道上站立，似乎有更多诡异的事情等待着他们。',
        0),
       (14, 'https://p0.meituan.net/movie/363e3a7e614d29b2847ff4e62afcd3f42168651.jpg', '鲁本·弗雷斯彻',
        '杰夫·皮克纳 / 斯科特·罗森伯格 / 凯莉·马塞尔 / 托德·麦克法兰 / 戴维·麦克法兰',
        '汤姆·哈迪 / 米歇尔·威廉姆斯 / 里兹·阿迈德 / 斯科特·黑兹 / 瑞德·斯科特',
        '动作 / 科幻 / 惊悚', '美国 / 中国大陆', '英语 / 汉语普通话', 112,
        '2019-06-19 00:00:00', '毒液：致命守护者 Venom',
        '　　艾迪（汤姆·哈迪 Tom Hardy 饰）是一位深受观众喜爱的新闻记者，和女友安妮（米歇尔·威廉姆斯 Michelle Williams 饰）相恋多年，彼此之间感情十分要好。安妮是一名律师，接手了生命基金会的案件，在女友的邮箱里，艾迪发现了基金会老板德雷克（里兹·阿迈德 Riz Ahmed 饰）不为人知的秘密。为此，艾迪不仅丢了工作，女友也离他而去。
　　之后，生命基金会的朵拉博士（珍妮·斯蕾特 Jenny Slate 饰）找到了艾迪，希望艾迪能够帮助她阻止德雷克疯狂的罪行。在生命基金会的实验室里，艾迪发现了德雷克进行人体实验的证据，并且在误打误撞之中被外星生命体毒液附身。回到家后，艾迪和毒液之间形成了共生关系，他们要应对的是德雷克派出的一波又一波杀手。',
        0),
       (15, 'https://p1.meituan.net/movie/d94e5c3054778f6f48bff3a813b0b7cd5300998.jpg',
        '布莱恩·辛格',
        '安东尼·麦卡滕 / 皮特·摩根',
        '拉米·马雷克 / 本·哈迪 / 约瑟夫·梅泽罗 / 格威利姆·李 / 艾伦·里奇',
        '剧情 / 音乐 / 传记', '英国 / 美国', '英语', 135,
        '2019-06-20 00:00:00', '波西米亚狂想曲 Bohemian Rhapsody',
        '　　本片是对皇后乐队、传奇主唱弗雷迪·莫库里以及他们音乐的致敬盛宴，这是一段充满爱、痛苦、接纳和音乐的旅程。
　　弗雷迪·莫库里（拉米·马雷克 饰）曾是希思罗机场的一名普通搬运工，对音乐满腔热血的他，与布莱恩·梅（格威利姆·李 饰）、罗杰·泰勒（本·哈迪 饰）、约 翰·迪肯（约瑟夫·梅泽罗 饰）组成皇后乐队。这个殿堂级乐队的从无到有，从疏离到重聚，从低谷到巅峰，仿佛就是弗雷迪一生的缩影。在最后的日子里，弗雷迪做了生命中最重要的决定——在音乐史上最伟大的“拯救生命”大型摇滚乐演唱会上，将所有热血付诸于歌声。',
        0),
       (16, 'https://p1.meituan.net/movie/94b9bfc5ffeea6d6e362395992f547762041095.jpg', '林孝谦', 'abcˆ', '陈意涵', '爱情',
        '大陆', NULL, 123, '2019-06-18 13:23:15', '比悲伤更悲伤的故事',
        '唱片制作人张哲凯(刘以豪)和王牌作词人宋媛媛(陈意涵)相依为命，两人自幼身世坎坷只有彼此为伴，他们是亲人、是朋友，也彷佛是命中注定的另一半。父亲罹患遗传重症而被母亲抛弃的哲凯，深怕自己随时会发病不久人世，始终没有跨出友谊的界线对媛媛展露爱意。眼见哲凯的病情加重，他暗自决定用剩余的生命完成他们之间的终曲，再为媛媛找个可以托付一生的好男人。这时，事业有 成温柔体贴的医生(张书豪)适时的出现让他成为照顾媛媛的最佳人选，二人按部就班发展着关系。一切看似都在哲凯的计划下进行。然而，故事远比这里所写更要悲伤......',
        1);
/*!40000 ALTER TABLE `movie`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_like`
--

DROP TABLE IF EXISTS `movie_like`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_like`
(
    `movie_id`  int(11)   NOT NULL,
    `user_id`   int(11)   NOT NULL,
    `like_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`movie_id`, `user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_like`
--

LOCK TABLES `movie_like` WRITE;
/*!40000 ALTER TABLE `movie_like`
    DISABLE KEYS */;
INSERT INTO `movie_like`
VALUES (9, 12, '2019-05-25 02:40:19'),
       (13, 12, '2019-05-25 02:40:19'),
       (11, 11, '2019-05-22 09:38:12'),
       (11, 12, '2019-05-23 09:38:12'),
       (11, 13, '2019-05-22 08:38:12'),
       (12, 11, '2019-05-23 09:48:46'),
       (12, 13, '2019-05-25 06:36:22'),
       (14, 11, '2019-05-23 09:38:12'),
       (16, 1, '2019-05-23 15:27:48'),
       (11, 1, '2019-05-22 09:38:12'),
       (11, 2, '2019-05-23 09:38:12'),
       (11, 3, '2019-05-22 08:38:12'),
       (12, 1, '2019-05-23 09:48:46'),
       (12, 3, '2019-05-25 06:36:22'),
       (14, 1, '2019-05-23 09:38:12'),
       (16, 12, '2019-05-23 15:27:48'),
       (6, 11, '2019-05-22 09:38:12'),
       (6, 12, '2019-05-23 09:38:12'),
       (6, 13, '2019-05-22 08:38:12'),
       (7, 11, '2019-05-23 09:48:46'),
       (7, 13, '2019-05-25 06:36:22'),
       (8, 11, '2019-05-23 09:38:12'),
       (8, 1, '2019-05-23 15:27:48'),
       (9, 1, '2019-05-22 09:38:12'),
       (9, 2, '2019-05-23 09:38:12'),
       (9, 3, '2019-05-22 08:38:12'),
       (10, 1, '2019-05-23 09:48:46'),
       (10, 3, '2019-05-25 06:36:22'),
       (6, 1, '2019-05-23 09:38:12'),
       (10, 12, '2019-05-23 15:27:48');
/*!40000 ALTER TABLE `movie_like`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule`
(
    `id`         int(11)   NOT NULL AUTO_INCREMENT,
    `hall_id`    int(11)   NOT NULL,
    `movie_id`   int(11)   NOT NULL,
    `start_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `end_time`   timestamp NOT NULL,
    `fare`       double    NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 69
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule`
    DISABLE KEYS */;
INSERT INTO `schedule`
VALUES (20, 1, 12, '2019-04-13 17:00:00', '2019-04-13 18:00:00', 20.5),
       (21, 1, 10, '2019-04-11 12:00:00', '2019-04-11 13:00:00', 90),
       (27, 1, 11, '2019-04-17 18:01:00', '2019-04-17 20:01:00', 20.5),
       (28, 1, 11, '2019-04-19 16:00:00', '2019-04-19 18:00:00', 20.5),
       (30, 1, 11, '2019-04-18 18:01:00', '2019-04-18 20:01:00', 20.5),
       (31, 1, 11, '2019-04-12 16:00:00', '2019-04-12 18:00:00', 20.5),
       (32, 1, 11, '2019-04-12 20:00:00', '2019-04-12 22:00:00', 20.5),
       (37, 1, 11, '2019-04-15 00:00:00', '2019-04-15 02:00:00', 20.5),
       (38, 1, 11, '2019-04-14 17:00:00', '2019-04-14 19:00:00', 20.5),
       (40, 1, 10, '2019-04-10 16:00:00', '2019-04-10 18:00:00', 20.5),
       (41, 1, 11, '2019-04-10 19:00:00', '2019-04-10 21:00:00', 20.5),
       (42, 1, 11, '2019-04-10 22:00:00', '2019-04-11 00:00:00', 20.5),
       (43, 1, 10, '2019-04-11 01:00:00', '2019-04-11 03:00:00', 20.5),
       (44, 2, 10, '2019-04-11 01:00:00', '2019-04-11 03:00:00', 20.5),
       (45, 2, 10, '2019-04-10 22:00:00', '2019-04-11 00:00:00', 20.5),
       (46, 2, 11, '2019-04-10 19:00:00', '2019-04-10 21:00:00', 20.5),
       (47, 2, 11, '2019-04-10 16:00:00', '2019-04-10 18:00:00', 20.5),
       (48, 2, 10, '2019-04-11 13:00:00', '2019-04-11 15:59:00', 20.5),
       (50, 1, 10, '2019-04-15 16:00:00', '2019-04-15 19:00:00', 2),
       (51, 1, 10, '2019-04-17 05:00:00', '2019-04-17 07:00:00', 9),
       (52, 1, 10, '2019-04-18 05:00:00', '2019-04-18 07:00:00', 9),
       (53, 1, 16, '2019-04-19 07:00:00', '2019-04-19 10:00:00', 9),
       (54, 1, 16, '2019-04-16 19:00:00', '2019-04-16 22:00:00', 9),
       (55, 1, 15, '2019-04-17 23:00:00', '2019-04-18 01:00:00', 9),
       (56, 2, 10, '2019-04-19 13:00:00', '2019-04-19 15:59:00', 20.5),
       (57, 2, 10, '2019-04-20 13:00:00', '2019-04-20 15:59:00', 20.5),
       (58, 2, 10, '2019-04-21 13:00:00', '2019-04-21 15:59:00', 20.5),
       (61, 1, 13, '2019-04-20 11:00:00', '2019-04-20 13:00:00', 25),
       (62, 1, 11, '2019-04-20 08:00:00', '2019-04-20 10:00:00', 25),
       (63, 2, 15, '2019-04-20 16:01:30', '2019-04-21 05:30:00', 30),
       (64, 1, 16, '2019-04-22 02:00:00', '2019-04-22 05:30:00', 30),
       (65, 1, 10, '2019-04-23 02:00:00', '2019-04-23 05:30:00', 30),
       (66, 2, 13, '2019-04-21 07:31:29', '2019-04-16 15:59:00', 20.5),
       (67, 2, 10, '2019-04-25 13:00:00', '2019-04-25 15:59:00', 20.5),
       (68, 2, 10, '2019-04-26 13:00:00', '2019-04-26 15:59:00', 20.5);
/*!40000 ALTER TABLE `schedule`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket`
(
    `user_id`      int(11)        DEFAULT NULL,
    `schedule_id`  int(11)        DEFAULT NULL,
    `column_index` int(11)        DEFAULT NULL,
    `row_index`    int(11)        DEFAULT NULL,
    `state`        tinyint(4)     DEFAULT NULL,
    `id`           int(11)   NOT NULL AUTO_INCREMENT,
    `time`         timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `actual_pay`   double         DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 63
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket`
    DISABLE KEYS */;
INSERT INTO `ticket`
VALUES (12, 50, 5, 3, 2, 1, '2019-04-23 13:50:52', 0),
       (12, 50, 5, 3, 2, 2, '2019-04-23 13:50:52', 0),
       (12, 50, 5, 3, 2, 3, '2019-04-23 13:50:52', 0),
       (12, 50, 5, 3, 2, 4, '2019-04-23 13:50:52', 0),
       (12, 50, 5, 3, 0, 5, '2019-04-23 13:50:52', 0),
       (15, 50, 4, 3, 0, 6, '2019-04-23 13:50:52', 0),
       (15, 58, 0, 0, 1, 15, '2019-04-23 13:50:52', 0),
       (15, 58, 2, 0, 1, 16, '2019-04-23 13:50:52', 0),
       (15, 58, 1, 1, 1, 17, '2019-04-23 13:50:52', 0),
       (15, 58, 11, 7, 1, 18, '2019-04-23 13:50:52', 0),
       (13, 50, 4, 2, 1, 19, '2019-04-23 13:50:52', 0),
       (15, 66, 3, 2, 1, 20, '2019-04-23 13:50:52', 0),
       (12, 50, 1, 1, 1, 21, '2019-04-23 13:50:52', 0),
       (13, 50, 4, 3, 1, 22, '2019-04-23 13:50:52', 0),
       (15, 50, 2, 2, 1, 23, '2019-04-23 13:50:52', 0),
       (15, 58, 0, 7, 0, 24, '2019-04-23 13:50:52', 0),
       (15, 58, 5, 4, 0, 25, '2019-04-23 13:50:52', 0),
       (15, 58, 6, 4, 0, 26, '2019-04-23 13:50:52', 0),
       (15, 58, 6, 2, 0, 27, '2019-04-23 13:50:52', 0),
       (15, 58, 7, 2, 0, 28, '2019-04-23 13:50:52', 0),
       (15, 58, 0, 4, 0, 29, '2019-04-23 13:50:52', 0),
       (15, 58, 0, 3, 0, 30, '2019-04-23 13:50:52', 0),
       (15, 58, 0, 2, 0, 31, '2019-04-23 13:50:52', 0),
       (15, 58, 10, 0, 0, 32, '2019-04-23 13:50:52', 0),
       (15, 58, 11, 0, 0, 33, '2019-04-23 13:50:52', 0),
       (15, 58, 8, 0, 0, 34, '2019-04-23 13:50:52', 0),
       (15, 58, 9, 0, 0, 35, '2019-04-23 13:50:52', 0),
       (15, 58, 5, 0, 0, 36, '2019-04-23 13:50:52', 0),
       (15, 58, 6, 0, 0, 37, '2019-04-23 13:50:52', 0),
       (15, 58, 6, 7, 0, 38, '2019-04-23 13:50:52', 0),
       (15, 58, 7, 7, 0, 39, '2019-04-23 13:50:52', 0),
       (15, 58, 8, 7, 0, 40, '2019-04-23 13:50:52', 0),
       (15, 58, 11, 4, 0, 41, '2019-04-23 13:50:52', 0),
       (15, 58, 11, 5, 0, 42, '2019-04-23 13:50:52', 0),
       (15, 58, 9, 6, 0, 43, '2019-04-23 13:50:52', 0),
       (15, 58, 10, 6, 0, 44, '2019-04-23 13:50:52', 0),
       (15, 58, 11, 6, 0, 45, '2019-04-23 13:50:52', 0),
       (15, 58, 3, 5, 1, 46, '2019-04-23 13:50:52', 0),
       (15, 58, 4, 5, 1, 47, '2019-04-23 13:50:52', 0),
       (15, 58, 5, 5, 1, 48, '2019-04-23 13:50:52', 0),
       (15, 58, 11, 2, 0, 49, '2019-04-23 13:50:52', 0),
       (15, 58, 11, 3, 0, 50, '2019-04-23 13:50:52', 0),
       (15, 58, 9, 4, 0, 51, '2019-04-23 13:50:52', 0),
       (15, 58, 9, 3, 1, 52, '2019-04-23 13:50:52', 0),
       (15, 58, 10, 3, 1, 53, '2019-04-23 13:50:52', 0),
       (15, 65, 7, 4, 0, 54, '2019-04-23 13:50:52', 0),
       (15, 65, 8, 4, 0, 55, '2019-04-23 13:50:52', 0),
       (15, 65, 9, 4, 0, 56, '2019-04-23 13:50:52', 0),
       (15, 65, 7, 3, 0, 57, '2019-04-23 13:50:52', 0),
       (15, 65, 8, 3, 0, 58, '2019-04-23 13:50:52', 0),
       (15, 65, 9, 3, 0, 59, '2019-04-23 13:50:52', 0),
       (15, 65, 0, 0, 1, 60, '2019-04-23 13:50:52', 0),
       (15, 65, 0, 1, 1, 61, '2019-04-23 13:50:52', 0),
       (15, 65, 0, 2, 1, 62, '2019-04-23 13:50:52', 0);
/*!40000 ALTER TABLE `ticket`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user`
(
    `id`       int(11)     NOT NULL AUTO_INCREMENT,
    `username` varchar(50) NOT NULL,
    `password` varchar(50) NOT NULL,
    `auth`     int(11)     not null,
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_id_uindex` (`id`),
    UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 16
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user`
    DISABLE KEYS */;
INSERT INTO `user`
VALUES (1, 'testname1', '123456', 0),
       (2, 'testname2', '123456', 0),
       (3, 'test1', '123456', 0),
       (4, 'test2', '123456', 0),
       (5, 'test3', '123456', 0),
       (6, 'test4', '123456', 0),
       (7, 'test121', '123456', 0),
       (8, 'root', '123456', 2),
       (9, 'test6', '123456', 0),
       (10, 'roottt1', '123123', 1),
       (11, 'roottt2', '123123', 0),
       (12, 'zhourui', '123456', 0),
       (13, 'steve', '123456', 0),
       (14, 'dd1', '123123', 0),
       (15, 'dd2', '123123', 0),
       (16, 'dd3', '123123', 0),
       (17, 'dd4', '123123', 0),
       (18, 'dd5', '123123', 0),
       (19, 'dd6', '123123', 0),
       (20, 'dd7', '123123', 0);
/*!40000 ALTER TABLE `user`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `view`
--

DROP TABLE IF EXISTS `view`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `view`
(
    `id`  int(11) NOT NULL AUTO_INCREMENT,
    `day` int(11) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `view`
--

LOCK TABLES `view` WRITE;
/*!40000 ALTER TABLE `view`
    DISABLE KEYS */;
INSERT INTO `view`
VALUES (1, 7);
/*!40000 ALTER TABLE `view`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vip_card`
--

DROP TABLE IF EXISTS `vip_card`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vip_card`
(
    `id`        int(11)   NOT NULL AUTO_INCREMENT,
    `type_id`   int(11)            DEFAULT NULL,
    `user_id`   int(11)            DEFAULT NULL,
    `balance`   double             DEFAULT '0',
    `join_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `state`     int(11)            DEFAULT '1',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_card`
--

LOCK TABLES `vip_card` WRITE;
/*!40000 ALTER TABLE `vip_card`
    DISABLE KEYS */;
INSERT INTO `vip_card`
VALUES (1, 1, 15, 375, '2019-04-21 13:54:38', 1),
       (2, 1, 12, 660, '2019-04-17 18:47:42', 1),
       (3, 1, 3, 660, '2019-05-17 18:47:42', 1);
/*!40000 ALTER TABLE `vip_card`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card_type`
--

DROP TABLE IF EXISTS `card_type`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card_type`
(
    `id`              int(11)     NOT NULL AUTO_INCREMENT,
    `card_name`       varchar(45) NOT NULL,
    `price`           double      NOT NULL,
    `description`     varchar(45) DEFAULT NULL,
    `top_up_target`   double      NOT NULL,
    `top_up_discount` double      NOT NULL,
    `ticket_target`   double      NOT NULL,
    `ticket_discount` double      NOT NULL,
    `state`           int(11)     DEFAULT '1',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card_type`
--

LOCK TABLES `card_type` WRITE;
/*!40000 ALTER TABLE `card_type`
    DISABLE KEYS */;
INSERT INTO `card_type`
VALUES (1, '67卡', 25, '满200送30', 200, 30, 50, 10, 1),
       (2, '67nb卡', 100, '充值会员卡满300送50', 300, 50, 100, 20, 1);
/*!40000 ALTER TABLE `card_type`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refund`
--

DROP TABLE IF EXISTS `refund`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `refund`
(
    `id`       int(11) NOT NULL AUTO_INCREMENT,
    `movie_id` int(11) NOT NULL,
    `time`     DOUBLE  NOT NULL,
    `discount` DOUBLE NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refund`
--

LOCK TABLES `refund` WRITE;
/*!40000 ALTER TABLE `refund`
    DISABLE KEYS */;
INSERT INTO `refund`
VALUES (1, 12, 7, 25),
       (2, 10, 4, 100);
/*!40000 ALTER TABLE `refund`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topup_history`
--

DROP TABLE IF EXISTS `topup_history`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topup_history`
(
    `id`       int(11)   NOT NULL AUTO_INCREMENT,
    `user_id`  int(11)            DEFAULT NULL,
    `money`    DOUBLE             DEFAULT NULL,
    `discount` DOUBLE             DEFAULT NULL,
    `balance`  DOUBLE             DEFAULT NULL,
    `time`     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topup_history`
--

LOCK TABLES `topup_history` WRITE;
/*!40000 ALTER TABLE `topup_history`
    DISABLE KEYS */;
INSERT INTO `topup_history`
VALUES (1, 15, 300, 20, 280, '2019-05-21 13:54:38'),
       (2, 12, 660, 60, 1200, '2019-05-21 18:47:42'),
       (3, 12, 320, 20, 1520, '2019-05-21 18:47:43'),
       (4, 12, 1000, 0.5, 2520, '2019-05-21 18:47:45');
/*!40000 ALTER TABLE `topup_history`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topup_history`
--

DROP TABLE IF EXISTS `consume_history`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consume_history`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `user_id`     int(11)     DEFAULT NULL,
    `money`       DOUBLE      DEFAULT NULL,
    `discount`    DOUBLE      DEFAULT NULL,
    `consumeType` VARCHAR(20) DEFAULT NULL,
    `type`        INT(11)     DEFAULT NULL,
    `contentId`   INT(11)     DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consume_history`
--

LOCK TABLES `consume_history` WRITE;
/*!40000 ALTER TABLE `consume_history`
    DISABLE KEYS */;
INSERT INTO `consume_history`
VALUES (1, 15, 20.5, 0.5, '会员卡', 101, 16),
       (2, 15, 30, 0, '银行卡', 101, 54),
       (3, 15, 30, 0, '银行卡', 101, 55),
       (4, 15, 25, 0, '银行卡', 102, 1);
/*!40000 ALTER TABLE `consume_history`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banner`
--

DROP TABLE IF EXISTS `banner`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banner`
(
  `img1`        VARCHAR(250) NOT NULL,
  `navi1`       VARCHAR(250) NOT NULL,
  `img2`        VARCHAR(250) NOT NULL,
  `navi2`       VARCHAR(250) NOT NULL,
  `img3`        VARCHAR(250) NOT NULL,
  `navi3`       VARCHAR(250) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner`
--

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner`
  DISABLE KEYS */;
INSERT INTO `banner`
VALUES ('https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1246180815,1068531272&fm=26&gp=0.jpg',
        'http://www.iselab.cn/faculty/ChunrongFang',
        'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559894859551&di=4c6309c77d87473a15c2c0b2da4f6aa7&imgtype=0&src=http%3A%2F%2Fimg4.a0bi.com%2Fupload%2Fttq%2F20180131%2F1517407295942.jpg',
        'http://software.nju.edu.cn/rentw/',
        'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560489614&di=c0d417ec78d087bec02e61b7ec7f7bcb&imgtype=jpg&er=1&src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2F0a728a07a1dd3bca30ecd9c6e8f84b2573d52699.jpg',
        'http://hetieke.ml/');
/*!40000 ALTER TABLE `banner`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'cinema'
--

--
-- Dumping routines for database 'cinema'
--
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;
-- Dump completed on 2019-04-24 21:20:52