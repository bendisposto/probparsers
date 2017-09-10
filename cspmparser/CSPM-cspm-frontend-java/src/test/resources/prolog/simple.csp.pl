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
'dataTypeDef'('FRUIT',['constructor'('apples'),'constructor'('oranges'),'constructor'('pears')]).
'channel'('left','type'('dotTupleType'(['FRUIT']))).
'channel'('right','type'('dotTupleType'(['FRUIT']))).
'channel'('mid','type'('dotTupleType'(['FRUIT']))).
'channel'('ack','type'('dotUnitType')).
'bindval'('COPY','prefix'('src_span'(14,8,14,12,402,4),['in'(_x)],'left','prefix'('src_span'(14,20,14,25,414,5),['out'(_x)],'right','val_of'('COPY','src_span'(14,33,14,37,427,4)),'src_span'(14,30,14,32,423,11)),'src_span'(14,17,14,19,410,24)),'src_span'(14,1,14,37,395,36)).
'bindval'('SEND','prefix'('src_span'(18,8,18,12,522,4),['in'(_x2)],'left','prefix'('src_span'(18,20,18,23,534,3),['out'(_x2)],'mid','prefix'('src_span'(18,31,18,34,545,3),[],'ack','val_of'('SEND','src_span'(18,38,18,42,552,4)),'src_span'(18,35,18,37,548,11)),'src_span'(18,28,18,30,541,18)),'src_span'(18,17,18,19,530,29)),'src_span'(18,1,18,42,515,41)).
'bindval'('REC','prefix'('src_span'(19,7,19,10,563,3),['in'(_x3)],'mid','prefix'('src_span'(19,18,19,23,574,5),['out'(_x3)],'right','prefix'('src_span'(19,31,19,34,587,3),[],'ack','val_of'('REC','src_span'(19,38,19,41,594,3)),'src_span'(19,35,19,37,590,10)),'src_span'(19,28,19,30,583,17)),'src_span'(19,15,19,17,570,30)),'src_span'(19,1,19,41,557,40)).
'bindval'('SYSTEM','\x5c\'('sharing'('closure'(['mid','ack']),'val_of'('SEND','src_span'(22,11,22,15,684,4)),'val_of'('REC','src_span'(22,37,22,40,710,3)),'src_span'(22,16,22,36,689,20)),'closure'(['mid','ack']),'src_span_operator'('no_loc_info_available','src_span'(22,42,22,43,715,1))),'src_span'(22,1,22,58,674,57)).
'assertRef'('False','val_of'('COPY','src_span'(27,8,27,12,829,4)),'FailureDivergence','val_of'('SYSTEM','src_span'(27,18,27,24,839,6)),'src_span'(27,1,27,24,822,23)).
'assertRef'('False','val_of'('SYSTEM','src_span'(31,8,31,14,904,6)),'FailureDivergence','val_of'('COPY','src_span'(31,20,31,24,916,4)),'src_span'(31,1,31,24,897,23)).
'comment'('lineComment'('-- Simple demonstration of FDR2'),'src_position'(1,1,0,31)).
'comment'('lineComment'('-- A single place buffer implemented over two channels'),'src_position'(2,1,32,54)).
'comment'('lineComment'('-- Original by D.Jackson 22 September 1992'),'src_position'(3,1,87,42)).
'comment'('lineComment'('-- Modified for FDR2 by M. Goldsmith 6 December 1995'),'src_position'(4,1,130,52)).
'comment'('lineComment'('-- First, the set of values to be communicated'),'src_position'(6,1,184,46)).
'comment'('lineComment'('-- Channel declarations'),'src_position'(9,1,274,23)).
'comment'('lineComment'('-- The specification is simply a single place buffer'),'src_position'(13,1,342,52)).
'comment'('lineComment'('-- The implementation consists of two processes communicating over'),'src_position'(16,1,433,66)).
'comment'('lineComment'('-- mid and ack'),'src_position'(17,1,500,14)).
'comment'('lineComment'('-- These components are composed in parallel and the internal comms hidden'),'src_position'(21,1,599,74)).
'comment'('lineComment'('-- Checking "SYSTEM" against "COPY" will confirm that the implementation'),'src_position'(24,1,733,72)).
'comment'('lineComment'('-- is correct.'),'src_position'(25,1,806,14)).
'comment'('lineComment'('-- In fact, the processes are equal, as shown by'),'src_position'(29,1,847,48)).
'symbol'('FRUIT','FRUIT','src_span'(7,10,7,15,240,5),'Datatype').
'symbol'('apples','apples','src_span'(7,18,7,24,248,6),'Constructor of Datatype').
'symbol'('oranges','oranges','src_span'(7,27,7,34,257,7),'Constructor of Datatype').
'symbol'('pears','pears','src_span'(7,37,7,42,267,5),'Constructor of Datatype').
'symbol'('left','left','src_span'(10,9,10,13,306,4),'Channel').
'symbol'('right','right','src_span'(10,14,10,19,311,5),'Channel').
'symbol'('mid','mid','src_span'(10,20,10,23,317,3),'Channel').
'symbol'('ack','ack','src_span'(11,9,11,12,337,3),'Channel').
'symbol'('COPY','COPY','src_span'(14,1,14,5,395,4),'Ident (Groundrep.)').
'symbol'('x','x','src_span'(14,15,14,16,409,1),'Ident (Prolog Variable)').
'symbol'('SEND','SEND','src_span'(18,1,18,5,515,4),'Ident (Groundrep.)').
'symbol'('x2','x','src_span'(18,15,18,16,529,1),'Ident (Prolog Variable)').
'symbol'('REC','REC','src_span'(19,1,19,4,557,3),'Ident (Groundrep.)').
'symbol'('x3','x','src_span'(19,13,19,14,569,1),'Ident (Prolog Variable)').
'symbol'('SYSTEM','SYSTEM','src_span'(22,1,22,7,674,6),'Ident (Groundrep.)').