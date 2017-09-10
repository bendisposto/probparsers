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
'bindval'('Data','setExp'('rangeEnum'(['int'(0),'int'(1)])),'src_span'(10,1,10,14,239,13)).
'bindval'('Chans','setExp'('rangeEnum'(['int'(0),'int'(1)])),'src_span'(11,1,11,15,253,14)).
'bindval'('LinkData','setExp'('rangeEnum'(['dotTuple'(['int'(0),'int'(0)]),'dotTuple'(['int'(0),'int'(1)]),'dotTuple'(['int'(1),'int'(0)]),'dotTuple'(['int'(1),'int'(1)])])),'src_span'(12,1,12,32,268,31)).
'bindval'('Acks','setExp'('rangeEnum'(['dotTuple'(['int'(0),'int'(0)]),'dotTuple'(['int'(1),'int'(0)])])),'src_span'(13,1,13,18,300,17)).
'channel'('upin0','type'('dotTupleType'(['val_of'('Data','src_span'(18,61,18,65,471,4))]))).
'channel'('upin1','type'('dotTupleType'(['val_of'('Data','src_span'(18,61,18,65,471,4))]))).
'channel'('downin0','type'('dotTupleType'(['val_of'('Data','src_span'(18,61,18,65,471,4))]))).
'channel'('downin1','type'('dotTupleType'(['val_of'('Data','src_span'(18,61,18,65,471,4))]))).
'channel'('downout0','type'('dotTupleType'(['val_of'('Data','src_span'(18,61,18,65,471,4))]))).
'channel'('downout1','type'('dotTupleType'(['val_of'('Data','src_span'(18,61,18,65,471,4))]))).
'channel'('uptodown','type'('dotTupleType'(['val_of'('LinkData','src_span'(22,19,22,27,538,8))]))).
'channel'('downtoup','type'('dotTupleType'(['val_of'('Acks','src_span'(23,19,23,23,565,4))]))).
'bindval'('MUX','repChoice'(['comprehensionGenerator'(_chan,'setExp'('rangeEnum'(['upin0','upin1'])))],'prefix'('src_span'(30,31,30,35,763,4),['in'(_x)],_chan,'ifte'('=='(_chan,'upin0'),'prefix'('src_span'(32,16,32,24,817,8),['out'('dotTuple'(['int'(0),_x]))],'uptodown','val_of'('MUX0','src_span'(32,34,32,38,835,4)),'src_span'(32,31,32,33,831,13)),'prefix'('src_span'(33,16,33,24,855,8),['out'('dotTuple'(['int'(1),_x]))],'uptodown','val_of'('MUX1','src_span'(33,34,33,38,873,4)),'src_span'(33,31,33,33,869,13)),'src_span'(31,9,31,27,783,18),'src_span'(31,28,32,15,801,53),'src_span'(32,39,33,15,839,60)),'src_span'(30,40,31,8,771,109)),'src_span'(30,9,30,30,741,21)),'src_span'(30,1,33,38,733,144)).
'bindval'('MUX0','repChoice'(['comprehensionGenerator'(_chan2,'setExp'('rangeEnum'(['upin1','dotTuple'(['downtoup','int'(0)])])))],'prefix'('src_span'(39,37,39,41,1125,4),['in'(_x2)],_chan2,'ifte'('=='(_chan2,'upin1'),'prefix'('src_span'(41,15,41,23,1177,8),['out'('dotTuple'(['int'(1),_x2]))],'uptodown','val_of'('MUX01','src_span'(41,33,41,38,1195,5)),'src_span'(41,30,41,32,1191,14)),'val_of'('MUX','src_span'(42,15,42,18,1215,3)),'src_span'(40,8,40,26,1144,18),'src_span'(40,27,41,14,1162,53),'src_span'(41,39,42,14,1200,41)),'src_span'(39,46,40,7,1133,88)),'src_span'(39,10,39,36,1098,26)),'src_span'(39,1,42,18,1089,129)).
'bindval'('MUX1','repChoice'(['comprehensionGenerator'(_chan3,'setExp'('rangeEnum'(['upin0','dotTuple'(['downtoup','int'(1)])])))],'prefix'('src_span'(46,37,46,41,1305,4),['in'(_x3)],_chan3,'ifte'('=='(_chan3,'upin0'),'prefix'('src_span'(48,15,48,23,1357,8),['out'('dotTuple'(['int'(0),_x3]))],'uptodown','val_of'('MUX01','src_span'(48,33,48,38,1375,5)),'src_span'(48,30,48,32,1371,14)),'val_of'('MUX','src_span'(49,15,49,18,1395,3)),'src_span'(47,8,47,26,1324,18),'src_span'(47,27,48,14,1342,53),'src_span'(48,39,49,14,1380,41)),'src_span'(46,46,47,7,1313,88)),'src_span'(46,10,46,36,1278,26)),'src_span'(46,1,49,18,1269,129)).
'bindval'('MUX01','prefix'('src_span'(55,9,55,17,1504,8),['inGuard'('dotpat'([_x4,_y]),'val_of'('Acks','src_span'(55,22,55,26,1517,4)))],'downtoup','ifte'('=='(_x4,'int'(0)),'val_of'('MUX1','src_span'(57,16,57,20,1560,4)),'val_of'('MUX0','src_span'(58,16,58,20,1580,4)),'src_span'(56,9,56,20,1533,11),'src_span'(56,21,57,15,1544,28),'src_span'(57,21,58,15,1564,24)),'src_span'(55,27,56,8,1521,72)),'src_span'(55,1,58,20,1496,88)).
'bindval'('DEMUX','prefix'('src_span'(64,9,64,17,1711,8),['in'('dotpat'([_vchan,_x5]))],'uptodown','ifte'('=='(_vchan,'int'(0)),'prefix'('src_span'(66,20,66,27,1778,7),['out'(_x5)],'downin0','agent_call'('src_span'(66,35,66,41,1793,6),'DEMUX0',[_x5]),'src_span'(66,32,66,34,1789,16)),'prefix'('src_span'(67,20,67,27,1823,7),['out'(_x5)],'downin1','agent_call'('src_span'(67,35,67,41,1838,6),'DEMUX1',[_x5]),'src_span'(67,32,67,34,1834,16)),'src_span'(65,13,65,28,1743,15),'src_span'(65,29,66,19,1758,56),'src_span'(66,45,67,19,1802,69)),'src_span'(64,26,65,12,1727,128)),'src_span'(64,1,67,44,1703,144)).
'agent'('DEMUX0'(_x6),'repChoice'(['comprehensionGenerator'(_chan4,'setExp'('rangeEnum'(['dotTuple'(['uptodown','int'(1)]),'dotTuple'(['downout0',_x6])])))],'prefix'('src_span'(73,48,73,52,2067,4),['in'(_y2)],_chan4,'ifte'('=='(_chan4,'dotTuple'(['uptodown','int'(1)])),'prefix'('src_span'(75,17,75,24,2128,7),['out'(_y2)],'downin1','agent_call'('src_span'(75,32,75,39,2143,7),'DEMUX01',[_x6,_y2]),'src_span'(75,29,75,31,2139,20)),'val_of'('DEMUX0a','src_span'(76,17,76,24,2173,7)),'src_span'(74,10,74,35,2086,25),'src_span'(74,36,75,16,2111,67),'src_span'(75,46,76,16,2156,52)),'src_span'(73,55,74,9,2073,109)),'src_span'(73,16,73,47,2035,31)),'src_span'(73,13,76,24,2032,148)).
'bindval'('DEMUX0a','repChoice'(['comprehensionGenerator'(_chan5,'setExp'('rangeEnum'(['dotTuple'(['uptodown','int'(1)]),'dotTuple'(['downtoup','int'(0),'int'(0)])])))],'prefix'('src_span'(78,49,78,53,2230,4),['in'(_y3)],_chan5,'ifte'('=='(_chan5,'dotTuple'(['uptodown','int'(1)])),'prefix'('src_span'(80,17,80,24,2291,7),['out'(_y3)],'downin1','agent_call'('src_span'(80,32,80,39,2306,7),'DEMUX0b',[_y3]),'src_span'(80,29,80,31,2302,17)),'val_of'('DEMUX','src_span'(81,17,81,22,2333,5)),'src_span'(79,10,79,35,2249,25),'src_span'(79,36,80,16,2274,64),'src_span'(80,43,81,16,2316,47)),'src_span'(78,56,79,9,2236,104)),'src_span'(78,15,78,48,2196,33)),'src_span'(78,1,81,22,2182,156)).
'agent'('DEMUX0b'(_y4),'repChoice'(['comprehensionGenerator'(_chan6,'setExp'('rangeEnum'(['dotTuple'(['downout1',_y4]),'dotTuple'(['downtoup','int'(0),'int'(0)])])))],'prefix'('src_span'(83,52,83,56,2391,4),[],_chan6,'ifte'('=='(_chan6,'dotTuple'(['downout1',_y4])),'val_of'('DEMUX01a','src_span'(85,17,85,25,2450,8)),'agent_call'('src_span'(86,17,86,23,2475,6),'DEMUX1',[_y4]),'src_span'(84,10,84,35,2408,25),'src_span'(84,36,85,16,2433,47),'src_span'(85,26,86,16,2458,34)),'src_span'(83,57,84,9,2395,93)),'src_span'(83,18,83,51,2357,33)),'src_span'(83,15,86,26,2354,130)).
'bindval'('DEMUX01a','repChoice'(['comprehensionGenerator'(_chan7,'setExp'('rangeEnum'(['dotTuple'(['downtoup','int'(0),'int'(0)]),'dotTuple'(['downtoup','int'(1),'int'(0)])])))],'prefix'('src_span'(89,51,89,55,2537,4),[],_chan7,'ifte'('=='(_chan7,'dotTuple'(['downtoup','int'(0),'int'(0)])),'val_of'('DEMUX1a','src_span'(91,18,91,25,2600,7)),'val_of'('DEMUX0a','src_span'(92,18,92,25,2625,7)),'src_span'(90,11,90,38,2555,27),'src_span'(90,39,91,17,2582,49),'src_span'(91,26,92,17,2607,32)),'src_span'(89,56,90,10,2541,95)),'src_span'(89,15,89,50,2501,35)),'src_span'(89,1,92,25,2487,145)).
'agent'('DEMUX1'(_x7),'repChoice'(['comprehensionGenerator'(_chan8,'setExp'('rangeEnum'(['dotTuple'(['uptodown','int'(0)]),'dotTuple'(['downout1',_x7])])))],'prefix'('src_span'(97,48,97,52,2714,4),['in'(_y5)],_chan8,'ifte'('=='(_chan8,'dotTuple'(['uptodown','int'(0)])),'prefix'('src_span'(99,17,99,24,2775,7),['out'(_y5)],'downin0','agent_call'('src_span'(99,32,99,39,2790,7),'DEMUX01',[_y5,_x7]),'src_span'(99,29,99,31,2786,20)),'val_of'('DEMUX1a','src_span'(100,17,100,24,2820,7)),'src_span'(98,10,98,35,2733,25),'src_span'(98,36,99,16,2758,67),'src_span'(99,46,100,16,2803,52)),'src_span'(97,55,98,9,2720,109)),'src_span'(97,16,97,47,2682,31)),'src_span'(97,13,100,24,2679,148)).
'bindval'('DEMUX1a','repChoice'(['comprehensionGenerator'(_chan9,'setExp'('rangeEnum'(['dotTuple'(['uptodown','int'(0)]),'dotTuple'(['downtoup','int'(1),'int'(0)])])))],'prefix'('src_span'(103,49,103,53,2878,4),['in'(_y6)],_chan9,'ifte'('=='(_chan9,'dotTuple'(['uptodown','int'(0)])),'prefix'('src_span'(105,17,105,24,2939,7),['out'(_y6)],'downin0','agent_call'('src_span'(105,32,105,39,2954,7),'DEMUX1b',[_y6]),'src_span'(105,29,105,31,2950,17)),'val_of'('DEMUX','src_span'(106,17,106,22,2981,5)),'src_span'(104,10,104,35,2897,25),'src_span'(104,36,105,16,2922,64),'src_span'(105,43,106,16,2964,47)),'src_span'(103,56,104,9,2884,104)),'src_span'(103,15,103,48,2844,33)),'src_span'(103,1,106,22,2830,156)).
'agent'('DEMUX1b'(_y7),'repChoice'(['comprehensionGenerator'(_chan63,'setExp'('rangeEnum'(['dotTuple'(['downout0',_y7]),'dotTuple'(['downtoup','int'(1),'int'(0)])])))],'prefix'('src_span'(109,52,109,56,3040,4),[],_chan63,'ifte'('=='(_chan63,'dotTuple'(['downout0',_y7])),'val_of'('DEMUX01a','src_span'(111,17,111,25,3101,8)),'agent_call'('src_span'(112,17,112,23,3126,6),'DEMUX0',[_y7]),'src_span'(110,12,110,37,3059,25),'src_span'(110,38,111,16,3084,47),'src_span'(111,26,112,16,3109,34)),'src_span'(109,57,110,11,3044,95)),'src_span'(109,18,109,51,3006,33)),'src_span'(109,15,112,26,3003,132)).
'agent'('DEMUX01'(_x8,_y8),'repChoice'(['comprehensionGenerator'(_chan66,'setExp'('rangeEnum'(['dotTuple'(['downout0',_x8]),'dotTuple'(['downout1',_y8])])))],'prefix'('src_span'(116,52,116,56,3232,4),[],_chan66,'ifte'('=='(_chan66,'dotTuple'(['downout0',_x8])),'prefix'('src_span'(118,18,118,26,3294,8),['out'('dotTuple'(['int'(0),'int'(0)]))],'downtoup','agent_call'('src_span'(118,36,118,42,3312,6),'DEMUX1',[_y8]),'src_span'(118,33,118,35,3308,18)),'prefix'('src_span'(119,18,119,26,3339,8),['out'('dotTuple'(['int'(1),'int'(0)]))],'downtoup','agent_call'('src_span'(119,36,119,42,3357,6),'DEMUX0',[_x8]),'src_span'(119,33,119,35,3353,18)),'src_span'(117,11,117,36,3251,25),'src_span'(117,37,118,17,3276,67),'src_span'(118,46,119,17,3321,72)),'src_span'(116,57,117,10,3236,134)),'src_span'(116,20,116,51,3200,31)),'src_span'(116,17,119,45,3197,169)).
'bindval'('Seqsys','\x5c\'('sharing'('closure'(['uptodown','downtoup']),'val_of'('MUX','src_span'(125,10,125,13,3509,3)),'val_of'('DEMUX','src_span'(125,45,125,50,3544,5)),'src_span'(125,14,125,44,3513,30)),'closure'(['uptodown','downtoup','downin0','downin1']),'src_span_operator'('no_loc_info_available','src_span'(125,51,125,52,3550,1))),'src_span'(125,1,126,52,3500,104)).
'assertModelCheckExt'('False','val_of'('Seqsys','src_span'(132,8,132,14,3688,6)),'Deterministic','FD').
'assertModelCheckExt'('False','val_of'('Seqsys','src_span'(133,8,133,14,3725,6)),'DeadlockFree','F').
'assertModelCheck'('False','val_of'('Seqsys','src_span'(134,8,134,14,3761,6)),'LivelockFree').
'bindval'('Copy0','prefix'('src_span'(138,9,138,14,3827,5),['inGuard'(_x9,'setExp'('rangeEnum'(['int'(0),'int'(1)])))],'upin0','prefix'('src_span'(138,27,138,35,3845,8),['out'(_x9)],'downout0','val_of'('Copy0','src_span'(138,43,138,48,3861,5)),'src_span'(138,40,138,42,3857,12)),'src_span'(138,24,138,26,3841,34)),'src_span'(138,1,138,48,3819,47)).
'bindval'('Copy1','prefix'('src_span'(140,9,140,14,3876,5),['inGuard'(_x68,'setExp'('rangeEnum'(['int'(0),'int'(1)])))],'upin1','prefix'('src_span'(140,27,140,35,3894,8),['out'(_x68)],'downout1','val_of'('Copy1','src_span'(140,43,140,48,3910,5)),'src_span'(140,40,140,42,3906,12)),'src_span'(140,24,140,26,3890,34)),'src_span'(140,1,140,48,3868,47)).
'bindval'('DCopy','|||'('val_of'('Copy0','src_span'(143,9,143,14,3926,5)),'val_of'('Copy1','src_span'(143,19,143,24,3936,5)),'src_span_operator'('no_loc_info_available','src_span'(143,15,143,18,3932,3))),'src_span'(143,1,143,24,3918,23)).
'assertRef'('False','val_of'('DCopy','src_span'(145,8,145,13,3950,5)),'FailureDivergence','val_of'('Seqsys','src_span'(145,20,145,26,3962,6)),'src_span'(145,1,145,26,3943,25)).
'assertRef'('False','val_of'('Seqsys','src_span'(146,8,146,14,3976,6)),'FailureDivergence','val_of'('DCopy','src_span'(146,20,146,25,3988,5)),'src_span'(146,1,146,25,3969,24)).
'comment'('lineComment'('-- Sequential version of the Virtual Channel MUX-DEMUX'),'src_position'(1,1,0,54)).
'comment'('lineComment'('-- G. S. Stiles'),'src_position'(2,1,55,15)).
'comment'('lineComment'('-- July 20, 1998'),'src_position'(3,1,71,16)).
'comment'('lineComment'('-- This script uses sequential processes on the upstream and downstream'),'src_position'(5,1,89,71)).
'comment'('lineComment'('-- nodes.'),'src_position'(6,1,161,9)).
'comment'('lineComment'('-- Declare the tag identifying the virtual channels and the data:'),'src_position'(8,1,172,65)).
'comment'('lineComment'('-- The input and output channels carry data tagged with the virtual channel'),'src_position'(15,1,319,75)).
'comment'('lineComment'('-- identifier:'),'src_position'(16,1,395,14)).
'comment'('lineComment'('-- The channels connecting the two nodes:'),'src_position'(20,1,477,41)).
'comment'('lineComment'('-- The upstream process; it monitors all possible inputs and then branches'),'src_position'(25,1,571,74)).
'comment'('lineComment'('-- according to the input:'),'src_position'(26,1,646,26)).
'comment'('lineComment'('-- MUX is used when we expect only data inputs (no acks):'),'src_position'(28,1,674,57)).
'comment'('lineComment'('-- MUX is used when we are awaiting acknowledgement of virtual channel 0'),'src_position'(35,1,879,72)).
'comment'('lineComment'('-- data and can accept virtual channel 1 input; if we get an acknowledgement'),'src_position'(36,1,952,76)).
'comment'('lineComment'('-- of the virtual channel 0 data we simply go back to MUX:'),'src_position'(37,1,1029,58)).
'comment'('lineComment'('-- MUX1 is similar - but for virtual channel 1:'),'src_position'(44,1,1220,47)).
'comment'('lineComment'('-- MUX01 applies when we are awaiting acknowledgements of data from both'),'src_position'(52,1,1401,72)).
'comment'('lineComment'('-- virtual channels:'),'src_position'(53,1,1474,20)).
'comment'('lineComment'('-- Now the downstream side; the processes are similar to the upstream,'),'src_position'(61,1,1587,70)).
'comment'('lineComment'('-- and differ based on the actions pending:'),'src_position'(62,1,1658,43)).
'comment'('lineComment'('-- DEMUX0 waits for virtual channel 0 data to be picked up, then sends the'),'src_position'(69,1,1849,74)).
'comment'('lineComment'('-- appropriate acknowledgement upstream - while waiting for virtual'),'src_position'(70,1,1924,67)).
'comment'('lineComment'('-- channel 1 data as well:'),'src_position'(71,1,1992,26)).
'comment'('lineComment'('-- DEMUX1 does the other half:'),'src_position'(95,1,2635,30)).
'comment'('lineComment'('-- DEMUX01 waits for two acknowledgements:'),'src_position'(114,1,3137,42)).
'comment'('lineComment'('-- The complete system runs the two processes in parallel, synchronizing'),'src_position'(122,1,3380,72)).
'comment'('lineComment'('-- them only on the connecting link channels:'),'src_position'(123,1,3453,45)).
'comment'('lineComment'('-- SeqsysV = MUX [| {| uptodown, downtoup |} |] DEMUX '),'src_position'(128,1,3606,54)).
'comment'('lineComment'('-- Safety Checks:'),'src_position'(130,1,3662,17)).
'comment'('lineComment'('-- Check the specifications:'),'src_position'(136,1,3789,28)).
'symbol'('Data','Data','src_span'(10,1,10,5,239,4),'Ident (Groundrep.)').
'symbol'('Chans','Chans','src_span'(11,1,11,6,253,5),'Ident (Groundrep.)').
'symbol'('LinkData','LinkData','src_span'(12,1,12,9,268,8),'Ident (Groundrep.)').
'symbol'('Acks','Acks','src_span'(13,1,13,5,300,4),'Ident (Groundrep.)').
'symbol'('upin0','upin0','src_span'(18,9,18,14,419,5),'Channel').
'symbol'('upin1','upin1','src_span'(18,16,18,21,426,5),'Channel').
'symbol'('downin0','downin0','src_span'(18,23,18,30,433,7),'Channel').
'symbol'('downin1','downin1','src_span'(18,32,18,39,442,7),'Channel').
'symbol'('downout0','downout0','src_span'(18,41,18,49,451,8),'Channel').
'symbol'('downout1','downout1','src_span'(18,51,18,59,461,8),'Channel').
'symbol'('uptodown','uptodown','src_span'(22,9,22,17,528,8),'Channel').
'symbol'('downtoup','downtoup','src_span'(23,9,23,17,555,8),'Channel').
'symbol'('MUX','MUX','src_span'(30,1,30,4,733,3),'Ident (Groundrep.)').
'symbol'('chan','chan','src_span'(30,9,30,13,741,4),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(30,38,30,39,770,1),'Ident (Prolog Variable)').
'symbol'('MUX0','MUX0','src_span'(39,1,39,5,1089,4),'Ident (Groundrep.)').
'symbol'('chan2','chan','src_span'(39,10,39,14,1098,4),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(39,44,39,45,1132,1),'Ident (Prolog Variable)').
'symbol'('MUX1','MUX1','src_span'(46,1,46,5,1269,4),'Ident (Groundrep.)').
'symbol'('chan3','chan','src_span'(46,10,46,14,1278,4),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(46,44,46,45,1312,1),'Ident (Prolog Variable)').
'symbol'('MUX01','MUX01','src_span'(55,1,55,6,1496,5),'Ident (Groundrep.)').
'symbol'('x4','x','src_span'(55,18,55,19,1513,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(55,20,55,21,1515,1),'Ident (Prolog Variable)').
'symbol'('DEMUX','DEMUX','src_span'(64,1,64,6,1703,5),'Ident (Groundrep.)').
'symbol'('vchan','vchan','src_span'(64,18,64,23,1720,5),'Ident (Prolog Variable)').
'symbol'('x5','x','src_span'(64,24,64,25,1726,1),'Ident (Prolog Variable)').
'symbol'('DEMUX0','DEMUX0','src_span'(73,1,73,7,2020,6),'Funktion or Process').
'symbol'('x6','x','src_span'(73,8,73,9,2027,1),'Ident (Prolog Variable)').
'symbol'('chan4','chan','src_span'(73,16,73,20,2035,4),'Ident (Prolog Variable)').
'symbol'('y2','y','src_span'(73,53,73,54,2072,1),'Ident (Prolog Variable)').
'symbol'('DEMUX0a','DEMUX0a','src_span'(78,1,78,8,2182,7),'Ident (Groundrep.)').
'symbol'('chan5','chan','src_span'(78,15,78,19,2196,4),'Ident (Prolog Variable)').
'symbol'('y3','y','src_span'(78,54,78,55,2235,1),'Ident (Prolog Variable)').
'symbol'('DEMUX0b','DEMUX0b','src_span'(83,1,83,8,2340,7),'Funktion or Process').
'symbol'('y4','y','src_span'(83,9,83,10,2348,1),'Ident (Prolog Variable)').
'symbol'('chan6','chan','src_span'(83,18,83,22,2357,4),'Ident (Prolog Variable)').
'symbol'('DEMUX01a','DEMUX01a','src_span'(89,1,89,9,2487,8),'Ident (Groundrep.)').
'symbol'('chan7','chan','src_span'(89,15,89,19,2501,4),'Ident (Prolog Variable)').
'symbol'('DEMUX1','DEMUX1','src_span'(97,1,97,7,2667,6),'Funktion or Process').
'symbol'('x7','x','src_span'(97,8,97,9,2674,1),'Ident (Prolog Variable)').
'symbol'('chan8','chan','src_span'(97,16,97,20,2682,4),'Ident (Prolog Variable)').
'symbol'('y5','y','src_span'(97,53,97,54,2719,1),'Ident (Prolog Variable)').
'symbol'('DEMUX1a','DEMUX1a','src_span'(103,1,103,8,2830,7),'Ident (Groundrep.)').
'symbol'('chan9','chan','src_span'(103,15,103,19,2844,4),'Ident (Prolog Variable)').
'symbol'('y6','y','src_span'(103,54,103,55,2883,1),'Ident (Prolog Variable)').
'symbol'('DEMUX1b','DEMUX1b','src_span'(109,1,109,8,2989,7),'Funktion or Process').
'symbol'('y7','y','src_span'(109,9,109,10,2997,1),'Ident (Prolog Variable)').
'symbol'('chan63','chan','src_span'(109,18,109,22,3006,4),'Ident (Prolog Variable)').
'symbol'('DEMUX01','DEMUX01','src_span'(116,1,116,8,3181,7),'Funktion or Process').
'symbol'('x8','x','src_span'(116,9,116,10,3189,1),'Ident (Prolog Variable)').
'symbol'('y8','y','src_span'(116,12,116,13,3192,1),'Ident (Prolog Variable)').
'symbol'('chan66','chan','src_span'(116,20,116,24,3200,4),'Ident (Prolog Variable)').
'symbol'('Seqsys','Seqsys','src_span'(125,1,125,7,3500,6),'Ident (Groundrep.)').
'symbol'('Copy0','Copy0','src_span'(138,1,138,6,3819,5),'Ident (Groundrep.)').
'symbol'('x9','x','src_span'(138,15,138,16,3833,1),'Ident (Prolog Variable)').
'symbol'('Copy1','Copy1','src_span'(140,1,140,6,3868,5),'Ident (Groundrep.)').
'symbol'('x68','x','src_span'(140,15,140,16,3882,1),'Ident (Prolog Variable)').
'symbol'('DCopy','DCopy','src_span'(143,1,143,6,3918,5),'Ident (Groundrep.)').