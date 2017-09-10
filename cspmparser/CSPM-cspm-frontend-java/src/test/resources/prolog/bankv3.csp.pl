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
'bindval'('QUEUENUM','setExp'('rangeClosed'('int'(1),'val_of'('numQueues','src_span'(9,16,9,25,261,9)))),'src_span'(9,1,9,26,246,25)).
'dataTypeDef'('CUSTOMER',['constructor'('c1'),'constructor'('c2'),'constructor'('c3'),'constructor'('c4'),'constructor'('c5'),'constructor'('c6')]).
'dataTypeDef'('STATUS',['constructor'('success'),'constructor'('fail')]).
'dataTypeDef'('QSTATUS',['constructor'('yes'),'constructor'('no')]).
'bindval'('maxLimit','int'(2),'src_span'(14,1,14,13,381,12)).
'bindval'('defaultCounter','int'(1),'src_span'(15,1,15,20,394,19)).
'bindval'('defaultCustomer','c1','src_span'(16,1,16,21,414,20)).
'bindval'('maxQueueingCustomers','-'('val_of'('maxLimit','src_span'(17,24,17,32,458,8)),'int'(1)),'src_span'(17,1,17,35,435,34)).
'bindval'('numQueues','int'(3),'src_span'(18,1,18,14,470,13)).
'channel'('enterBank','type'('dotTupleType'(['CUSTOMER']))).
'channel'('leaveBank','type'('dotTupleType'(['CUSTOMER']))).
'channel'('report','type'('dotTupleType'(['STATUS']))).
'channel'('com1','type'('dotTupleType'(['CUSTOMER']))).
'channel'('com3','type'('dotTupleType'(['CUSTOMER']))).
'channel'('canJoinResponse','type'('dotTupleType'(['STATUS']))).
'channel'('retrieveCustomer','type'('dotUnitType')).
'channel'('joinQueue','type'('dotTupleType'(['CUSTOMER']))).
'channel'('leaveQueue','type'('dotTupleType'(['val_of'('QUEUENUM','src_span'(34,22,34,30,833,8)),'CUSTOMER']))).
'channel'('queryQueueEmpty','type'('dotTupleType'(['val_of'('QUEUENUM','src_span'(35,27,35,35,877,8)),'QSTATUS']))).
'agent'('inc'(_queueNo),'+'('%'(_queueNo,'val_of'('numQueues','src_span'(38,27,38,36,922,9))),'int'(1)),'no_loc_info_available').
'agent'('dec'(_queueNo2),'+'('%'('-'(_queueNo2,'int'(2)),'val_of'('numQueues','src_span'(39,33,39,42,969,9))),'int'(1)),'no_loc_info_available').
'bindval'('CounterCtrl','agent_call'('src_span'(53,15,53,26,1334,11),'CurrentCtrl',['int'(0),'val_of'('defaultCustomer','src_span'(53,29,53,44,1348,15))]),'src_span'(53,1,53,45,1320,44)).
'agent'('CurrentCtrl'(_num,_currentCust),'[]'('agent_call'('src_span'(56,5,56,13,1403,8),'JoinCtrl',[_num,_currentCust]),'agent_call'('src_span'(56,35,56,44,1433,9),'LeaveCtrl',[_num,_currentCust]),'src_span_operator'('no_loc_info_available','src_span'(56,32,56,34,1430,2))),'no_loc_info_available').
'agent'('JoinCtrl'(_num2,_currentCust2),'&'('<'(_num2,'val_of'('maxLimit','src_span'(64,9,64,17,1637,8))),'prefix'('src_span'(64,20,64,29,1648,9),['in'(_cc)],'enterBank','[]'('[]'('&'('=='(_num2,'int'(0)),'prefix'('src_span'(65,21,65,35,1685,14),[],'dotTuple'(['report','success']),'agent_call'('src_span'(65,39,65,50,1703,11),'CurrentCtrl',['+'(_num2,'int'(1)),_cc]),'src_span'(65,36,65,38,1699,39))),'&'('bool_and'('>'(_num2,'int'(0)),'bool_not'('=='(_cc,_currentCust2))),'prefix'('src_span'(67,47,67,51,1783,4),['out'(_cc)],'com1','prefix'('src_span'(68,23,68,38,1818,15),['in'(_bb)],'canJoinResponse','prefix'('src_span'(68,48,68,57,1843,9),[],'dotTuple'(['report',_bb]),'ifte'('=='(_bb,'success'),'agent_call'('src_span'(70,27,70,38,1930,11),'CurrentCtrl',['+'(_num2,'int'(1)),_currentCust2]),'agent_call'('src_span'(72,27,72,38,2018,11),'CurrentCtrl',[_num2,_currentCust2]),'src_span'(69,25,69,43,1880,18),'src_span'(69,44,70,26,1898,77),'src_span'(70,58,72,26,1960,116)),'src_span'(68,58,69,24,1852,203)),'src_span'(68,45,68,47,1839,211)),'src_span'(67,56,68,22,1791,259))),'src_span_operator'('no_loc_info_available','src_span'(66,9,66,11,1734,2))),'&'('bool_and'('>'(_num2,'int'(0)),'=='(_cc,_currentCust2)),'prefix'('src_span'(74,47,74,58,2105,11),[],'dotTuple'(['report','fail']),'agent_call'('src_span'(74,62,74,73,2120,11),'CurrentCtrl',[_num2,_currentCust2]),'src_span'(74,59,74,61,2116,43))),'src_span_operator'('no_loc_info_available','src_span'(73,9,73,11,2056,2))),'src_span'(64,34,65,3,1661,493))),'no_loc_info_available').
'agent'('LeaveCtrl'(_num3,_currentCust3),'[]'('&'('=='(_num3,'int'(1)),'prefix'('src_span'(87,21,87,30,2705,9),['out'(_currentCust3)],'leaveBank','agent_call'('src_span'(87,46,87,57,2730,11),'CurrentCtrl',['int'(0),'val_of'('defaultCustomer','src_span'(87,60,87,75,2744,15))]),'src_span'(87,43,87,45,2726,46))),'&'('>'(_num3,'int'(1)),'prefix'('src_span'(89,21,89,30,2794,9),['out'(_currentCust3)],'leaveBank','prefix'('src_span'(89,46,89,62,2819,16),[],'retrieveCustomer','prefix'('src_span'(89,66,89,70,2839,4),['in'(_cc2)],'com3','agent_call'('src_span'(89,77,89,88,2850,11),'CurrentCtrl',['-'(_num3,'int'(1)),_cc2]),'src_span'(89,74,89,76,2846,28)),'src_span'(89,63,89,65,2835,52)),'src_span'(89,43,89,45,2815,68))),'src_span_operator'('no_loc_info_available','src_span'(88,10,88,12,2771,2))),'no_loc_info_available').
'bindval'('QueuesCtrl','agent_call'('src_span'(94,14,94,19,2985,5),'QCtrl',['int'(0),'int'(1),'setExp'('rangeEnum'([]))]),'src_span'(94,1,94,27,2972,26)).
'agent'('QCtrl'(_s,_queueNo3,_custSet),'[]'('&'('<'(_s,'val_of'('maxQueueingCustomers','src_span'(97,11,97,31,3038,20))),'prefix'('src_span'(97,34,97,38,3061,4),['in'(_cc3)],'com1','ifte'('agent_call'('src_span'(98,20,98,26,3094,6),'member',[_cc3,_custSet]),'prefix'('src_span'(99,19,99,34,3140,15),['out'('fail')],'canJoinResponse','agent_call'('src_span'(99,43,99,48,3164,5),'QCtrl',[_s,_queueNo3,_custSet]),'src_span'(99,40,99,42,3160,33)),'prefix'('src_span'(101,19,101,34,3227,15),['out'('success')],'canJoinResponse','prefix'('src_span'(101,46,101,58,3254,12),[],'dotTuple'(['joinQueue',_cc3]),'agent_call'('src_span'(102,5,102,10,3274,5),'QCtrl',['+'(_s,'int'(1)),_queueNo3,'agent_call'('src_span'(102,23,102,28,3292,5),'union',[_custSet,'setExp'('rangeEnum'([_cc3]))])]),'src_span'(101,59,102,4,3266,60)),'src_span'(101,43,101,45,3250,72)),'src_span'(98,16,98,39,3090,23),'src_span'(98,40,99,18,3113,95),'src_span'(99,68,101,18,3188,174)),'src_span'(97,44,98,15,3070,248))),'&'('>'(_s,'int'(0)),'prefix'('src_span'(105,15,105,31,3346,16),[],'retrieveCustomer','agent_call'('src_span'(105,35,105,44,3366,9),'NextQCtrl',[_s,_queueNo3,'agent_call'('src_span'(105,55,105,58,3386,3),'dec',[_queueNo3]),_custSet]),'src_span'(105,32,105,34,3362,61))),'src_span_operator'('no_loc_info_available','src_span'(103,6,103,8,3322,2))),'no_loc_info_available').
'agent'('NextQCtrl'(_s2,_queueNo4,_lastQueueNo,_custSet2),'[]'('[]'('&'('bool_not'('=='(_queueNo4,_lastQueueNo)),'prefix'('src_span'(112,43,112,58,3633,15),['out'(_queueNo4),'in'(_bb2)],'queryQueueEmpty','ifte'('=='(_bb2,'no'),'prefix'('src_span'(115,14,115,24,3718,10),['out'(_queueNo4),'in'(_cc4)],'leaveQueue','ifte'('agent_call'('src_span'(116,19,116,25,3761,6),'member',[_cc4,_custSet2]),'prefix'('src_span'(117,20,117,24,3801,4),['out'(_cc4)],'com3','agent_call'('src_span'(118,24,118,29,3835,5),'QCtrl',['-'(_s2,'int'(1)),'agent_call'('src_span'(118,34,118,37,3845,3),'inc',[_queueNo4]),'agent_call'('src_span'(118,47,118,51,3858,4),'diff',[_custSet2,'setExp'('rangeEnum'([_cc4]))])]),'src_span'(117,28,118,23,3808,72)),'val_of'('DIV','src_span'(119,20,119,23,3897,3)),'src_span'(116,15,116,38,3757,23),'src_span'(116,39,117,19,3780,117),'src_span'(118,67,119,19,3877,99)),'src_span'(115,36,116,14,3739,164)),'agent_call'('src_span'(121,14,121,23,3930,9),'NextQCtrl',[_s2,'agent_call'('src_span'(121,26,121,29,3942,3),'inc',[_queueNo4]),_lastQueueNo,_custSet2]),'src_span'(113,12,113,25,3674,13),'src_span'(113,26,115,13,3687,223),'src_span'(119,24,121,13,3900,257)),'src_span'(112,70,113,11,3659,319))),'&'('=='(_queueNo4,_lastQueueNo),'prefix'('src_span'(123,40,123,55,4030,15),['out'('dotTuple'([_queueNo4,'no']))],'queryQueueEmpty','prefix'('src_span'(124,15,124,25,4074,10),['out'(_queueNo4),'in'(_cc5)],'leaveQueue','ifte'('agent_call'('src_span'(125,22,125,28,4120,6),'member',[_cc5,_custSet2]),'prefix'('src_span'(126,22,126,26,4162,4),['out'(_cc5)],'com3','agent_call'('src_span'(127,26,127,31,4198,5),'QCtrl',['-'(_s2,'int'(1)),'agent_call'('src_span'(127,36,127,39,4208,3),'inc',[_queueNo4]),'agent_call'('src_span'(127,49,127,53,4221,4),'diff',[_custSet2,'setExp'('rangeEnum'([_cc5]))])]),'src_span'(126,30,127,25,4169,74)),'val_of'('DIV','src_span'(128,22,128,25,4262,3)),'src_span'(125,18,125,41,4116,23),'src_span'(125,42,126,21,4139,121),'src_span'(127,69,128,21,4240,103)),'src_span'(124,37,125,16,4095,174)),'src_span'(123,67,124,14,4056,221))),'src_span_operator'('no_loc_info_available','src_span'(122,12,122,14,3988,2))),'&'('=='(_queueNo4,_lastQueueNo),'prefix'('src_span'(129,43,129,58,4310,15),['out'('dotTuple'([_queueNo4,'yes']))],'queryQueueEmpty','val_of'('DIV','src_span'(130,27,130,30,4364,3)),'src_span'(129,71,130,26,4337,42))),'src_span_operator'('no_loc_info_available','src_span'(129,12,129,14,4279,2))),'no_loc_info_available').
'bindval'('A','closure'(['com1','com3','canJoinResponse','retrieveCustomer']),'src_span'(138,1,138,51,4669,50)).
'bindval'('B','closure'(['joinQueue','leaveQueue','queryQueueEmpty']),'src_span'(139,1,139,52,4720,51)).
'bindval'('BankSystem','\x5c\'('sharing'('val_of'('A','src_span'(141,28,141,29,4800,1)),'val_of'('CounterCtrl','src_span'(141,14,141,25,4786,11)),'val_of'('QueuesCtrl','src_span'(141,32,141,42,4804,10)),'src_span'(141,26,141,31,4798,5)),'agent_call'('src_span'(141,45,141,50,4817,5),'union',['val_of'('A','src_span'(141,51,141,52,4823,1)),'val_of'('B','src_span'(141,53,141,54,4825,1))]),'src_span_operator'('no_loc_info_available','src_span'(141,43,141,44,4815,1))),'src_span'(141,1,141,55,4773,54)).
'bindval'('FullBankSystem','sharing'('val_of'('A','src_span'(143,32,143,33,4860,1)),'val_of'('CounterCtrl','src_span'(143,18,143,29,4846,11)),'val_of'('QueuesCtrl','src_span'(143,36,143,46,4864,10)),'src_span'(143,30,143,35,4858,5)),'src_span'(143,1,143,46,4829,45)).
'bindval'('SPEC','repInterleave'(['comprehensionGenerator'(_i,'setExp'('rangeClosed'('int'(1),'val_of'('maxLimit','src_span'(145,18,145,26,4893,8)))))],'val_of'('CUST','src_span'(145,30,145,34,4905,4)),'src_span'(145,12,145,29,4887,17)),'src_span'(145,1,145,34,4876,33)).
'bindval'('CUST','prefix'('src_span'(146,8,146,17,4917,9),['in'(_i2)],'enterBank','[]'('prefix'('src_span'(146,24,146,35,4933,11),[],'dotTuple'(['report','fail']),'val_of'('CUST','src_span'(146,39,146,43,4948,4)),'src_span'(146,36,146,38,4944,19)),'prefix'('src_span'(146,47,146,61,4956,14),[],'dotTuple'(['report','success']),'prefix'('src_span'(147,29,147,40,5002,11),[],'dotTuple'(['leaveBank',_i2]),'val_of'('CUST','src_span'(147,44,147,48,5017,4)),'src_span'(147,41,147,43,5013,19)),'src_span'(146,62,147,28,4970,65)),'src_span_operator'('no_loc_info_available','src_span'(146,44,146,46,4953,2))),'src_span'(146,20,146,22,4928,96)),'src_span'(146,1,147,49,4910,112)).
'assertRef'('False','val_of'('SPEC','src_span'(154,9,154,13,5125,4)),'Trace','val_of'('BankSystem','src_span'(154,18,154,28,5134,10)),'src_span'(154,2,154,28,5118,26)).
'assertRef'('False','val_of'('SPEC','src_span'(155,9,155,13,5153,4)),'Failure','val_of'('BankSystem','src_span'(155,18,155,28,5162,10)),'src_span'(155,2,155,28,5146,26)).
'bindval'('SPEC2','agent_call'('src_span'(158,9,158,16,5244,7),'NEWSPEC',['int'(0)]),'src_span'(158,1,158,19,5236,18)).
'agent'('NEWSPEC'(_num4),'[]'('&'('<'(_num4,'val_of'('maxLimit','src_span'(160,10,160,18,5280,8))),'prefix'('src_span'(160,21,160,30,5291,9),['in'(_cc6)],'enterBank','|~|'('prefix'('src_span'(161,12,161,26,5320,14),[],'dotTuple'(['report','success']),'agent_call'('src_span'(161,30,161,37,5338,7),'NEWSPEC',['+'(_num4,'int'(1))]),'src_span'(161,27,161,29,5334,32)),'prefix'('src_span'(161,49,161,60,5357,11),[],'dotTuple'(['report','fail']),'agent_call'('src_span'(161,64,161,71,5372,7),'NEWSPEC',[_num4]),'src_span'(161,61,161,63,5368,27)),'src_span_operator'('no_loc_info_available','src_span'(161,45,161,48,5353,3))),'src_span'(160,35,161,10,5304,85))),'&'('>'(_num4,'int'(0)),'repInternalChoice'(['comprehensionGenerator'(_cc7,'CUSTOMER')],'prefix'('src_span'(163,33,163,46,5424,13),[],'dotTuple'(['leaveBank',_cc7]),'agent_call'('src_span'(163,50,163,57,5441,7),'NEWSPEC',['-'(_num4,'int'(1))]),'src_span'(163,47,163,49,5437,31)),'src_span'(163,18,163,32,5409,14))),'src_span_operator'('no_loc_info_available','src_span'(162,4,162,6,5389,2))),'no_loc_info_available').
'assertRef'('False','val_of'('SPEC2','src_span'(165,8,165,13,5464,5)),'Trace','val_of'('BankSystem','src_span'(165,18,165,28,5474,10)),'src_span'(165,1,165,28,5457,27)).
'assertRef'('False','val_of'('SPEC2','src_span'(166,8,166,13,5492,5)),'Failure','val_of'('BankSystem','src_span'(166,18,166,28,5502,10)),'src_span'(166,1,166,28,5485,27)).
'channel'('a','type'('dotUnitType')).
'bindval'('DIV','\x5c\'('val_of'('LOOP','src_span'(171,7,171,11,5532,4)),'setExp'('rangeEnum'(['a'])),'src_span_operator'('no_loc_info_available','src_span'(171,12,171,13,5537,1))),'src_span'(171,1,171,17,5526,16)).
'bindval'('LOOP','prefix'('src_span'(172,8,172,9,5550,1),[],'a','val_of'('LOOP','src_span'(172,13,172,17,5555,4)),'src_span'(172,10,172,12,5551,9)),'src_span'(172,1,172,17,5543,16)).
'comment'('lineComment'('-- Example controllers to accompany "ZB2003" paper'),'src_position'(1,1,0,50)).
'comment'('lineComment'('--  version 3'),'src_position'(2,1,51,13)).
'comment'('lineComment'('-- In this example, the CSP controllers have assumptions and no guards.'),'src_position'(4,1,66,71)).
'comment'('lineComment'('-- NextQCtrl is adapted with an additional state variable in order to'),'src_position'(6,1,139,69)).
'comment'('lineComment'('-- prevent divergence of BankSystem'),'src_position'(7,1,209,35)).
'comment'('lineComment'('-- external channels'),'src_position'(21,1,486,20)).
'comment'('lineComment'('-- communication channels, no underlying B, between CSP processes'),'src_position'(26,1,590,65)).
'comment'('lineComment'('-- machine channels'),'src_position'(32,1,763,19)).
'comment'('lineComment'('-- abstract specfication'),'src_position'(41,1,985,24)).
'comment'('lineComment'('-- num is the number of people waiting and the one being serviced'),'src_position'(42,1,1010,65)).
'comment'('lineComment'('--Spec  = Waiting(0,<>)'),'src_position'(44,1,1077,23)).
'comment'('lineComment'('--Waiting(num,people_waiting) = '),'src_position'(45,1,1101,32)).
'comment'('lineComment'('--   num < bankLimit & input ? cc -> Waiting(num+1, people_waiting ^<cc>)'),'src_position'(46,1,1134,73)).
'comment'('lineComment'('--   []'),'src_position'(47,1,1208,7)).
'comment'('lineComment'('--   num > 0 & |~| cc : people_waiting @ output. cc ->'),'src_position'(48,1,1216,54)).
'comment'('lineComment'('--   Waiting(num - 1, .....'),'src_position'(49,1,1271,27)).
'comment'('lineComment'('--- implementation'),'src_position'(51,1,1300,18)).
'comment'('lineComment'('-- we allow any input but then the queues do the checking about'),'src_position'(58,1,1461,63)).
'comment'('lineComment'('-- passing com1 access '),'src_position'(59,1,1525,23)).
'comment'('lineComment'('-- whether the customer is already in the queue '),'src_position'(60,1,1549,48)).
'comment'('lineComment'('-- don\x27\t need to set back to default because could put it as cust'),'src_position'(76,1,2177,65)).
'comment'('lineComment'('--because never going to use that value again'),'src_position'(77,1,2243,45)).
'comment'('lineComment'('-- but what if the size of that queue is empty the we should precoess'),'src_position'(78,1,2289,69)).
'comment'('lineComment'('--the next one along, where is that info going to be kept'),'src_position'(79,1,2359,57)).
'comment'('lineComment'('-- with the com3 is there goign to be a deadlock again with requesting'),'src_position'(80,1,2417,70)).
'comment'('lineComment'('--value, do we need an atomic event to prevent that to signal update'),'src_position'(81,1,2488,68)).
'comment'('lineComment'('--sequence of events'),'src_position'(82,1,2557,20)).
'comment'('lineComment'('-- if > 1 then output the currentperson and get the next one from a'),'src_position'(83,1,2578,67)).
'comment'('lineComment'('--queue'),'src_position'(84,1,2646,7)).
'comment'('lineComment'('-- now queues is tracking the counter and is making the choice of'),'src_position'(91,1,2874,65)).
'comment'('lineComment'('--which queue to get info from'),'src_position'(92,1,2940,30)).
'comment'('lineComment'('-- Because after finding somebody to leave the queues we need to'),'src_position'(108,1,3411,64)).
'comment'('lineComment'('--return with those parameters, we need to carry them across as well.'),'src_position'(109,1,3476,69)).
'comment'('lineComment'('-- We have introduced an additional parameter, lastQueueNo, into'),'src_position'(132,1,4370,64)).
'comment'('lineComment'('-- NextQCtrl.  This is the last point by which a `no\x27\ response can'),'src_position'(133,1,4435,66)).
'comment'('lineComment'('-- come on a queryQueueEmpty query.  This is built into the model,'),'src_position'(134,1,4502,66)).
'comment'('lineComment'('-- which has that if queueNo == lastQueueNo then the only possible'),'src_position'(135,1,4569,66)).
'comment'('lineComment'('-- input for that queue is `no\x27\'),'src_position'(136,1,4636,31)).
'comment'('lineComment'('-- Need to set maxLimit to 2 for this check to succeed in'),'src_position'(149,1,5024,57)).
'comment'('lineComment'('-- any sort of reasonable time.'),'src_position'(150,1,5082,31)).
'comment'('lineComment'('--- not appropriate to check CUSTSPEC for failures refinement'),'src_position'(156,1,5173,61)).
'symbol'('QUEUENUM','QUEUENUM','src_span'(9,1,9,9,246,8),'Ident (Groundrep.)').
'symbol'('CUSTOMER','CUSTOMER','src_span'(10,10,10,18,281,8),'Datatype').
'symbol'('c1','c1','src_span'(10,21,10,23,292,2),'Constructor of Datatype').
'symbol'('c2','c2','src_span'(10,26,10,28,297,2),'Constructor of Datatype').
'symbol'('c3','c3','src_span'(10,31,10,33,302,2),'Constructor of Datatype').
'symbol'('c4','c4','src_span'(10,36,10,38,307,2),'Constructor of Datatype').
'symbol'('c5','c5','src_span'(10,41,10,43,312,2),'Constructor of Datatype').
'symbol'('c6','c6','src_span'(10,46,10,48,317,2),'Constructor of Datatype').
'symbol'('STATUS','STATUS','src_span'(11,10,11,16,329,6),'Datatype').
'symbol'('success','success','src_span'(11,19,11,26,338,7),'Constructor of Datatype').
'symbol'('fail','fail','src_span'(11,29,11,33,348,4),'Constructor of Datatype').
'symbol'('QSTATUS','QSTATUS','src_span'(12,10,12,17,362,7),'Datatype').
'symbol'('yes','yes','src_span'(12,19,12,22,371,3),'Constructor of Datatype').
'symbol'('no','no','src_span'(12,25,12,27,377,2),'Constructor of Datatype').
'symbol'('maxLimit','maxLimit','src_span'(14,1,14,9,381,8),'Ident (Groundrep.)').
'symbol'('defaultCounter','defaultCounter','src_span'(15,1,15,15,394,14),'Ident (Groundrep.)').
'symbol'('defaultCustomer','defaultCustomer','src_span'(16,1,16,16,414,15),'Ident (Groundrep.)').
'symbol'('maxQueueingCustomers','maxQueueingCustomers','src_span'(17,1,17,21,435,20),'Ident (Groundrep.)').
'symbol'('numQueues','numQueues','src_span'(18,1,18,10,470,9),'Ident (Groundrep.)').
'symbol'('enterBank','enterBank','src_span'(22,9,22,18,515,9),'Channel').
'symbol'('leaveBank','leaveBank','src_span'(23,9,23,18,544,9),'Channel').
'symbol'('report','report','src_span'(24,9,24,15,573,6),'Channel').
'symbol'('com1','com1','src_span'(27,9,27,13,664,4),'Channel').
'symbol'('com3','com3','src_span'(28,9,28,13,688,4),'Channel').
'symbol'('canJoinResponse','canJoinResponse','src_span'(29,9,29,24,712,15),'Channel').
'symbol'('retrieveCustomer','retrieveCustomer','src_span'(30,9,30,25,745,16),'Channel').
'symbol'('joinQueue','joinQueue','src_span'(33,9,33,18,791,9),'Channel').
'symbol'('leaveQueue','leaveQueue','src_span'(34,9,34,19,820,10),'Channel').
'symbol'('queryQueueEmpty','queryQueueEmpty','src_span'(35,9,35,24,859,15),'Channel').
'symbol'('inc','inc','src_span'(38,1,38,4,896,3),'Funktion or Process').
'symbol'('queueNo','queueNo','src_span'(38,5,38,12,900,7),'Ident (Prolog Variable)').
'symbol'('dec','dec','src_span'(39,1,39,4,937,3),'Funktion or Process').
'symbol'('queueNo2','queueNo','src_span'(39,5,39,12,941,7),'Ident (Prolog Variable)').
'symbol'('CounterCtrl','CounterCtrl','src_span'(53,1,53,12,1320,11),'Ident (Groundrep.)').
'symbol'('CurrentCtrl','CurrentCtrl','src_span'(55,1,55,12,1366,11),'Funktion or Process').
'symbol'('num','num','src_span'(55,13,55,16,1378,3),'Ident (Prolog Variable)').
'symbol'('currentCust','currentCust','src_span'(55,18,55,29,1383,11),'Ident (Prolog Variable)').
'symbol'('JoinCtrl','JoinCtrl','src_span'(63,1,63,9,1600,8),'Funktion or Process').
'symbol'('num2','num','src_span'(63,10,63,13,1609,3),'Ident (Prolog Variable)').
'symbol'('currentCust2','currentCust','src_span'(63,14,63,25,1613,11),'Ident (Prolog Variable)').
'symbol'('cc','cc','src_span'(64,31,64,33,1659,2),'Ident (Prolog Variable)').
'symbol'('bb','bb','src_span'(68,42,68,44,1837,2),'Ident (Prolog Variable)').
'symbol'('LeaveCtrl','LeaveCtrl','src_span'(86,1,86,10,2655,9),'Funktion or Process').
'symbol'('num3','num','src_span'(86,11,86,14,2665,3),'Ident (Prolog Variable)').
'symbol'('currentCust3','currentCust','src_span'(86,15,86,26,2669,11),'Ident (Prolog Variable)').
'symbol'('cc2','cc','src_span'(89,71,89,73,2844,2),'Ident (Prolog Variable)').
'symbol'('QueuesCtrl','QueuesCtrl','src_span'(94,1,94,11,2972,10),'Ident (Groundrep.)').
'symbol'('QCtrl','QCtrl','src_span'(96,1,96,6,3000,5),'Funktion or Process').
'symbol'('s','s','src_span'(96,7,96,8,3006,1),'Ident (Prolog Variable)').
'symbol'('queueNo3','queueNo','src_span'(96,9,96,16,3008,7),'Ident (Prolog Variable)').
'symbol'('custSet','custSet','src_span'(96,17,96,24,3016,7),'Ident (Prolog Variable)').
'symbol'('cc3','cc','src_span'(97,41,97,43,3068,2),'Ident (Prolog Variable)').
'symbol'('member','member','src_span'(98,20,98,26,3094,6),'BuiltIn primitive').
'symbol'('union','union','src_span'(102,23,102,28,3292,5),'BuiltIn primitive').
'symbol'('NextQCtrl','NextQCtrl','src_span'(111,1,111,10,3547,9),'Funktion or Process').
'symbol'('s2','s','src_span'(111,11,111,12,3557,1),'Ident (Prolog Variable)').
'symbol'('queueNo4','queueNo','src_span'(111,13,111,20,3559,7),'Ident (Prolog Variable)').
'symbol'('lastQueueNo','lastQueueNo','src_span'(111,21,111,32,3567,11),'Ident (Prolog Variable)').
'symbol'('custSet2','custSet','src_span'(111,33,111,40,3579,7),'Ident (Prolog Variable)').
'symbol'('bb2','bb','src_span'(112,67,112,69,3657,2),'Ident (Prolog Variable)').
'symbol'('cc4','cc','src_span'(115,33,115,35,3737,2),'Ident (Prolog Variable)').
'symbol'('member','member','src_span'(116,19,116,25,3761,6),'BuiltIn primitive').
'symbol'('diff','diff','src_span'(118,47,118,51,3858,4),'BuiltIn primitive').
'symbol'('cc5','cc','src_span'(124,34,124,36,4093,2),'Ident (Prolog Variable)').
'symbol'('member','member','src_span'(125,22,125,28,4120,6),'BuiltIn primitive').
'symbol'('diff','diff','src_span'(127,49,127,53,4221,4),'BuiltIn primitive').
'symbol'('A','A','src_span'(138,1,138,2,4669,1),'Ident (Groundrep.)').
'symbol'('B','B','src_span'(139,1,139,2,4720,1),'Ident (Groundrep.)').
'symbol'('BankSystem','BankSystem','src_span'(141,1,141,11,4773,10),'Ident (Groundrep.)').
'symbol'('union','union','src_span'(141,45,141,50,4817,5),'BuiltIn primitive').
'symbol'('FullBankSystem','FullBankSystem','src_span'(143,1,143,15,4829,14),'Ident (Groundrep.)').
'symbol'('SPEC','SPEC','src_span'(145,1,145,5,4876,4),'Ident (Groundrep.)').
'symbol'('i','i','src_span'(145,12,145,13,4887,1),'Ident (Prolog Variable)').
'symbol'('CUST','CUST','src_span'(146,1,146,5,4910,4),'Ident (Groundrep.)').
'symbol'('i2','i','src_span'(146,18,146,19,4927,1),'Ident (Prolog Variable)').
'symbol'('SPEC2','SPEC2','src_span'(158,1,158,6,5236,5),'Ident (Groundrep.)').
'symbol'('NEWSPEC','NEWSPEC','src_span'(159,1,159,8,5255,7),'Funktion or Process').
'symbol'('num4','num','src_span'(159,9,159,12,5263,3),'Ident (Prolog Variable)').
'symbol'('cc6','cc','src_span'(160,32,160,34,5302,2),'Ident (Prolog Variable)').
'symbol'('cc7','cc','src_span'(163,18,163,20,5409,2),'Ident (Prolog Variable)').
'symbol'('a','a','src_span'(170,9,170,10,5524,1),'Channel').
'symbol'('DIV','DIV','src_span'(171,1,171,4,5526,3),'Ident (Groundrep.)').
'symbol'('LOOP','LOOP','src_span'(172,1,172,5,5543,4),'Ident (Groundrep.)').