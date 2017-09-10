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
'dataTypeDef'('Tag',['constructor'('t1'),'constructor'('t2'),'constructor'('t3')]).
'dataTypeDef'('Data',['constructor'('d1'),'constructor'('d2')]).
'channel'('left','type'('dotTupleType'(['Tag','Data']))).
'channel'('right','type'('dotTupleType'(['Tag','Data']))).
'channel'('snd_mess','type'('dotTupleType'(['Tag','Data']))).
'channel'('rcv_mess','type'('dotTupleType'(['Tag','Data']))).
'channel'('snd_ack','type'('dotTupleType'(['Tag']))).
'channel'('rcv_ack','type'('dotTupleType'(['Tag']))).
'channel'('mess','type'('dotTupleType'(['Tag','Data']))).
'channel'('ack','type'('dotTupleType'(['Tag']))).
'bindval'('SndMess','repChoice'(['comprehensionGenerator'(_i,'Tag')],'prefix'('src_span'(30,23,30,33,1078,10),['in'(_x)],'dotTuple'(['snd_mess',_i]),'prefix'('src_span'(30,41,30,45,1096,4),['out'('dotTuple'([_i,_x]))],'mess','val_of'('SndMess','src_span'(30,55,30,62,1110,7)),'src_span'(30,52,30,54,1106,16)),'src_span'(30,38,30,40,1092,28)),'src_span'(30,14,30,21,1069,7)),'src_span'(30,1,30,63,1056,62)).
'bindval'('RcvMess','prefix'('src_span'(32,11,32,15,1130,4),['in'('dotpat'([_i2,_x2]))],'mess','prefix'('src_span'(32,25,32,35,1144,10),['out'(_x2)],'dotTuple'(['rcv_mess',_i2]),'val_of'('RcvMess','src_span'(32,43,32,50,1162,7)),'src_span'(32,40,32,42,1158,14)),'src_span'(32,22,32,24,1140,34)),'src_span'(32,1,32,50,1120,49)).
'bindval'('SndAck','repChoice'(['comprehensionGenerator'(_i3,'Tag')],'prefix'('src_span'(34,22,34,31,1192,9),[],'dotTuple'(['snd_ack',_i3]),'prefix'('src_span'(34,36,34,39,1206,3),['out'(_i3)],'ack','val_of'('SndAck','src_span'(34,47,34,53,1217,6)),'src_span'(34,44,34,46,1213,13)),'src_span'(34,32,34,35,1201,31)),'src_span'(34,13,34,20,1183,7)),'src_span'(34,1,34,54,1171,53)).
'bindval'('RcvAck','prefix'('src_span'(36,10,36,13,1235,3),['in'(_i4)],'ack','prefix'('src_span'(36,21,36,30,1246,9),[],'dotTuple'(['rcv_ack',_i4]),'val_of'('RcvAck','src_span'(36,34,36,40,1259,6)),'src_span'(36,31,36,33,1255,19)),'src_span'(36,18,36,20,1242,26)),'src_span'(36,1,36,40,1226,39)).
'agent'('Tx'(_i5),'prefix'('src_span'(42,9,42,15,1437,6),['in'(_x3)],'dotTuple'(['left',_i5]),'prefix'('src_span'(42,23,42,33,1451,10),['out'(_x3)],'dotTuple'(['snd_mess',_i5]),'prefix'('src_span'(42,41,42,50,1469,9),[],'dotTuple'(['rcv_ack',_i5]),'agent_call'('src_span'(42,54,42,56,1482,2),'Tx',[_i5]),'src_span'(42,51,42,53,1478,18)),'src_span'(42,38,42,40,1465,25)),'src_span'(42,20,42,22,1447,43)),'src_span'(42,9,42,59,1437,50)).
'agent'('Rx'(_i6),'prefix'('src_span'(44,9,44,19,1497,10),['in'(_x4)],'dotTuple'(['rcv_mess',_i6]),'prefix'('src_span'(44,27,44,34,1515,7),['out'(_x4)],'dotTuple'(['right',_i6]),'prefix'('src_span'(44,42,44,51,1530,9),[],'dotTuple'(['snd_ack',_i6]),'agent_call'('src_span'(44,55,44,57,1543,2),'Rx',[_i6]),'src_span'(44,52,44,54,1539,18)),'src_span'(44,39,44,41,1526,25)),'src_span'(44,24,44,26,1511,40)),'src_span'(44,9,44,60,1497,51)).
'agent'('FaultyRx'(_i7),'prefix'('src_span'(46,15,46,25,1564,10),['in'(_x5)],'dotTuple'(['rcv_mess',_i7]),'prefix'('src_span'(46,33,46,40,1582,7),['out'(_x5)],'dotTuple'(['right',_i7]),'|~|'('agent_call'('src_span'(46,48,46,56,1597,8),'FaultyRx',[_i7]),'prefix'('src_span'(47,51,47,60,1659,9),[],'dotTuple'(['snd_ack',_i7]),'agent_call'('src_span'(47,64,47,72,1672,8),'FaultyRx',[_i7]),'src_span'(47,61,47,63,1668,24)),'src_span_operator'('no_loc_info_available','src_span'(47,47,47,50,1655,3))),'src_span'(46,45,46,46,1593,94)),'src_span'(46,30,46,32,1578,109)),'src_span'(46,15,47,76,1564,120)).
'bindval'('Txs','repInterleave'(['comprehensionGenerator'(_i8,'Tag')],'agent_call'('src_span'(51,19,51,21,1768,2),'Tx',[_i8]),'src_span'(51,11,51,18,1760,7)),'src_span'(51,1,51,24,1750,23)).
'bindval'('LHS','\x5c\'('sharing'('closure'(['snd_mess','rcv_ack']),'val_of'('Txs','src_span'(56,8,56,11,1886,3)),'|||'('val_of'('SndMess','src_span'(56,38,56,45,1916,7)),'val_of'('RcvAck','src_span'(56,50,56,56,1928,6)),'src_span_operator'('no_loc_info_available','src_span'(56,46,56,49,1924,3))),'src_span'(56,12,56,37,1890,25)),'closure'(['snd_mess','rcv_ack']),'src_span_operator'('no_loc_info_available','src_span'(56,58,56,59,1936,1))),'src_span'(56,1,56,80,1879,79)).
'bindval'('Rxs','repInterleave'(['comprehensionGenerator'(_i9,'Tag')],'agent_call'('src_span'(60,19,60,21,2028,2),'Rx',[_i9]),'src_span'(60,11,60,18,2020,7)),'src_span'(60,1,60,24,2010,23)).
'bindval'('FaultyRxs','|||'('|||'('agent_call'('src_span'(62,13,62,15,2047,2),'Rx',['t1']),'agent_call'('src_span'(62,24,62,26,2058,2),'Rx',['t2']),'src_span_operator'('no_loc_info_available','src_span'(62,20,62,23,2054,3))),'agent_call'('src_span'(62,35,62,43,2069,8),'FaultyRx',['t3']),'src_span_operator'('no_loc_info_available','src_span'(62,31,62,34,2065,3))),'src_span'(62,1,62,47,2035,46)).
'bindval'('RHS','\x5c\'('sharing'('closure'(['rcv_mess','snd_ack']),'val_of'('Rxs','src_span'(64,8,64,11,2096,3)),'|||'('val_of'('RcvMess','src_span'(65,20,65,27,2145,7)),'val_of'('SndAck','src_span'(65,32,65,38,2157,6)),'src_span_operator'('no_loc_info_available','src_span'(65,28,65,31,2153,3))),'src_span'(64,12,64,37,2100,25)),'closure'(['rcv_mess','snd_ack']),'src_span_operator'('no_loc_info_available','src_span'(65,40,65,41,2165,1))),'src_span'(64,1,65,62,2089,98)).
'bindval'('FaultyRHS','\x5c\'('sharing'('closure'(['rcv_mess','snd_ack']),'val_of'('FaultyRxs','src_span'(67,14,67,23,2202,9)),'|||'('val_of'('RcvMess','src_span'(68,26,68,33,2263,7)),'val_of'('SndAck','src_span'(68,38,68,44,2275,6)),'src_span_operator'('no_loc_info_available','src_span'(68,34,68,37,2271,3))),'src_span'(67,24,67,49,2212,25)),'closure'(['rcv_mess','snd_ack']),'src_span_operator'('no_loc_info_available','src_span'(68,46,68,47,2283,1))),'src_span'(67,1,68,68,2189,116)).
'bindval'('System','\x5c\'('sharing'('closure'(['mess','ack']),'val_of'('LHS','src_span'(72,11,72,14,2386,3)),'val_of'('RHS','src_span'(72,33,72,36,2408,3)),'src_span'(72,15,72,32,2390,17)),'closure'(['mess','ack']),'src_span_operator'('no_loc_info_available','src_span'(72,37,72,38,2412,1))),'src_span'(72,1,72,50,2376,49)).
'bindval'('FaultySystem','\x5c\'('sharing'('closure'(['mess','ack']),'val_of'('LHS','src_span'(74,17,74,20,2443,3)),'val_of'('FaultyRHS','src_span'(74,39,74,48,2465,9)),'src_span'(74,21,74,38,2447,17)),'closure'(['mess','ack']),'src_span_operator'('no_loc_info_available','src_span'(74,49,74,50,2475,1))),'src_span'(74,1,74,63,2427,62)).
'agent'('Copy'(_i56),'prefix'('src_span'(79,11,79,17,2589,6),['in'(_x6)],'dotTuple'(['left',_i56]),'prefix'('src_span'(79,25,79,32,2603,7),['out'(_x6)],'dotTuple'(['right',_i56]),'agent_call'('src_span'(79,40,79,44,2618,4),'Copy',[_i56]),'src_span'(79,37,79,39,2614,14)),'src_span'(79,22,79,24,2599,29)),'src_span'(79,11,79,47,2589,36)).
'bindval'('Spec','repInterleave'(['comprehensionGenerator'(_i58,'Tag')],'agent_call'('src_span'(81,20,81,24,2646,4),'Copy',[_i58]),'src_span'(81,12,81,19,2638,7)),'src_span'(81,1,81,27,2627,26)).
'assertRef'('False','val_of'('Spec','src_span'(85,8,85,12,2727,4)),'FailureDivergence','val_of'('System','src_span'(85,18,85,24,2737,6)),'src_span'(85,1,85,24,2720,23)).
'comment'('lineComment'('-- Multiplexed buffers, version for fdr.1.1   -- Bill Roscoe'),'src_position'(1,1,0,60)).
'comment'('lineComment'('-- Modified for fdr.1.2 10/8/92 Dave Jackson'),'src_position'(2,1,61,44)).
'comment'('lineComment'('-- The idea of this example is to multplex a number of buffers down a'),'src_position'(4,1,107,69)).
'comment'('lineComment'('-- pair of channels.  They can all be in one direction, or there might be'),'src_position'(5,1,177,73)).
'comment'('lineComment'('-- some both ways.  The techniques demonstrated here work for all'),'src_position'(6,1,251,65)).
'comment'('lineComment'('-- numbers of buffers, and any types for transmission.  The number of states'),'src_position'(7,1,317,76)).
'comment'('lineComment'('-- in the system can be easily increased to any desired size by increasing'),'src_position'(8,1,394,74)).
'comment'('lineComment'('-- either the number of buffers, or the size of the transmitted type.'),'src_position'(9,1,469,69)).
'comment'('lineComment'('-- The following four processes form the core of the system'),'src_position'(20,1,740,59)).
'comment'('lineComment'('--'),'src_position'(21,1,800,2)).
'comment'('lineComment'('--'),'src_position'(22,1,803,2)).
'comment'('lineComment'('--    --> SndMess -->  ...........   --> RcvMess -->'),'src_position'(23,1,806,52)).
'comment'('lineComment'('--                '),'src_position'(24,1,859,18)).
'comment'('lineComment'('--    <-- RcvAck  <--  ...........   <-- SndAck  <--'),'src_position'(25,1,878,52)).
'comment'('lineComment'('--'),'src_position'(26,1,931,2)).
'comment'('lineComment'('-- SndMess and RcvMess send and receive tagged messages, while'),'src_position'(27,1,934,62)).
'comment'('lineComment'('-- SndAck and RcvAck send and receive acknowledgements.  '),'src_position'(28,1,997,57)).
'comment'('lineComment'('-- These four processes communicate with equal numbers of transmitters (Tx)'),'src_position'(38,1,1268,75)).
'comment'('lineComment'('-- and receivers (Rx), which in turn provide the interface with the'),'src_position'(39,1,1344,67)).
'comment'('lineComment'('-- environment.'),'src_position'(40,1,1412,15)).
'comment'('lineComment'('-- Txs is the collection of transmitters working independently'),'src_position'(49,1,1686,62)).
'comment'('lineComment'('-- LHS is just everything concerned with transmission combined, with'),'src_position'(53,1,1775,68)).
'comment'('lineComment'('-- internal communication hidden.'),'src_position'(54,1,1844,33)).
'comment'('lineComment'('-- The receiving side is built in a similar way.'),'src_position'(58,1,1960,48)).
'comment'('lineComment'('-- Finally we put it all together, and hide internal communication '),'src_position'(70,1,2307,67)).
'comment'('lineComment'('-- The specification is just the parallel composition of several one-place'),'src_position'(76,1,2491,74)).
'comment'('lineComment'('-- buffers.'),'src_position'(77,1,2566,11)).
'comment'('lineComment'('-- Correctness of the system is asserted by  Spec [FD= System.'),'src_position'(83,1,2656,62)).
'comment'('lineComment'('-- If the multiplexer is being used as part of a larger system, then'),'src_position'(87,1,2745,68)).
'comment'('lineComment'('-- it would make a lot of sense to prove that it meets its specification'),'src_position'(88,1,2814,72)).
'comment'('lineComment'('-- and then use its specification in its stead in higher-level system'),'src_position'(89,1,2887,69)).
'comment'('lineComment'('-- descriptions.  This applies even if the higher-level system does not'),'src_position'(90,1,2957,71)).
'comment'('lineComment'('-- break up into smaller components, since the state-space of the'),'src_position'(91,1,3029,65)).
'comment'('lineComment'('-- specification is significantly smaller than that of the multiplexer,'),'src_position'(92,1,3095,71)).
'comment'('lineComment'('-- which will make the verification of a large system quicker.  It is'),'src_position'(93,1,3167,69)).
'comment'('lineComment'('-- even more true if the channels of the multiplexer are used independently,'),'src_position'(94,1,3237,76)).
'comment'('lineComment'('-- in other words if each external channel of the multiplexer is connected'),'src_position'(95,1,3314,74)).
'comment'('lineComment'('-- to a different user, and the users do not interact otherwise,'),'src_position'(96,1,3389,64)).
'comment'('lineComment'('-- for it would then be sufficient to prove that each of the separate'),'src_position'(97,1,3454,69)).
'comment'('lineComment'('-- pairs of processes interacting via our multiplexer are correct relative'),'src_position'(98,1,3524,74)).
'comment'('lineComment'('-- to its own specification, with a simple one-place buffer between them.'),'src_position'(99,1,3599,73)).
'comment'('lineComment'('-- For we would have proved the equivalence, by the correctness of the'),'src_position'(101,1,3674,70)).
'comment'('lineComment'('-- multiplexer, of our system with a set of three-process independent ones.'),'src_position'(102,1,3745,75)).
'symbol'('Tag','Tag','src_span'(11,10,11,13,549,3),'Datatype').
'symbol'('t1','t1','src_span'(11,17,11,19,556,2),'Constructor of Datatype').
'symbol'('t2','t2','src_span'(11,22,11,24,561,2),'Constructor of Datatype').
'symbol'('t3','t3','src_span'(11,27,11,29,566,2),'Constructor of Datatype').
'symbol'('Data','Data','src_span'(12,10,12,14,578,4),'Datatype').
'symbol'('d1','d1','src_span'(12,17,12,19,585,2),'Constructor of Datatype').
'symbol'('d2','d2','src_span'(12,22,12,24,590,2),'Constructor of Datatype').
'symbol'('left','left','src_span'(14,9,14,13,602,4),'Channel').
'symbol'('right','right','src_span'(14,15,14,20,608,5),'Channel').
'symbol'('snd_mess','snd_mess','src_span'(15,9,15,17,633,8),'Channel').
'symbol'('rcv_mess','rcv_mess','src_span'(15,19,15,27,643,8),'Channel').
'symbol'('snd_ack','snd_ack','src_span'(16,9,16,16,671,7),'Channel').
'symbol'('rcv_ack','rcv_ack','src_span'(16,18,16,25,680,7),'Channel').
'symbol'('mess','mess','src_span'(17,9,17,13,704,4),'Channel').
'symbol'('ack','ack','src_span'(18,9,18,12,728,3),'Channel').
'symbol'('SndMess','SndMess','src_span'(30,1,30,8,1056,7),'Ident (Groundrep.)').
'symbol'('i','i','src_span'(30,14,30,15,1069,1),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(30,36,30,37,1091,1),'Ident (Prolog Variable)').
'symbol'('RcvMess','RcvMess','src_span'(32,1,32,8,1120,7),'Ident (Groundrep.)').
'symbol'('i2','i','src_span'(32,18,32,19,1137,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(32,20,32,21,1139,1),'Ident (Prolog Variable)').
'symbol'('SndAck','SndAck','src_span'(34,1,34,7,1171,6),'Ident (Groundrep.)').
'symbol'('i3','i','src_span'(34,13,34,14,1183,1),'Ident (Prolog Variable)').
'symbol'('RcvAck','RcvAck','src_span'(36,1,36,7,1226,6),'Ident (Groundrep.)').
'symbol'('i4','i','src_span'(36,16,36,17,1241,1),'Ident (Prolog Variable)').
'symbol'('Tx','Tx','src_span'(42,1,42,3,1429,2),'Funktion or Process').
'symbol'('i5','i','src_span'(42,4,42,5,1432,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(42,18,42,19,1446,1),'Ident (Prolog Variable)').
'symbol'('Rx','Rx','src_span'(44,1,44,3,1489,2),'Funktion or Process').
'symbol'('i6','i','src_span'(44,4,44,5,1492,1),'Ident (Prolog Variable)').
'symbol'('x4','x','src_span'(44,22,44,23,1510,1),'Ident (Prolog Variable)').
'symbol'('FaultyRx','FaultyRx','src_span'(46,1,46,9,1550,8),'Funktion or Process').
'symbol'('i7','i','src_span'(46,10,46,11,1559,1),'Ident (Prolog Variable)').
'symbol'('x5','x','src_span'(46,28,46,29,1577,1),'Ident (Prolog Variable)').
'symbol'('Txs','Txs','src_span'(51,1,51,4,1750,3),'Ident (Groundrep.)').
'symbol'('i8','i','src_span'(51,11,51,12,1760,1),'Ident (Prolog Variable)').
'symbol'('LHS','LHS','src_span'(56,1,56,4,1879,3),'Ident (Groundrep.)').
'symbol'('Rxs','Rxs','src_span'(60,1,60,4,2010,3),'Ident (Groundrep.)').
'symbol'('i9','i','src_span'(60,11,60,12,2020,1),'Ident (Prolog Variable)').
'symbol'('FaultyRxs','FaultyRxs','src_span'(62,1,62,10,2035,9),'Ident (Groundrep.)').
'symbol'('RHS','RHS','src_span'(64,1,64,4,2089,3),'Ident (Groundrep.)').
'symbol'('FaultyRHS','FaultyRHS','src_span'(67,1,67,10,2189,9),'Ident (Groundrep.)').
'symbol'('System','System','src_span'(72,1,72,7,2376,6),'Ident (Groundrep.)').
'symbol'('FaultySystem','FaultySystem','src_span'(74,1,74,13,2427,12),'Ident (Groundrep.)').
'symbol'('Copy','Copy','src_span'(79,1,79,5,2579,4),'Funktion or Process').
'symbol'('i56','i','src_span'(79,6,79,7,2584,1),'Ident (Prolog Variable)').
'symbol'('x6','x','src_span'(79,20,79,21,2598,1),'Ident (Prolog Variable)').
'symbol'('Spec','Spec','src_span'(81,1,81,5,2627,4),'Ident (Groundrep.)').
'symbol'('i58','i','src_span'(81,12,81,13,2638,1),'Ident (Prolog Variable)').