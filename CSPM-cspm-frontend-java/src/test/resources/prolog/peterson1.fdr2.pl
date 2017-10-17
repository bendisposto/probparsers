:- dynamic parserVersionNum/1, parserVersionStr/1, parseResult/5.
:- dynamic module/4.
'parserVersionStr'('0.6.1.1').
'parseResult'('ok','',0,0,0).
:- dynamic channel/2, bindval/3, agent/3.
:- dynamic agent_curry/3, symbol/4.
:- dynamic dataTypeDef/2, subTypeDef/2, nameType/2.
:- dynamic cspTransparent/1.
:- dynamic cspPrint/1.
:- dynamic pragma/1.
:- dynamic comment/2.
:- dynamic assertBool/1, assertRef/5, assertTauPrio/6.
:- dynamic assertModelCheckExt/4, assertModelCheck/3.
:- dynamic assertLtl/4, assertCtl/4.
'parserVersionNum'([0,11,0,1]).
'parserVersionStr'('CSPM-Frontent-0.11.0.1').
'channel'('p1setflag1','type'('dotUnitType')).
'channel'('p1resetflag1','type'('dotUnitType')).
'channel'('p2setflag1','type'('dotUnitType')).
'channel'('p2resetflag1','type'('dotUnitType')).
'channel'('p1gettrueflag1','type'('dotUnitType')).
'channel'('p1getfalseflag1','type'('dotUnitType')).
'channel'('p2gettrueflag1','type'('dotUnitType')).
'channel'('p2getfalseflag1','type'('dotUnitType')).
'channel'('p1setflag2','type'('dotUnitType')).
'channel'('p1resetflag2','type'('dotUnitType')).
'channel'('p2setflag2','type'('dotUnitType')).
'channel'('p2resetflag2','type'('dotUnitType')).
'channel'('p1gettrueflag2','type'('dotUnitType')).
'channel'('p1getfalseflag2','type'('dotUnitType')).
'channel'('p2gettrueflag2','type'('dotUnitType')).
'channel'('p2getfalseflag2','type'('dotUnitType')).
'channel'('p1set1turn','type'('dotUnitType')).
'channel'('p1set2turn','type'('dotUnitType')).
'channel'('p2set1turn','type'('dotUnitType')).
'channel'('p2set2turn','type'('dotUnitType')).
'channel'('p1get1turn','type'('dotUnitType')).
'channel'('p1get2turn','type'('dotUnitType')).
'channel'('p2get1turn','type'('dotUnitType')).
'channel'('p2get2turn','type'('dotUnitType')).
'channel'('p1enter','type'('dotUnitType')).
'channel'('p1critical','type'('dotUnitType')).
'channel'('p1leave','type'('dotUnitType')).
'channel'('p2enter','type'('dotUnitType')).
'channel'('p2critical','type'('dotUnitType')).
'channel'('p2leave','type'('dotUnitType')).
'bindval'('FLAG1','val_of'('FLAG1FALSE','src_span'(15,9,15,19,556,10)),'src_span'(15,1,15,19,548,18)).
'bindval'('FLAG1FALSE','[]'('[]'('[]'('[]'('[]'('prefix'('src_span'(16,15,16,30,581,15),[],'p1getfalseflag1','val_of'('FLAG1FALSE','src_span'(16,34,16,44,600,10)),'src_span'(16,31,16,33,596,29)),'prefix'('src_span'(17,15,17,30,625,15),[],'p2getfalseflag1','val_of'('FLAG1FALSE','src_span'(17,34,17,44,644,10)),'src_span'(17,31,17,33,640,29)),'src_span_operator'('no_loc_info_available','src_span'(17,12,17,14,622,2))),'prefix'('src_span'(18,15,18,25,669,10),[],'p1setflag1','val_of'('FLAG1TRUE','src_span'(18,29,18,38,683,9)),'src_span'(18,26,18,28,679,23)),'src_span_operator'('no_loc_info_available','src_span'(18,12,18,14,666,2))),'prefix'('src_span'(19,15,19,25,707,10),[],'p2setflag1','val_of'('FLAG1TRUE','src_span'(19,29,19,38,721,9)),'src_span'(19,26,19,28,717,23)),'src_span_operator'('no_loc_info_available','src_span'(19,12,19,14,704,2))),'prefix'('src_span'(20,15,20,27,745,12),[],'p1resetflag1','val_of'('FLAG1FALSE','src_span'(20,31,20,41,761,10)),'src_span'(20,28,20,30,757,26)),'src_span_operator'('no_loc_info_available','src_span'(20,12,20,14,742,2))),'prefix'('src_span'(21,15,21,27,786,12),[],'p2resetflag1','val_of'('FLAG1FALSE','src_span'(21,31,21,41,802,10)),'src_span'(21,28,21,30,798,26)),'src_span_operator'('no_loc_info_available','src_span'(21,12,21,14,783,2))),'src_span'(16,1,21,41,567,245)).
'bindval'('FLAG1TRUE','[]'('[]'('[]'('[]'('[]'('prefix'('src_span'(22,15,22,29,827,14),[],'p1gettrueflag1','val_of'('FLAG1TRUE','src_span'(22,33,22,42,845,9)),'src_span'(22,30,22,32,841,27)),'prefix'('src_span'(23,15,23,29,869,14),[],'p2gettrueflag1','val_of'('FLAG1TRUE','src_span'(23,33,23,42,887,9)),'src_span'(23,30,23,32,883,27)),'src_span_operator'('no_loc_info_available','src_span'(23,12,23,14,866,2))),'prefix'('src_span'(24,15,24,27,911,12),[],'p1resetflag1','val_of'('FLAG1FALSE','src_span'(24,31,24,41,927,10)),'src_span'(24,28,24,30,923,26)),'src_span_operator'('no_loc_info_available','src_span'(24,12,24,14,908,2))),'prefix'('src_span'(25,15,25,27,952,12),[],'p2resetflag1','val_of'('FLAG1FALSE','src_span'(25,31,25,41,968,10)),'src_span'(25,28,25,30,964,26)),'src_span_operator'('no_loc_info_available','src_span'(25,12,25,14,949,2))),'prefix'('src_span'(26,15,26,25,993,10),[],'p1setflag1','val_of'('FLAG1TRUE','src_span'(26,29,26,38,1007,9)),'src_span'(26,26,26,28,1003,23)),'src_span_operator'('no_loc_info_available','src_span'(26,12,26,14,990,2))),'prefix'('src_span'(27,15,27,25,1031,10),[],'p2setflag1','val_of'('FLAG1TRUE','src_span'(27,29,27,38,1045,9)),'src_span'(27,26,27,28,1041,23)),'src_span_operator'('no_loc_info_available','src_span'(27,12,27,14,1028,2))),'src_span'(22,1,27,38,813,241)).
'bindval'('FLAG2','val_of'('FLAG2FALSE','src_span'(29,9,29,19,1064,10)),'src_span'(29,1,29,19,1056,18)).
'bindval'('FLAG2FALSE','[]'('[]'('[]'('[]'('[]'('prefix'('src_span'(30,15,30,30,1089,15),[],'p1getfalseflag2','val_of'('FLAG2FALSE','src_span'(30,34,30,44,1108,10)),'src_span'(30,31,30,33,1104,29)),'prefix'('src_span'(31,15,31,30,1133,15),[],'p2getfalseflag2','val_of'('FLAG2FALSE','src_span'(31,34,31,44,1152,10)),'src_span'(31,31,31,33,1148,29)),'src_span_operator'('no_loc_info_available','src_span'(31,12,31,14,1130,2))),'prefix'('src_span'(32,15,32,25,1177,10),[],'p1setflag2','val_of'('FLAG2TRUE','src_span'(32,29,32,38,1191,9)),'src_span'(32,26,32,28,1187,23)),'src_span_operator'('no_loc_info_available','src_span'(32,12,32,14,1174,2))),'prefix'('src_span'(33,15,33,25,1215,10),[],'p2setflag2','val_of'('FLAG2TRUE','src_span'(33,29,33,38,1229,9)),'src_span'(33,26,33,28,1225,23)),'src_span_operator'('no_loc_info_available','src_span'(33,12,33,14,1212,2))),'prefix'('src_span'(34,15,34,27,1253,12),[],'p1resetflag2','val_of'('FLAG2FALSE','src_span'(34,31,34,41,1269,10)),'src_span'(34,28,34,30,1265,26)),'src_span_operator'('no_loc_info_available','src_span'(34,12,34,14,1250,2))),'prefix'('src_span'(35,15,35,27,1294,12),[],'p2resetflag2','val_of'('FLAG2FALSE','src_span'(35,31,35,41,1310,10)),'src_span'(35,28,35,30,1306,26)),'src_span_operator'('no_loc_info_available','src_span'(35,12,35,14,1291,2))),'src_span'(30,1,35,41,1075,245)).
'bindval'('FLAG2TRUE','[]'('[]'('[]'('[]'('[]'('prefix'('src_span'(36,15,36,29,1335,14),[],'p1gettrueflag2','val_of'('FLAG2TRUE','src_span'(36,33,36,42,1353,9)),'src_span'(36,30,36,32,1349,27)),'prefix'('src_span'(37,15,37,29,1377,14),[],'p2gettrueflag2','val_of'('FLAG2TRUE','src_span'(37,33,37,42,1395,9)),'src_span'(37,30,37,32,1391,27)),'src_span_operator'('no_loc_info_available','src_span'(37,12,37,14,1374,2))),'prefix'('src_span'(38,15,38,27,1419,12),[],'p1resetflag2','val_of'('FLAG2FALSE','src_span'(38,31,38,41,1435,10)),'src_span'(38,28,38,30,1431,26)),'src_span_operator'('no_loc_info_available','src_span'(38,12,38,14,1416,2))),'prefix'('src_span'(39,15,39,27,1460,12),[],'p2resetflag2','val_of'('FLAG2FALSE','src_span'(39,31,39,41,1476,10)),'src_span'(39,28,39,30,1472,26)),'src_span_operator'('no_loc_info_available','src_span'(39,12,39,14,1457,2))),'prefix'('src_span'(40,15,40,25,1501,10),[],'p1setflag2','val_of'('FLAG2TRUE','src_span'(40,29,40,38,1515,9)),'src_span'(40,26,40,28,1511,23)),'src_span_operator'('no_loc_info_available','src_span'(40,12,40,14,1498,2))),'prefix'('src_span'(41,15,41,25,1539,10),[],'p2setflag2','val_of'('FLAG2TRUE','src_span'(41,29,41,38,1553,9)),'src_span'(41,26,41,28,1549,23)),'src_span_operator'('no_loc_info_available','src_span'(41,12,41,14,1536,2))),'src_span'(36,1,41,38,1321,241)).
'bindval'('TURN','val_of'('TURNONE','src_span'(43,8,43,15,1571,7)),'src_span'(43,1,43,15,1564,14)).
'bindval'('TURNONE','[]'('[]'('[]'('[]'('[]'('prefix'('src_span'(44,12,44,22,1590,10),[],'p1get1turn','val_of'('TURNONE','src_span'(44,26,44,33,1604,7)),'src_span'(44,23,44,25,1600,21)),'prefix'('src_span'(45,12,45,22,1623,10),[],'p2get1turn','val_of'('TURNONE','src_span'(45,26,45,33,1637,7)),'src_span'(45,23,45,25,1633,21)),'src_span_operator'('no_loc_info_available','src_span'(45,9,45,11,1620,2))),'prefix'('src_span'(46,12,46,22,1656,10),[],'p1set1turn','val_of'('TURNONE','src_span'(46,26,46,33,1670,7)),'src_span'(46,23,46,25,1666,21)),'src_span_operator'('no_loc_info_available','src_span'(46,9,46,11,1653,2))),'prefix'('src_span'(47,12,47,22,1689,10),[],'p2set1turn','val_of'('TURNONE','src_span'(47,26,47,33,1703,7)),'src_span'(47,23,47,25,1699,21)),'src_span_operator'('no_loc_info_available','src_span'(47,9,47,11,1686,2))),'prefix'('src_span'(48,12,48,22,1722,10),[],'p1set2turn','val_of'('TURNTWO','src_span'(48,26,48,33,1736,7)),'src_span'(48,23,48,25,1732,21)),'src_span_operator'('no_loc_info_available','src_span'(48,9,48,11,1719,2))),'prefix'('src_span'(49,12,49,22,1755,10),[],'p2set2turn','val_of'('TURNTWO','src_span'(49,26,49,33,1769,7)),'src_span'(49,23,49,25,1765,21)),'src_span_operator'('no_loc_info_available','src_span'(49,9,49,11,1752,2))),'src_span'(44,1,49,33,1579,197)).
'bindval'('TURNTWO','[]'('[]'('[]'('[]'('[]'('prefix'('src_span'(50,12,50,22,1788,10),[],'p1get2turn','val_of'('TURNTWO','src_span'(50,26,50,33,1802,7)),'src_span'(50,23,50,25,1798,21)),'prefix'('src_span'(51,12,51,22,1821,10),[],'p2get2turn','val_of'('TURNTWO','src_span'(51,26,51,33,1835,7)),'src_span'(51,23,51,25,1831,21)),'src_span_operator'('no_loc_info_available','src_span'(51,9,51,11,1818,2))),'prefix'('src_span'(52,12,52,22,1854,10),[],'p1set1turn','val_of'('TURNONE','src_span'(52,26,52,33,1868,7)),'src_span'(52,23,52,25,1864,21)),'src_span_operator'('no_loc_info_available','src_span'(52,9,52,11,1851,2))),'prefix'('src_span'(53,12,53,22,1887,10),[],'p2set1turn','val_of'('TURNONE','src_span'(53,26,53,33,1901,7)),'src_span'(53,23,53,25,1897,21)),'src_span_operator'('no_loc_info_available','src_span'(53,9,53,11,1884,2))),'prefix'('src_span'(54,12,54,22,1920,10),[],'p1set2turn','val_of'('TURNTWO','src_span'(54,26,54,33,1934,7)),'src_span'(54,23,54,25,1930,21)),'src_span_operator'('no_loc_info_available','src_span'(54,9,54,11,1917,2))),'prefix'('src_span'(55,12,55,22,1953,10),[],'p2set2turn','val_of'('TURNTWO','src_span'(55,26,55,33,1967,7)),'src_span'(55,23,55,25,1963,21)),'src_span_operator'('no_loc_info_available','src_span'(55,9,55,11,1950,2))),'src_span'(50,1,55,33,1777,197)).
'bindval'('P1','prefix'('src_span'(57,6,57,16,1981,10),[],'p1setflag1','prefix'('src_span'(57,20,57,30,1995,10),[],'p1set2turn','val_of'('P1WAIT','src_span'(57,34,57,40,2009,6)),'src_span'(57,31,57,33,2005,20)),'src_span'(57,17,57,19,1991,34)),'src_span'(57,1,57,40,1976,39)).
'bindval'('P1WAIT','[]'('prefix'('src_span'(59,11,59,25,2027,14),[],'p1gettrueflag2','[]'('prefix'('src_span'(59,30,59,40,2046,10),[],'p1get2turn','val_of'('P1WAIT','src_span'(59,44,59,50,2060,6)),'src_span'(59,41,59,43,2056,20)),'prefix'('src_span'(60,32,60,42,2098,10),[],'p1get1turn','val_of'('P1ENTER','src_span'(60,46,60,53,2112,7)),'src_span'(60,43,60,45,2108,21)),'src_span_operator'('no_loc_info_available','src_span'(60,29,60,31,2095,2))),'src_span'(59,26,59,28,2041,93)),'prefix'('src_span'(61,11,61,26,2131,15),[],'p1getfalseflag2','val_of'('P1ENTER','src_span'(61,30,61,37,2150,7)),'src_span'(61,27,61,29,2146,26)),'src_span_operator'('no_loc_info_available','src_span'(61,8,61,10,2128,2))),'src_span'(59,1,61,37,2017,140)).
'bindval'('P1ENTER','prefix'('src_span'(63,11,63,18,2169,7),[],'p1enter','prefix'('src_span'(63,22,63,32,2180,10),[],'p1critical','prefix'('src_span'(63,36,63,43,2194,7),[],'p1leave','prefix'('src_span'(63,47,63,59,2205,12),[],'p1resetflag1','val_of'('P1','src_span'(63,63,63,65,2221,2)),'src_span'(63,60,63,62,2217,18)),'src_span'(63,44,63,46,2201,29)),'src_span'(63,33,63,35,2190,43)),'src_span'(63,19,63,21,2176,54)),'src_span'(63,1,63,65,2159,64)).
'bindval'('P2','prefix'('src_span'(65,6,65,16,2230,10),[],'p2setflag2','prefix'('src_span'(65,20,65,30,2244,10),[],'p2set1turn','val_of'('P2WAIT','src_span'(65,34,65,40,2258,6)),'src_span'(65,31,65,33,2254,20)),'src_span'(65,17,65,19,2240,34)),'src_span'(65,1,65,40,2225,39)).
'bindval'('P2WAIT','[]'('prefix'('src_span'(67,11,67,25,2276,14),[],'p2gettrueflag1','[]'('prefix'('src_span'(67,30,67,40,2295,10),[],'p2get1turn','val_of'('P2WAIT','src_span'(67,44,67,50,2309,6)),'src_span'(67,41,67,43,2305,20)),'prefix'('src_span'(68,32,68,42,2347,10),[],'p2get2turn','val_of'('P2ENTER','src_span'(68,46,68,53,2361,7)),'src_span'(68,43,68,45,2357,21)),'src_span_operator'('no_loc_info_available','src_span'(68,29,68,31,2344,2))),'src_span'(67,26,67,28,2290,93)),'prefix'('src_span'(69,11,69,26,2380,15),[],'p2getfalseflag1','val_of'('P2ENTER','src_span'(69,30,69,37,2399,7)),'src_span'(69,27,69,29,2395,26)),'src_span_operator'('no_loc_info_available','src_span'(69,8,69,10,2377,2))),'src_span'(67,1,69,37,2266,140)).
'bindval'('P2ENTER','prefix'('src_span'(71,11,71,18,2418,7),[],'p2enter','prefix'('src_span'(71,22,71,32,2429,10),[],'p2critical','prefix'('src_span'(71,36,71,43,2443,7),[],'p2leave','prefix'('src_span'(71,47,71,59,2454,12),[],'p2resetflag2','val_of'('P2','src_span'(71,63,71,65,2470,2)),'src_span'(71,60,71,62,2466,18)),'src_span'(71,44,71,46,2450,29)),'src_span'(71,33,71,35,2439,43)),'src_span'(71,19,71,21,2425,54)),'src_span'(71,1,71,65,2408,64)).
'bindval'('aP1','closure'(['p1setflag1','p1resetflag1','p1gettrueflag1','p1getfalseflag1','p1setflag2','p1resetflag2','p1gettrueflag2','p1getfalseflag2','p1set1turn','p1set2turn','p1get1turn','p1get2turn','p1enter','p1critical','p1leave']),'src_span'(73,1,76,39,2474,222)).
'bindval'('aP2','closure'(['p2setflag1','p2resetflag1','p2gettrueflag1','p2getfalseflag1','p2setflag2','p2resetflag2','p2gettrueflag2','p2getfalseflag2','p2set1turn','p2set2turn','p2get1turn','p2get2turn','p2enter','p2critical','p2leave']),'src_span'(78,1,81,39,2698,222)).
'bindval'('aF1','closure'(['p1setflag1','p1resetflag1','p1gettrueflag1','p1getfalseflag1','p2setflag1','p2resetflag1','p2gettrueflag1','p2getfalseflag1']),'src_span'(83,1,84,67,2922,131)).
'bindval'('aF2','closure'(['p1setflag2','p1resetflag2','p1gettrueflag2','p1getfalseflag2','p2setflag2','p2resetflag2','p2gettrueflag2','p2getfalseflag2']),'src_span'(86,1,87,67,3055,131)).
'bindval'('aT','closure'(['p1set1turn','p1set2turn','p1get1turn','p1get2turn','p2set1turn','p2set2turn','p2get1turn','p2get2turn']),'src_span'(89,1,90,56,3188,109)).
'bindval'('PROCS','aParallel'('val_of'('aP1','src_span'(92,14,92,17,3312,3)),'val_of'('P1','src_span'(92,9,92,11,3307,2)),'val_of'('aP2','src_span'(92,21,92,24,3319,3)),'val_of'('P2','src_span'(92,27,92,29,3325,2)),'src_span'(92,12,92,26,3310,14)),'src_span'(92,1,92,29,3299,28)).
'bindval'('FLAGS','aParallel'('val_of'('aF1','src_span'(94,17,94,20,3345,3)),'val_of'('FLAG1','src_span'(94,9,94,14,3337,5)),'val_of'('aF2','src_span'(94,24,94,27,3352,3)),'val_of'('FLAG2','src_span'(94,30,94,35,3358,5)),'src_span'(94,15,94,29,3343,14)),'src_span'(94,1,94,35,3329,34)).
'bindval'('VARS','aParallel'('agent_call'('src_span'(96,16,96,21,3380,5),'union',['val_of'('aF1','src_span'(96,22,96,25,3386,3)),'val_of'('aF2','src_span'(96,26,96,29,3390,3))]),'val_of'('FLAGS','src_span'(96,8,96,13,3372,5)),'val_of'('aT','src_span'(96,34,96,36,3398,2)),'val_of'('TURN','src_span'(96,39,96,43,3403,4)),'src_span'(96,14,96,38,3378,24)),'src_span'(96,1,96,43,3365,42)).
'bindval'('SYSTEM','aParallel'('agent_call'('src_span'(98,18,98,23,3426,5),'union',['val_of'('aP1','src_span'(98,24,98,27,3432,3)),'val_of'('aP2','src_span'(98,28,98,31,3436,3))]),'val_of'('PROCS','src_span'(98,10,98,15,3418,5)),'agent_call'('src_span'(98,36,98,41,3444,5),'union',['agent_call'('src_span'(98,42,98,47,3450,5),'union',['val_of'('aF1','src_span'(98,48,98,51,3456,3)),'val_of'('aF2','src_span'(98,52,98,55,3460,3))]),'val_of'('aT','src_span'(98,57,98,59,3465,2))]),'val_of'('VARS','src_span'(98,63,98,67,3471,4)),'src_span'(98,16,98,62,3424,46)),'src_span'(98,1,98,67,3409,66)).
'comment'('lineComment'('-- Peterson\x27\s Algorithm in CSP: version 1'),'src_position'(1,1,0,41)).
'comment'('lineComment'('--'),'src_position'(2,1,42,2)).
'comment'('lineComment'('-- Simon Gay, Royal Holloway, January 1999'),'src_position'(3,1,45,42)).
'comment'('lineComment'('--'),'src_position'(4,1,88,2)).
'symbol'('p1setflag1','p1setflag1','src_span'(6,9,6,19,100,10),'Channel').
'symbol'('p1resetflag1','p1resetflag1','src_span'(6,21,6,33,112,12),'Channel').
'symbol'('p2setflag1','p2setflag1','src_span'(6,35,6,45,126,10),'Channel').
'symbol'('p2resetflag1','p2resetflag1','src_span'(6,47,6,59,138,12),'Channel').
'symbol'('p1gettrueflag1','p1gettrueflag1','src_span'(7,9,7,23,160,14),'Channel').
'symbol'('p1getfalseflag1','p1getfalseflag1','src_span'(7,25,7,40,176,15),'Channel').
'symbol'('p2gettrueflag1','p2gettrueflag1','src_span'(7,42,7,56,193,14),'Channel').
'symbol'('p2getfalseflag1','p2getfalseflag1','src_span'(7,58,7,73,209,15),'Channel').
'symbol'('p1setflag2','p1setflag2','src_span'(8,9,8,19,234,10),'Channel').
'symbol'('p1resetflag2','p1resetflag2','src_span'(8,21,8,33,246,12),'Channel').
'symbol'('p2setflag2','p2setflag2','src_span'(8,35,8,45,260,10),'Channel').
'symbol'('p2resetflag2','p2resetflag2','src_span'(8,47,8,59,272,12),'Channel').
'symbol'('p1gettrueflag2','p1gettrueflag2','src_span'(9,9,9,23,294,14),'Channel').
'symbol'('p1getfalseflag2','p1getfalseflag2','src_span'(9,25,9,40,310,15),'Channel').
'symbol'('p2gettrueflag2','p2gettrueflag2','src_span'(9,42,9,56,327,14),'Channel').
'symbol'('p2getfalseflag2','p2getfalseflag2','src_span'(9,58,9,73,343,15),'Channel').
'symbol'('p1set1turn','p1set1turn','src_span'(10,9,10,19,368,10),'Channel').
'symbol'('p1set2turn','p1set2turn','src_span'(10,21,10,31,380,10),'Channel').
'symbol'('p2set1turn','p2set1turn','src_span'(10,33,10,43,392,10),'Channel').
'symbol'('p2set2turn','p2set2turn','src_span'(10,45,10,55,404,10),'Channel').
'symbol'('p1get1turn','p1get1turn','src_span'(11,9,11,19,424,10),'Channel').
'symbol'('p1get2turn','p1get2turn','src_span'(11,21,11,31,436,10),'Channel').
'symbol'('p2get1turn','p2get1turn','src_span'(11,33,11,43,448,10),'Channel').
'symbol'('p2get2turn','p2get2turn','src_span'(11,45,11,55,460,10),'Channel').
'symbol'('p1enter','p1enter','src_span'(12,9,12,16,480,7),'Channel').
'symbol'('p1critical','p1critical','src_span'(12,18,12,28,489,10),'Channel').
'symbol'('p1leave','p1leave','src_span'(12,30,12,37,501,7),'Channel').
'symbol'('p2enter','p2enter','src_span'(13,9,13,16,518,7),'Channel').
'symbol'('p2critical','p2critical','src_span'(13,18,13,28,527,10),'Channel').
'symbol'('p2leave','p2leave','src_span'(13,30,13,37,539,7),'Channel').
'symbol'('FLAG1','FLAG1','src_span'(15,1,15,6,548,5),'Ident (Groundrep.)').
'symbol'('FLAG1FALSE','FLAG1FALSE','src_span'(16,1,16,11,567,10),'Ident (Groundrep.)').
'symbol'('FLAG1TRUE','FLAG1TRUE','src_span'(22,1,22,10,813,9),'Ident (Groundrep.)').
'symbol'('FLAG2','FLAG2','src_span'(29,1,29,6,1056,5),'Ident (Groundrep.)').
'symbol'('FLAG2FALSE','FLAG2FALSE','src_span'(30,1,30,11,1075,10),'Ident (Groundrep.)').
'symbol'('FLAG2TRUE','FLAG2TRUE','src_span'(36,1,36,10,1321,9),'Ident (Groundrep.)').
'symbol'('TURN','TURN','src_span'(43,1,43,5,1564,4),'Ident (Groundrep.)').
'symbol'('TURNONE','TURNONE','src_span'(44,1,44,8,1579,7),'Ident (Groundrep.)').
'symbol'('TURNTWO','TURNTWO','src_span'(50,1,50,8,1777,7),'Ident (Groundrep.)').
'symbol'('P1','P1','src_span'(57,1,57,3,1976,2),'Ident (Groundrep.)').
'symbol'('P1WAIT','P1WAIT','src_span'(59,1,59,7,2017,6),'Ident (Groundrep.)').
'symbol'('P1ENTER','P1ENTER','src_span'(63,1,63,8,2159,7),'Ident (Groundrep.)').
'symbol'('P2','P2','src_span'(65,1,65,3,2225,2),'Ident (Groundrep.)').
'symbol'('P2WAIT','P2WAIT','src_span'(67,1,67,7,2266,6),'Ident (Groundrep.)').
'symbol'('P2ENTER','P2ENTER','src_span'(71,1,71,8,2408,7),'Ident (Groundrep.)').
'symbol'('aP1','aP1','src_span'(73,1,73,4,2474,3),'Ident (Groundrep.)').
'symbol'('aP2','aP2','src_span'(78,1,78,4,2698,3),'Ident (Groundrep.)').
'symbol'('aF1','aF1','src_span'(83,1,83,4,2922,3),'Ident (Groundrep.)').
'symbol'('aF2','aF2','src_span'(86,1,86,4,3055,3),'Ident (Groundrep.)').
'symbol'('aT','aT','src_span'(89,1,89,3,3188,2),'Ident (Groundrep.)').
'symbol'('PROCS','PROCS','src_span'(92,1,92,6,3299,5),'Ident (Groundrep.)').
'symbol'('FLAGS','FLAGS','src_span'(94,1,94,6,3329,5),'Ident (Groundrep.)').
'symbol'('VARS','VARS','src_span'(96,1,96,5,3365,4),'Ident (Groundrep.)').
'symbol'('union','union','src_span'(96,16,96,21,3380,5),'BuiltIn primitive').
'symbol'('SYSTEM','SYSTEM','src_span'(98,1,98,7,3409,6),'Ident (Groundrep.)').