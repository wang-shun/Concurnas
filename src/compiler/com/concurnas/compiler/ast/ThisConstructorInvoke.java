package com.concurnas.compiler.ast;

import com.concurnas.compiler.ast.FuncInvokeArgs;
import com.concurnas.compiler.ast.SuperOrThisConstructorInvoke;
import com.concurnas.compiler.ast.interfaces.Expression;
import com.concurnas.compiler.visitors.Visitor;

public class ThisConstructorInvoke extends SuperOrThisConstructorInvoke {

	public ThisConstructorInvoke(int line, int col, FuncInvokeArgs args) {
		super(line, col, args);
	}

	@Override
	public Object accept(Visitor visitor) {
		visitor.setLastLineVisited(super.getLine());
		return visitor.visit(this);
	}

	@Override
	public Node copyTypeSpecific() {
		ThisConstructorInvoke ret = new ThisConstructorInvoke(super.getLine(), super.getColumn(), (FuncInvokeArgs)args.copy());
		ret.parNestorToAdd=parNestorToAdd;//probably not needed
		ret.resolvedFuncType = resolvedFuncType==null?null:resolvedFuncType.copyTypeSpecific();
		ret.isEnumconstru=isEnumconstru;
		ret.isEnumconstruSubClass=isEnumconstruSubClass;
		ret.preceedingExpression = preceedingExpression==null?null:(Expression)preceedingExpression.copy();

		return ret; 
	}
	private Expression preceedingExpression;
	@Override
	public void setPreceedingExpression(Expression expr) {
		this.preceedingExpression = expr;
	}
	@Override
	public Expression getPreceedingExpression() {
		return preceedingExpression;
	}

}
