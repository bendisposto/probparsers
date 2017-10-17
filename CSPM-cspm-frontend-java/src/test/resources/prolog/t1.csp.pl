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
'bindval'('SensorTiming','sharing'('closure'(['tock']),'val_of'('InSensorTiming','src_span'(135,16,135,30,4272,14)),'val_of'('OutSensorTiming','src_span'(135,44,135,59,4300,15)),'src_span'(135,31,135,43,4287,12)),'src_span'(135,1,135,59,4257,58)).
'agent'('NetworkTiming'(_min2),'sharing'('closure'(['tock','dotTuple'(['enter','int'(1)])]),'agent_call'('src_span'(137,22,137,31,4338,9),'SpeedRegs',[_min2]),'val_of'('SensorTiming','src_span'(137,59,137,71,4375,12)),'src_span'(137,37,137,58,4353,21)),'src_span'(137,37,137,58,4353,21)).
'agent'('TimedNetwork'(_min3),'sharing'('closure'(['enter','sensor','dotTuple'(['leave','val_of'('GateSeg','src_span'(140,36,140,43,4444,7))])]),'val_of'('Network','src_span'(140,3,140,10,4411,7)),'agent_call'('src_span'(140,48,140,61,4456,13),'NetworkTiming',[_min3]),'src_span'(140,11,140,47,4419,36)),'src_span'(140,11,140,47,4419,36)).
'bindval'('Controller','let'(['bindval'('ControllerUp','[]'('[]'('prefix'('src_span'(159,20,159,29,5368,9),[],'dotTuple'(['sensor','in']),'prefix'('src_span'(159,33,159,37,5381,4),['out'('go_down')],'gate','agent_call'('src_span'(159,49,159,68,5397,19),'ControllerGoingDown',['int'(1)]),'src_span'(159,46,159,48,5393,34)),'src_span'(159,30,159,32,5377,51)),'prefix'('src_span'(160,20,160,30,5439,10),[],'dotTuple'(['sensor','out']),'val_of'('ERROR','src_span'(160,34,160,39,5453,5)),'src_span'(160,31,160,33,5449,19)),'src_span_operator'('no_loc_info_available','src_span'(160,17,160,19,5436,2))),'prefix'('src_span'(161,20,161,24,5478,4),[],'tock','val_of'('ControllerUp','src_span'(161,28,161,40,5486,12)),'src_span'(161,25,161,27,5482,20)),'src_span_operator'('no_loc_info_available','src_span'(161,17,161,19,5475,2))),'src_span'(159,5,161,40,5353,145)),'agent'('ControllerGoingDown'(_n2),'[]'('[]'('[]'('ifte'('<'('val_of'('GateSeg','src_span'(172,14,172,21,6031,7)),_n2),'val_of'('ERROR','src_span'(172,31,172,36,6048,5)),'prefix'('src_span'(172,42,172,51,6059,9),[],'dotTuple'(['sensor','in']),'agent_call'('src_span'(172,55,172,74,6072,19),'ControllerGoingDown',['+'(_n2,'int'(1))]),'src_span'(172,52,172,54,6068,37)),'no_loc_info_available','no_loc_info_available','src_span'(172,37,172,41,6053,48)),'prefix'('src_span'(173,10,173,19,6107,9),[],'dotTuple'(['gate','down']),'agent_call'('src_span'(173,23,173,37,6120,14),'ControllerDown',[_n2]),'src_span'(173,20,173,22,6116,30)),'src_span_operator'('no_loc_info_available','src_span'(173,7,173,9,6104,2))),'prefix'('src_span'(174,10,174,14,6147,4),[],'tock','agent_call'('src_span'(174,18,174,37,6155,19),'ControllerGoingDown',[_n2]),'src_span'(174,15,174,17,6151,30)),'src_span_operator'('no_loc_info_available','src_span'(174,7,174,9,6144,2))),'prefix'('src_span'(175,10,175,20,6187,10),[],'dotTuple'(['sensor','out']),'val_of'('ERROR','src_span'(175,24,175,29,6201,5)),'src_span'(175,21,175,23,6197,19)),'src_span_operator'('no_loc_info_available','src_span'(175,7,175,9,6184,2))),'no_loc_info_available'),'agent'('ControllerDown'(_n3),'[]'('[]'('ifte'('<'('val_of'('GateSeg','src_span'(183,14,183,21,6624,7)),_n3),'val_of'('ERROR','src_span'(183,31,183,36,6641,5)),'prefix'('src_span'(183,42,183,51,6652,9),[],'dotTuple'(['sensor','in']),'agent_call'('src_span'(183,55,183,69,6665,14),'ControllerDown',['+'(_n3,'int'(1))]),'src_span'(183,52,183,54,6661,32)),'no_loc_info_available','no_loc_info_available','src_span'(183,37,183,41,6646,43)),'prefix'('src_span'(184,10,184,20,6695,10),[],'dotTuple'(['sensor','out']),'ifte'('=='(_n3,'int'(1)),'prefix'('src_span'(184,38,184,42,6723,4),['out'('go_up')],'gate','val_of'('ControllerGoingUp','src_span'(184,52,184,69,6737,17)),'src_span'(184,49,184,51,6733,27)),'agent_call'('src_span'(185,38,185,52,6793,14),'ControllerDown',['-'(_n3,'int'(1))]),'no_loc_info_available','no_loc_info_available','src_span'(184,70,185,37,6754,89)),'src_span'(184,21,184,23,6705,118)),'src_span_operator'('no_loc_info_available','src_span'(184,7,184,9,6692,2))),'prefix'('src_span'(186,10,186,14,6823,4),[],'tock','agent_call'('src_span'(186,18,186,32,6831,14),'ControllerDown',[_n3]),'src_span'(186,15,186,17,6827,25)),'src_span_operator'('no_loc_info_available','src_span'(186,7,186,9,6820,2))),'no_loc_info_available'),'bindval'('ControllerGoingUp','[]'('[]'('[]'('prefix'('src_span'(190,26,190,30,7065,4),['out'('up')],'gate','val_of'('ControllerUp','src_span'(190,37,190,49,7076,12)),'src_span'(190,34,190,36,7072,19)),'prefix'('src_span'(191,25,191,29,7113,4),[],'tock','val_of'('ControllerGoingUp','src_span'(191,33,191,50,7121,17)),'src_span'(191,30,191,32,7117,25)),'src_span_operator'('no_loc_info_available','src_span'(191,22,191,24,7110,2))),'prefix'('src_span'(192,25,192,34,7163,9),[],'dotTuple'(['sensor','in']),'prefix'('src_span'(192,38,192,42,7176,4),['out'('go_down')],'gate','agent_call'('src_span'(192,54,192,73,7192,19),'ControllerGoingDown',['int'(1)]),'src_span'(192,51,192,53,7188,34)),'src_span'(192,35,192,37,7172,51)),'src_span_operator'('no_loc_info_available','src_span'(192,22,192,24,7160,2))),'prefix'('src_span'(193,25,193,35,7239,10),[],'dotTuple'(['sensor','out']),'val_of'('ERROR','src_span'(193,39,193,44,7253,5)),'src_span'(193,36,193,38,7249,19)),'src_span_operator'('no_loc_info_available','src_span'(193,22,193,24,7236,2))),'src_span'(190,5,193,44,7044,214))],'val_of'('ControllerUp','src_span'(194,10,194,22,7268,12))),'src_span'(152,1,194,22,5038,2242)).
'bindval'('ERROR','prefix'('src_span'(200,9,200,14,7488,5),[],'error','stop'('src_span'(200,18,200,22,7497,4)),'src_span'(200,15,200,17,7493,13)),'src_span'(200,1,200,22,7480,21)).
'bindval'('VeryFastGate','int'(3),'src_span'(206,1,206,17,7692,16)).
'bindval'('FastGate','int'(4),'src_span'(207,1,207,13,7709,12)).
'bindval'('NormalGate','int'(5),'src_span'(208,1,208,15,7722,14)).
'bindval'('SlowGate','int'(6),'src_span'(209,1,209,13,7737,12)).
'bindval'('UpTime','int'(2),'src_span'(211,1,211,11,7751,10)).
'agent'('Gate'(_DownTime),'let'(['bindval'('GateUp','[]'('[]'('prefix'('src_span'(215,14,215,24,7799,10),[],'dotTuple'(['gate','go_up']),'val_of'('GateUp','src_span'(215,28,215,34,7813,6)),'src_span'(215,25,215,27,7809,20)),'prefix'('src_span'(216,14,216,26,7833,12),[],'dotTuple'(['gate','go_down']),'agent_call'('src_span'(216,30,216,43,7849,13),'GateGoingDown',['int'(0)]),'src_span'(216,27,216,29,7845,32)),'src_span_operator'('no_loc_info_available','src_span'(216,11,216,13,7830,2))),'prefix'('src_span'(217,7,217,11,7872,4),[],'tock','val_of'('GateUp','src_span'(217,15,217,21,7880,6)),'src_span'(217,12,217,14,7876,14)),'src_span_operator'('no_loc_info_available','src_span'(217,4,217,6,7869,2))),'src_span'(215,5,217,21,7790,96)),'agent'('GateGoingDown'(_n4),'[]'('prefix'('src_span'(219,10,219,22,7919,12),[],'dotTuple'(['gate','go_down']),'agent_call'('src_span'(219,26,219,39,7935,13),'GateGoingDown',[_n4]),'src_span'(219,23,219,25,7931,32)),'ifte'('=='(_n4,_DownTime),'prefix'('src_span'(221,15,221,24,7992,9),[],'dotTuple'(['gate','down']),'val_of'('GateDown','src_span'(221,28,221,36,8005,8)),'src_span'(221,25,221,27,8001,21)),'|~|'('prefix'('src_span'(222,8,222,17,8021,9),[],'dotTuple'(['gate','down']),'val_of'('GateDown','src_span'(222,21,222,29,8034,8)),'src_span'(222,18,222,20,8030,21)),'prefix'('src_span'(222,34,222,38,8047,4),[],'tock','agent_call'('src_span'(222,42,222,55,8055,13),'GateGoingDown',['+'(_n4,'int'(1))]),'src_span'(222,39,222,41,8051,26)),'src_span_operator'('no_loc_info_available','src_span'(222,30,222,33,8043,3))),'no_loc_info_available','no_loc_info_available','no_loc_info_available'),'src_span_operator'('no_loc_info_available','src_span'(220,7,220,9,7958,2))),'no_loc_info_available'),'bindval'('GateDown','[]'('[]'('prefix'('src_span'(223,16,223,28,8089,12),[],'dotTuple'(['gate','go_down']),'val_of'('GateDown','src_span'(223,32,223,40,8105,8)),'src_span'(223,29,223,31,8101,24)),'prefix'('src_span'(224,16,224,26,8129,10),[],'dotTuple'(['gate','go_up']),'agent_call'('src_span'(224,30,224,41,8143,11),'GateGoingUp',['int'(0)]),'src_span'(224,27,224,29,8139,28)),'src_span_operator'('no_loc_info_available','src_span'(224,13,224,15,8126,2))),'prefix'('src_span'(225,9,225,13,8166,4),[],'tock','val_of'('GateDown','src_span'(225,17,225,25,8174,8)),'src_span'(225,14,225,16,8170,16)),'src_span_operator'('no_loc_info_available','src_span'(225,6,225,8,8163,2))),'src_span'(223,5,225,25,8078,104)),'agent'('GateGoingUp'(_n5),'[]'('[]'('prefix'('src_span'(226,22,226,32,8204,10),[],'dotTuple'(['gate','go_up']),'agent_call'('src_span'(226,36,226,47,8218,11),'GateGoingUp',[_n5]),'src_span'(226,33,226,35,8214,28)),'prefix'('src_span'(227,22,227,34,8254,12),[],'dotTuple'(['gate','go_down']),'agent_call'('src_span'(227,38,227,51,8270,13),'GateGoingDown',['int'(0)]),'src_span'(227,35,227,37,8266,32)),'src_span_operator'('no_loc_info_available','src_span'(227,19,227,21,8251,2))),'ifte'('=='(_n5,'val_of'('UpTime','src_span'(228,30,228,36,8316,6))),'prefix'('src_span'(229,27,229,34,8349,7),[],'dotTuple'(['gate','up']),'val_of'('GateUp','src_span'(229,38,229,44,8360,6)),'src_span'(229,35,229,37,8356,17)),'|~|'('prefix'('src_span'(230,20,230,27,8386,7),[],'dotTuple'(['gate','up']),'val_of'('GateUp','src_span'(230,31,230,37,8397,6)),'src_span'(230,28,230,30,8393,17)),'prefix'('src_span'(230,42,230,46,8408,4),[],'tock','agent_call'('src_span'(230,50,230,61,8416,11),'GateGoingUp',['+'(_n5,'int'(1))]),'src_span'(230,47,230,49,8412,24)),'src_span_operator'('no_loc_info_available','src_span'(230,38,230,41,8404,3))),'no_loc_info_available','no_loc_info_available','no_loc_info_available'),'src_span_operator'('no_loc_info_available','src_span'(228,19,228,21,8305,2))),'no_loc_info_available')],'val_of'('GateUp','src_span'(231,10,231,16,8442,6))),'src_span'(214,3,231,16,7782,666)).
'cspTransparent'(['sbisim']).
'cspTransparent'(['normalise']).
'cspTransparent'(['explicate']).
'cspTransparent'(['diamond']).
'agent'('GateAndController'(_dt),'sharing'('closure'(['tock','gate']),'val_of'('Controller','src_span'(242,25,242,35,8763,10)),'agent_call'('src_span'(242,54,242,61,8792,7),'diamond',['agent_call'('src_span'(242,62,242,66,8800,4),'Gate',[_dt])]),'src_span'(242,36,242,53,8774,17)),'src_span'(242,36,242,53,8774,17)).
'agent'('System'(_invmaxspeed,_gatedowntime),'sharing'('closure'(['sensor','tock']),'agent_call'('src_span'(248,3,248,15,8938,12),'TimedNetwork',[_invmaxspeed]),'agent_call'('src_span'(248,49,248,66,8984,17),'GateAndController',[_gatedowntime]),'src_span'(248,29,248,48,8964,19)),'src_span'(248,29,248,48,8964,19)).
'bindval'('NoError','builtin_call'('CHAOS'('src_span'(255,11,255,40,9198,29),'agent_call'('src_span'(255,17,255,21,9204,4),'diff',['Events','closure'(['error'])]))),'src_span'(255,1,255,40,9188,39)).
'assertRef'('False','val_of'('NoError','src_span'(257,8,257,15,9236,7)),'Trace','agent_call'('src_span'(257,20,257,26,9248,6),'System',['val_of'('NormalTrain','src_span'(257,27,257,38,9255,11)),'val_of'('NormalGate','src_span'(257,39,257,49,9267,10))]),'src_span'(257,1,257,50,9229,49)).
'agent'('SETBETWEENx'(_EN,_DIS,_C),'[]'('repChoice'(['comprehensionGenerator'(_x,_EN)],'prefix'('src_span'(267,35,267,36,9708,1),[],_x,'agent_call'('src_span'(267,40,267,51,9713,11),'SETOUTSIDEx',[_DIS,_EN,_C]),'src_span'(267,37,267,39,9709,26)),'src_span'(267,28,267,34,9701,6)),'repChoice'(['comprehensionGenerator'(_x2,_DIS)],'prefix'('src_span'(268,40,268,41,9775,1),[],_x2,'agent_call'('src_span'(268,45,268,56,9780,11),'SETBETWEENx',[_EN,_DIS,_C]),'src_span'(268,42,268,44,9776,26)),'src_span'(268,32,268,39,9767,7)),'src_span_operator'('no_loc_info_available','src_span'(268,25,268,27,9760,2))),'no_loc_info_available').
'agent'('SETOUTSIDEx'(_DIS2,_EN2,_C2),'[]'('[]'('repChoice'(['comprehensionGenerator'(_c,_C2)],'prefix'('src_span'(270,35,270,36,9838,1),[],_c,'agent_call'('src_span'(270,40,270,51,9843,11),'SETOUTSIDEx',[_DIS2,_EN2,_C2]),'src_span'(270,37,270,39,9839,26)),'src_span'(270,29,270,34,9832,5)),'repChoice'(['comprehensionGenerator'(_x3,_EN2)],'prefix'('src_span'(271,40,271,41,9905,1),[],_x3,'agent_call'('src_span'(271,45,271,56,9910,11),'SETOUTSIDEx',[_DIS2,_EN2,_C2]),'src_span'(271,42,271,44,9906,26)),'src_span'(271,32,271,39,9897,7)),'src_span_operator'('no_loc_info_available','src_span'(271,25,271,27,9890,2))),'repChoice'(['comprehensionGenerator'(_x4,_DIS2)],'prefix'('src_span'(272,40,272,41,9972,1),[],_x4,'agent_call'('src_span'(272,45,272,56,9977,11),'SETBETWEENx',[_EN2,_DIS2,_C2]),'src_span'(272,42,272,44,9973,26)),'src_span'(272,32,272,39,9964,7)),'src_span_operator'('no_loc_info_available','src_span'(272,25,272,27,9957,2))),'no_loc_info_available').
'bindval'('EnterWhenDown','sharing'('closure'(['gate','dotTuple'(['enter','val_of'('GateSeg','src_span'(285,19,285,26,10505,7))])]),'agent_call'('src_span'(282,3,282,14,10382,11),'SETBETWEENx',['setExp'('rangeEnum'(['dotTuple'(['gate','down'])])),'setExp'('rangeEnum'(['dotTuple'(['gate','up']),'dotTuple'(['gate','go_up']),'dotTuple'(['gate','go_down'])])),'setExp'('rangeEnum'(['dotTuple'(['enter','val_of'('GateSeg','src_span'(284,22,284,29,10477,7))])]))]),'builtin_call'('CHAOS'('src_span'(286,3,286,16,10519,13),'Events')),'src_span'(285,3,285,30,10489,27)),'src_span'(279,1,286,16,10268,264)).
'bindval'('GateStillWhenTrain','sharing'('closure'(['gate','dotTuple'(['enter','val_of'('GateSeg','src_span'(290,18,290,25,10632,7))]),'dotTuple'(['leave','val_of'('GateSeg','src_span'(290,32,290,39,10646,7))])]),'agent_call'('src_span'(289,3,289,14,10557,11),'SETOUTSIDEx',['closure'(['dotTuple'(['enter','val_of'('GateSeg','src_span'(289,23,289,30,10577,7))])]),'closure'(['dotTuple'(['leave','val_of'('GateSeg','src_span'(289,41,289,48,10595,7))])]),'closure'(['gate'])]),'builtin_call'('CHAOS'('src_span'(291,3,291,16,10660,13),'Events')),'src_span'(290,3,290,43,10617,40)),'src_span'(288,1,291,16,10534,139)).
'bindval'('Safety','sharing'('Events','val_of'('EnterWhenDown','src_span'(295,10,295,23,10745,13)),'val_of'('GateStillWhenTrain','src_span'(295,35,295,53,10770,18)),'src_span'(295,24,295,34,10759,10)),'src_span'(295,1,295,53,10736,52)).
'assertRef'('False','val_of'('Safety','src_span'(299,8,299,14,10875,6)),'Trace','agent_call'('src_span'(299,19,299,25,10886,6),'System',['val_of'('SlowTrain','src_span'(299,26,299,35,10893,9)),'val_of'('NormalGate','src_span'(299,36,299,46,10903,10))]),'src_span'(299,1,299,47,10868,46)).
'assertRef'('False','val_of'('Safety','src_span'(300,8,300,14,10922,6)),'Trace','agent_call'('src_span'(300,19,300,25,10933,6),'System',['val_of'('NormalTrain','src_span'(300,26,300,37,10940,11)),'val_of'('NormalGate','src_span'(300,38,300,48,10952,10))]),'src_span'(300,1,300,49,10915,48)).
'assertRef'('False','val_of'('NoError','src_span'(301,8,301,15,10971,7)),'Trace','agent_call'('src_span'(301,20,301,26,10983,6),'System',['val_of'('FastTrain','src_span'(301,27,301,36,10990,9)),'val_of'('SlowGate','src_span'(301,37,301,45,11000,8))]),'src_span'(301,1,301,46,10964,45)).
'assertRef'('False','val_of'('Safety','src_span'(302,8,302,14,11017,6)),'Trace','agent_call'('src_span'(302,19,302,25,11028,6),'System',['val_of'('FastTrain','src_span'(302,26,302,35,11035,9)),'val_of'('NormalGate','src_span'(302,36,302,46,11045,10))]),'src_span'(302,1,302,47,11010,46)).
'assertRef'('False','val_of'('NoError','src_span'(303,8,303,15,11064,7)),'Trace','agent_call'('src_span'(303,20,303,26,11076,6),'System',['val_of'('FastTrain','src_span'(303,27,303,36,11083,9)),'val_of'('NormalGate','src_span'(303,37,303,47,11093,10))]),'src_span'(303,1,303,48,11057,47)).
'assertRef'('False','val_of'('Safety','src_span'(304,8,304,14,11112,6)),'Trace','agent_call'('src_span'(304,19,304,25,11123,6),'System',['val_of'('SlowTrain','src_span'(304,26,304,35,11130,9)),'val_of'('SlowGate','src_span'(304,36,304,44,11140,8))]),'src_span'(304,1,304,45,11105,44)).
'assertRef'('False','val_of'('Safety','src_span'(305,8,305,14,11157,6)),'Trace','agent_call'('src_span'(305,19,305,25,11168,6),'System',['val_of'('FastTrain','src_span'(305,26,305,35,11175,9)),'val_of'('FastGate','src_span'(305,36,305,44,11185,8))]),'src_span'(305,1,305,45,11150,44)).
'assertRef'('False','val_of'('Safety','src_span'(306,8,306,14,11202,6)),'Trace','agent_call'('src_span'(306,19,306,25,11213,6),'System',['val_of'('FastTrain','src_span'(306,26,306,35,11220,9)),'val_of'('VeryFastGate','src_span'(306,36,306,48,11230,12))]),'src_span'(306,1,306,49,11195,48)).
'bindval'('TOCKS','prefix'('src_span'(313,9,313,13,11423,4),[],'tock','val_of'('TOCKS','src_span'(313,17,313,22,11431,5)),'src_span'(313,14,313,16,11427,13)),'src_span'(313,1,313,22,11415,21)).
'bindval'('Delayable','closure'(['dotTuple'(['enter','int'(1)])]),'src_span'(318,1,318,24,11533,23)).
'bindval'('NonTock','agent_call'('src_span'(319,11,319,15,11567,4),'diff',['Events','setExp'('rangeEnum'(['tock']))]),'src_span'(319,1,319,30,11557,29)).
'agent'('TimingConsistency'(_ts,_gs),'agent_call'('src_span'(321,3,321,12,11616,9),'explicate',['\x5c\'('sharing'('val_of'('Delayable','src_span'(321,28,321,37,11641,9)),'agent_call'('src_span'(321,13,321,19,11626,6),'System',[_ts,_gs]),'agent_call'('src_span'(321,39,321,48,11652,9),'normalise',['builtin_call'('CHAOS'('src_span'(321,49,321,65,11662,16),'val_of'('Delayable','src_span'(321,55,321,64,11668,9))))]),'src_span'(321,26,321,39,11639,13)),'val_of'('NonTock','src_span'(321,67,321,74,11680,7)),'src_span_operator'('no_loc_info_available','src_span'(321,66,321,67,11679,1)))]),'src_span'(321,3,321,75,11616,72)).
'assertRef'('False','val_of'('TOCKS','src_span'(323,8,323,13,11697,5)),'FailureDivergence','agent_call'('src_span'(323,19,323,36,11708,17),'TimingConsistency',['val_of'('NormalTrain','src_span'(323,37,323,48,11726,11)),'val_of'('NormalGate','src_span'(323,49,323,59,11738,10))]),'src_span'(323,1,323,60,11690,59)).
'agent'('Liveness'(_X),'let'(['bindval'('Idle','[]'('prefix'('src_span'(342,12,342,16,12688,4),[],'tock','val_of'('Idle','src_span'(342,20,342,24,12696,4)),'src_span'(342,17,342,19,12692,12)),'prefix'('src_span'(343,12,343,19,12712,7),['in'(_)],'dotTuple'(['enter','int'(1)]),'agent_call'('src_span'(343,25,343,29,12725,4),'Busy',['int'(1)]),'src_span'(343,22,343,24,12721,13)),'src_span_operator'('no_loc_info_available','src_span'(343,9,343,11,12709,2))),'src_span'(342,5,343,32,12681,51)),'agent'('Busy'(_n6),'[]'('[]'('[]'('prefix'('src_span'(344,15,344,19,12747,4),[],'tock','agent_call'('src_span'(344,23,344,27,12755,4),'Busy',[_n6]),'src_span'(344,20,344,22,12751,15)),'prefix'('src_span'(345,15,345,22,12777,7),['in'(_)],'dotTuple'(['enter','int'(1)]),'agent_call'('src_span'(345,28,345,32,12790,4),'Busy',['ifte'('<'(_n6,'val_of'('GateSeg','src_span'(345,40,345,47,12802,7))),'+'(_n6,'int'(1)),_n6,'no_loc_info_available','no_loc_info_available','src_span'(345,59,345,63,12820,12))]),'src_span'(345,25,345,27,12786,44)),'src_span_operator'('no_loc_info_available','src_span'(345,12,345,14,12774,2))),'prefix'('src_span'(346,15,346,28,12843,13),['in'(_)],'dotTuple'(['leave','val_of'('GateSeg','src_span'(346,21,346,28,12849,7))]),'ifte'('=='(_n6,'int'(1)),'agent_call'('src_span'(346,48,346,56,12876,8),'UpBefore',[_X]),'agent_call'('src_span'(346,65,346,69,12893,4),'Busy',['-'(_n6,'int'(1))]),'no_loc_info_available','no_loc_info_available','src_span'(346,60,346,64,12887,26)),'src_span'(346,31,346,33,12858,47)),'src_span_operator'('no_loc_info_available','src_span'(346,12,346,14,12840,2))),'prefix'('src_span'(347,15,347,19,12918,4),['in'(_)],'gate','agent_call'('src_span'(347,25,347,29,12928,4),'Busy',[_n6]),'src_span'(347,22,347,24,12924,13)),'src_span_operator'('no_loc_info_available','src_span'(347,12,347,14,12915,2))),'no_loc_info_available'),'agent'('UpBefore'(_m),'[]'('[]'('&'('!='(_m,'int'(0)),'prefix'('src_span'(348,28,348,32,12963,4),[],'tock','agent_call'('src_span'(348,36,348,44,12971,8),'UpBefore',['-'(_m,'int'(1))]),'src_span'(348,33,348,35,12967,21))),'prefix'('src_span'(349,19,349,23,13003,4),['in'(_x5)],'gate','ifte'('=='(_x5,'up'),'val_of'('Idle','src_span'(349,44,349,48,13028,4)),'agent_call'('src_span'(349,54,349,62,13038,8),'UpBefore',[_m]),'no_loc_info_available','no_loc_info_available','src_span'(349,49,349,53,13032,21)),'src_span'(349,26,349,28,13009,43)),'src_span_operator'('no_loc_info_available','src_span'(349,16,349,18,13000,2))),'prefix'('src_span'(350,19,350,26,13069,7),['in'(_)],'dotTuple'(['enter','int'(1)]),'agent_call'('src_span'(350,32,350,36,13082,4),'Busy',['int'(1)]),'src_span'(350,29,350,31,13078,13)),'src_span_operator'('no_loc_info_available','src_span'(350,16,350,18,13066,2))),'no_loc_info_available')],'val_of'('Idle','src_span'(353,10,353,14,13200,4))),'src_span'(341,3,353,14,12673,531)).
'agent'('GateLive'(_X2),'sharing'('closure'(['tock','gate','dotTuple'(['enter','int'(1)]),'dotTuple'(['leave','val_of'('GateSeg','src_span'(355,55,355,62,13277,7))])]),'agent_call'('src_span'(355,15,355,23,13237,8),'Liveness',[_X2]),'builtin_call'('CHAOS'('src_span'(355,66,355,79,13288,13),'Events')),'src_span'(355,27,355,66,13249,39)),'src_span'(355,27,355,66,13249,39)).
'assertRef'('False','agent_call'('src_span'(357,8,357,16,13310,8),'GateLive',['int'(3)]),'Trace','agent_call'('src_span'(357,24,357,30,13326,6),'System',['val_of'('NormalTrain','src_span'(357,31,357,42,13333,11)),'val_of'('NormalGate','src_span'(357,43,357,53,13345,10))]),'src_span'(357,1,357,54,13303,53)).
'assertRef'('False','agent_call'('src_span'(358,8,358,16,13364,8),'GateLive',['int'(2)]),'Trace','agent_call'('src_span'(358,24,358,30,13380,6),'System',['val_of'('NormalTrain','src_span'(358,31,358,42,13387,11)),'val_of'('NormalGate','src_span'(358,43,358,53,13399,10))]),'src_span'(358,1,358,54,13357,53)).
'assertRef'('False','agent_call'('src_span'(359,8,359,16,13418,8),'GateLive',['int'(1)]),'Trace','agent_call'('src_span'(359,24,359,30,13434,6),'System',['val_of'('NormalTrain','src_span'(359,31,359,42,13441,11)),'val_of'('NormalGate','src_span'(359,43,359,53,13453,10))]),'src_span'(359,1,359,54,13411,53)).
'assertRef'('False','agent_call'('src_span'(363,8,363,16,13526,8),'GateLive',['int'(3)]),'Trace','agent_call'('src_span'(363,24,363,32,13542,8),'GateLive',['int'(2)]),'src_span'(363,1,363,35,13519,34)).
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
'comment'('lineComment'('-- possible type-error in FDR-example'),'src_position'(133,1,4160,37)).
'comment'('lineComment'('--SensorTiming = InSensorTiming [|{tock}|] OutSensorTiming'),'src_position'(134,1,4198,58)).
'comment'('lineComment'('-- The last component of our system is a controller for the gate, whose duties'),'src_position'(142,1,4476,78)).
'comment'('lineComment'('-- are to ensure that the gate is always down when there is a train on the'),'src_position'(143,1,4555,74)).
'comment'('lineComment'('-- gate, and that it is up whenever prudent.'),'src_position'(144,1,4630,44)).
'comment'('lineComment'('-- Unlike the first version of this example, here we will separate the'),'src_position'(146,1,4676,70)).
'comment'('lineComment'('-- timing assumptions about how the gate behaves into a separate process.'),'src_position'(147,1,4747,73)).
'comment'('lineComment'('-- But some timing details (relating to the intervals between sensors'),'src_position'(148,1,4821,69)).
'comment'('lineComment'('-- firing and signals being sent to the gate) are coded directly into this'),'src_position'(149,1,4891,74)).
'comment'('lineComment'('-- process, to illustrate a different coding style to that used above:'),'src_position'(150,1,4966,70)).
'comment'('lineComment'('-- When the gate is up, the controller does nothing until the sensor'),'src_position'(154,5,5062,68)).
'comment'('lineComment'('-- detects an approaching train.  '),'src_position'(155,5,5135,34)).
'comment'('lineComment'('-- In this state, time is allowed to pass arbitrarily, except that the'),'src_position'(156,5,5174,70)).
'comment'('lineComment'('-- signal for the gate to go down is sent immediately on the occurrence of'),'src_position'(157,5,5249,74)).
'comment'('lineComment'('-- the sensor event.'),'src_position'(158,5,5328,20)).
'comment'('lineComment'('-- The two states ControllerGoingDown and ControllerDown both keep'),'src_position'(162,5,5503,66)).
'comment'('lineComment'('-- a record of how many trains have to pass before the gate may go'),'src_position'(163,5,5574,66)).
'comment'('lineComment'('-- up.  '),'src_position'(164,5,5645,8)).
'comment'('lineComment'('-- Each time the sensor event occurs this count is increased.'),'src_position'(165,5,5658,61)).
'comment'('lineComment'('-- The count should not get greater than the number of trains that'),'src_position'(166,5,5724,66)).
'comment'('lineComment'('-- can legally be between the sensor and the gate (which equals'),'src_position'(167,5,5795,63)).
'comment'('lineComment'('-- the number of track segments).'),'src_position'(168,5,5863,33)).
'comment'('lineComment'('-- The ControllerGoingDown state comes to an end when the'),'src_position'(169,5,5901,57)).
'comment'('lineComment'('-- gate.down event occurs'),'src_position'(170,5,5963,25)).
'comment'('lineComment'('-- When the gate is down, the occurrence of a train entering its'),'src_position'(176,5,6211,64)).
'comment'('lineComment'('-- sector causes no alarm, and each time a train leaves the gate'),'src_position'(177,5,6280,64)).
'comment'('lineComment'('-- sector the remaining count goes down, or the gate is signalled'),'src_position'(178,5,6349,65)).
'comment'('lineComment'('-- to go up, as appropriate.'),'src_position'(179,5,6419,28)).
'comment'('lineComment'('-- Time is allowed to pass arbitrarily in this state, except that'),'src_position'(180,5,6452,65)).
'comment'('lineComment'('-- the direction to the gate to go up is instantaneous when due.'),'src_position'(181,5,6522,64)).
'comment'('lineComment'('-- When the gate is going up, the inward sensor may still fire,'),'src_position'(187,5,6853,63)).
'comment'('lineComment'('-- which means that the gate must be signalled to go down again.'),'src_position'(188,5,6921,64)).
'comment'('lineComment'('-- Otherwise the gate goes up after UpTime units.'),'src_position'(189,5,6990,49)).
'comment'('lineComment'('-- Any process will be allowed to generate an error event, and since we will'),'src_position'(196,1,7282,76)).
'comment'('lineComment'('-- be establishing that these do not occur, we can make the successor process'),'src_position'(197,1,7359,77)).
'comment'('lineComment'('-- anything we please, in this case STOP.'),'src_position'(198,1,7437,41)).
'comment'('lineComment'('-- The following are the times we assume here for the gate to go up'),'src_position'(202,1,7503,67)).
'comment'('lineComment'('-- and go down.  They represent upper bounds in each case.'),'src_position'(203,1,7571,58)).
'comment'('lineComment'('-- DownTime = 5 -- make this a parameter for experimentation'),'src_position'(205,1,7631,60)).
'comment'('lineComment'('-- Since Gate has explicitly nondeterministic behaviour, we can expect'),'src_position'(233,1,8450,70)).
'comment'('lineComment'('-- to gain by applying a compression function, such as diamond, to it;'),'src_position'(234,1,8521,70)).
'comment'('lineComment'('-- we declare a number of "transparent" compression functions'),'src_position'(235,1,8592,61)).
'comment'('lineComment'('-- Finally, we put the network together with the gate unit to give our'),'src_position'(244,1,8811,70)).
'comment'('lineComment'('-- overall system'),'src_position'(245,1,8882,17)).
'comment'('lineComment'('-- And now for specifications.  Since we have not synchronised on any'),'src_position'(251,1,9018,69)).
'comment'('lineComment'('-- error events, they would remain visible if they occurred.  Their'),'src_position'(252,1,9088,67)).
'comment'('lineComment'('-- absence can be checked with'),'src_position'(253,1,9156,30)).
'comment'('lineComment'('-- This shows that none of the explicitly caught error conditions arises,'),'src_position'(259,1,9280,73)).
'comment'('lineComment'('-- but does not show that the system has the required safety property of'),'src_position'(260,1,9354,72)).
'comment'('lineComment'('-- having no train on the GateSeg when the gate is other than down.'),'src_position'(261,1,9427,67)).
'comment'('lineComment'('-- The required specifications are slight generalisations of those'),'src_position'(263,1,9496,66)).
'comment'('lineComment'('-- discussed in specs.csp; the following notation and development is'),'src_position'(264,1,9563,68)).
'comment'('lineComment'('-- consistent with that discussed there.'),'src_position'(265,1,9632,40)).
'comment'('lineComment'('-- The above capture the sort of relationships we need between the'),'src_position'(274,1,10001,66)).
'comment'('lineComment'('-- relevant events.  If we want to stay within Failures-Divergence Refinement'),'src_position'(275,1,10068,77)).
'comment'('lineComment'('-- (as opposed to using Trace checking subtly), we need to do the following to'),'src_position'(276,1,10146,78)).
'comment'('lineComment'('-- turn them into the conditions we need:'),'src_position'(277,1,10225,41)).
'comment'('lineComment'('--  SETBETWEENx({gate.down},'),'src_position'(280,1,10284,28)).
'comment'('lineComment'('--              {gate.up,gate.go_up,gate.go_down},  --type error !'),'src_position'(281,1,10313,66)).
'comment'('lineComment'('-- So we can form a single safety spec by conjoining these:'),'src_position'(293,1,10675,59)).
'comment'('lineComment'('-- There are a number of possible combinations which may be of interest; try'),'src_position'(297,1,10790,76)).
'comment'('lineComment'('-- An important form of liveness we have thus far ignored is that the clock'),'src_position'(309,1,11246,75)).
'comment'('lineComment'('-- is not stopped: for this it is sufficient that TimingConsistency'),'src_position'(310,1,11322,67)).
'comment'('lineComment'('-- refines TOCKS, where'),'src_position'(311,1,11390,23)).
'comment'('lineComment'('-- The following is the set of events that we cannot rely on the environment'),'src_position'(315,1,11438,76)).
'comment'('lineComment'('-- not delaying.'),'src_position'(316,1,11515,16)).
'comment'('lineComment'('-- The Safety condition completely ignored time (although, if you change some'),'src_position'(325,1,11751,77)).
'comment'('lineComment'('-- of the timing constants enough, you will find it relies upon timing for'),'src_position'(326,1,11829,74)).
'comment'('lineComment'('-- it to be satisfied).  Because of the way we are modelling time, the'),'src_position'(327,1,11904,70)).
'comment'('lineComment'('-- main liveness constraint (that the gate is up when prudent) actually'),'src_position'(328,1,11975,71)).
'comment'('lineComment'('-- becomes a safety condition (one on traces).  It is the combination of this'),'src_position'(329,1,12047,77)).
'comment'('lineComment'('-- with the TOCKS condition above (asserting that time passes) that gives'),'src_position'(330,1,12125,73)).
'comment'('lineComment'('-- it the desired meaning.'),'src_position'(331,1,12199,26)).
'comment'('lineComment'('-- We will specify that when X units of time has passed since the last'),'src_position'(333,1,12227,70)).
'comment'('lineComment'('-- train left the gate, it must be open, and remain so until another'),'src_position'(334,1,12298,68)).
'comment'('lineComment'('-- train enters the system.  This is done by the following,  which monitor'),'src_position'(335,1,12367,74)).
'comment'('lineComment'('-- the number of trains in the system and, once the last has left, no'),'src_position'(336,1,12442,69)).
'comment'('lineComment'('-- more than X units of time pass (tock events) before the gate is up.  The'),'src_position'(337,1,12512,75)).
'comment'('lineComment'('-- gate is not permitted to go down until a train is in the system.'),'src_position'(338,1,12588,67)).
'comment'('lineComment'('-- Initially the gate is up in the system, so the liveness condition'),'src_position'(351,3,13092,68)).
'comment'('lineComment'('-- takes this into account.'),'src_position'(352,3,13163,27)).
'comment'('lineComment'('-- Note that GateLive is antitonic, so for instance'),'src_position'(361,1,13466,51)).
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
'symbol'('SensorTiming','SensorTiming','src_span'(135,1,135,13,4257,12),'Ident (Groundrep.)').
'symbol'('NetworkTiming','NetworkTiming','src_span'(137,1,137,14,4317,13),'Funktion or Process').
'symbol'('min2','min','src_span'(137,15,137,18,4331,3),'Ident (Prolog Variable)').
'symbol'('TimedNetwork','TimedNetwork','src_span'(139,1,139,13,4389,12),'Funktion or Process').
'symbol'('min3','min','src_span'(139,14,139,17,4402,3),'Ident (Prolog Variable)').
'symbol'('Controller','Controller','src_span'(152,1,152,11,5038,10),'Ident (Groundrep.)').
'symbol'('ControllerUp','ControllerUp','src_span'(159,5,159,17,5353,12),'Ident (Groundrep.)').
'symbol'('ControllerGoingDown','ControllerGoingDown','src_span'(171,5,171,24,5993,19),'Funktion or Process').
'symbol'('n2','n','src_span'(171,25,171,26,6013,1),'Ident (Prolog Variable)').
'symbol'('ControllerDown','ControllerDown','src_span'(182,5,182,19,6591,14),'Funktion or Process').
'symbol'('n3','n','src_span'(182,20,182,21,6606,1),'Ident (Prolog Variable)').
'symbol'('ControllerGoingUp','ControllerGoingUp','src_span'(190,5,190,22,7044,17),'Ident (Groundrep.)').
'symbol'('ERROR','ERROR','src_span'(200,1,200,6,7480,5),'Ident (Groundrep.)').
'symbol'('VeryFastGate','VeryFastGate','src_span'(206,1,206,13,7692,12),'Ident (Groundrep.)').
'symbol'('FastGate','FastGate','src_span'(207,1,207,9,7709,8),'Ident (Groundrep.)').
'symbol'('NormalGate','NormalGate','src_span'(208,1,208,11,7722,10),'Ident (Groundrep.)').
'symbol'('SlowGate','SlowGate','src_span'(209,1,209,9,7737,8),'Ident (Groundrep.)').
'symbol'('UpTime','UpTime','src_span'(211,1,211,7,7751,6),'Ident (Groundrep.)').
'symbol'('Gate','Gate','src_span'(213,1,213,5,7763,4),'Funktion or Process').
'symbol'('DownTime','DownTime','src_span'(213,6,213,14,7768,8),'Ident (Prolog Variable)').
'symbol'('GateUp','GateUp','src_span'(215,5,215,11,7790,6),'Ident (Groundrep.)').
'symbol'('GateGoingDown','GateGoingDown','src_span'(218,5,218,18,7891,13),'Funktion or Process').
'symbol'('n4','n','src_span'(218,19,218,20,7905,1),'Ident (Prolog Variable)').
'symbol'('GateDown','GateDown','src_span'(223,5,223,13,8078,8),'Ident (Groundrep.)').
'symbol'('GateGoingUp','GateGoingUp','src_span'(226,5,226,16,8187,11),'Funktion or Process').
'symbol'('n5','n','src_span'(226,17,226,18,8199,1),'Ident (Prolog Variable)').
'symbol'('sbisim','sbisim','src_span'(237,13,237,19,8667,6),'Transparent function').
'symbol'('normalise','normalise','src_span'(238,13,238,22,8686,9),'Transparent function').
'symbol'('explicate','explicate','src_span'(239,13,239,22,8708,9),'Transparent function').
'symbol'('diamond','diamond','src_span'(240,13,240,20,8730,7),'Transparent function').
'symbol'('GateAndController','GateAndController','src_span'(242,1,242,18,8739,17),'Funktion or Process').
'symbol'('dt','dt','src_span'(242,19,242,21,8757,2),'Ident (Prolog Variable)').
'symbol'('System','System','src_span'(247,1,247,7,8901,6),'Funktion or Process').
'symbol'('invmaxspeed','invmaxspeed','src_span'(247,8,247,19,8908,11),'Ident (Prolog Variable)').
'symbol'('gatedowntime','gatedowntime','src_span'(247,20,247,32,8920,12),'Ident (Prolog Variable)').
'symbol'('NoError','NoError','src_span'(255,1,255,8,9188,7),'Ident (Groundrep.)').
'symbol'('diff','diff','src_span'(255,17,255,21,9204,4),'BuiltIn primitive').
'symbol'('SETBETWEENx','SETBETWEENx','src_span'(267,1,267,12,9674,11),'Funktion or Process').
'symbol'('EN','EN','src_span'(267,13,267,15,9686,2),'Ident (Prolog Variable)').
'symbol'('DIS','DIS','src_span'(267,16,267,19,9689,3),'Ident (Prolog Variable)').
'symbol'('C','C','src_span'(267,20,267,21,9693,1),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(267,28,267,29,9701,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(268,32,268,33,9767,1),'Ident (Prolog Variable)').
'symbol'('SETOUTSIDEx','SETOUTSIDEx','src_span'(270,1,270,12,9804,11),'Funktion or Process').
'symbol'('DIS2','DIS','src_span'(270,13,270,16,9816,3),'Ident (Prolog Variable)').
'symbol'('EN2','EN','src_span'(270,17,270,19,9820,2),'Ident (Prolog Variable)').
'symbol'('C2','C','src_span'(270,20,270,21,9823,1),'Ident (Prolog Variable)').
'symbol'('c','c','src_span'(270,29,270,30,9832,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(271,32,271,33,9897,1),'Ident (Prolog Variable)').
'symbol'('x4','x','src_span'(272,32,272,33,9964,1),'Ident (Prolog Variable)').
'symbol'('EnterWhenDown','EnterWhenDown','src_span'(279,1,279,14,10268,13),'Ident (Groundrep.)').
'symbol'('GateStillWhenTrain','GateStillWhenTrain','src_span'(288,1,288,19,10534,18),'Ident (Groundrep.)').
'symbol'('Safety','Safety','src_span'(295,1,295,7,10736,6),'Ident (Groundrep.)').
'symbol'('TOCKS','TOCKS','src_span'(313,1,313,6,11415,5),'Ident (Groundrep.)').
'symbol'('Delayable','Delayable','src_span'(318,1,318,10,11533,9),'Ident (Groundrep.)').
'symbol'('NonTock','NonTock','src_span'(319,1,319,8,11557,7),'Ident (Groundrep.)').
'symbol'('TimingConsistency','TimingConsistency','src_span'(320,1,320,18,11587,17),'Funktion or Process').
'symbol'('ts','ts','src_span'(320,19,320,21,11605,2),'Ident (Prolog Variable)').
'symbol'('gs','gs','src_span'(320,22,320,24,11608,2),'Ident (Prolog Variable)').
'symbol'('Liveness','Liveness','src_span'(340,1,340,9,12657,8),'Funktion or Process').
'symbol'('X','X','src_span'(340,10,340,11,12666,1),'Ident (Prolog Variable)').
'symbol'('Idle','Idle','src_span'(342,5,342,9,12681,4),'Ident (Groundrep.)').
'symbol'('Busy','Busy','src_span'(344,5,344,9,12737,4),'Funktion or Process').
'symbol'('n6','n','src_span'(344,10,344,11,12742,1),'Ident (Prolog Variable)').
'symbol'('UpBefore','UpBefore','src_span'(348,5,348,13,12940,8),'Funktion or Process').
'symbol'('m','m','src_span'(348,14,348,15,12949,1),'Ident (Prolog Variable)').
'symbol'('x5','x','src_span'(349,24,349,25,13008,1),'Ident (Prolog Variable)').
'symbol'('GateLive','GateLive','src_span'(355,1,355,9,13223,8),'Funktion or Process').
'symbol'('X2','X','src_span'(355,10,355,11,13232,1),'Ident (Prolog Variable)').