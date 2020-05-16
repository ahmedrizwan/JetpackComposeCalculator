package com.sudo.rizwan.composecalculator.exprk.internal

internal class Token(
    val type: TokenType,
    val lexeme: String,
    val literal: Any?
) {

    override fun toString(): String {
        return type.toString() + " " + lexeme + " " + literal
    }

}