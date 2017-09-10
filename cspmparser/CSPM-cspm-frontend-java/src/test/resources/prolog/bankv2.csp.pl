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
'bindval'('QUEUENUM','setExp'('rangeClosed'('int'(1),'int'(3))),'src_span'(6,1,6,18,157,17)).
'dataTypeDef'('CUSTOMER',['constructor'('c1'),'constructor'('c2'),'constructor'('c3')]).
'dataTypeDef'('STATUS',['constructor'('success'),'constructor'('fail')]).
'dataTypeDef'('QSTATUS',['constructor'('yes'),'constructor'('no')]).
'bindval'('maxLimit','int'(3),'src_span'(11,1,11,13,269,12)).
'bindval'('defaultCounter','int'(1),'src_span'(12,1,12,20,282,19)).
'bindval'('defaultCustomer','c1','src_span'(13,1,13,21,302,20)).
'bindval'('maxQueueingCustomers','-'('val_of'('maxLimit','src_span'(14,24,14,32,346,8)),'int'(1)),'src_span'(14,1,14,35,323,34)).
'bindval'('numQueues','int'(2),'src_span'(15,1,15,14,358,13)).
'channel'('enterBank','type'('dotTupleType'(['CUSTOMER']))).
'channel'('leaveBank','type'('dotTupleType'(['CUSTOMER']))).
'channel'('report','type'('dotTupleType'(['STATUS']))).
'channel'('com1','type'('dotTupleType'(['CUSTOMER']))).
'channel'('com3','type'('dotTupleType'(['CUSTOMER']))).
'channel'('canJoinResponse','type'('dotTupleType'(['STATUS']))).
'channel'('retrieveCustomer','type'('dotUnitType')).
'channel'('joinQueue','type'('dotTupleType'(['CUSTOMER']))).
'channel'('leaveQueue','type'('dotTupleType'(['val_of'('QUEUENUM','src_span'(31,22,31,30,722,8)),'CUSTOMER']))).
'channel'('queryQueueEmpty','type'('dotTupleType'(['val_of'('QUEUENUM','src_span'(32,27,32,35,766,8)),'QSTATUS']))).
'agent'('inc'(_queueNo),'+'('%'(_queueNo,'val_of'('numQueues','src_span'(35,27,35,36,811,9))),'int'(1)),'no_loc_info_available').
'bindval'('CounterCtrl','agent_call'('src_span'(49,15,49,26,1176,11),'CurrentCtrl',['int'(0),'val_of'('defaultCustomer','src_span'(49,29,49,44,1190,15))]),'src_span'(49,1,49,45,1162,44)).
'agent'('CurrentCtrl'(_num,_currentCust),'[]'('agent_call'('src_span'(52,5,52,13,1245,8),'JoinCtrl',[_num,_currentCust]),'agent_call'('src_span'(52,35,52,44,1275,9),'LeaveCtrl',[_num,_currentCust]),'src_span_operator'('no_loc_info_available','src_span'(52,32,52,34,1272,2))),'no_loc_info_available').
'agent'('JoinCtrl'(_num2,_currentCust2),'&'('<'(_num2,'val_of'('maxLimit','src_span'(60,9,60,17,1479,8))),'prefix'('src_span'(60,20,60,29,1490,9),['in'(_cc)],'enterBank','[]'('[]'('&'('=='(_num2,'int'(0)),'prefix'('src_span'(61,21,61,35,1527,14),[],'dotTuple'(['report','success']),'agent_call'('src_span'(61,39,61,50,1545,11),'CurrentCtrl',['+'(_num2,'int'(1)),_cc]),'src_span'(61,36,61,38,1541,39))),'&'('bool_and'('>'(_num2,'int'(0)),'bool_not'('=='(_cc,_currentCust2))),'prefix'('src_span'(63,47,63,51,1625,4),['out'(_cc)],'com1','prefix'('src_span'(64,23,64,38,1660,15),['in'(_bb)],'canJoinResponse','prefix'('src_span'(64,48,64,57,1685,9),[],'dotTuple'(['report',_bb]),'ifte'('=='(_bb,'success'),'agent_call'('src_span'(66,27,66,38,1772,11),'CurrentCtrl',['+'(_num2,'int'(1)),_currentCust2]),'agent_call'('src_span'(68,27,68,38,1860,11),'CurrentCtrl',[_num2,_currentCust2]),'src_span'(65,25,65,43,1722,18),'src_span'(65,44,66,26,1740,77),'src_span'(66,58,68,26,1802,116)),'src_span'(64,58,65,24,1694,203)),'src_span'(64,45,64,47,1681,211)),'src_span'(63,56,64,22,1633,259))),'src_span_operator'('no_loc_info_available','src_span'(62,9,62,11,1576,2))),'&'('bool_and'('>'(_num2,'int'(0)),'=='(_cc,_currentCust2)),'prefix'('src_span'(70,47,70,58,1947,11),[],'dotTuple'(['report','fail']),'agent_call'('src_span'(70,62,70,73,1962,11),'CurrentCtrl',[_num2,_currentCust2]),'src_span'(70,59,70,61,1958,43))),'src_span_operator'('no_loc_info_available','src_span'(69,9,69,11,1898,2))),'src_span'(60,34,61,3,1503,493))),'no_loc_info_available').
'agent'('LeaveCtrl'(_num3,_currentCust3),'[]'('&'('=='(_num3,'int'(1)),'prefix'('src_span'(83,21,83,30,2547,9),['out'(_currentCust3)],'leaveBank','agent_call'('src_span'(83,46,83,57,2572,11),'CurrentCtrl',['int'(0),'val_of'('defaultCustomer','src_span'(83,60,83,75,2586,15))]),'src_span'(83,43,83,45,2568,46))),'&'('>'(_num3,'int'(1)),'prefix'('src_span'(85,20,85,29,2635,9),['out'(_currentCust3)],'leaveBank','prefix'('src_span'(85,45,85,61,2660,16),[],'retrieveCustomer','prefix'('src_span'(85,65,85,69,2680,4),['in'(_cc2)],'com3','agent_call'('src_span'(85,76,85,87,2691,11),'CurrentCtrl',['-'(_num3,'int'(1)),_cc2]),'src_span'(85,73,85,75,2687,28)),'src_span'(85,62,85,64,2676,52)),'src_span'(85,42,85,44,2656,68))),'src_span_operator'('no_loc_info_available','src_span'(84,10,84,12,2613,2))),'no_loc_info_available').
'bindval'('QueuesCtrl','agent_call'('src_span'(91,14,91,19,2905,5),'QCtrl',['int'(0),'int'(1),'setExp'('rangeEnum'([]))]),'src_span'(91,1,91,27,2892,26)).
'agent'('QCtrl'(_s,_queueNo2,_custSet),'[]'('&'('<'(_s,'val_of'('maxQueueingCustomers','src_span'(93,11,93,31,2957,20))),'prefix'('src_span'(93,34,93,38,2980,4),['in'(_cc3)],'com1','ifte'('agent_call'('src_span'(94,20,94,26,3013,6),'member',[_cc3,_custSet]),'prefix'('src_span'(95,19,95,34,3059,15),['out'('fail')],'canJoinResponse','agent_call'('src_span'(95,43,95,48,3083,5),'QCtrl',[_s,_queueNo2,_custSet]),'src_span'(95,40,95,42,3079,33)),'prefix'('src_span'(97,19,97,34,3146,15),['out'('success')],'canJoinResponse','prefix'('src_span'(97,46,97,58,3173,12),[],'dotTuple'(['joinQueue',_cc3]),'agent_call'('src_span'(98,5,98,10,3193,5),'QCtrl',['+'(_s,'int'(1)),_queueNo2,'agent_call'('src_span'(98,23,98,28,3211,5),'union',[_custSet,'setExp'('rangeEnum'([_cc3]))])]),'src_span'(97,59,98,4,3185,60)),'src_span'(97,43,97,45,3169,72)),'src_span'(94,16,94,39,3009,23),'src_span'(94,40,95,18,3032,95),'src_span'(95,68,97,18,3107,174)),'src_span'(93,44,94,15,2989,248))),'&'('>'(_s,'int'(0)),'prefix'('src_span'(101,15,101,31,3265,16),[],'retrieveCustomer','agent_call'('src_span'(101,35,101,44,3285,9),'NextQCtrl',[_s,_queueNo2,_custSet]),'src_span'(101,32,101,34,3281,48))),'src_span_operator'('no_loc_info_available','src_span'(99,6,99,8,3241,2))),'no_loc_info_available').
'agent'('NextQCtrl'(_s2,_queueNo3,_custSet2),'prefix'('src_span'(110,12,110,27,3544,15),['out'(_queueNo3),'in'(_bb2)],'queryQueueEmpty','ifte'('=='(_bb2,'no'),'prefix'('src_span'(112,14,112,24,3617,10),['out'(_queueNo3),'in'(_cc4)],'leaveQueue','ifte'('agent_call'('src_span'(113,17,113,23,3659,6),'member',[_cc4,_custSet2]),'prefix'('src_span'(114,20,114,24,3699,4),['out'(_cc4)],'com3','agent_call'('src_span'(115,24,115,29,3733,5),'QCtrl',['-'(_s2,'int'(1)),'agent_call'('src_span'(115,34,115,37,3743,3),'inc',[_queueNo3]),'agent_call'('src_span'(115,47,115,51,3756,4),'diff',[_custSet2,'setExp'('rangeEnum'([_cc4]))])]),'src_span'(114,28,115,23,3706,72)),'val_of'('DIV','src_span'(116,20,116,23,3795,3)),'src_span'(113,13,113,36,3655,23),'src_span'(113,37,114,19,3678,117),'src_span'(115,67,116,19,3775,99)),'src_span'(112,36,113,12,3638,163)),'agent_call'('src_span'(119,14,119,23,3932,9),'NextQCtrl',[_s2,'agent_call'('src_span'(119,26,119,29,3944,3),'inc',[_queueNo3]),_custSet2]),'src_span'(111,12,111,25,3585,13),'src_span'(111,26,112,13,3598,210),'src_span'(116,24,119,13,3798,348)),'src_span'(110,39,111,11,3570,398)),'src_span'(110,12,119,47,3544,421)).
'bindval'('A','closure'(['com1','com3','canJoinResponse','retrieveCustomer']),'src_span'(121,1,121,51,3967,50)).
'bindval'('B','closure'(['joinQueue','leaveQueue','queryQueueEmpty']),'src_span'(122,1,122,52,4018,51)).
'bindval'('BankSystem','\x5c\'('sharing'('val_of'('A','src_span'(123,28,123,29,4097,1)),'val_of'('CounterCtrl','src_span'(123,14,123,25,4083,11)),'val_of'('QueuesCtrl','src_span'(123,32,123,42,4101,10)),'src_span'(123,26,123,31,4095,5)),'agent_call'('src_span'(123,45,123,50,4114,5),'union',['val_of'('A','src_span'(123,51,123,52,4120,1)),'val_of'('B','src_span'(123,53,123,54,4122,1))]),'src_span_operator'('no_loc_info_available','src_span'(123,43,123,44,4112,1))),'src_span'(123,1,123,55,4070,54)).
'bindval'('SPEC','repInterleave'(['comprehensionGenerator'(_i,'setExp'('rangeClosed'('int'(1),'val_of'('maxLimit','src_span'(127,18,127,26,4145,8)))))],'val_of'('CUST','src_span'(127,30,127,34,4157,4)),'src_span'(127,12,127,29,4139,17)),'src_span'(127,1,127,34,4128,33)).
'bindval'('CUST','prefix'('src_span'(128,8,128,17,4169,9),['in'(_i2)],'enterBank','[]'('prefix'('src_span'(128,24,128,35,4185,11),[],'dotTuple'(['report','fail']),'val_of'('CUST','src_span'(128,39,128,43,4200,4)),'src_span'(128,36,128,38,4196,19)),'prefix'('src_span'(128,47,128,61,4208,14),[],'dotTuple'(['report','success']),'prefix'('src_span'(129,29,129,40,4254,11),[],'dotTuple'(['leaveBank',_i2]),'val_of'('CUST','src_span'(129,44,129,48,4269,4)),'src_span'(129,41,129,43,4265,19)),'src_span'(128,62,129,28,4222,65)),'src_span_operator'('no_loc_info_available','src_span'(128,44,128,46,4205,2))),'src_span'(128,20,128,22,4180,96)),'src_span'(128,1,129,49,4162,112)).
'assertRef'('False','val_of'('SPEC','src_span'(136,9,136,13,4389,4)),'Trace','val_of'('BankSystem','src_span'(136,18,136,28,4398,10)),'src_span'(136,2,136,28,4382,26)).
'bindval'('SPEC2','agent_call'('src_span'(140,9,140,16,4481,7),'NEWSPEC',['int'(0)]),'src_span'(140,1,140,19,4473,18)).
'agent'('NEWSPEC'(_num4),'[]'('&'('<'(_num4,'val_of'('maxLimit','src_span'(142,10,142,18,4517,8))),'prefix'('src_span'(142,21,142,30,4528,9),['in'(_cc5)],'enterBank','|~|'('prefix'('src_span'(143,12,143,26,4557,14),[],'dotTuple'(['report','success']),'agent_call'('src_span'(143,30,143,37,4575,7),'NEWSPEC',['+'(_num4,'int'(1))]),'src_span'(143,27,143,29,4571,32)),'prefix'('src_span'(143,49,143,60,4594,11),[],'dotTuple'(['report','fail']),'agent_call'('src_span'(143,64,143,71,4609,7),'NEWSPEC',[_num4]),'src_span'(143,61,143,63,4605,27)),'src_span_operator'('no_loc_info_available','src_span'(143,45,143,48,4590,3))),'src_span'(142,35,143,10,4541,85))),'&'('>'(_num4,'int'(0)),'repInternalChoice'(['comprehensionGenerator'(_cc6,'CUSTOMER')],'prefix'('src_span'(145,33,145,46,4661,13),[],'dotTuple'(['leaveBank',_cc6]),'agent_call'('src_span'(145,50,145,57,4678,7),'NEWSPEC',['-'(_num4,'int'(1))]),'src_span'(145,47,145,49,4674,31)),'src_span'(145,18,145,32,4646,14))),'src_span_operator'('no_loc_info_available','src_span'(144,4,144,6,4626,2))),'no_loc_info_available').
'assertRef'('False','val_of'('SPEC2','src_span'(147,8,147,13,4701,5)),'Trace','val_of'('BankSystem','src_span'(147,18,147,28,4711,10)),'src_span'(147,1,147,28,4694,27)).
'assertRef'('False','val_of'('SPEC2','src_span'(148,8,148,13,4729,5)),'Failure','val_of'('BankSystem','src_span'(148,18,148,28,4739,10)),'src_span'(148,1,148,28,4722,27)).
'channel'('a','type'('dotUnitType')).
'bindval'('DIV','\x5c\'('val_of'('LOOP','src_span'(153,7,153,11,4769,4)),'setExp'('rangeEnum'(['a'])),'src_span_operator'('no_loc_info_available','src_span'(153,12,153,13,4774,1))),'src_span'(153,1,153,17,4763,16)).
'bindval'('LOOP','prefix'('src_span'(154,8,154,9,4787,1),[],'a','val_of'('LOOP','src_span'(154,13,154,17,4792,4)),'src_span'(154,10,154,12,4788,9)),'src_span'(154,1,154,17,4780,16)).
'comment'('lineComment'('-- Example controllers to accompany "ZB2003" paper'),'src_position'(1,1,0,50)).
'comment'('lineComment'('-- bank version 2 with more state in the CSP'),'src_position'(3,1,52,44)).
'comment'('lineComment'('-- in an attempt to verify safety and liveness properties.'),'src_position'(4,1,97,58)).
'comment'('lineComment'('-- external channels'),'src_position'(18,1,375,20)).
'comment'('lineComment'('-- communication channels, no underlying B, between CSP processes'),'src_position'(23,1,479,65)).
'comment'('lineComment'('-- machine channels'),'src_position'(29,1,652,19)).
'comment'('lineComment'('-- abstract specfication'),'src_position'(37,1,827,24)).
'comment'('lineComment'('-- num is the number of people waiting and the one being serviced'),'src_position'(38,1,852,65)).
'comment'('lineComment'('--Spec  = Waiting(0,<>)'),'src_position'(40,1,919,23)).
'comment'('lineComment'('--Waiting(num,people_waiting) = '),'src_position'(41,1,943,32)).
'comment'('lineComment'('--   num < bankLimit & input ? cc -> Waiting(num+1, people_waiting ^<cc>)'),'src_position'(42,1,976,73)).
'comment'('lineComment'('--   []'),'src_position'(43,1,1050,7)).
'comment'('lineComment'('--   num > 0 & |~| cc : people_waiting @ output. cc ->'),'src_position'(44,1,1058,54)).
'comment'('lineComment'('--   Waiting(num - 1, .....'),'src_position'(45,1,1113,27)).
'comment'('lineComment'('--- implementation'),'src_position'(47,1,1142,18)).
'comment'('lineComment'('-- we allow any input but then the queues do the checking about'),'src_position'(54,1,1303,63)).
'comment'('lineComment'('-- passing com1 access '),'src_position'(55,1,1367,23)).
'comment'('lineComment'('-- whether the customer is already in the queue '),'src_position'(56,1,1391,48)).
'comment'('lineComment'('-- don\x27\t need to set back to default because could put it as cust'),'src_position'(72,1,2019,65)).
'comment'('lineComment'('--because never going to use that value again'),'src_position'(73,1,2085,45)).
'comment'('lineComment'('-- but what if the size of that queue is empty the we should precoess'),'src_position'(74,1,2131,69)).
'comment'('lineComment'('--the next one along, where is that info going to be kept'),'src_position'(75,1,2201,57)).
'comment'('lineComment'('-- with the com3 is there goign to be a deadlock again with requesting'),'src_position'(76,1,2259,70)).
'comment'('lineComment'('--value, do we need an atomic event to prevent that to signal update'),'src_position'(77,1,2330,68)).
'comment'('lineComment'('--sequence of events'),'src_position'(78,1,2399,20)).
'comment'('lineComment'('-- if > 1 then output the currentperson and get the next one from a'),'src_position'(79,1,2420,67)).
'comment'('lineComment'('--queue'),'src_position'(80,1,2488,7)).
'comment'('lineComment'('--      (num > 1 & leaveBank!currentCust  -> com3?cc -> CurrentCtrl(num-1,cc))'),'src_position'(86,1,2714,78)).
'comment'('lineComment'('-- now queues is tracking the counter and is making the choice of'),'src_position'(88,1,2794,65)).
'comment'('lineComment'('--which queue to get info from'),'src_position'(89,1,2860,30)).
'comment'('lineComment'('--     (s > 0 &  NextQCtrl(s,queueNo,custSet))'),'src_position'(102,1,3315,46)).
'comment'('lineComment'('-- Because after finding somebody to leave the queues we need to'),'src_position'(106,1,3365,64)).
'comment'('lineComment'('--return with those parameters, we need to carry them across as well.'),'src_position'(107,1,3430,69)).
'comment'('lineComment'('--            leaveQueue!queueNo?cc : custSet -> com3!cc -> QCtrl(s-1,inc(queueNo),diff(custSet,{cc}))'),'src_position'(117,3,3801,102)).
'comment'('lineComment'('-- Need to set maxQueueingCustomers to 2 for this check to succeed in'),'src_position'(131,1,4276,69)).
'comment'('lineComment'('-- any sort of reasonable time.'),'src_position'(132,1,4346,31)).
'comment'('lineComment'('--- not appropriate to check CUSTSPEC for failures refinement'),'src_position'(138,1,4410,61)).
'symbol'('QUEUENUM','QUEUENUM','src_span'(6,1,6,9,157,8),'Ident (Groundrep.)').
'symbol'('CUSTOMER','CUSTOMER','src_span'(7,10,7,18,184,8),'Datatype').
'symbol'('c1','c1','src_span'(7,21,7,23,195,2),'Constructor of Datatype').
'symbol'('c2','c2','src_span'(7,26,7,28,200,2),'Constructor of Datatype').
'symbol'('c3','c3','src_span'(7,31,7,33,205,2),'Constructor of Datatype').
'symbol'('STATUS','STATUS','src_span'(8,10,8,16,217,6),'Datatype').
'symbol'('success','success','src_span'(8,19,8,26,226,7),'Constructor of Datatype').
'symbol'('fail','fail','src_span'(8,29,8,33,236,4),'Constructor of Datatype').
'symbol'('QSTATUS','QSTATUS','src_span'(9,10,9,17,250,7),'Datatype').
'symbol'('yes','yes','src_span'(9,19,9,22,259,3),'Constructor of Datatype').
'symbol'('no','no','src_span'(9,25,9,27,265,2),'Constructor of Datatype').
'symbol'('maxLimit','maxLimit','src_span'(11,1,11,9,269,8),'Ident (Groundrep.)').
'symbol'('defaultCounter','defaultCounter','src_span'(12,1,12,15,282,14),'Ident (Groundrep.)').
'symbol'('defaultCustomer','defaultCustomer','src_span'(13,1,13,16,302,15),'Ident (Groundrep.)').
'symbol'('maxQueueingCustomers','maxQueueingCustomers','src_span'(14,1,14,21,323,20),'Ident (Groundrep.)').
'symbol'('numQueues','numQueues','src_span'(15,1,15,10,358,9),'Ident (Groundrep.)').
'symbol'('enterBank','enterBank','src_span'(19,9,19,18,404,9),'Channel').
'symbol'('leaveBank','leaveBank','src_span'(20,9,20,18,433,9),'Channel').
'symbol'('report','report','src_span'(21,9,21,15,462,6),'Channel').
'symbol'('com1','com1','src_span'(24,9,24,13,553,4),'Channel').
'symbol'('com3','com3','src_span'(25,9,25,13,577,4),'Channel').
'symbol'('canJoinResponse','canJoinResponse','src_span'(26,9,26,24,601,15),'Channel').
'symbol'('retrieveCustomer','retrieveCustomer','src_span'(27,9,27,25,634,16),'Channel').
'symbol'('joinQueue','joinQueue','src_span'(30,9,30,18,680,9),'Channel').
'symbol'('leaveQueue','leaveQueue','src_span'(31,9,31,19,709,10),'Channel').
'symbol'('queryQueueEmpty','queryQueueEmpty','src_span'(32,9,32,24,748,15),'Channel').
'symbol'('inc','inc','src_span'(35,1,35,4,785,3),'Funktion or Process').
'symbol'('queueNo','queueNo','src_span'(35,5,35,12,789,7),'Ident (Prolog Variable)').
'symbol'('CounterCtrl','CounterCtrl','src_span'(49,1,49,12,1162,11),'Ident (Groundrep.)').
'symbol'('CurrentCtrl','CurrentCtrl','src_span'(51,1,51,12,1208,11),'Funktion or Process').
'symbol'('num','num','src_span'(51,13,51,16,1220,3),'Ident (Prolog Variable)').
'symbol'('currentCust','currentCust','src_span'(51,18,51,29,1225,11),'Ident (Prolog Variable)').
'symbol'('JoinCtrl','JoinCtrl','src_span'(59,1,59,9,1442,8),'Funktion or Process').
'symbol'('num2','num','src_span'(59,10,59,13,1451,3),'Ident (Prolog Variable)').
'symbol'('currentCust2','currentCust','src_span'(59,14,59,25,1455,11),'Ident (Prolog Variable)').
'symbol'('cc','cc','src_span'(60,31,60,33,1501,2),'Ident (Prolog Variable)').
'symbol'('bb','bb','src_span'(64,42,64,44,1679,2),'Ident (Prolog Variable)').
'symbol'('LeaveCtrl','LeaveCtrl','src_span'(82,1,82,10,2497,9),'Funktion or Process').
'symbol'('num3','num','src_span'(82,11,82,14,2507,3),'Ident (Prolog Variable)').
'symbol'('currentCust3','currentCust','src_span'(82,15,82,26,2511,11),'Ident (Prolog Variable)').
'symbol'('cc2','cc','src_span'(85,70,85,72,2685,2),'Ident (Prolog Variable)').
'symbol'('QueuesCtrl','QueuesCtrl','src_span'(91,1,91,11,2892,10),'Ident (Groundrep.)').
'symbol'('QCtrl','QCtrl','src_span'(92,1,92,6,2919,5),'Funktion or Process').
'symbol'('s','s','src_span'(92,7,92,8,2925,1),'Ident (Prolog Variable)').
'symbol'('queueNo2','queueNo','src_span'(92,9,92,16,2927,7),'Ident (Prolog Variable)').
'symbol'('custSet','custSet','src_span'(92,17,92,24,2935,7),'Ident (Prolog Variable)').
'symbol'('cc3','cc','src_span'(93,41,93,43,2987,2),'Ident (Prolog Variable)').
'symbol'('member','member','src_span'(94,20,94,26,3013,6),'BuiltIn primitive').
'symbol'('union','union','src_span'(98,23,98,28,3211,5),'BuiltIn primitive').
'symbol'('NextQCtrl','NextQCtrl','src_span'(109,1,109,10,3501,9),'Funktion or Process').
'symbol'('s2','s','src_span'(109,11,109,12,3511,1),'Ident (Prolog Variable)').
'symbol'('queueNo3','queueNo','src_span'(109,13,109,20,3513,7),'Ident (Prolog Variable)').
'symbol'('custSet2','custSet','src_span'(109,21,109,28,3521,7),'Ident (Prolog Variable)').
'symbol'('bb2','bb','src_span'(110,36,110,38,3568,2),'Ident (Prolog Variable)').
'symbol'('cc4','cc','src_span'(112,33,112,35,3636,2),'Ident (Prolog Variable)').
'symbol'('member','member','src_span'(113,17,113,23,3659,6),'BuiltIn primitive').
'symbol'('diff','diff','src_span'(115,47,115,51,3756,4),'BuiltIn primitive').
'symbol'('A','A','src_span'(121,1,121,2,3967,1),'Ident (Groundrep.)').
'symbol'('B','B','src_span'(122,1,122,2,4018,1),'Ident (Groundrep.)').
'symbol'('BankSystem','BankSystem','src_span'(123,1,123,11,4070,10),'Ident (Groundrep.)').
'symbol'('union','union','src_span'(123,45,123,50,4114,5),'BuiltIn primitive').
'symbol'('SPEC','SPEC','src_span'(127,1,127,5,4128,4),'Ident (Groundrep.)').
'symbol'('i','i','src_span'(127,12,127,13,4139,1),'Ident (Prolog Variable)').
'symbol'('CUST','CUST','src_span'(128,1,128,5,4162,4),'Ident (Groundrep.)').
'symbol'('i2','i','src_span'(128,18,128,19,4179,1),'Ident (Prolog Variable)').
'symbol'('SPEC2','SPEC2','src_span'(140,1,140,6,4473,5),'Ident (Groundrep.)').
'symbol'('NEWSPEC','NEWSPEC','src_span'(141,1,141,8,4492,7),'Funktion or Process').
'symbol'('num4','num','src_span'(141,9,141,12,4500,3),'Ident (Prolog Variable)').
'symbol'('cc5','cc','src_span'(142,32,142,34,4539,2),'Ident (Prolog Variable)').
'symbol'('cc6','cc','src_span'(145,18,145,20,4646,2),'Ident (Prolog Variable)').
'symbol'('a','a','src_span'(152,9,152,10,4761,1),'Channel').
'symbol'('DIV','DIV','src_span'(153,1,153,4,4763,3),'Ident (Groundrep.)').
'symbol'('LOOP','LOOP','src_span'(154,1,154,5,4780,4),'Ident (Groundrep.)').