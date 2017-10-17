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
'bindval'('Test','prefix'('src_span'(17,8,17,14,322,6),['in'(_sgn)],'remove','val_of'('Test','src_span'(17,22,17,26,336,4)),'src_span'(17,19,17,21,332,12)),'src_span'(17,1,17,26,315,25)).
'bindval'('Test2','prefix'('src_span'(18,9,18,21,349,12),['in'(_v)],'gen_high_pri','val_of'('Test2','src_span'(18,26,18,31,366,5)),'src_span'(18,24,18,25,363,10)),'src_span'(18,1,18,31,341,30)).
'bindval'('CAPACITY','int'(3),'src_span'(20,1,20,13,373,12)).
'agent'('Q'(_s1,_s2),'[]'('[]'('[]'('&'('<'('#'(_s1),'val_of'('CAPACITY','src_span'(22,10,22,18,406,8))),'prefix'('src_span'(22,21,22,33,417,12),['in'(_sig)],'gen_high_pri','agent_call'('src_span'(22,41,22,42,437,1),'Q',['^'(_s1,'listExp'('rangeEnum'([_sig]))),_s2]),'src_span'(22,38,22,40,433,22))),'&'('<'('#'(_s2),'val_of'('CAPACITY','src_span'(24,10,24,18,467,8))),'prefix'('src_span'(24,21,24,32,478,11),['in'(_sig2)],'gen_low_pri','agent_call'('src_span'(24,40,24,41,497,1),'Q',[_s1,'^'(_s2,'listExp'('rangeEnum'([_sig2])))]),'src_span'(24,37,24,39,493,22))),'src_span_operator'('no_loc_info_available','src_span'(23,4,23,6,455,2))),'&'('>'('#'(_s1),'int'(0)),'prefix'('src_span'(26,14,26,20,531,6),['out'('agent_call'('src_span'(26,21,26,25,538,4),'head',[_s1]))],'remove','agent_call'('src_span'(26,33,26,34,550,1),'Q',['agent_call'('src_span'(26,35,26,39,552,4),'tail',[_s1]),_s2]),'src_span'(26,30,26,32,546,27))),'src_span_operator'('no_loc_info_available','src_span'(25,4,25,6,515,2))),'&'('bool_and'('=='('#'(_s1),'int'(0)),'>'('#'(_s2),'int'(0))),'prefix'('src_span'(28,27,28,33,597,6),['out'('agent_call'('src_span'(28,34,28,38,604,4),'head',[_s2]))],'remove','agent_call'('src_span'(28,46,28,47,616,1),'Q',[_s1,'agent_call'('src_span'(28,51,28,55,621,4),'tail',[_s2])]),'src_span'(28,43,28,45,612,27))),'src_span_operator'('no_loc_info_available','src_span'(27,4,27,6,568,2))),'no_loc_info_available').
'bindval'('IN','prefix'('src_span'(30,6,30,23,637,17),[],'dotTuple'(['gen_low_pri','noarg']),'prefix'('src_span'(30,27,30,46,658,19),['in'(_v1)],'dotTuple'(['gen_high_pri','valarg']),'prefix'('src_span'(31,8,31,27,693,19),['in'(_s12)],'dotTuple'(['gen_low_pri','slotarg']),'prefix'('src_span'(31,34,31,54,719,20),['in'(_v2),'in'(_s22)],'dotTuple'(['gen_high_pri','botharg']),'stop'('src_span'(31,64,31,68,749,4)),'src_span'(31,61,31,63,745,11)),'src_span'(31,31,31,33,715,41)),'src_span'(30,50,31,7,680,76)),'src_span'(30,24,30,26,654,116)),'src_span'(30,1,31,68,632,121)).
'bindval'('OUT','prefix'('src_span'(33,7,33,13,761,6),['in'(_s)],'remove','val_of'('OUT','src_span'(33,19,33,22,773,3)),'src_span'(33,16,33,18,769,9)),'src_span'(33,1,33,22,755,21)).
'bindval'('NET','sharing'('closure'(['gen_high_pri','gen_low_pri','remove']),'|||'('val_of'('IN','src_span'(35,8,35,10,785,2)),'val_of'('OUT','src_span'(35,15,35,18,792,3)),'src_span_operator'('no_loc_info_available','src_span'(35,11,35,14,788,3))),'agent_call'('src_span'(35,62,35,63,839,1),'Q',['listExp'('rangeEnum'([])),'listExp'('rangeEnum'([]))]),'src_span'(35,20,35,61,797,41)),'src_span'(35,1,35,70,778,69)).
'bindval'('MAIN','val_of'('NET','src_span'(38,8,38,11,857,3)),'src_span'(38,1,38,11,850,10)).
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
'symbol'('Test','Test','src_span'(17,1,17,5,315,4),'Ident (Groundrep.)').
'symbol'('sgn','sgn','src_span'(17,15,17,18,329,3),'Ident (Prolog Variable)').
'symbol'('Test2','Test2','src_span'(18,1,18,6,341,5),'Ident (Groundrep.)').
'symbol'('v','v','src_span'(18,22,18,23,362,1),'Ident (Prolog Variable)').
'symbol'('CAPACITY','CAPACITY','src_span'(20,1,20,9,373,8),'Ident (Groundrep.)').
'symbol'('Q','Q','src_span'(21,1,21,2,386,1),'Funktion or Process').
'symbol'('s1','s1','src_span'(21,3,21,5,388,2),'Ident (Prolog Variable)').
'symbol'('s2','s2','src_span'(21,6,21,8,391,2),'Ident (Prolog Variable)').
'symbol'('sig','sig','src_span'(22,34,22,37,430,3),'Ident (Prolog Variable)').
'symbol'('sig2','sig','src_span'(24,33,24,36,490,3),'Ident (Prolog Variable)').
'symbol'('head','head','src_span'(26,21,26,25,538,4),'BuiltIn primitive').
'symbol'('tail','tail','src_span'(26,35,26,39,552,4),'BuiltIn primitive').
'symbol'('head','head','src_span'(28,34,28,38,604,4),'BuiltIn primitive').
'symbol'('tail','tail','src_span'(28,51,28,55,621,4),'BuiltIn primitive').
'symbol'('IN','IN','src_span'(30,1,30,3,632,2),'Ident (Groundrep.)').
'symbol'('v1','v1','src_span'(30,47,30,49,678,2),'Ident (Prolog Variable)').
'symbol'('s12','s1','src_span'(31,28,31,30,713,2),'Ident (Prolog Variable)').
'symbol'('v2','v2','src_span'(31,55,31,57,740,2),'Ident (Prolog Variable)').
'symbol'('s22','s2','src_span'(31,58,31,60,743,2),'Ident (Prolog Variable)').
'symbol'('OUT','OUT','src_span'(33,1,33,4,755,3),'Ident (Groundrep.)').
'symbol'('s','s','src_span'(33,14,33,15,768,1),'Ident (Prolog Variable)').
'symbol'('NET','NET','src_span'(35,1,35,4,778,3),'Ident (Groundrep.)').
'symbol'('MAIN','MAIN','src_span'(38,1,38,5,850,4),'Ident (Groundrep.)').