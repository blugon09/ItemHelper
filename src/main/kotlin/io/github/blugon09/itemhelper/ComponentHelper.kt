package io.github.blugon09.itemhelper

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.format.TextDecoration

fun String.component(): TextComponent {
    return Component.text(this)
}

fun TextComponent.effect(effect : TextDecoration, apply : Boolean): TextComponent {
    return this.decoration(effect, apply)
}