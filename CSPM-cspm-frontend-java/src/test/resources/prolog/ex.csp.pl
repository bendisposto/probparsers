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
'nameType'('Val','type'('dotTupleType'(['setExp'('rangeClosed'('int'(0),'int'(6)))]))).
'dataTypeDef'('Slot',['constructor'('sl1'),'constructor'('sl2'),'constructor'('sl3'),'constructor'('sl4')]).
'dataTypeDef'('Signal',['constructor'('noarg'),'constructorC'('valarg','dotTupleType'(['Val'])),'constructorC'('slotarg','dotTupleType'(['Slot'])),'constructorC'('botharg','dotTupleType'(['Val','Slot']))]).
'subTypeDef'('LPSignal',['constructor'('noarg'),'constructorC'('slotarg','dotTupleType'(['Slot']))]).
'subTypeDef'('HPSignal',['constructorC'('valarg','dotTupleType'(['Val'])),'constructorC'('botharg','dotTupleType'(['Val','Slot']))]).
'channel'('gen_high_pri','type'('dotTupleType'(['HPSignal']))).
'channel'('gen_low_pri','type'('dotTupleType'(['LPSignal']))).
'channel'('remove','type'('dotTupleType'(['Signal']))).
'bindval'('CAPACITY','int'(3),'src_span'(17,1,17,13,315,12)).
'agent'('Q'(_s1,_s2),'[]'('[]'('[]'('&'('<'('#'(_s1),'val_of'('CAPACITY','src_span'(19,10,19,18,348,8))),'prefix'('src_span'(19,21,19,33,359,12),['in'(_sig)],'gen_high_pri','agent_call'('src_span'(19,41,19,42,379,1),'Q',['^'(_s1,'listExp'('rangeEnum'([_sig]))),_s2]),'src_span'(19,38,19,40,375,22))),'&'('<'('#'(_s2),'val_of'('CAPACITY','src_span'(21,10,21,18,409,8))),'prefix'('src_span'(21,21,21,32,420,11),['in'(_sig2)],'gen_low_pri','agent_call'('src_span'(21,40,21,41,439,1),'Q',[_s1,'^'(_s2,'listExp'('rangeEnum'([_sig2])))]),'src_span'(21,37,21,39,435,22))),'src_span_operator'('no_loc_info_available','src_span'(20,4,20,6,397,2))),'&'('>'('#'(_s1),'int'(0)),'prefix'('src_span'(23,14,23,20,473,6),['out'('agent_call'('src_span'(23,21,23,25,480,4),'head',[_s1]))],'remove','agent_call'('src_span'(23,33,23,34,492,1),'Q',['agent_call'('src_span'(23,35,23,39,494,4),'tail',[_s1]),_s2]),'src_span'(23,30,23,32,488,27))),'src_span_operator'('no_loc_info_available','src_span'(22,4,22,6,457,2))),'&'('bool_and'('=='('#'(_s1),'int'(0)),'>'('#'(_s2),'int'(0))),'prefix'('src_span'(25,27,25,33,539,6),['out'('agent_call'('src_span'(25,34,25,38,546,4),'head',[_s2]))],'remove','agent_call'('src_span'(25,46,25,47,558,1),'Q',[_s1,'agent_call'('src_span'(25,51,25,55,563,4),'tail',[_s2])]),'src_span'(25,43,25,45,554,27))),'src_span_operator'('no_loc_info_available','src_span'(24,4,24,6,510,2))),'no_loc_info_available').
'bindval'('IN','prefix'('src_span'(27,6,27,23,579,17),[],'dotTuple'(['gen_low_pri','noarg']),'prefix'('src_span'(27,27,27,46,600,19),['in'(_v1)],'dotTuple'(['gen_high_pri','valarg']),'prefix'('src_span'(28,8,28,27,633,19),['in'(_s12)],'dotTuple'(['gen_low_pri','slotarg']),'prefix'('src_span'(28,34,28,54,659,20),['in'(_v2),'in'(_s22)],'dotTuple'(['gen_high_pri','botharg']),'stop'('src_span'(28,64,28,68,689,4)),'src_span'(28,61,28,63,685,11)),'src_span'(28,31,28,33,655,41)),'src_span'(27,50,28,7,622,74)),'src_span'(27,24,27,26,596,114)),'src_span'(27,1,28,68,574,119)).
'bindval'('OUT','prefix'('src_span'(30,7,30,13,701,6),['in'(_s)],'remove','val_of'('OUT','src_span'(30,19,30,22,713,3)),'src_span'(30,16,30,18,709,9)),'src_span'(30,1,30,22,695,21)).
'bindval'('NET','sharing'('closure'(['gen_high_pri','gen_low_pri','remove']),'|||'('val_of'('IN','src_span'(32,8,32,10,725,2)),'val_of'('OUT','src_span'(32,15,32,18,732,3)),'src_span_operator'('no_loc_info_available','src_span'(32,11,32,14,728,3))),'agent_call'('src_span'(32,60,32,61,777,1),'Q',['listExp'('rangeEnum'([])),'listExp'('rangeEnum'([]))]),'src_span'(32,20,32,59,737,39)),'src_span'(32,1,32,68,718,67)).
'symbol'('Val','Val','src_span'(1,10,1,13,9,3),'Nametype').
'symbol'('Slot','Slot','src_span'(3,10,3,14,32,4),'Datatype').
'symbol'('sl1','sl1','src_span'(3,17,3,20,39,3),'Constructor of Datatype').
'symbol'('sl2','sl2','src_span'(3,23,3,26,45,3),'Constructor of Datatype').
'symbol'('sl3','sl3','src_span'(3,29,3,32,51,3),'Constructor of Datatype').
'symbol'('sl4','sl4','src_span'(3,35,3,38,57,3),'Constructor of Datatype').
'symbol'('Signal','Signal','src_span'(5,10,5,16,71,6),'Datatype').
'symbol'('noarg','noarg','src_span'(5,19,5,24,80,5),'Constructor of Datatype').
'symbol'('valarg','valarg','src_span'(5,27,5,33,88,6),'Constructor of Datatype').
'symbol'('slotarg','slotarg','src_span'(5,40,5,47,101,7),'Constructor of Datatype').
'symbol'('botharg','botharg','src_span'(5,55,5,62,116,7),'Constructor of Datatype').
'symbol'('LPSignal','LPSignal','src_span'(7,9,7,17,142,8),'Datatype').
'symbol'('HPSignal','HPSignal','src_span'(9,9,9,17,183,8),'Datatype').
'symbol'('gen_high_pri','gen_high_pri','src_span'(11,9,11,21,233,12),'Channel').
'symbol'('gen_low_pri','gen_low_pri','src_span'(13,9,13,20,266,11),'Channel').
'symbol'('remove','remove','src_span'(15,9,15,15,298,6),'Channel').
'symbol'('CAPACITY','CAPACITY','src_span'(17,1,17,9,315,8),'Ident (Groundrep.)').
'symbol'('Q','Q','src_span'(18,1,18,2,328,1),'Funktion or Process').
'symbol'('s1','s1','src_span'(18,3,18,5,330,2),'Ident (Prolog Variable)').
'symbol'('s2','s2','src_span'(18,6,18,8,333,2),'Ident (Prolog Variable)').
'symbol'('sig','sig','src_span'(19,34,19,37,372,3),'Ident (Prolog Variable)').
'symbol'('sig2','sig','src_span'(21,33,21,36,432,3),'Ident (Prolog Variable)').
'symbol'('head','head','src_span'(23,21,23,25,480,4),'BuiltIn primitive').
'symbol'('tail','tail','src_span'(23,35,23,39,494,4),'BuiltIn primitive').
'symbol'('head','head','src_span'(25,34,25,38,546,4),'BuiltIn primitive').
'symbol'('tail','tail','src_span'(25,51,25,55,563,4),'BuiltIn primitive').
'symbol'('IN','IN','src_span'(27,1,27,3,574,2),'Ident (Groundrep.)').
'symbol'('v1','v1','src_span'(27,47,27,49,620,2),'Ident (Prolog Variable)').
'symbol'('s12','s1','src_span'(28,28,28,30,653,2),'Ident (Prolog Variable)').
'symbol'('v2','v2','src_span'(28,55,28,57,680,2),'Ident (Prolog Variable)').
'symbol'('s22','s2','src_span'(28,58,28,60,683,2),'Ident (Prolog Variable)').
'symbol'('OUT','OUT','src_span'(30,1,30,4,695,3),'Ident (Groundrep.)').
'symbol'('s','s','src_span'(30,14,30,15,708,1),'Ident (Prolog Variable)').
'symbol'('NET','NET','src_span'(32,1,32,4,718,3),'Ident (Groundrep.)').