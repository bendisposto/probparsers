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
'agent'('wire'(_P,_A,_Q),'\x5c\'('sharing'(_A,_P,_Q,'src_span'(23,18,23,25,462,7)),_A,'src_span_operator'('no_loc_info_available','src_span'(23,29,23,30,473,1))),'no_loc_info_available').
'agent'('BUFFER'(_n,_in,_out,_type),'let'(['agent'('B'('listPat'([])),'prefix'('src_span'(30,13,30,15,640,2),['inGuard'(_x,_type)],_in,'agent_call'('src_span'(30,26,30,27,653,1),'B',['listExp'('rangeEnum'([_x]))]),'src_span'(30,23,30,25,649,17)),'src_span'(30,13,30,32,640,19)),'agent'('B'(_s),'[]'('prefix'('src_span'(31,13,31,16,672,3),['out'('agent_call'('src_span'(31,17,31,21,676,4),'head',[_s]))],_out,'agent_call'('src_span'(31,28,31,29,687,1),'B',['agent_call'('src_span'(31,30,31,34,689,4),'tail',[_s])]),'src_span'(31,25,31,27,683,22)),'|~|'('stop'('src_span'(32,14,32,18,711,4)),'&'('<'('#'(_s),_n),'prefix'('src_span'(32,30,32,32,727,2),['inGuard'(_x2,_type)],_in,'agent_call'('src_span'(32,43,32,44,740,1),'B',['^'(_s,'listExp'('rangeEnum'([_x2])))]),'src_span'(32,40,32,42,736,19))),'src_span_operator'('no_loc_info_available','src_span'(32,19,32,22,716,3))),'src_span_operator'('no_loc_info_available','src_span'(32,10,32,12,707,2))),'no_loc_info_available')],'agent_call'('src_span'(33,10,33,11,759,1),'B',['listExp'('rangeEnum'([]))])),'src_span'(29,3,33,15,624,140)).
'agent'('FAULTY'(_n2,_in2,_out2,_type2),'let'(['bindval'('PassRate','int'(3),'src_span'(40,5,40,17,913,12)),'agent'('B2'('listPat'([]),_since_last),'prefix'('src_span'(41,24,41,26,949,2),['inGuard'(_x3,_type2)],_in2,'agent_call'('src_span'(41,37,41,38,962,1),'A2',['listExp'('rangeEnum'([])),_since_last,_x3]),'src_span'(41,34,41,36,958,29)),'src_span'(41,24,41,55,949,31)),'agent'('B2'(_s2,_since_last2),'[]'('prefix'('src_span'(42,24,42,27,1004,3),['out'('agent_call'('src_span'(42,28,42,32,1008,4),'head',[_s2]))],_out2,'agent_call'('src_span'(42,39,42,40,1019,1),'B2',['agent_call'('src_span'(42,41,42,45,1021,4),'tail',[_s2]),_since_last2]),'src_span'(42,36,42,38,1015,33)),'|~|'('stop'('src_span'(43,25,43,29,1065,4)),'&'('<'('#'(_s2),_n2),'prefix'('src_span'(43,41,43,43,1081,2),['inGuard'(_x4,_type2)],_in2,'agent_call'('src_span'(43,54,43,55,1094,1),'A2',[_s2,_since_last2,_x4]),'src_span'(43,51,43,53,1090,28))),'src_span_operator'('no_loc_info_available','src_span'(43,30,43,33,1070,3))),'src_span_operator'('no_loc_info_available','src_span'(43,21,43,23,1061,2))),'no_loc_info_available'),'agent'('A2'(_s3,_since_last3,_x5),'ifte'('<'(_since_last3,'val_of'('PassRate','src_span'(45,23,45,31,1159,8))),'|~|'('agent_call'('src_span'(46,9,46,10,1181,1),'B2',['^'(_s3,'listExp'('rangeEnum'([_x5]))),'int'(0)]),'agent_call'('src_span'(46,24,46,25,1196,1),'B2',[_s3,'+'(_since_last3,'int'(1))]),'src_span_operator'('no_loc_info_available','src_span'(46,20,46,23,1192,3))),'agent_call'('src_span'(48,9,48,10,1233,1),'B2',['^'(_s3,'listExp'('rangeEnum'([_x5]))),'int'(0)]),'no_loc_info_available','no_loc_info_available','no_loc_info_available'),'src_span'(45,7,48,19,1143,100))],'agent_call'('src_span'(49,10,49,11,1253,1),'B2',['listExp'('rangeEnum'([])),'int'(0)])),'src_span'(39,3,49,17,905,355)).
'dataTypeDef'('Connection_Data',['constructorC'('bit','dotTupleType'(['setExp'('rangeEnum'(['int'(0),'int'(1)]))]))]).
'dataTypeDef'('Host',['constructor'('newton'),'constructor'('goedel')]).
'nameType'('Port','type'('dotTupleType'(['setExp'('rangeClosed'('int'(0),'int'(2)))]))).
'nameType'('Connection_Addr','type'('dotTupleType'(['Host','Port']))).
'channel'('connection','type'('dotTupleType'(['Connection_Addr','Connection_Data']))).
'bindval'('Connections','setExp'('rangeEnum'(['tupleExp'(['dotTuple'(['newton','int'(0)]),'dotTuple'(['goedel','int'(1)])]),'tupleExp'(['dotTuple'(['goedel','int'(0)]),'dotTuple'(['newton','int'(1)])]),'tupleExp'(['dotTuple'(['newton','int'(2)]),'dotTuple'(['goedel','int'(2)])])])),'src_span'(71,1,75,2,1764,88)).
'bindval'('ConnectionService','let'(['agent'('Buff'(_read,_write),'prefix'('src_span'(85,25,85,29,2064,4),['in'(_x6)],_read,'prefix'('src_span'(85,35,85,40,2074,5),['out'(_x6)],_write,'agent_call'('src_span'(85,46,85,50,2085,4),'Buff',[_read,_write]),'src_span'(85,43,85,45,2081,23)),'src_span'(85,32,85,34,2070,34)),'src_span'(85,25,85,63,2064,38))],'repInterleave'(['comprehensionGenerator'('tuplePat'([_s4,_d]),'val_of'('Connections','src_span'(86,22,86,33,2124,11)))],'agent_call'('src_span'(86,36,86,40,2138,4),'Buff',['dotTuple'(['connection',_s4]),'dotTuple'(['connection',_d])]),'src_span'(86,14,86,35,2116,21))),'src_span'(83,1,86,68,2013,157)).
'dataTypeDef'('Network_Data',['constructor'('CAck'),'constructorC'('CData','dotTupleType'(['Connection_Data']))]).
'bindval'('Network_Data_Fwd','closure'(['CData']),'src_span'(98,1,98,31,2453,30)).
'bindval'('Network_Data_Rev','agent_call'('src_span'(99,20,99,24,2503,4),'diff',['Network_Data','val_of'('Network_Data_Fwd','src_span'(99,39,99,55,2522,16))]),'src_span'(99,1,99,56,2484,55)).
'bindval'('Network_Addr','Connection_Addr','src_span'(101,1,101,31,2541,30)).
'agent'('hostof'(_h,_p),_h,'src_span'(104,15,104,16,2649,1)).
'channel'('network','type'('dotTupleType'(['val_of'('Network_Addr','src_span'(106,19,106,31,2670,12)),'Network_Data']))).
'bindval'('NetworkService','repInterleave'(['comprehensionGenerator'('tuplePat'([_s5,_d2]),'val_of'('Connections','src_span'(114,15,114,26,2870,11)))],'|||'('agent_call'('src_span'(116,7,116,13,2896,6),'BUFFER',['int'(3),'dotTuple'(['network',_s5]),'dotTuple'(['network',_d2]),'val_of'('Network_Data_Fwd','src_span'(116,38,116,54,2927,16))]),'agent_call'('src_span'(118,7,118,13,2959,6),'BUFFER',['int'(3),'dotTuple'(['network',_d2]),'dotTuple'(['network',_s5]),'val_of'('Network_Data_Rev','src_span'(118,38,118,54,2990,16))]),'src_span_operator'('no_loc_info_available','src_span'(117,5,117,8,2949,3))),'src_span'(114,7,114,28,2862,21)),'src_span'(113,1,119,6,2839,174)).
'agent'('inports'(_host),'setExp'('rangeEnum'([_s6]),['comprehensionGenerator'('tuplePat'([_s6,_]),'val_of'('Connections','src_span'(130,32,130,43,3453,11))),'comprehensionGuard'('=='('agent_call'('src_span'(130,45,130,51,3466,6),'hostof',[_s6]),_host))]),'src_span'(130,18,130,61,3439,43)).
'agent'('outports'(_host2),'setExp'('rangeEnum'([_d3]),['comprehensionGenerator'('tuplePat'([_,_d3]),'val_of'('Connections','src_span'(132,32,132,43,3515,11))),'comprehensionGuard'('=='('agent_call'('src_span'(132,45,132,51,3528,6),'hostof',[_d3]),_host2))]),'src_span'(132,18,132,61,3501,43)).
'bindval'('ConImp','let'(['agent'('InPort'(_addr),'prefix'('src_span'(137,7,137,22,3586,15),['in'(_v)],'dotTuple'(['connection',_addr]),'prefix'('src_span'(138,7,138,19,3614,12),['out'('dotTuple'(['CData',_v]))],'dotTuple'(['network',_addr]),'prefix'('src_span'(139,7,139,24,3645,17),[],'dotTuple'(['network',_addr,'CAck']),'agent_call'('src_span'(139,28,139,34,3666,6),'InPort',[_addr]),'src_span'(139,25,139,27,3662,33)),'src_span'(138,28,139,6,3634,52)),'src_span'(137,25,138,6,3603,77)),'src_span'(137,7,139,40,3586,92)),'agent'('OutPort'(_addr2),'prefix'('src_span'(141,7,141,25,3705,18),['in'(_v2)],'dotTuple'(['network',_addr2,'CData']),'prefix'('src_span'(142,7,142,22,3735,15),['out'(_v2)],'dotTuple'(['connection',_addr2]),'prefix'('src_span'(143,7,143,19,3762,12),['out'('CAck')],'dotTuple'(['network',_addr2]),'agent_call'('src_span'(143,28,143,35,3783,7),'OutPort',[_addr2]),'src_span'(143,25,143,27,3779,22)),'src_span'(142,25,143,6,3752,46)),'src_span'(141,28,142,6,3725,73)),'src_span'(141,7,143,41,3705,91)),'agent'('Ports'(_host3),'|||'('repInterleave'(['comprehensionGenerator'(_s7,'agent_call'('src_span'(145,16,145,23,3830,7),'inports',[_host3]))],'agent_call'('src_span'(145,32,145,38,3846,6),'InPort',[_s7]),'src_span'(145,12,145,31,3826,19)),'repInterleave'(['comprehensionGenerator'(_d4,'agent_call'('src_span'(147,16,147,24,3882,8),'outports',[_host3]))],'agent_call'('src_span'(147,33,147,40,3899,7),'OutPort',[_d4]),'src_span'(147,12,147,32,3878,20)),'src_span_operator'('no_loc_info_available','src_span'(146,7,146,10,3863,3))),'no_loc_info_available')],'repInterleave'(['comprehensionGenerator'(_h2,'Host')],'agent_call'('src_span'(148,25,148,30,3935,5),'Ports',[_h2]),'src_span'(148,14,148,24,3924,10))),'src_span'(134,1,148,33,3546,397)).
'bindval'('ConnectionLayer1','agent_call'('src_span'(151,3,151,7,3966,4),'wire',['val_of'('ConImp','src_span'(151,8,151,14,3971,6)),'closure'(['network']),'val_of'('NetworkService','src_span'(151,29,151,43,3992,14))]),'src_span'(150,1,151,44,3945,62)).
'assertRef'('False','val_of'('ConnectionService','src_span'(153,8,153,25,4016,17)),'Failure','val_of'('ConnectionLayer1','src_span'(153,30,153,46,4038,16)),'src_span'(153,1,153,46,4009,45)).
'dataTypeDef'('Direction',['constructor'('Inbound'),'constructor'('Outbound')]).
'nameType'('Transport_Addr','type'('dotTupleType'(['Host','Direction']))).
'nameType'('Transport_Data','type'('dotTupleType'(['Connection_Addr','Network_Data']))).
'channel'('transport','type'('dotTupleType'(['Transport_Addr','Transport_Data']))).
'agent'('remoteport'(_p2),'let'(['agent'('uniq'('singleSetPat'([_x7])),_x7,'src_span'(176,17,176,18,4528,1)),'bindval'('others','setExp'('rangeEnum'(['ifte'('=='(_p2,_s8),_d5,_s8,'no_loc_info_available','no_loc_info_available','src_span'(177,31,177,35,4559,8))]),['comprehensionGenerator'('tuplePat'([_s8,_d5]),'val_of'('Connections','src_span'(177,49,177,60,4578,11))),'comprehensionGuard'('bool_or'('=='(_p2,_s8),'=='(_p2,_d5)))]),'src_span'(177,5,177,76,4534,71))],'agent_call'('src_span'(178,10,178,14,4615,4),'uniq',['val_of'('others','src_span'(178,15,178,21,4620,6))])),'src_span'(175,3,178,22,4508,119)).
'agent'('Transport_Data_Sent'(_host4),'agent_call'('src_span'(181,3,181,8,4659,5),'union',['setExp'('rangeEnum'(['dotTuple'(['agent_call'('src_span'(182,7,182,17,4672,10),'remoteport',[_p3]),_d6])]),['comprehensionGenerator'(_d6,'val_of'('Network_Data_Fwd','src_span'(182,30,182,46,4695,16))),'comprehensionGenerator'(_p3,'agent_call'('src_span'(182,53,182,60,4718,7),'inports',[_host4]))]),'setExp'('rangeEnum'(['dotTuple'(['agent_call'('src_span'(183,7,183,17,4741,10),'remoteport',[_p4]),_d7])]),['comprehensionGenerator'(_d7,'val_of'('Network_Data_Rev','src_span'(183,30,183,46,4764,16))),'comprehensionGenerator'(_p4,'agent_call'('src_span'(183,53,183,61,4787,8),'outports',[_host4]))])]),'src_span'(181,3,184,4,4659,148)).
'agent'('Transport_Data_Recd'(_host5),'agent_call'('src_span'(187,3,187,8,4839,5),'union',['setExp'('rangeEnum'(['dotTuple'([_p5,_d8])]),['comprehensionGenerator'(_d8,'val_of'('Network_Data_Fwd','src_span'(188,18,188,34,4863,16))),'comprehensionGenerator'(_p5,'agent_call'('src_span'(188,41,188,49,4886,8),'outports',[_host5]))]),'setExp'('rangeEnum'(['dotTuple'([_p6,_d9])]),['comprehensionGenerator'(_d9,'val_of'('Network_Data_Rev','src_span'(189,18,189,34,4921,16))),'comprehensionGenerator'(_p6,'agent_call'('src_span'(189,41,189,48,4944,7),'inports',[_host5]))])]),'src_span'(187,3,190,4,4839,124)).
'bindval'('TransportService','repInterleave'(['comprehensionGenerator'(_s9,'Host'),'comprehensionGenerator'(_d143,'Host'),'comprehensionGuard'('!='(_s9,_d143))],'agent_call'('src_span'(196,5,196,11,5050,6),'BUFFER',['int'(1),'dotTuple'(['transport',_s9,'Outbound']),'dotTuple'(['transport',_d143,'Inbound']),'agent_call'('src_span'(197,12,197,17,5114,5),'inter',['agent_call'('src_span'(197,18,197,37,5120,19),'Transport_Data_Sent',[_s9]),'agent_call'('src_span'(197,42,197,61,5144,19),'Transport_Data_Recd',[_d143])])]),'src_span'(195,7,195,35,5017,28)),'src_span'(194,1,197,66,4992,176)).
'bindval'('NetImp','let'(['agent'('NetworkTransmitter'(_host6),'let'(['bindval'('th','dotTuple'(['transport',_host6,'Outbound']),'src_span'(205,9,205,37,5262,28)),'bindval'('NT','[]'('repChoice'(['comprehensionGenerator'(_p7,'agent_call'('src_span'(207,19,207,26,5322,7),'inports',[_host6]))],'prefix'('src_span'(208,13,208,22,5351,9),['inGuard'(_d150,'val_of'('Network_Data_Fwd','src_span'(208,25,208,41,5363,16)))],'dotTuple'(['network',_p7]),'prefix'('src_span'(209,13,209,15,5395,2),['out'('agent_call'('src_span'(209,16,209,26,5398,10),'remoteport',[_p7])),'out'(_d150)],'val_of'('th','src_span'(209,13,209,15,5395,2)),'val_of'('NT','src_span'(209,35,209,37,5417,2)),'src_span'(209,32,209,34,5413,8)),'src_span'(208,42,209,12,5379,59)),'src_span'(207,15,207,34,5318,19)),'repChoice'(['comprehensionGenerator'(_p8,'agent_call'('src_span'(212,19,212,27,5463,8),'outports',[_host6]))],'prefix'('src_span'(213,13,213,22,5492,9),['inGuard'(_d152,'val_of'('Network_Data_Rev','src_span'(213,25,213,41,5504,16)))],'dotTuple'(['network',_p8]),'prefix'('src_span'(214,13,214,15,5536,2),['out'('agent_call'('src_span'(214,16,214,26,5539,10),'remoteport',[_p8])),'out'(_d152)],'val_of'('th','src_span'(214,13,214,15,5536,2)),'val_of'('NT','src_span'(214,35,214,37,5558,2)),'src_span'(214,32,214,34,5554,8)),'src_span'(213,42,214,12,5520,59)),'src_span'(212,15,212,35,5459,20)),'src_span_operator'('no_loc_info_available','src_span'(211,11,211,13,5442,2))),'src_span'(206,9,215,12,5299,273))],'val_of'('NT','src_span'(216,14,216,16,5586,2))),'src_span'(204,7,216,16,5250,338)),'agent'('NetworkReceiver'(_host7),'let'(['bindval'('th2','dotTuple'(['transport',_host7,'Inbound']),'src_span'(219,9,219,36,5635,27)),'bindval'('NR','[]'('repChoice'(['comprehensionGenerator'(_p9,'agent_call'('src_span'(221,19,221,27,5694,8),'outports',[_host7]))],'prefix'('src_span'(222,13,222,17,5723,4),['inGuard'(_d157,'val_of'('Network_Data_Fwd','src_span'(222,20,222,36,5730,16)))],'dotTuple'(['val_of'('th2','src_span'(222,13,222,15,5723,2)),_p9]),'prefix'('src_span'(223,13,223,22,5762,9),['out'(_d157)],'dotTuple'(['network',_p9]),'val_of'('NR','src_span'(223,28,223,30,5777,2)),'src_span'(223,25,223,27,5773,8)),'src_span'(222,37,223,12,5746,52)),'src_span'(221,15,221,35,5690,20)),'repChoice'(['comprehensionGenerator'(_p158,'agent_call'('src_span'(226,19,226,26,5823,7),'inports',[_host7]))],'prefix'('src_span'(227,13,227,17,5851,4),['inGuard'(_d159,'val_of'('Network_Data_Rev','src_span'(227,20,227,36,5858,16)))],'dotTuple'(['val_of'('th2','src_span'(227,13,227,15,5851,2)),_p158]),'prefix'('src_span'(228,13,228,22,5890,9),['out'(_d159)],'dotTuple'(['network',_p158]),'val_of'('NR','src_span'(228,28,228,30,5905,2)),'src_span'(228,25,228,27,5901,8)),'src_span'(227,37,228,12,5874,52)),'src_span'(226,15,226,34,5819,19)),'src_span_operator'('no_loc_info_available','src_span'(225,11,225,13,5802,2))),'src_span'(220,9,229,12,5671,248))],'val_of'('NR','src_span'(230,14,230,16,5933,2))),'src_span'(218,7,230,16,5623,312))],'repInterleave'(['comprehensionGenerator'(_h3,'Host')],'|||'('agent_call'('src_span'(234,26,234,44,6105,18),'NetworkTransmitter',[_h3]),'agent_call'('src_span'(234,52,234,67,6131,15),'NetworkReceiver',[_h3]),'src_span_operator'('no_loc_info_available','src_span'(234,48,234,51,6127,3))),'src_span'(234,14,234,24,6093,10))),'src_span'(201,1,234,71,5198,952)).
'bindval'('NetworkLayer1','agent_call'('src_span'(237,3,237,7,6171,4),'wire',['val_of'('NetImp','src_span'(237,8,237,14,6176,6)),'closure'(['transport']),'val_of'('TransportService','src_span'(237,31,237,47,6199,16))]),'src_span'(236,1,237,48,6152,64)).
'bindval'('ConnectionLayer2','agent_call'('src_span'(247,3,247,7,6443,4),'wire',['val_of'('ConImp','src_span'(247,8,247,14,6448,6)),'closure'(['network']),'val_of'('NetworkLayer1','src_span'(247,29,247,42,6469,13))]),'src_span'(246,1,247,43,6422,61)).
'assertRef'('False','val_of'('ConnectionService','src_span'(249,8,249,25,6492,17)),'Failure','val_of'('ConnectionLayer2','src_span'(249,30,249,46,6514,16)),'src_span'(249,1,249,46,6485,45)).
'dataTypeDef'('MySeq',['constructor'('seq0'),'constructor'('seq1')]).
'agent'('seq_next'('seq0'),'seq1','src_span'(261,18,261,22,6832,4)).
'agent'('seq_next'('seq1'),'seq0','src_span'(262,18,262,22,6854,4)).
'dataTypeDef'('Transport_Primitive',['constructorC'('TAck','dotTupleType'(['MySeq'])),'constructorC'('TData','dotTupleType'(['MySeq','Transport_Data']))]).
'bindval'('Packet_Data','Transport_Primitive','src_span'(268,1,268,34,6962,33)).
'dataTypeDef'('Function',['constructor'('Forward'),'constructor'('Reverse')]).
'nameType'('Packet_Addr','type'('dotTupleType'(['Host','Direction','Function']))).
'channel'('packet','type'('dotTupleType'(['Packet_Addr','val_of'('Packet_Data','src_span'(274,32,274,43,7119,11))]))).
'agent'('Packet_Data_Sent'(_host8),'setExp'('rangeEnum'(['dotTuple'(['TData',_s162,_d163])]),['comprehensionGenerator'(_s162,'MySeq'),'comprehensionGenerator'(_d163,'agent_call'('src_span'(277,34,277,53,7190,19),'Transport_Data_Sent',[_host8]))]),'src_span'(277,3,277,61,7159,58)).
'agent'('Packet_Data_Recd'(_host9),'setExp'('rangeEnum'(['dotTuple'(['TData',_s165,_d166])]),['comprehensionGenerator'(_s165,'MySeq'),'comprehensionGenerator'(_d166,'agent_call'('src_span'(280,34,280,53,7277,19),'Transport_Data_Recd',[_host9]))]),'src_span'(280,3,280,61,7246,58)).
'agent'('PacketPair'(_s167,_d168),'|||'('agent_call'('src_span'(283,3,283,9,7326,6),'FAULTY',['int'(1),'dotTuple'(['packet',_s167,'Forward']),'dotTuple'(['packet',_d168,'Forward']),'agent_call'('src_span'(284,10,284,15,7380,5),'inter',['agent_call'('src_span'(284,16,284,32,7386,16),'Packet_Data_Sent',['agent_call'('src_span'(284,33,284,39,7403,6),'hostof',[_s167])]),'agent_call'('src_span'(284,44,284,60,7414,16),'Packet_Data_Recd',['agent_call'('src_span'(284,61,284,67,7431,6),'hostof',[_d168])])])]),'agent_call'('src_span'(286,3,286,9,7452,6),'FAULTY',['int'(1),'dotTuple'(['packet',_d168,'Reverse']),'dotTuple'(['packet',_s167,'Reverse']),'closure'(['TAck'])]),'src_span_operator'('no_loc_info_available','src_span'(285,3,285,6,7446,3))),'no_loc_info_available').
'bindval'('PacketService','repInterleave'(['comprehensionGenerator'(_s169,'Host'),'comprehensionGenerator'(_d170,'Host'),'comprehensionGuard'('!='(_s169,_d170))],'agent_call'('src_span'(289,36,289,46,7559,10),'PacketPair',['dotTuple'([_s169,'Outbound']),'dotTuple'([_d170,'Inbound'])]),'src_span'(289,7,289,35,7530,28)),'src_span'(288,1,289,68,7508,83)).
'agent'('TransportSender'(_taddr),'let'(['bindval'('h4','agent_call'('src_span'(293,9,293,15,7633,6),'hostof',[_taddr]),'src_span'(293,5,293,22,7629,17)),'agent'('TS'(_vs,_seq),'[]'('ifte'('agent_call'('src_span'(295,11,295,15,7676,4),'null',[_vs]),'prefix'('src_span'(296,9,296,24,7698,15),['inGuard'(_tdata,'agent_call'('src_span'(297,9,297,28,7730,19),'Transport_Data_Sent',['val_of'('h4','src_span'(297,29,297,30,7750,1))]))],'dotTuple'(['transport',_taddr]),'agent_call'('src_span'(298,9,298,11,7764,2),'TS',['listExp'('rangeEnum'([_tdata])),'agent_call'('src_span'(298,20,298,28,7775,8),'seq_next',[_seq])]),'src_span'(297,32,298,8,7752,76)),'prefix'('src_span'(300,9,300,29,7809,20),['out'('dotTuple'(['TData',_seq,'agent_call'('src_span'(300,40,300,44,7840,4),'head',[_vs])]))],'dotTuple'(['packet',_taddr,'Forward']),'agent_call'('src_span'(301,9,301,11,7860,2),'TS',[_vs,_seq]),'src_span'(300,49,301,8,7848,41)),'src_span'(295,8,295,19,7673,11),'src_span'(295,20,296,8,7684,113),'src_span'(298,35,300,8,7789,172)),'prefix'('src_span'(304,8,304,33,7895,25),['in'(_s177)],'dotTuple'(['packet',_taddr,'Reverse','TAck']),'agent_call'('src_span'(305,9,305,11,7934,2),'TS',['ifte'('=='(_s177,_seq),'listExp'('rangeEnum'([])),_vs,'no_loc_info_available','no_loc_info_available','src_span'(305,30,305,34,7954,10)),_seq]),'src_span'(304,36,305,8,7922,47)),'src_span_operator'('no_loc_info_available','src_span'(303,7,303,9,7885,2))),'no_loc_info_available')],'agent_call'('src_span'(307,10,307,12,7985,2),'TS',['listExp'('rangeEnum'([])),'seq1'])),'src_span'(292,3,307,21,7621,375)).
'agent'('TransportReceiver'(_taddr2),'let'(['bindval'('h5','agent_call'('src_span'(311,9,311,15,8040,6),'hostof',[_taddr2]),'src_span'(311,5,311,22,8036,17)),'agent'('TR'(_seq2),'[]'('prefix'('src_span'(313,7,313,32,8075,25),['out'('agent_call'('src_span'(313,33,313,41,8101,8),'seq_next',[_seq2]))],'dotTuple'(['packet',_taddr2,'Reverse','TAck']),'agent_call'('src_span'(313,50,313,52,8118,2),'TR',[_seq2]),'src_span'(313,47,313,49,8114,25)),'prefix'('src_span'(315,7,315,33,8141,26),['in'(_s182),'inGuard'(_v3,'agent_call'('src_span'(315,38,315,57,8172,19),'Transport_Data_Recd',['val_of'('h5','src_span'(315,58,315,59,8192,1))]))],'dotTuple'(['packet',_taddr2,'Forward','TData']),'ifte'('=='(_s182,_seq2),'prefix'('src_span'(317,11,317,26,8231,15),['out'(_v3)],'dotTuple'(['transport',_taddr2]),'agent_call'('src_span'(317,31,317,33,8251,2),'TR',['agent_call'('src_span'(317,34,317,42,8254,8),'seq_next',[_seq2])]),'src_span'(317,29,317,30,8248,22)),'agent_call'('src_span'(319,11,319,13,8292,2),'TR',[_seq2]),'no_loc_info_available','no_loc_info_available','src_span'(317,49,319,10,8268,68)),'src_span'(315,61,316,8,8194,130)),'src_span_operator'('no_loc_info_available','src_span'(314,7,314,9,8132,2))),'no_loc_info_available')],'agent_call'('src_span'(320,10,320,12,8309,2),'TR',['seq0'])),'src_span'(310,3,320,18,8028,289)).
'agent'('SenderInterface'(_h6),'closureComp'(['comprehensionGenerator'(_d185,'agent_call'('src_span'(325,10,325,26,8420,16),'Packet_Data_Sent',[_h6]))],['dotTuple'(['packet',_h6,'Outbound','Forward',_d185]),'dotTuple'(['packet',_h6,'Outbound','Reverse','TAck'])]),'src_span'(323,3,325,32,8343,99)).
'agent'('ReceiverInterface'(_h7),'closureComp'(['comprehensionGenerator'(_d187,'agent_call'('src_span'(330,10,330,26,8544,16),'Packet_Data_Recd',[_h7]))],['dotTuple'(['packet',_h7,'Inbound','Forward',_d187]),'dotTuple'(['packet',_h7,'Inbound','Reverse','TAck'])]),'src_span'(328,3,330,32,8469,97)).
'bindval'('TransportLayer','let'(['agent'('Trans'(_s189,_d190),'let'(['bindval'('si','agent_call'('src_span'(336,14,336,29,8631,15),'SenderInterface',[_s189]),'src_span'(336,9,336,32,8626,23)),'bindval'('ri','agent_call'('src_span'(337,14,337,31,8663,17),'ReceiverInterface',[_d190]),'src_span'(337,9,337,34,8658,25)),'bindval'('sr','|||'('agent_call'('src_span'(338,14,338,29,8697,15),'TransportSender',['dotTuple'([_s189,'Outbound'])]),'agent_call'('src_span'(338,46,338,63,8729,17),'TransportReceiver',['dotTuple'([_d190,'Inbound'])]),'src_span_operator'('no_loc_info_available','src_span'(338,42,338,45,8725,3))),'src_span'(338,9,338,74,8692,65))],'agent_call'('src_span'(339,14,339,18,8771,4),'wire',['val_of'('sr','src_span'(339,19,339,21,8776,2)),'agent_call'('src_span'(339,23,339,28,8780,5),'union',['val_of'('si','src_span'(339,29,339,31,8786,2)),'val_of'('ri','src_span'(339,33,339,35,8790,2))]),'agent_call'('src_span'(339,38,339,48,8795,10),'PacketPair',['dotTuple'([_s189,'Outbound']),'dotTuple'([_d190,'Inbound'])])])),'src_span'(335,7,339,72,8614,215))],'repInterleave'(['comprehensionGenerator'(_s194,'Host'),'comprehensionGenerator'(_d195,'Host'),'comprehensionGuard'('!='(_s194,_d195))],'agent_call'('src_span'(340,43,340,49,8872,6),'normal',['agent_call'('src_span'(340,50,340,55,8879,5),'Trans',[_s194,_d195])]),'src_span'(340,14,340,42,8843,28))),'src_span'(332,1,340,61,8568,322)).
'assertRef'('False','val_of'('TransportService','src_span'(342,8,342,24,8899,16)),'Failure','val_of'('TransportLayer','src_span'(342,29,342,43,8920,14)),'src_span'(342,1,342,43,8892,42)).
'bindval'('NetworkLayer2','agent_call'('src_span'(345,3,345,7,8955,4),'wire',['val_of'('NetImp','src_span'(345,8,345,14,8960,6)),'closure'(['transport']),'val_of'('TransportLayer','src_span'(345,31,345,45,8983,14))]),'src_span'(344,1,345,46,8936,62)).
'bindval'('ConnectionLayer3','agent_call'('src_span'(358,3,358,7,9276,4),'wire',['val_of'('ConImp','src_span'(358,8,358,14,9281,6)),'closure'(['network']),'val_of'('NetworkLayer2','src_span'(358,29,358,42,9302,13))]),'src_span'(357,1,358,43,9255,61)).
'assertRef'('False','val_of'('ConnectionService','src_span'(360,8,360,25,9325,17)),'Failure','val_of'('ConnectionLayer3','src_span'(360,30,360,46,9347,16)),'src_span'(360,1,360,46,9318,45)).
'bindval'('FaultyConnectionLayer','let'(['bindval'('FaultyTransportService','repInterleave'(['comprehensionGenerator'(_s198,'Host'),'comprehensionGenerator'(_d199,'Host'),'comprehensionGuard'('!='(_s198,_d199))],'agent_call'('src_span'(373,9,373,15,9720,6),'FAULTY',['int'(1),'dotTuple'(['transport',_s198,'Outbound']),'dotTuple'(['transport',_d199,'Inbound']),'agent_call'('src_span'(374,16,374,21,9787,5),'inter',['agent_call'('src_span'(374,22,374,41,9793,19),'Transport_Data_Sent',[_s198]),'agent_call'('src_span'(374,46,374,65,9817,19),'Transport_Data_Recd',[_d199])])]),'src_span'(372,11,372,39,9683,28)),'src_span'(371,5,374,70,9648,193)),'bindval'('FaultyNetworkLayer','agent_call'('src_span'(376,7,376,11,9873,4),'wire',['val_of'('NetImp','src_span'(376,12,376,18,9878,6)),'closure'(['transport']),'val_of'('FaultyTransportService','src_span'(376,35,376,57,9901,22))]),'src_span'(375,5,376,58,9846,78))],'agent_call'('src_span'(377,10,377,14,9934,4),'wire',['val_of'('ConImp','src_span'(377,15,377,21,9939,6)),'closure'(['network']),'val_of'('FaultyNetworkLayer','src_span'(377,36,377,54,9960,18))])),'src_span'(369,1,377,55,9614,365)).
'assertRef'('False','val_of'('ConnectionService','src_span'(379,8,379,25,9988,17)),'Failure','val_of'('FaultyConnectionLayer','src_span'(379,30,379,51,10010,21)),'src_span'(379,1,379,51,9981,50)).
'comment'('blockComment'('{-\xa\  Case study in protocol development\xa\  David Jackson, 1995\xa\  Adapted for FDR2.11, Bryan Scattergood, May 1997\xa\\xa\  Protocols are built in layers, as shown below\xa\\xa\    environment\xa\      | connection channel\xa\    Connection\xa\      | network channel\xa\    Network\xa\      | transport channel\xa\    Transport\xa\-}'),'src_position'(1,1,0,299)).
'comment'('lineComment'('-- We begin by declaring the compression operations we will use'),'src_position'(17,1,301,63)).
'comment'('lineComment'('-- and also a convenient shorthand for sharing and hiding'),'src_position'(21,1,386,57)).
'comment'('lineComment'('-- A buffer from \x27\in\x27\ to \x27\out\x27\ restricted to values in \x27\type\x27\'),'src_position'(25,1,478,61)).
'comment'('lineComment'('-- and non-deterministically holding at most \x27\n\x27\ values.'),'src_position'(26,1,540,56)).
'comment'('lineComment'('-- A buffer, as above, but which is allowed to drop up to (but not'),'src_position'(35,1,766,66)).
'comment'('lineComment'('-- including) \x27\PassRate\x27\ consecutive values.'),'src_position'(36,1,833,44)).
'comment'('blockComment'('{-----------------------------------------------------------------------\xa\  Specification of Connection Service\xa\ \xa\  Packets of data (type Connection_Data) are to be transmitted between hosts\xa\  according to connections defined by ports at each end.\xa\-}'),'src_position'(52,1,1263,249)).
'comment'('lineComment'('-- Instance data: how this particular network is wired up'),'src_position'(69,1,1705,57)).
'comment'('blockComment'('{-\xa\  The specification for the connection is that each\xa\  source/destination pair which are specified by Connections\xa\  show behave like a one-place buffer.\xa\-}'),'src_position'(77,1,1854,157)).
'comment'('blockComment'('{-----------------------------------------------------------------------\xa\  Refinement 1\xa\\xa\  Connection service implemented on top of a network service which provides\xa\  unsynchronized transmission between host.port pairs.\xa\-}'),'src_position'(89,1,2173,222)).
'comment'('lineComment'('--hostof(h.p) = h  -- dot-pattern not supported at the moment'),'src_position'(103,1,2573,61)).
'comment'('blockComment'('{-\xa\  The specification for the network service is that it provides\xa\  reliable bidirectional communication, but may introduce buffering.\xa\-}'),'src_position'(108,1,2699,138)).
'comment'('blockComment'('{-\xa\  Given a network layer which is at least as good as NetworkService,\xa\  we can build a connection layer which is as good as the required\xa\  ConnectionService.  We do this by masking the buffering introduced\xa\  by the network using an explicit acknowledgement.  Each output\xa\  port between the connection and network layer needs to wait for the\xa\  acknowledgement inserted by the corresponding input port.\xa\-}'),'src_position'(121,1,3015,405)).
'comment'('blockComment'('{-----------------------------------------------------------------------\xa\  Refinement 2\xa\\xa\  Implementing the network layer on top of a transport layer by\xa\  multiplexing.\xa\-}'),'src_position'(156,1,4057,171)).
'comment'('lineComment'('-- Transport packets are labelled with destination addresses'),'src_position'(163,1,4230,60)).
'comment'('lineComment'('-- Transport Service Spec'),'src_position'(192,1,4965,25)).
'comment'('lineComment'('-- Multiplexer Definitions'),'src_position'(199,1,5170,26)).
'comment'('lineComment'('--  within ||| h : Host @ NetworkTransmitter(h) ||| NetworkReceiver(h)'),'src_position'(231,1,5936,70)).
'comment'('lineComment'('-- precedences in FDR are unconsistent'),'src_position'(232,1,6007,38)).
'comment'('lineComment'('-- we have to use Parenteses here'),'src_position'(233,1,6046,33)).
'comment'('lineComment'('-- Subject to establishing suitable buffering constraints, we could prove'),'src_position'(239,1,6218,73)).
'comment'('lineComment'('--'),'src_position'(240,1,6292,2)).
'comment'('lineComment'('--         C[NetworkService] [F= C[NetworkLayer}'),'src_position'(241,1,6295,48)).
'comment'('lineComment'('--'),'src_position'(242,1,6344,2)).
'comment'('lineComment'('-- for a context C[ ]'),'src_position'(243,1,6347,21)).
'comment'('lineComment'('-- The Connection Layer is, in fact such a context:'),'src_position'(244,1,6369,51)).
'comment'('blockComment'('{-----------------------------------------------------------------------\xa\  Refinement 3\xa\\xa\  Implement transport service on top of two pairs of potentially unreliable\xa\  channels, using the alternating bit protocol.\xa\-}'),'src_position'(252,1,6533,215)).
'comment'('lineComment'('-- Seq is allread a buitin !'),'src_position'(259,36,6785,28)).
'comment'('lineComment'('-- Packet Service Spec'),'src_position'(266,1,6938,22)).
'comment'('blockComment'('{-\xa\  Given suitable constraints on the buffering and network capacity\xa\  (which we might represent as a context C[ ], we would be able to\xa\  show\xa\          C[NetworkService] [F= C[NetworkLayer2]\xa\  \xa\  Indeed the following  result is an example of this:\xa\-}'),'src_position'(348,1,9001,252)).
'comment'('blockComment'('{-\xa\  If we introduce a deliberate fault into the transport service,\xa\  then the implementations of the network and connection layers\xa\  may no longer good enough ... if we attempt to use them, we could\xa\  fail to be as good as ConnectionService.  \xa\-}'),'src_position'(362,1,9365,247)).
'symbol'('normal','normal','src_span'(19,13,19,19,378,6),'Transparent function').
'symbol'('wire','wire','src_span'(23,1,23,5,445,4),'Funktion or Process').
'symbol'('P','P','src_span'(23,6,23,7,450,1),'Ident (Prolog Variable)').
'symbol'('A','A','src_span'(23,8,23,9,452,1),'Ident (Prolog Variable)').
'symbol'('Q','Q','src_span'(23,10,23,11,454,1),'Ident (Prolog Variable)').
'symbol'('BUFFER','BUFFER','src_span'(28,1,28,7,598,6),'Funktion or Process').
'symbol'('n','n','src_span'(28,8,28,9,605,1),'Ident (Prolog Variable)').
'symbol'('in','in','src_span'(28,10,28,12,607,2),'Ident (Prolog Variable)').
'symbol'('out','out','src_span'(28,13,28,16,610,3),'Ident (Prolog Variable)').
'symbol'('type','type','src_span'(28,17,28,21,614,4),'Ident (Prolog Variable)').
'symbol'('B','B','src_span'(30,5,30,6,632,1),'Funktion or Process').
'symbol'('x','x','src_span'(30,16,30,17,643,1),'Ident (Prolog Variable)').
'symbol'('s','s','src_span'(31,7,31,8,666,1),'Ident (Prolog Variable)').
'symbol'('head','head','src_span'(31,17,31,21,676,4),'BuiltIn primitive').
'symbol'('tail','tail','src_span'(31,30,31,34,689,4),'BuiltIn primitive').
'symbol'('x2','x','src_span'(32,33,32,34,730,1),'Ident (Prolog Variable)').
'symbol'('FAULTY','FAULTY','src_span'(38,1,38,7,879,6),'Funktion or Process').
'symbol'('n2','n','src_span'(38,8,38,9,886,1),'Ident (Prolog Variable)').
'symbol'('in2','in','src_span'(38,10,38,12,888,2),'Ident (Prolog Variable)').
'symbol'('out2','out','src_span'(38,13,38,16,891,3),'Ident (Prolog Variable)').
'symbol'('type2','type','src_span'(38,17,38,21,895,4),'Ident (Prolog Variable)').
'symbol'('PassRate','PassRate','src_span'(40,5,40,13,913,8),'Ident (Groundrep.)').
'symbol'('B2','B','src_span'(41,5,41,6,930,1),'Funktion or Process').
'symbol'('since_last','since_last','src_span'(41,10,41,20,935,10),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(41,27,41,28,952,1),'Ident (Prolog Variable)').
'symbol'('s2','s','src_span'(42,7,42,8,987,1),'Ident (Prolog Variable)').
'symbol'('since_last2','since_last','src_span'(42,9,42,19,989,10),'Ident (Prolog Variable)').
'symbol'('head','head','src_span'(42,28,42,32,1008,4),'BuiltIn primitive').
'symbol'('tail','tail','src_span'(42,41,42,45,1021,4),'BuiltIn primitive').
'symbol'('x4','x','src_span'(43,44,43,45,1084,1),'Ident (Prolog Variable)').
'symbol'('A2','A','src_span'(44,5,44,6,1117,1),'Funktion or Process').
'symbol'('s3','s','src_span'(44,7,44,8,1119,1),'Ident (Prolog Variable)').
'symbol'('since_last3','since_last','src_span'(44,9,44,19,1121,10),'Ident (Prolog Variable)').
'symbol'('x5','x','src_span'(44,20,44,21,1132,1),'Ident (Prolog Variable)').
'symbol'('Connection_Data','Connection_Data','src_span'(59,10,59,25,1523,15),'Datatype').
'symbol'('bit','bit','src_span'(59,28,59,31,1541,3),'Constructor of Datatype').
'symbol'('Host','Host','src_span'(61,10,61,14,1561,4),'Datatype').
'symbol'('newton','newton','src_span'(61,17,61,23,1568,6),'Constructor of Datatype').
'symbol'('goedel','goedel','src_span'(61,26,61,32,1577,6),'Constructor of Datatype').
'symbol'('Port','Port','src_span'(63,10,63,14,1594,4),'Nametype').
'symbol'('Connection_Addr','Connection_Addr','src_span'(65,10,65,25,1618,15),'Nametype').
'symbol'('connection','connection','src_span'(67,9,67,19,1657,10),'Channel').
'symbol'('Connections','Connections','src_span'(71,1,71,12,1764,11),'Ident (Groundrep.)').
'symbol'('ConnectionService','ConnectionService','src_span'(83,1,83,18,2013,17),'Ident (Groundrep.)').
'symbol'('Buff','Buff','src_span'(85,5,85,9,2044,4),'Funktion or Process').
'symbol'('read','read','src_span'(85,10,85,14,2049,4),'Ident (Prolog Variable)').
'symbol'('write','write','src_span'(85,16,85,21,2055,5),'Ident (Prolog Variable)').
'symbol'('x6','x','src_span'(85,30,85,31,2069,1),'Ident (Prolog Variable)').
'symbol'('s4','s','src_span'(86,15,86,16,2117,1),'Ident (Prolog Variable)').
'symbol'('d','d','src_span'(86,17,86,18,2119,1),'Ident (Prolog Variable)').
'symbol'('Network_Data','Network_Data','src_span'(96,10,96,22,2406,12),'Datatype').
'symbol'('CAck','CAck','src_span'(96,25,96,29,2421,4),'Constructor of Datatype').
'symbol'('CData','CData','src_span'(96,32,96,37,2428,5),'Constructor of Datatype').
'symbol'('Network_Data_Fwd','Network_Data_Fwd','src_span'(98,1,98,17,2453,16),'Ident (Groundrep.)').
'symbol'('Network_Data_Rev','Network_Data_Rev','src_span'(99,1,99,17,2484,16),'Ident (Groundrep.)').
'symbol'('diff','diff','src_span'(99,20,99,24,2503,4),'BuiltIn primitive').
'symbol'('Network_Addr','Network_Addr','src_span'(101,1,101,13,2541,12),'Ident (Groundrep.)').
'symbol'('hostof','hostof','src_span'(104,1,104,7,2635,6),'Funktion or Process').
'symbol'('h','h','src_span'(104,8,104,9,2642,1),'Ident (Prolog Variable)').
'symbol'('p','p','src_span'(104,10,104,11,2644,1),'Ident (Prolog Variable)').
'symbol'('network','network','src_span'(106,9,106,16,2660,7),'Channel').
'symbol'('NetworkService','NetworkService','src_span'(113,1,113,15,2839,14),'Ident (Groundrep.)').
'symbol'('s5','s','src_span'(114,8,114,9,2863,1),'Ident (Prolog Variable)').
'symbol'('d2','d','src_span'(114,10,114,11,2865,1),'Ident (Prolog Variable)').
'symbol'('inports','inports','src_span'(130,1,130,8,3422,7),'Funktion or Process').
'symbol'('host','host','src_span'(130,9,130,13,3430,4),'Ident (Prolog Variable)').
'symbol'('s6','s','src_span'(130,24,130,25,3445,1),'Ident (Prolog Variable)').
'symbol'('outports','outports','src_span'(132,1,132,9,3484,8),'Funktion or Process').
'symbol'('host2','host','src_span'(132,10,132,14,3493,4),'Ident (Prolog Variable)').
'symbol'('d3','d','src_span'(132,26,132,27,3509,1),'Ident (Prolog Variable)').
'symbol'('ConImp','ConImp','src_span'(134,1,134,7,3546,6),'Ident (Groundrep.)').
'symbol'('InPort','InPort','src_span'(136,5,136,11,3565,6),'Funktion or Process').
'symbol'('addr','addr','src_span'(136,12,136,16,3572,4),'Ident (Prolog Variable)').
'symbol'('v','v','src_span'(137,23,137,24,3602,1),'Ident (Prolog Variable)').
'symbol'('OutPort','OutPort','src_span'(140,5,140,12,3683,7),'Funktion or Process').
'symbol'('addr2','addr','src_span'(140,13,140,17,3691,4),'Ident (Prolog Variable)').
'symbol'('v2','v','src_span'(141,26,141,27,3724,1),'Ident (Prolog Variable)').
'symbol'('Ports','Ports','src_span'(144,5,144,10,3801,5),'Funktion or Process').
'symbol'('host3','host','src_span'(144,11,144,15,3807,4),'Ident (Prolog Variable)').
'symbol'('s7','s','src_span'(145,12,145,13,3826,1),'Ident (Prolog Variable)').
'symbol'('d4','d','src_span'(147,12,147,13,3878,1),'Ident (Prolog Variable)').
'symbol'('h2','h','src_span'(148,14,148,15,3924,1),'Ident (Prolog Variable)').
'symbol'('ConnectionLayer1','ConnectionLayer1','src_span'(150,1,150,17,3945,16),'Ident (Groundrep.)').
'symbol'('Direction','Direction','src_span'(165,10,165,19,4301,9),'Datatype').
'symbol'('Inbound','Inbound','src_span'(165,22,165,29,4313,7),'Constructor of Datatype').
'symbol'('Outbound','Outbound','src_span'(165,32,165,40,4323,8),'Constructor of Datatype').
'symbol'('Transport_Addr','Transport_Addr','src_span'(167,10,167,24,4342,14),'Nametype').
'symbol'('Transport_Data','Transport_Data','src_span'(169,10,169,24,4386,14),'Nametype').
'symbol'('transport','transport','src_span'(171,9,171,18,4443,9),'Channel').
'symbol'('remoteport','remoteport','src_span'(174,1,174,11,4489,10),'Funktion or Process').
'symbol'('p2','p','src_span'(174,12,174,13,4500,1),'Ident (Prolog Variable)').
'symbol'('uniq','uniq','src_span'(176,5,176,9,4516,4),'Funktion or Process').
'symbol'('x7','x','src_span'(176,11,176,12,4522,1),'Ident (Prolog Variable)').
'symbol'('others','others','src_span'(177,5,177,11,4534,6),'Ident (Groundrep.)').
'symbol'('s8','s','src_span'(177,41,177,42,4570,1),'Ident (Prolog Variable)').
'symbol'('d5','d','src_span'(177,43,177,44,4572,1),'Ident (Prolog Variable)').
'symbol'('Transport_Data_Sent','Transport_Data_Sent','src_span'(180,1,180,20,4629,19),'Funktion or Process').
'symbol'('host4','host','src_span'(180,21,180,25,4649,4),'Ident (Prolog Variable)').
'symbol'('union','union','src_span'(181,3,181,8,4659,5),'BuiltIn primitive').
'symbol'('d6','d','src_span'(182,25,182,26,4690,1),'Ident (Prolog Variable)').
'symbol'('p3','p','src_span'(182,48,182,49,4713,1),'Ident (Prolog Variable)').
'symbol'('d7','d','src_span'(183,25,183,26,4759,1),'Ident (Prolog Variable)').
'symbol'('p4','p','src_span'(183,48,183,49,4782,1),'Ident (Prolog Variable)').
'symbol'('Transport_Data_Recd','Transport_Data_Recd','src_span'(186,1,186,20,4809,19),'Funktion or Process').
'symbol'('host5','host','src_span'(186,21,186,25,4829,4),'Ident (Prolog Variable)').
'symbol'('union','union','src_span'(187,3,187,8,4839,5),'BuiltIn primitive').
'symbol'('d8','d','src_span'(188,13,188,14,4858,1),'Ident (Prolog Variable)').
'symbol'('p5','p','src_span'(188,36,188,37,4881,1),'Ident (Prolog Variable)').
'symbol'('d9','d','src_span'(189,13,189,14,4916,1),'Ident (Prolog Variable)').
'symbol'('p6','p','src_span'(189,36,189,37,4939,1),'Ident (Prolog Variable)').
'symbol'('TransportService','TransportService','src_span'(194,1,194,17,4992,16),'Ident (Groundrep.)').
'symbol'('s9','s','src_span'(195,7,195,8,5017,1),'Ident (Prolog Variable)').
'symbol'('d143','d','src_span'(195,17,195,18,5027,1),'Ident (Prolog Variable)').
'symbol'('inter','inter','src_span'(197,12,197,17,5114,5),'BuiltIn primitive').
'symbol'('NetImp','NetImp','src_span'(201,1,201,7,5198,6),'Ident (Groundrep.)').
'symbol'('NetworkTransmitter','NetworkTransmitter','src_span'(203,5,203,23,5217,18),'Funktion or Process').
'symbol'('host6','host','src_span'(203,24,203,28,5236,4),'Ident (Prolog Variable)').
'symbol'('th','th','src_span'(205,9,205,11,5262,2),'Ident (Groundrep.)').
'symbol'('NT','NT','src_span'(206,9,206,11,5299,2),'Ident (Groundrep.)').
'symbol'('p7','p','src_span'(207,15,207,16,5318,1),'Ident (Prolog Variable)').
'symbol'('d150','d','src_span'(208,23,208,24,5361,1),'Ident (Prolog Variable)').
'symbol'('p8','p','src_span'(212,15,212,16,5459,1),'Ident (Prolog Variable)').
'symbol'('d152','d','src_span'(213,23,213,24,5502,1),'Ident (Prolog Variable)').
'symbol'('NetworkReceiver','NetworkReceiver','src_span'(217,5,217,20,5593,15),'Funktion or Process').
'symbol'('host7','host','src_span'(217,21,217,25,5609,4),'Ident (Prolog Variable)').
'symbol'('th2','th','src_span'(219,9,219,11,5635,2),'Ident (Groundrep.)').
'symbol'('NR','NR','src_span'(220,9,220,11,5671,2),'Ident (Groundrep.)').
'symbol'('p9','p','src_span'(221,15,221,16,5690,1),'Ident (Prolog Variable)').
'symbol'('d157','d','src_span'(222,18,222,19,5728,1),'Ident (Prolog Variable)').
'symbol'('p158','p','src_span'(226,15,226,16,5819,1),'Ident (Prolog Variable)').
'symbol'('d159','d','src_span'(227,18,227,19,5856,1),'Ident (Prolog Variable)').
'symbol'('h3','h','src_span'(234,14,234,15,6093,1),'Ident (Prolog Variable)').
'symbol'('NetworkLayer1','NetworkLayer1','src_span'(236,1,236,14,6152,13),'Ident (Groundrep.)').
'symbol'('ConnectionLayer2','ConnectionLayer2','src_span'(246,1,246,17,6422,16),'Ident (Groundrep.)').
'symbol'('MySeq','MySeq','src_span'(259,10,259,15,6759,5),'Datatype').
'symbol'('seq0','seq0','src_span'(259,18,259,22,6767,4),'Constructor of Datatype').
'symbol'('seq1','seq1','src_span'(259,25,259,29,6774,4),'Constructor of Datatype').
'symbol'('seq_next','seq_next','src_span'(261,1,261,9,6815,8),'Funktion or Process').
'symbol'('seq0','seq0','src_span'(259,18,259,22,6767,4),'Constructor of Datatype').
'symbol'('seq1','seq1','src_span'(259,25,259,29,6774,4),'Constructor of Datatype').
'symbol'('Transport_Primitive','Transport_Primitive','src_span'(264,10,264,29,6869,19),'Datatype').
'symbol'('TAck','TAck','src_span'(264,32,264,36,6891,4),'Constructor of Datatype').
'symbol'('TData','TData','src_span'(264,47,264,52,6906,5),'Constructor of Datatype').
'symbol'('Packet_Data','Packet_Data','src_span'(268,1,268,12,6962,11),'Ident (Groundrep.)').
'symbol'('Function','Function','src_span'(270,10,270,18,7006,8),'Datatype').
'symbol'('Forward','Forward','src_span'(270,21,270,28,7017,7),'Constructor of Datatype').
'symbol'('Reverse','Reverse','src_span'(270,31,270,38,7027,7),'Constructor of Datatype').
'symbol'('Packet_Addr','Packet_Addr','src_span'(272,10,272,21,7045,11),'Nametype').
'symbol'('packet','packet','src_span'(274,9,274,15,7096,6),'Channel').
'symbol'('Packet_Data_Sent','Packet_Data_Sent','src_span'(276,1,276,17,7132,16),'Funktion or Process').
'symbol'('host8','host','src_span'(276,18,276,22,7149,4),'Ident (Prolog Variable)').
'symbol'('s162','s','src_span'(277,17,277,18,7173,1),'Ident (Prolog Variable)').
'symbol'('d163','d','src_span'(277,29,277,30,7185,1),'Ident (Prolog Variable)').
'symbol'('Packet_Data_Recd','Packet_Data_Recd','src_span'(279,1,279,17,7219,16),'Funktion or Process').
'symbol'('host9','host','src_span'(279,18,279,22,7236,4),'Ident (Prolog Variable)').
'symbol'('s165','s','src_span'(280,17,280,18,7260,1),'Ident (Prolog Variable)').
'symbol'('d166','d','src_span'(280,29,280,30,7272,1),'Ident (Prolog Variable)').
'symbol'('PacketPair','PacketPair','src_span'(282,1,282,11,7306,10),'Funktion or Process').
'symbol'('s167','s','src_span'(282,12,282,13,7317,1),'Ident (Prolog Variable)').
'symbol'('d168','d','src_span'(282,14,282,15,7319,1),'Ident (Prolog Variable)').
'symbol'('inter','inter','src_span'(284,10,284,15,7380,5),'BuiltIn primitive').
'symbol'('PacketService','PacketService','src_span'(288,1,288,14,7508,13),'Ident (Groundrep.)').
'symbol'('s169','s','src_span'(289,7,289,8,7530,1),'Ident (Prolog Variable)').
'symbol'('d170','d','src_span'(289,17,289,18,7540,1),'Ident (Prolog Variable)').
'symbol'('TransportSender','TransportSender','src_span'(291,1,291,16,7593,15),'Funktion or Process').
'symbol'('taddr','taddr','src_span'(291,17,291,22,7609,5),'Ident (Prolog Variable)').
'symbol'('h4','h','src_span'(293,5,293,6,7629,1),'Ident (Groundrep.)').
'symbol'('TS','TS','src_span'(294,5,294,7,7651,2),'Funktion or Process').
'symbol'('vs','vs','src_span'(294,8,294,10,7654,2),'Ident (Prolog Variable)').
'symbol'('seq','seq','src_span'(294,12,294,15,7658,3),'Ident (Prolog Variable)').
'symbol'('null','null','src_span'(295,11,295,15,7676,4),'BuiltIn primitive').
'symbol'('tdata','tdata','src_span'(296,25,296,30,7714,5),'Ident (Prolog Variable)').
'symbol'('head','head','src_span'(300,40,300,44,7840,4),'BuiltIn primitive').
'symbol'('s177','s','src_span'(304,34,304,35,7921,1),'Ident (Prolog Variable)').
'symbol'('TransportReceiver','TransportReceiver','src_span'(309,1,309,18,7998,17),'Funktion or Process').
'symbol'('taddr2','taddr','src_span'(309,19,309,24,8016,5),'Ident (Prolog Variable)').
'symbol'('h5','h','src_span'(311,5,311,6,8036,1),'Ident (Groundrep.)').
'symbol'('TR','TR','src_span'(312,5,312,7,8058,2),'Funktion or Process').
'symbol'('seq2','seq','src_span'(312,8,312,11,8061,3),'Ident (Prolog Variable)').
'symbol'('s182','s','src_span'(315,34,315,35,8168,1),'Ident (Prolog Variable)').
'symbol'('v3','v','src_span'(315,36,315,37,8170,1),'Ident (Prolog Variable)').
'symbol'('SenderInterface','SenderInterface','src_span'(322,1,322,16,8319,15),'Funktion or Process').
'symbol'('h6','h','src_span'(322,17,322,18,8335,1),'Ident (Prolog Variable)').
'symbol'('d185','d','src_span'(325,5,325,6,8415,1),'Ident (Prolog Variable)').
'symbol'('ReceiverInterface','ReceiverInterface','src_span'(327,1,327,18,8444,17),'Funktion or Process').
'symbol'('h7','h','src_span'(327,19,327,20,8462,1),'Ident (Prolog Variable)').
'symbol'('d187','d','src_span'(330,5,330,6,8539,1),'Ident (Prolog Variable)').
'symbol'('TransportLayer','TransportLayer','src_span'(332,1,332,15,8568,14),'Ident (Groundrep.)').
'symbol'('Trans','Trans','src_span'(334,5,334,10,8595,5),'Funktion or Process').
'symbol'('s189','s','src_span'(334,11,334,12,8601,1),'Ident (Prolog Variable)').
'symbol'('d190','d','src_span'(334,13,334,14,8603,1),'Ident (Prolog Variable)').
'symbol'('si','si','src_span'(336,9,336,11,8626,2),'Ident (Groundrep.)').
'symbol'('ri','ri','src_span'(337,9,337,11,8658,2),'Ident (Groundrep.)').
'symbol'('sr','sr','src_span'(338,9,338,11,8692,2),'Ident (Groundrep.)').
'symbol'('union','union','src_span'(339,23,339,28,8780,5),'BuiltIn primitive').
'symbol'('s194','s','src_span'(340,14,340,15,8843,1),'Ident (Prolog Variable)').
'symbol'('d195','d','src_span'(340,24,340,25,8853,1),'Ident (Prolog Variable)').
'symbol'('NetworkLayer2','NetworkLayer2','src_span'(344,1,344,14,8936,13),'Ident (Groundrep.)').
'symbol'('ConnectionLayer3','ConnectionLayer3','src_span'(357,1,357,17,9255,16),'Ident (Groundrep.)').
'symbol'('FaultyConnectionLayer','FaultyConnectionLayer','src_span'(369,1,369,22,9614,21),'Ident (Groundrep.)').
'symbol'('FaultyTransportService','FaultyTransportService','src_span'(371,5,371,27,9648,22),'Ident (Groundrep.)').
'symbol'('s198','s','src_span'(372,11,372,12,9683,1),'Ident (Prolog Variable)').
'symbol'('d199','d','src_span'(372,21,372,22,9693,1),'Ident (Prolog Variable)').
'symbol'('inter','inter','src_span'(374,16,374,21,9787,5),'BuiltIn primitive').
'symbol'('FaultyNetworkLayer','FaultyNetworkLayer','src_span'(375,5,375,23,9846,18),'Ident (Groundrep.)').