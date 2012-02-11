package de.be4.classicalb.core.parser.analysis.transforming;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.be4.classicalb.core.parser.Definitions;
import de.be4.classicalb.core.parser.Utils;
import de.be4.classicalb.core.parser.Definitions.Type;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.exceptions.BParseException;
import de.be4.classicalb.core.parser.node.AAnySubstitution;
import de.be4.classicalb.core.parser.node.AComprehensionSetExpression;
import de.be4.classicalb.core.parser.node.ADefinitionExpression;
import de.be4.classicalb.core.parser.node.ADefinitionSubstitution;
import de.be4.classicalb.core.parser.node.AExistsPredicate;
import de.be4.classicalb.core.parser.node.AExpressionDefinition;
import de.be4.classicalb.core.parser.node.AFuncOpSubstitution;
import de.be4.classicalb.core.parser.node.AFunctionExpression;
import de.be4.classicalb.core.parser.node.AGeneralProductExpression;
import de.be4.classicalb.core.parser.node.AGeneralSumExpression;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.ALambdaExpression;
import de.be4.classicalb.core.parser.node.ALetSubstitution;
import de.be4.classicalb.core.parser.node.AOpSubstitution;
import de.be4.classicalb.core.parser.node.AQuantifiedIntersectionExpression;
import de.be4.classicalb.core.parser.node.AQuantifiedUnionExpression;
import de.be4.classicalb.core.parser.node.ASubstitutionDefinition;
import de.be4.classicalb.core.parser.node.AForallPredicate;
import de.be4.classicalb.core.parser.node.AVarSubstitution;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.PSubstitution;
import de.be4.classicalb.core.parser.node.TDefLiteralSubstitution;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.hhu.stups.sablecc.patch.PositionedNode;
import de.hhu.stups.sablecc.patch.SourcePositions;

/**
 * <p>
 * This visitor performs 2 AST transformations which need to be done in one DFS
 * cause they influence each other when definitions are involved. The reason is
 * that in some cases we cannot decide during preparsing and parsing, if the RHS
 * of a definition is an expression or a substitution. Function and operation
 * calls are syntactically the same in most cases. So we postpone the decision
 * until this DFS is made. The first usage of a definition determines which type
 * is asumed. All other uses need to follow this typing otherwise an error is
 * thrown.
 * </p>
 * <p>
 * During parsing substitutions that are operation calls without return values
 * are recognized as {@link AFuncOpSubstitution}. These
 * {@link AFuncOpSubstitution} nodes can contain any expression. So the parser
 * accepts any expression as substitution here.
 * </p>
 * <p>
 * This visitor checks any {@link AFuncOpSubstitution} if the child of the node
 * is of type {@link AFunctionExpression} (i.e. operation call with parameters)
 * or {@link AIdentifierExpression} (i.e. operation call without parameters).
 * Then the {@link AFuncOpSubstitution} node is replaced by a coresponding
 * {@link AOpSubstitution} node.
 * </p>
 * <p>
 * If the child is any other expression type, a {@link BParseException} is
 * thrown.
 * </p>
 * <p>
 * Definitions with type Expression are not recognized during parsing. In the
 * resulting AST they are not distinguishable from normal
 * {@link AIdentifierExpression} or {@link AFunctionExpression} nodes.
 * </p>
 * <p>
 * Therefor this visitor searches {@link AIdentifierExpression} and
 * {@link AFunctionExpression} nodes and checks if their name is declared as a
 * definition. If such a node is found, it is replaced by a new node
 * {@link ADefinitionExpression}.
 * </p>
 * <p>
 * The visitor keeps track of scoped variables to ensure, that no node of a
 * scoped variable is replaced.
 * </p>
 * 
 * @author Fabian
 * 
 */
public class OpSubstitutions extends DepthFirstAdapter {

	private final SourcePositions sourcePositions;
	private final Definitions definitions;
	private final Map<String, Integer> scopedVariables = new HashMap<String, Integer>();

	public OpSubstitutions(final SourcePositions sourcePositions,
			final Definitions definitions) {
		this.sourcePositions = sourcePositions;
		this.definitions = definitions;
	}

