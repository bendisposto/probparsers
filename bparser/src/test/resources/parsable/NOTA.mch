MACHINE NOTA
	/*
		Translation from UML2 model 
		generated on 24/9/2005 at 15:13:10
	*/


SETS
	/* Generated from classes and enumerations */
	Application;
	InterconnectNode;
	Service;
	Socket;
	LocalSocket;
	RemoteSocket;
	TargetSocket;
	Connection;
	ResourceManager;
	Property;
	ResourceManagerTableEntry;
	ResourceManagerMessages = { RM_REGISTRATION_SUCCESS, RM_REGISTRATION_FAILURE }

DEFINITIONS
	/* Generated from defintions list */
	disjoint(f) == !(a1,a2).( a1:dom(f) & a2:dom(f) & a1/=a2 => f(a1)/\f(a2)={})

VARIABLES
	/* object instances are postpended with _objects */
	Application_objects,
	hostinterconnectnode,
	socketapplication,
	applicationCredentials,
	InterconnectNode_objects,
	socketinterconnectnode,
	theResourceManager,
	interconnectnodeservice,
	ic_rm_address,
	Service_objects,
	Socket_objects,
	LocalSocket_objects,
	RemoteSocket_objects,
	TargetSocket_objects,
	Connection_objects,
	ResourceManager_objects,
	rm_rmtconnection,
	Property_objects,
	ResourceManagerTableEntry_objects,
	ResourceManagerTableEntry_credentials

INVARIANT
	/* relationships in the form:  assocname : source_objects X target_objects */
	/*                             union and disjoin clauses as necessary */
	/* variables in the form:      CLASS_objects : source_objects X targetType */
	ResourceManagerTableEntry_objects : POW(ResourceManagerTableEntry) &
	Property_objects : POW(Property) &
	ResourceManager_objects : POW(ResourceManager) &
	Connection_objects : POW(Connection) &
	TargetSocket_objects : POW(TargetSocket) &
	RemoteSocket_objects : POW(RemoteSocket) &
	LocalSocket_objects : POW(LocalSocket) &
	Socket_objects : POW(Socket) &
	Service_objects : POW(Service) &
	InterconnectNode_objects : POW(InterconnectNode) &
	Application_objects : POW(Application) &
	hostinterconnectnode:Application_objects --> InterconnectNode_objects &
	socketapplication:Application_objects --> POW(Socket_objects) &
	union(ran(socketapplication)) = Socket_objects &
	disjoint(socketapplication) &
	applicationCredentials:Application_objects --> POW1(Property_objects) &
	socketinterconnectnode:InterconnectNode_objects --> POW(Socket_objects) &
	union(ran(socketinterconnectnode)) = Socket_objects &
	disjoint(socketinterconnectnode) &
	theResourceManager:InterconnectNode_objects >+> ResourceManager_objects &
	interconnectnodeservice:InterconnectNode_objects --> POW(Application_objects) &
	union(ran(interconnectnodeservice)) = Application_objects &
	disjoint(interconnectnodeservice) &
	ic_rm_address:InterconnectNode_objects +-> ResourceManager_objects &
	rm_rmtconnection:ResourceManager_objects --> POW(ResourceManagerTableEntry_objects) &
	union(ran(rm_rmtconnection)) = ResourceManagerTableEntry_objects &
	disjoint(rm_rmtconnection) &
	ResourceManagerTableEntry_credentials : ResourceManagerTableEntry_objects +-> Property_objects
	&
	/* class invariants and other constraints over the model */
	/* Constraint: Applpication-ICNode-Socket triangle */
	!self. ( self : Application_objects => ( socketapplication(self) <: socketinterconnectnode(hostinterconnectnode(self)) ) ) &
	/* Constraint: Application/Service in set of IC node's known applications and services */
	!self. ( self : Application_objects => ( self : interconnectnodeservice(hostinterconnectnode(self)) ) )

INITIALISATION
	/* Default initialisation is everything is assigned the empty set */
	Application_objects := {} ||
	hostinterconnectnode := {} ||
	socketapplication := {} ||
	applicationCredentials := {} ||
	InterconnectNode_objects := {} ||
	socketinterconnectnode := {} ||
	theResourceManager := {} ||
	interconnectnodeservice := {} ||
	ic_rm_address := {} ||
	Service_objects := {} ||
	Socket_objects := {} ||
	LocalSocket_objects := {} ||
	RemoteSocket_objects := {} ||
	TargetSocket_objects := {} ||
	Connection_objects := {} ||
	ResourceManager_objects := {} ||
	rm_rmtconnection := {} ||
	Property_objects := {} ||
	ResourceManagerTableEntry_objects := {} ||
	ResourceManagerTableEntry_credentials := {}

