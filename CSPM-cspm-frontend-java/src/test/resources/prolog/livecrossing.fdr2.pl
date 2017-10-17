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
'dataTypeDef'('UD',['constructor'('raise'),'constructor'('lower')]).
'dataTypeDef'('ELA',['constructor'('enter'),'constructor'('leave'),'constructor'('approach')]).
'channel'('car','type'('dotTupleType'(['ELA']))).
'channel'('train','type'('dotTupleType'(['ELA']))).
'channel'('gate','type'('dotTupleType'(['UD']))).
'bindval'('CARS','prefix'('src_span'(18,8,18,20,360,12),[],'dotTuple'(['car','approach']),'prefix'('src_span'(18,24,18,33,376,9),[],'dotTuple'(['car','enter']),'prefix'('src_span'(18,37,18,46,389,9),[],'dotTuple'(['car','leave']),'val_of'('CARS','src_span'(18,50,18,54,402,4)),'src_span'(18,47,18,49,398,17)),'src_span'(18,34,18,36,385,30)),'src_span'(18,21,18,23,372,46)),'src_span'(18,1,18,54,353,53)).
'bindval'('TRAINS','prefix'('src_span'(22,10,22,24,436,14),[],'dotTuple'(['train','approach']),'prefix'('src_span'(22,28,22,39,454,11),[],'dotTuple'(['train','enter']),'prefix'('src_span'(22,43,22,54,469,11),[],'dotTuple'(['train','leave']),'val_of'('TRAINS','src_span'(22,58,22,64,484,6)),'src_span'(22,55,22,57,480,21)),'src_span'(22,40,22,42,465,36)),'src_span'(22,25,22,27,450,54)),'src_span'(22,1,22,64,427,63)).
'bindval'('CR_UP','[]'('[]'('[]'('[]'('[]'('[]'('prefix'('src_span'(28,10,28,22,688,12),[],'dotTuple'(['car','approach']),'val_of'('CR_UP','src_span'(28,26,28,31,704,5)),'src_span'(28,23,28,25,700,21)),'prefix'('src_span'(29,10,29,19,719,9),[],'dotTuple'(['car','enter']),'val_of'('CR_UP','src_span'(29,23,29,28,732,5)),'src_span'(29,20,29,22,728,18)),'src_span_operator'('no_loc_info_available','src_span'(29,7,29,9,716,2))),'prefix'('src_span'(30,10,30,19,747,9),[],'dotTuple'(['car','leave']),'val_of'('CR_UP','src_span'(30,23,30,28,760,5)),'src_span'(30,20,30,22,756,18)),'src_span_operator'('no_loc_info_available','src_span'(30,7,30,9,744,2))),'prefix'('src_span'(31,10,31,24,775,14),[],'dotTuple'(['train','approach']),'val_of'('CR_UP','src_span'(31,28,31,33,793,5)),'src_span'(31,25,31,27,789,23)),'src_span_operator'('no_loc_info_available','src_span'(31,7,31,9,772,2))),'prefix'('src_span'(32,10,32,21,808,11),[],'dotTuple'(['train','enter']),'val_of'('CR_UP','src_span'(32,25,32,30,823,5)),'src_span'(32,22,32,24,819,20)),'src_span_operator'('no_loc_info_available','src_span'(32,7,32,9,805,2))),'prefix'('src_span'(33,10,33,21,838,11),[],'dotTuple'(['train','leave']),'val_of'('CR_UP','src_span'(33,25,33,30,853,5)),'src_span'(33,22,33,24,849,20)),'src_span_operator'('no_loc_info_available','src_span'(33,7,33,9,835,2))),'prefix'('src_span'(34,10,34,20,868,10),[],'dotTuple'(['gate','lower']),'val_of'('CR_DOWN','src_span'(34,24,34,31,882,7)),'src_span'(34,21,34,23,878,21)),'src_span_operator'('no_loc_info_available','src_span'(34,7,34,9,865,2))),'src_span'(28,1,34,31,679,210)).
'bindval'('CR_DOWN','[]'('[]'('[]'('[]'('[]'('prefix'('src_span'(36,12,36,24,902,12),[],'dotTuple'(['car','approach']),'val_of'('CR_DOWN','src_span'(36,28,36,35,918,7)),'src_span'(36,25,36,27,914,23)),'prefix'('src_span'(37,10,37,19,935,9),[],'dotTuple'(['car','leave']),'val_of'('CR_DOWN','src_span'(37,23,37,30,948,7)),'src_span'(37,20,37,22,944,20)),'src_span_operator'('no_loc_info_available','src_span'(37,7,37,9,932,2))),'prefix'('src_span'(38,10,38,24,965,14),[],'dotTuple'(['train','approach']),'val_of'('CR_DOWN','src_span'(38,28,38,35,983,7)),'src_span'(38,25,38,27,979,25)),'src_span_operator'('no_loc_info_available','src_span'(38,7,38,9,962,2))),'prefix'('src_span'(39,10,39,21,1000,11),[],'dotTuple'(['train','enter']),'val_of'('CR_DOWN','src_span'(39,25,39,32,1015,7)),'src_span'(39,22,39,24,1011,22)),'src_span_operator'('no_loc_info_available','src_span'(39,7,39,9,997,2))),'prefix'('src_span'(40,10,40,21,1032,11),[],'dotTuple'(['train','leave']),'val_of'('CR_DOWN','src_span'(40,25,40,32,1047,7)),'src_span'(40,22,40,24,1043,22)),'src_span_operator'('no_loc_info_available','src_span'(40,7,40,9,1029,2))),'prefix'('src_span'(41,10,41,20,1064,10),[],'dotTuple'(['gate','raise']),'val_of'('CR_UP','src_span'(41,24,41,29,1078,5)),'src_span'(41,21,41,23,1074,19)),'src_span_operator'('no_loc_info_available','src_span'(41,7,41,9,1061,2))),'src_span'(36,1,41,29,891,192)).
'bindval'('SYSTEM','aParallel'('closure'(['train','car','gate']),'aParallel'('closure'(['train','car','gate']),'val_of'('CR_UP','src_span'(47,11,47,16,1282,5)),'closure'(['car']),'val_of'('CARS','src_span'(47,51,47,55,1322,4)),'src_span'(47,17,47,50,1288,33)),'closure'(['train']),'val_of'('TRAINS','src_span'(48,47,48,53,1374,6)),'src_span'(48,11,48,46,1338,35)),'src_span'(47,1,48,53,1272,108)).
'bindval'('SPEC','[]'('prefix'('src_span'(53,9,53,20,1492,11),[],'dotTuple'(['train','enter']),'prefix'('src_span'(53,24,53,35,1507,11),[],'dotTuple'(['train','leave']),'val_of'('SPEC','src_span'(53,39,53,43,1522,4)),'src_span'(53,36,53,38,1518,19)),'src_span'(53,21,53,23,1503,34)),'prefix'('src_span'(54,9,54,18,1535,9),[],'dotTuple'(['car','enter']),'prefix'('src_span'(54,22,54,31,1548,9),[],'dotTuple'(['car','leave']),'val_of'('SPEC','src_span'(54,35,54,39,1561,4)),'src_span'(54,32,54,34,1557,17)),'src_span'(54,19,54,21,1544,30)),'src_span_operator'('no_loc_info_available','src_span'(54,6,54,8,1532,2))),'src_span'(53,1,54,39,1484,81)).
'agent'('RUN'(_A),'repChoice'(['comprehensionGenerator'(_x,_A)],'prefix'('src_span'(58,19,58,20,1603,1),[],_x,'agent_call'('src_span'(58,24,58,27,1608,3),'RUN',[_A]),'src_span'(58,21,58,23,1604,11)),'src_span'(58,13,58,18,1597,5)),'src_span'(58,10,58,30,1594,20)).
'assertRef'('False','val_of'('SPEC','src_span'(62,8,62,12,1669,4)),'Trace','\x5c\'('val_of'('SYSTEM','src_span'(62,17,62,23,1678,6)),'closure'(['gate','dotTuple'(['car','approach']),'dotTuple'(['train','approach'])]),'src_span_operator'('no_loc_info_available','src_span'(62,24,62,25,1685,1))),'src_span'(62,1,62,62,1662,61)).
'bindval'('CONTROL','[]'('prefix'('src_span'(66,12,66,26,1780,14),[],'dotTuple'(['train','approach']),'prefix'('src_span'(66,30,66,40,1798,10),[],'dotTuple'(['gate','lower']),'val_of'('CONTROL_D','src_span'(66,44,66,53,1812,9)),'src_span'(66,41,66,43,1808,23)),'src_span'(66,27,66,29,1794,41)),'prefix'('src_span'(67,12,67,24,1833,12),[],'dotTuple'(['car','approach']),'prefix'('src_span'(67,28,67,37,1849,9),[],'dotTuple'(['car','enter']),'prefix'('src_span'(67,41,67,50,1862,9),[],'dotTuple'(['car','leave']),'val_of'('CONTROL','src_span'(67,54,67,61,1875,7)),'src_span'(67,51,67,53,1871,20)),'src_span'(67,38,67,40,1858,33)),'src_span'(67,25,67,27,1845,49)),'src_span_operator'('no_loc_info_available','src_span'(67,9,67,11,1830,2))),'src_span'(66,1,67,61,1769,113)).
'bindval'('CONTROL_D','prefix'('src_span'(68,13,68,24,1895,11),[],'dotTuple'(['train','enter']),'prefix'('src_span'(68,28,68,39,1910,11),[],'dotTuple'(['train','leave']),'[]'('prefix'('src_span'(68,44,68,54,1926,10),[],'dotTuple'(['gate','raise']),'val_of'('CONTROL','src_span'(68,58,68,65,1940,7)),'src_span'(68,55,68,57,1936,21)),'prefix'('src_span'(69,46,69,60,1993,14),[],'dotTuple'(['train','approach']),'val_of'('CONTROL_D','src_span'(69,64,69,73,2011,9)),'src_span'(69,61,69,63,2007,27)),'src_span_operator'('no_loc_info_available','src_span'(69,43,69,45,1990,2))),'src_span'(68,40,68,42,1921,111)),'src_span'(68,25,68,27,1906,126)),'src_span'(68,1,69,74,1883,138)).
'bindval'('SAFE_SYSTEM','aParallel'('closure'(['train','car','gate']),'val_of'('SYSTEM','src_span'(73,15,73,21,2071,6)),'closure'(['train','car','gate']),'val_of'('CONTROL','src_span'(75,15,75,22,2154,7)),'src_span'(74,17,74,61,2095,44)),'src_span'(73,1,75,22,2057,104)).
'assertRef'('False','val_of'('SPEC','src_span'(79,8,79,12,2195,4)),'Trace','\x5c\'('val_of'('SAFE_SYSTEM','src_span'(79,17,79,28,2204,11)),'closure'(['gate','dotTuple'(['car','approach']),'dotTuple'(['train','approach'])]),'src_span_operator'('no_loc_info_available','src_span'(79,29,79,30,2216,1))),'src_span'(79,1,79,67,2188,66)).
'bindval'('CARSPEC','prefix'('src_span'(83,11,83,23,2298,12),[],'dotTuple'(['car','approach']),'prefix'('src_span'(83,27,83,36,2314,9),[],'dotTuple'(['car','enter']),'prefix'('src_span'(83,40,83,49,2327,9),[],'dotTuple'(['car','leave']),'val_of'('CARSPEC','src_span'(83,53,83,60,2340,7)),'src_span'(83,50,83,52,2336,20)),'src_span'(83,37,83,39,2323,33)),'src_span'(83,24,83,26,2310,49)),'src_span'(83,1,83,60,2288,59)).
'bindval'('TRAINSPEC','prefix'('src_span'(85,13,85,27,2361,14),[],'dotTuple'(['train','approach']),'prefix'('src_span'(85,31,85,42,2379,11),[],'dotTuple'(['train','enter']),'prefix'('src_span'(85,46,85,57,2394,11),[],'dotTuple'(['train','leave']),'val_of'('TRAINSPEC','src_span'(85,61,85,70,2409,9)),'src_span'(85,58,85,60,2405,24)),'src_span'(85,43,85,45,2390,39)),'src_span'(85,28,85,30,2375,57)),'src_span'(85,1,85,70,2349,69)).
'assertRef'('False','val_of'('CARSPEC','src_span'(89,8,89,15,2446,7)),'Failure','\x5c\'('val_of'('SAFE_SYSTEM','src_span'(89,21,89,32,2459,11)),'closure'(['train','gate']),'src_span_operator'('no_loc_info_available','src_span'(89,33,89,34,2471,1))),'src_span'(89,1,89,50,2439,49)).
'assertRef'('False','val_of'('TRAINSPEC','src_span'(91,8,91,17,2497,9)),'Failure','\x5c\'('val_of'('SAFE_SYSTEM','src_span'(91,23,91,34,2512,11)),'closure'(['car','gate']),'src_span_operator'('no_loc_info_available','src_span'(91,35,91,36,2524,1))),'src_span'(91,1,91,50,2490,49)).
'bindval'('NC','prefix'('src_span'(93,6,93,16,2546,10),[],'dotTuple'(['gate','lower']),'stop'('src_span'(93,20,93,24,2560,4)),'src_span'(93,17,93,19,2556,18)),'src_span'(93,1,93,24,2541,23)).
'bindval'('NEW_SAFE_SYSTEM','aParallel'('closure'(['train','car','gate']),'val_of'('SAFE_SYSTEM','src_span'(95,19,95,30,2584,11)),'closure'(['gate']),'val_of'('NC','src_span'(95,66,95,68,2631,2)),'src_span'(95,31,95,65,2596,34)),'src_span'(95,1,95,68,2566,67)).
'assertRef'('False','val_of'('CARSPEC','src_span'(97,8,97,15,2642,7)),'Failure','\x5c\'('val_of'('NEW_SAFE_SYSTEM','src_span'(97,21,97,36,2655,15)),'closure'(['train','gate']),'src_span_operator'('no_loc_info_available','src_span'(97,37,97,38,2671,1))),'src_span'(97,1,97,54,2635,53)).
'assertRef'('False','val_of'('TRAINSPEC','src_span'(99,8,99,17,2697,9)),'Failure','\x5c\'('val_of'('NEW_SAFE_SYSTEM','src_span'(99,23,99,38,2712,15)),'closure'(['car','gate']),'src_span_operator'('no_loc_info_available','src_span'(99,39,99,40,2728,1))),'src_span'(99,1,99,54,2690,53)).
'comment'('lineComment'('-- The level crossing with a liveness specification.'),'src_position'(1,1,0,52)).
'comment'('lineComment'('--'),'src_position'(2,1,53,2)).
'comment'('lineComment'('-- Simon Gay, Royal Holloway, January 1999'),'src_position'(3,1,56,42)).
'comment'('lineComment'('--'),'src_position'(4,1,99,2)).
'comment'('lineComment'('-- The purpose of these datatype and channel declarations is to give'),'src_position'(6,1,103,68)).
'comment'('lineComment'('-- us events car.enter, gate.raise etc'),'src_position'(7,1,172,38)).
'comment'('lineComment'('-- A supply of cars'),'src_position'(16,1,332,19)).
'comment'('lineComment'('-- ... and trains'),'src_position'(20,1,408,17)).
'comment'('lineComment'('-- The behaviour of the crossing, unconstrained by any sensible'),'src_position'(24,1,492,63)).
'comment'('lineComment'('-- restrictions on when the gate should be raised. The only'),'src_position'(25,1,556,59)).
'comment'('lineComment'('-- thing not allowed is a car crossing when the gate is down.'),'src_position'(26,1,616,61)).
'comment'('lineComment'('-- The crossing, cars and trains - still no restrictions on the gate'),'src_position'(43,1,1085,68)).
'comment'('lineComment'('-- Notice that we only have to put the channel names, not all the '),'src_position'(44,1,1154,66)).
'comment'('lineComment'('-- individual events, in the alphabets for [ || ]'),'src_position'(45,1,1221,49)).
'comment'('lineComment'('-- The safety specification doesn\x27\t allow a car and a train to enter'),'src_position'(50,1,1382,68)).
'comment'('lineComment'('-- the crossing simultaneously.'),'src_position'(51,1,1451,31)).
'comment'('lineComment'('-- Define RUN(A)'),'src_position'(56,1,1567,16)).
'comment'('lineComment'('-- The assertion is best written with hiding'),'src_position'(60,1,1616,44)).
'comment'('lineComment'('-- Now we can restrict the use of the gate'),'src_position'(64,1,1725,42)).
'comment'('lineComment'('-- The system with control added'),'src_position'(71,1,2023,32)).
'comment'('lineComment'('-- and a new assertion'),'src_position'(77,1,2164,22)).
'comment'('lineComment'('-- The liveness specifications'),'src_position'(81,1,2256,30)).
'comment'('lineComment'('-- and assertions'),'src_position'(87,1,2420,17)).
'symbol'('UD','UD','src_span'(9,10,9,12,221,2),'Datatype').
'symbol'('raise','raise','src_span'(9,15,9,20,226,5),'Constructor of Datatype').
'symbol'('lower','lower','src_span'(9,23,9,28,234,5),'Constructor of Datatype').
'symbol'('ELA','ELA','src_span'(10,10,10,13,249,3),'Datatype').
'symbol'('enter','enter','src_span'(10,16,10,21,255,5),'Constructor of Datatype').
'symbol'('leave','leave','src_span'(10,24,10,29,263,5),'Constructor of Datatype').
'symbol'('approach','approach','src_span'(10,32,10,40,271,8),'Constructor of Datatype').
'symbol'('car','car','src_span'(12,9,12,12,289,3),'Channel').
'symbol'('train','train','src_span'(13,9,13,14,305,5),'Channel').
'symbol'('gate','gate','src_span'(14,9,14,13,323,4),'Channel').
'symbol'('CARS','CARS','src_span'(18,1,18,5,353,4),'Ident (Groundrep.)').
'symbol'('TRAINS','TRAINS','src_span'(22,1,22,7,427,6),'Ident (Groundrep.)').
'symbol'('CR_UP','CR_UP','src_span'(28,1,28,6,679,5),'Ident (Groundrep.)').
'symbol'('CR_DOWN','CR_DOWN','src_span'(36,1,36,8,891,7),'Ident (Groundrep.)').
'symbol'('SYSTEM','SYSTEM','src_span'(47,1,47,7,1272,6),'Ident (Groundrep.)').
'symbol'('SPEC','SPEC','src_span'(53,1,53,5,1484,4),'Ident (Groundrep.)').
'symbol'('RUN','RUN','src_span'(58,1,58,4,1585,3),'Funktion or Process').
'symbol'('A','A','src_span'(58,5,58,6,1589,1),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(58,13,58,14,1597,1),'Ident (Prolog Variable)').
'symbol'('CONTROL','CONTROL','src_span'(66,1,66,8,1769,7),'Ident (Groundrep.)').
'symbol'('CONTROL_D','CONTROL_D','src_span'(68,1,68,10,1883,9),'Ident (Groundrep.)').
'symbol'('SAFE_SYSTEM','SAFE_SYSTEM','src_span'(73,1,73,12,2057,11),'Ident (Groundrep.)').
'symbol'('CARSPEC','CARSPEC','src_span'(83,1,83,8,2288,7),'Ident (Groundrep.)').
'symbol'('TRAINSPEC','TRAINSPEC','src_span'(85,1,85,10,2349,9),'Ident (Groundrep.)').
'symbol'('NC','NC','src_span'(93,1,93,3,2541,2),'Ident (Groundrep.)').
'symbol'('NEW_SAFE_SYSTEM','NEW_SAFE_SYSTEM','src_span'(95,1,95,16,2566,15),'Ident (Groundrep.)').