	@Override
	public void caseAFuncOpSubstitution(final AFuncOpSubstitution node) {
		final PExpression expression = node.getFunction();
		PExpression idExpr = null;
		LinkedList<PExpression> parameters = null;
		Type type = null;
		TIdentifierLiteral idToken = null;
		String idString = null;

		if (expression instanceof AFunctionExpression) {
			// the operation was parsed as a function expression
			final AFunctionExpression function = (AFunctionExpression) expression;
			final PExpression funcId = function.getIdentifier();

			if (funcId instanceof AIdentifierExpression) {
				final AIdentifierExpression identifier = (AIdentifierExpression) funcId;
				idString = Utils.getIdentifierAsString(identifier
						.getIdentifier());
				idToken = identifier.getIdentifier().get(0);
				type = definitions.getType(idString);
			} else {
				type = Type.NoDefinition;
			}

			idExpr = function.getIdentifier();
			parameters = new LinkedList<PExpression>(function.getParameters());
		} else if (expression instanceof AIdentifierExpression) {
			// the operation was parsed as an identifier expression
			final AIdentifierExpression identifier = (AIdentifierExpression) expression;
			idString = Utils.getIdentifierAsString(identifier.getIdentifier());
			idToken = identifier.getIdentifier().get(0);
			type = definitions.getType(idString);

			idExpr = expression;
			parameters = new LinkedList<PExpression>();
		} else {
			// some other expression was parsed (NOT allowed)
			throw new BParseException(null, sourcePositions
					.getSourcecodeRange(node), "Expecting operation");
		}

		if (type != Type.NoDefinition) {
			if (type == Type.Substitution || type == Type.ExprOrSubst) {
				// create DefinitionSubstitution
				final ADefinitionSubstitution defSubst = new ADefinitionSubstitution(
						new TDefLiteralSubstitution(idToken.getText(), idToken
								.getLine(), idToken.getPos()), parameters);

				if (type == Type.ExprOrSubst) {
					// type is determined now => set to Substitution
					setTypeSubstDef(node, idString);
				}

				// transfer position information
				if (node instanceof PositionedNode){
					final PositionedNode posNode = (PositionedNode) node;
					final PositionedNode newPosNode = (PositionedNode) defSubst;
					newPosNode.setStartPos(posNode.getStartPos());
					newPosNode.setEndPos(posNode.getEndPos());
					sourcePositions.replaceMapping(posNode, newPosNode);
				}

				node.replaceBy(defSubst);
				defSubst.apply(this);
			} else {
				// finding some other type here is an error!
				throw new BParseException(null, sourcePositions
						.getSourcecodeRange(node),
						"Expecting substitution here but found definition with type '"
								+ type + "'");
			}
		} else {
			// no def, no problem ;-)
			final AOpSubstitution opSubst = new AOpSubstitution(idExpr,
					parameters);
			sourcePositions.replaceMapping(node, opSubst);
			node.replaceBy(opSubst);
			opSubst.apply(this);
		}
	}

	@Override
	public void inAExistsPredicate(
			final AExistsPredicate node) {
		enterScope(node.getIdentifiers());
	}

	@Override
	public void inAForallPredicate(
			final AForallPredicate node) {
		enterScope(node.getIdentifiers());
	}

	@Override
	public void inAGeneralSumExpression(final AGeneralSumExpression node) {
		enterScope(node.getIdentifiers());
	}

	@Override
	public void inAGeneralProductExpression(final AGeneralProductExpression node) {
		enterScope(node.getIdentifiers());
	}

	@Override
	public void inALambdaExpression(final ALambdaExpression node) {
		enterScope(node.getIdentifiers());
	}

	@Override
	public void inAComprehensionSetExpression(
			final AComprehensionSetExpression node) {
		enterScope(node.getIdentifiers());
	}

	@Override
	public void inAQuantifiedUnionExpression(
			final AQuantifiedUnionExpression node) {
		enterScope(node.getIdentifiers());
	}