OPERATIONS

	/* operations in the form  :  CLASS_OPERATIONNAME(self,[PARAMETERS]*) */
	/* constructors in the form:  CLASS_OPERATIONNAME([PARAMETERS]*) */
	Application_acceptConnectionRequest(self)=
	PRE
		self : Application_objects
	THEN
		skip
	END;

	self_out <-- Application_Application(pp,ii)=
	PRE
		pp : POW1( Property_objects ) &
		ii : InterconnectNode_objects &
		card(pp)>0
	THEN
		ANY self
		WHERE self : Application - Application_objects
		THEN
			Application_objects := Application_objects \/ { self } ||
			applicationCredentials(self) := pp ||
hostinterconnectnode(self) := ii ||
socketapplication(self) := {} ||
interconnectnodeservice(ii) := interconnectnodeservice(ii) \/ {self} ||
			self_out := self
		END
	END;

	InterconnectNode_announceResourceManagerAddress(self,rmAddress)=
	PRE
		self : InterconnectNode_objects &
		rmAddress : ResourceManager_objects &
		{ic_rm_address(self)} = {}
	THEN
		ic_rm_address(self) := rmAddress
	END;

	InterconnectNode_connect(self,targetService)=
	PRE
		self : InterconnectNode_objects &
		targetService : Service_objects &
		not({ic_rm_address(self)}={})
	THEN
		skip
	END;

	InterconnectNode_getICAddress(self)=
	PRE
		self : InterconnectNode_objects
	THEN
		skip
	END;

	InterconnectNode_requestTargetSocket(self)=
	PRE
		self : InterconnectNode_objects
	THEN
		skip
	END;

	self_out <-- InterconnectNode_InterconnectNode=
	BEGIN
		ANY self
		WHERE self : InterconnectNode - InterconnectNode_objects
		THEN
			InterconnectNode_objects := InterconnectNode_objects \/ { self } ||
			socketinterconnectnode(self) := {} ||
interconnectnodeservice(self) := {} ||
			self_out := self
		END
	END;

	self_out <-- Socket_Socket(icnode,app)=
	PRE
		icnode : InterconnectNode_objects &
		app : Application_objects &
		app : interconnectnodeservice(icnode)
	THEN
		ANY self
		WHERE self : Socket - Socket_objects
		THEN
			Socket_objects := Socket_objects \/ { self } ||
			socketinterconnectnode(icnode) := socketinterconnectnode(icnode) \/ {self} ||
socketapplication(app) := socketapplication(app) \/ {self} ||
			self_out := self
		END
	END;

	TargetSocket_announceRemoteSocketIdentity(self)=
	PRE
		self : TargetSocket_objects
	THEN
		skip
	END;

	ResourceManager_register(self)=
	PRE
		self : ResourceManager_objects
	THEN
		skip
	END;

	ResourceManager_deRegister(self)=
	PRE
		self : ResourceManager_objects
	THEN
		skip
	END;

	ResourceManager_getSID(self)=
	PRE
		self : ResourceManager_objects
	THEN
		skip
	END;

	ResourceManager_getICNodeAddress(self)=
	PRE
		self : ResourceManager_objects
	THEN
		skip
	END;

	ResourceManager_getICAddress(self)=
	PRE
		self : ResourceManager_objects
	THEN
		skip
	END;

	self_out <-- ResourceManager_ResourceManager(icnode)=
	PRE
		icnode : InterconnectNode_objects
	THEN
		ANY self
		WHERE self : ResourceManager - ResourceManager_objects
		THEN
			ResourceManager_objects := ResourceManager_objects \/ { self } ||
			theResourceManager(icnode):=self ||
rm_rmtconnection(self) := {} ||
ic_rm_address(icnode) := self ||
			self_out := self
		END
	END;

	self_out <-- Property_Property=
	BEGIN
		ANY self
		WHERE self : Property - Property_objects
		THEN
			Property_objects := Property_objects \/ { self } ||
			self_out := self
		END
	END


END