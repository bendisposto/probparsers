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
'bindval'('FLAG1','val_of'('FLAG1FALSE','src_span'(18,9,18,19,561,10)),'src_span'(18,1,18,19,553,18)).
'bindval'('FLAG1FALSE','[]'('[]'('[]'('[]'('[]'('prefix'('src_span'(19,15,19,30,586,15),[],'p1getfalseflag1','val_of'('FLAG1FALSE','src_span'(19,34,19,44,605,10)),'src_span'(19,31,19,33,601,29)),'prefix'('src_span'(20,15,20,30,630,15),[],'p2getfalseflag1','val_of'('FLAG1FALSE','src_span'(20,34,20,44,649,10)),'src_span'(20,31,20,33,645,29)),'src_span_operator'('no_loc_info_available','src_span'(20,12,20,14,627,2))),'prefix'('src_span'(21,15,21,25,674,10),[],'p1setflag1','val_of'('FLAG1TRUE','src_span'(21,29,21,38,688,9)),'src_span'(21,26,21,28,684,23)),'src_span_operator'('no_loc_info_available','src_span'(21,12,21,14,671,2))),'prefix'('src_span'(22,15,22,25,712,10),[],'p2setflag1','val_of'('FLAG1TRUE','src_span'(22,29,22,38,726,9)),'src_span'(22,26,22,28,722,23)),'src_span_operator'('no_loc_info_available','src_span'(22,12,22,14,709,2))),'prefix'('src_span'(23,15,23,27,750,12),[],'p1resetflag1','val_of'('FLAG1FALSE','src_span'(23,31,23,41,766,10)),'src_span'(23,28,23,30,762,26)),'src_span_operator'('no_loc_info_available','src_span'(23,12,23,14,747,2))),'prefix'('src_span'(24,15,24,27,791,12),[],'p2resetflag1','val_of'('FLAG1FALSE','src_span'(24,31,24,41,807,10)),'src_span'(24,28,24,30,803,26)),'src_span_operator'('no_loc_info_available','src_span'(24,12,24,14,788,2))),'src_span'(19,1,24,41,572,245)).
'bindval'('FLAG1TRUE','[]'('[]'('[]'('[]'('[]'('prefix'('src_span'(25,15,25,29,832,14),[],'p1gettrueflag1','val_of'('FLAG1TRUE','src_span'(25,33,25,42,850,9)),'src_span'(25,30,25,32,846,27)),'prefix'('src_span'(26,15,26,29,874,14),[],'p2gettrueflag1','val_of'('FLAG1TRUE','src_span'(26,33,26,42,892,9)),'src_span'(26,30,26,32,888,27)),'src_span_operator'('no_loc_info_available','src_span'(26,12,26,14,871,2))),'prefix'('src_span'(27,15,27,27,916,12),[],'p1resetflag1','val_of'('FLAG1FALSE','src_span'(27,31,27,41,932,10)),'src_span'(27,28,27,30,928,26)),'src_span_operator'('no_loc_info_available','src_span'(27,12,27,14,913,2))),'prefix'('src_span'(28,15,28,27,957,12),[],'p2resetflag1','val_of'('FLAG1FALSE','src_span'(28,31,28,41,973,10)),'src_span'(28,28,28,30,969,26)),'src_span_operator'('no_loc_info_available','src_span'(28,12,28,14,954,2))),'prefix'('src_span'(29,15,29,25,998,10),[],'p1setflag1','val_of'('FLAG1TRUE','src_span'(29,29,29,38,1012,9)),'src_span'(29,26,29,28,1008,23)),'src_span_operator'('no_loc_info_available','src_span'(29,12,29,14,995,2))),'prefix'('src_span'(30,15,30,25,1036,10),[],'p2setflag1','val_of'('FLAG1TRUE','src_span'(30,29,30,38,1050,9)),'src_span'(30,26,30,28,1046,23)),'src_span_operator'('no_loc_info_available','src_span'(30,12,30,14,1033,2))),'src_span'(25,1,30,38,818,241)).
'bindval'('FLAG2','val_of'('FLAG2FALSE','src_span'(32,9,32,19,1069,10)),'src_span'(32,1,32,19,1061,18)).
'bindval'('FLAG2FALSE','[]'('[]'('[]'('[]'('[]'('prefix'('src_span'(33,15,33,30,1094,15),[],'p1getfalseflag2','val_of'('FLAG2FALSE','src_span'(33,34,33,44,1113,10)),'src_span'(33,31,33,33,1109,29)),'prefix'('src_span'(34,15,34,30,1138,15),[],'p2getfalseflag2','val_of'('FLAG2FALSE','src_span'(34,34,34,44,1157,10)),'src_span'(34,31,34,33,1153,29)),'src_span_operator'('no_loc_info_available','src_span'(34,12,34,14,1135,2))),'prefix'('src_span'(35,15,35,25,1182,10),[],'p1setflag2','val_of'('FLAG2TRUE','src_span'(35,29,35,38,1196,9)),'src_span'(35,26,35,28,1192,23)),'src_span_operator'('no_loc_info_available','src_span'(35,12,35,14,1179,2))),'prefix'('src_span'(36,15,36,25,1220,10),[],'p2setflag2','val_of'('FLAG2TRUE','src_span'(36,29,36,38,1234,9)),'src_span'(36,26,36,28,1230,23)),'src_span_operator'('no_loc_info_available','src_span'(36,12,36,14,1217,2))),'prefix'('src_span'(37,15,37,27,1258,12),[],'p1resetflag2','val_of'('FLAG2FALSE','src_span'(37,31,37,41,1274,10)),'src_span'(37,28,37,30,1270,26)),'src_span_operator'('no_loc_info_available','src_span'(37,12,37,14,1255,2))),'prefix'('src_span'(38,15,38,27,1299,12),[],'p2resetflag2','val_of'('FLAG2FALSE','src_span'(38,31,38,41,1315,10)),'src_span'(38,28,38,30,1311,26)),'src_span_operator'('no_loc_info_available','src_span'(38,12,38,14,1296,2))),'src_span'(33,1,38,41,1080,245)).
'bindval'('FLAG2TRUE','[]'('[]'('[]'('[]'('[]'('prefix'('src_span'(39,15,39,29,1340,14),[],'p1gettrueflag2','val_of'('FLAG2TRUE','src_span'(39,33,39,42,1358,9)),'src_span'(39,30,39,32,1354,27)),'prefix'('src_span'(40,15,40,29,1382,14),[],'p2gettrueflag2','val_of'('FLAG2TRUE','src_span'(40,33,40,42,1400,9)),'src_span'(40,30,40,32,1396,27)),'src_span_operator'('no_loc_info_available','src_span'(40,12,40,14,1379,2))),'prefix'('src_span'(41,15,41,27,1424,12),[],'p1resetflag2','val_of'('FLAG2FALSE','src_span'(41,31,41,41,1440,10)),'src_span'(41,28,41,30,1436,26)),'src_span_operator'('no_loc_info_available','src_span'(41,12,41,14,1421,2))),'prefix'('src_span'(42,15,42,27,1465,12),[],'p2resetflag2','val_of'('FLAG2FALSE','src_span'(42,31,42,41,1481,10)),'src_span'(42,28,42,30,1477,26)),'src_span_operator'('no_loc_info_available','src_span'(42,12,42,14,1462,2))),'prefix'('src_span'(43,15,43,25,1506,10),[],'p1setflag2','val_of'('FLAG2TRUE','src_span'(43,29,43,38,1520,9)),'src_span'(43,26,43,28,1516,23)),'src_span_operator'('no_loc_info_available','src_span'(43,12,43,14,1503,2))),'prefix'('src_span'(44,15,44,25,1544,10),[],'p2setflag2','val_of'('FLAG2TRUE','src_span'(44,29,44,38,1558,9)),'src_span'(44,26,44,28,1554,23)),'src_span_operator'('no_loc_info_available','src_span'(44,12,44,14,1541,2))),'src_span'(39,1,44,38,1326,241)).
'bindval'('TURN','val_of'('TURNONE','src_span'(46,8,46,15,1576,7)),'src_span'(46,1,46,15,1569,14)).
'bindval'('TURNONE','[]'('[]'('[]'('[]'('[]'('prefix'('src_span'(47,12,47,22,1595,10),[],'p1get1turn','val_of'('TURNONE','src_span'(47,26,47,33,1609,7)),'src_span'(47,23,47,25,1605,21)),'prefix'('src_span'(48,12,48,22,1628,10),[],'p2get1turn','val_of'('TURNONE','src_span'(48,26,48,33,1642,7)),'src_span'(48,23,48,25,1638,21)),'src_span_operator'('no_loc_info_available','src_span'(48,9,48,11,1625,2))),'prefix'('src_span'(49,12,49,22,1661,10),[],'p1set1turn','val_of'('TURNONE','src_span'(49,26,49,33,1675,7)),'src_span'(49,23,49,25,1671,21)),'src_span_operator'('no_loc_info_available','src_span'(49,9,49,11,1658,2))),'prefix'('src_span'(50,12,50,22,1694,10),[],'p2set1turn','val_of'('TURNONE','src_span'(50,26,50,33,1708,7)),'src_span'(50,23,50,25,1704,21)),'src_span_operator'('no_loc_info_available','src_span'(50,9,50,11,1691,2))),'prefix'('src_span'(51,12,51,22,1727,10),[],'p1set2turn','val_of'('TURNTWO','src_span'(51,26,51,33,1741,7)),'src_span'(51,23,51,25,1737,21)),'src_span_operator'('no_loc_info_available','src_span'(51,9,51,11,1724,2))),'prefix'('src_span'(52,12,52,22,1760,10),[],'p2set2turn','val_of'('TURNTWO','src_span'(52,26,52,33,1774,7)),'src_span'(52,23,52,25,1770,21)),'src_span_operator'('no_loc_info_available','src_span'(52,9,52,11,1757,2))),'src_span'(47,1,52,33,1584,197)).
'bindval'('TURNTWO','[]'('[]'('[]'('[]'('[]'('prefix'('src_span'(53,12,53,22,1793,10),[],'p1get2turn','val_of'('TURNTWO','src_span'(53,26,53,33,1807,7)),'src_span'(53,23,53,25,1803,21)),'prefix'('src_span'(54,12,54,22,1826,10),[],'p2get2turn','val_of'('TURNTWO','src_span'(54,26,54,33,1840,7)),'src_span'(54,23,54,25,1836,21)),'src_span_operator'('no_loc_info_available','src_span'(54,9,54,11,1823,2))),'prefix'('src_span'(55,12,55,22,1859,10),[],'p1set1turn','val_of'('TURNONE','src_span'(55,26,55,33,1873,7)),'src_span'(55,23,55,25,1869,21)),'src_span_operator'('no_loc_info_available','src_span'(55,9,55,11,1856,2))),'prefix'('src_span'(56,12,56,22,1892,10),[],'p2set1turn','val_of'('TURNONE','src_span'(56,26,56,33,1906,7)),'src_span'(56,23,56,25,1902,21)),'src_span_operator'('no_loc_info_available','src_span'(56,9,56,11,1889,2))),'prefix'('src_span'(57,12,57,22,1925,10),[],'p1set2turn','val_of'('TURNTWO','src_span'(57,26,57,33,1939,7)),'src_span'(57,23,57,25,1935,21)),'src_span_operator'('no_loc_info_available','src_span'(57,9,57,11,1922,2))),'prefix'('src_span'(58,12,58,22,1958,10),[],'p2set2turn','val_of'('TURNTWO','src_span'(58,26,58,33,1972,7)),'src_span'(58,23,58,25,1968,21)),'src_span_operator'('no_loc_info_available','src_span'(58,9,58,11,1955,2))),'src_span'(53,1,58,33,1782,197)).
'bindval'('P1','prefix'('src_span'(60,6,60,16,1986,10),[],'p1setflag1','prefix'('src_span'(60,20,60,30,2000,10),[],'p1set2turn','val_of'('P1WAIT','src_span'(60,34,60,40,2014,6)),'src_span'(60,31,60,33,2010,20)),'src_span'(60,17,60,19,1996,34)),'src_span'(60,1,60,40,1981,39)).
'bindval'('P1WAIT','[]'('prefix'('src_span'(62,11,62,25,2032,14),[],'p1gettrueflag2','[]'('prefix'('src_span'(62,30,62,40,2051,10),[],'p1get2turn','val_of'('P1WAIT','src_span'(62,44,62,50,2065,6)),'src_span'(62,41,62,43,2061,20)),'prefix'('src_span'(63,32,63,42,2103,10),[],'p1get1turn','val_of'('P1ENTER','src_span'(63,46,63,53,2117,7)),'src_span'(63,43,63,45,2113,21)),'src_span_operator'('no_loc_info_available','src_span'(63,29,63,31,2100,2))),'src_span'(62,26,62,28,2046,93)),'prefix'('src_span'(64,11,64,26,2136,15),[],'p1getfalseflag2','val_of'('P1ENTER','src_span'(64,30,64,37,2155,7)),'src_span'(64,27,64,29,2151,26)),'src_span_operator'('no_loc_info_available','src_span'(64,8,64,10,2133,2))),'src_span'(62,1,64,37,2022,140)).
'bindval'('P1ENTER','prefix'('src_span'(66,11,66,18,2174,7),[],'p1enter','prefix'('src_span'(66,22,66,32,2185,10),[],'p1critical','prefix'('src_span'(66,36,66,43,2199,7),[],'p1leave','prefix'('src_span'(66,47,66,59,2210,12),[],'p1resetflag1','val_of'('P1','src_span'(66,63,66,65,2226,2)),'src_span'(66,60,66,62,2222,18)),'src_span'(66,44,66,46,2206,29)),'src_span'(66,33,66,35,2195,43)),'src_span'(66,19,66,21,2181,54)),'src_span'(66,1,66,65,2164,64)).
'bindval'('P2','prefix'('src_span'(68,6,68,16,2235,10),[],'p2setflag2','prefix'('src_span'(68,20,68,30,2249,10),[],'p2set1turn','val_of'('P2WAIT','src_span'(68,34,68,40,2263,6)),'src_span'(68,31,68,33,2259,20)),'src_span'(68,17,68,19,2245,34)),'src_span'(68,1,68,40,2230,39)).
'bindval'('P2WAIT','[]'('prefix'('src_span'(70,11,70,25,2281,14),[],'p2gettrueflag1','[]'('prefix'('src_span'(70,30,70,40,2300,10),[],'p2get1turn','val_of'('P2WAIT','src_span'(70,44,70,50,2314,6)),'src_span'(70,41,70,43,2310,20)),'prefix'('src_span'(71,32,71,42,2352,10),[],'p2get2turn','val_of'('P2ENTER','src_span'(71,46,71,53,2366,7)),'src_span'(71,43,71,45,2362,21)),'src_span_operator'('no_loc_info_available','src_span'(71,29,71,31,2349,2))),'src_span'(70,26,70,28,2295,93)),'prefix'('src_span'(72,11,72,26,2385,15),[],'p2getfalseflag1','val_of'('P2ENTER','src_span'(72,30,72,37,2404,7)),'src_span'(72,27,72,29,2400,26)),'src_span_operator'('no_loc_info_available','src_span'(72,8,72,10,2382,2))),'src_span'(70,1,72,37,2271,140)).
'bindval'('P2ENTER','prefix'('src_span'(74,11,74,18,2423,7),[],'p2enter','prefix'('src_span'(74,22,74,32,2434,10),[],'p2critical','prefix'('src_span'(74,36,74,43,2448,7),[],'p2leave','prefix'('src_span'(74,47,74,59,2459,12),[],'p2resetflag2','val_of'('P2','src_span'(74,63,74,65,2475,2)),'src_span'(74,60,74,62,2471,18)),'src_span'(74,44,74,46,2455,29)),'src_span'(74,33,74,35,2444,43)),'src_span'(74,19,74,21,2430,54)),'src_span'(74,1,74,65,2413,64)).
'bindval'('aP1','closure'(['p1setflag1','p1resetflag1','p1gettrueflag1','p1getfalseflag1','p1setflag2','p1resetflag2','p1gettrueflag2','p1getfalseflag2','p1set1turn','p1set2turn','p1get1turn','p1get2turn','p1enter','p1critical','p1leave']),'src_span'(76,1,79,39,2479,222)).
'bindval'('aP2','closure'(['p2setflag1','p2resetflag1','p2gettrueflag1','p2getfalseflag1','p2setflag2','p2resetflag2','p2gettrueflag2','p2getfalseflag2','p2set1turn','p2set2turn','p2get1turn','p2get2turn','p2enter','p2critical','p2leave']),'src_span'(81,1,84,39,2703,222)).
'bindval'('aF1','closure'(['p1setflag1','p1resetflag1','p1gettrueflag1','p1getfalseflag1','p2setflag1','p2resetflag1','p2gettrueflag1','p2getfalseflag1']),'src_span'(86,1,87,67,2927,131)).
'bindval'('aF2','closure'(['p1setflag2','p1resetflag2','p1gettrueflag2','p1getfalseflag2','p2setflag2','p2resetflag2','p2gettrueflag2','p2getfalseflag2']),'src_span'(89,1,90,67,3060,131)).
'bindval'('aT','closure'(['p1set1turn','p1set2turn','p1get1turn','p1get2turn','p2set1turn','p2set2turn','p2get1turn','p2get2turn']),'src_span'(92,1,93,56,3193,109)).
'bindval'('PROCS','aParallel'('val_of'('aP1','src_span'(95,14,95,17,3317,3)),'val_of'('P1','src_span'(95,9,95,11,3312,2)),'val_of'('aP2','src_span'(95,21,95,24,3324,3)),'val_of'('P2','src_span'(95,27,95,29,3330,2)),'src_span'(95,12,95,26,3315,14)),'src_span'(95,1,95,29,3304,28)).
'bindval'('FLAGS','aParallel'('val_of'('aF1','src_span'(97,17,97,20,3350,3)),'val_of'('FLAG1','src_span'(97,9,97,14,3342,5)),'val_of'('aF2','src_span'(97,24,97,27,3357,3)),'val_of'('FLAG2','src_span'(97,30,97,35,3363,5)),'src_span'(97,15,97,29,3348,14)),'src_span'(97,1,97,35,3334,34)).
'bindval'('VARS','aParallel'('agent_call'('src_span'(99,16,99,21,3385,5),'union',['val_of'('aF1','src_span'(99,22,99,25,3391,3)),'val_of'('aF2','src_span'(99,26,99,29,3395,3))]),'val_of'('FLAGS','src_span'(99,8,99,13,3377,5)),'val_of'('aT','src_span'(99,34,99,36,3403,2)),'val_of'('TURN','src_span'(99,39,99,43,3408,4)),'src_span'(99,14,99,38,3383,24)),'src_span'(99,1,99,43,3370,42)).
'bindval'('SYSTEM','aParallel'('agent_call'('src_span'(101,18,101,23,3431,5),'union',['val_of'('aP1','src_span'(101,24,101,27,3437,3)),'val_of'('aP2','src_span'(101,28,101,31,3441,3))]),'val_of'('PROCS','src_span'(101,10,101,15,3423,5)),'agent_call'('src_span'(101,36,101,41,3449,5),'union',['agent_call'('src_span'(101,42,101,47,3455,5),'union',['val_of'('aF1','src_span'(101,48,101,51,3461,3)),'val_of'('aF2','src_span'(101,52,101,55,3465,3))]),'val_of'('aT','src_span'(101,57,101,59,3470,2))]),'val_of'('VARS','src_span'(101,63,101,67,3476,4)),'src_span'(101,16,101,62,3429,46)),'src_span'(101,1,101,67,3414,66)).
'comment'('lineComment'('-- Peterson\x27\s Algorithm in CSP: version 1'),'src_position'(1,1,0,41)).
'comment'('lineComment'('--'),'src_position'(2,1,42,2)).
'comment'('lineComment'('-- Simon Gay, Royal Holloway, January 1999'),'src_position'(3,1,45,42)).
'comment'('lineComment'('--'),'src_position'(4,1,88,2)).
'comment'('lineComment'('--'),'src_position'(5,1,91,2)).
'symbol'('p1setflag1','p1setflag1','src_span'(9,9,9,19,105,10),'Channel').
'symbol'('p1resetflag1','p1resetflag1','src_span'(9,21,9,33,117,12),'Channel').
'symbol'('p2setflag1','p2setflag1','src_span'(9,35,9,45,131,10),'Channel').
'symbol'('p2resetflag1','p2resetflag1','src_span'(9,47,9,59,143,12),'Channel').
'symbol'('p1gettrueflag1','p1gettrueflag1','src_span'(10,9,10,23,165,14),'Channel').
'symbol'('p1getfalseflag1','p1getfalseflag1','src_span'(10,25,10,40,181,15),'Channel').
'symbol'('p2gettrueflag1','p2gettrueflag1','src_span'(10,42,10,56,198,14),'Channel').
'symbol'('p2getfalseflag1','p2getfalseflag1','src_span'(10,58,10,73,214,15),'Channel').
'symbol'('p1setflag2','p1setflag2','src_span'(11,9,11,19,239,10),'Channel').
'symbol'('p1resetflag2','p1resetflag2','src_span'(11,21,11,33,251,12),'Channel').
'symbol'('p2setflag2','p2setflag2','src_span'(11,35,11,45,265,10),'Channel').
'symbol'('p2resetflag2','p2resetflag2','src_span'(11,47,11,59,277,12),'Channel').
'symbol'('p1gettrueflag2','p1gettrueflag2','src_span'(12,9,12,23,299,14),'Channel').
'symbol'('p1getfalseflag2','p1getfalseflag2','src_span'(12,25,12,40,315,15),'Channel').
'symbol'('p2gettrueflag2','p2gettrueflag2','src_span'(12,42,12,56,332,14),'Channel').
'symbol'('p2getfalseflag2','p2getfalseflag2','src_span'(12,58,12,73,348,15),'Channel').
'symbol'('p1set1turn','p1set1turn','src_span'(13,9,13,19,373,10),'Channel').
'symbol'('p1set2turn','p1set2turn','src_span'(13,21,13,31,385,10),'Channel').
'symbol'('p2set1turn','p2set1turn','src_span'(13,33,13,43,397,10),'Channel').
'symbol'('p2set2turn','p2set2turn','src_span'(13,45,13,55,409,10),'Channel').
'symbol'('p1get1turn','p1get1turn','src_span'(14,9,14,19,429,10),'Channel').
'symbol'('p1get2turn','p1get2turn','src_span'(14,21,14,31,441,10),'Channel').
'symbol'('p2get1turn','p2get1turn','src_span'(14,33,14,43,453,10),'Channel').
'symbol'('p2get2turn','p2get2turn','src_span'(14,45,14,55,465,10),'Channel').
'symbol'('p1enter','p1enter','src_span'(15,9,15,16,485,7),'Channel').
'symbol'('p1critical','p1critical','src_span'(15,18,15,28,494,10),'Channel').
'symbol'('p1leave','p1leave','src_span'(15,30,15,37,506,7),'Channel').
'symbol'('p2enter','p2enter','src_span'(16,9,16,16,523,7),'Channel').
'symbol'('p2critical','p2critical','src_span'(16,18,16,28,532,10),'Channel').
'symbol'('p2leave','p2leave','src_span'(16,30,16,37,544,7),'Channel').
'symbol'('FLAG1','FLAG1','src_span'(18,1,18,6,553,5),'Ident (Groundrep.)').
'symbol'('FLAG1FALSE','FLAG1FALSE','src_span'(19,1,19,11,572,10),'Ident (Groundrep.)').
'symbol'('FLAG1TRUE','FLAG1TRUE','src_span'(25,1,25,10,818,9),'Ident (Groundrep.)').
'symbol'('FLAG2','FLAG2','src_span'(32,1,32,6,1061,5),'Ident (Groundrep.)').
'symbol'('FLAG2FALSE','FLAG2FALSE','src_span'(33,1,33,11,1080,10),'Ident (Groundrep.)').
'symbol'('FLAG2TRUE','FLAG2TRUE','src_span'(39,1,39,10,1326,9),'Ident (Groundrep.)').
'symbol'('TURN','TURN','src_span'(46,1,46,5,1569,4),'Ident (Groundrep.)').
'symbol'('TURNONE','TURNONE','src_span'(47,1,47,8,1584,7),'Ident (Groundrep.)').
'symbol'('TURNTWO','TURNTWO','src_span'(53,1,53,8,1782,7),'Ident (Groundrep.)').
'symbol'('P1','P1','src_span'(60,1,60,3,1981,2),'Ident (Groundrep.)').
'symbol'('P1WAIT','P1WAIT','src_span'(62,1,62,7,2022,6),'Ident (Groundrep.)').
'symbol'('P1ENTER','P1ENTER','src_span'(66,1,66,8,2164,7),'Ident (Groundrep.)').
'symbol'('P2','P2','src_span'(68,1,68,3,2230,2),'Ident (Groundrep.)').
'symbol'('P2WAIT','P2WAIT','src_span'(70,1,70,7,2271,6),'Ident (Groundrep.)').
'symbol'('P2ENTER','P2ENTER','src_span'(74,1,74,8,2413,7),'Ident (Groundrep.)').
'symbol'('aP1','aP1','src_span'(76,1,76,4,2479,3),'Ident (Groundrep.)').
'symbol'('aP2','aP2','src_span'(81,1,81,4,2703,3),'Ident (Groundrep.)').
'symbol'('aF1','aF1','src_span'(86,1,86,4,2927,3),'Ident (Groundrep.)').
'symbol'('aF2','aF2','src_span'(89,1,89,4,3060,3),'Ident (Groundrep.)').
'symbol'('aT','aT','src_span'(92,1,92,3,3193,2),'Ident (Groundrep.)').
'symbol'('PROCS','PROCS','src_span'(95,1,95,6,3304,5),'Ident (Groundrep.)').
'symbol'('FLAGS','FLAGS','src_span'(97,1,97,6,3334,5),'Ident (Groundrep.)').
'symbol'('VARS','VARS','src_span'(99,1,99,5,3370,4),'Ident (Groundrep.)').
'symbol'('union','union','src_span'(99,16,99,21,3385,5),'BuiltIn primitive').
'symbol'('SYSTEM','SYSTEM','src_span'(101,1,101,7,3414,6),'Ident (Groundrep.)').