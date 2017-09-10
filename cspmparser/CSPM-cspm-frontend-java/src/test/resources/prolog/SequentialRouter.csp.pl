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
'nameType'('LinkDataT','type'('dotTupleType'(['setExp'('rangeEnum'(['int'(0),'int'(1)])),'setExp'('rangeEnum'(['int'(0),'int'(1)]))]))).
'bindval'('Acks','setExp'('rangeEnum'(['dotTuple'(['int'(0),'int'(0)]),'dotTuple'(['int'(1),'int'(0)])])),'src_span'(14,1,14,18,354,17)).
'nameType'('AcksT','type'('dotTupleType'(['setExp'('rangeEnum'(['int'(0),'int'(1)])),'setExp'('rangeEnum'(['int'(0)]))]))).
'channel'('upin0','type'('dotTupleType'(['val_of'('Data','src_span'(20,61,20,65,573,4))]))).
'channel'('upin1','type'('dotTupleType'(['val_of'('Data','src_span'(20,61,20,65,573,4))]))).
'channel'('downin0','type'('dotTupleType'(['val_of'('Data','src_span'(20,61,20,65,573,4))]))).
'channel'('downin1','type'('dotTupleType'(['val_of'('Data','src_span'(20,61,20,65,573,4))]))).
'channel'('downout0','type'('dotTupleType'(['val_of'('Data','src_span'(20,61,20,65,573,4))]))).
'channel'('downout1','type'('dotTupleType'(['val_of'('Data','src_span'(20,61,20,65,573,4))]))).
'channel'('uptodown','type'('dotTupleType'(['LinkDataT']))).
'channel'('downtoup','type'('dotTupleType'(['AcksT']))).
'bindval'('MUX','repChoice'(['comprehensionGenerator'(_chan,'setExp'('rangeEnum'(['upin0','upin1'])))],'prefix'('src_span'(33,31,33,35,868,4),['in'(_x)],_chan,'ifte'('=='(_chan,'upin0'),'prefix'('src_span'(35,16,35,24,922,8),['out'('dotTuple'(['int'(0),_x]))],'uptodown','val_of'('MUX0','src_span'(35,34,35,38,940,4)),'src_span'(35,31,35,33,936,13)),'prefix'('src_span'(36,16,36,24,960,8),['out'('dotTuple'(['int'(1),_x]))],'uptodown','val_of'('MUX1','src_span'(36,34,36,38,978,4)),'src_span'(36,31,36,33,974,13)),'src_span'(34,9,34,27,888,18),'src_span'(34,28,35,15,906,53),'src_span'(35,39,36,15,944,60)),'src_span'(33,40,34,8,876,109)),'src_span'(33,9,33,30,846,21)),'src_span'(33,1,36,38,838,144)).
'bindval'('MUX0','repChoice'(['comprehensionGenerator'(_chan2,'setExp'('rangeEnum'(['upin1','dotTuple'(['downtoup','int'(0)])])))],'prefix'('src_span'(42,37,42,41,1230,4),['in'(_x2)],_chan2,'ifte'('=='(_chan2,'upin1'),'prefix'('src_span'(44,15,44,23,1282,8),['out'('dotTuple'(['int'(1),_x2]))],'uptodown','val_of'('MUX01','src_span'(44,35,44,40,1302,5)),'src_span'(44,32,44,34,1298,16)),'val_of'('MUX','src_span'(45,15,45,18,1322,3)),'src_span'(43,8,43,26,1249,18),'src_span'(43,27,44,14,1267,55),'src_span'(44,41,45,14,1307,43)),'src_span'(42,46,43,7,1238,90)),'src_span'(42,10,42,36,1203,26)),'src_span'(42,1,45,18,1194,131)).
'bindval'('MUX1','repChoice'(['comprehensionGenerator'(_chan3,'setExp'('rangeEnum'(['upin0','dotTuple'(['downtoup','int'(1)])])))],'prefix'('src_span'(49,37,49,41,1412,4),['in'(_x3)],_chan3,'ifte'('=='(_chan3,'upin0'),'prefix'('src_span'(53,15,53,23,1623,8),['out'('dotTuple'(['int'(0),_x3]))],'uptodown','val_of'('MUX01','src_span'(53,35,53,40,1643,5)),'src_span'(53,32,53,34,1639,16)),'val_of'('MUX','src_span'(54,15,54,18,1663,3)),'src_span'(52,8,52,26,1590,18),'src_span'(52,27,53,14,1608,55),'src_span'(53,41,54,14,1648,43)),'src_span'(49,46,52,7,1420,249)),'src_span'(49,10,49,36,1385,26)),'src_span'(49,1,54,18,1376,290)).
'bindval'('MUX01','prefix'('src_span'(60,9,60,17,1772,8),['inGuard'('dotpat'([_x4,_y]),'val_of'('Acks','src_span'(60,24,60,28,1787,4)))],'downtoup','ifte'('=='(_x4,'int'(0)),'val_of'('MUX1','src_span'(62,16,62,20,1880,4)),'val_of'('MUX0','src_span'(63,16,63,20,1900,4)),'src_span'(61,9,61,20,1853,11),'src_span'(61,21,62,15,1864,28),'src_span'(62,21,63,15,1884,24)),'src_span'(60,29,61,8,1791,124)),'src_span'(60,1,63,20,1764,140)).
'bindval'('DEMUX','prefix'('src_span'(69,9,69,17,2031,8),['in'('dotpat'([_vchan,_x5]))],'uptodown','ifte'('=='(_vchan,'int'(0)),'prefix'('src_span'(71,20,71,27,2098,7),['out'(_x5)],'downin0','agent_call'('src_span'(71,35,71,41,2113,6),'DEMUX0',[_x5]),'src_span'(71,32,71,34,2109,16)),'prefix'('src_span'(72,20,72,27,2143,7),['out'(_x5)],'downin1','agent_call'('src_span'(72,35,72,41,2158,6),'DEMUX1',[_x5]),'src_span'(72,32,72,34,2154,16)),'src_span'(70,13,70,28,2063,15),'src_span'(70,29,71,19,2078,56),'src_span'(71,45,72,19,2122,69)),'src_span'(69,26,70,12,2047,128)),'src_span'(69,1,72,44,2023,144)).
'agent'('DEMUX0'(_x6),'repChoice'(['comprehensionGenerator'(_chan4,'setExp'('rangeEnum'(['dotTuple'(['uptodown','int'(1)]),'dotTuple'(['downout0',_x6])])))],'prefix'('src_span'(78,48,78,52,2387,4),['in'(_y2)],_chan4,'ifte'('=='(_chan4,'dotTuple'(['uptodown','int'(1)])),'prefix'('src_span'(80,17,80,24,2448,7),['out'(_y2)],'downin1','agent_call'('src_span'(80,32,80,39,2463,7),'DEMUX01',[_x6,_y2]),'src_span'(80,29,80,31,2459,20)),'val_of'('DEMUX0a','src_span'(81,17,81,24,2493,7)),'src_span'(79,10,79,35,2406,25),'src_span'(79,36,80,16,2431,67),'src_span'(80,46,81,16,2476,52)),'src_span'(78,55,79,9,2393,109)),'src_span'(78,16,78,47,2355,31)),'src_span'(78,13,81,24,2352,148)).
'bindval'('DEMUX0a','repChoice'(['comprehensionGenerator'(_chan5,'setExp'('rangeEnum'(['dotTuple'(['uptodown','int'(1)]),'dotTuple'(['downtoup','int'(0),'int'(0)])])))],'prefix'('src_span'(83,49,83,53,2550,4),['in'(_y3)],_chan5,'ifte'('=='(_chan5,'dotTuple'(['uptodown','int'(1)])),'prefix'('src_span'(85,17,85,24,2611,7),['out'(_y3)],'downin1','agent_call'('src_span'(85,32,85,39,2626,7),'DEMUX0b',[_y3]),'src_span'(85,29,85,31,2622,17)),'val_of'('DEMUX','src_span'(86,17,86,22,2653,5)),'src_span'(84,10,84,35,2569,25),'src_span'(84,36,85,16,2594,64),'src_span'(85,43,86,16,2636,47)),'src_span'(83,56,84,9,2556,104)),'src_span'(83,15,83,48,2516,33)),'src_span'(83,1,86,22,2502,156)).
'agent'('DEMUX0b'(_y4),'repChoice'(['comprehensionGenerator'(_chan6,'setExp'('rangeEnum'(['dotTuple'(['downout1',_y4]),'dotTuple'(['downtoup','int'(0),'int'(0)])])))],'prefix'('src_span'(88,52,88,56,2711,4),[],_chan6,'ifte'('=='(_chan6,'dotTuple'(['downout1',_y4])),'val_of'('DEMUX01a','src_span'(90,17,90,25,2770,8)),'agent_call'('src_span'(91,17,91,23,2795,6),'DEMUX1',[_y4]),'src_span'(89,10,89,35,2728,25),'src_span'(89,36,90,16,2753,47),'src_span'(90,26,91,16,2778,34)),'src_span'(88,57,89,9,2715,93)),'src_span'(88,18,88,51,2677,33)),'src_span'(88,15,91,26,2674,130)).
'bindval'('DEMUX01a','repChoice'(['comprehensionGenerator'(_chan7,'setExp'('rangeEnum'(['dotTuple'(['downtoup','int'(0),'int'(0)]),'dotTuple'(['downtoup','int'(1),'int'(0)])])))],'prefix'('src_span'(94,51,94,55,2857,4),[],_chan7,'ifte'('=='(_chan7,'dotTuple'(['downtoup','int'(0),'int'(0)])),'val_of'('DEMUX1a','src_span'(96,18,96,25,2920,7)),'val_of'('DEMUX0a','src_span'(97,18,97,25,2945,7)),'src_span'(95,11,95,38,2875,27),'src_span'(95,39,96,17,2902,49),'src_span'(96,26,97,17,2927,32)),'src_span'(94,56,95,10,2861,95)),'src_span'(94,15,94,50,2821,35)),'src_span'(94,1,97,25,2807,145)).
'agent'('DEMUX1'(_x7),'repChoice'(['comprehensionGenerator'(_chan8,'setExp'('rangeEnum'(['dotTuple'(['uptodown','int'(0)]),'dotTuple'(['downout1',_x7])])))],'prefix'('src_span'(102,48,102,52,3034,4),['in'(_y5)],_chan8,'ifte'('=='(_chan8,'dotTuple'(['uptodown','int'(0)])),'prefix'('src_span'(104,17,104,24,3095,7),['out'(_y5)],'downin0','agent_call'('src_span'(104,32,104,39,3110,7),'DEMUX01',[_y5,_x7]),'src_span'(104,29,104,31,3106,20)),'val_of'('DEMUX1a','src_span'(105,17,105,24,3140,7)),'src_span'(103,10,103,35,3053,25),'src_span'(103,36,104,16,3078,67),'src_span'(104,46,105,16,3123,52)),'src_span'(102,55,103,9,3040,109)),'src_span'(102,16,102,47,3002,31)),'src_span'(102,13,105,24,2999,148)).
'bindval'('DEMUX1a','repChoice'(['comprehensionGenerator'(_chan9,'setExp'('rangeEnum'(['dotTuple'(['uptodown','int'(0)]),'dotTuple'(['downtoup','int'(1),'int'(0)])])))],'prefix'('src_span'(108,49,108,53,3198,4),['in'(_y6)],_chan9,'ifte'('=='(_chan9,'dotTuple'(['uptodown','int'(0)])),'prefix'('src_span'(110,17,110,24,3259,7),['out'(_y6)],'downin0','agent_call'('src_span'(110,32,110,39,3274,7),'DEMUX1b',[_y6]),'src_span'(110,29,110,31,3270,17)),'val_of'('DEMUX','src_span'(111,17,111,22,3301,5)),'src_span'(109,10,109,35,3217,25),'src_span'(109,36,110,16,3242,64),'src_span'(110,43,111,16,3284,47)),'src_span'(108,56,109,9,3204,104)),'src_span'(108,15,108,48,3164,33)),'src_span'(108,1,111,22,3150,156)).
'agent'('DEMUX1b'(_y7),'repChoice'(['comprehensionGenerator'(_chan66,'setExp'('rangeEnum'(['dotTuple'(['downout0',_y7]),'dotTuple'(['downtoup','int'(1),'int'(0)])])))],'prefix'('src_span'(114,52,114,56,3360,4),[],_chan66,'ifte'('=='(_chan66,'dotTuple'(['downout0',_y7])),'val_of'('DEMUX01a','src_span'(116,17,116,25,3421,8)),'agent_call'('src_span'(117,17,117,23,3446,6),'DEMUX0',[_y7]),'src_span'(115,12,115,37,3379,25),'src_span'(115,38,116,16,3404,47),'src_span'(116,26,117,16,3429,34)),'src_span'(114,57,115,11,3364,95)),'src_span'(114,18,114,51,3326,33)),'src_span'(114,15,117,26,3323,132)).
'agent'('DEMUX01'(_x8,_y8),'repChoice'(['comprehensionGenerator'(_chan69,'setExp'('rangeEnum'(['dotTuple'(['downout0',_x8]),'dotTuple'(['downout1',_y8])])))],'prefix'('src_span'(121,52,121,56,3552,4),[],_chan69,'ifte'('=='(_chan69,'dotTuple'(['downout0',_x8])),'prefix'('src_span'(123,18,123,26,3614,8),['out'('dotTuple'(['int'(0),'int'(0)]))],'downtoup','agent_call'('src_span'(123,36,123,42,3632,6),'DEMUX1',[_y8]),'src_span'(123,33,123,35,3628,18)),'prefix'('src_span'(124,18,124,26,3659,8),['out'('dotTuple'(['int'(1),'int'(0)]))],'downtoup','agent_call'('src_span'(124,36,124,42,3677,6),'DEMUX0',[_x8]),'src_span'(124,33,124,35,3673,18)),'src_span'(122,11,122,36,3571,25),'src_span'(122,37,123,17,3596,67),'src_span'(123,46,124,17,3641,72)),'src_span'(121,57,122,10,3556,134)),'src_span'(121,20,121,51,3520,31)),'src_span'(121,17,124,45,3517,169)).
'bindval'('Seqsys','\x5c\'('sharing'('closure'(['uptodown','downtoup']),'val_of'('MUX','src_span'(130,10,130,13,3829,3)),'val_of'('DEMUX','src_span'(130,45,130,50,3864,5)),'src_span'(130,14,130,44,3833,30)),'closure'(['uptodown','downtoup','downin0','downin1']),'src_span_operator'('no_loc_info_available','src_span'(130,51,130,52,3870,1))),'src_span'(130,1,131,52,3820,104)).
'bindval'('Copy0','prefix'('src_span'(143,9,143,14,4156,5),['inGuard'(_x9,'setExp'('rangeEnum'(['int'(0),'int'(1)])))],'upin0','prefix'('src_span'(143,27,143,35,4174,8),['out'(_x9)],'downout0','val_of'('Copy0','src_span'(143,43,143,48,4190,5)),'src_span'(143,40,143,42,4186,12)),'src_span'(143,24,143,26,4170,34)),'src_span'(143,1,143,48,4148,47)).
'bindval'('Copy1','prefix'('src_span'(145,9,145,14,4205,5),['inGuard'(_x71,'setExp'('rangeEnum'(['int'(0),'int'(1)])))],'upin1','prefix'('src_span'(145,27,145,35,4223,8),['out'(_x71)],'downout1','val_of'('Copy1','src_span'(145,43,145,48,4239,5)),'src_span'(145,40,145,42,4235,12)),'src_span'(145,24,145,26,4219,34)),'src_span'(145,1,145,48,4197,47)).
'bindval'('DCopy','|||'('val_of'('Copy0','src_span'(148,9,148,14,4255,5)),'val_of'('Copy1','src_span'(148,19,148,24,4265,5)),'src_span_operator'('no_loc_info_available','src_span'(148,15,148,18,4261,3))),'src_span'(148,1,148,24,4247,23)).
'bindval'('MAIN','val_of'('Seqsys','src_span'(153,8,153,14,4337,6)),'src_span'(153,1,153,14,4330,13)).
'comment'('lineComment'('-- Sequential version of the Virtual Channel MUX-DEMUX'),'src_position'(1,1,0,54)).
'comment'('lineComment'('-- G. S. Stiles'),'src_position'(2,1,55,15)).
'comment'('lineComment'('-- July 20, 1998'),'src_position'(3,1,71,16)).
'comment'('lineComment'('-- This script uses sequential processes on the upstream and downstream'),'src_position'(5,1,89,71)).
'comment'('lineComment'('-- nodes.'),'src_position'(6,1,161,9)).
'comment'('lineComment'('-- Declare the tag identifying the virtual channels and the data:'),'src_position'(8,1,172,65)).
'comment'('lineComment'('-- added by leuschel'),'src_position'(13,34,333,20)).
'comment'('lineComment'('-- added by leuschel'),'src_position'(15,28,399,20)).
'comment'('lineComment'('-- The input and output channels carry data tagged with the virtual channel'),'src_position'(17,1,421,75)).
'comment'('lineComment'('-- identifier:'),'src_position'(18,1,497,14)).
'comment'('lineComment'('-- The channels connecting the two nodes:'),'src_position'(22,1,579,41)).
'comment'('lineComment'('-- The upstream process; it monitors all possible inputs and then branches'),'src_position'(28,1,676,74)).
'comment'('lineComment'('-- according to the input:'),'src_position'(29,1,751,26)).
'comment'('lineComment'('-- MUX is used when we expect only data inputs (no acks):'),'src_position'(31,1,779,57)).
'comment'('lineComment'('-- MUX is used when we are awaiting acknowledgement of virtual channel 0'),'src_position'(38,1,984,72)).
'comment'('lineComment'('-- data and can accept virtual channel 1 input; if we get an acknowledgement'),'src_position'(39,1,1057,76)).
'comment'('lineComment'('-- of the virtual channel 0 data we simply go back to MUX:'),'src_position'(40,1,1134,58)).
'comment'('lineComment'('-- MUX1 is similar - but for virtual channel 1:'),'src_position'(47,1,1327,47)).
'comment'('lineComment'('-- originally not work with ProB'),'src_position'(49,50,1425,32)).
'comment'('lineComment'('-- The Problem is that Acks is seen as a single type;'),'src_position'(50,15,1472,53)).
'comment'('lineComment'('-- have added nametype directive for AcksT'),'src_position'(51,15,1540,42)).
'comment'('lineComment'('-- MUX01 applies when we are awaiting acknowledgements of data from both'),'src_position'(57,1,1669,72)).
'comment'('lineComment'('-- virtual channels:'),'src_position'(58,1,1742,20)).
'comment'('lineComment'('-- leuschel: had to put () around x.y for parser'),'src_position'(60,33,1796,48)).
'comment'('lineComment'('-- Now the downstream side; the processes are similar to the upstream,'),'src_position'(66,1,1907,70)).
'comment'('lineComment'('-- and differ based on the actions pending:'),'src_position'(67,1,1978,43)).
'comment'('lineComment'('-- DEMUX0 waits for virtual channel 0 data to be picked up, then sends the'),'src_position'(74,1,2169,74)).
'comment'('lineComment'('-- appropriate acknowledgement upstream - while waiting for virtual'),'src_position'(75,1,2244,67)).
'comment'('lineComment'('-- channel 1 data as well:'),'src_position'(76,1,2312,26)).
'comment'('lineComment'('-- DEMUX1 does the other half:'),'src_position'(100,1,2955,30)).
'comment'('lineComment'('-- DEMUX01 waits for two acknowledgements:'),'src_position'(119,1,3457,42)).
'comment'('lineComment'('-- The complete system runs the two processes in parallel, synchronizing'),'src_position'(127,1,3700,72)).
'comment'('lineComment'('-- them only on the connecting link channels:'),'src_position'(128,1,3773,45)).
'comment'('lineComment'('-- SeqsysV = MUX [| {| uptodown, downtoup |} |] DEMUX '),'src_position'(133,1,3926,54)).
'comment'('lineComment'('-- Safety Checks:'),'src_position'(135,1,3982,17)).
'comment'('lineComment'('-- assert Seqsys :[deterministic [FD] ]'),'src_position'(137,1,4001,39)).
'comment'('lineComment'('-- assert Seqsys :[deadlock free [F] ]'),'src_position'(138,1,4041,38)).
'comment'('lineComment'('-- assert Seqsys :[divergence free ]'),'src_position'(139,1,4080,36)).
'comment'('lineComment'('-- Check the specifications:'),'src_position'(141,1,4118,28)).
'comment'('lineComment'('-- assert DCopy  [FD= Seqsys'),'src_position'(150,1,4272,28)).
'comment'('lineComment'('-- assert Seqsys [FD= DCopy'),'src_position'(151,1,4301,27)).
'symbol'('Data','Data','src_span'(10,1,10,5,239,4),'Ident (Groundrep.)').
'symbol'('Chans','Chans','src_span'(11,1,11,6,253,5),'Ident (Groundrep.)').
'symbol'('LinkData','LinkData','src_span'(12,1,12,9,268,8),'Ident (Groundrep.)').
'symbol'('LinkDataT','LinkDataT','src_span'(13,10,13,19,309,9),'Nametype').
'symbol'('Acks','Acks','src_span'(14,1,14,5,354,4),'Ident (Groundrep.)').
'symbol'('AcksT','AcksT','src_span'(15,10,15,15,381,5),'Nametype').
'symbol'('upin0','upin0','src_span'(20,9,20,14,521,5),'Channel').
'symbol'('upin1','upin1','src_span'(20,16,20,21,528,5),'Channel').
'symbol'('downin0','downin0','src_span'(20,23,20,30,535,7),'Channel').
'symbol'('downin1','downin1','src_span'(20,32,20,39,544,7),'Channel').
'symbol'('downout0','downout0','src_span'(20,41,20,49,553,8),'Channel').
'symbol'('downout1','downout1','src_span'(20,51,20,59,563,8),'Channel').
'symbol'('uptodown','uptodown','src_span'(25,9,25,17,631,8),'Channel').
'symbol'('downtoup','downtoup','src_span'(26,9,26,17,659,8),'Channel').
'symbol'('MUX','MUX','src_span'(33,1,33,4,838,3),'Ident (Groundrep.)').
'symbol'('chan','chan','src_span'(33,9,33,13,846,4),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(33,38,33,39,875,1),'Ident (Prolog Variable)').
'symbol'('MUX0','MUX0','src_span'(42,1,42,5,1194,4),'Ident (Groundrep.)').
'symbol'('chan2','chan','src_span'(42,10,42,14,1203,4),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(42,44,42,45,1237,1),'Ident (Prolog Variable)').
'symbol'('MUX1','MUX1','src_span'(49,1,49,5,1376,4),'Ident (Groundrep.)').
'symbol'('chan3','chan','src_span'(49,10,49,14,1385,4),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(49,44,49,45,1419,1),'Ident (Prolog Variable)').
'symbol'('MUX01','MUX01','src_span'(60,1,60,6,1764,5),'Ident (Groundrep.)').
'symbol'('x4','x','src_span'(60,19,60,20,1782,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(60,21,60,22,1784,1),'Ident (Prolog Variable)').
'symbol'('DEMUX','DEMUX','src_span'(69,1,69,6,2023,5),'Ident (Groundrep.)').
'symbol'('vchan','vchan','src_span'(69,18,69,23,2040,5),'Ident (Prolog Variable)').
'symbol'('x5','x','src_span'(69,24,69,25,2046,1),'Ident (Prolog Variable)').
'symbol'('DEMUX0','DEMUX0','src_span'(78,1,78,7,2340,6),'Funktion or Process').
'symbol'('x6','x','src_span'(78,8,78,9,2347,1),'Ident (Prolog Variable)').
'symbol'('chan4','chan','src_span'(78,16,78,20,2355,4),'Ident (Prolog Variable)').
'symbol'('y2','y','src_span'(78,53,78,54,2392,1),'Ident (Prolog Variable)').
'symbol'('DEMUX0a','DEMUX0a','src_span'(83,1,83,8,2502,7),'Ident (Groundrep.)').
'symbol'('chan5','chan','src_span'(83,15,83,19,2516,4),'Ident (Prolog Variable)').
'symbol'('y3','y','src_span'(83,54,83,55,2555,1),'Ident (Prolog Variable)').
'symbol'('DEMUX0b','DEMUX0b','src_span'(88,1,88,8,2660,7),'Funktion or Process').
'symbol'('y4','y','src_span'(88,9,88,10,2668,1),'Ident (Prolog Variable)').
'symbol'('chan6','chan','src_span'(88,18,88,22,2677,4),'Ident (Prolog Variable)').
'symbol'('DEMUX01a','DEMUX01a','src_span'(94,1,94,9,2807,8),'Ident (Groundrep.)').
'symbol'('chan7','chan','src_span'(94,15,94,19,2821,4),'Ident (Prolog Variable)').
'symbol'('DEMUX1','DEMUX1','src_span'(102,1,102,7,2987,6),'Funktion or Process').
'symbol'('x7','x','src_span'(102,8,102,9,2994,1),'Ident (Prolog Variable)').
'symbol'('chan8','chan','src_span'(102,16,102,20,3002,4),'Ident (Prolog Variable)').
'symbol'('y5','y','src_span'(102,53,102,54,3039,1),'Ident (Prolog Variable)').
'symbol'('DEMUX1a','DEMUX1a','src_span'(108,1,108,8,3150,7),'Ident (Groundrep.)').
'symbol'('chan9','chan','src_span'(108,15,108,19,3164,4),'Ident (Prolog Variable)').
'symbol'('y6','y','src_span'(108,54,108,55,3203,1),'Ident (Prolog Variable)').
'symbol'('DEMUX1b','DEMUX1b','src_span'(114,1,114,8,3309,7),'Funktion or Process').
'symbol'('y7','y','src_span'(114,9,114,10,3317,1),'Ident (Prolog Variable)').
'symbol'('chan66','chan','src_span'(114,18,114,22,3326,4),'Ident (Prolog Variable)').
'symbol'('DEMUX01','DEMUX01','src_span'(121,1,121,8,3501,7),'Funktion or Process').
'symbol'('x8','x','src_span'(121,9,121,10,3509,1),'Ident (Prolog Variable)').
'symbol'('y8','y','src_span'(121,12,121,13,3512,1),'Ident (Prolog Variable)').
'symbol'('chan69','chan','src_span'(121,20,121,24,3520,4),'Ident (Prolog Variable)').
'symbol'('Seqsys','Seqsys','src_span'(130,1,130,7,3820,6),'Ident (Groundrep.)').
'symbol'('Copy0','Copy0','src_span'(143,1,143,6,4148,5),'Ident (Groundrep.)').
'symbol'('x9','x','src_span'(143,15,143,16,4162,1),'Ident (Prolog Variable)').
'symbol'('Copy1','Copy1','src_span'(145,1,145,6,4197,5),'Ident (Groundrep.)').
'symbol'('x71','x','src_span'(145,15,145,16,4211,1),'Ident (Prolog Variable)').
'symbol'('DCopy','DCopy','src_span'(148,1,148,6,4247,5),'Ident (Groundrep.)').
'symbol'('MAIN','MAIN','src_span'(153,1,153,5,4330,4),'Ident (Groundrep.)').