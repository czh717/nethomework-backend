create database if not exists network;
use network;

drop table if exists `packet`;
create table `packet`
(
    `id`       int         not null auto_increment,
    `serialId` int         not null,
    `type`     varchar(20) not null,
    `content`  text        default null,
    primary key(`id`)
) engine = Innodb default charset = utf8 auto_increment = 1;

insert into packet values (1,1597,'UDP','{\n''1'': ''1597'',\n''2'': ''34.2540999'',\n''3'': ''53260'',\n''4'': ''8082'',\n''5'': ''UDP'',\n''6'': ''11'',\n''7'': ''53260->8082 [SYN] Seq-0 Win-65535 Len-0 MSS-65495 WS-256 TSVal-4232838434 TSecr-0''}'),
                          (2,1598,'IP','{\n''1'': ''1598'',\n''2'': ''34.2540999'',\n''3'': ''192.168.0.103'',\n''4'': ''192.168.0.20'',\n''5'': ''IP'',\n''6'': ''31'',\n''7'': ''53260->8082 [SYN] Seq-0 Win-65535 Len-0 MSS-65495 WS-256 TSVal-4232838434 TSecr-0''}'),
                          (3,1599,'MAC','{\n''1'': ''1599'',\n''2'': ''34.2540999'',\n''3'': ''38:f9:d3:c9:48:f9'',\n''4'': ''00:0c:29:4e:80:29'',\n''5'': ''MAC'',\n''6'': ''null'',\n''7'': ''53260->8082 [SYN] Seq-0 Win-65535 Len-0 MSS-65495 WS-256 TSVal-4232838434 TSecr-0''}'),
                          (4,1600,'TAP','{\n''1'': ''1600'',\n''2'': ''34.2540999'',\n''3'': ''null'',\n''4'': ''null'',\n''5'': ''TAP'',\n''6'': ''null'',\n''7'': ''53260->8082 [SYN] Seq-0 Win-65535 Len-0 MSS-65495 WS-256 TSVal-4232838434 TSecr-0''}'),
                          (5,1600,'TAP','{\n''1'': ''1601'',\n''2'': ''34.2540999'',\n''3'': ''null'',\n''4'': ''null'',\n''5'': ''TAP'',\n''6'': ''null'',\n''7'': ''8082->53260 [SYN] Seq-0 Win-65535 Len-0 MSS-65495 WS-256 TSVal-4232838434 TSecr-0''}'),
                          (6,1599,'MAC','{\n''1'': ''1602'',\n''2'': ''34.2540999'',\n''3'': ''00:0c:29:4e:80:29'',\n''4'': ''38:f9:d3:c9:48:f9'',\n''5'': ''MAC'',\n''6'': ''null'',\n''7'': ''8082->53260 [SYN] Seq-0 Win-65535 Len-0 MSS-65495 WS-256 TSVal-4232838434 TSecr-0''}'),
                          (7,1598,'IP','{\n''1'': ''1603'',\n''2'': ''34.2540999'',\n''3'': ''192.168.0.20'',\n''4'': ''192.168.0.103'',\n''5'': ''IP'',\n''6'': ''31'',\n''7'': ''8082->53260 [SYN] Seq-0 Win-65535 Len-0 MSS-65495 WS-256 TSVal-4232838434 TSecr-0''}'),
                          (8,1597,'UDP','{\n''1'': ''1604'',\n''2'': ''34.2540999'',\n''3'': ''8082'',\n''4'': ''53260'',\n''5'': ''UDP'',\n''6'': ''11'',\n''7'': ''8082->53260 [SYN] Seq-0 Win-65535 Len-0 MSS-65495 WS-256 TSVal-4232838434 TSecr-0''}');
