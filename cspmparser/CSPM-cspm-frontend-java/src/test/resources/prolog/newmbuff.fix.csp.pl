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
'cspTransparent'(['normal']).
'bindval'('Tags','setExp'('rangeEnum'(['int'(1),'int'(2),'int'(3)])),'src_span'(17,1,17,15,604,14)).
'bindval'('Data','setExp'('rangeEnum'(['int'(1),'int'(2)])),'src_span'(18,1,18,13,619,12)).
'channel'('left','type'('dotTupleType'(['val_of'('Tags','src_span'(20,29,20,33,661,4)),'val_of'('Data','src_span'(20,36,20,40,668,4))]))).
'channel'('a','type'('dotTupleType'(['val_of'('Tags','src_span'(20,29,20,33,661,4)),'val_of'('Data','src_span'(20,36,20,40,668,4))]))).
'channel'('b','type'('dotTupleType'(['val_of'('Tags','src_span'(20,29,20,33,661,4)),'val_of'('Data','src_span'(20,36,20,40,668,4))]))).
'channel'('right','type'('dotTupleType'(['val_of'('Tags','src_span'(20,29,20,33,661,4)),'val_of'('Data','src_span'(20,36,20,40,668,4))]))).
'channel'('c','type'('dotTupleType'(['val_of'('Tags','src_span'(21,16,21,20,688,4))]))).
'channel'('d','type'('dotTupleType'(['val_of'('Tags','src_span'(21,16,21,20,688,4))]))).
'channel'('mess','type'('dotTupleType'(['val_of'('Tags','src_span'(22,16,22,20,708,4)),'val_of'('Data','src_span'(22,23,22,27,715,4))]))).
'channel'('ack','type'('dotTupleType'(['val_of'('Tags','src_span'(23,16,23,20,735,4))]))).
'bindval'('SM','repChoice'(['comprehensionGenerator'(_i,'val_of'('Tags','src_span'(38,13,38,17,1110,4)))],'prefix'('src_span'(38,21,38,26,1118,5),['in'(_x)],'dotTuple'(['a',_i]),'prefix'('src_span'(38,34,38,38,1131,4),['out'('dotTuple'([_i,_x]))],'mess','val_of'('SM','src_span'(38,50,38,52,1147,2)),'src_span'(38,47,38,49,1143,13)),'src_span'(38,31,38,33,1127,25)),'src_span'(38,9,38,19,1106,10)),'src_span'(38,1,38,53,1098,52)).
'bindval'('RM','prefix'('src_span'(40,6,40,10,1157,4),['in'(_t),'in'(_x2)],'mess','prefix'('src_span'(40,22,40,27,1173,5),['out'(_x2)],'dotTuple'(['b',_t]),'val_of'('RM','src_span'(40,35,40,37,1186,2)),'src_span'(40,32,40,34,1182,9)),'src_span'(40,19,40,21,1169,22)),'src_span'(40,1,40,37,1152,36)).
'bindval'('SA','repChoice'(['comprehensionGenerator'(_i2,'val_of'('Tags','src_span'(42,13,42,17,1202,4)))],'prefix'('src_span'(42,21,42,26,1210,5),[],'dotTuple'(['c',_i2]),'prefix'('src_span'(42,31,42,34,1220,3),['out'(_i2)],'ack','val_of'('SA','src_span'(42,42,42,44,1231,2)),'src_span'(42,39,42,41,1227,9)),'src_span'(42,27,42,30,1215,23)),'src_span'(42,9,42,19,1198,10)),'src_span'(42,1,42,45,1190,44)).
'bindval'('RA','prefix'('src_span'(44,6,44,9,1241,3),['in'(_x3)],'ack','prefix'('src_span'(44,17,44,22,1252,5),[],'dotTuple'(['d',_x3]),'val_of'('RA','src_span'(44,26,44,28,1261,2)),'src_span'(44,23,44,25,1257,11)),'src_span'(44,14,44,16,1248,18)),'src_span'(44,1,44,28,1236,27)).
'agent'('T'(_i3),'prefix'('src_span'(49,8,49,14,1430,6),['in'(_x4)],'dotTuple'(['left',_i3]),'prefix'('src_span'(49,22,49,25,1444,3),['out'(_x4)],'dotTuple'(['a',_i3]),'prefix'('src_span'(49,33,49,36,1455,3),[],'dotTuple'(['d',_i3]),'agent_call'('src_span'(49,40,49,41,1462,1),'T',[_i3]),'src_span'(49,37,49,39,1458,11)),'src_span'(49,30,49,32,1451,18)),'src_span'(49,19,49,21,1440,29)),'src_span'(49,8,49,44,1430,36)).
'agent'('R'(_i4),'prefix'('src_span'(51,8,51,11,1475,3),['in'(_x5)],'dotTuple'(['b',_i4]),'prefix'('src_span'(51,19,51,26,1486,7),['out'(_x5)],'dotTuple'(['right',_i4]),'prefix'('src_span'(51,34,51,37,1501,3),[],'dotTuple'(['c',_i4]),'agent_call'('src_span'(51,41,51,42,1508,1),'R',[_i4]),'src_span'(51,38,51,40,1504,11)),'src_span'(51,31,51,33,1497,18)),'src_span'(51,16,51,18,1482,33)),'src_span'(51,8,51,45,1475,37)).
'agent'('FAULTYR'(_i5),'prefix'('src_span'(53,14,53,17,1527,3),['in'(_x6)],'dotTuple'(['b',_i5]),'prefix'('src_span'(53,25,53,32,1538,7),['out'(_x6)],'dotTuple'(['right',_i5]),'|~|'('agent_call'('src_span'(53,41,53,48,1554,7),'FAULTYR',[_i5]),'prefix'('src_span'(53,56,53,59,1569,3),[],'dotTuple'(['c',_i5]),'agent_call'('src_span'(53,63,53,70,1576,7),'FAULTYR',[_i5]),'src_span'(53,60,53,62,1572,17)),'src_span_operator'('no_loc_info_available','src_span'(53,52,53,55,1565,3))),'src_span'(53,37,53,39,1549,41)),'src_span'(53,22,53,24,1534,56)),'src_span'(53,14,53,74,1527,60)).
'bindval'('INS','repInterleave'(['comprehensionGenerator'(_i6,'val_of'('Tags','src_span'(58,15,58,19,1718,4)))],'agent_call'('src_span'(58,23,58,24,1726,1),'T',[_i6]),'src_span'(58,11,58,21,1714,10)),'src_span'(58,1,58,27,1704,26)).
'bindval'('ASM','closure'(['a','mess']),'src_span'(63,1,63,20,1869,19)).
'bindval'('ARA','closure'(['d','ack']),'src_span'(64,1,64,19,1889,18)).
'bindval'('LHS','agent_call'('src_span'(67,7,67,13,1977,6),'normal',['lParallel'('linkList'(['link'('a','a'),'link'('d','d')]),'val_of'('INS','src_span'(67,14,67,17,1984,3)),'|||'('val_of'('SM','src_span'(67,34,67,36,2004,2)),'val_of'('RA','src_span'(67,41,67,43,2011,2)),'src_span_operator'('no_loc_info_available','src_span'(67,37,67,40,2007,3))),'src_span'(67,18,67,32,1988,14))]),'src_span'(67,1,67,45,1971,44)).
'bindval'('AR1','closure'(['dotTuple'(['right','int'(1)]),'dotTuple'(['b','int'(1)]),'dotTuple'(['c','int'(1)])]),'src_span'(74,1,74,28,2169,27)).
'bindval'('AR2','closure'(['dotTuple'(['right','int'(2)]),'dotTuple'(['b','int'(2)]),'dotTuple'(['c','int'(2)])]),'src_span'(75,1,75,28,2197,27)).
'bindval'('AR3','closure'(['dotTuple'(['right','int'(3)]),'dotTuple'(['b','int'(3)]),'dotTuple'(['c','int'(3)])]),'src_span'(76,1,76,28,2225,27)).
'bindval'('OUTS','repInterleave'(['comprehensionGenerator'(_i7,'val_of'('Tags','src_span'(78,16,78,20,2269,4)))],'agent_call'('src_span'(78,23,78,24,2276,1),'R',[_i7]),'src_span'(78,12,78,22,2265,10)),'src_span'(78,1,78,27,2254,26)).
'bindval'('FAULTYOUTS','repInterleave'(['comprehensionGenerator'(_i8,'val_of'('Tags','src_span'(80,22,80,26,2303,4)))],'agent_call'('src_span'(80,29,80,58,2310,29),'ifte'('!='(_i8,'int'(3)),'R','FAULTYR','no_loc_info_available','no_loc_info_available','src_span'(80,45,80,49,2325,14)),[_i8]),'src_span'(80,18,80,28,2299,10)),'src_span'(80,1,80,61,2282,60)).
'bindval'('RHS','agent_call'('src_span'(82,7,82,13,2356,6),'normal',['lParallel'('linkList'(['link'('b','b'),'link'('c','c')]),'val_of'('OUTS','src_span'(82,14,82,18,2363,4)),'|||'('val_of'('RM','src_span'(82,35,82,37,2384,2)),'val_of'('SA','src_span'(82,42,82,44,2391,2)),'src_span_operator'('no_loc_info_available','src_span'(82,38,82,41,2387,3))),'src_span'(82,19,82,33,2368,14))]),'src_span'(82,1,82,46,2350,45)).
'bindval'('FAULTYRHS','agent_call'('src_span'(84,13,84,19,2409,6),'normal',['lParallel'('linkList'(['link'('b','b'),'link'('c','c')]),'val_of'('FAULTYOUTS','src_span'(84,20,84,30,2416,10)),'|||'('val_of'('RM','src_span'(84,47,84,49,2443,2)),'val_of'('SA','src_span'(84,54,84,56,2450,2)),'src_span_operator'('no_loc_info_available','src_span'(84,50,84,53,2446,3))),'src_span'(84,31,84,45,2427,14))]),'src_span'(84,1,84,58,2397,57)).
'bindval'('SYSTEM','lParallel'('linkList'(['link'('mess','mess'),'link'('ack','ack')]),'val_of'('LHS','src_span'(88,10,88,13,2538,3)),'val_of'('RHS','src_span'(88,39,88,42,2567,3)),'src_span'(88,14,88,38,2542,24)),'src_span'(88,1,88,42,2529,41)).
'bindval'('FAULTYSYSTEM','lParallel'('linkList'(['link'('mess','mess'),'link'('ack','ack')]),'val_of'('LHS','src_span'(90,16,90,19,2587,3)),'val_of'('FAULTYRHS','src_span'(90,45,90,54,2616,9)),'src_span'(90,20,90,44,2591,24)),'src_span'(90,1,90,54,2572,53)).
'agent'('COPY'(_in,_out),'prefix'('src_span'(95,16,95,18,2728,2),['in'(_x7)],_in,'prefix'('src_span'(95,24,95,27,2736,3),['out'(_x7)],_out,'agent_call'('src_span'(95,33,95,37,2745,4),'COPY',[_in,_out]),'src_span'(95,30,95,32,2741,18)),'src_span'(95,21,95,23,2732,27)),'src_span'(95,16,95,45,2728,29)).
'bindval'('SPEC','repInterleave'(['comprehensionGenerator'(_i9,'val_of'('Tags','src_span'(97,16,97,20,2774,4)))],'agent_call'('src_span'(97,23,97,27,2781,4),'COPY',['dotTuple'(['left',_i9]),'dotTuple'(['right',_i9])]),'src_span'(97,12,97,22,2770,10)),'src_span'(97,1,97,44,2759,43)).
'assertRef'('False','val_of'('SPEC','src_span'(101,8,101,12,2906,4)),'FailureDivergence','val_of'('SYSTEM','src_span'(101,18,101,24,2916,6)),'src_span'(101,1,101,24,2899,23)).
'comment'('blockComment'('{-\xa\  Multiplexed buffers\xa\  Original version for FDR1.1, Bill Roscoe\xa\  Modified for FDR1.2 August 1992, Dave Jackson\xa\  Updates for FDR2.11, May 1997, JBS\xa\\xa\  The idea of this example is to multplex a number of buffers down a\xa\  pair of channels.  They can all be in one direction, or there might be\xa\  some both ways.  The techniques demonstrated here work for all\xa\  numbers of buffers, and any types for transmission.  The number of states\xa\  in the system can be easily increased to any desired size by increasing\xa\  either the number of buffers, or the size of the transmitted type.\xa\-}'),'src_position'(1,1,0,582)).
'comment'('blockComment'('{-\xa\  The following four processes form the core of the system\xa\ \xa\     a              mess                b\xa\     --> SM -->  ...........   --> RM -->\xa\                 \xa\     <-- RA <--  ...........   <-- SA <--\xa\     d              ack                 c\xa\ \xa\  SM and RM send and receive tagged messages, while\xa\  SA and RA send and receive acknowledgements.  \xa\-}'),'src_position'(25,1,741,355)).
'comment'('lineComment'('-- These four processes communicate with equal numbers of  transmitters (T)'),'src_position'(46,1,1266,75)).
'comment'('lineComment'('-- and receivers (R), which in turn provide the interface with the environment.'),'src_position'(47,1,1342,79)).
'comment'('lineComment'('-- To get the transmitters, we just combine the T\x27\s'),'src_position'(55,1,1589,51)).
'comment'('lineComment'('-- We can interleave them since they have no events in common'),'src_position'(56,1,1641,61)).
'comment'('lineComment'('-- (the parallel composition of the transmitters, which could have been'),'src_position'(60,1,1732,71)).
'comment'('lineComment'('-- written T1 ||| T2 ||| T3 since their alphabets are disjoint)'),'src_position'(61,1,1804,63)).
'comment'('lineComment'('-- ATS = Union({ AT(i) | i <- Tags }}  AT(i) nowhere defined '),'src_position'(65,1,1908,61)).
'comment'('lineComment'('-- LHS is just everything in the left hand `box\x27\ combined, with its internal'),'src_position'(69,1,2017,76)).
'comment'('lineComment'('-- communication hidden.'),'src_position'(70,1,2094,24)).
'comment'('lineComment'('-- Below we do the same for the right hand end.'),'src_position'(72,1,2120,47)).
'comment'('lineComment'('-- and finally we put it all together, and hide internal communication '),'src_position'(86,1,2456,71)).
'comment'('lineComment'('-- The specification is just the parallel composition of three one-place'),'src_position'(92,1,2627,72)).
'comment'('lineComment'('-- buffers.'),'src_position'(93,1,2700,11)).
'comment'('lineComment'('-- We can check the refinement by Check1 "SPEC" "SYSTEM"; (or the other versions-- of Check) '),'src_position'(99,1,2804,93)).
'comment'('lineComment'('--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%'),'src_position'(104,1,2925,74)).
'comment'('lineComment'('-- You are challenged to modify this example so that some of the channels'),'src_position'(106,1,3001,73)).
'comment'('lineComment'('-- go one way, and some the other (say, 2 and 2).'),'src_position'(107,1,3075,49)).
'comment'('lineComment'('--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%'),'src_position'(109,1,3126,74)).
'comment'('lineComment'('-- If the multiplexer is being used as part of a larger system, then'),'src_position'(111,1,3202,68)).
'comment'('lineComment'('-- it would make a lot of sense to prove that it meets its specification'),'src_position'(112,1,3271,72)).
'comment'('lineComment'('-- and then use its specification in its stead in higher-level system'),'src_position'(113,1,3344,69)).
'comment'('lineComment'('-- descriptions.  This applies even if the higher-level system does not'),'src_position'(114,1,3414,71)).
'comment'('lineComment'('-- break up into smaller components, since the state-space of the'),'src_position'(115,1,3486,65)).
'comment'('lineComment'('-- specification is significantly smaller than that of the multiplexer,'),'src_position'(116,1,3552,71)).
'comment'('lineComment'('-- which will make the verification of a large system quicker.  It is'),'src_position'(117,1,3624,69)).
'comment'('lineComment'('-- even more true if the channels of the multiplexer are used independently,'),'src_position'(118,1,3694,76)).
'comment'('lineComment'('-- in other words if each external channel of the multiplexer is connected'),'src_position'(119,1,3771,74)).
'comment'('lineComment'('-- to a different user, and the users do not interact otherwise,'),'src_position'(120,1,3846,64)).
'comment'('lineComment'('-- for it would then be sufficient to prove that each of the separate'),'src_position'(121,1,3911,69)).
'comment'('lineComment'('-- pairs of processes interacting via our multiplexer are correct relative'),'src_position'(122,1,3981,74)).
'comment'('lineComment'('-- to its own specification, with a simple one-place buffer between them.'),'src_position'(123,1,4056,73)).
'comment'('lineComment'('-- For we would have proved the equivalence, by the correctness of the'),'src_position'(125,1,4131,70)).
'comment'('lineComment'('-- multiplexer, of our system with a set of three-process independent ones.'),'src_position'(126,1,4202,75)).
'symbol'('normal','normal','src_span'(15,13,15,19,596,6),'Transparent function').
'symbol'('Tags','Tags','src_span'(17,1,17,5,604,4),'Ident (Groundrep.)').
'symbol'('Data','Data','src_span'(18,1,18,5,619,4),'Ident (Groundrep.)').
'symbol'('left','left','src_span'(20,9,20,13,641,4),'Channel').
'symbol'('a','a','src_span'(20,15,20,16,647,1),'Channel').
'symbol'('b','b','src_span'(20,18,20,19,650,1),'Channel').
'symbol'('right','right','src_span'(20,21,20,26,653,5),'Channel').
'symbol'('c','c','src_span'(21,9,21,10,681,1),'Channel').
'symbol'('d','d','src_span'(21,12,21,13,684,1),'Channel').
'symbol'('mess','mess','src_span'(22,9,22,13,701,4),'Channel').
'symbol'('ack','ack','src_span'(23,9,23,12,728,3),'Channel').
'symbol'('SM','SM','src_span'(38,1,38,3,1098,2),'Ident (Groundrep.)').
'symbol'('i','i','src_span'(38,9,38,10,1106,1),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(38,29,38,30,1126,1),'Ident (Prolog Variable)').
'symbol'('RM','RM','src_span'(40,1,40,3,1152,2),'Ident (Groundrep.)').
'symbol'('t','t','src_span'(40,13,40,14,1164,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(40,17,40,18,1168,1),'Ident (Prolog Variable)').
'symbol'('SA','SA','src_span'(42,1,42,3,1190,2),'Ident (Groundrep.)').
'symbol'('i2','i','src_span'(42,9,42,10,1198,1),'Ident (Prolog Variable)').
'symbol'('RA','RA','src_span'(44,1,44,3,1236,2),'Ident (Groundrep.)').
'symbol'('x3','x','src_span'(44,12,44,13,1247,1),'Ident (Prolog Variable)').
'symbol'('T','T','src_span'(49,1,49,2,1423,1),'Funktion or Process').
'symbol'('i3','i','src_span'(49,3,49,4,1425,1),'Ident (Prolog Variable)').
'symbol'('x4','x','src_span'(49,17,49,18,1439,1),'Ident (Prolog Variable)').
'symbol'('R','R','src_span'(51,1,51,2,1468,1),'Funktion or Process').
'symbol'('i4','i','src_span'(51,3,51,4,1470,1),'Ident (Prolog Variable)').
'symbol'('x5','x','src_span'(51,14,51,15,1481,1),'Ident (Prolog Variable)').
'symbol'('FAULTYR','FAULTYR','src_span'(53,1,53,8,1514,7),'Funktion or Process').
'symbol'('i5','i','src_span'(53,9,53,10,1522,1),'Ident (Prolog Variable)').
'symbol'('x6','x','src_span'(53,20,53,21,1533,1),'Ident (Prolog Variable)').
'symbol'('INS','INS','src_span'(58,1,58,4,1704,3),'Ident (Groundrep.)').
'symbol'('i6','i','src_span'(58,11,58,12,1714,1),'Ident (Prolog Variable)').
'symbol'('ASM','ASM','src_span'(63,1,63,4,1869,3),'Ident (Groundrep.)').
'symbol'('ARA','ARA','src_span'(64,1,64,4,1889,3),'Ident (Groundrep.)').
'symbol'('LHS','LHS','src_span'(67,1,67,4,1971,3),'Ident (Groundrep.)').
'symbol'('AR1','AR1','src_span'(74,1,74,4,2169,3),'Ident (Groundrep.)').
'symbol'('AR2','AR2','src_span'(75,1,75,4,2197,3),'Ident (Groundrep.)').
'symbol'('AR3','AR3','src_span'(76,1,76,4,2225,3),'Ident (Groundrep.)').
'symbol'('OUTS','OUTS','src_span'(78,1,78,5,2254,4),'Ident (Groundrep.)').
'symbol'('i7','i','src_span'(78,12,78,13,2265,1),'Ident (Prolog Variable)').
'symbol'('FAULTYOUTS','FAULTYOUTS','src_span'(80,1,80,11,2282,10),'Ident (Groundrep.)').
'symbol'('i8','i','src_span'(80,18,80,19,2299,1),'Ident (Prolog Variable)').
'symbol'('RHS','RHS','src_span'(82,1,82,4,2350,3),'Ident (Groundrep.)').
'symbol'('FAULTYRHS','FAULTYRHS','src_span'(84,1,84,10,2397,9),'Ident (Groundrep.)').
'symbol'('SYSTEM','SYSTEM','src_span'(88,1,88,7,2529,6),'Ident (Groundrep.)').
'symbol'('FAULTYSYSTEM','FAULTYSYSTEM','src_span'(90,1,90,13,2572,12),'Ident (Groundrep.)').
'symbol'('COPY','COPY','src_span'(95,1,95,5,2713,4),'Funktion or Process').
'symbol'('in','in','src_span'(95,6,95,8,2718,2),'Ident (Prolog Variable)').
'symbol'('out','out','src_span'(95,9,95,12,2721,3),'Ident (Prolog Variable)').
'symbol'('x7','x','src_span'(95,19,95,20,2731,1),'Ident (Prolog Variable)').
'symbol'('SPEC','SPEC','src_span'(97,1,97,5,2759,4),'Ident (Groundrep.)').
'symbol'('i9','i','src_span'(97,12,97,13,2770,1),'Ident (Prolog Variable)').