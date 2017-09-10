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
'channel'('tock','type'('dotUnitType')).
'dataTypeDef'('GateControl',['constructor'('go_down'),'constructor'('go_up'),'constructor'('up'),'constructor'('down')]).
'channel'('gate','type'('dotTupleType'(['GateControl']))).
'channel'('error','type'('dotUnitType')).
'bindval'('Segments','int'(5),'src_span'(38,1,38,13,1120,12)).
'bindval'('LastSeg','-'('val_of'('Segments','src_span'(39,11,39,19,1195,8)),'int'(1)),'src_span'(39,1,39,23,1185,22)).
'bindval'('TRACKS','setExp'('rangeClosed'('int'(0),'val_of'('LastSeg','src_span'(40,14,40,21,1221,7)))),'src_span'(40,1,40,22,1208,21)).
'bindval'('REALTRACKS','setExp'('rangeClosed'('int'(1),'val_of'('LastSeg','src_span'(41,18,41,25,1247,7)))),'src_span'(41,1,41,26,1230,25)).
'bindval'('GateSeg','int'(3),'src_span'(46,1,46,10,1386,9)).
'dataTypeDef'('TRAINS',['constructor'('Thomas'),'constructor'('Gordon')]).
'channel'('enter','type'('dotTupleType'(['val_of'('TRACKS','src_span'(54,24,54,30,1531,6)),'TRAINS']))).
'channel'('leave','type'('dotTupleType'(['val_of'('TRACKS','src_span'(54,24,54,30,1531,6)),'TRAINS']))).
'dataTypeDef'('sensed',['constructor'('in'),'constructor'('out')]).
'channel'('sensor','type'('dotTupleType'(['sensed']))).
'agent'('Train'(_A,_j),'prefix'('src_span'(68,15,68,39,1937,24),[],'dotTuple'(['enter','%'('+'(_j,'int'(1)),'val_of'('Segments','src_span'(68,28,68,36,1950,8))),_A]),'prefix'('src_span'(68,43,68,52,1965,9),[],'dotTuple'(['leave',_j,_A]),'agent_call'('src_span'(68,56,68,61,1978,5),'Train',[_A,'%'('+'(_j,'int'(1)),'val_of'('Segments','src_span'(68,70,68,78,1992,8)))]),'src_span'(68,53,68,55,1974,36)),'src_span'(68,40,68,42,1961,64)),'src_span'(68,15,68,79,1937,64)).
'bindval'('Trains','|||'('agent_call'('src_span'(72,10,72,15,2067,5),'Train',['Thomas','int'(0)]),'agent_call'('src_span'(72,30,72,35,2087,5),'Train',['Gordon','int'(0)]),'src_span_operator'('no_loc_info_available','src_span'(72,26,72,29,2083,3))),'src_span'(72,1,72,45,2058,44)).
'agent'('Track'(_j2),'let'(['bindval'('Empty','prefix'('src_span'(79,15,79,22,2282,7),['in'(_A2)],'dotTuple'(['enter',_j2]),'ifte'('=='(_j2,'int'(1)),'prefix'('src_span'(79,41,79,50,2308,9),[],'dotTuple'(['sensor','in']),'agent_call'('src_span'(79,54,79,58,2321,4),'Full',[_A2]),'src_span'(79,51,79,53,2317,20)),'agent_call'('src_span'(79,67,79,71,2334,4),'Full',[_A2]),'no_loc_info_available','no_loc_info_available','src_span'(79,62,79,66,2328,33)),'src_span'(79,25,79,27,2291,52)),'src_span'(79,5,79,74,2272,69)),'agent'('Full'(_A3),'prefix'('src_span'(80,15,80,24,2356,9),[],'dotTuple'(['leave',_j2,_A3]),'ifte'('=='(_j2,'val_of'('GateSeg','src_span'(80,34,80,41,2375,7))),'prefix'('src_span'(80,47,80,57,2388,10),[],'dotTuple'(['sensor','out']),'val_of'('Empty','src_span'(80,61,80,66,2402,5)),'src_span'(80,58,80,60,2398,19)),'val_of'('Empty','src_span'(80,72,80,77,2413,5)),'no_loc_info_available','no_loc_info_available','src_span'(80,67,80,71,2407,30)),'src_span'(80,25,80,27,2365,62)),'src_span'(80,15,80,77,2356,62))],'val_of'('Empty','src_span'(81,10,81,15,2428,5))),'src_span'(78,3,81,15,2264,169)).
'bindval'('Tracks','repInterleave'(['comprehensionGenerator'(_j3,'val_of'('REALTRACKS','src_span'(86,18,86,28,2539,10)))],'agent_call'('src_span'(86,31,86,36,2552,5),'Track',[_j3]),'src_span'(86,14,86,30,2535,16)),'src_span'(86,1,86,39,2522,38)).
'bindval'('Network','sharing'('closureComp'(['comprehensionGenerator'(_j4,'val_of'('REALTRACKS','src_span'(92,44,92,54,2803,10)))],['dotTuple'(['enter',_j4]),'dotTuple'(['leave',_j4])]),'val_of'('Trains','src_span'(92,11,92,17,2770,6)),'val_of'('Tracks','src_span'(92,59,92,65,2818,6)),'src_span'(92,18,92,58,2777,40)),'src_span'(92,1,92,65,2760,64)).
'bindval'('SlowTrain','int'(4),'src_span'(98,1,98,14,3023,13)).
'bindval'('NormalTrain','int'(3),'src_span'(99,1,99,16,3090,15)).
'bindval'('FastTrain','int'(2),'src_span'(100,1,100,14,3106,13)).
'bindval'('MaxTocksPerSeg','int'(6),'src_span'(102,1,102,19,3121,18)).
'agent'('SpeedReg'(_j5,_MinTocksPerSeg),'let'(['bindval'('Empty2','[]'('prefix'('src_span'(109,15,109,22,3279,7),['in'(_A4)],'dotTuple'(['enter',_j5]),'agent_call'('src_span'(109,28,109,32,3292,4),'Full2',['int'(0)]),'src_span'(109,25,109,27,3288,13)),'prefix'('src_span'(109,39,109,43,3303,4),[],'tock','val_of'('Empty2','src_span'(109,47,109,52,3311,5)),'src_span'(109,44,109,46,3307,13)),'src_span_operator'('no_loc_info_available','src_span'(109,36,109,38,3300,2))),'src_span'(109,5,109,52,3269,47)),'agent'('Full2'(_n),'[]'('&'('<'(_n,'val_of'('MaxTocksPerSeg','src_span'(110,20,110,34,3336,14))),'prefix'('src_span'(110,37,110,41,3353,4),[],'tock','agent_call'('src_span'(110,45,110,49,3361,4),'Full2',['+'(_n,'int'(1))]),'src_span'(110,42,110,44,3357,17))),'&'('<='(_MinTocksPerSeg,_n),'prefix'('src_span'(111,37,111,57,3408,20),['in'(_A5)],'dotTuple'(['enter','%'('+'(_j5,'int'(1)),'val_of'('Segments','src_span'(111,49,111,57,3420,8)))]),'val_of'('Empty2','src_span'(111,63,111,68,3434,5)),'src_span'(111,60,111,62,3430,11))),'src_span_operator'('no_loc_info_available','src_span'(111,12,111,14,3383,2))),'no_loc_info_available')],'val_of'('Empty2','src_span'(112,10,112,15,3449,5))),'src_span'(108,3,112,15,3261,193)).
'bindval'('InSensorTiming','[]'('prefix'('src_span'(118,18,118,22,3641,4),[],'tock','val_of'('InSensorTiming','src_span'(118,26,118,40,3649,14)),'src_span'(118,23,118,25,3645,22)),'prefix'('src_span'(119,18,119,25,3681,7),['in'(_A6)],'dotTuple'(['enter','int'(1)]),'prefix'('src_span'(119,31,119,40,3694,9),[],'dotTuple'(['sensor','in']),'val_of'('InSensorTiming','src_span'(119,44,119,58,3707,14)),'src_span'(119,41,119,43,3703,27)),'src_span'(119,28,119,30,3690,33)),'src_span_operator'('no_loc_info_available','src_span'(119,15,119,17,3678,2))),'src_span'(118,1,119,58,3624,97)).
'bindval'('OutSensorTiming','[]'('prefix'('src_span'(121,19,121,23,3741,4),[],'tock','val_of'('OutSensorTiming','src_span'(121,27,121,42,3749,15)),'src_span'(121,24,121,26,3745,23)),'prefix'('src_span'(122,19,122,32,3783,13),['in'(_A7)],'dotTuple'(['leave','val_of'('GateSeg','src_span'(122,25,122,32,3789,7))]),'prefix'('src_span'(122,38,122,48,3802,10),[],'dotTuple'(['sensor','out']),'val_of'('OutSensorTiming','src_span'(122,52,122,67,3816,15)),'src_span'(122,49,122,51,3812,29)),'src_span'(122,35,122,37,3798,35)),'src_span_operator'('no_loc_info_available','src_span'(122,16,122,18,3780,2))),'src_span'(121,1,122,67,3723,108)).
'agent'('SpeedRegs'(_min),'stop'('src_span'(131,18,131,22,4154,4)),'src_span'(131,18,131,22,4154,4)).
'bindval'('SensorTiming','sharing'('setExp'('rangeEnum'(['tock'])),'val_of'('InSensorTiming','src_span'(134,16,134,30,4176,14)),'val_of'('OutSensorTiming','src_span'(134,42,134,57,4202,15)),'src_span'(134,31,134,41,4191,10)),'src_span'(134,1,134,57,4161,56)).
'agent'('NetworkTiming'(_min2),'sharing'('closure'(['tock','dotTuple'(['enter','int'(1)])]),'agent_call'('src_span'(136,22,136,31,4240,9),'SpeedRegs',[_min2]),'val_of'('SensorTiming','src_span'(136,59,136,71,4277,12)),'src_span'(136,37,136,58,4255,21)),'src_span'(136,37,136,58,4255,21)).
'agent'('TimedNetwork'(_min3),'sharing'('closure'(['enter','sensor','dotTuple'(['leave','val_of'('GateSeg','src_span'(139,36,139,43,4346,7))])]),'val_of'('Network','src_span'(139,3,139,10,4313,7)),'agent_call'('src_span'(139,48,139,61,4358,13),'NetworkTiming',[_min3]),'src_span'(139,11,139,47,4321,36)),'src_span'(139,11,139,47,4321,36)).
'bindval'('Controller','let'(['bindval'('ControllerUp','[]'('[]'('prefix'('src_span'(158,20,158,29,5270,9),[],'dotTuple'(['sensor','in']),'prefix'('src_span'(158,33,158,37,5283,4),['out'('go_down')],'gate','agent_call'('src_span'(158,49,158,68,5299,19),'ControllerGoingDown',['int'(1)]),'src_span'(158,46,158,48,5295,34)),'src_span'(158,30,158,32,5279,51)),'prefix'('src_span'(159,20,159,30,5341,10),[],'dotTuple'(['sensor','out']),'val_of'('ERROR','src_span'(159,34,159,39,5355,5)),'src_span'(159,31,159,33,5351,19)),'src_span_operator'('no_loc_info_available','src_span'(159,17,159,19,5338,2))),'prefix'('src_span'(160,20,160,24,5380,4),[],'tock','val_of'('ControllerUp','src_span'(160,28,160,40,5388,12)),'src_span'(160,25,160,27,5384,20)),'src_span_operator'('no_loc_info_available','src_span'(160,17,160,19,5377,2))),'src_span'(158,5,160,40,5255,145)),'agent'('ControllerGoingDown'(_n2),'[]'('[]'('[]'('ifte'('<'('val_of'('GateSeg','src_span'(171,14,171,21,5933,7)),_n2),'val_of'('ERROR','src_span'(171,31,171,36,5950,5)),'prefix'('src_span'(171,42,171,51,5961,9),[],'dotTuple'(['sensor','in']),'agent_call'('src_span'(171,55,171,74,5974,19),'ControllerGoingDown',['+'(_n2,'int'(1))]),'src_span'(171,52,171,54,5970,37)),'no_loc_info_available','no_loc_info_available','src_span'(171,37,171,41,5955,48)),'prefix'('src_span'(172,10,172,19,6009,9),[],'dotTuple'(['gate','down']),'agent_call'('src_span'(172,23,172,37,6022,14),'ControllerDown',[_n2]),'src_span'(172,20,172,22,6018,30)),'src_span_operator'('no_loc_info_available','src_span'(172,7,172,9,6006,2))),'prefix'('src_span'(173,10,173,14,6049,4),[],'tock','agent_call'('src_span'(173,18,173,37,6057,19),'ControllerGoingDown',[_n2]),'src_span'(173,15,173,17,6053,30)),'src_span_operator'('no_loc_info_available','src_span'(173,7,173,9,6046,2))),'prefix'('src_span'(174,10,174,20,6089,10),[],'dotTuple'(['sensor','out']),'val_of'('ERROR','src_span'(174,24,174,29,6103,5)),'src_span'(174,21,174,23,6099,19)),'src_span_operator'('no_loc_info_available','src_span'(174,7,174,9,6086,2))),'no_loc_info_available'),'agent'('ControllerDown'(_n3),'[]'('[]'('ifte'('<'('val_of'('GateSeg','src_span'(182,14,182,21,6526,7)),_n3),'val_of'('ERROR','src_span'(182,31,182,36,6543,5)),'prefix'('src_span'(182,42,182,51,6554,9),[],'dotTuple'(['sensor','in']),'agent_call'('src_span'(182,55,182,69,6567,14),'ControllerDown',['+'(_n3,'int'(1))]),'src_span'(182,52,182,54,6563,32)),'no_loc_info_available','no_loc_info_available','src_span'(182,37,182,41,6548,43)),'prefix'('src_span'(183,10,183,20,6597,10),[],'dotTuple'(['sensor','out']),'ifte'('=='(_n3,'int'(1)),'prefix'('src_span'(183,38,183,42,6625,4),['out'('go_up')],'gate','val_of'('ControllerGoingUp','src_span'(183,52,183,69,6639,17)),'src_span'(183,49,183,51,6635,27)),'agent_call'('src_span'(184,38,184,52,6695,14),'ControllerDown',['-'(_n3,'int'(1))]),'no_loc_info_available','no_loc_info_available','src_span'(183,70,184,37,6656,89)),'src_span'(183,21,183,23,6607,118)),'src_span_operator'('no_loc_info_available','src_span'(183,7,183,9,6594,2))),'prefix'('src_span'(185,10,185,14,6725,4),[],'tock','agent_call'('src_span'(185,18,185,32,6733,14),'ControllerDown',[_n3]),'src_span'(185,15,185,17,6729,25)),'src_span_operator'('no_loc_info_available','src_span'(185,7,185,9,6722,2))),'no_loc_info_available'),'bindval'('ControllerGoingUp','[]'('[]'('[]'('prefix'('src_span'(189,26,189,30,6967,4),['out'('up')],'gate','val_of'('ControllerUp','src_span'(189,37,189,49,6978,12)),'src_span'(189,34,189,36,6974,19)),'prefix'('src_span'(190,25,190,29,7015,4),[],'tock','val_of'('ControllerGoingUp','src_span'(190,33,190,50,7023,17)),'src_span'(190,30,190,32,7019,25)),'src_span_operator'('no_loc_info_available','src_span'(190,22,190,24,7012,2))),'prefix'('src_span'(191,25,191,34,7065,9),[],'dotTuple'(['sensor','in']),'prefix'('src_span'(191,38,191,42,7078,4),['out'('go_down')],'gate','agent_call'('src_span'(191,54,191,73,7094,19),'ControllerGoingDown',['int'(1)]),'src_span'(191,51,191,53,7090,34)),'src_span'(191,35,191,37,7074,51)),'src_span_operator'('no_loc_info_available','src_span'(191,22,191,24,7062,2))),'prefix'('src_span'(192,25,192,35,7141,10),[],'dotTuple'(['sensor','out']),'val_of'('ERROR','src_span'(192,39,192,44,7155,5)),'src_span'(192,36,192,38,7151,19)),'src_span_operator'('no_loc_info_available','src_span'(192,22,192,24,7138,2))),'src_span'(189,5,192,44,6946,214))],'val_of'('ControllerUp','src_span'(193,10,193,22,7170,12))),'src_span'(151,1,193,22,4940,2242)).
'bindval'('ERROR','prefix'('src_span'(199,9,199,14,7390,5),[],'error','stop'('src_span'(199,18,199,22,7399,4)),'src_span'(199,15,199,17,7395,13)),'src_span'(199,1,199,22,7382,21)).
'bindval'('VeryFastGate','int'(3),'src_span'(205,1,205,17,7594,16)).
'bindval'('FastGate','int'(4),'src_span'(206,1,206,13,7611,12)).
'bindval'('NormalGate','int'(5),'src_span'(207,1,207,15,7624,14)).
'bindval'('SlowGate','int'(6),'src_span'(208,1,208,13,7639,12)).
'bindval'('UpTime','int'(2),'src_span'(210,1,210,11,7653,10)).
'agent'('Gate'(_DownTime),'let'(['bindval'('GateUp','[]'('[]'('prefix'('src_span'(214,14,214,24,7701,10),[],'dotTuple'(['gate','go_up']),'val_of'('GateUp','src_span'(214,28,214,34,7715,6)),'src_span'(214,25,214,27,7711,20)),'prefix'('src_span'(215,14,215,26,7735,12),[],'dotTuple'(['gate','go_down']),'agent_call'('src_span'(215,30,215,43,7751,13),'GateGoingDown',['int'(0)]),'src_span'(215,27,215,29,7747,32)),'src_span_operator'('no_loc_info_available','src_span'(215,11,215,13,7732,2))),'prefix'('src_span'(216,7,216,11,7774,4),[],'tock','val_of'('GateUp','src_span'(216,15,216,21,7782,6)),'src_span'(216,12,216,14,7778,14)),'src_span_operator'('no_loc_info_available','src_span'(216,4,216,6,7771,2))),'src_span'(214,5,216,21,7692,96)),'agent'('GateGoingDown'(_n4),'[]'('prefix'('src_span'(218,10,218,22,7821,12),[],'dotTuple'(['gate','go_down']),'agent_call'('src_span'(218,26,218,39,7837,13),'GateGoingDown',[_n4]),'src_span'(218,23,218,25,7833,32)),'ifte'('=='(_n4,_DownTime),'prefix'('src_span'(220,15,220,24,7894,9),[],'dotTuple'(['gate','down']),'val_of'('GateDown','src_span'(220,28,220,36,7907,8)),'src_span'(220,25,220,27,7903,21)),'|~|'('prefix'('src_span'(221,8,221,17,7923,9),[],'dotTuple'(['gate','down']),'val_of'('GateDown','src_span'(221,21,221,29,7936,8)),'src_span'(221,18,221,20,7932,21)),'prefix'('src_span'(221,34,221,38,7949,4),[],'tock','agent_call'('src_span'(221,42,221,55,7957,13),'GateGoingDown',['+'(_n4,'int'(1))]),'src_span'(221,39,221,41,7953,26)),'src_span_operator'('no_loc_info_available','src_span'(221,30,221,33,7945,3))),'no_loc_info_available','no_loc_info_available','no_loc_info_available'),'src_span_operator'('no_loc_info_available','src_span'(219,7,219,9,7860,2))),'no_loc_info_available'),'bindval'('GateDown','[]'('[]'('prefix'('src_span'(222,16,222,28,7991,12),[],'dotTuple'(['gate','go_down']),'val_of'('GateDown','src_span'(222,32,222,40,8007,8)),'src_span'(222,29,222,31,8003,24)),'prefix'('src_span'(223,16,223,26,8031,10),[],'dotTuple'(['gate','go_up']),'agent_call'('src_span'(223,30,223,41,8045,11),'GateGoingUp',['int'(0)]),'src_span'(223,27,223,29,8041,28)),'src_span_operator'('no_loc_info_available','src_span'(223,13,223,15,8028,2))),'prefix'('src_span'(224,9,224,13,8068,4),[],'tock','val_of'('GateDown','src_span'(224,17,224,25,8076,8)),'src_span'(224,14,224,16,8072,16)),'src_span_operator'('no_loc_info_available','src_span'(224,6,224,8,8065,2))),'src_span'(222,5,224,25,7980,104)),'agent'('GateGoingUp'(_n5),'[]'('[]'('prefix'('src_span'(225,22,225,32,8106,10),[],'dotTuple'(['gate','go_up']),'agent_call'('src_span'(225,36,225,47,8120,11),'GateGoingUp',[_n5]),'src_span'(225,33,225,35,8116,28)),'prefix'('src_span'(226,22,226,34,8156,12),[],'dotTuple'(['gate','go_down']),'agent_call'('src_span'(226,38,226,51,8172,13),'GateGoingDown',['int'(0)]),'src_span'(226,35,226,37,8168,32)),'src_span_operator'('no_loc_info_available','src_span'(226,19,226,21,8153,2))),'ifte'('=='(_n5,'val_of'('UpTime','src_span'(227,30,227,36,8218,6))),'prefix'('src_span'(228,27,228,34,8251,7),[],'dotTuple'(['gate','up']),'val_of'('GateUp','src_span'(228,38,228,44,8262,6)),'src_span'(228,35,228,37,8258,17)),'|~|'('prefix'('src_span'(229,20,229,27,8288,7),[],'dotTuple'(['gate','up']),'val_of'('GateUp','src_span'(229,31,229,37,8299,6)),'src_span'(229,28,229,30,8295,17)),'prefix'('src_span'(229,42,229,46,8310,4),[],'tock','agent_call'('src_span'(229,50,229,61,8318,11),'GateGoingUp',['+'(_n5,'int'(1))]),'src_span'(229,47,229,49,8314,24)),'src_span_operator'('no_loc_info_available','src_span'(229,38,229,41,8306,3))),'no_loc_info_available','no_loc_info_available','no_loc_info_available'),'src_span_operator'('no_loc_info_available','src_span'(227,19,227,21,8207,2))),'no_loc_info_available')],'val_of'('GateUp','src_span'(230,10,230,16,8344,6))),'src_span'(213,3,230,16,7684,666)).
'cspTransparent'(['sbisim']).
'cspTransparent'(['normalise']).
'cspTransparent'(['explicate']).
'cspTransparent'(['diamond']).
'agent'('GateAndController'(_dt),'sharing'('closure'(['tock','gate']),'val_of'('Controller','src_span'(241,25,241,35,8665,10)),'agent_call'('src_span'(241,54,241,61,8694,7),'diamond',['agent_call'('src_span'(241,62,241,66,8702,4),'Gate',[_dt])]),'src_span'(241,36,241,53,8676,17)),'src_span'(241,36,241,53,8676,17)).
'agent'('System'(_invmaxspeed,_gatedowntime),'sharing'('closure'(['sensor','tock']),'agent_call'('src_span'(247,3,247,15,8840,12),'TimedNetwork',[_invmaxspeed]),'agent_call'('src_span'(247,49,247,66,8886,17),'GateAndController',[_gatedowntime]),'src_span'(247,29,247,48,8866,19)),'src_span'(247,29,247,48,8866,19)).
'bindval'('NoError','builtin_call'('CHAOS'('src_span'(254,11,254,38,9100,27),'agent_call'('src_span'(254,17,254,21,9106,4),'diff',['Events','setExp'('rangeEnum'(['error']))]))),'src_span'(254,1,254,38,9090,37)).
'assertRef'('False','val_of'('NoError','src_span'(256,8,256,15,9136,7)),'Trace','agent_call'('src_span'(256,20,256,26,9148,6),'System',['val_of'('NormalTrain','src_span'(256,27,256,38,9155,11)),'val_of'('NormalGate','src_span'(256,39,256,49,9167,10))]),'src_span'(256,1,256,50,9129,49)).
'agent'('SETBETWEENx'(_EN,_DIS,_C),'[]'('repChoice'(['comprehensionGenerator'(_x,_EN)],'prefix'('src_span'(266,35,266,36,9608,1),[],_x,'agent_call'('src_span'(266,40,266,51,9613,11),'SETOUTSIDEx',[_DIS,_EN,_C]),'src_span'(266,37,266,39,9609,26)),'src_span'(266,28,266,34,9601,6)),'repChoice'(['comprehensionGenerator'(_x2,_DIS)],'prefix'('src_span'(267,40,267,41,9675,1),[],_x2,'agent_call'('src_span'(267,45,267,56,9680,11),'SETBETWEENx',[_EN,_DIS,_C]),'src_span'(267,42,267,44,9676,26)),'src_span'(267,32,267,39,9667,7)),'src_span_operator'('no_loc_info_available','src_span'(267,25,267,27,9660,2))),'no_loc_info_available').
'agent'('SETOUTSIDEx'(_DIS2,_EN2,_C2),'[]'('[]'('repChoice'(['comprehensionGenerator'(_c,_C2)],'prefix'('src_span'(269,35,269,36,9738,1),[],_c,'agent_call'('src_span'(269,40,269,51,9743,11),'SETOUTSIDEx',[_DIS2,_EN2,_C2]),'src_span'(269,37,269,39,9739,26)),'src_span'(269,29,269,34,9732,5)),'repChoice'(['comprehensionGenerator'(_x3,_EN2)],'prefix'('src_span'(270,40,270,41,9805,1),[],_x3,'agent_call'('src_span'(270,45,270,56,9810,11),'SETOUTSIDEx',[_DIS2,_EN2,_C2]),'src_span'(270,42,270,44,9806,26)),'src_span'(270,32,270,39,9797,7)),'src_span_operator'('no_loc_info_available','src_span'(270,25,270,27,9790,2))),'repChoice'(['comprehensionGenerator'(_x4,_DIS2)],'prefix'('src_span'(271,40,271,41,9872,1),[],_x4,'agent_call'('src_span'(271,45,271,56,9877,11),'SETBETWEENx',[_EN2,_DIS2,_C2]),'src_span'(271,42,271,44,9873,26)),'src_span'(271,32,271,39,9864,7)),'src_span_operator'('no_loc_info_available','src_span'(271,25,271,27,9857,2))),'no_loc_info_available').
'bindval'('EnterWhenDown','sharing'('closure'(['gate','dotTuple'(['enter','val_of'('GateSeg','src_span'(282,19,282,26,10311,7))])]),'agent_call'('src_span'(279,3,279,14,10186,11),'SETBETWEENx',['setExp'('rangeEnum'(['dotTuple'(['gate','down'])])),'setExp'('rangeEnum'(['dotTuple'(['gate','up']),'dotTuple'(['gate','go_up']),'dotTuple'(['gate','go_down'])])),'closure'(['dotTuple'(['enter','val_of'('GateSeg','src_span'(281,23,281,30,10282,7))])])]),'builtin_call'('CHAOS'('src_span'(283,3,283,16,10325,13),'Events')),'src_span'(282,3,282,30,10295,27)),'src_span'(278,1,283,16,10168,170)).
'bindval'('GateStillWhenTrain','sharing'('closure'(['gate','dotTuple'(['enter','val_of'('GateSeg','src_span'(287,18,287,25,10438,7))]),'dotTuple'(['leave','val_of'('GateSeg','src_span'(287,32,287,39,10452,7))])]),'agent_call'('src_span'(286,3,286,14,10363,11),'SETOUTSIDEx',['closure'(['dotTuple'(['enter','val_of'('GateSeg','src_span'(286,23,286,30,10383,7))])]),'closure'(['dotTuple'(['leave','val_of'('GateSeg','src_span'(286,41,286,48,10401,7))])]),'closure'(['gate'])]),'builtin_call'('CHAOS'('src_span'(288,3,288,16,10466,13),'Events')),'src_span'(287,3,287,43,10423,40)),'src_span'(285,1,288,16,10340,139)).
'bindval'('Safety','sharing'('Events','val_of'('EnterWhenDown','src_span'(292,10,292,23,10551,13)),'val_of'('GateStillWhenTrain','src_span'(292,35,292,53,10576,18)),'src_span'(292,24,292,34,10565,10)),'src_span'(292,1,292,53,10542,52)).
'assertRef'('False','val_of'('Safety','src_span'(296,8,296,14,10681,6)),'Trace','agent_call'('src_span'(296,19,296,25,10692,6),'System',['val_of'('SlowTrain','src_span'(296,26,296,35,10699,9)),'val_of'('NormalGate','src_span'(296,36,296,46,10709,10))]),'src_span'(296,1,296,47,10674,46)).
'assertRef'('False','val_of'('Safety','src_span'(297,8,297,14,10728,6)),'Trace','agent_call'('src_span'(297,19,297,25,10739,6),'System',['val_of'('NormalTrain','src_span'(297,26,297,37,10746,11)),'val_of'('NormalGate','src_span'(297,38,297,48,10758,10))]),'src_span'(297,1,297,49,10721,48)).
'assertRef'('False','val_of'('NoError','src_span'(298,8,298,15,10777,7)),'Trace','agent_call'('src_span'(298,20,298,26,10789,6),'System',['val_of'('FastTrain','src_span'(298,27,298,36,10796,9)),'val_of'('SlowGate','src_span'(298,37,298,45,10806,8))]),'src_span'(298,1,298,46,10770,45)).
'assertRef'('False','val_of'('Safety','src_span'(299,8,299,14,10823,6)),'Trace','agent_call'('src_span'(299,19,299,25,10834,6),'System',['val_of'('FastTrain','src_span'(299,26,299,35,10841,9)),'val_of'('NormalGate','src_span'(299,36,299,46,10851,10))]),'src_span'(299,1,299,47,10816,46)).
'assertRef'('False','val_of'('NoError','src_span'(300,8,300,15,10870,7)),'Trace','agent_call'('src_span'(300,20,300,26,10882,6),'System',['val_of'('FastTrain','src_span'(300,27,300,36,10889,9)),'val_of'('NormalGate','src_span'(300,37,300,47,10899,10))]),'src_span'(300,1,300,48,10863,47)).
'assertRef'('False','val_of'('Safety','src_span'(301,8,301,14,10918,6)),'Trace','agent_call'('src_span'(301,19,301,25,10929,6),'System',['val_of'('SlowTrain','src_span'(301,26,301,35,10936,9)),'val_of'('SlowGate','src_span'(301,36,301,44,10946,8))]),'src_span'(301,1,301,45,10911,44)).
'assertRef'('False','val_of'('Safety','src_span'(302,8,302,14,10963,6)),'Trace','agent_call'('src_span'(302,19,302,25,10974,6),'System',['val_of'('FastTrain','src_span'(302,26,302,35,10981,9)),'val_of'('FastGate','src_span'(302,36,302,44,10991,8))]),'src_span'(302,1,302,45,10956,44)).
'assertRef'('False','val_of'('Safety','src_span'(303,8,303,14,11008,6)),'Trace','agent_call'('src_span'(303,19,303,25,11019,6),'System',['val_of'('FastTrain','src_span'(303,26,303,35,11026,9)),'val_of'('VeryFastGate','src_span'(303,36,303,48,11036,12))]),'src_span'(303,1,303,49,11001,48)).
'bindval'('TOCKS','prefix'('src_span'(310,9,310,13,11229,4),[],'tock','val_of'('TOCKS','src_span'(310,17,310,22,11237,5)),'src_span'(310,14,310,16,11233,13)),'src_span'(310,1,310,22,11221,21)).
'bindval'('Delayable','closure'(['dotTuple'(['enter','int'(1)])]),'src_span'(315,1,315,24,11339,23)).
'bindval'('NonTock','agent_call'('src_span'(316,11,316,15,11373,4),'diff',['Events','setExp'('rangeEnum'(['tock']))]),'src_span'(316,1,316,30,11363,29)).
'agent'('TimingConsistency'(_ts,_gs),'agent_call'('src_span'(318,3,318,12,11422,9),'explicate',['\x5c\'('sharing'('val_of'('Delayable','src_span'(318,28,318,37,11447,9)),'agent_call'('src_span'(318,13,318,19,11432,6),'System',[_ts,_gs]),'agent_call'('src_span'(318,39,318,48,11458,9),'normalise',['builtin_call'('CHAOS'('src_span'(318,49,318,65,11468,16),'val_of'('Delayable','src_span'(318,55,318,64,11474,9))))]),'src_span'(318,26,318,39,11445,13)),'val_of'('NonTock','src_span'(318,67,318,74,11486,7)),'src_span_operator'('no_loc_info_available','src_span'(318,66,318,67,11485,1)))]),'src_span'(318,3,318,75,11422,72)).
'assertRef'('False','val_of'('TOCKS','src_span'(320,8,320,13,11503,5)),'FailureDivergence','agent_call'('src_span'(320,19,320,36,11514,17),'TimingConsistency',['val_of'('NormalTrain','src_span'(320,37,320,48,11532,11)),'val_of'('NormalGate','src_span'(320,49,320,59,11544,10))]),'src_span'(320,1,320,60,11496,59)).
'agent'('Liveness'(_X),'let'(['bindval'('Idle','[]'('prefix'('src_span'(339,12,339,16,12494,4),[],'tock','val_of'('Idle','src_span'(339,20,339,24,12502,4)),'src_span'(339,17,339,19,12498,12)),'prefix'('src_span'(340,12,340,19,12518,7),['in'(_)],'dotTuple'(['enter','int'(1)]),'agent_call'('src_span'(340,25,340,29,12531,4),'Busy',['int'(1)]),'src_span'(340,22,340,24,12527,13)),'src_span_operator'('no_loc_info_available','src_span'(340,9,340,11,12515,2))),'src_span'(339,5,340,32,12487,51)),'agent'('Busy'(_n6),'[]'('[]'('[]'('prefix'('src_span'(341,15,341,19,12553,4),[],'tock','agent_call'('src_span'(341,23,341,27,12561,4),'Busy',[_n6]),'src_span'(341,20,341,22,12557,15)),'prefix'('src_span'(342,15,342,22,12583,7),['in'(_)],'dotTuple'(['enter','int'(1)]),'agent_call'('src_span'(342,28,342,32,12596,4),'Busy',['ifte'('<'(_n6,'val_of'('GateSeg','src_span'(342,40,342,47,12608,7))),'+'(_n6,'int'(1)),_n6,'no_loc_info_available','no_loc_info_available','src_span'(342,59,342,63,12626,12))]),'src_span'(342,25,342,27,12592,44)),'src_span_operator'('no_loc_info_available','src_span'(342,12,342,14,12580,2))),'prefix'('src_span'(343,15,343,28,12649,13),['in'(_)],'dotTuple'(['leave','val_of'('GateSeg','src_span'(343,21,343,28,12655,7))]),'ifte'('=='(_n6,'int'(1)),'agent_call'('src_span'(343,48,343,56,12682,8),'UpBefore',[_X]),'agent_call'('src_span'(343,65,343,69,12699,4),'Busy',['-'(_n6,'int'(1))]),'no_loc_info_available','no_loc_info_available','src_span'(343,60,343,64,12693,26)),'src_span'(343,31,343,33,12664,47)),'src_span_operator'('no_loc_info_available','src_span'(343,12,343,14,12646,2))),'prefix'('src_span'(344,15,344,19,12724,4),['in'(_)],'gate','agent_call'('src_span'(344,25,344,29,12734,4),'Busy',[_n6]),'src_span'(344,22,344,24,12730,13)),'src_span_operator'('no_loc_info_available','src_span'(344,12,344,14,12721,2))),'no_loc_info_available'),'agent'('UpBefore'(_m),'[]'('[]'('&'('!='(_m,'int'(0)),'prefix'('src_span'(345,28,345,32,12769,4),[],'tock','agent_call'('src_span'(345,36,345,44,12777,8),'UpBefore',['-'(_m,'int'(1))]),'src_span'(345,33,345,35,12773,21))),'prefix'('src_span'(346,19,346,23,12809,4),['in'(_x5)],'gate','ifte'('=='(_x5,'up'),'val_of'('Idle','src_span'(346,44,346,48,12834,4)),'agent_call'('src_span'(346,54,346,62,12844,8),'UpBefore',[_m]),'no_loc_info_available','no_loc_info_available','src_span'(346,49,346,53,12838,21)),'src_span'(346,26,346,28,12815,43)),'src_span_operator'('no_loc_info_available','src_span'(346,16,346,18,12806,2))),'prefix'('src_span'(347,19,347,26,12875,7),['in'(_)],'dotTuple'(['enter','int'(1)]),'agent_call'('src_span'(347,32,347,36,12888,4),'Busy',['int'(1)]),'src_span'(347,29,347,31,12884,13)),'src_span_operator'('no_loc_info_available','src_span'(347,16,347,18,12872,2))),'no_loc_info_available')],'val_of'('Idle','src_span'(350,10,350,14,13006,4))),'src_span'(338,3,350,14,12479,531)).
'agent'('GateLive'(_X2),'sharing'('closure'(['tock','gate','dotTuple'(['enter','int'(1)]),'dotTuple'(['leave','val_of'('GateSeg','src_span'(352,55,352,62,13083,7))])]),'agent_call'('src_span'(352,15,352,23,13043,8),'Liveness',[_X2]),'builtin_call'('CHAOS'('src_span'(352,66,352,79,13094,13),'Events')),'src_span'(352,27,352,66,13055,39)),'src_span'(352,27,352,66,13055,39)).
'assertRef'('False','agent_call'('src_span'(354,8,354,16,13116,8),'GateLive',['int'(3)]),'Trace','agent_call'('src_span'(354,24,354,30,13132,6),'System',['val_of'('NormalTrain','src_span'(354,31,354,42,13139,11)),'val_of'('NormalGate','src_span'(354,43,354,53,13151,10))]),'src_span'(354,1,354,54,13109,53)).
'assertRef'('False','agent_call'('src_span'(355,8,355,16,13170,8),'GateLive',['int'(2)]),'Trace','agent_call'('src_span'(355,24,355,30,13186,6),'System',['val_of'('NormalTrain','src_span'(355,31,355,42,13193,11)),'val_of'('NormalGate','src_span'(355,43,355,53,13205,10))]),'src_span'(355,1,355,54,13163,53)).
'assertRef'('False','agent_call'('src_span'(356,8,356,16,13224,8),'GateLive',['int'(1)]),'Trace','agent_call'('src_span'(356,24,356,30,13240,6),'System',['val_of'('NormalTrain','src_span'(356,31,356,42,13247,11)),'val_of'('NormalGate','src_span'(356,43,356,53,13259,10))]),'src_span'(356,1,356,54,13217,53)).
'assertRef'('False','agent_call'('src_span'(360,8,360,16,13332,8),'GateLive',['int'(3)]),'Trace','agent_call'('src_span'(360,24,360,32,13348,8),'GateLive',['int'(2)]),'src_span'(360,1,360,35,13325,34)).
'comment'('lineComment'('-- Model of a level crossing gate for FDR: revised version'),'src_position'(1,1,0,58)).
'comment'('lineComment'('-- Illustrating discrete-time modelling using untimed CSP'),'src_position'(2,1,59,57)).
'comment'('lineComment'('-- (c) Bill Roscoe, November 1992 and July 1995'),'src_position'(4,1,118,47)).
'comment'('lineComment'('-- Revised for FDR 2.11 May 1997'),'src_position'(5,1,166,32)).
'comment'('blockComment'('{-\xa\  This file contains a revised version, to coincide with my 1995\xa\  notes, of the level crossing gate example which was the first CSP\xa\  program to use the "tock" model of time.\xa\\xa\  The present version has (I think) a marginally better incorporation\xa\  of timing information.\xa\-}'),'src_position'(7,1,200,277)).
'comment'('lineComment'('-- The tock event represents the passing of a unit of time'),'src_position'(16,1,479,58)).
'comment'('lineComment'('-- The following are the communications between the controller process and'),'src_position'(20,1,553,74)).
'comment'('lineComment'('-- the gate process'),'src_position'(21,1,628,19)).
'comment'('lineComment'('-- where we can think of the first two as being commands to it, and the'),'src_position'(25,1,701,71)).
'comment'('lineComment'('-- last two as being confirmations from a sensor that they are up or down.'),'src_position'(26,1,773,74)).
'comment'('lineComment'('-- For reasons discussed below, we introduce a special error event:'),'src_position'(30,1,877,67)).
'comment'('lineComment'('-- To model the speed of trains, and also the separation of more than one'),'src_position'(34,1,961,73)).
'comment'('lineComment'('-- trains, we divide the track into segments that the trains can enter or'),'src_position'(35,1,1035,73)).
'comment'('lineComment'('-- leave.'),'src_position'(36,1,1109,9)).
'comment'('lineComment'('-- the number of segments including the outside one'),'src_position'(38,14,1133,51)).
'comment'('lineComment'('-- Here, segment 0 represents theo outside world, and [1,Segment) actual'),'src_position'(43,1,1257,72)).
'comment'('lineComment'('-- track segments; including the crossing, which is at'),'src_position'(44,1,1330,54)).
'comment'('lineComment'('-- This model handles two trains'),'src_position'(48,1,1397,32)).
'comment'('lineComment'('-- which can move between track segments'),'src_position'(52,1,1466,40)).
'comment'('lineComment'('-- Trains are detected when they enter the first track segment by a sensor,'),'src_position'(56,1,1546,75)).
'comment'('lineComment'('-- which drives the controller, and are also detected by a second sensor'),'src_position'(57,1,1622,72)).
'comment'('lineComment'('-- when they leave GateSeg'),'src_position'(58,1,1695,26)).
'comment'('lineComment'('-- The following gives an untimed description of Train A on track segment j'),'src_position'(65,1,1777,75)).
'comment'('lineComment'('-- A train not currently in the domain of interest is given index 0.'),'src_position'(66,1,1853,68)).
'comment'('lineComment'('-- There is no direct interference between the trains'),'src_position'(70,1,2003,53)).
'comment'('lineComment'('-- The real track segments can be occupied by one train at a time, and each'),'src_position'(74,1,2104,75)).
'comment'('lineComment'('-- time a train enters segment 1 or leaves GateSeg the sensors fire.'),'src_position'(75,1,2180,68)).
'comment'('lineComment'('-- Like the trains, the untimed track segments do not communicate with '),'src_position'(83,1,2435,71)).
'comment'('lineComment'('-- each other'),'src_position'(84,1,2507,13)).
'comment'('lineComment'('-- And we can put together the untimed network, noting that since there is'),'src_position'(88,1,2562,74)).
'comment'('lineComment'('-- no process modelling the outside world there is no need to synchronise'),'src_position'(89,1,2637,73)).
'comment'('lineComment'('-- on the enter and leave events for this area.'),'src_position'(90,1,2711,47)).
'comment'('lineComment'('-- We make assumptions about the speed of trains by placing (uniform)'),'src_position'(94,1,2826,69)).
'comment'('lineComment'('-- upper and lower "speed limits" on the track segments:'),'src_position'(95,1,2896,56)).
'comment'('lineComment'('-- MinTocksPerSeg = 3 -- make this a parameter to experiment with it'),'src_position'(97,1,2954,68)).
'comment'('lineComment'('-- inverse speed parameter, MinTocksPerSegment'),'src_position'(98,21,3043,46)).
'comment'('lineComment'('-- The speed regulators express bounds on the times between successive'),'src_position'(104,1,3141,70)).
'comment'('lineComment'('-- enter events.'),'src_position'(105,1,3212,16)).
'comment'('lineComment'('-- The following pair of processes express the timing contraint that'),'src_position'(114,1,3456,68)).
'comment'('lineComment'('-- the two sensor events occur within one time unit of a train entering'),'src_position'(115,1,3525,71)).
'comment'('lineComment'('-- or leaving the domain.'),'src_position'(116,1,3597,25)).
'comment'('lineComment'('-- The timing constraints of the trains and sensors are combined into the'),'src_position'(124,1,3833,73)).
'comment'('lineComment'('-- network as follows, noting that no speed limits are used outside the domain:'),'src_position'(125,1,3907,79)).
'comment'('lineComment'('--SpeedRegs(min) = '),'src_position'(127,1,3988,19)).
'comment'('lineComment'('--  || j : REALTRACKS @ [{|tock, enter.j, enter.(j+1)%Segments|}] SpeedReg(j,min)'),'src_position'(128,1,4008,81)).
'comment'('lineComment'('-- replicated alphabet parallel not supported'),'src_position'(129,1,4090,45)).
'comment'('lineComment'('-- The last component of our system is a controller for the gate, whose duties'),'src_position'(141,1,4378,78)).
'comment'('lineComment'('-- are to ensure that the gate is always down when there is a train on the'),'src_position'(142,1,4457,74)).
'comment'('lineComment'('-- gate, and that it is up whenever prudent.'),'src_position'(143,1,4532,44)).
'comment'('lineComment'('-- Unlike the first version of this example, here we will separate the'),'src_position'(145,1,4578,70)).
'comment'('lineComment'('-- timing assumptions about how the gate behaves into a separate process.'),'src_position'(146,1,4649,73)).
'comment'('lineComment'('-- But some timing details (relating to the intervals between sensors'),'src_position'(147,1,4723,69)).
'comment'('lineComment'('-- firing and signals being sent to the gate) are coded directly into this'),'src_position'(148,1,4793,74)).
'comment'('lineComment'('-- process, to illustrate a different coding style to that used above:'),'src_position'(149,1,4868,70)).
'comment'('lineComment'('-- When the gate is up, the controller does nothing until the sensor'),'src_position'(153,5,4964,68)).
'comment'('lineComment'('-- detects an approaching train.  '),'src_position'(154,5,5037,34)).
'comment'('lineComment'('-- In this state, time is allowed to pass arbitrarily, except that the'),'src_position'(155,5,5076,70)).
'comment'('lineComment'('-- signal for the gate to go down is sent immediately on the occurrence of'),'src_position'(156,5,5151,74)).
'comment'('lineComment'('-- the sensor event.'),'src_position'(157,5,5230,20)).
'comment'('lineComment'('-- The two states ControllerGoingDown and ControllerDown both keep'),'src_position'(161,5,5405,66)).
'comment'('lineComment'('-- a record of how many trains have to pass before the gate may go'),'src_position'(162,5,5476,66)).
'comment'('lineComment'('-- up.  '),'src_position'(163,5,5547,8)).
'comment'('lineComment'('-- Each time the sensor event occurs this count is increased.'),'src_position'(164,5,5560,61)).
'comment'('lineComment'('-- The count should not get greater than the number of trains that'),'src_position'(165,5,5626,66)).
'comment'('lineComment'('-- can legally be between the sensor and the gate (which equals'),'src_position'(166,5,5697,63)).
'comment'('lineComment'('-- the number of track segments).'),'src_position'(167,5,5765,33)).
'comment'('lineComment'('-- The ControllerGoingDown state comes to an end when the'),'src_position'(168,5,5803,57)).
'comment'('lineComment'('-- gate.down event occurs'),'src_position'(169,5,5865,25)).
'comment'('lineComment'('-- When the gate is down, the occurrence of a train entering its'),'src_position'(175,5,6113,64)).
'comment'('lineComment'('-- sector causes no alarm, and each time a train leaves the gate'),'src_position'(176,5,6182,64)).
'comment'('lineComment'('-- sector the remaining count goes down, or the gate is signalled'),'src_position'(177,5,6251,65)).
'comment'('lineComment'('-- to go up, as appropriate.'),'src_position'(178,5,6321,28)).
'comment'('lineComment'('-- Time is allowed to pass arbitrarily in this state, except that'),'src_position'(179,5,6354,65)).
'comment'('lineComment'('-- the direction to the gate to go up is instantaneous when due.'),'src_position'(180,5,6424,64)).
'comment'('lineComment'('-- When the gate is going up, the inward sensor may still fire,'),'src_position'(186,5,6755,63)).
'comment'('lineComment'('-- which means that the gate must be signalled to go down again.'),'src_position'(187,5,6823,64)).
'comment'('lineComment'('-- Otherwise the gate goes up after UpTime units.'),'src_position'(188,5,6892,49)).
'comment'('lineComment'('-- Any process will be allowed to generate an error event, and since we will'),'src_position'(195,1,7184,76)).
'comment'('lineComment'('-- be establishing that these do not occur, we can make the successor process'),'src_position'(196,1,7261,77)).
'comment'('lineComment'('-- anything we please, in this case STOP.'),'src_position'(197,1,7339,41)).
'comment'('lineComment'('-- The following are the times we assume here for the gate to go up'),'src_position'(201,1,7405,67)).
'comment'('lineComment'('-- and go down.  They represent upper bounds in each case.'),'src_position'(202,1,7473,58)).
'comment'('lineComment'('-- DownTime = 5 -- make this a parameter for experimentation'),'src_position'(204,1,7533,60)).
'comment'('lineComment'('-- Since Gate has explicitly nondeterministic behaviour, we can expect'),'src_position'(232,1,8352,70)).
'comment'('lineComment'('-- to gain by applying a compression function, such as diamond, to it;'),'src_position'(233,1,8423,70)).
'comment'('lineComment'('-- we declare a number of "transparent" compression functions'),'src_position'(234,1,8494,61)).
'comment'('lineComment'('-- Finally, we put the network together with the gate unit to give our'),'src_position'(243,1,8713,70)).
'comment'('lineComment'('-- overall system'),'src_position'(244,1,8784,17)).
'comment'('lineComment'('-- And now for specifications.  Since we have not synchronised on any'),'src_position'(250,1,8920,69)).
'comment'('lineComment'('-- error events, they would remain visible if they occurred.  Their'),'src_position'(251,1,8990,67)).
'comment'('lineComment'('-- absence can be checked with'),'src_position'(252,1,9058,30)).
'comment'('lineComment'('-- This shows that none of the explicitly caught error conditions arises,'),'src_position'(258,1,9180,73)).
'comment'('lineComment'('-- but does not show that the system has the required safety property of'),'src_position'(259,1,9254,72)).
'comment'('lineComment'('-- having no train on the GateSeg when the gate is other than down.'),'src_position'(260,1,9327,67)).
'comment'('lineComment'('-- The required specifications are slight generalisations of those'),'src_position'(262,1,9396,66)).
'comment'('lineComment'('-- discussed in specs.csp; the following notation and development is'),'src_position'(263,1,9463,68)).
'comment'('lineComment'('-- consistent with that discussed there.'),'src_position'(264,1,9532,40)).
'comment'('lineComment'('-- The above capture the sort of relationships we need between the'),'src_position'(273,1,9901,66)).
'comment'('lineComment'('-- relevant events.  If we want to stay within Failures-Divergence Refinement'),'src_position'(274,1,9968,77)).
'comment'('lineComment'('-- (as opposed to using Trace checking subtly), we need to do the following to'),'src_position'(275,1,10046,78)).
'comment'('lineComment'('-- turn them into the conditions we need:'),'src_position'(276,1,10125,41)).
'comment'('lineComment'('-- So we can form a single safety spec by conjoining these:'),'src_position'(290,1,10481,59)).
'comment'('lineComment'('-- There are a number of possible combinations which may be of interest; try'),'src_position'(294,1,10596,76)).
'comment'('lineComment'('-- An important form of liveness we have thus far ignored is that the clock'),'src_position'(306,1,11052,75)).
'comment'('lineComment'('-- is not stopped: for this it is sufficient that TimingConsistency'),'src_position'(307,1,11128,67)).
'comment'('lineComment'('-- refines TOCKS, where'),'src_position'(308,1,11196,23)).
'comment'('lineComment'('-- The following is the set of events that we cannot rely on the environment'),'src_position'(312,1,11244,76)).
'comment'('lineComment'('-- not delaying.'),'src_position'(313,1,11321,16)).
'comment'('lineComment'('-- The Safety condition completely ignored time (although, if you change some'),'src_position'(322,1,11557,77)).
'comment'('lineComment'('-- of the timing constants enough, you will find it relies upon timing for'),'src_position'(323,1,11635,74)).
'comment'('lineComment'('-- it to be satisfied).  Because of the way we are modelling time, the'),'src_position'(324,1,11710,70)).
'comment'('lineComment'('-- main liveness constraint (that the gate is up when prudent) actually'),'src_position'(325,1,11781,71)).
'comment'('lineComment'('-- becomes a safety condition (one on traces).  It is the combination of this'),'src_position'(326,1,11853,77)).
'comment'('lineComment'('-- with the TOCKS condition above (asserting that time passes) that gives'),'src_position'(327,1,11931,73)).
'comment'('lineComment'('-- it the desired meaning.'),'src_position'(328,1,12005,26)).
'comment'('lineComment'('-- We will specify that when X units of time has passed since the last'),'src_position'(330,1,12033,70)).
'comment'('lineComment'('-- train left the gate, it must be open, and remain so until another'),'src_position'(331,1,12104,68)).
'comment'('lineComment'('-- train enters the system.  This is done by the following,  which monitor'),'src_position'(332,1,12173,74)).
'comment'('lineComment'('-- the number of trains in the system and, once the last has left, no'),'src_position'(333,1,12248,69)).
'comment'('lineComment'('-- more than X units of time pass (tock events) before the gate is up.  The'),'src_position'(334,1,12318,75)).
'comment'('lineComment'('-- gate is not permitted to go down until a train is in the system.'),'src_position'(335,1,12394,67)).
'comment'('lineComment'('-- Initially the gate is up in the system, so the liveness condition'),'src_position'(348,3,12898,68)).
'comment'('lineComment'('-- takes this into account.'),'src_position'(349,3,12969,27)).
'comment'('lineComment'('-- Note that GateLive is antitonic, so for instance'),'src_position'(358,1,13272,51)).
'symbol'('tock','tock','src_span'(18,9,18,13,547,4),'Channel').
'symbol'('GateControl','GateControl','src_span'(23,10,23,21,658,11),'Datatype').
'symbol'('go_down','go_down','src_span'(23,24,23,31,672,7),'Constructor of Datatype').
'symbol'('go_up','go_up','src_span'(23,34,23,39,682,5),'Constructor of Datatype').
'symbol'('up','up','src_span'(23,42,23,44,690,2),'Constructor of Datatype').
'symbol'('down','down','src_span'(23,47,23,51,695,4),'Constructor of Datatype').
'symbol'('gate','gate','src_span'(28,9,28,13,857,4),'Channel').
'symbol'('error','error','src_span'(32,9,32,14,954,5),'Channel').
'symbol'('Segments','Segments','src_span'(38,1,38,9,1120,8),'Ident (Groundrep.)').
'symbol'('LastSeg','LastSeg','src_span'(39,1,39,8,1185,7),'Ident (Groundrep.)').
'symbol'('TRACKS','TRACKS','src_span'(40,1,40,7,1208,6),'Ident (Groundrep.)').
'symbol'('REALTRACKS','REALTRACKS','src_span'(41,1,41,11,1230,10),'Ident (Groundrep.)').
'symbol'('GateSeg','GateSeg','src_span'(46,1,46,8,1386,7),'Ident (Groundrep.)').
'symbol'('TRAINS','TRAINS','src_span'(50,10,50,16,1440,6),'Datatype').
'symbol'('Thomas','Thomas','src_span'(50,19,50,25,1449,6),'Constructor of Datatype').
'symbol'('Gordon','Gordon','src_span'(50,28,50,34,1458,6),'Constructor of Datatype').
'symbol'('enter','enter','src_span'(54,9,54,14,1516,5),'Channel').
'symbol'('leave','leave','src_span'(54,16,54,21,1523,5),'Channel').
'symbol'('sensed','sensed','src_span'(60,10,60,16,1732,6),'Datatype').
'symbol'('in','in','src_span'(60,19,60,21,1741,2),'Constructor of Datatype').
'symbol'('out','out','src_span'(60,24,60,27,1746,3),'Constructor of Datatype').
'symbol'('sensor','sensor','src_span'(62,9,62,15,1759,6),'Channel').
'symbol'('Train','Train','src_span'(68,1,68,6,1923,5),'Funktion or Process').
'symbol'('A','A','src_span'(68,7,68,8,1929,1),'Ident (Prolog Variable)').
'symbol'('j','j','src_span'(68,9,68,10,1931,1),'Ident (Prolog Variable)').
'symbol'('Trains','Trains','src_span'(72,1,72,7,2058,6),'Ident (Groundrep.)').
'symbol'('Track','Track','src_span'(77,1,77,6,2250,5),'Funktion or Process').
'symbol'('j2','j','src_span'(77,7,77,8,2256,1),'Ident (Prolog Variable)').
'symbol'('Empty','Empty','src_span'(79,5,79,10,2272,5),'Ident (Groundrep.)').
'symbol'('A2','A','src_span'(79,23,79,24,2290,1),'Ident (Prolog Variable)').
'symbol'('Full','Full','src_span'(80,5,80,9,2346,4),'Funktion or Process').
'symbol'('A3','A','src_span'(80,10,80,11,2351,1),'Ident (Prolog Variable)').
'symbol'('Tracks','Tracks','src_span'(86,1,86,7,2522,6),'Ident (Groundrep.)').
'symbol'('j3','j','src_span'(86,14,86,15,2535,1),'Ident (Prolog Variable)').
'symbol'('Network','Network','src_span'(92,1,92,8,2760,7),'Ident (Groundrep.)').
'symbol'('j4','j','src_span'(92,41,92,42,2800,1),'Ident (Prolog Variable)').
'symbol'('SlowTrain','SlowTrain','src_span'(98,1,98,10,3023,9),'Ident (Groundrep.)').
'symbol'('NormalTrain','NormalTrain','src_span'(99,1,99,12,3090,11),'Ident (Groundrep.)').
'symbol'('FastTrain','FastTrain','src_span'(100,1,100,10,3106,9),'Ident (Groundrep.)').
'symbol'('MaxTocksPerSeg','MaxTocksPerSeg','src_span'(102,1,102,15,3121,14),'Ident (Groundrep.)').
'symbol'('SpeedReg','SpeedReg','src_span'(107,1,107,9,3230,8),'Funktion or Process').
'symbol'('j5','j','src_span'(107,10,107,11,3239,1),'Ident (Prolog Variable)').
'symbol'('MinTocksPerSeg','MinTocksPerSeg','src_span'(107,12,107,26,3241,14),'Ident (Prolog Variable)').
'symbol'('Empty2','Empty','src_span'(109,5,109,10,3269,5),'Ident (Groundrep.)').
'symbol'('A4','A','src_span'(109,23,109,24,3287,1),'Ident (Prolog Variable)').
'symbol'('Full2','Full','src_span'(110,5,110,9,3321,4),'Funktion or Process').
'symbol'('n','n','src_span'(110,10,110,11,3326,1),'Ident (Prolog Variable)').
'symbol'('A5','A','src_span'(111,58,111,59,3429,1),'Ident (Prolog Variable)').
'symbol'('InSensorTiming','InSensorTiming','src_span'(118,1,118,15,3624,14),'Ident (Groundrep.)').
'symbol'('A6','A','src_span'(119,26,119,27,3689,1),'Ident (Prolog Variable)').
'symbol'('OutSensorTiming','OutSensorTiming','src_span'(121,1,121,16,3723,15),'Ident (Groundrep.)').
'symbol'('A7','A','src_span'(122,33,122,34,3797,1),'Ident (Prolog Variable)').
'symbol'('SpeedRegs','SpeedRegs','src_span'(131,1,131,10,4137,9),'Funktion or Process').
'symbol'('min','min','src_span'(131,11,131,14,4147,3),'Ident (Prolog Variable)').
'symbol'('SensorTiming','SensorTiming','src_span'(134,1,134,13,4161,12),'Ident (Groundrep.)').
'symbol'('NetworkTiming','NetworkTiming','src_span'(136,1,136,14,4219,13),'Funktion or Process').
'symbol'('min2','min','src_span'(136,15,136,18,4233,3),'Ident (Prolog Variable)').
'symbol'('TimedNetwork','TimedNetwork','src_span'(138,1,138,13,4291,12),'Funktion or Process').
'symbol'('min3','min','src_span'(138,14,138,17,4304,3),'Ident (Prolog Variable)').
'symbol'('Controller','Controller','src_span'(151,1,151,11,4940,10),'Ident (Groundrep.)').
'symbol'('ControllerUp','ControllerUp','src_span'(158,5,158,17,5255,12),'Ident (Groundrep.)').
'symbol'('ControllerGoingDown','ControllerGoingDown','src_span'(170,5,170,24,5895,19),'Funktion or Process').
'symbol'('n2','n','src_span'(170,25,170,26,5915,1),'Ident (Prolog Variable)').
'symbol'('ControllerDown','ControllerDown','src_span'(181,5,181,19,6493,14),'Funktion or Process').
'symbol'('n3','n','src_span'(181,20,181,21,6508,1),'Ident (Prolog Variable)').
'symbol'('ControllerGoingUp','ControllerGoingUp','src_span'(189,5,189,22,6946,17),'Ident (Groundrep.)').
'symbol'('ERROR','ERROR','src_span'(199,1,199,6,7382,5),'Ident (Groundrep.)').
'symbol'('VeryFastGate','VeryFastGate','src_span'(205,1,205,13,7594,12),'Ident (Groundrep.)').
'symbol'('FastGate','FastGate','src_span'(206,1,206,9,7611,8),'Ident (Groundrep.)').
'symbol'('NormalGate','NormalGate','src_span'(207,1,207,11,7624,10),'Ident (Groundrep.)').
'symbol'('SlowGate','SlowGate','src_span'(208,1,208,9,7639,8),'Ident (Groundrep.)').
'symbol'('UpTime','UpTime','src_span'(210,1,210,7,7653,6),'Ident (Groundrep.)').
'symbol'('Gate','Gate','src_span'(212,1,212,5,7665,4),'Funktion or Process').
'symbol'('DownTime','DownTime','src_span'(212,6,212,14,7670,8),'Ident (Prolog Variable)').
'symbol'('GateUp','GateUp','src_span'(214,5,214,11,7692,6),'Ident (Groundrep.)').
'symbol'('GateGoingDown','GateGoingDown','src_span'(217,5,217,18,7793,13),'Funktion or Process').
'symbol'('n4','n','src_span'(217,19,217,20,7807,1),'Ident (Prolog Variable)').
'symbol'('GateDown','GateDown','src_span'(222,5,222,13,7980,8),'Ident (Groundrep.)').
'symbol'('GateGoingUp','GateGoingUp','src_span'(225,5,225,16,8089,11),'Funktion or Process').
'symbol'('n5','n','src_span'(225,17,225,18,8101,1),'Ident (Prolog Variable)').
'symbol'('sbisim','sbisim','src_span'(236,13,236,19,8569,6),'Transparent function').
'symbol'('normalise','normalise','src_span'(237,13,237,22,8588,9),'Transparent function').
'symbol'('explicate','explicate','src_span'(238,13,238,22,8610,9),'Transparent function').
'symbol'('diamond','diamond','src_span'(239,13,239,20,8632,7),'Transparent function').
'symbol'('GateAndController','GateAndController','src_span'(241,1,241,18,8641,17),'Funktion or Process').
'symbol'('dt','dt','src_span'(241,19,241,21,8659,2),'Ident (Prolog Variable)').
'symbol'('System','System','src_span'(246,1,246,7,8803,6),'Funktion or Process').
'symbol'('invmaxspeed','invmaxspeed','src_span'(246,8,246,19,8810,11),'Ident (Prolog Variable)').
'symbol'('gatedowntime','gatedowntime','src_span'(246,20,246,32,8822,12),'Ident (Prolog Variable)').
'symbol'('NoError','NoError','src_span'(254,1,254,8,9090,7),'Ident (Groundrep.)').
'symbol'('diff','diff','src_span'(254,17,254,21,9106,4),'BuiltIn primitive').
'symbol'('SETBETWEENx','SETBETWEENx','src_span'(266,1,266,12,9574,11),'Funktion or Process').
'symbol'('EN','EN','src_span'(266,13,266,15,9586,2),'Ident (Prolog Variable)').
'symbol'('DIS','DIS','src_span'(266,16,266,19,9589,3),'Ident (Prolog Variable)').
'symbol'('C','C','src_span'(266,20,266,21,9593,1),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(266,28,266,29,9601,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(267,32,267,33,9667,1),'Ident (Prolog Variable)').
'symbol'('SETOUTSIDEx','SETOUTSIDEx','src_span'(269,1,269,12,9704,11),'Funktion or Process').
'symbol'('DIS2','DIS','src_span'(269,13,269,16,9716,3),'Ident (Prolog Variable)').
'symbol'('EN2','EN','src_span'(269,17,269,19,9720,2),'Ident (Prolog Variable)').
'symbol'('C2','C','src_span'(269,20,269,21,9723,1),'Ident (Prolog Variable)').
'symbol'('c','c','src_span'(269,29,269,30,9732,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(270,32,270,33,9797,1),'Ident (Prolog Variable)').
'symbol'('x4','x','src_span'(271,32,271,33,9864,1),'Ident (Prolog Variable)').
'symbol'('EnterWhenDown','EnterWhenDown','src_span'(278,1,278,14,10168,13),'Ident (Groundrep.)').
'symbol'('GateStillWhenTrain','GateStillWhenTrain','src_span'(285,1,285,19,10340,18),'Ident (Groundrep.)').
'symbol'('Safety','Safety','src_span'(292,1,292,7,10542,6),'Ident (Groundrep.)').
'symbol'('TOCKS','TOCKS','src_span'(310,1,310,6,11221,5),'Ident (Groundrep.)').
'symbol'('Delayable','Delayable','src_span'(315,1,315,10,11339,9),'Ident (Groundrep.)').
'symbol'('NonTock','NonTock','src_span'(316,1,316,8,11363,7),'Ident (Groundrep.)').
'symbol'('TimingConsistency','TimingConsistency','src_span'(317,1,317,18,11393,17),'Funktion or Process').
'symbol'('ts','ts','src_span'(317,19,317,21,11411,2),'Ident (Prolog Variable)').
'symbol'('gs','gs','src_span'(317,22,317,24,11414,2),'Ident (Prolog Variable)').
'symbol'('Liveness','Liveness','src_span'(337,1,337,9,12463,8),'Funktion or Process').
'symbol'('X','X','src_span'(337,10,337,11,12472,1),'Ident (Prolog Variable)').
'symbol'('Idle','Idle','src_span'(339,5,339,9,12487,4),'Ident (Groundrep.)').
'symbol'('Busy','Busy','src_span'(341,5,341,9,12543,4),'Funktion or Process').
'symbol'('n6','n','src_span'(341,10,341,11,12548,1),'Ident (Prolog Variable)').
'symbol'('UpBefore','UpBefore','src_span'(345,5,345,13,12746,8),'Funktion or Process').
'symbol'('m','m','src_span'(345,14,345,15,12755,1),'Ident (Prolog Variable)').
'symbol'('x5','x','src_span'(346,24,346,25,12814,1),'Ident (Prolog Variable)').
'symbol'('GateLive','GateLive','src_span'(352,1,352,9,13029,8),'Funktion or Process').
'symbol'('X2','X','src_span'(352,10,352,11,13038,1),'Ident (Prolog Variable)').