	@Override
	public void inAQuantifiedIntersectionExpression(
			final AQuantifiedIntersectionExpression node) {
		enterScope(node.getIdentifiers());
	}

	@Override
	public void inAAnySubstitution(final AAnySubstitution node) {
		enterScope(node.getIdentifiers());
	}

	@Override
	public void inALetSubstitution(final ALetSubstitution node) {
		enterScope(node.getIdentifiers());
	}

	@Override
	public void inAVarSubstitution(final AVarSubstitution node) {
		enterScope(node.getIdentifiers());
	}

	@Override
	public void outAExistsPredicate(
			final AExistsPredicate node) {
		leaveScope(node.getIdentifiers());
	}

	@Override
	public void outAForallPredicate(
			final AForallPredicate node) {
		leaveScope(node.getIdentifiers());
	}

	@Override
	public void outAGeneralSumExpression(final AGeneralSumExpression node) {
		leaveScope(node.getIdentifiers());
	}

	@Override
	public void outAGeneralProductExpression(
			final AGeneralProductExpression node) {
		leaveScope(node.getIdentifiers());
	}

	@Override
	public void outALambdaExpression(final ALambdaExpression node) {
		leaveScope(node.getIdentifiers());
	}

	@Override
	public void outAComprehensionSetExpression(
			final AComprehensionSetExpression node) {
		leaveScope(node.getIdentifiers());
	}

	@Override
	public void outAQuantifiedIntersectionExpression(
			final AQuantifiedIntersectionExpression node) {
		leaveScope(node.getIdentifiers());
	}

	@Override
	public void outAQuantifiedUnionExpression(
			final AQuantifiedUnionExpression node) {
		leaveScope(node.getIdentifiers());
	}

	@Override
	public void outAAnySubstitution(final AAnySubstitution node) {
		leaveScope(node.getIdentifiers());
	}

	@Override
	public void outALetSubstitution(final ALetSubstitution node) {
		leaveScope(node.getIdentifiers());
	}

	@Override
	public void outAVarSubstitution(final AVarSubstitution node) {
		leaveScope(node.getIdentifiers());
	}

	@Override
	public void caseAIdentifierExpression(final AIdentifierExpression node) {

		final String identifierString = Utils.getIdentifierAsString(node
				.getIdentifier());
		final Integer number = scopedVariables.get(identifierString);
		final Type type = definitions.getType(identifierString);

		if (number == null && type != Type.NoDefinition) {
			if (type == Type.Expression || type == Type.ExprOrSubst) {
				/*
				 * getFirst() is enough cause definitions cannot have composed
				 * identifiers
				 */
				replaceWithDefExpression(node, node.getIdentifier().getFirst(),
						null);

				if (type == Type.ExprOrSubst) {
					// type is determined now => set to Expression
					final AExpressionDefinition definition = (AExpressionDefinition) definitions
							.removeDefinition(identifierString);
					definitions.addDefinition(definition, Type.Expression);
				}
			} else {
				// finding some other type here is an error!
				throw new BParseException(null, sourcePositions
						.getSourcecodeRange(node),
						"Expecting expression here but found definition with type '"
								+ type + "'");
			}
		}
	}

	@Override
	public void caseAFunctionExpression(final AFunctionExpression node) {
		if (node.getIdentifier() != null) {
			node.getIdentifier().apply(this);
		}

		if (node.getIdentifier() instanceof ADefinitionExpression) {
			final LinkedList<PExpression> paramList = new LinkedList<PExpression>(
					node.getParameters());
			final TIdentifierLiteral identifier = ((ADefinitionExpression) node
					.getIdentifier()).getDefLiteral();

			if (paramList.size() <= definitions.getParameterCount(identifier
					.getText())) {
				/*
				 * The parameters seem to belong to this definition, so we need
				 * to replace the FunctionExpression by a
				 * DefinitionFunctionExpression. If not enough parameters were
				 * given this will be found by a later check, i.e.
				 * DefinitionUsageCheck.
				 */
				final ADefinitionExpression newNode = replaceWithDefExpression(
						node, identifier, paramList);

				final List<PExpression> copy = newNode.getParameters();
				for (final PExpression e : copy) {
					e.apply(this);
				}

				return;
			}
		}

		/*
		 * Reached in case that: Identifier of this FunctionExpression is not a
		 * definition or there were more parameters than the definition needs
		 * (by declaration), so we asume the parameters belong to some other
		 * construct (for example a function a level higher in the AST).
		 */
		final List<PExpression> copy = node.getParameters();
		for (final PExpression e : copy) {
			e.apply(this);
		}
	}

