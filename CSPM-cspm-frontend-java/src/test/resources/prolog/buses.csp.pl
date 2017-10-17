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
'channel'('board37A','type'('dotUnitType')).
'channel'('alight37A','type'('dotUnitType')).
'channel'('alight37B','type'('dotUnitType')).
'channel'('board111A','type'('dotUnitType')).
'channel'('alight111A','type'('dotUnitType')).
'channel'('alight111B','type'('dotUnitType')).
'channel'('pay70','type'('dotUnitType')).
'channel'('pay90','type'('dotUnitType')).
'bindval'('BUS37','prefix'('src_span'(11,9,11,17,181,8),[],'board37A','[]'('prefix'('src_span'(11,22,11,27,194,5),[],'pay90','prefix'('src_span'(11,31,11,40,203,9),[],'alight37B','stop'('src_span'(11,44,11,48,216,4)),'src_span'(11,41,11,43,212,17)),'src_span'(11,28,11,30,199,26)),'prefix'('src_span'(12,25,12,34,245,9),[],'alight37A','stop'('src_span'(12,38,12,42,258,4)),'src_span'(12,35,12,37,254,17)),'src_span_operator'('no_loc_info_available','src_span'(12,22,12,24,242,2))),'src_span'(11,18,11,20,189,82)),'src_span'(11,1,12,43,173,90)).
'bindval'('BUS111','prefix'('src_span'(14,10,14,19,274,9),[],'board111A','[]'('prefix'('src_span'(14,24,14,29,288,5),[],'pay70','prefix'('src_span'(14,33,14,43,297,10),[],'alight111B','stop'('src_span'(14,47,14,51,311,4)),'src_span'(14,44,14,46,307,18)),'src_span'(14,30,14,32,293,27)),'prefix'('src_span'(15,26,15,36,341,10),[],'alight111A','stop'('src_span'(15,40,15,44,355,4)),'src_span'(15,37,15,39,351,18)),'src_span_operator'('no_loc_info_available','src_span'(15,23,15,25,338,2))),'src_span'(14,20,14,22,283,86)),'src_span'(14,1,15,45,265,95)).
'bindval'('SERVICE','|~|'('val_of'('BUS37','src_span'(17,11,17,16,372,5)),'val_of'('BUS111','src_span'(17,21,17,27,382,6)),'src_span_operator'('no_loc_info_available','src_span'(17,17,17,20,378,3))),'src_span'(17,1,17,27,362,26)).
'bindval'('PASS','prefix'('src_span'(19,8,19,16,397,8),[],'board37A','prefix'('src_span'(19,20,19,25,409,5),[],'pay90','prefix'('src_span'(19,29,19,38,418,9),[],'alight37B','stop'('src_span'(19,42,19,46,431,4)),'src_span'(19,39,19,41,427,17)),'src_span'(19,26,19,28,414,26)),'src_span'(19,17,19,19,405,38)),'src_span'(19,1,19,46,390,45)).
'bindval'('SYSTEM','aParallel'('closure'(['board37A','alight37A','alight37B','board111A','alight111A','alight111B','pay70','pay90']),'val_of'('SERVICE','src_span'(21,10,21,17,446,7)),'closure'(['board37A','alight37A','alight37B','board111A','alight111A','alight111B','pay70','pay90']),'val_of'('PASS','src_span'(26,3,26,7,616,4)),'src_span'(22,1,26,2,454,161)),'src_span'(21,1,26,7,437,183)).
'comment'('lineComment'('-- buses.csp'),'src_position'(1,1,0,12)).
'comment'('lineComment'('--'),'src_position'(2,1,13,2)).
'comment'('lineComment'('-- Simon Gay, Royal Holloway, January 1999'),'src_position'(3,1,16,42)).
'comment'('lineComment'('--'),'src_position'(4,1,59,2)).
'comment'('lineComment'('--'),'src_position'(5,1,62,2)).
'symbol'('board37A','board37A','src_span'(7,9,7,17,74,8),'Channel').
'symbol'('alight37A','alight37A','src_span'(7,19,7,28,84,9),'Channel').
'symbol'('alight37B','alight37B','src_span'(7,30,7,39,95,9),'Channel').
'symbol'('board111A','board111A','src_span'(8,9,8,18,115,9),'Channel').
'symbol'('alight111A','alight111A','src_span'(8,20,8,30,126,10),'Channel').
'symbol'('alight111B','alight111B','src_span'(8,32,8,42,138,10),'Channel').
'symbol'('pay70','pay70','src_span'(9,9,9,14,159,5),'Channel').
'symbol'('pay90','pay90','src_span'(9,16,9,21,166,5),'Channel').
'symbol'('BUS37','BUS37','src_span'(11,1,11,6,173,5),'Ident (Groundrep.)').
'symbol'('BUS111','BUS111','src_span'(14,1,14,7,265,6),'Ident (Groundrep.)').
'symbol'('SERVICE','SERVICE','src_span'(17,1,17,8,362,7),'Ident (Groundrep.)').
'symbol'('PASS','PASS','src_span'(19,1,19,5,390,4),'Ident (Groundrep.)').
'symbol'('SYSTEM','SYSTEM','src_span'(21,1,21,7,437,6),'Ident (Groundrep.)').