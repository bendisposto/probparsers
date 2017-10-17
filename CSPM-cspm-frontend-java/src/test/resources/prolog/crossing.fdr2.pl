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
'channel'('car','type'('dotTupleType'(['LOC']))).
'channel'('train','type'('dotTupleType'(['LOC']))).
'channel'('gate','type'('dotTupleType'(['POS']))).
'channel'('crash','type'('dotUnitType')).
'dataTypeDef'('LOC',['constructor'('approach'),'constructor'('enter'),'constructor'('leave')]).
'dataTypeDef'('POS',['constructor'('raise'),'constructor'('lower')]).
'bindval'('CR','[]'('prefix'('src_span'(15,6,15,18,386,12),[],'dotTuple'(['car','approach']),'prefix'('src_span'(15,22,15,31,402,9),[],'dotTuple'(['car','enter']),'val_of'('C','src_span'(15,35,15,36,415,1)),'src_span'(15,32,15,34,411,14)),'src_span'(15,19,15,21,398,30)),'prefix'('src_span'(16,9,16,23,425,14),[],'dotTuple'(['train','approach']),'prefix'('src_span'(16,27,16,38,443,11),[],'dotTuple'(['train','enter']),'val_of'('T','src_span'(16,42,16,43,458,1)),'src_span'(16,39,16,41,454,16)),'src_span'(16,24,16,26,439,34)),'src_span_operator'('no_loc_info_available','src_span'(16,6,16,8,422,2))),'src_span'(15,1,16,43,381,78)).
'bindval'('C','[]'('prefix'('src_span'(18,5,18,14,465,9),[],'dotTuple'(['car','leave']),'val_of'('CR','src_span'(18,18,18,20,478,2)),'src_span'(18,15,18,17,474,15)),'prefix'('src_span'(19,8,19,22,488,14),[],'dotTuple'(['train','approach']),'prefix'('src_span'(19,26,19,37,506,11),[],'dotTuple'(['train','enter']),'val_of'('CT','src_span'(19,41,19,43,521,2)),'src_span'(19,38,19,40,517,17)),'src_span'(19,23,19,25,502,35)),'src_span_operator'('no_loc_info_available','src_span'(19,5,19,7,485,2))),'src_span'(18,1,19,43,461,62)).
'bindval'('T','[]'('prefix'('src_span'(21,5,21,16,529,11),[],'dotTuple'(['train','leave']),'val_of'('CR','src_span'(21,20,21,22,544,2)),'src_span'(21,17,21,19,540,17)),'prefix'('src_span'(22,8,22,20,554,12),[],'dotTuple'(['car','approach']),'prefix'('src_span'(22,24,22,33,570,9),[],'dotTuple'(['car','enter']),'val_of'('CT','src_span'(22,37,22,39,583,2)),'src_span'(22,34,22,36,579,15)),'src_span'(22,21,22,23,566,31)),'src_span_operator'('no_loc_info_available','src_span'(22,5,22,7,551,2))),'src_span'(21,1,22,39,525,60)).
'bindval'('CT','prefix'('src_span'(24,6,24,11,592,5),[],'crash','stop'('src_span'(24,15,24,19,601,4)),'src_span'(24,12,24,14,597,13)),'src_span'(24,1,24,19,587,18)).
'bindval'('GATE','[]'('prefix'('src_span'(26,8,26,18,614,10),[],'dotTuple'(['gate','lower']),'prefix'('src_span'(26,22,26,32,628,10),[],'dotTuple'(['gate','raise']),'val_of'('GATE','src_span'(26,36,26,40,642,4)),'src_span'(26,33,26,35,638,18)),'src_span'(26,19,26,21,624,32)),'prefix'('src_span'(27,11,27,20,657,9),[],'dotTuple'(['car','enter']),'val_of'('GATE','src_span'(27,24,27,28,670,4)),'src_span'(27,21,27,23,666,17)),'src_span_operator'('no_loc_info_available','src_span'(27,8,27,10,654,2))),'src_span'(26,1,27,28,607,67)).
'bindval'('CARS','prefix'('src_span'(29,8,29,20,683,12),[],'dotTuple'(['car','approach']),'prefix'('src_span'(29,24,29,33,699,9),[],'dotTuple'(['car','enter']),'prefix'('src_span'(29,37,29,46,712,9),[],'dotTuple'(['car','leave']),'val_of'('CARS','src_span'(29,50,29,54,725,4)),'src_span'(29,47,29,49,721,17)),'src_span'(29,34,29,36,708,30)),'src_span'(29,21,29,23,695,46)),'src_span'(29,1,29,54,676,53)).
'bindval'('TRAINS','prefix'('src_span'(31,10,31,24,740,14),[],'dotTuple'(['train','approach']),'prefix'('src_span'(31,28,31,39,758,11),[],'dotTuple'(['train','enter']),'prefix'('src_span'(31,43,31,54,773,11),[],'dotTuple'(['train','leave']),'val_of'('TRAINS','src_span'(31,58,31,64,788,6)),'src_span'(31,55,31,57,784,21)),'src_span'(31,40,31,42,769,36)),'src_span'(31,25,31,27,754,54)),'src_span'(31,1,31,64,731,63)).
'bindval'('ET','closure'(['train']),'src_span'(33,1,33,17,796,16)).
'bindval'('EC','closure'(['car']),'src_span'(35,1,35,15,814,14)).
'bindval'('EGC','setExp'('rangeEnum'(['dotTuple'(['gate','raise']),'dotTuple'(['gate','lower']),'dotTuple'(['car','enter'])])),'src_span'(37,1,37,42,830,41)).
'bindval'('EX','setExp'('rangeEnum'(['crash'])),'src_span'(39,1,39,13,873,12)).
'bindval'('ES','closure'(['train','car','gate','crash']),'src_span'(41,1,41,35,887,34)).
'bindval'('ETCC','closure'(['train','car','crash']),'src_span'(43,1,43,31,923,30)).
'bindval'('ETCG','closure'(['train','car','gate']),'src_span'(45,1,45,30,955,29)).
'bindval'('SYSTEM','aParallel'('val_of'('ES','src_span'(47,50,47,52,1035,2)),'aParallel'('val_of'('ES','src_span'(47,34,47,36,1019,2)),'aParallel'('val_of'('ETCC','src_span'(47,16,47,20,1001,4)),'val_of'('CR','src_span'(47,12,47,14,997,2)),'val_of'('EGC','src_span'(47,22,47,25,1007,3)),'val_of'('GATE','src_span'(47,27,47,31,1012,4)),'src_span'(47,15,47,26,1000,11)),'val_of'('EC','src_span'(47,38,47,40,1023,2)),'val_of'('CARS','src_span'(47,42,47,46,1027,4)),'src_span'(47,33,47,41,1018,8)),'val_of'('ET','src_span'(47,54,47,56,1039,2)),'val_of'('TRAINS','src_span'(47,58,47,64,1043,6)),'src_span'(47,49,47,57,1034,8)),'src_span'(47,1,47,64,986,63)).
'bindval'('SPEC','repChoice'(['comprehensionGenerator'(_x,'val_of'('ETCG','src_span'(49,15,49,19,1065,4)))],'prefix'('src_span'(49,22,49,23,1072,1),[],_x,'val_of'('SPEC','src_span'(49,27,49,31,1077,4)),'src_span'(49,24,49,26,1073,9)),'src_span'(49,11,49,21,1061,10)),'src_span'(49,1,49,31,1051,30)).
'assertRef'('False','val_of'('SPEC','src_span'(51,8,51,12,1090,4)),'Trace','val_of'('SYSTEM','src_span'(51,17,51,23,1099,6)),'src_span'(51,1,51,23,1083,22)).
'assertRef'('False','stop'('src_span'(58,8,58,12,1351,4)),'Trace','\x5c\'('val_of'('SYSTEM','src_span'(58,17,58,23,1360,6)),'val_of'('ETCG','src_span'(58,26,58,30,1369,4)),'src_span_operator'('no_loc_info_available','src_span'(58,24,58,25,1367,1))),'src_span'(58,1,58,30,1344,29)).
'bindval'('CONTROL','[]'('prefix'('src_span'(63,11,63,25,1498,14),[],'dotTuple'(['train','approach']),'prefix'('src_span'(63,29,63,39,1516,10),[],'dotTuple'(['gate','lower']),'prefix'('src_span'(64,13,64,24,1543,11),[],'dotTuple'(['train','enter']),'prefix'('src_span'(64,28,64,39,1558,11),[],'dotTuple'(['train','leave']),'prefix'('src_span'(64,43,64,53,1573,10),[],'dotTuple'(['gate','raise']),'val_of'('CONTROL','src_span'(64,57,64,64,1587,7)),'src_span'(64,54,64,56,1583,21)),'src_span'(64,40,64,42,1569,36)),'src_span'(64,25,64,27,1554,51)),'src_span'(63,40,64,12,1526,78)),'src_span'(63,26,63,28,1512,96)),'prefix'('src_span'(65,14,65,26,1608,12),[],'dotTuple'(['car','approach']),'prefix'('src_span'(65,30,65,39,1624,9),[],'dotTuple'(['car','enter']),'prefix'('src_span'(65,43,65,52,1637,9),[],'dotTuple'(['car','leave']),'val_of'('CONTROL','src_span'(65,56,65,63,1650,7)),'src_span'(65,53,65,55,1646,20)),'src_span'(65,40,65,42,1633,33)),'src_span'(65,27,65,29,1620,49)),'src_span_operator'('no_loc_info_available','src_span'(65,11,65,13,1605,2))),'src_span'(63,1,65,63,1488,169)).
'bindval'('SAFE_SYSTEM','aParallel'('val_of'('ES','src_span'(67,23,67,25,1681,2)),'val_of'('SYSTEM','src_span'(67,15,67,21,1673,6)),'val_of'('ETCG','src_span'(67,27,67,31,1685,4)),'val_of'('CONTROL','src_span'(67,33,67,40,1691,7)),'src_span'(67,22,67,32,1680,10)),'src_span'(67,1,67,40,1659,39)).
'assertRef'('False','val_of'('SPEC','src_span'(69,8,69,12,1707,4)),'Trace','val_of'('SAFE_SYSTEM','src_span'(69,17,69,28,1716,11)),'src_span'(69,1,69,28,1700,27)).
'assertRef'('False','stop'('src_span'(71,8,71,12,1736,4)),'Trace','\x5c\'('val_of'('SAFE_SYSTEM','src_span'(71,17,71,28,1745,11)),'val_of'('ETCG','src_span'(71,31,71,35,1759,4)),'src_span_operator'('no_loc_info_available','src_span'(71,29,71,30,1757,1))),'src_span'(71,1,71,35,1729,34)).
'bindval'('SPEC1','repChoice'(['comprehensionGenerator'(_x2,'closure'(['gate']))],'prefix'('src_span'(77,29,77,30,1995,1),[],_x2,'val_of'('SPEC1','src_span'(77,34,77,39,2000,5)),'src_span'(77,31,77,33,1996,10)),'src_span'(77,12,77,28,1978,16)),'src_span'(77,1,77,39,1967,38)).
'bindval'('SPEC2','[]'('prefix'('src_span'(79,9,79,23,2015,14),[],'dotTuple'(['train','approach']),'prefix'('src_span'(79,27,79,38,2033,11),[],'dotTuple'(['train','enter']),'prefix'('src_span'(79,42,79,53,2048,11),[],'dotTuple'(['train','leave']),'val_of'('SPEC2','src_span'(79,57,79,62,2063,5)),'src_span'(79,54,79,56,2059,20)),'src_span'(79,39,79,41,2044,35)),'src_span'(79,24,79,26,2029,53)),'prefix'('src_span'(80,12,80,24,2080,12),[],'dotTuple'(['car','approach']),'prefix'('src_span'(80,28,80,37,2096,9),[],'dotTuple'(['car','enter']),'prefix'('src_span'(80,41,80,50,2109,9),[],'dotTuple'(['car','leave']),'val_of'('SPEC2','src_span'(80,54,80,59,2122,5)),'src_span'(80,51,80,53,2118,18)),'src_span'(80,38,80,40,2105,31)),'src_span'(80,25,80,27,2092,47)),'src_span_operator'('no_loc_info_available','src_span'(80,9,80,11,2077,2))),'src_span'(79,1,80,59,2007,120)).
'bindval'('SPEC0','|||'('val_of'('SPEC1','src_span'(82,9,82,14,2137,5)),'val_of'('SPEC2','src_span'(82,19,82,24,2147,5)),'src_span_operator'('no_loc_info_available','src_span'(82,15,82,18,2143,3))),'src_span'(82,1,82,24,2129,23)).
'assertRef'('False','val_of'('SPEC0','src_span'(84,8,84,13,2161,5)),'Trace','val_of'('SYSTEM','src_span'(84,18,84,24,2171,6)),'src_span'(84,1,84,24,2154,23)).
'assertRef'('False','val_of'('SPEC0','src_span'(86,8,86,13,2186,5)),'Trace','val_of'('SAFE_SYSTEM','src_span'(86,18,86,29,2196,11)),'src_span'(86,1,86,29,2179,28)).
'comment'('lineComment'('-- The level crossing example'),'src_position'(1,1,0,29)).
'comment'('lineComment'('-- Originator: Simon Gay, Royal Holloway, January 1999'),'src_position'(2,1,30,54)).
'comment'('lineComment'('-- Adapted by Steve Schneider, Royal Holloway, May 1999'),'src_position'(3,1,85,55)).
'comment'('lineComment'('-- Given in the lecture slides accompanying the book '),'src_position'(4,1,141,53)).
'comment'('lineComment'('-- `Concurrent and Real Time Systems: the CSP Approach'),'src_position'(5,1,195,54)).
'comment'('lineComment'('-- checking this assertion in FDR will check whether any sequence of events'),'src_position'(53,1,1107,75)).
'comment'('lineComment'('-- can lead to the event `crash\x27\, since performance of this event is the '),'src_position'(54,1,1183,73)).
'comment'('lineComment'('-- only way that SPEC can fail to be met.  This can also be expressed as '),'src_position'(55,1,1257,73)).
'comment'('lineComment'('-- follows:'),'src_position'(56,1,1331,11)).
'comment'('lineComment'('-- Since the system is not safe (as checking the above assertion '),'src_position'(60,1,1375,65)).
'comment'('lineComment'('-- demonstrates), a controller is introduced:'),'src_position'(61,1,1441,45)).
'comment'('lineComment'('-- Finally, the specification which requires the activity of trains and '),'src_position'(73,1,1765,72)).
'comment'('lineComment'('-- cars not to overlap at all, but allows any gate activity intermingled'),'src_position'(74,1,1838,72)).
'comment'('lineComment'('-- with car and train activity, will be given as SPEC0'),'src_position'(75,1,1911,54)).
'symbol'('car','car','src_span'(7,9,7,12,259,3),'Channel').
'symbol'('train','train','src_span'(7,14,7,19,264,5),'Channel').
'symbol'('gate','gate','src_span'(8,9,8,13,284,4),'Channel').
'symbol'('crash','crash','src_span'(9,9,9,14,303,5),'Channel').
'symbol'('LOC','LOC','src_span'(11,10,11,13,319,3),'Datatype').
'symbol'('approach','approach','src_span'(11,16,11,24,325,8),'Constructor of Datatype').
'symbol'('enter','enter','src_span'(11,27,11,32,336,5),'Constructor of Datatype').
'symbol'('leave','leave','src_span'(11,35,11,40,344,5),'Constructor of Datatype').
'symbol'('POS','POS','src_span'(13,10,13,13,360,3),'Datatype').
'symbol'('raise','raise','src_span'(13,16,13,21,366,5),'Constructor of Datatype').
'symbol'('lower','lower','src_span'(13,24,13,29,374,5),'Constructor of Datatype').
'symbol'('CR','CR','src_span'(15,1,15,3,381,2),'Ident (Groundrep.)').
'symbol'('C','C','src_span'(18,1,18,2,461,1),'Ident (Groundrep.)').
'symbol'('T','T','src_span'(21,1,21,2,525,1),'Ident (Groundrep.)').
'symbol'('CT','CT','src_span'(24,1,24,3,587,2),'Ident (Groundrep.)').
'symbol'('GATE','GATE','src_span'(26,1,26,5,607,4),'Ident (Groundrep.)').
'symbol'('CARS','CARS','src_span'(29,1,29,5,676,4),'Ident (Groundrep.)').
'symbol'('TRAINS','TRAINS','src_span'(31,1,31,7,731,6),'Ident (Groundrep.)').
'symbol'('ET','ET','src_span'(33,1,33,3,796,2),'Ident (Groundrep.)').
'symbol'('EC','EC','src_span'(35,1,35,3,814,2),'Ident (Groundrep.)').
'symbol'('EGC','EGC','src_span'(37,1,37,4,830,3),'Ident (Groundrep.)').
'symbol'('EX','EX','src_span'(39,1,39,3,873,2),'Ident (Groundrep.)').
'symbol'('ES','ES','src_span'(41,1,41,3,887,2),'Ident (Groundrep.)').
'symbol'('ETCC','ETCC','src_span'(43,1,43,5,923,4),'Ident (Groundrep.)').
'symbol'('ETCG','ETCG','src_span'(45,1,45,5,955,4),'Ident (Groundrep.)').
'symbol'('SYSTEM','SYSTEM','src_span'(47,1,47,7,986,6),'Ident (Groundrep.)').
'symbol'('SPEC','SPEC','src_span'(49,1,49,5,1051,4),'Ident (Groundrep.)').
'symbol'('x','x','src_span'(49,11,49,12,1061,1),'Ident (Prolog Variable)').
'symbol'('CONTROL','CONTROL','src_span'(63,1,63,8,1488,7),'Ident (Groundrep.)').
'symbol'('SAFE_SYSTEM','SAFE_SYSTEM','src_span'(67,1,67,12,1659,11),'Ident (Groundrep.)').
'symbol'('SPEC1','SPEC1','src_span'(77,1,77,6,1967,5),'Ident (Groundrep.)').
'symbol'('x2','x','src_span'(77,12,77,13,1978,1),'Ident (Prolog Variable)').
'symbol'('SPEC2','SPEC2','src_span'(79,1,79,6,2007,5),'Ident (Groundrep.)').
'symbol'('SPEC0','SPEC0','src_span'(82,1,82,6,2129,5),'Ident (Groundrep.)').