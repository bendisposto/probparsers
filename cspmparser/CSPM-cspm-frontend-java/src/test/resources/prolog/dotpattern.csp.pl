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
'dataTypeDef'('COL',['constructor'('red'),'constructor'('green')]).
'channel'('ap','type'('dotTupleType'(['setExp'('rangeClosed'('int'(0),'int'(3))),'COL']))).
'agent'('f'('dotpat'(['int'(0),'red'])),'int'(1),'src_span'(5,12,5,13,63,1)).
'agent'('f'('dotpat'(['int'(0),'green'])),'int'(2),'src_span'(6,14,6,15,78,1)).
'agent'('f'('dotpat'([_x,'red'])),'agent_call'('src_span'(7,12,7,13,91,1),'f',['dotTuple'(['-'(_x,'int'(1)),'green'])]),'src_span'(7,12,7,24,91,12)).
'agent'('f'('dotpat'([_x2,'green'])),'agent_call'('src_span'(8,14,8,15,117,1),'f',['dotTuple'(['-'(_x2,'int'(1)),'red'])]),'src_span'(8,14,8,24,117,10)).
'bindval'('MAIN','prefix'('src_span'(10,8,10,10,136,2),['in'(_pair)],'ap','prefix'('src_span'(10,19,10,21,147,2),['out'('dotTuple'(['agent_call'('src_span'(10,23,10,24,151,1),'f',[_pair]),'red']))],'ap','prefix'('src_span'(10,39,10,41,167,2),['in'(_x3),'in'(_y)],'ap','prefix'('src_span'(10,49,10,51,177,2),['out'('dotTuple'(['agent_call'('src_span'(10,52,10,53,180,1),'f',['dotTuple'([_x3,_y])]),'green']))],'ap','val_of'('MAIN','src_span'(10,68,10,72,196,4)),'src_span'(10,65,10,67,192,21)),'src_span'(10,46,10,48,173,29)),'src_span'(10,36,10,38,163,51)),'src_span'(10,16,10,18,143,62)),'src_span'(10,1,10,72,129,71)).
'symbol'('COL','COL','src_span'(1,10,1,13,9,3),'Datatype').
'symbol'('red','red','src_span'(1,16,1,19,15,3),'Constructor of Datatype').
'symbol'('green','green','src_span'(1,22,1,27,21,5),'Constructor of Datatype').
'symbol'('ap','ap','src_span'(2,9,2,11,35,2),'Channel').
'symbol'('f','f','src_span'(5,1,5,2,52,1),'Funktion or Process').
'symbol'('red','red','src_span'(1,16,1,19,15,3),'Constructor of Datatype').
'symbol'('green','green','src_span'(1,22,1,27,21,5),'Constructor of Datatype').
'symbol'('x','x','src_span'(7,3,7,4,82,1),'Ident (Prolog Variable)').
'symbol'('red','red','src_span'(1,16,1,19,15,3),'Constructor of Datatype').
'symbol'('x2','x','src_span'(8,3,8,4,106,1),'Ident (Prolog Variable)').
'symbol'('green','green','src_span'(1,22,1,27,21,5),'Constructor of Datatype').
'symbol'('MAIN','MAIN','src_span'(10,1,10,5,129,4),'Ident (Groundrep.)').
'symbol'('pair','pair','src_span'(10,11,10,15,139,4),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(10,42,10,43,170,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(10,44,10,45,172,1),'Ident (Prolog Variable)').