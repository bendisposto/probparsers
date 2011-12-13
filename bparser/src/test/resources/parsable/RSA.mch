MACHINE RSA
SETS    CDS; DBS; OBS; DT; 
        LOADSTATUS  = {load_success, already_loaded}; 
        GENSTATUS   = {gen_success, already_generated, no_cds_loaded}; 
        POPDBSTATUS = {popdbs_success, already_populated_dbs, no_dbs_generated, not_valid_dbs_population}; 
        POPCDSTATUS = {popcds_success, already_populated_cds, no_dbs_populated}
CONSTANTS schTrans, dtTrans,  dbInst
PROPERTIES
	schTrans : CDS >-> DBS 		&
	dtTrans : OBS >-> DT		&
	dbInst : DBS --> POW1(DT)	
VARIABLES currentCDS, currentDBS, currentOBS, currentDT, cdload, dbgen, dbpop, cdpop
INVARIANT
	cdload : BOOL									&
	dbgen : BOOL									&
	dbpop : BOOL									&
	cdpop : BOOL									&
	currentCDS : CDS								&
	currentDBS : DBS								&
	currentOBS : OBS								&
	currentDT : DT      								&
	( dbgen = TRUE => currentDBS = schTrans(currentCDS) ) 				&
	( dbpop = TRUE => currentDT : dbInst(currentDBS) & currentDT : ran(dtTrans) ) 	&
	( cdpop = TRUE => currentDT = dtTrans(currentOBS) )
INITIALISATION 
	ANY cds, dbs, obs, dt 
	WHERE cds:CDS & dbs:DBS &  dt:DT & obs:OBS
	THEN  currentCDS, currentDBS, currentOBS, currentDT, dbgen, cdload, dbpop, cdpop := cds, dbs, obs, dt, FALSE, FALSE, FALSE, FALSE 
	END
OPERATIONS

m <-- loadCDS(cds) =
	PRE
		cds : CDS
    	THEN
		SELECT ( cdload = FALSE or cds /= currentCDS )	
            		THEN  	currentCDS := cds	||
		          	dbgen := FALSE		||
		          	cdload := TRUE		||
		          	dbpop := FALSE		||
		          	cdpop := FALSE       	||
		          	m := load_success
        	ELSE m := already_loaded
        	END
	END;	

m <-- generateDB = 
	BEGIN
	   	SELECT dbgen = FALSE & cdload = TRUE 
	       		THEN  	currentDBS := schTrans(currentCDS)	||
		         	dbgen := TRUE				||
		         	dbpop := FALSE				||
		         	cdpop := FALSE                       	||
		         	m := gen_success	
        	WHEN dbgen = TRUE 
            		THEN 	m := already_generated
        	ELSE m := no_cds_loaded
        	END
	END;


m <-- populateDB(dt) = 
	PRE
	    dt : DT
    	THEN
        	SELECT dbgen = TRUE & ( dbpop = FALSE or dt /= currentDT ) & dt : dbInst(currentDBS) & dt : ran(dtTrans) /* guarantee that there is a mapping back to obs */
            		THEN  	currentDT := dt   ||
		          	dbpop := TRUE	  ||
		          	cdpop := FALSE    ||
		          	m := popdbs_success
        	WHEN dbgen = FALSE
            		THEN 	m:= no_dbs_generated
        	WHEN dbpop = TRUE & dt = currentDT
            		THEN 	m := already_populated_dbs
        	ELSE m := not_valid_dbs_population
        	END
	END;
	
m <-- populateCD =
	BEGIN
		SELECT dbpop = TRUE & ( cdpop = FALSE or currentOBS /= dtTrans~(currentDT) ) 
			THEN  	currentOBS := dtTrans~(currentDT)	||
		        	cdpop := TRUE             		||
		        	m := popcds_success
        	WHEN dbpop = FALSE
            		THEN 	m := no_dbs_populated
        	ELSE m := already_populated_cds
        	END
	END

END