	private ADefinitionExpression replaceWithDefExpression(final Node node,
			TIdentifierLiteral identifier, final List<PExpression> paramList) {
		
		final ADefinitionExpression newNode = new ADefinitionExpression();
		newNode.setDefLiteral(identifier);

		if (paramList != null) {
			newNode.setParameters(paramList);
		}
		
		if (node instanceof PositionedNode){
			final PositionedNode posNode = (PositionedNode) node;
			final PositionedNode newPosNode = (PositionedNode) newNode;
			newPosNode.setStartPos(posNode.getStartPos());
			newPosNode.setEndPos(posNode.getEndPos());
			sourcePositions.replaceMapping(posNode, newPosNode);
		}

		node.replaceBy(newNode);

		return newNode;
	}

	private void enterScope(final LinkedList<PExpression> identifiers2) {
		for (final PExpression expression : identifiers2) {
			if (expression instanceof AIdentifierExpression) {
				final String identifierString = Utils
						.getIdentifierAsString(((AIdentifierExpression) expression)
								.getIdentifier());

				if (scopedVariables.containsKey(identifierString)) {
					scopedVariables.put(identifierString, scopedVariables
							.get(identifierString) + 1);
				} else {
					scopedVariables.put(identifierString, 1);
				}
			} else {
				// IGNORE, typechecking is done later
			}
		}
	}

	private void leaveScope(final LinkedList<PExpression> identifiers) {
		for (final PExpression expression : identifiers) {
			if (expression instanceof AIdentifierExpression) {
				final String identifierString = Utils
						.getIdentifierAsString(((AIdentifierExpression) expression)
								.getIdentifier());
				final Integer number = scopedVariables.get(identifierString);

				if (number > 1) {
					scopedVariables.put(identifierString, number - 1);
				} else {
					scopedVariables.remove(identifierString);
				}
			} else {
				// IGNORE, typechecking is done later
			}
		}
	}

	private void setTypeSubstDef(final AFuncOpSubstitution node,
			final String idString) {
		final AExpressionDefinition removedDef = (AExpressionDefinition) definitions
				.removeDefinition(idString);
		final Node defRhs = removedDef.getRhs();
		final PSubstitution rhsSubst;

		if (defRhs instanceof AFunctionExpression) {
			final AFunctionExpression rhsFunction = (AFunctionExpression) defRhs;
			rhsSubst = new AOpSubstitution(rhsFunction.getIdentifier(),
					new LinkedList<PExpression>(rhsFunction.getParameters()));
		} else if (defRhs instanceof AIdentifierExpression) {
			final AIdentifierExpression rhsIdent = (AIdentifierExpression) defRhs;
			rhsSubst = new AOpSubstitution(rhsIdent,
					new LinkedList<PExpression>());
		} else {
			// some other expression was parsed (NOT allowed)
			throw new BParseException(null, sourcePositions
					.getSourcecodeRange(node), "Expecting operation");
		}

		final TIdentifierLiteral oldDefId = removedDef.getName();
		final TDefLiteralSubstitution defId = new TDefLiteralSubstitution(
				oldDefId.getText(), oldDefId.getLine(), oldDefId.getPos());
		final ASubstitutionDefinition substDef = new ASubstitutionDefinition(
				defId, new LinkedList<PExpression>(removedDef.getParameters()),
				rhsSubst);

		definitions.addDefinition(substDef, Type.Substitution);
		sourcePositions.replaceMapping(removedDef, substDef);
		removedDef.replaceBy(substDef);
	}
}
