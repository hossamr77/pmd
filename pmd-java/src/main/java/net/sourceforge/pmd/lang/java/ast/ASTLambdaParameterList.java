/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.java.ast;

import net.sourceforge.pmd.lang.java.ast.ASTList.ASTMaybeEmptyListOf;

/**
 * The parameter list of a {@linkplain ASTLambdaExpression lambda expression}.
 *
 * <pre class="grammar">
 *
 * LambdaParameterList ::= "(" ")"
 *                       | "(" {@link ASTLambdaParameter LambdaParameter} ("," {@link ASTLambdaParameter LambdaParameter})*")"
 *
 * </pre>
 */
public final class ASTLambdaParameterList extends ASTMaybeEmptyListOf<ASTLambdaParameter> {

    ASTLambdaParameterList(int id) {
        super(id, ASTLambdaParameter.class);
    }


    @Override
    public Object jjtAccept(JavaParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }


    @Override
    public <T> void jjtAccept(SideEffectingVisitor<T> visitor, T data) {
        visitor.visit(this, data);
    }
}
